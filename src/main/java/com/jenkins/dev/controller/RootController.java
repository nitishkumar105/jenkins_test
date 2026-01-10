package com.jenkins.dev.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/")
    public String greeting(){
         return "Hello from Nitish Kumar through jenkins pipeline ";
    }
}
