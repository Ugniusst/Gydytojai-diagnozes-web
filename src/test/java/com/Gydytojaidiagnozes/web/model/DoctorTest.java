package com.Gydytojaidiagnozes.web.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DoctorTest {
    @Test
    void testConstructor() {
        Doctor doctor = new Doctor(1,"Kasparas","+37062475204");
        assertAll(
                () -> assertEquals(1, doctor.getId() ),
                () -> assertEquals("Kasparas",doctor.getName()),
                () -> assertEquals("+37062475204",doctor.getTelephoneNumber())
        );
    }
    
    @Test
    void testEqualsObject() {
        Doctor doctor1 = new Doctor(1, "Kasparas", "+37062475204");
        Doctor doctor2 = new Doctor(1, "Kasparas", "++37062475204");
        assertEquals(doctor1, doctor2);
    }
    @Test
    void testSetId() {
        Doctor doctor = new Doctor();
        doctor.setId(10);
        assertEquals(doctor.getId(),10);
    }
    @Test
    void testSetName() {
        Doctor doctor = new Doctor();
        doctor.setName("Kasparas");
        assertEquals(doctor.getName(),"Kasparas");
    }
    @Test
    void testSetPhoneNumber() {
        Doctor doctor = new Doctor();
        doctor.setTelephoneNumber("+37062475204");
        assertEquals(doctor.getTelephoneNumber(),"+37062475204");
    }
}
