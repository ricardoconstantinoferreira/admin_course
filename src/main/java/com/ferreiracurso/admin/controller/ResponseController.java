package com.ferreiracurso.admin.controller;

import com.ferreiracurso.admin.dto.ResponseDto;
import com.ferreiracurso.admin.mapper.ResponseMapper;
import com.ferreiracurso.admin.model.Question;
import com.ferreiracurso.admin.model.Response;
import com.ferreiracurso.admin.model.Student;
import com.ferreiracurso.admin.service.QuestionService;
import com.ferreiracurso.admin.service.ResponseService;
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
@RequestMapping("/api/response")
public class ResponseController {

    private final ResponseService responseService;
    private final StudentService studentService;
    private final QuestionService questionService;
    private final ResponseMapper responseMapper;

    @PostMapping("/description")
    public ResponseEntity<Response> save(@RequestBody ResponseDto responseDto) {
        Response response = responseMapper.toEntity(responseDto);
        Student student = studentService.getById(responseDto.getStudentId());
        Question question = questionService.getById(responseDto.getQuestionId());

        response.setQuestion(question);
        response.setStudent(student);

        return ResponseEntity.status(HttpStatus.OK).body(
                responseService.save(response)
        );
    }
}
