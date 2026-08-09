package com.ferreiracurso.admin.controller;

import com.ferreiracurso.admin.dto.*;
import com.ferreiracurso.admin.model.Student;
import com.ferreiracurso.admin.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody StudentLoginDto studentLoginDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn("Invalid login request");
            return ResponseEntity.badRequest().body("Invalid credentials format");
        }

        try {
            String token = studentService.authenticateStudentToken(studentLoginDto.getRegistration(), studentLoginDto.getPassword());
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (BadCredentialsException ex) {
            log.warn("Bad credentials for {}", studentLoginDto.getRegistration());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (Exception ex) {
            log.error("Authentication error", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Authentication failed");
        }
    }

    @PutMapping("/associate-course")
    public ResponseEntity<String> associate(@Valid @RequestBody StudentCourseDto studentCourseDto) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.associateStudentToCourse(studentCourseDto));
    }

    @PutMapping("/change-locked")
    public ResponseEntity<String> locked(@RequestBody LockedDto lockedDto) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.changeLockedStudentCourse(lockedDto));
    }

    @PutMapping("/change-finish")
    public ResponseEntity<String> finish(@RequestBody FinishDto finishDto) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.changeFinishStudentCourse(finishDto));
    }
}