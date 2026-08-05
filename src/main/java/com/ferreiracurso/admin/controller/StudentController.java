package com.ferreiracurso.admin.controller;

import com.ferreiracurso.admin.dto.StudentDto;
import com.ferreiracurso.admin.mapper.StudentMapper;
import com.ferreiracurso.admin.model.Student;
import com.ferreiracurso.admin.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @PostMapping
    public ResponseEntity<Student> save(@Valid @RequestBody StudentDto studentDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                studentService.save(studentDto)
        );
    }
}