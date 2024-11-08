package com.example.demo.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.dto.login.LoginPayload;
import com.example.demo.dto.login.LoginResponse;
import com.example.demo.dto.users.UserResponseDTO;
import com.example.demo.exceptions.ResponseException;
import com.example.demo.interfaces.IJWTService;
import com.example.demo.interfaces.ILoginService;
import com.example.demo.repositories.UserRepository;

public class LoginService implements ILoginService {

    @Autowired
    UserRepository repo;

    @Autowired
    IJWTService jwtService;

    private final BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder(); 

    @Override
    public LoginResponse login(LoginPayload payload) {
        
        var query = repo.findOneByUsername(payload.username());
        if(query.isEmpty()) throw new ResponseException("User not found", 404);

        var user = query.get();

        if(!encoder.matches(payload.password(), user.getPassword())) {
            throw new ResponseException("Incorrect credentials", 401);
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("email", user.getEmail());
        claims.put("id", user.getId());
        String token = jwtService.generateToken(claims);

        return new LoginResponse(token, new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail()));
    }
    
}
