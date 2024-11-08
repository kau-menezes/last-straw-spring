package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.dto.users.UserCreationDTO;
import com.example.demo.dto.users.UserResponseDTO;
import com.example.demo.dto.users.UserUpdateDTO;
import com.example.demo.entities.UserEntity;
import com.example.demo.exceptions.ResponseException;
import com.example.demo.interfaces.IUserService;
import com.example.demo.repositories.UserRepository;

public class UserService implements IUserService {
    
    @Autowired
    UserRepository repo;
    
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 
    
    @Override
    public UserResponseDTO createUser(UserCreationDTO user) {
        UserEntity newUser = new UserEntity();
        newUser.setEmail(user.email());
        newUser.setUsername(user.username());
        
        newUser.setPassword(encoder.encode(user.password()));
        
        var saved = repo.saveAndFlush(newUser);
        
        return new UserResponseDTO(saved.getId(), saved.getUsername(), saved.getEmail());
    }
    
    @Override
    public UserResponseDTO changePassword(UserUpdateDTO payload) {
        var query = repo.findOneByUsername(payload.username());
        if(query.isEmpty()) throw new ResponseException("User not found", 404);

        var user = query.get();
        user.setPassword(encoder.encode(payload.newPassword()));
        repo.save(user);

        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail());
    }
    
}
