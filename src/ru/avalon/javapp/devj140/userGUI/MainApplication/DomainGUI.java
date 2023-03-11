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
            FXMLDomainGUIController fdc = loader.getController();
            if(!fdc.initTable(personId)) return;
            fdc.setScene(scene);
            fdc.setStage(this);
            showAndWait();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
