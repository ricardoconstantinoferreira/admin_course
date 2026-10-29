package com.ferreiracurso.admin.service;

import com.ferreiracurso.admin.dto.QuestionAnswerResponse;
import com.ferreiracurso.admin.dto.QuestionDto;
import com.ferreiracurso.admin.model.ExamQuestionOption;
import com.ferreiracurso.admin.model.Question;

import java.util.List;

public interface QuestionService {

    Question save(QuestionDto questionDto);
    Question getById(Long id);
    void delete(Long id);
    Question update(Long id, QuestionDto questionDto);
    List<ExamQuestionOption> getQuestionDescription(Long studentId, Long subjectId);
    List<QuestionAnswerResponse> getQuestionOptions(Long studentId, Long examId, Integer typeQuestion);
    QuestionAnswerResponse getQuestionDescriptionCorrector(Long studentId, Long examId, Long questionId);
}
