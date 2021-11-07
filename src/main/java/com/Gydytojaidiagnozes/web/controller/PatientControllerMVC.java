package com.Gydytojaidiagnozes.web.controller;

import com.Gydytojaidiagnozes.web.model.Patient;
import com.Gydytojaidiagnozes.web.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PatientControllerMVC {
    @Autowired
    private PatientService patientService;

    @GetMapping("/list-patients")
    public String showPatients(ModelMap model) {
        model.put("patients", patientService.findAllPatients());
        return "list-patients"; // view resolver
    }
    @GetMapping("/add-patient")
    public String showAddPage(ModelMap model) {
        model.addAttribute("patient", new Patient());
        return "add-patient";
    }

    @PostMapping("/add-patient")
    public String add(ModelMap model, @ModelAttribute("patient") Patient patient, BindingResult result) {

        if(result.hasErrors()) {
            return "add-patient";
        }
        patientService.savePatient(patient);

        return "redirect:/list-patients";
    }

    @GetMapping("/delete-patient/{id}")
    @Transactional
    public String delete(@PathVariable int id) {
        patientService.deletePatientById(id);
        return "redirect:/list-patients";
    }
    @GetMapping("/update-patient/{id}")
    public String showUpdatePage(ModelMap model, @PathVariable int id) {
        model.addAttribute("patient", patientService.findPatientById(id));
        return "add-patient";
    }

    @PostMapping("/update-patient/{id}")
    public String update(ModelMap model, @ModelAttribute("patient") Patient patient, @PathVariable int id,
                         BindingResult result) {
        if(result.hasErrors()) {
            return "add-patient";
        }

        patientService.updatePatient(patient);

        return "redirect:/list-patients";
    }
}
