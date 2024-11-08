package com.example.demo.exceptions;

import lombok.Getter;

@Getter
public class ResponseException extends RuntimeException {

    private final String message;
    private final int statusCode;

    public ResponseException(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
