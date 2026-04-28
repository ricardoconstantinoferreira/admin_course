package com.ferreiracurso.admin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Login request DTO validated with JSR-380.
 */
public class LoginRequest {

    @NotBlank
    @Size(min = 3, max = 100)
    private String usernameOrEmail;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
