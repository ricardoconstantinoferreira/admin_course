package com.ferreiracurso.admin.service;

import com.ferreiracurso.admin.dto.CreateSubjectRequest;
import com.ferreiracurso.admin.dto.SubjectDto;

import java.util.List;

/**
 * Service interface for Subject CRUD operations.
 */
public interface SubjectService {
    SubjectDto create(CreateSubjectRequest request);
    SubjectDto getById(Long id);
    List<SubjectDto> getAll();
    SubjectDto update(Long id, CreateSubjectRequest request);
    void delete(Long id);
}
