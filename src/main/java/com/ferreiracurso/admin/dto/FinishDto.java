package com.ferreiracurso.admin.dto;

public class FinishDto {
    private Long studentId;
    private Long courseId;
    private boolean finish;

    public FinishDto() {
    }

    public FinishDto(Long studentId, Long courseId, boolean finish) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.finish = finish;
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

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}
