package ru.avalon.javapp.devj140.userGUI.Models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Domain {
    private int id;
    private String webName;
    private String domainName;
    private String ip;
    private Date dateReg;
    private String countryReg;
    private int personId;
    private Person person;


    public Domain(int id, String webName, String domainName, String ip, Date dateReg, String countryReg, int personId) {
        this.id = id;
        this.webName = webName;
        this.domainName = domainName;
        this.ip = ip;
        this.dateReg = dateReg;
        this.countryReg = countryReg;
        this.personId = personId;
    }

    public static String[] getNameColumns(){
        return new String[]{"Id", "WebName", "DomainName", "Ip", "DateReg", "CountryReg", "PersonId"};
    }

    public static String[] getNameVariable(){
        return new String[] {"id", "webName", "domainName", "ip", "dateReg", "countryReg", "personId"};
    }

    public List<Class<?>> getClassVariable(){
        List<Class<?>> listClass = new ArrayList<>();
        listClass.add(Integer.valueOf(id).getClass());
        listClass.add(webName.getClass());
        listClass.add(domainName.getClass());
        listClass.add(ip.getClass());
        listClass.add(dateReg.getClass());
        listClass.add(countryReg.getClass());
        listClass.add(Integer.valueOf(personId).getClass());
        return listClass;
    }

    public Integer getId() {
        return id;
    }

    public String getWebName() {
        return webName;
    }

    public String getDomainName() {
        return domainName;
    }

    public String getIp() {
        return ip;
    }

    public Date getDateReg() {
        return dateReg;
    }

    public String getCountryReg() {
        return countryReg;
    }

    public Integer getPersonId() {
        return personId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "id = " + id + ", webName = " + webName + ", domainName = " + domainName + ", ip = " + ip + ", dateReg = " + dateReg
                + ", countryReg = " + countryReg + ", personId = " + personId;
    }

}
