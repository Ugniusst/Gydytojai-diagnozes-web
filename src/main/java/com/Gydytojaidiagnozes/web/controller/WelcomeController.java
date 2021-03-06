package com.Gydytojaidiagnozes.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController {
    //@RequestMapping(value = "/", method= RequestMethod.GET)
    @GetMapping("/")
    public String showRootPage(ModelMap model) {
        model.put("name", "ANONYMOUS");
        return "welcome"; // view resolver
    }

    @GetMapping("/welcome") //
    public String showWelcomePage(@RequestParam String name, ModelMap model) {
        model.put("name", name);
        return "welcome"; // view resolver
    }
}
