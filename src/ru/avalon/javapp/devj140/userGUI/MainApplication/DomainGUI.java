package ru.avalon.javapp.devj140.userGUI.MainApplication;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DomainGUI extends Stage {

    private final int personId;

    public DomainGUI(int id) {

        this.personId = id;
    }

    public void init() {

        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDomainGUI.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            setScene(scene);
            setTitle("Domains");

            initModality(Modality.APPLICATION_MODAL);
            if(!((FXMLDomainGUIController)loader.getController()).initTable(personId)) return;
            showAndWait();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
