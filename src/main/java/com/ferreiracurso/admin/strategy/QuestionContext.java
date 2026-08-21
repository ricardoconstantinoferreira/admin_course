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
public class QuestionContext {
    private final Map<TypeQuestion, QuestionStrategy> strategies;

    @Autowired
    public QuestionContext(List<QuestionStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(QuestionStrategy::getType, strategy -> strategy));
    }

    public void executeSave(Question result, QuestionDto dto) {
        QuestionStrategy strategy = strategies.get(dto.getTypeQuestion());

        if (strategy != null) {
            strategy.save(result, dto);
        }
    }

    public void executeDelete(Question result) {
        QuestionStrategy strategy = strategies.get(result.getTypeQuestion());

        if (strategy != null) {
            strategy.delete(result);
        }
    }
}
