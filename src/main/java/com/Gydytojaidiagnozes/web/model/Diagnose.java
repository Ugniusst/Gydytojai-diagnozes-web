package com.Gydytojaidiagnozes.web.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Diagnose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @JoinColumn(name = "doctorId")
    private Doctor doctorId;
    @ManyToOne()
    @JoinColumn(name = "patientId")
    private Patient patientId;
    private String diagnoseText;
    private String date;

    public Diagnose() {

    }

    public Diagnose(int id, Doctor doctor, Patient patient, String diagnoseText, String date) {
        this.id = id;
        this.doctorId = doctor;
        this.patientId = patient;
        this.diagnoseText = diagnoseText;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Doctor getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Doctor doctorId) {
        this.doctorId = doctorId;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }

    public String getDiagnoseText() {
        return diagnoseText;
    }

    public void setDiagnoseText(String diagnoseText) {
        this.diagnoseText = diagnoseText;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diagnose diagnose = (Diagnose) o;
        return (getId() == diagnose.getId() && getDiagnoseText().equals(diagnose.getDiagnoseText())) ;
    }
}
