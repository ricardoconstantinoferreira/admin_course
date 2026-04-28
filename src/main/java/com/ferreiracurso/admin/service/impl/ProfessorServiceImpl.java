package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.dto.CreateProfessorRequest;
import com.ferreiracurso.admin.dto.ProfessorDto;
import com.ferreiracurso.admin.model.Professor;
import com.ferreiracurso.admin.model.Subject;
import com.ferreiracurso.admin.repository.ProfessorRepository;
import com.ferreiracurso.admin.repository.SubjectRepository;
import com.ferreiracurso.admin.service.ProfessorService;
import com.ferreiracurso.admin.mapper.ProfessorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProfessorServiceImpl implements ProfessorService {

    private static final Logger logger = LoggerFactory.getLogger(ProfessorServiceImpl.class);

    private final ProfessorRepository professorRepository;
    private final SubjectRepository subjectRepository;
    private final ProfessorMapper professorMapper;

    public ProfessorServiceImpl(ProfessorRepository professorRepository, SubjectRepository subjectRepository, ProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.subjectRepository = subjectRepository;
        this.professorMapper = professorMapper;
    }

    @Override
    public ProfessorDto create(CreateProfessorRequest request) {
        Professor professor = new Professor();
        professor = professorMapper.toEntity(professor, request);

        if (request.getSubjectIds() != null && !request.getSubjectIds().isEmpty()) {
            Set<Subject> subjects = subjectRepository.findAllById(request.getSubjectIds()).stream().collect(Collectors.toSet());
            if (subjects.size() != request.getSubjectIds().size()) {
                throw new ResourceNotFoundException("Subject", "id", "one or more subject ids not found");
            }
            professor.setSubjects(subjects);
        }
        Professor saved = professorRepository.save(professor);
        logger.info("Created Professor id {}", saved.getId());
        return professorMapper.toDto(saved);
    }

    @Override
    public ProfessorDto getById(Long id) {
        Professor p = professorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Professor", "id", id));
        return professorMapper.toDto(p);
    }

    @Override
    public List<ProfessorDto> getAll() {
        return professorRepository.findAll().stream().map(professorMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ProfessorDto update(Long id, CreateProfessorRequest request) {
        Professor professor = professorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Professor", "id", id));
        professor = professorMapper.toEntity(professor, request);
        if (request.getSubjectIds() != null) {
            Set<Subject> subjects = subjectRepository.findAllById(request.getSubjectIds()).stream().collect(Collectors.toSet());
            if (subjects.size() != request.getSubjectIds().size()) {
                throw new ResourceNotFoundException("Subject", "id", "one or more subject ids not found");
            }
            professor.setSubjects(subjects);
        }
        Professor saved = professorRepository.save(professor);
        logger.info("Updated Professor id {}", saved.getId());
        return professorMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        if (!professorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Professor", "id", id);
        }
        professorRepository.deleteById(id);
        logger.info("Deleted Professor id {}", id);
    }

    static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String resource, String field, Object value) {
            super(String.format("%s not found with %s : '%s'", resource, field, value));
        }
    }
}
