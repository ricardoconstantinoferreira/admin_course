package com.ferreiracurso.admin.controller;

import com.ferreiracurso.admin.dto.StudentCourseDto;
import com.ferreiracurso.admin.dto.StudentDto;
import com.ferreiracurso.admin.model.Student;
import com.ferreiracurso.admin.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> save(@Valid @RequestBody StudentDto studentDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                studentService.save(studentDto)
        );
    }

    @PutMapping("/associate-course")
    public ResponseEntity<String> associate(@Valid @RequestBody StudentCourseDto studentCourseDto) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.associateStudentToCourse(studentCourseDto));
    }
}