package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.example.demo.filters.TokenFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    RequestMatcher protectedEndpoints = new AntPathRequestMatcher("/protected/**");
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/**").permitAll()
                .requestMatchers("/authenticated").authenticated()
            )
            .addFilterBefore(new TokenFilter(protectedEndpoints), UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}