package ru.avalon.javapp.devj140.userGUI.MainApplication;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ClientGUI extends Stage {

    public void init(){

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FXMLClientGUI.fxml"));

            Scene scene = new Scene(root);
            setScene(scene);
            setTitle("Persons");

            Stage stage = (Stage) getWindows().get(0);
            stage.close();
            //setOnCloseRequest(e -> Common.sharedResource.writeCommand(CommandsName.STOP));
            show();
        }
        catch (IOException e){

            System.out.println("ERROR" + e.getMessage());
            e.printStackTrace();
        }
    }
}
