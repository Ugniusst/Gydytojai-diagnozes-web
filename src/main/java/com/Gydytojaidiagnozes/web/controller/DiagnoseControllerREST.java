package com.Gydytojaidiagnozes.web.controller;

import com.Gydytojaidiagnozes.web.model.Diagnose;
import com.Gydytojaidiagnozes.web.service.DiagnoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DiagnoseControllerREST {
    @Autowired
    DiagnoseService diagnoseService;


    @GetMapping("/Diagnose/{id}")
    @ResponseBody
    public Diagnose getDiagnose(@PathVariable("id") int id) {
        return diagnoseService.findDiagnoseById(id);
    }
    @GetMapping("/Diagnose")
    public Iterable<Diagnose> all() {
        return diagnoseService.findAllDiagnoses();
    }
    @PostMapping("/Diagnose")
    public Diagnose add(Diagnose Diagnose) {
        return diagnoseService.saveDiagnose(Diagnose);
    }

    @DeleteMapping("/Diagnose/{id}")
    @Transactional
    public void deleteById(@PathVariable("id") int id) {

        try {
            diagnoseService.deleteDiagnoseById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Transactional
    @DeleteMapping("/Diagnose")
    public void delete(Diagnose Diagnose) {
        diagnoseService.deleteDiagnose(Diagnose);
    }

    @PutMapping("/Diagnose/{id}")
    public void updateDiagnose(Diagnose Diagnose, @PathVariable("id") int id) {
        diagnoseService.updateDiagnose(Diagnose);
    }
}
