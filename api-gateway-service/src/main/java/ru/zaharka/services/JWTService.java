package ru.zaharka.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JWTService {
    private final static String JWT_SECRET = "a3366ab389a1d7b3f1fbcad97c499bb976294ef24c38e6c2472ac866167727bc";

    public Jws<Claims> parseToken(String token) {
        return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token);
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
