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

    /*private final DoctorRepository doctorRepository;
    private final DiagnoseRepository diagnoseRepository;*/

    private static List<Doctor> doctors = new ArrayList<>();
    private static int doctorsCount = 3;

    static {
        doctors.add(new Doctor(1, "Petras", "+37062742568"));
        doctors.add(new Doctor(2, "Jonas", "+37062742788"));
        doctors.add(new Doctor(3, "Gintaras", "+37062522568"));
    }

    /*public DoctorService(DoctorRepository doctorRepository, DiagnoseRepository diagnoseRepository) {
        this.doctorRepository = doctorRepository;
        this.diagnoseRepository = diagnoseRepository;
    }*/

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
            System.out.println(updatable.getName());
            updatable.setName(doctor.getName());
            updatable.setTelephoneNumber(doctor.getTelephoneNumber());
            doctorRepository.save(updatable);
        }
        else {
            doctorRepository.save(doctor);
        }
    }

    public void deleteDoctorById(int id) {
        doctorRepository.deleteById(id);
        diagnoseRepository.deleteAllByDoctorId(id);
    }

    public void deleteDoctor(Doctor doctor) {
        doctorRepository.delete(doctor);
        diagnoseRepository.deleteAllByDoctorId(doctor.getId());
    }
    public List<Doctor> retrieveDoctors() {
        return doctors;
    }
}


