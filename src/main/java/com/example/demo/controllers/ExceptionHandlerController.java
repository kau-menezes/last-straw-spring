package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exceptions.ResponseException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHandlerController {

    public record Message(String message) {}
    
    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<Message> responseError(ResponseException ex, HttpServletRequest request) {
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(new Message(ex.getMessage()));
    }
}
