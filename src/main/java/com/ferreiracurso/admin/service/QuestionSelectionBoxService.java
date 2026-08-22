package com.ferreiracurso.admin.service;

import com.ferreiracurso.admin.model.QuestionSelectionBox;

import java.util.List;

public interface QuestionSelectionBoxService {

    QuestionSelectionBox save(QuestionSelectionBox questionSelectionBox);

    List<QuestionSelectionBox> getByQuestionId(Long questionId);

    void delete(QuestionSelectionBox questionSelectionBox);
}
