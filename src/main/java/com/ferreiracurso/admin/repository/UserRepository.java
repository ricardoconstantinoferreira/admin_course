package com.ferreiracurso.admin.repository;

import com.ferreiracurso.admin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for User. Uses Spring Data JPA to prevent SQL injection via parameter binding.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameIgnoreCase(String username);
    Optional<User> findByEmailIgnoreCase(String email);

    default Optional<User> findByUsernameOrEmail(String input) {
        Optional<User> byUsername = findByUsernameIgnoreCase(input);
        return byUsername.isPresent() ? byUsername : findByEmailIgnoreCase(input);
    }
}
