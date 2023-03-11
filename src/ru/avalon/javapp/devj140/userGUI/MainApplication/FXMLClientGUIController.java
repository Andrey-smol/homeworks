package ru.avalon.javapp.devj140.userGUI.MainApplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.avalon.javapp.devj140.userGUI.BusinessLogic.CommandsName;
import ru.avalon.javapp.devj140.userGUI.Models.Person;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLClientGUIController implements Initializable {

    private List<Person> persons = new ArrayList<>();
    private String message = "";
    List<Item> items = new ArrayList<>();

    @FXML
    private TableView<Item> table;
    @FXML
    private TableColumn<Item, Integer> idColumn;
    @FXML
    private TableColumn<Item, String> jobColumn;
    @FXML
    private TableColumn<Item, String> nameColumn;
    @FXML
    private TableColumn<Item, String> phoneColumn;
    @FXML
    private TableColumn<Item, String> emailColumn;
    @FXML
    private TableColumn<Item, Integer> countColumn;

    @FXML
    public void handlerMouseClicked(MouseEvent event) {
        if(event.getClickCount() == 2){
            Item item = table.getSelectionModel().getSelectedItem();
            if(item != null) new DomainGUI(item.getId()).init();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if(!getTablePerson()){
            Alert mess = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
            mess.showAndWait();
            return;
        }
        items = setItems();
        ObservableList<Item> obList = FXCollections.observableList(items);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        jobColumn.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstNameLastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

        table.setItems(obList);
    }

    private boolean getTablePerson(){
        Common.sharedResource.writeCommand(CommandsName.GET_PERSONS_WITH_DOMAINS);
        try {
            List<?> list = Common.sharedResource.readDataBuf();
            if(list.size() > 0 && list.get(0) instanceof Person) {
                persons = (List<Person>) Common.sharedResource.readDataBuf();
                return true;
            }
            message = list.get(0).toString();
        }
        catch (InterruptedException e){
            message = "ERROR read date \n" + e.getMessage();
        }
        return false;
    }

    private List<Item> setItems(){
        List<Item> item = new ArrayList<>();
        int i = 0;
        for (Person p: persons) {
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
