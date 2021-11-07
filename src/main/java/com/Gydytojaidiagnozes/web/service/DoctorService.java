package com.Gydytojaidiagnozes.web.service;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import com.Gydytojaidiagnozes.web.model.Doctor;
import com.Gydytojaidiagnozes.web.repository.DiagnoseRepository;
import com.Gydytojaidiagnozes.web.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    DiagnoseRepository diagnoseRepository;


    public Doctor findDoctorById(int id) {
        return doctorRepository.findById(id);
    }

    public Iterable<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor findDoctorByTelephoneNumber(String telephoneNumber) {
        return doctorRepository.findByTelephoneNumber(telephoneNumber);
    }

    public Doctor findDoctorByName(String name) {
        return doctorRepository.findByName(name);
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void updateDoctor(Doctor doctor) {
        Doctor updatable = doctorRepository.findById(doctor.getId());
        if(updatable != null) {
            updatable.setName(doctor.getName());
            updatable.setTelephoneNumber(doctor.getTelephoneNumber());
            doctorRepository.save(updatable);
        }
        else {
            doctorRepository.save(doctor);
        }
    }

    public void deleteDoctorById(int id) {
        Doctor doctor = doctorRepository.findById(id);
        if(doctor != null) {
            doctorRepository.deleteById(id);
            if (diagnoseRepository.findByDoctorId(doctor) != null) diagnoseRepository.deleteAllByDoctorId(doctor);
        }
    }

    public void deleteDoctor(Doctor doctor) {
        doctorRepository.delete(doctor);
        diagnoseRepository.deleteAllByDoctorId(doctor);
    }

}


