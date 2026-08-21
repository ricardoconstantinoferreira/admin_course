package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.dto.QuestionDto;
import com.ferreiracurso.admin.model.*;
import com.ferreiracurso.admin.repository.QuestionRepository;
import com.ferreiracurso.admin.service.ExamService;
import com.ferreiracurso.admin.service.QuestionService;
import com.ferreiracurso.admin.strategy.QuestionSaveContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final ExamService examService;
    private QuestionSaveContext questionSaveContext;

    @Override
    public Question save(QuestionDto questionDto) {
        Exam exam = examService.getById(questionDto.getExamId());

        Question question = new Question();
        question.setDescription(questionDto.getDescription());
        question.setExam(exam);
        question.setTypeQuestion(questionDto.getTypeQuestion());

        Question questionResult = questionRepository.save(question);

        questionSaveContext.executeSave(questionResult, questionDto);

        return questionResult;
    }
}
