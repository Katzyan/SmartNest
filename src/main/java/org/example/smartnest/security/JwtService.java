package org.example.smartnest.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.example.smartnest.entities.User;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public JwtService() {
    }

    public String generateToken(User user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("type", user.getType())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }
}
