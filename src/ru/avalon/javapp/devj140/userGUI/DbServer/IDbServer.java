package ru.avalon.javapp.devj140.userGUI.DbServer;

import ru.avalon.javapp.devj140.userGUI.Models.Domain;
import ru.avalon.javapp.devj140.userGUI.Models.Person;
import ru.avalon.javapp.devj140.userGUI.Models.User;

import java.util.List;

public interface IDbServer extends AutoCloseable{

    User getUser(int id);
    List<User> getUsers();

    List<Person> getPersons();

    int getCountRecords(String nameTable, int personId);

    List<Domain> getDomain(int personId);

    List<Person> getPersonsWithDomains();
}
