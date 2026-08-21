package com.ferreiracurso.admin.strategy;

import com.ferreiracurso.admin.dto.QuestionDto;
import com.ferreiracurso.admin.model.Question;
import com.ferreiracurso.admin.model.enums.TypeQuestion;

public interface QuestionSaveStrategy {
    void save(Question question, QuestionDto dto);
    TypeQuestion getType();
}
