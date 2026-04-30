package com.ferreiracurso.admin.model;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * Curriculum (Grade Curricular) entity related one-to-one with Course.
 * - Stores a description and a reference to the Course entity.
 */
@Entity
@Table(name = "curriculum")
public class Curriculum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false, unique = true)
    private Course course;

    public Curriculum() {
    }

    public Curriculum(String description, Course course) {
        this.description = description;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
