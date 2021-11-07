package com.Gydytojaidiagnozes.web.repository;

import com.Gydytojaidiagnozes.web.model.Diagnose;
import com.Gydytojaidiagnozes.web.model.Doctor;
import com.Gydytojaidiagnozes.web.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface DiagnoseRepository extends CrudRepository<Diagnose, Integer> {
    Diagnose findById(int id);

    Iterable<Diagnose> findAll();

    Iterable<Diagnose> findByPatientId(Patient patientId);

    Iterable<Diagnose> findByDoctorId(Doctor doctorId);

    Iterable<Diagnose> findByDate(String date);

    Diagnose save(Diagnose Diagnose);

    void deleteById(int id);

    void delete(Diagnose Diagnose);

    void deleteAllByDoctorId(Doctor doctorId);
}
