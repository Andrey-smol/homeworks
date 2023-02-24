package ru.avalon.javapp.devj140.userGUI.MainApplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ru.avalon.javapp.devj140.userGUI.BusinessLogic.CommandsName;
import ru.avalon.javapp.devj140.userGUI.BusinessLogic.SharedResource;
import ru.avalon.javapp.devj140.userGUI.Models.Person;

import java.util.ArrayList;
import java.util.List;

public class ClientGUI extends Stage {

    private final SharedResource sharedResource;
    private List<Person> persons = new ArrayList<>();
    List<Integer> countRecords = new ArrayList<>();
    List<Item> items = new ArrayList<>();

    private String message = "";

    public ClientGUI(SharedResource sharedResource){
        this.sharedResource = sharedResource;
    }

    public void init(){

        if(!getTablePerson()){
            Alert mess = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
            mess.showAndWait();
            return;
        }
        //getCountDomain();
        items = setItems();

        ObservableList<Item> obList = FXCollections.observableList(items);
        TableView<Item> table = new TableView<>(obList);

        table.setRowFactory(row->{
            TableRow<Item> tableRow = new TableRow<>();
            tableRow.setOnMouseClicked(mouseEvent -> {
                if(mouseEvent.getClickCount() == 2 && !tableRow.isEmpty()){
                    Item it = tableRow.getItem();
                    new DomainGUI(sharedResource).init(it.id);
                }
            });
            return tableRow;
        });

            TableColumn<Item, Integer> idColumn = new TableColumn<>("Id");
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            table.getColumns().add(idColumn);

            TableColumn<Item, String> jobColumn = new TableColumn<>("jobTitle");
            jobColumn.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
            table.getColumns().add(jobColumn);

            TableColumn<Item, String> nameColumn = new TableColumn<>("firstNameLastName");
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstNameLastName"));
            table.getColumns().add(nameColumn);

            TableColumn<Item, String> phoneColumn = new TableColumn<>("phone");
            phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
            table.getColumns().add(phoneColumn);

            TableColumn<Item, String> emailColumn = new TableColumn<>("email");
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            table.getColumns().add(emailColumn);

            TableColumn<Item, Integer> countColumn = new TableColumn<>("CountDomains");
            countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
            table.getColumns().add(countColumn);



        Scene scene = new Scene(table, 1000, 400);
        setScene(scene);
        setTitle("Persons");

        Stage stage = (Stage) getWindows().get(0);// Stage.getWindows().stream().filter(Window::isShowing).findFirst().get();
        stage.close();
        show();
    }

    private boolean getTablePerson(){
        sharedResource.writeCommand(CommandsName.GET_PERSONS_WITH_DOMAINS);// GET_TABLE_PERSONS);
        try {
            List<?> list = sharedResource.readDataBuf();
            if(list.size() > 0 && list.get(0) instanceof Person) {
                persons = (List<Person>) sharedResource.readDataBuf();
                return true;
            }
            message = list.get(0).toString();
        }
        catch (InterruptedException e){
            message = "ERROR read date \n" + e.getMessage();
        }
        return false;
    }
    private void getCountDomain(){
        int dat = 0;
        try {
            if (persons.size() > 0) {
                for (Person p : persons) {
                    sharedResource.writeCommand(CommandsName.GET_COUNT_RECORDS_ID + "<domains>,<" + p.getId() + ">");
                    dat = (Integer) sharedResource.readDataBuf().get(0);

                    countRecords.add(dat);
                    System.out.println(dat);
                }
            }
        }
        catch (InterruptedException e){}
    }

    private List<Item> setItems(){
        List<Item> item = new ArrayList<>();
        int i = 0;
        for (Person p: persons) {
            //item.add(new Item(p.getId(), p.getJobTitle(), p.getFirstNameLastName(), p.getPhone(), p.getEmail(), countRecords.get(i++)));
            item.add(new Item(p.getId(), p.getJobTitle(), p.getFirstNameLastName(), p.getPhone(), p.getEmail(), p.getDomains().size()));
        }
        return item;
    }

    public class Item{
         private  int id;
         private String jobTitle;
         private String firstNameLastName;
         private String phone;
         private String email;
         private int count;


        public Item(int id, String jobTitle, String firstNameLastName, String phone, String email, int count) {
            this.id = id;
            this.jobTitle = jobTitle;
            this.firstNameLastName = firstNameLastName;
            this.phone = phone;
            this.email = email;
            this.count = count;
        }

        public int getId() {
            return id;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public String getFirstNameLastName() {
            return firstNameLastName;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return "id = " + getId() + "," + getJobTitle() + "," + getFirstNameLastName() + "," + getPhone() + "," + getEmail() + "," + getCount();
        }
    }
}
