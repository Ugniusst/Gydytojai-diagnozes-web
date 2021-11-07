package com.Gydytojaidiagnozes.web.controller;

import com.Gydytojaidiagnozes.web.model.Doctor;
import com.Gydytojaidiagnozes.web.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class DoctorControllerREST {

    @Autowired
    DoctorService doctorService;


    @GetMapping("/Doctor/{id}")
    @ResponseBody
    public Doctor getDoctor(@PathVariable("id") int id) {
        return doctorService.findDoctorById(id);
    }
    @GetMapping("/Doctor")
    public Iterable<Doctor> all() {
        return doctorService.findAllDoctors();
    }
    @PostMapping("/Doctor")
    public Doctor add(Doctor Doctor) {
        return doctorService.saveDoctor(Doctor);
    }

    @DeleteMapping("/Doctor/{id}")
    @Transactional
    public void deleteById(@PathVariable("id") int id) {

        try {
            doctorService.deleteDoctorById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Transactional
    @DeleteMapping("/Doctor")
    public void delete(Doctor Doctor) {
        doctorService.deleteDoctor(Doctor);
    }

    @PutMapping("/Doctor/{id}")
    public void updateDoctor(Doctor Doctor, @PathVariable("id") int id) {
        doctorService.updateDoctor(Doctor);
    }
}
