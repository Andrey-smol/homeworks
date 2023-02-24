package ru.avalon.javapp.devj140.userGUI.DbServer;

import ru.avalon.javapp.devj140.userGUI.Models.Domain;
import ru.avalon.javapp.devj140.userGUI.Models.Person;
import ru.avalon.javapp.devj140.userGUI.Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbServer implements IDbServer{


    private String url;
    private String login;
    private String password;

    public DbServer(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }


    @Override
    public User getUser(int id) {
        try(Connection connection = DriverManager.getConnection(url, login, password)){
            try(Statement st = connection.createStatement();
                ResultSet res = st.executeQuery("select * from USERS where id = " + id)){
                if(res.next()) {
                    return new User(res.getInt(1), res.getString(2), res.getString(3));
                }
            }
        }
        catch (SQLException e){

        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        try(Connection connection = DriverManager.getConnection(url, login, password)){
           try(Statement st = connection.createStatement();
               ResultSet res = st.executeQuery("select * from USERS")){
               List<User> list = new ArrayList<>();
               while(res.next()){
                   list.add(new User(res.getInt(1), res.getString(2), res.getString(3)));
               }
               return list;
           }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Person> getPersons() {
        try(Connection connection = DriverManager.getConnection(url, login, password)){
            try(Statement st = connection.createStatement();
                ResultSet res = st.executeQuery("select * from Person")){
                List<Person> list = new ArrayList<>();
                while(res.next()){
                    list.add(new Person(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5)));
                }
                return list;
            }
        }
        catch (SQLException e){

        }
        return null;
    }

    @Override
    public int getCountRecords(String nameTable, int personId) {
        try(Connection connection = DriverManager.getConnection(url, login, password)){
            try(PreparedStatement st = connection.prepareStatement("select count(id) from " + nameTable + " where personid = ?")){
                //st.setString(1, nameTable);
                st.setInt(1, personId);
                try(ResultSet rs = st.executeQuery()){
                    if (rs.next()) return rs.getInt(1);
                }
            }
        }
        catch (SQLException e){

        }
        return 0;
    }

    @Override
    public List<Domain> getDomain(int personId) {
        try(Connection connection = DriverManager.getConnection(url, login, password)){
            try(Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("select * from domains where personid = " + personId)){
                List<Domain> list = new ArrayList<>();
                while(rs.next()){
                    list.add(new Domain(rs.getInt(1), rs.getString(2), rs.getString(3),
                             rs.getString(4), rs.getDate(5), rs.getString(6), rs.getInt(7)));
                }
                return list;
            }
        }
        catch (SQLException e){

        }
        return null;
    }

    @Override
    public List<Person> getPersonsWithDomains() {
        try(Connection connection = DriverManager.getConnection(url, login, password)){
            try(Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM Person left JOIN Domains on Person.id = Domains.personid")){
                List<Person> personList = new ArrayList<>();
                while(rs.next()){
                    int id = rs.getInt(1);
                    Person person = personList.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
                    if(person == null){
                        person = new Person(id, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                        personList.add(person);
                    }
                    if(rs.getString(7) != null) {
                        Domain domain = new Domain(rs.getInt(6), rs.getString(7), rs.getString(8),
                                rs.getString(9), rs.getDate(10), rs.getString(11), rs.getInt(12));
                        person.addDomain(domain);
                    }
                }
                return personList;
            }
        }
        catch (SQLException e){

        }
        return null;
    }

    @Override
    public void close() throws Exception {

    }

}
