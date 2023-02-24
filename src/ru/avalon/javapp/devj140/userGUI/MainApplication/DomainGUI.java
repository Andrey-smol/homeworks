package ru.avalon.javapp.devj140.userGUI.MainApplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.avalon.javapp.devj140.userGUI.BusinessLogic.CommandsName;
import ru.avalon.javapp.devj140.userGUI.BusinessLogic.SharedResource;
import ru.avalon.javapp.devj140.userGUI.Models.Domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DomainGUI extends Stage {

    private final SharedResource sharedResource;
    private List<Domain> item = new ArrayList<>();
    private String[] nameColumns;
    private String[] nameVariables;
    private List<Class<?>> variables = new ArrayList<>();

    private String message = "";

    public DomainGUI(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }


    public void init(int idPerson){

        if(!getDomainById(idPerson)){
            Alert mess = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
            mess.showAndWait();
            return;
        }

        ObservableList<Domain> obList = FXCollections.observableList(item);
        TableView<Domain> table = new TableView<>(obList);

        for(int i = 0; i < nameColumns.length; i++){
            TableColumn<Domain, ?> column = getObject(variables.get(i), nameColumns[i]);
            column.setCellValueFactory(new PropertyValueFactory<>(nameVariables[i]));
            table.getColumns().add(column);
        }

        Scene scene = new Scene(table, 800, 400);
        setScene(scene);
        setTitle("Domains");

        initModality(Modality.APPLICATION_MODAL);
        showAndWait();
    }

    private TableColumn<Domain, ?> getObject(Class<?> dat, String name){
        if(dat.getName().equals("Integer")) return new TableColumn<Domain, Integer>(name);
        if(dat.getName().equals("Date")) return new TableColumn<Domain, Date>(name);
        return new TableColumn<Domain, String>(name);
    }

    private boolean getDomainById(int id){

        sharedResource.writeCommand(CommandsName.GET_TABLE_DOMAIN_ID + "<" + id + ">");
        try {
            List<?> list = sharedResource.readDataBuf();
            if(list.size() > 0 && list.get(0) instanceof Domain){
                item = (List<Domain>) list;
                nameColumns = Domain.getNameColumns();
                nameVariables = Domain.getNameVariable();
                variables = item.get(0).getClassVariable();
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
