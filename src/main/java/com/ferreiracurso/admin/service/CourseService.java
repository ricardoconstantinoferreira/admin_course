package com.ferreiracurso.admin.service;

import com.ferreiracurso.admin.dto.CourseDto;
import com.ferreiracurso.admin.dto.CreateCourseRequest;
import com.ferreiracurso.admin.model.StudentRegistrationEmailResponse;
import com.ferreiracurso.admin.model.StudentRegistrationResponse;

import java.util.List;

public interface CourseService {
    CourseDto create(CreateCourseRequest request);
    CourseDto getById(Long id);
    List<CourseDto> getAll();
    CourseDto update(Long id, CreateCourseRequest request);
    void delete(Long id);
    List<StudentRegistrationResponse> getStudentExamNoDone(Long examId);
    List<StudentRegistrationEmailResponse> getStudentEmail(Long examId);
}
