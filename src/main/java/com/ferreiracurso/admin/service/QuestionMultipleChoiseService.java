package com.ferreiracurso.admin.service;

import com.ferreiracurso.admin.model.QuestionMultipleChoise;

import java.util.List;

public interface QuestionMultipleChoiseService {

    QuestionMultipleChoise save(QuestionMultipleChoise questionMultipleChoise);

    List<QuestionMultipleChoise> getByQuestionId(Long questionId);

    void delete(QuestionMultipleChoise questionMultipleChoise);
}
