package com.ferreiracurso.admin.controller;

import com.ferreiracurso.admin.dto.ResponseMultipleChoiseDto;
import com.ferreiracurso.admin.mapper.ResponseMultipleChoiseMapper;
import com.ferreiracurso.admin.model.Question;
import com.ferreiracurso.admin.model.ResponseMultipleChoise;
import com.ferreiracurso.admin.model.Student;
import com.ferreiracurso.admin.service.QuestionService;
import com.ferreiracurso.admin.service.ResponseMultipleChoiseService;
import com.ferreiracurso.admin.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/response-multiple-choise")
public class ResponseMultipleChoiseController {

    private final ResponseMultipleChoiseService responseMultipleChoiseService;
    private final ResponseMultipleChoiseMapper responseMultipleChoiseMapper;
    private final StudentService studentService;
    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<ResponseMultipleChoise> save(@RequestBody ResponseMultipleChoiseDto responseMultipleChoiseDto) {
        ResponseMultipleChoise responseMultipleChoise = responseMultipleChoiseMapper.toEntity(responseMultipleChoiseDto);
        Student student = studentService.getById(responseMultipleChoiseDto.getStudentId());
        Question question = questionService.getById(responseMultipleChoiseDto.getQuestionId());

        responseMultipleChoise.setStudent(student);
        responseMultipleChoise.setQuestion(question);

        return ResponseEntity.status(HttpStatus.OK).body(
                responseMultipleChoiseService.save(responseMultipleChoise)
        );
    }
}
