package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.dto.CurriculumDto;
import com.ferreiracurso.admin.dto.CreateCurriculumRequest;
import com.ferreiracurso.admin.mapper.CurriculumMapper;
import com.ferreiracurso.admin.model.Course;
import com.ferreiracurso.admin.model.Curriculum;
import com.ferreiracurso.admin.repository.CourseRepository;
import com.ferreiracurso.admin.repository.CurriculumRepository;
import com.ferreiracurso.admin.service.CurriculumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CurriculumServiceImpl implements CurriculumService {

    private static final Logger logger = LoggerFactory.getLogger(CurriculumServiceImpl.class);

    private final CurriculumRepository curriculumRepository;
    private final CourseRepository courseRepository;
    private final CurriculumMapper mapper;

    public CurriculumServiceImpl(CurriculumRepository curriculumRepository, CourseRepository courseRepository, CurriculumMapper mapper) {
        this.curriculumRepository = curriculumRepository;
        this.courseRepository = courseRepository;
        this.mapper = mapper;
    }

    @Override
    public CurriculumDto create(CreateCurriculumRequest request) {
        Course course = courseRepository.findById(request.getCourseId()).orElseThrow(() -> new ResourceNotFoundException("Course", "id", request.getCourseId()));
        // Ensure no other curriculum exists for this course (one-to-one).
        if (curriculumRepository.existsByCourse_Id(course.getId())) {
            throw new IllegalStateException("Curriculum already exists for course id " + course.getId());
        }
        Curriculum curriculum = new Curriculum();
        curriculum = mapper.toEntity(curriculum, request, course);
        Curriculum saved = curriculumRepository.save(curriculum);
        logger.info("Created Curriculum id {} for course id {}", saved.getId(), course.getId());
        return mapper.toDto(saved);
    }

    @Override
    public CurriculumDto getById(Long id) {
        Curriculum c = curriculumRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Curriculum", "id", id));
        // mapper will include course name
        return mapper.toDto(c);
    }

    @Override
    public List<CurriculumDto> getAll() {
        return curriculumRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CurriculumDto update(Long id, CreateCurriculumRequest request) {
        Curriculum c = curriculumRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Curriculum", "id", id));
        Course course = courseRepository.findById(request.getCourseId()).orElseThrow(() -> new ResourceNotFoundException("Course", "id", request.getCourseId()));
        // If course is changing, ensure the new course doesn't already have a curriculum (one-to-one)
        if (c.getCourse() == null || !c.getCourse().getId().equals(course.getId())) {
            curriculumRepository.findByCourse_Id(course.getId()).ifPresent(existing -> {
                if (!existing.getId().equals(id)) {
                    throw new IllegalStateException("Another curriculum already exists for course id " + course.getId());
                }
            });
        }
        c = mapper.toEntity(c, request, course);
        Curriculum updated = curriculumRepository.save(c);
        logger.info("Updated Curriculum id {}", updated.getId());
        return mapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!curriculumRepository.existsById(id)) {
            throw new ResourceNotFoundException("Curriculum", "id", id);
        }
        curriculumRepository.deleteById(id);
        logger.info("Deleted Curriculum id {}", id);
    }

    static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String resource, String field, Object value) {
            super(String.format("%s not found with %s : '%s'", resource, field, value));
        }
    }
}
