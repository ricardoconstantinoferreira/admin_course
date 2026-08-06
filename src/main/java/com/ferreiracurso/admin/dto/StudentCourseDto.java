package com.ferreiracurso.admin.dto;

import jakarta.validation.constraints.NotNull;

public class StudentCourseDto {

    @NotNull(message = "Por favor, informe o id do estudante")
    private Long studentId;

    @NotNull(message = "Por favor, informe o id do curso")
    private Long courseId;

    public StudentCourseDto() {
    }

    public StudentCourseDto(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
