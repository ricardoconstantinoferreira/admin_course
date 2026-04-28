package com.ferreiracurso.admin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Test to verify the application context loads successfully with JWT authentication.
 */
@SpringBootTest
@ActiveProfiles("test")
class JwtAuthenticationTest {

    @Test
    void contextLoads() {
        // This test will pass if the Spring application context loads successfully,
        // including all security and JWT components
    }
}
