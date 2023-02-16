package ru.avalon.javapp.devj140.userGUI.BusinessLogic;

import ru.avalon.javapp.devj140.userGUI.DbServer.DbServer;
import ru.avalon.javapp.devj140.userGUI.Models.Domain;
import ru.avalon.javapp.devj140.userGUI.Models.Person;
import ru.avalon.javapp.devj140.userGUI.Models.User;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Program extends Thread{

    private String userName;
    private String userPassword;

    private DbServer dbServer;
    private SharedResource sharedResource;

    private final String nameFileProperties = "application.properties";
    private ConnectionData connectionData;
    private boolean stopThread = false;



    public Program(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
        start();
    }

    private boolean checkUser(){
        connectionData = getProperties(new File(nameFileProperties));
        if(connectionData == null) connectionData = new ConnectionData();
        dbServer = new DbServer(connectionData.getUrl(), connectionData.getUser(), connectionData.getPassword());
        return true;
    }

    private ConnectionData getProperties(File name){
        Properties properties = new Properties();
        ConnectionData cd;
        String url, user, pass;
        try{
            properties.load(new FileReader(name));
            url = properties.getProperty("db.url");
            user = properties.getProperty("db.user");
            pass = properties.getProperty("db.password");
            cd = new ConnectionData(url, user, pass);
        }
        catch(IOException e){
            return null;
        }
        return cd;
    }

    @Override
    public void run() {
        if(!checkUser()) {
            //to do
        }

        while(!stopThread){

            try {
                String com = sharedResource.readCommand();
                System.out.println("PROG " + com);
                sharedResource.writeDataBuf(parseCommand(com));
            }
            catch (InterruptedException e){

            }
        }
    }
    /**

     */
    private List<?> parseCommand(String com){
        if (com.indexOf(CommandsName.CHECK_USER, 0) == 0) {
            String dat = com.substring(CommandsName.CHECK_USER.length()).trim();
            if(dat.matches("<(.+)>,<(.+)>")){
                String[] st = dat.split(",");
                String user = st[0].substring(st[0].indexOf("<") + 1, st[0].indexOf(">"));
                String pass = st[1].substring(st[1].indexOf("<") + 1, st[1].indexOf(">"));
                List<User> us = dbServer.getUsers();
                List<String> list = new ArrayList<>();
                if(us == null){
                    list.add("ERROR read Table USERS");
                }
                if (us.size() < 1){
                    list.add("ERROR Table USERS is empty");
                }
                if(us.get(0).getName().equals(user) && us.get(0).getPassword().equals(pass)){
                    list.add("OK");
                }
                else list.add("ERROR user did not find");
                return list;
            }
        }
        else if (com.indexOf(CommandsName.GET_COUNT_RECORDS_ID, 0) == 0) {
            String dat = com.substring(CommandsName.GET_COUNT_RECORDS_ID.length()).trim();
            if(dat.matches("<(.+)>,<([0-9]+)>")) {
                String[] st = dat.split(",");
                String table = st[0].substring(st[0].indexOf("<") + 1, st[0].indexOf(">"));
                int id = Integer.parseInt(st[1].substring(st[1].indexOf("<") + 1, st[1].indexOf(">")));
                List<Integer> list = new ArrayList<>();
                list.add(dbServer.getCountRecords(table, id));
                return list;
            }
        }
        else if (com.indexOf(CommandsName.GET_TABLE_PERSONS, 0) == 0) {
            List<Person> list = dbServer.getPersons();
            if(list == null){
                List<String> error = new ArrayList<>();
                error.add("ERROR read dat");
                return error;
            }
            if(list.size() == 0){
                List<String> mess = new ArrayList<>();
                mess.add("Нет данных");
                return mess;
            }
            return list;
        }
        else if(com.indexOf(CommandsName.GET_TABLE_DOMAIN_ID, 0) == 0){
            String dat = com.substring(CommandsName.GET_TABLE_DOMAIN_ID.length()).trim();
            if(dat.matches("<([0-9]+)>")) {
                int id = Integer.parseInt(dat.substring(dat.indexOf("<") + 1, dat.indexOf(">")));
                List<Domain> domains = dbServer.getDomain(id);
                if(domains == null){
                    List<String> error = new ArrayList<>();
                    error.add("ERROR read dat");
                    return error;
                }
                if(domains.size() == 0){
                    List<String> mess = new ArrayList<>();
                    mess.add("Нет данных");
                    return mess;
                }
                return domains;
            }
        }
        else if (com.indexOf(CommandsName.STOP, 0) == 0) {
            dbServer = null;
            stopThread = true;
        }

        List<String> listError = new ArrayList<>();
        listError.add("ERROR command");
        return listError;
    }

}
