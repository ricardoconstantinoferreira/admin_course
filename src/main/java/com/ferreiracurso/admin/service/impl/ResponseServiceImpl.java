package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.model.Response;
import com.ferreiracurso.admin.repository.ResponseRepository;
import com.ferreiracurso.admin.service.ResponseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ResponseServiceImpl implements ResponseService {

    private final ResponseRepository responseRepository;

    @Override
    public Response save(Response response) {
        return responseRepository.save(response);
    }
}
