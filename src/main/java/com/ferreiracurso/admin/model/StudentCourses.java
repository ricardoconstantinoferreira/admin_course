package com.ferreiracurso.admin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student_courses")
public class StudentCourses {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "locked", nullable = false)
    private boolean locked = false;

    @Column(name = "finish", nullable = false)
    private boolean finish = false;

    public StudentCourses() {
    }

    public StudentCourses(Long id, Student student, Course course, boolean locked, boolean finish) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.locked = locked;
        this.finish = finish;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}
