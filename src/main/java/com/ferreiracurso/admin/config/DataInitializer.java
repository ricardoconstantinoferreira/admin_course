package com.ferreiracurso.admin.config;

import com.ferreiracurso.admin.model.User;
import com.ferreiracurso.admin.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Creates a default user for local development if none exists.
 * Password: 'password' (encoded). In production, remove or protect this initializer.
 */
@Configuration
public class DataInitializer {
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    public CommandLineRunner createDefaultUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                User u = new User("admin", "admin@example.com", passwordEncoder.encode("password"), "ROLE_ADMIN");
                userRepository.save(u);
                logger.info("Created default admin user 'admin' with password 'password' (change in production)");
            }
        };
    }
}
