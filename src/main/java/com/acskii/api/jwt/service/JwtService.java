package com.acskii.api.jwt.service;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

/*
*
*   Service responsible to generate a unique JWT token
*   to each registered user that sent a login request
*
*   Its lifetime is detailed in application.yml
*
* */

@Service
public class JwtService {
    private final String issuer;
    private final Duration ttl;
    private final JwtEncoder jwtEncoder;

    public JwtService(String issuer, Duration ttl, JwtEncoder jwtEncoder) {
        this.issuer = issuer;
        this.ttl = ttl;
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(final String email) {
        final var claimsSet = JwtClaimsSet.builder()
                .subject(email)
                .issuer(issuer)
                .expiresAt(Instant.now().plus(ttl))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet))
                .getTokenValue();
    }
}
