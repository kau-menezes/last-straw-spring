package com.example.demo.interfaces;

import java.util.Map;

import io.jsonwebtoken.Claims;

public interface IJWTService {
    
    public String generateToken(Map<String, Object> claims);
    public Claims extractInfo(String token);

}
