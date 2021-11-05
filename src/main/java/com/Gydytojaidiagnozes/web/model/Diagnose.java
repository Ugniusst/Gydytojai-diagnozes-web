package com.Gydytojaidiagnozes.web.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Diagnose {

    @Id
    private int id;
    private int doctorId;
    private int patientId;
    private String diagnose;
    private Date date;

    public Diagnose() {

    }

    public Diagnose(int id, int doctor, int patient, String diagnose, Date date) {
        this.id = id;
        this.doctorId = doctor;
        this.patientId = patient;
        this.diagnose = diagnose;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }
}
