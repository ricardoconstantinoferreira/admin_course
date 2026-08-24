package com.ferreiracurso.admin.controller;

import com.ferreiracurso.admin.dto.QuestionAnswerResponse;
import com.ferreiracurso.admin.dto.QuestionDto;
import com.ferreiracurso.admin.model.ExamQuestionOption;
import com.ferreiracurso.admin.model.Question;
import com.ferreiracurso.admin.model.enums.TypeQuestion;
import com.ferreiracurso.admin.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @PutMapping("/{id}")
    public ResponseEntity<Question> update(@PathVariable Long id, @RequestBody QuestionDto questionDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                questionService.update(id, questionDto)
        );
    }

    @GetMapping("/{studentId}/{subjectId}")
    public ResponseEntity<List<ExamQuestionOption>> getQuestion(@PathVariable Long studentId, @PathVariable Long subjectId) {
        List<ExamQuestionOption> optionList = questionService.getQuestionDescription(studentId, subjectId);
        return ResponseEntity.status(HttpStatus.OK).body(optionList);
    }
}
