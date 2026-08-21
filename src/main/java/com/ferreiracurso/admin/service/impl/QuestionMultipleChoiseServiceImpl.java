package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.model.QuestionMultipleChoise;
import com.ferreiracurso.admin.repository.QuestionMultipleChoiseRepository;
import com.ferreiracurso.admin.service.QuestionMultipleChoiseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class QuestionMultipleChoiseServiceImpl implements QuestionMultipleChoiseService {

    private final QuestionMultipleChoiseRepository questionMultipleChoiseRepository;

    @Override
    public QuestionMultipleChoise save(QuestionMultipleChoise questionMultipleChoise) {
        return questionMultipleChoiseRepository.save(questionMultipleChoise);
    }

    @Override
    public List<QuestionMultipleChoise> getByQuestionId(Long questionId) {
        return questionMultipleChoiseRepository.findByQuestionId(questionId);
    }

    @Override
    public void delete(QuestionMultipleChoise questionMultipleChoise) {
        questionMultipleChoiseRepository.delete(questionMultipleChoise);
    }
}
