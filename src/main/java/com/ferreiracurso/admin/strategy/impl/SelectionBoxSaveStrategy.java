package com.ferreiracurso.admin.strategy.impl;

import com.ferreiracurso.admin.dto.DescriptionOptionDto;
import com.ferreiracurso.admin.dto.QuestionDto;
import com.ferreiracurso.admin.model.Question;
import com.ferreiracurso.admin.model.QuestionSelectionBox;
import com.ferreiracurso.admin.model.enums.TypeQuestion;
import com.ferreiracurso.admin.service.QuestionSelectionBoxService;
import com.ferreiracurso.admin.strategy.QuestionStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class SelectionBoxSaveStrategy implements QuestionStrategy {

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
    public void delete(Question question) {
        List<QuestionSelectionBox> questionSelectionBoxs = questionSelectionBoxService.getByQuestionId(question.getId());
        for (QuestionSelectionBox questionSelectionBox: questionSelectionBoxs) {
            questionSelectionBoxService.delete(questionSelectionBox);
        }
    }

    @Override
    public TypeQuestion getType() {
        return TypeQuestion.SELECTION_BOX;
    }
}
