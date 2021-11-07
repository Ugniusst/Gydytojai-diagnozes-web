package com.Gydytojaidiagnozes.web.repository;

import com.Gydytojaidiagnozes.web.model.Diagnose;
import com.Gydytojaidiagnozes.web.model.Doctor;
import com.Gydytojaidiagnozes.web.model.Patient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DiagnoseRepositoryTest {
    @Autowired
    DiagnoseRepository diagnoseRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientRepository patientRepository;

    private Doctor doctor;
    private Patient patient;
    @BeforeAll
    public void populateData() {
        Doctor doctor = new Doctor(1,"Tomas","+37062475204");
        this.doctor = doctor;
        doctorRepository.save(doctor);
        Patient patient = new Patient(1,"Patrikas","+44062475204");
        this.patient = patient;
        patientRepository.save(patient);
        Diagnose o0 = new Diagnose(1, doctor, patient, "covid-19", "2021-11-05");
        Diagnose o1 = new Diagnose(2, doctor, patient, "covid-19", "2021-11-04");
        Diagnose o2 = new Diagnose(3, doctor, patient, "covid-19", "2021-11-06");
        diagnoseRepository.save(o0);
        diagnoseRepository.save(o1);
        diagnoseRepository.save(o2);
    }

    @Test
    public void testFindAll()
    {
        Iterable<Diagnose> diagnoseList = diagnoseRepository.findAll();
        assertNotNull(diagnoseList);
        List<Diagnose> result = new ArrayList<>();
        diagnoseList.forEach(result::add);
        assertEquals(3, result.size());
    }
    @Test
    public void testSave()
    {
        Diagnose diagnose = new Diagnose(4, doctor, patient, "covid-19", "2021-11-06");
        diagnoseRepository.save(diagnose);
        Iterable<Diagnose> savedDiagnose = diagnoseRepository.findByPatientId(patient);

        assertNotNull(savedDiagnose);
        assertEquals(4, ((Collection<Diagnose>) savedDiagnose).size());
    }
    @Test
    public void testFindByPatient() {
        Iterable<Diagnose> diagnoseList = diagnoseRepository.findByPatientId(patient);
        assertNotNull(diagnoseList);
        assertEquals(3, ((Collection<Diagnose>) diagnoseList).size());
    }

    @Test
    public void testFindByDoctor() {
        Iterable<Diagnose> diagnoseList = diagnoseRepository.findByDoctorId(doctor);
        assertNotNull(diagnoseList);
        assertEquals(3, ((Collection<Diagnose>) diagnoseList).size());
    }
    @Test
    public void testFindByDate() {
        Iterable<Diagnose> diagnoseList = diagnoseRepository.findByDate("2021-11-04");
        assertNotNull(diagnoseList);
        assertEquals(1, ((Collection<Diagnose>) diagnoseList).size());
    }
    @Test
    public void testDelete() {
        Diagnose savedDiagnose = diagnoseRepository.findById(2);
        assertNotNull(savedDiagnose);
        diagnoseRepository.delete(savedDiagnose);
        Iterable<Diagnose> diagnoseList = diagnoseRepository.findAll();

        assertEquals(2, ((Collection<Diagnose>) diagnoseList).size());
    }
}
