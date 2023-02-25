package ru.avalon.javapp.devj140.userGUI.MainApplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import ru.avalon.javapp.devj140.userGUI.BusinessLogic.CommandsName;
import java.io.IOException;

public class UserGUI  extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FXMLUserGUI.fxml"));
            //root.setStyle("-fx-background-color:#ffebcd;");
            Scene scene = new Scene(root);
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
