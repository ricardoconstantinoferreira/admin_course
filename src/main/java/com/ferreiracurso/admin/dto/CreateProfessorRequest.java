package com.ferreiracurso.admin.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class CreateProfessorRequest {

    @NotBlank
    @Size(min = 1, max = 200)
    private String name;

    @NotBlank
    @Size(min = 1, max = 255)
    private String specialization;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal salary;

    private Set<Long> subjectIds;

    public CreateProfessorRequest() {
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

    public Set<Long> getSubjectIds() {
        return subjectIds;
    }

    public void setSubjectIds(Set<Long> subjectIds) {
        this.subjectIds = subjectIds;
    }
}
