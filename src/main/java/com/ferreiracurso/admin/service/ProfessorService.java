package com.ferreiracurso.admin.service;

import com.ferreiracurso.admin.dto.ProfessorDto;
import com.ferreiracurso.admin.dto.CreateProfessorRequest;

import java.util.List;

public interface ProfessorService {
    ProfessorDto create(CreateProfessorRequest request);
    ProfessorDto getById(Long id);
    List<ProfessorDto> getAll();
    ProfessorDto update(Long id, CreateProfessorRequest request);
    void delete(Long id);
}
