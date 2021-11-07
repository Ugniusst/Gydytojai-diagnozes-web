package com.Gydytojaidiagnozes.web.controller;

import com.Gydytojaidiagnozes.web.model.Patient;
import com.Gydytojaidiagnozes.web.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PatientControllerREST {
    @Autowired
    PatientService patientService;


    @GetMapping("/PatientGet/{id}")
    @ResponseBody
    public Patient getPatient(@PathVariable("id") int id) {
        return patientService.findPatientById(id);
    }
    @GetMapping("/Patient")
    public Iterable<Patient> all() {
        return patientService.findAllPatients();
    }
    @PostMapping("/Patient")
    public Patient add(Patient Patient) {
        return patientService.savePatient(Patient);
    }

    @DeleteMapping("/Patient/{id}")
    @Transactional
    public void deleteById(@PathVariable("id") int id) {

        try {
            patientService.deletePatientById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Transactional
    @DeleteMapping("/Patient")
    public void delete(Patient Patient) {
        patientService.deletePatient(Patient);
    }

    @PutMapping("/Patient/{id}")
    public void updatePatient(Patient Patient, @PathVariable("id") int id) {
        patientService.updatePatient(Patient);
    }
}
