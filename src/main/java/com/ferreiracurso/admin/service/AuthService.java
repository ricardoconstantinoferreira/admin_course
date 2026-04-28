package com.ferreiracurso.admin.service;

import com.ferreiracurso.admin.model.User;

public interface AuthService {

    String authenticateAndCreateToken(String usernameOrEmail, String rawPassword);

    User createUser(String username, String email, String rawPassword, String role);
}
