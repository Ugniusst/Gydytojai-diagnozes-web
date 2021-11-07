package com.Gydytojaidiagnozes.web.repository;

import static org.junit.jupiter.api.Assertions.*;
import com.Gydytojaidiagnozes.web.model.Doctor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DoctorRepositoryTest {
    @Autowired
    DoctorRepository doctorRepository;

    @BeforeAll
    public void populateData() {
        Doctor o0 = new Doctor(1, "Kasparas", "+37062578963");
        Doctor o1 = new Doctor(2, "Romas", "+44062578963");
        Doctor o2 = new Doctor(3, "Tomas", "+37062578963");
        doctorRepository.save(o0);
        doctorRepository.save(o1);
        doctorRepository.save(o2);
    }

    @Test
    public void testFindAll()
    {
        Iterable<Doctor> doctorList = doctorRepository.findAll();
        assertNotNull(doctorList);
        List<Doctor> result = new ArrayList<>();
        doctorList.forEach(result::add);
        assertEquals(3, result.size());
    }
    @Test
    public void testSave()
    {
        Doctor doctor = new Doctor(4, "Rasa", "+37062542736");
        doctorRepository.save(doctor);
        Doctor savedDoctor = doctorRepository.findByName("Rasa");
        assertNotNull(savedDoctor);
        assertEquals(4, savedDoctor.getId());
    }
    @Test
    public void testFindByName() {

        Doctor doctor = doctorRepository.findByName("Tomas");
        assertNotNull(doctor);
        assertEquals(3, doctor.getId());
    }

    @Test
    public void testFindByTelephoneNumber() {
        Doctor doctor = doctorRepository.findByTelephoneNumber("+44062578963");
        assertNotNull(doctor);
        assertEquals(2, doctor.getId());
    }
    @Test
    public void testDelete() {
        Doctor savedDoctor = doctorRepository.findByName("Romas");
        assertNotNull(savedDoctor);
        doctorRepository.delete(savedDoctor);
        Iterable<Doctor> doctor = doctorRepository.findAll();

        List<Doctor> result = StreamSupport.stream(doctor.spliterator(), false).collect(Collectors.toList());
        assertEquals(2, result.size());
    }
}
