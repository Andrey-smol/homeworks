package ru.avalon.javapp.devj140.userGUI.Models;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String password;

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof User other)){
            return false;
        }
        return (Objects.equals(this.name, other.name) && Objects.equals(this.password, other.password));
    }

    @Override
    public String toString() {
        return "id = " + id + ", name = " + name + ", password = " + password;
    }
}
