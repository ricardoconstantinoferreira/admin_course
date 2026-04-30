package com.ferreiracurso.admin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Request DTO to create or update Curriculum.
 */
public class CreateCurriculumRequest {

    @NotBlank
    @Size(min = 1, max = 1000)
    private String description;

    @NotNull
    private Long courseId;

    public CreateCurriculumRequest() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
