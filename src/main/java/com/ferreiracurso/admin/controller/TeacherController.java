package com.ferreiracurso.admin.controller;

import com.ferreiracurso.admin.dto.AssociateTeacherSubjects;
import com.ferreiracurso.admin.dto.CreateTeacherRequest;
import com.ferreiracurso.admin.dto.TeacherDto;
import com.ferreiracurso.admin.model.Teacher;
import com.ferreiracurso.admin.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateTeacherRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.warn("Invalid create professor request");
            return ResponseEntity.badRequest().body("Invalid payload");
        }
        TeacherDto created = teacherService.create(request);
        return ResponseEntity.created(URI.create("/api/professors/" + created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAll() {
        return ResponseEntity.ok(teacherService.getAll());
    }

    @PostMapping("/associate")
    public ResponseEntity<Teacher> associate(@RequestBody AssociateTeacherSubjects associateTeacherSubjects) {
        return ResponseEntity.status(HttpStatus.OK).body(
                teacherService.associate(associateTeacherSubjects)
        );
    }

    @PutMapping("/exit/{id}")
    public ResponseEntity<Teacher> exit(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                teacherService.exitTeacher(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CreateTeacherRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid payload");
        }
        TeacherDto updated = teacherService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
