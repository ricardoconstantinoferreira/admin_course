package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.model.QuestionSelectionBox;
import com.ferreiracurso.admin.repository.QuestionSelectionBoxRepository;
import com.ferreiracurso.admin.service.QuestionSelectionBoxService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class QuestionSelectionBoxServiceImpl implements QuestionSelectionBoxService {

    private final QuestionSelectionBoxRepository selectionBoxRepository;

    @Override
    public QuestionSelectionBox save(QuestionSelectionBox questionSelectionBox) {
        return selectionBoxRepository.save(questionSelectionBox);
    }

    @Override
    public List<QuestionSelectionBox> getByQuestionId(Long questionId) {
        return selectionBoxRepository.findByQuestionId(questionId);
    }

    @Override
    public void delete(QuestionSelectionBox questionSelectionBox) {
        selectionBoxRepository.delete(questionSelectionBox);
    }

}
