package com.example.demo.interfaces;

import com.example.demo.dto.users.UserCreationDTO;
import com.example.demo.dto.users.UserResponseDTO;
import com.example.demo.dto.users.UserUpdateDTO;

public interface IUserService {
    public UserResponseDTO createUser(UserCreationDTO user);
    public UserResponseDTO changePassword(UserUpdateDTO user);

}
 
