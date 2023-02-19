package ru.avalon.javapp.devj140.userGUI.Models;

import java.util.ArrayList;
import java.util.List;

public class Person {

   private int id;
   private String jobTitle;
   private String firstNameLastName;
   private String phone;
   private String email;
   private List<Domain> domains = new ArrayList<>();

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

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }
    public void addDomain(Domain domain){
        domains.add(domain);
    }

    @Override
    public String toString() {
        return "id = " + id + ", job = " + jobTitle + ", firstNameLastName = " + firstNameLastName + ", phone = " + phone + ", email = " + email;
    }
}
