package ru.avalon.javapp.devj140.userGUI.MainApplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ru.avalon.javapp.devj140.userGUI.BusinessLogic.CommandsName;
import ru.avalon.javapp.devj140.userGUI.BusinessLogic.SharedResource;
import ru.avalon.javapp.devj140.userGUI.Models.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
