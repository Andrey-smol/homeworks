package ru.avalon.javapp.devj140.userGUI.Models;

public class Person {

   private int id;
   private String jobTitle;
   private String firstNameLastName;
   private String phone;
   private String email;

    public Person(int id, String jobTitle, String firstNameLastName, String phone, String email) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.firstNameLastName = firstNameLastName;
        this.phone = phone;
        this.email = email;
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


    @Override
    public String toString() {
        return "id = " + id + ", job = " + jobTitle + ", firstNameLastName = " + firstNameLastName + ", phone = " + phone + ", email = " + email;
    }
}
