package com.ferreiracurso.admin.service;

import com.ferreiracurso.admin.dto.QuestionDto;
import com.ferreiracurso.admin.model.Question;

public interface QuestionService {

    Question save(QuestionDto questionDto);
}
