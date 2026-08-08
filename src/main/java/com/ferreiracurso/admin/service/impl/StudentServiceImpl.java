package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.dto.StudentCourseDto;
import com.ferreiracurso.admin.dto.StudentDto;
import com.ferreiracurso.admin.mapper.StudentMapper;
import com.ferreiracurso.admin.model.Course;
import com.ferreiracurso.admin.model.Student;
import com.ferreiracurso.admin.repository.CourseRepository;
import com.ferreiracurso.admin.repository.StudentRepository;
import com.ferreiracurso.admin.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final CourseRepository courseRepository;

    @Override
    public Student save(StudentDto studentDto) {

        Random random = new Random();
        String registration = String.valueOf(10000 + random.nextInt(90000));
        Student student = studentMapper.toEntity(studentDto);
        student.setRegistration(registration);

        List<Course> courseList = new ArrayList<>();
        for (Long course: studentDto.getCourses()) {
            Optional<Course> course1 = courseRepository.findById(course);

            if (!course1.isEmpty()) {
                courseList.add(course1.get());
            }
        }
        student.setCourses(courseList);
        return studentRepository.save(student);
    }

    @Override
    public Student getById(Long id) {
        return null;
    }

    @Override
    public List<Student> getAll() {
        return List.of();
    }

    @Override
    public String associateStudentToCourse(StudentCourseDto studentCourseDto) {
        List<Course> courseList = new ArrayList<>();

        Course course = courseRepository.findById(studentCourseDto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", studentCourseDto.getCourseId()));
        Student student = studentRepository.findById(studentCourseDto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentCourseDto.getStudentId()));

        for(Course course1: student.getCourses()) {
            courseList.add(course1);
        }

        courseList.add(course);

        List<Course> resultList = new ArrayList<>(new LinkedHashSet<>(courseList));
        student.setCourses(resultList);

        Student student1 = studentRepository.save(student);

        if (student1 == null) {
            throw new IllegalArgumentException("Estudante " + student.getName() + " não associado ao curso " + course.getDescription());
        } else {
            return "Estudante " + student.getName() + " associado ao curso " + course.getDescription();
        }
    }

    static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String resource, String field, Object value) {
            super(String.format("%s not found with %s : '%s'", resource, field, value));
        }
    }
}
