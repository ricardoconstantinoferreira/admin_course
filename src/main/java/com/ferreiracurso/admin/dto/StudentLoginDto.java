package com.ferreiracurso.admin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentLoginDto {

    @NotBlank
    @Size(min = 3, max = 100)
    private String registration;

    @NotBlank
    @Size(min = 3, max = 100)
    private String password;

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
