package com.deanguterman.minidropbox;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtService {

    private final Key secretKey;
    private final long jwtExpirationMilis;

    public JwtService(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") long jwtExpirationMilis) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.jwtExpirationMilis = jwtExpirationMilis;
    }
}
