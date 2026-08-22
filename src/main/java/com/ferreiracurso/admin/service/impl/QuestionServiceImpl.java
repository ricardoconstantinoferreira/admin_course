package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.dto.QuestionDto;
import com.ferreiracurso.admin.model.*;
import com.ferreiracurso.admin.repository.QuestionRepository;
import com.ferreiracurso.admin.service.ExamService;
import com.ferreiracurso.admin.service.QuestionService;
import com.ferreiracurso.admin.strategy.QuestionContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final ExamService examService;
    private final QuestionContext questionContext;
    private static final int POINTS_LIMIT = 10;

    @Override
    public Question save(QuestionDto questionDto) {

        Long counts = questionRepository.countPoints(questionDto.getExamId());
        Long result = (counts == null) ? 0 : counts;
        result += questionDto.getPoints();
        if (result > POINTS_LIMIT) {
            throw new RuntimeException("Pontuação passou o limite.");
        }

        Exam exam = examService.getById(questionDto.getExamId());

        Question question = new Question();
        question.setDescription(questionDto.getDescription());
        question.setExam(exam);
        question.setTypeQuestion(questionDto.getTypeQuestion());
        question.setPoints(questionDto.getPoints());

        Question questionResult = questionRepository.save(question);
        questionContext.executeSave(questionResult, questionDto);

        return questionResult;
    }

    @Override
    public Question getById(Long id) {
        return questionRepository.getReferenceById(id);
    }

    @Override
    public void delete(Long id) {
        Question question = getById(id);
        questionContext.executeDelete(question);
        questionRepository.deleteById(question.getId());
    }

    @Override
    public Question update(Long id, QuestionDto questionDto) {
        delete(id);
        return save(questionDto);
    }
}
