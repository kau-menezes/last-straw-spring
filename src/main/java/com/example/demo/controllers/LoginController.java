package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.login.LoginPayload;
import com.example.demo.dto.login.LoginResponse;
import com.example.demo.interfaces.ILoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    ILoginService loginService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginPayload payload) {
        var response = loginService.login(payload);
        return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
    }
}
