package com.ferreiracurso.admin;

import com.ferreiracurso.admin.security.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test to verify JwtService bean is created successfully without JWT_SECRET configuration.
 */
@SpringBootTest
class JwtServiceInitializationTest {

    @Autowired
    private JwtService jwtService;

    @Test
    void jwtServiceShouldBeInitialized() {
        assertNotNull(jwtService, "JwtService should be initialized successfully");
    }

    @Test
    void jwtServiceShouldGenerateToken() {
        String token = jwtService.generateToken("testuser", 1L);
        assertNotNull(token, "JwtService should generate a valid token");
    }
}
