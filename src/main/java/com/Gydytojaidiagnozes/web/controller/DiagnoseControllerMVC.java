package com.Gydytojaidiagnozes.web.controller;

import com.Gydytojaidiagnozes.web.model.Diagnose;
import com.Gydytojaidiagnozes.web.service.DiagnoseService;
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
public class DiagnoseControllerMVC {
    @Autowired
    private DiagnoseService diagnoseService;

    @GetMapping("/list-diagnoses")
    public String showDiagnoses(ModelMap model) {
        model.put("diagnoses", diagnoseService.findAllDiagnoses());
        return "list-diagnoses"; // view resolver
    }
    @GetMapping("/add-diagnose")
    public String showAddPage(ModelMap model) {
        model.addAttribute("diagnose", new Diagnose());
        return "add-diagnose";
    }

    @PostMapping("/add-diagnose")
    public String add(ModelMap model, @ModelAttribute("diagnose") Diagnose diagnose, BindingResult result) {

        if(result.hasErrors()) {
            return "add-diagnose";
        }
        diagnoseService.saveDiagnose(diagnose);

        return "redirect:/list-diagnoses";
    }

    @GetMapping("/delete-diagnose/{id}")
    @Transactional
    public String delete(@PathVariable int id) {
        diagnoseService.deleteDiagnoseById(id);
        return "redirect:/list-diagnoses";
    }
    @GetMapping("/update-diagnose/{id}")
    public String showUpdatePage(ModelMap model, @PathVariable int id) {
        model.addAttribute("diagnose", diagnoseService.findDiagnoseById(id));
        return "add-diagnose";
    }

    @PostMapping("/update-diagnose/{id}")
    public String update(ModelMap model, @ModelAttribute("diagnose") Diagnose diagnose, @PathVariable int id,
                         BindingResult result) {
        if(result.hasErrors()) {
            return "add-diagnose";
        }

        diagnoseService.updateDiagnose(diagnose);

        return "redirect:/list-diagnoses";
    }
}
