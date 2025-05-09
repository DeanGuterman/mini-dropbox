package com.deanguterman.minidropbox;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final Key secretKey;
    private final long jwtExpirationMillis;

    public JwtService(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") long jwtExpirationMillis) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.jwtExpirationMillis = jwtExpirationMillis;
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMillis))
                .signWith(secretKey)
                .compact();
    }
}
