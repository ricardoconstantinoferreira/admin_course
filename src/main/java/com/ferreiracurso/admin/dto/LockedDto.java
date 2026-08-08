package com.ferreiracurso.admin.dto;

public class LockedDto {
    private Long studentId;
    private Long courseId;
    private boolean locked;

    public LockedDto() {
    }

    public LockedDto(Long studentId, Long courseId, boolean locked) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.locked = locked;
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

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
}
