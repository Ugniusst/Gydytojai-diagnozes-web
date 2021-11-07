package com.Gydytojaidiagnozes.web.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiagnoseTest {

    private final Doctor doctor = new Doctor(2,"Kasparas","+37062475204");
    private final Patient patient = new Patient(3,"Romas","+37062477454");

    @Test
    void testConstructor() {

        Diagnose diagnose = new Diagnose(1,doctor,patient,"covid","2021-11-05");
        assertAll(
                () -> assertEquals(1, diagnose.getId() ),
                () -> assertEquals(2, diagnose.getDoctorId().getId()),
                () -> assertEquals("Kasparas", diagnose.getDoctorId().getName()),
                () -> assertEquals(3, diagnose.getPatientId().getId()),
                () -> assertEquals("Romas", diagnose.getPatientId().getName()),
                () -> assertEquals("covid", diagnose.getDiagnoseText() ),
                () -> assertEquals("2021-11-05", diagnose.getDate() )

        );
    }

    @Test
    void testEqualsObject() {
        Diagnose diagnose1 = new Diagnose(1,doctor,patient,"covid","2021-11-05");
        Diagnose diagnose2 = new Diagnose(1,doctor,patient,"covid","2021-11-05");
        assertEquals(diagnose1, diagnose2);
    }
    @Test
    void testSetId() {
        Diagnose diagnose = new Diagnose();
        diagnose.setId(10);
        assertEquals(diagnose.getId(),10);
    }
    @Test
    void testSetDoctorId() {
        Diagnose diagnose = new Diagnose();
        diagnose.setDoctorId(doctor);
        assertAll(
                () -> assertEquals(2, diagnose.getDoctorId().getId()),
                () -> assertEquals("Kasparas", diagnose.getDoctorId().getName())
        );
    }
    @Test
    void testSetPatientId() {
        Diagnose diagnose = new Diagnose();
        diagnose.setPatientId(patient);
        assertAll(
                () -> assertEquals(3, diagnose.getPatientId().getId()),
                () -> assertEquals("Romas", diagnose.getPatientId().getName())
        );
    }
    @Test
    void testSetDiagnoseText() {
        Diagnose diagnose = new Diagnose();
        diagnose.setDiagnoseText("covid");
        assertEquals(diagnose.getDiagnoseText(),"covid");
    }
    @Test
    void testSetDate() {
        Diagnose diagnose = new Diagnose();
        diagnose.setDate("2021-11-05");
        assertEquals(diagnose.getDate(),"2021-11-05");
    }
}
