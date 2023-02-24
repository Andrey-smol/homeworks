package ru.avalon.javapp.devj140.userGUI.MainApplication;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import ru.avalon.javapp.devj140.userGUI.BusinessLogic.CommandsName;
import ru.avalon.javapp.devj140.userGUI.BusinessLogic.Program;
import ru.avalon.javapp.devj140.userGUI.BusinessLogic.SharedResource;

import javax.swing.*;
import java.beans.EventHandler;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLUserGUIController implements Initializable {

    @FXML
    private Text message;
    @FXML
    private Button signIn;
    @FXML
    private TextField userNameText;
    @FXML
    private TextField userPasswordText;

    private List<String> buf;

    @FXML
    private void checkUser(){
        if(userNameText == null || userNameText.getText().isEmpty()) {
            setMessage("Не определено поле имя", Message.ERROR);
            return;
        }
        if(userPasswordText == null || userPasswordText.getText().isEmpty()) {
            setMessage("Не определено поле пароль", Message.ERROR);
            return;
        }
        if(userPasswordText == null || userPasswordText.getText().isEmpty()) {
            setMessage("Не определено поле пароль", Message.ERROR);
            return;
        }
        setMessage("Подключение к базе данных", Message.OK);

        connectToDb();
    }
    @FXML
    private void clearMessage(){
        message.setText("");
    }

    public void setMessage(String mess, Message value){
        SwingUtilities.invokeLater(()-> {
            message.setText(mess);
            if (value == Message.ERROR) message.setFill(Color.RED);
            if (value == Message.WARNING) message.setFill(Color.YELLOW);
        });
    }

    public void connectToDb(){
         Common.sharedResource.writeCommand(CommandsName.CHECK_USER + " <" + userNameText.getText() + ">,<" + userPasswordText.getText() + ">");
        try {
            buf = (List<String>) Common.sharedResource.readDataBuf();

            if(buf.size() > 0) {
                buf.stream().forEach(System.out::println);
                String res = buf.get(0);
                if(res.equals("OK")){
                    //setMessage("Аутенфикация прошла успешно", Message.OK);
                    new ClientGUI().init();
                    return;
                }
                else message.setText(res);
            }
            setMessage("Не удалось пройти аутенфикацию", Message.ERROR);
        }
        catch (InterruptedException e){
            setMessage("Не удалось пройти аутенфикацию", Message.ERROR);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buf = new ArrayList<>();
        new Program(Common.sharedResource);
    }

}
