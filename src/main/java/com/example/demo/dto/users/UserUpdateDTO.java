package com.example.demo.dto.users;

public record UserUpdateDTO(String username, String password, String newPassword, String repeatPassword) {
    
}
