package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.dto.ResponseCorrect;
import com.ferreiracurso.admin.dto.ResponseCorrectBox;
import com.ferreiracurso.admin.model.Response;
import com.ferreiracurso.admin.model.enums.TypeQuestion;
import com.ferreiracurso.admin.repository.ResponseRepository;
import com.ferreiracurso.admin.service.ResponseService;
import com.ferreiracurso.admin.util.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ResponseServiceImpl implements ResponseService {

    private final ResponseRepository responseRepository;

    @Override
    public Response save(Response response) {
        return responseRepository.save(response);
    }

    @Override
    public ResponseCorrect getResponseOptionsChoise(Long questionId) {
        return responseRepository.getResponseOptionsChoise(questionId);
    }

    @Override
    public boolean getResponseOptionsBox(Long questionId) {
        List<Long> responseValid = new ArrayList<>();
        List<ResponseCorrectBox> responseCorrects = responseRepository.getResponseOptionsBox(questionId);
        List<Long> responseList = Utils.converterStringtoList(responseCorrects.get(0).getResponse());

        for (ResponseCorrectBox correct: responseCorrects) {
            responseValid.add(correct.getCorrectQuestion());
        }
        return Utils.compareListElements(responseList, responseValid);
    }

    @Override
    public void saveByQuestion(Long questionId, double points) {
        Response response = responseRepository.findByQuestionId(questionId);
        response.setPoints(points);
        save(response);
    }
}
