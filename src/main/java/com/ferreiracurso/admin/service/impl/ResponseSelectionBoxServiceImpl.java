package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.model.ResponseSelectionBox;
import com.ferreiracurso.admin.repository.ResponseSelectionBoxRepository;
import com.ferreiracurso.admin.service.ResponseSelectionBoxService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResponseSelectionBoxServiceImpl implements ResponseSelectionBoxService {

    private ResponseSelectionBoxRepository responseSelectionBoxRepository;

    @Override
    public ResponseSelectionBox save(ResponseSelectionBox responseSelectionBox) {
        return responseSelectionBoxRepository.save(responseSelectionBox);
    }
}
