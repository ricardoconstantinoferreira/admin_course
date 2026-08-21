package com.ferreiracurso.admin.controller;

import com.ferreiracurso.admin.dto.QuestionDto;
import com.ferreiracurso.admin.model.Question;
import com.ferreiracurso.admin.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/question")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<Question> save(@RequestBody QuestionDto questionDto) {
        Question question = questionService.save(questionDto);
        return ResponseEntity.status(HttpStatus.OK).body(question);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        questionService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Pergunta excluída.");
    }
}
