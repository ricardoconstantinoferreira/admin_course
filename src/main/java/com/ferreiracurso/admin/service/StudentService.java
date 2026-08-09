package com.ferreiracurso.admin.service;

import com.ferreiracurso.admin.dto.FinishDto;
import com.ferreiracurso.admin.dto.LockedDto;
import com.ferreiracurso.admin.dto.StudentCourseDto;
import com.ferreiracurso.admin.dto.StudentDto;
import com.ferreiracurso.admin.model.Student;

import java.util.List;

public interface StudentService {
    Student save(StudentDto studentDto);
    Student getById(Long id);
    List<Student> getAll();
    String associateStudentToCourse(StudentCourseDto studentCourseDto);
    String changeLockedStudentCourse(LockedDto lockedDto);
    String changeFinishStudentCourse(FinishDto finishDto);
    String authenticateStudentToken(String registration, String password);
}
