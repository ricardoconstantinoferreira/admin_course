package com.ferreiracurso.admin.strategy.impl;

import com.ferreiracurso.admin.dto.DescriptionOptionDto;
import com.ferreiracurso.admin.dto.QuestionDto;
import com.ferreiracurso.admin.model.Question;
import com.ferreiracurso.admin.model.QuestionMultipleChoise;
import com.ferreiracurso.admin.model.enums.TypeQuestion;
import com.ferreiracurso.admin.service.QuestionMultipleChoiseService;
import com.ferreiracurso.admin.strategy.QuestionSaveStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class MultipleChoiseSaveStrategy implements QuestionSaveStrategy {

    private final QuestionMultipleChoiseService questionMultipleChoiseService;

    @Override
    public void save(Question question, QuestionDto dto) {
        List<DescriptionOptionDto> dtos = dto.getDescriptionOption();

        for (DescriptionOptionDto dt: dtos) {
            QuestionMultipleChoise questionMultipleChoise = new QuestionMultipleChoise();
            questionMultipleChoise.setDescription(dt.getDescription());
            questionMultipleChoise.setQuestion(question);
            questionMultipleChoise.setIsCorrect(dt.getIsCorrect());

            questionMultipleChoiseService.save(questionMultipleChoise);
        }
    }

    @Override
    public TypeQuestion getType() {
        return TypeQuestion.MULTIPLE_CHOISE;
    }
}
