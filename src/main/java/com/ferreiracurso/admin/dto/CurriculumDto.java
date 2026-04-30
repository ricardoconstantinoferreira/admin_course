package com.ferreiracurso.admin.dto;

/**
 * Response DTO for Curriculum (Grade Curricular).
 */
public class CurriculumDto {
    private Long id;
    private String description;
    private Long courseId;
    private String courseDescription;

    public CurriculumDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
}
