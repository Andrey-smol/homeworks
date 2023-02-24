package ru.avalon.javapp.devj140.userGUI.MainApplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.avalon.javapp.devj140.userGUI.BusinessLogic.CommandsName;
import ru.avalon.javapp.devj140.userGUI.Models.Domain;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLDomainGUIController implements Initializable {

    @FXML
    private TableView<Domain> table;
    private String message = "";

    private List<Domain> item = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) throws IllegalArgumentException{
        if(!getDomainById(DomainGUI.PERSONID)){
            Alert mess = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
            mess.showAndWait();
            throw new IllegalArgumentException("");
        }
        ObservableList<Domain> obList = FXCollections.observableList(item);
        table.getColumns().forEach(column->{
            String str = column.getText();
            switch (str){
                case "Id":
                    column.setCellValueFactory(new PropertyValueFactory<>("id"));
                    break;
                case "WebName":
                    column.setCellValueFactory(new PropertyValueFactory<>("webName"));
                    break;
                case "DomainName":
                    column.setCellValueFactory(new PropertyValueFactory<>("domainName"));
                    break;
                case "Ip":
                    column.setCellValueFactory(new PropertyValueFactory<>("ip"));
                    break;
                case "DateReg":
                    column.setCellValueFactory(new PropertyValueFactory<>("dateReg"));
                    break;
                case "CountryReg":
                    column.setCellValueFactory(new PropertyValueFactory<>("countryReg"));
                    break;
                case "PersonId":
                    column.setCellValueFactory(new PropertyValueFactory<>("personId"));
                    break;
            }
        });
        table.setItems(obList);
    }

    private boolean getDomainById(int id){

        Common.sharedResource.writeCommand(CommandsName.GET_TABLE_DOMAIN_ID + "<" + id + ">");
        try {
            List<?> list = Common.sharedResource.readDataBuf();
            if(list.size() > 0 && list.get(0) instanceof Domain){
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
}
