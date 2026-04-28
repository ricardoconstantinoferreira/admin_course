package com.ferreiracurso.admin.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ProfessorDto {
    private Long id;
    private String name;
    private String specialization;
    private BigDecimal salary;
    private Set<SubjectDto> subjects = new HashSet<>();

    public ProfessorDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Set<SubjectDto> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<SubjectDto> subjects) {
        this.subjects = subjects;
    }
}
