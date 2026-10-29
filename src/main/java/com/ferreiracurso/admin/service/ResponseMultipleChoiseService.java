package com.ferreiracurso.admin.service;

import com.ferreiracurso.admin.dto.ResponseCorrect;
import com.ferreiracurso.admin.model.ResponseMultipleChoise;

public interface ResponseMultipleChoiseService {

    ResponseMultipleChoise save(ResponseMultipleChoise responseMultipleChoise);

    ResponseCorrect getResponseCorrectQuestion(Long questionId);
}
