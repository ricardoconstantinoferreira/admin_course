package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.dto.CorrectorRequest;
import com.ferreiracurso.admin.dto.QuestionAnswerResponse;
import com.ferreiracurso.admin.dto.QuestionItemRequest;
import com.ferreiracurso.admin.dto.ResponseCorrect;
import com.ferreiracurso.admin.model.enums.TypeQuestion;
import com.ferreiracurso.admin.repository.CorrectorRepository;
import com.ferreiracurso.admin.service.CorrectorService;
import com.ferreiracurso.admin.service.QuestionService;
import com.ferreiracurso.admin.service.ResponseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CorrectorServiceImpl implements CorrectorService {

    private final CorrectorRepository correctorRepository;
    private final QuestionService questionService;
    private final ResponseService responseService;

    @Override
    public Long getPointsOptions(Long studentId, Long examId) {
        Long points = 0L;
        List<QuestionAnswerResponse> questions = questionService.getQuestionOptions(studentId, examId,
                TypeQuestion.MULTIPLE_CHOISE.ordinal());

        if (questions.isEmpty()) {
            return null;
        }

        for (QuestionAnswerResponse questionAnswerResponse: questions) {
            Long questionId = questionAnswerResponse.getId();
            ResponseCorrect correct = responseService.getResponseOptionsChoise(questionId);

            if (correct.getResponse().equals(correct.getCorrectQuestion())) {
                points += questionAnswerResponse.getPoints();
            }
        }

        return points;
    }

    @Override
    public Long getPointsOptionsBox(Long studentId, Long examId) {
        Long points = 0L;
        List<QuestionAnswerResponse> questions = questionService.getQuestionOptions(studentId, examId,
                TypeQuestion.SELECTION_BOX.ordinal());

        if (questions.isEmpty()) {
            return null;
        }

        for (QuestionAnswerResponse questionAnswerResponse: questions) {
            Long questionId = questionAnswerResponse.getId();
            if (responseService.getResponseOptionsBox(questionId)) {
                points += questionAnswerResponse.getPoints();
            }
        }

        return points;
    }

    @Override
    public double getPointsDescriptions(CorrectorRequest correctorRequests) {
        double points = 0L;
        Long studentId = correctorRequests.studentId();
        Long examId = correctorRequests.examId();

        for (QuestionItemRequest questionItemRequest: correctorRequests.questions()) {
            QuestionAnswerResponse question = questionService.getQuestionDescriptionCorrector(
                    studentId, examId, questionItemRequest.questionId());

            double value = ((questionItemRequest.percentage() / 100) * question.getPoints());
            double result = Math.round(value * 2) / 2.0;
            responseService.saveByQuestion(question.getId(), result);
            points += result;
        }

        return points;
    }
}
