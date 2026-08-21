package com.ferreiracurso.admin.strategy;

import com.ferreiracurso.admin.dto.QuestionDto;
import com.ferreiracurso.admin.model.Question;
import com.ferreiracurso.admin.model.enums.TypeQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class QuestionSaveContext {
    private final Map<TypeQuestion, QuestionSaveStrategy> strategies;

    @Autowired
    public QuestionSaveContext(List<QuestionSaveStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(QuestionSaveStrategy::getType, strategy -> strategy));
    }

    public void executeSave(Question result, QuestionDto dto) {
        QuestionSaveStrategy strategy = strategies.get(dto.getTypeQuestion());

        if (strategy != null) {
            strategy.save(result, dto);
        }
    }
}
