package ru.avalon.javapp.devj140.userGUI.MainApplication;

import com.mysql.cj.protocol.Message;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import ru.avalon.javapp.devj140.userGUI.BusinessLogic.CommandsName;
import ru.avalon.javapp.devj140.userGUI.BusinessLogic.Program;
import ru.avalon.javapp.devj140.userGUI.BusinessLogic.SharedResource;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserGUI  extends Application {

    //private TextField userNameText;
    //private PasswordField userPasswordText;
    //private Text message;
    //private Program program;
    //private SharedResource sharedResource = new SharedResource();
    //private List<String> buf = new ArrayList<>();
    //private Timer timer;

    public static void main(String[] args){
        launch(args);
    }

//    @Override
//    public void init() throws Exception {
//        //program = new Program(sharedResource);
//        super.init();
//    }

    @Override
    public void start(Stage stage) {

        //GridPane root = new GridPane();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FXMLUserGUI.fxml"));
            //message = new Text();
            //timer = new Timer(10000, evt -> processTimer());

//        root.setPadding(new Insets(20));
//        root.setHgap(25);
//        root.setVgap(15);

//        Label labelTitle = new Label("Welcome!");
//        labelTitle.setFont(new Font(20));
//        root.add(labelTitle, 0, 0, 2, 1);
//        Label labelUserName = new Label("User Name");
//        userNameText = new TextField();
//        userNameText.textProperty().addListener(e -> message.setText(""));
//
//        Label labelPassword = new Label("User Password");
//        userPasswordText = new PasswordField();
//        userPasswordText.textProperty().addListener(e -> message.setText(""));
//
//        Button signIn = new Button("Sign in");
//        signIn.setOnAction(e->checkUser());
//
//        GridPane.setHalignment(labelUserName, HPos.RIGHT);
//        root.add(labelUserName, 0, 1);
//
//        GridPane.setHalignment(labelPassword, HPos.RIGHT);
//        root.add(labelPassword, 0, 2);
//
//        GridPane.setHalignment(userNameText, HPos.LEFT);
//        root.add(userNameText, 1, 1);
//
//        GridPane.setHalignment(userPasswordText, HPos.LEFT);
//        root.add(userPasswordText, 1, 2);
//
//        GridPane.setHalignment(signIn, HPos.RIGHT);
//        root.add(signIn, 1, 3);
//
//        root.add(message, 0, 5, 2, 1);
//        root.setAlignment(Pos.CENTER);

            Scene scene = new Scene(root);//, 600, 300);
            stage.setScene(scene);
            stage.setTitle("Authentication");
            stage.show();
        }
        catch (IOException e){
            System.out.println("ERROR" + e.getMessage());
            e.printStackTrace();
        }
    }

//    public SharedResource getSharedResource() {
//        return sharedResource;
//    }

//    private void checkUser(){
//        if(userNameText == null || userNameText.getText().isEmpty()) {
//            setMessage("Не определено поле имя", Message.ERROR);
//            return;
//        }
//        if(userPasswordText == null || userPasswordText.getText().isEmpty()) {
//            setMessage("Не определено поле пароль", Message.ERROR);
//            return;
//        }
//        //setTimeout(10000);
//        setMessage("Подключение к базе данных", Message.OK);
//        sharedResource.writeCommand(CommandsName.CHECK_USER + " <" + userNameText.getText() + ">,<" + userPasswordText.getText() + ">");
//        try {
//            buf = (List<String>) sharedResource.readDataBuf();
//
//            if(buf.size() > 0) {
//                buf.stream().forEach(System.out::println);
//                String res = buf.get(0);
//                if(res.equals("OK")){
//                    //setMessage("Аутенфикация прошла успешно", Message.OK);
//                    new ClientGUI(sharedResource).init();
//                    return;
//                }
//                else message.setText(res);
//            }
//            setMessage("Не удалось пройти аутенфикацию", Message.ERROR);
//        }
//        catch (InterruptedException e){
//            setMessage("Не удалось пройти аутенфикацию", Message.ERROR);
//        }
//    }

//    private void setMessage(String mess, Message value){
//        SwingUtilities.invokeLater(()-> {
//            message.setText(mess);
//            if (value == Message.ERROR) message.setFill(Color.RED);
//            if (value == Message.WARNING) message.setFill(Color.YELLOW);
//        });
//    }

//    private void processTimer(){
//        timer.stop();
//    }
//    private void restartTimer(){
//        timer.restart();
//    }
//    private void setTimeout(int value){
//        timer.setDelay(value);
//        timer.start();
//    }

//    @Override
//    public void stop() {
//
//       // sharedResource.writeCommand(CommandsName.STOP);
//    }

//    enum Message {
//        ERROR,
//        WARNING,
//        OK
//    }
}
