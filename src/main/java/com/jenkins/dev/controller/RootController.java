package com.jenkins.dev.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String greeting(Model model){
        // return "Hello from Nitish Kumar through jenkins pipeline ";
        model.addAttribute("deployedBy", "Nitish Kumar");
        model.addAttribute("deploymentMethod", "Jenkins Pipeline");
        model.addAttribute("deploymentTime", new java.util.Date());
        model.addAttribute("status", "Active and Running");
        return "index"; // This will look for src/main/resources/templates/index.html
    }
}
