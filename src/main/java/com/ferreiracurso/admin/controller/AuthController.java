package com.ferreiracurso.admin.controller;

import com.ferreiracurso.admin.service.impl.AuthServiceImpl;
import com.ferreiracurso.admin.dto.LoginRequest;
import com.ferreiracurso.admin.dto.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

/**
 * Auth endpoints. Controllers kept thin: delegates to service layer.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.warn("Invalid login request");
            return ResponseEntity.badRequest().body("Invalid credentials format");
        }
        try {
            String token = authService.authenticateAndCreateToken(request.getUsernameOrEmail(), request.getPassword());
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (BadCredentialsException ex) {
            logger.warn("Bad credentials for {}", request.getUsernameOrEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (Exception ex) {
            logger.error("Authentication error", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Authentication failed");
        }
    }
}
