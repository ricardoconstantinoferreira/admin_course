package com.ferreiracurso.admin.service;

import com.ferreiracurso.admin.dto.ResponseCorrect;
import com.ferreiracurso.admin.model.Response;

public interface ResponseService {

    Response save(Response response);
    ResponseCorrect getResponseOptionsChoise(Long questionId);
    boolean getResponseOptionsBox(Long questionId);
    void saveByQuestion(Long questionId, double points);
}
