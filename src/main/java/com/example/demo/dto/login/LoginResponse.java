package com.example.demo.dto.login;

import com.example.demo.dto.users.UserResponseDTO;

public record LoginResponse(String token, UserResponseDTO user) {}
