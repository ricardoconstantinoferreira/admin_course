package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.dto.DescriptionOptionDto;
import com.ferreiracurso.admin.dto.QuestionDto;
import com.ferreiracurso.admin.model.Exam;
import com.ferreiracurso.admin.model.Question;
import com.ferreiracurso.admin.model.QuestionMultipleChoise;
import com.ferreiracurso.admin.model.TypeQuestion;
import com.ferreiracurso.admin.repository.QuestionRepository;
import com.ferreiracurso.admin.service.ExamService;
import com.ferreiracurso.admin.service.QuestionMultipleChoiseService;
import com.ferreiracurso.admin.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final ExamService examService;
    private final QuestionMultipleChoiseService questionMultipleChoiseService;

    @Override
    public Question save(QuestionDto questionDto) {
        Exam exam = examService.getById(questionDto.getExamId());

        Question question = new Question();
        question.setDescription(questionDto.getDescription());
        question.setExam(exam);
        question.setTypeQuestion(questionDto.getTypeQuestion());

        Question questionResult = questionRepository.save(question);

        if (questionDto.getTypeQuestion().equals(TypeQuestion.MULTIPLE_CHOISE)) {
            saveMultipleChoise(questionResult, questionDto);
        }

        return questionResult;
    }

    private void saveMultipleChoise(Question question, QuestionDto questionDto) {
        List<DescriptionOptionDto> dtos = questionDto.getDescriptionOption();

        for (DescriptionOptionDto dto: dtos) {
            QuestionMultipleChoise questionMultipleChoise = new QuestionMultipleChoise();
            questionMultipleChoise.setDescription(dto.getDescription());
            questionMultipleChoise.setQuestion(question);
            questionMultipleChoise.setIsCorrect(dto.getIsCorrect());

            questionMultipleChoiseService.save(questionMultipleChoise);
        }
    }

}
