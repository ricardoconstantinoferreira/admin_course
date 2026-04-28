package com.ferreiracurso.admin.controller;

import com.ferreiracurso.admin.service.SubjectService;
import com.ferreiracurso.admin.dto.CreateSubjectRequest;
import com.ferreiracurso.admin.dto.SubjectDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    private static final Logger logger = LoggerFactory.getLogger(SubjectController.class);

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateSubjectRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.warn("Invalid create subject request");
            return ResponseEntity.badRequest().body("Invalid payload");
        }
        SubjectDto created = subjectService.create(request);
        return ResponseEntity.created(URI.create("/api/subjects/" + created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(subjectService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<SubjectDto>> getAll() {
        return ResponseEntity.ok(subjectService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CreateSubjectRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid payload");
        }
        SubjectDto updated = subjectService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        subjectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
