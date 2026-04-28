package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.model.User;
import com.ferreiracurso.admin.repository.UserRepository;
import com.ferreiracurso.admin.security.JwtService;
import com.ferreiracurso.admin.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Auth service: validates credentials and produces JWT tokens.
 */
@Service
public class AuthServiceImpl implements AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public String authenticateAndCreateToken(String usernameOrEmail, String rawPassword) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail)
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            logger.warn("Bad credentials for {}", usernameOrEmail);
            throw new BadCredentialsException("Invalid username or password");
        }

        String token = jwtService.generateToken(user.getUsername(), user.getId());
        logger.info("Issued token for user {} (id={})", user.getUsername(), user.getId());
        return token;
    }

    @Override
    public User createUser(String username, String email, String rawPassword, String role) {
        User user = new User(username, email, passwordEncoder.encode(rawPassword), role);
        return userRepository.save(user);
    }
}
