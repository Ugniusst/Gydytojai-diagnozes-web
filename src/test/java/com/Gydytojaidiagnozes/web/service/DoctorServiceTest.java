package com.Gydytojaidiagnozes.web.service;

import com.Gydytojaidiagnozes.web.model.Doctor;
import com.Gydytojaidiagnozes.web.repository.DiagnoseRepository;
import com.Gydytojaidiagnozes.web.repository.DoctorRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DoctorServiceTest {
    @Mock
    DoctorRepository doctorRepository;
    @Mock
    DiagnoseRepository diagnoseRepository;

    @InjectMocks
    DoctorService doctorService;

    private List<Doctor> doctorList;
    private Doctor doctor;

    @BeforeAll
    void setup() throws SQLException {
        Doctor doctor = new Doctor(1,"Kasparas","+37062475204");
        this.doctor = doctor;
        this.doctorList = new ArrayList<>();
        this.doctorList.add(doctor);
        this.doctorList.add(new Doctor(2,"Tomas","+44062475204"));
    }
    @Test
    void testFindById() {
        when(doctorRepository.findById(Mockito.anyInt())).thenReturn(doctor);
        Doctor found = doctorService.findDoctorById(Mockito.anyInt());
        verify(doctorRepository).findById(Mockito.anyInt());
        assertNotNull(found);
    }
    @Test
    void testFindAll() {
        when(doctorRepository.findAll()).thenReturn(doctorList);
        List<Doctor> found = (List<Doctor>) doctorService.findAllDoctors();
        verify(doctorRepository).findAll();
        assertEquals(2, found.size());
    }
    @Test
    void testFindByName() {
        when(doctorRepository.findByName(Mockito.anyString())).thenReturn(doctor);
        Doctor found = doctorService.findDoctorByName(Mockito.anyString());
        verify(doctorRepository).findByName(Mockito.anyString());
        assertNotNull(found);
    }
    @Test
    void testFindByTelephoneNumber() {
        when(doctorRepository.findByTelephoneNumber(Mockito.anyString())).thenReturn(doctor);
        Doctor found = doctorService.findDoctorByTelephoneNumber(Mockito.anyString());
        verify(doctorRepository).findByTelephoneNumber(Mockito.anyString());
        assertNotNull(found);
    }
    @Test
    void testUpdate() {
        doctorService.updateDoctor(doctor);
        verify(doctorRepository).save(Mockito.any(Doctor.class));
    }
    @Test
    void testDelete() {
        doctorService.deleteDoctor(doctor);
        verify(doctorRepository).delete(Mockito.any(Doctor.class));
    }
}
