package com.ferreiracurso.admin.service;

import com.ferreiracurso.admin.dto.StudentCourseDto;
import com.ferreiracurso.admin.dto.StudentDto;
import com.ferreiracurso.admin.model.Student;

import java.util.List;

public interface StudentService {
    Student save(StudentDto studentDto);
    Student getById(Long id);
    List<Student> getAll();
    String associateStudentToCourse(StudentCourseDto studentCourseDto);
}
