package com.ferreiracurso.admin.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Type-safe binding for JWT configuration. Secrets should be provided via environment variables in production.
 */
@Component
@ConfigurationProperties(prefix = "security.jwt")
public class JwtProperties {

    private String secret;

    private Long expirationSeconds = 3600L; // 1 hour default

    private String issuer = "ferreiracurso-admin";

    public String getSecret() {
        // Fallback to environment variable JWT_SECRET if property not provided
        if (secret != null && !secret.isBlank()) {
            return secret;
        }
        String env = System.getenv("JWT_SECRET");
        return (env != null && !env.isBlank()) ? env : null;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpirationSeconds() {
        return expirationSeconds;
    }

    public void setExpirationSeconds(Long expirationSeconds) {
        this.expirationSeconds = expirationSeconds;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }
}
