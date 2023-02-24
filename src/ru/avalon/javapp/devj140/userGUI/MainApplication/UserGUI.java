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

    //private Timer timer;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FXMLUserGUI.fxml"));
            //timer = new Timer(10000, evt -> processTimer());
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

    @Override
    public void stop() {

       Common.sharedResource.writeCommand(CommandsName.STOP);
    }

}
