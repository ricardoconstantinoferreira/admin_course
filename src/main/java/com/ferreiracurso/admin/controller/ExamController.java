package com.ferreiracurso.admin.controller;

import com.ferreiracurso.admin.dto.ExamDto;
import com.ferreiracurso.admin.model.Exam;
import com.ferreiracurso.admin.service.ExamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/exam")
public class ExamController {

    private final ExamService examService;

    @PostMapping
    public ResponseEntity<Exam> save(@RequestBody ExamDto examDto) {

        return ResponseEntity.status(HttpStatus.OK).body(
                examService.save(examDto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        examService.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body(
                "Prova removida"
        );
    }
}
