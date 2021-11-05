package com.Gydytojaidiagnozes.web.controller;

import com.Gydytojaidiagnozes.web.model.Doctor;
import com.Gydytojaidiagnozes.web.service.DoctorService;
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
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/list-doctors")
    public String showDoctors(ModelMap model) {
        model.put("doctors", doctorService.findAllDoctors());
        return "list-doctors"; // view resolver
    }
    @GetMapping("/add-doctor")
    public String showAddPage(ModelMap model) {
        model.addAttribute("doctor", new Doctor());
        return "add-doctor";
    }

    @PostMapping("/add-doctor")
    public String add(ModelMap model, @ModelAttribute("doctor") Doctor doctor, BindingResult result) {

        if(result.hasErrors()) {
            return "add-doctor";
        }
        doctorService.saveDoctor(doctor);

        return "redirect:/list-doctors";
    }

    @GetMapping("/delete-doctor/{id}")
    @Transactional
    public String delete(@PathVariable int id) {
        doctorService.deleteDoctorById(id);
        return "redirect:/list-doctors";
    }
    @GetMapping("/update-doctor/{id}")
    public String showUpdatePage(ModelMap model, @PathVariable int id) {
        model.addAttribute("doctor", doctorService.findDoctorById(id));
        return "add-doctor";
    }

    @PostMapping("/update-vartotojas/{id}")
    public String update(ModelMap model, @ModelAttribute("doctor") Doctor doctor, @PathVariable int id,
                         BindingResult result) {
        if(result.hasErrors()) {
            return "add-doctor";
        }

        doctorService.updateDoctor(doctor);

        return "redirect:/list-doctors";
    }

}
