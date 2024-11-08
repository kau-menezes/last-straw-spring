package com.example.demo.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.interfaces.IJWTService;
import com.example.demo.services.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TokenFilter extends OncePerRequestFilter {

    private final RequestMatcher requestMatcher;

    public TokenFilter(RequestMatcher requestMatcher) {
        this.requestMatcher = requestMatcher;
    }
    
    // não dá pra injetar dependencia em filtro
    private IJWTService tokenService = new JWTService();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
        throws ServletException, IOException {
            
        // Etapa que faz o filtro se aplicar somente a rotas que você quer!!
        if (requestMatcher.matches(request)) {
            // verifica se o token foi enviado
            String token = request.getHeader("Authorization");
            if(token == null) {
                // mudando a response para 401 não autorizado
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
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
                // mudando a response para 401 não autorizado porque o token era inválido
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                return;
            }
        }


        filterChain.doFilter(request, response);
    }
}
