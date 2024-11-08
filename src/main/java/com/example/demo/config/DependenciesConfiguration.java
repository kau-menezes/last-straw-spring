package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.interfaces.IJWTService;
import com.example.demo.interfaces.ILoginService;
import com.example.demo.interfaces.IUserService;
import com.example.demo.services.JWTService;
import com.example.demo.services.LoginService;
import com.example.demo.services.UserService;

@Configuration
public class DependenciesConfiguration {

    @Bean
    public IUserService userService() {
        return new UserService();
    }

    @Bean
    public ILoginService loginService() {
        return new LoginService();
    }

    @Bean
    public IJWTService tokenService() {
        return new JWTService();
    }
}
