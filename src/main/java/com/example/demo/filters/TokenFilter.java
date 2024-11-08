package com.example.demo.filters;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.interfaces.IJWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TokenFilter extends OncePerRequestFilter {
    
    IJWTService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
        throws ServletException, IOException {

        // verifica se o token foi enviado
        String token = request.getHeader("Authorization");
        if(token == null) {
            return;
        }
        
        // tenta tirar as informações do token, que caso seja inválido, estoura um erro.
        token = token.substring(7);
        try {
            var claims = tokenService.extractInfo(token);
            var authentication = new UsernamePasswordAuthenticationToken(claims.get("username"), null, null);
            // com as informações, é iniciado um objeto de credenciais no request que é do spring
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            request.setAttribute("username", claims.get("username"));
            request.setAttribute("email", claims.get("email"));
            request.setAttribute("id", claims.get("id"));
        } catch (Exception e) {
            return;
        }

        filterChain.doFilter(request, response);
    }
}
