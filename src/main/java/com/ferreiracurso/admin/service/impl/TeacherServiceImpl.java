package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.dto.AssociateTeacherSubjects;
import com.ferreiracurso.admin.dto.CreateTeacherRequest;
import com.ferreiracurso.admin.dto.TeacherDto;
import com.ferreiracurso.admin.model.Teacher;
import com.ferreiracurso.admin.model.Subject;
import com.ferreiracurso.admin.repository.TeacherRepository;
import com.ferreiracurso.admin.repository.SubjectRepository;
import com.ferreiracurso.admin.service.TeacherService;
import com.ferreiracurso.admin.mapper.ProfessorMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private static final Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);

    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final ProfessorMapper professorMapper;

    @Override
    public TeacherDto create(CreateTeacherRequest request) {
        Teacher teacher = new Teacher();
        teacher = professorMapper.toEntity(teacher, request);

        if (request.getSubjectIds() != null && !request.getSubjectIds().isEmpty()) {
            Set<Subject> subjects = subjectRepository.findAllById(request.getSubjectIds()).stream().collect(Collectors.toSet());
            if (subjects.size() != request.getSubjectIds().size()) {
                throw new ResourceNotFoundException("Subject", "id", "one or more subject ids not found");
            }
            teacher.setSubjects(subjects);
        }
        teacher.setAdmissionDate(LocalDateTime.now());
        Teacher saved = teacherRepository.save(teacher);
        logger.info("Created Professor id {}", saved.getId());
        return professorMapper.toDto(saved);
    }

    @Override
    public TeacherDto getById(Long id) {
        Teacher p = teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Professor", "id", id));
        return professorMapper.toDto(p);
    }

    @Override
    public List<TeacherDto> getAll() {
        return teacherRepository.findAll().stream().map(professorMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TeacherDto update(Long id, CreateTeacherRequest request) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Professor", "id", id));
        teacher = professorMapper.toEntity(teacher, request);
        if (request.getSubjectIds() != null) {
            Set<Subject> subjects = subjectRepository.findAllById(request.getSubjectIds()).stream().collect(Collectors.toSet());
            if (subjects.size() != request.getSubjectIds().size()) {
                throw new ResourceNotFoundException("Subject", "id", "one or more subject ids not found");
            }
            teacher.setSubjects(subjects);
        }
        Teacher saved = teacherRepository.save(teacher);
        logger.info("Updated Professor id {}", saved.getId());
        return professorMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new ResourceNotFoundException("Professor", "id", id);
        }
        teacherRepository.deleteById(id);
        logger.info("Deleted Professor id {}", id);
    }

    @Override
    public Teacher associate(AssociateTeacherSubjects associateTeacherSubjects) {
        Set<Subject> setSubject = new HashSet<>();

        Teacher teacher = teacherRepository.findById(associateTeacherSubjects.teacherId())
                .orElseThrow(() -> new StudentServiceImpl.ResourceNotFoundException("Teacher", "id",
                        associateTeacherSubjects.teacherId()));

        Subject subject = subjectRepository.findById(associateTeacherSubjects.subjectId())
                .orElseThrow(() -> new StudentServiceImpl.ResourceNotFoundException("Subject", "id",
                        associateTeacherSubjects.subjectId()));

        for (Subject subject1: teacher.getSubjects()) {
            setSubject.add(subject1);
        }

        setSubject.add(subject);
        teacher.setSubjects(setSubject);

        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher exitTeacher(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new StudentServiceImpl.ResourceNotFoundException("Teacher", "id", id));

        teacher.setEndDate(LocalDateTime.now());
        return teacherRepository.save(teacher);
    }

    static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String resource, String field, Object value) {
            super(String.format("%s not found with %s : '%s'", resource, field, value));
        }
    }
}
