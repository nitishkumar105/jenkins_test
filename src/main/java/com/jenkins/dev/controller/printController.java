package com.jenkins.dev.controller;
import com.jenkins.dev.dto.PrintResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jenkins.dev.service.PrintService;

@RestController
@RequestMapping("/api/print")
public class printController {

     private final   PrintService printService;
      public   printController(PrintService printService){
             this.printService=printService;
        }
        @GetMapping
      public ResponseEntity<PrintResponse> printAbout(){

         //  PrintResponse printRespone=printService.printAbout();

           return ResponseEntity.ok(printService.printAbout());
         }

}
