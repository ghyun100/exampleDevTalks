package com.teamproject.devTalks.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {
    @Value("${jwt.secret-key}")
    private String SECRET_KEY;

    public String createJwt(String email, String role) {

        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        String jwt = 
            Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .claim("role", role)
                .compact();
        return jwt;

    }

    public String validateJwt(String jwt) {
        Claims claims =
                Jwts.parser().setSigningKey(SECRET_KEY)
                        .parseClaimsJws(jwt).getBody();
        return claims.getSubject();

    }
    public List<GrantedAuthority> getAuthoritiesFromJwt(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt).getBody();
        String role = claims.get("role", String.class);
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
    }

}
