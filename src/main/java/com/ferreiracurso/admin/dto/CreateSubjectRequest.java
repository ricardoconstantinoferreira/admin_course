package com.ferreiracurso.admin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO for creating a Subject.
 */
public class CreateSubjectRequest {

    @NotBlank
    @Size(min = 1, max = 500)
    private String description;

    public CreateSubjectRequest() {
    }

    public CreateSubjectRequest(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
