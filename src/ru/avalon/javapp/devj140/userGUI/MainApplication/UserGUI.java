package ru.avalon.javapp.devj140.userGUI.MainApplication;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.avalon.javapp.devj140.userGUI.BusinessLogic.CommandsName;
import ru.avalon.javapp.devj140.userGUI.BusinessLogic.Program;
import ru.avalon.javapp.devj140.userGUI.BusinessLogic.SharedResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserGUI  extends Application {


    TextField userNameText;
    PasswordField userPasswordText;
    Text message;
    Program program;
    SharedResource sharedResource = new SharedResource();
    List<String> buf = new ArrayList<>();
    Stage stage;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void init() throws Exception {
        program = new Program(sharedResource);
        super.init();
    }

    @Override
    public void start(Stage stage) throws Exception {

        
        GridPane root = new GridPane();

        root.setPadding(new Insets(20));
        root.setHgap(25);
        root.setVgap(15);

        Label labelTitle = new Label("Enter your user name and password!");
        labelTitle.setFont(new Font(20));

        // Put on cell (0,0), span 2 column, 1 row.
        root.add(labelTitle, 0, 0, 2, 1);

        Label labelUserName = new Label("User Name");
        userNameText = new TextField();

        Label labelPassword = new Label("User Password");

        userPasswordText = new PasswordField();

        Button signIn = new Button("Sign in");
        signIn.setOnAction(e->checkUser());

        GridPane.setHalignment(labelUserName, HPos.RIGHT);

        // Put on cell (0,1)
        root.add(labelUserName, 0, 1);


        GridPane.setHalignment(labelPassword, HPos.RIGHT);
        root.add(labelPassword, 0, 2);

        // Horizontal alignment for User Name field.
        GridPane.setHalignment(userNameText, HPos.LEFT);
        root.add(userNameText, 1, 1);

        // Horizontal alignment for Password field.
        GridPane.setHalignment(userPasswordText, HPos.LEFT);
        root.add(userPasswordText, 1, 2);

        // Horizontal alignment for Login button.
        GridPane.setHalignment(signIn, HPos.RIGHT);
        root.add(signIn, 1, 3);

        message = new Text();
        root.add(message, 1, 5);


        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 600, 300);
        stage.setScene(scene);
        stage.setTitle("Authentication");
        stage.show();
    }

    private void checkUser(){
        if(userNameText == null || userNameText.getText().isEmpty()) {
            message.setText("Не определено поле имя");
            return;
        }
        if(userPasswordText == null || userPasswordText.getText().isEmpty()) {
            message.setText("Не определено поле пароль");
            return;
        }
        message.setText("Подключение к базе данных");
        sharedResource.writeCommand(CommandsName.CHECK_USER + " <" + userNameText.getText() + ">,<" + userPasswordText.getText() + ">");
        try {
            buf = (List<String>) sharedResource.readDataBuf();

            if(buf.size() > 0) {
                buf.stream().forEach(System.out::println);
                String res = buf.get(0);
                if(res.equals("OK")){
                    message.setText("OK");
                    new ClientGUI(sharedResource).init();

                    return;
                }
                else message.setText(res);
            }
            message.setText("ERROR authentication");
        }
        catch (InterruptedException e){

        }
    }

    @Override
    public void stop() throws Exception {
        sharedResource.writeCommand(CommandsName.STOP);
    }
}
