package ru.avalon.javapp.devj140.userGUI.BusinessLogic;

public class ConnectionData {

    //public static final String DRIVER = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/javafx1";
    private String user = "root";
    private String password = "1234";


    public ConnectionData(){}
    public ConnectionData(String url, String user, String password) {
        if(url == null || user == null || password == null) return;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
