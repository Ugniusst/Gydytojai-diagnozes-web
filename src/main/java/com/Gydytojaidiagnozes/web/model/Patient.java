package com.Gydytojaidiagnozes.web.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Patient {

    @Id
    private int id;
    private String name;
    private String telephoneNumber;
    private int registrationNumber;
    private String address;

    public Patient() {

    }
    public Patient(int id, String name, String telephoneNumber, int registrationNumber, String address) {
        this.id = id;
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.registrationNumber = registrationNumber;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
