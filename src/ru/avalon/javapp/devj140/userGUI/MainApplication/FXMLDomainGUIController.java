package ru.avalon.javapp.devj140.userGUI.MainApplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ru.avalon.javapp.devj140.userGUI.BusinessLogic.CommandsName;
import ru.avalon.javapp.devj140.userGUI.Models.Domain;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLDomainGUIController implements Initializable {

    @FXML
    private TableView<Domain> table;
    private String message = "";

    private Scene scene;
    private Stage stage;

    private String styleMy = getClass().getResource("Style/styleUserGUI.css").toExternalForm();

    private List<Domain> item = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("webName"));
        table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("domainName"));
        table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("ip"));
        table.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dateReg"));
        table.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("countryReg"));
        table.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("personId"));
    }

    public boolean initTable(int personId){
        if(!getDomainById(personId)){
            Alert mess = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
            mess.showAndWait();
            return false;
        }
        ObservableList<Domain> obList = FXCollections.observableList(item);
        table.setItems(obList);
        return true;
    }

    private boolean getDomainById(int id){

        Common.sharedResource.writeCommand(CommandsName.GET_TABLE_DOMAIN_ID + "<" + id + ">");
        try {
            List<?> list = Common.sharedResource.readDataBuf();
            if(list.size() > 0 && (list.get(0) instanceof Domain)){
                item = (List<Domain>) list;
                return true;
            }
            message = list.get(0).toString();
        }
        catch (InterruptedException e){
            message = "ERROR read date \n" + e.getMessage();
        }
        return false;
    }

    @FXML
    private void close(){
        stage.close();
    }
    @FXML
    private void changeStyle(){
        if(!scene.getStylesheets().contains(styleMy)){
            scene.getStylesheets().clear();
            scene.getStylesheets().add(styleMy);
        }
        else scene.getStylesheets().clear();
        //System.out.println(scene.getStylesheets());
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
    public void setStage(Stage stage){
       this.stage = stage;
    }
}
