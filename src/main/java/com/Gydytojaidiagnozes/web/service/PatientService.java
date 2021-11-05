package com.Gydytojaidiagnozes.web.service;

import com.Gydytojaidiagnozes.web.model.Patient;
import com.Gydytojaidiagnozes.web.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    Patient findPatientById(int id) {
        return patientRepository.findById(id);
    }

    Iterable<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    Patient findPatientByTelephoneNumber(String telephoneNumber) {
        return patientRepository.findByTelephoneNumber(telephoneNumber);
    }

    Patient findPatientByName(String name) {
        return patientRepository.findByName(name);
    }

    Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    void deletePatientById(int id) {
        patientRepository.deleteById(id);
    }

    void deletePatient(Patient patient) {
        patientRepository.delete(patient);
    }
}
