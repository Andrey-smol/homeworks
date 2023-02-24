package ru.avalon.javapp.devj140.userGUI.MainApplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.avalon.javapp.devj140.userGUI.BusinessLogic.CommandsName;
import ru.avalon.javapp.devj140.userGUI.BusinessLogic.SharedResource;
import ru.avalon.javapp.devj140.userGUI.Models.Domain;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DomainGUI extends Stage {

    public static int PERSONID;

    public DomainGUI(int id) {

        PERSONID = id;
    }

    public void init() {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("FXMLDomainGUI.fxml"));
            Scene scene = new Scene(root);
            setScene(scene);
            setTitle("Domains");

            initModality(Modality.APPLICATION_MODAL);
            showAndWait();
        } catch (IOException e) {

        }
    }
}
