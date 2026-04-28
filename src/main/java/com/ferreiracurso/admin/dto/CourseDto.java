package com.ferreiracurso.admin.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * DTO representing a Course for responses.
 */
public class CourseDto {
    private Long id;
    private String description;
    private Integer totalTime;
    private BigDecimal price;
    private Set<SubjectDto> subjects = new HashSet<>();

    public CourseDto() {
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

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<SubjectDto> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<SubjectDto> subjects) {
        this.subjects = subjects;
    }
}
