package com.jenkins.dev.controller;


import com.jenkins.dev.dto.UserRequestDto;
import com.jenkins.dev.dto.UserResponseDto;
import com.jenkins.dev.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

       private final UserService userservice;
       public UserController(UserService userService){
           this.userservice=userService;
       }
     @PostMapping
     public ResponseEntity<UserResponseDto> userCreated(@Valid @RequestBody UserRequestDto request ){
         UserResponseDto userResponseDto= userservice.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
     }
     @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUser(){
             return ResponseEntity.ok(userservice.getAllUsers());
     }
}
