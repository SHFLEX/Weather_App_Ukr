package com.oldd6.users;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Meteorologist {
    @Id
    private String id;
    private String fml;
    private int age;
    private String mail;
    private String phoneN;

    public Meteorologist(String id, String fml, int age, String mail, String phoneN) {
        this.id = id; this.fml = fml; this.age = age; this.mail = mail; this.phoneN = phoneN;
    }

    public String getId() {
        return id;
    }


    public String getFml() {
        return fml;
    }

    public int getAge() {
        return age;
    }

    public String getMail() {
        return mail;
    }

    public String getPhoneN() {
        return phoneN;
    }

    public Meteorologist(){}
}