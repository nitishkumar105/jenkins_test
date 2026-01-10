package com.jenkins.dev.service;

import com.jenkins.dev.dto.PrintResponse;
import org.springframework.stereotype.Service;


@Service
 public class PrintService {

       public PrintResponse printAbout(){
           return new PrintResponse("nitish","nitishkumaryadav105@gmail.com");
       }

}
