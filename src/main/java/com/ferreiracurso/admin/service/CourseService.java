package com.ferreiracurso.admin.service;

import com.ferreiracurso.admin.dto.CourseDto;
import com.ferreiracurso.admin.dto.CreateCourseRequest;

import java.util.List;

public interface CourseService {
    CourseDto create(CreateCourseRequest request);
    CourseDto getById(Long id);
    List<CourseDto> getAll();
    CourseDto update(Long id, CreateCourseRequest request);
    void delete(Long id);
}
