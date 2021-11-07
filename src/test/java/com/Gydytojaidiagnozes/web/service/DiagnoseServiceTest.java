package com.Gydytojaidiagnozes.web.service;

import com.Gydytojaidiagnozes.web.model.Diagnose;
import com.Gydytojaidiagnozes.web.model.Doctor;
import com.Gydytojaidiagnozes.web.model.Patient;
import com.Gydytojaidiagnozes.web.repository.DiagnoseRepository;
import com.Gydytojaidiagnozes.web.repository.DiagnoseRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DiagnoseServiceTest {
    @Mock
    DiagnoseRepository diagnoseRepository;

    @InjectMocks
    DiagnoseService diagnoseService;

    private List<Diagnose> diagnoseList;
    private Diagnose diagnose;
    private Patient patient;
    private Doctor doctor;

    @BeforeAll
    void setup() throws SQLException {
        Doctor doctor = new Doctor(1,"Kasparas","+37062475204");
        this.doctor = doctor;
        Patient patient = new Patient(1,"Romas","+44062475204");
        this.patient = patient;
        Diagnose diagnose = new Diagnose(1, doctor, patient, "covid", "2021-11-05");
        this.diagnose = diagnose;
        this.diagnoseList = new ArrayList<>();
        this.diagnoseList.add(diagnose);
        this.diagnoseList.add(new Diagnose(2, doctor, patient, "covid-19", "2021-11-05"));
    }
    @Test
    void testFindById() {
        when(diagnoseRepository.findById(Mockito.anyInt())).thenReturn(diagnose);
        Diagnose found = diagnoseService.findDiagnoseById(Mockito.anyInt());
        verify(diagnoseRepository).findById(Mockito.anyInt());
        assertNotNull(found);
    }
    @Test
    void testFindAll() {
        when(diagnoseRepository.findAll()).thenReturn(diagnoseList);
        List<Diagnose> found = (List<Diagnose>) diagnoseService.findAllDiagnoses();
        verify(diagnoseRepository).findAll();
        assertEquals(2, found.size());
    }
    @Test
    void testFindByPatientId() {
        when(diagnoseRepository.findByPatientId(patient)).thenReturn(diagnoseList);
        Iterable<Diagnose> found = diagnoseService.findDiagnoseByPatientId(patient);
        verify(diagnoseRepository).findByPatientId(patient);
        assertNotNull(found);
    }
    @Test
    void testFindByDoctorId() {
        when(diagnoseRepository.findByDoctorId(doctor)).thenReturn(diagnoseList);
        Iterable<Diagnose> found = diagnoseService.findDiagnoseByDoctorId(doctor);
        verify(diagnoseRepository).findByDoctorId(doctor);
        assertNotNull(found);
    }
    @Test
    void testFindByDate() {
        when(diagnoseRepository.findByDate(Mockito.anyString())).thenReturn(diagnoseList);
        Iterable<Diagnose> found = diagnoseService.findDiagnoseByDate(Mockito.anyString());
        verify(diagnoseRepository).findByDate(Mockito.anyString());
        assertNotNull(found);
    }
    @Test
    void testUpdate() {
        diagnoseService.updateDiagnose(diagnose);
        verify(diagnoseRepository).save(Mockito.any(Diagnose.class));
    }
    @Test
    void testDelete() {
        diagnoseService.deleteDiagnose(diagnose);
        verify(diagnoseRepository).delete(Mockito.any(Diagnose.class));
    }
}
