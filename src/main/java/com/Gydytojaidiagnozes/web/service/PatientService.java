package com.Gydytojaidiagnozes.web.service;

import com.Gydytojaidiagnozes.web.model.Diagnose;
import com.Gydytojaidiagnozes.web.model.Patient;
import com.Gydytojaidiagnozes.web.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient findPatientById(int id) {
        return patientRepository.findById(id);
    }

    public Iterable<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    public Patient findPatientByTelephoneNumber(String telephoneNumber) {
        return patientRepository.findByTelephoneNumber(telephoneNumber);
    }

    public Patient findPatientByName(String name) {
        return patientRepository.findByName(name);
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public void updatePatient(Patient patient) {
        patientRepository.save(patient);

    }

    public void deletePatientById(int id) {
        patientRepository.deleteById(id);
    }

    public void deletePatient(Patient patient) {
        patientRepository.delete(patient);
    }
}
