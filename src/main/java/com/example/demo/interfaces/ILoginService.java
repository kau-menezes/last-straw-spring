package com.example.demo.interfaces;

import com.example.demo.dto.login.LoginPayload;
import com.example.demo.dto.login.LoginResponse;

public interface ILoginService {
    LoginResponse login(LoginPayload payload);
}
