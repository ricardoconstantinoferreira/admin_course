package com.ferreiracurso.admin.service;

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
}
