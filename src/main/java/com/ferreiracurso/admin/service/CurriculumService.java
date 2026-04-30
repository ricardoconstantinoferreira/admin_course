package com.ferreiracurso.admin.service;

import com.ferreiracurso.admin.dto.CurriculumDto;
import com.ferreiracurso.admin.dto.CreateCurriculumRequest;

import java.util.List;

public interface CurriculumService {
    CurriculumDto create(CreateCurriculumRequest request);
    CurriculumDto getById(Long id);
    List<CurriculumDto> getAll();
    CurriculumDto update(Long id, CreateCurriculumRequest request);
    void delete(Long id);
}
