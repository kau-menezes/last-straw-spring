package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.users.UserCreationDTO;
import com.example.demo.dto.users.UserResponseDTO;
import com.example.demo.dto.users.UserUpdateDTO;
import com.example.demo.interfaces.IUserService;

@RestController
@RequestMapping("/users")
public class UserController {
    

    @Autowired
    IUserService service;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserCreationDTO user) {

        var createdUser = service.createUser(user);

        return new ResponseEntity<UserResponseDTO>(createdUser, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<UserResponseDTO> updatePassword(@RequestBody UserUpdateDTO user) {
        var updated = service.changePassword(user);

        return new ResponseEntity<UserResponseDTO>(updated, HttpStatus.OK);

    }    
}
