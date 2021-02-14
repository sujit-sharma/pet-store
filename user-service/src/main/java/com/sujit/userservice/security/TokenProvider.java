package com.sujit.userservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;

@Slf4j
@Component
public class TokenProvider {

    private static final String SECRET = "MySecret@#$%K%y";

    public String getToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new NoAuthFoundException("User not logged in");
        }

        log.info("Generating Jwt token ");
        
        User user = (User) authentication.getPrincipal();
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("authorities", new HashSet<>(user.getAuthorities()));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 2000L))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

    }
}
