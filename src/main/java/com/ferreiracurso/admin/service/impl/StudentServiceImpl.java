package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.dto.StudentDto;
import com.ferreiracurso.admin.mapper.StudentMapper;
import com.ferreiracurso.admin.model.Course;
import com.ferreiracurso.admin.model.Student;
import com.ferreiracurso.admin.repository.CourseRepository;
import com.ferreiracurso.admin.repository.StudentRepository;
import com.ferreiracurso.admin.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
}
