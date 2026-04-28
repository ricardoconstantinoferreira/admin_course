package com.ferreiracurso.admin.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

/**
 * JWT generation and validation service using HMAC256.
 * Uses a default secret for development if none provided.
 */
@Service
public class JwtService {
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);
    private static final String DEFAULT_SECRET = "default-jwt-secret-for-development-only-at-least-32-characters-long";

    private final JwtProperties properties;
    private Algorithm algorithm;
    private JWTVerifier verifier;

    public JwtService(JwtProperties properties) {
        this.properties = properties;
    }

    @PostConstruct
    public void initialize() {
        String secret = getSecret();
        logger.info("Initializing JwtService with secret length: {}", secret.length());

        this.algorithm = Algorithm.HMAC256(secret.getBytes(StandardCharsets.UTF_8));
        this.verifier = JWT.require(algorithm)
                .withIssuer(properties.getIssuer())
                .build();

        logger.info("JwtService initialized successfully");
    }

    private String getSecret() {
        String secret = properties.getSecret();

        if (secret != null && !secret.isBlank() && secret.length() >= 32) {
            logger.debug("Using configured JWT secret");
            return secret;
        }

        logger.warn("Using default JWT secret. Set JWT_SECRET environment variable or security.jwt.secret property for production.");
        return DEFAULT_SECRET;
    }

    public String generateToken(String username, Long userId) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(properties.getExpirationSeconds());

        return JWT.create()
                .withIssuer(properties.getIssuer())
                .withSubject(username)
                .withClaim("uid", userId)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(exp))
                .sign(algorithm);
    }

    public boolean validateToken(String token) {
        if (token == null || token.isBlank()) {
            return false;
        }
        try {
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException ex) {
            logger.debug("Invalid JWT token: {}", ex.getMessage());
            return false;
        }
    }

    public DecodedJWT decode(String token) {
        if (token == null || token.isBlank()) {
            return null;
        }
        try {
            return verifier.verify(token);
        } catch (JWTVerificationException ex) {
            logger.debug("Could not decode JWT: {}", ex.getMessage());
            return null;
        }
    }
}
