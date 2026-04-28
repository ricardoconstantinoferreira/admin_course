package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.model.Subject;
import com.ferreiracurso.admin.repository.SubjectRepository;
import com.ferreiracurso.admin.service.SubjectService;
import com.ferreiracurso.admin.dto.CreateSubjectRequest;
import com.ferreiracurso.admin.dto.SubjectDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {
    private static final Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);

    private final SubjectRepository repository;

    public SubjectServiceImpl(SubjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public SubjectDto create(CreateSubjectRequest request) {
        Subject subject = new Subject(request.getDescription());
        Subject saved = repository.save(subject);
        logger.info("Created Subject with id {}", saved.getId());
        return toDto(saved);
    }

    @Override
    public SubjectDto getById(Long id) {
        Subject s = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subject", "id", id));
        return toDto(s);
    }

    @Override
    public List<SubjectDto> getAll() {
        return repository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public SubjectDto update(Long id, CreateSubjectRequest request) {
        Subject s = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subject", "id", id));
        s.setDescription(request.getDescription());
        Subject updated = repository.save(s);
        logger.info("Updated Subject id {}", updated.getId());
        return toDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Subject", "id", id);
        }
        repository.deleteById(id);
        logger.info("Deleted Subject id {}", id);
    }

    private SubjectDto toDto(Subject subject) {
        return new SubjectDto(subject.getId(), subject.getDescription());
    }

    // Local exception to avoid creating a separate file in this step
    static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String resource, String field, Object value) {
            super(String.format("%s not found with %s : '%s'", resource, field, value));
        }
    }
}
