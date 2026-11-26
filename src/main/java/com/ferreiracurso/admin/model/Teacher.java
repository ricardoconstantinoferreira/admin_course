package com.ferreiracurso.admin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, length = 255)
    private String specialization;

    @Column(nullable = false)
    private BigDecimal salary;

    @Column(name = "admission_date")
    private LocalDateTime admissionDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "teacher_subject",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects = new HashSet<>();

    @Column(name = "email")
    private String email;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id) && Objects.equals(name, teacher.name) && Objects.equals(specialization, teacher.specialization) && Objects.equals(salary, teacher.salary) && Objects.equals(admissionDate, teacher.admissionDate) && Objects.equals(endDate, teacher.endDate) && Objects.equals(subjects, teacher.subjects) && Objects.equals(email, teacher.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, specialization, salary, admissionDate, endDate, subjects, email);
    }
}
