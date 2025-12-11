package com.jenkins.dev.controller;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jenkins.dev.service.PrintService;


@RestController
@RequestMapping("/api/print")
public class printController {

        PrintService printService;
        printController(PrintService printService){
             this.printService=printService;
        }
        @GetMapping
      public ResponseEntity<String> printStat(){
            String s= printService.sayHello();
            return ResponseEntity.ok(s);
         }

}
