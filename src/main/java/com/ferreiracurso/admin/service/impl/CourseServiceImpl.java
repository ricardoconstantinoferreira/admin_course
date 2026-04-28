package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.dto.CourseDto;
import com.ferreiracurso.admin.dto.CreateCourseRequest;
import com.ferreiracurso.admin.model.Course;
import com.ferreiracurso.admin.model.Subject;
import com.ferreiracurso.admin.repository.CourseRepository;
import com.ferreiracurso.admin.repository.SubjectRepository;
import com.ferreiracurso.admin.service.CourseService;
import com.ferreiracurso.admin.mapper.CourseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;
    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseRepository courseRepository, SubjectRepository subjectRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.subjectRepository = subjectRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public CourseDto create(CreateCourseRequest request) {
        Course course = new Course();
        course = courseMapper.toEntity(course, request);

        if (request.getSubjectIds() != null && !request.getSubjectIds().isEmpty()) {
            Set<Subject> subjects = subjectRepository.findAllById(request.getSubjectIds()).stream().collect(Collectors.toSet());
            if (subjects.size() != request.getSubjectIds().size()) {
                throw new ResourceNotFoundException("Subject", "id", "one or more subject ids not found");
            }
            course.setSubjects(subjects);
        }
        Course saved = courseRepository.save(course);
        logger.info("Created Course id {}", saved.getId());
        return courseMapper.toDto(saved);
    }

    @Override
    public CourseDto getById(Long id) {
        Course c = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
        return courseMapper.toDto(c);
    }

    @Override
    public List<CourseDto> getAll() {
        return courseRepository.findAll().stream().map(courseMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CourseDto update(Long id, CreateCourseRequest request) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
        course = courseMapper.toEntity(course, request);
        if (request.getSubjectIds() != null) {
            Set<Subject> subjects = subjectRepository.findAllById(request.getSubjectIds()).stream().collect(Collectors.toSet());
            if (subjects.size() != request.getSubjectIds().size()) {
                throw new ResourceNotFoundException("Subject", "id", "one or more subject ids not found");
            }
            course.setSubjects(subjects);
        }
        Course saved = courseRepository.save(course);
        logger.info("Updated Course id {}", saved.getId());
        return courseMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course", "id", id);
        }
        courseRepository.deleteById(id);
        logger.info("Deleted Course id {}", id);
    }

    // Local exception similar to SubjectServiceImpl's inner exception
    static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String resource, String field, Object value) {
            super(String.format("%s not found with %s : '%s'", resource, field, value));
        }
    }
}
