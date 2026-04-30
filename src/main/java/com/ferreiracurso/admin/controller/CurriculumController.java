package com.ferreiracurso.admin.controller;

import com.ferreiracurso.admin.dto.CreateCurriculumRequest;
import com.ferreiracurso.admin.dto.CurriculumDto;
import com.ferreiracurso.admin.service.CurriculumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/curriculum")
public class CurriculumController {

    private static final Logger logger = LoggerFactory.getLogger(CurriculumController.class);

    private final CurriculumService curriculumService;

    public CurriculumController(CurriculumService curriculumService) {
        this.curriculumService = curriculumService;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateCurriculumRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.warn("Invalid create curriculum request");
            return ResponseEntity.badRequest().body("Invalid payload");
        }
        CurriculumDto created = curriculumService.create(request);
        // Mantém o Location consistente com o @RequestMapping do controller e com os demais endpoints expostos.
        return ResponseEntity.created(URI.create("/api/curriculum/" + created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurriculumDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(curriculumService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<CurriculumDto>> getAll() {
        return ResponseEntity.ok(curriculumService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CreateCurriculumRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid payload");
        }
        CurriculumDto updated = curriculumService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        curriculumService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
