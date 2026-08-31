package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.model.ResponseMultipleChoise;
import com.ferreiracurso.admin.repository.ResponseMultipleChoiseRepository;
import com.ferreiracurso.admin.service.ResponseMultipleChoiseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResponseMultipleChoiseServiceImpl implements ResponseMultipleChoiseService {

    private final ResponseMultipleChoiseRepository responseMultipleChoiseRepository;

    @Override
    public ResponseMultipleChoise save(ResponseMultipleChoise responseMultipleChoise) {
        return responseMultipleChoiseRepository.save(responseMultipleChoise);
    }
}
