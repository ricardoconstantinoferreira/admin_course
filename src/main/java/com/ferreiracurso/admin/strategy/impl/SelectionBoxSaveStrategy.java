package com.ferreiracurso.admin.strategy.impl;

import com.ferreiracurso.admin.dto.DescriptionOptionDto;
import com.ferreiracurso.admin.dto.QuestionDto;
import com.ferreiracurso.admin.model.Question;
import com.ferreiracurso.admin.model.QuestionSelectionBox;
import com.ferreiracurso.admin.model.enums.TypeQuestion;
import com.ferreiracurso.admin.service.QuestionSelectionBoxService;
import com.ferreiracurso.admin.strategy.QuestionSaveStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class SelectionBoxSaveStrategy implements QuestionSaveStrategy {

    private final QuestionSelectionBoxService questionSelectionBoxService;

    @Override
    public void save(Question question, QuestionDto dto) {
        List<DescriptionOptionDto> dtos = dto.getDescriptionOption();

        for (DescriptionOptionDto dt: dtos) {
            QuestionSelectionBox questionSelectionBox = new QuestionSelectionBox();
            questionSelectionBox.setDescription(dt.getDescription());
            questionSelectionBox.setQuestion(question);
            questionSelectionBox.setIsCorrect(dt.getIsCorrect());
            questionSelectionBoxService.save(questionSelectionBox);
        }
    }

    @Override
    public TypeQuestion getType() {
        return TypeQuestion.SELECTION_BOX;
    }
}
