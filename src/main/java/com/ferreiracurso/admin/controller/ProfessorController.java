package com.ferreiracurso.admin.controller;

import com.ferreiracurso.admin.dto.CreateProfessorRequest;
import com.ferreiracurso.admin.dto.ProfessorDto;
import com.ferreiracurso.admin.service.ProfessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/professors")
public class ProfessorController {

    private static final Logger logger = LoggerFactory.getLogger(ProfessorController.class);

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateProfessorRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.warn("Invalid create professor request");
            return ResponseEntity.badRequest().body("Invalid payload");
        }
        ProfessorDto created = professorService.create(request);
        return ResponseEntity.created(URI.create("/api/professors/" + created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(professorService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDto>> getAll() {
        return ResponseEntity.ok(professorService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CreateProfessorRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid payload");
        }
        ProfessorDto updated = professorService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        professorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
