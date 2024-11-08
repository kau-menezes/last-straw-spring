package com.example.demo.services;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import com.example.demo.exceptions.ResponseException;
import com.example.demo.interfaces.IJWTService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JWTService implements IJWTService {

    private String secretKey = "secretkeymuitolokarynfyjryujryujryj6u474y7tuj4tyj4j";
    private Long expirationTime = (long) (1000*60*60);

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String generateToken(Map<String, Object> claims) {
        return Jwts
                .builder()
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSignInKey())
                .compact();
    }

    @Override
    public Claims extractInfo(String token) throws ResponseException {
        try {
            return Jwts
                    .parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException e) {
            throw new ResponseException("Invalid Token", 401);
        }
    }
}