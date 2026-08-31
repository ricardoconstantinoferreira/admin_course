package com.ferreiracurso.admin.controller;

import com.ferreiracurso.admin.dto.ResponseSelectionBoxDto;
import com.ferreiracurso.admin.mapper.ResponseSelectionBoxMapper;
import com.ferreiracurso.admin.model.Question;
import com.ferreiracurso.admin.model.ResponseSelectionBox;
import com.ferreiracurso.admin.model.Student;
import com.ferreiracurso.admin.service.QuestionService;
import com.ferreiracurso.admin.service.ResponseSelectionBoxService;
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
@RequestMapping("/api/response-selection-box")
public class ResponseSelectionBoxController {

    private final ResponseSelectionBoxService responseSelectionBoxService;
    private final ResponseSelectionBoxMapper responseSelectionBoxMapper;
    private final StudentService studentService;
    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<ResponseSelectionBox> save(@RequestBody ResponseSelectionBoxDto responseSelectionBoxDto) {
        ResponseSelectionBox responseSelectionBox = responseSelectionBoxMapper.toEntity(responseSelectionBoxDto);
        Student student = studentService.getById(responseSelectionBoxDto.getStudentId());
        Question question = questionService.getById(responseSelectionBoxDto.getQuestionId());

        responseSelectionBox.setStudent(student);
        responseSelectionBox.setQuestion(question);
        responseSelectionBox.setOptionIds(responseSelectionBoxDto.getOptionsId());

        return ResponseEntity.status(HttpStatus.OK).body(
                responseSelectionBoxService.save(responseSelectionBox)
        );
    }
}
