package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.dto.FinishDto;
import com.ferreiracurso.admin.dto.LockedDto;
import com.ferreiracurso.admin.dto.StudentCourseDto;
import com.ferreiracurso.admin.dto.StudentDto;
import com.ferreiracurso.admin.exception.EmailAlreadyExistsException;
import com.ferreiracurso.admin.mapper.StudentMapper;
import com.ferreiracurso.admin.model.Course;
import com.ferreiracurso.admin.model.Student;
import com.ferreiracurso.admin.model.StudentCourses;
import com.ferreiracurso.admin.repository.CourseRepository;
import com.ferreiracurso.admin.repository.StudentCoursesRepository;
import com.ferreiracurso.admin.repository.StudentRepository;
import com.ferreiracurso.admin.security.JwtService;
import com.ferreiracurso.admin.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final CourseRepository courseRepository;
    private final StudentCoursesRepository studentCoursesRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public Student save(StudentDto studentDto) {

        Optional<Student> studentOptional = studentRepository.findByEmail(studentDto.getEmail());

        if (!studentOptional.isEmpty()) {
            throw new EmailAlreadyExistsException("Email já existe.");
        }

        Random random = new Random();
        String registration = String.valueOf(10000 + random.nextInt(90000));
        Student student = studentMapper.toEntity(studentDto);
        student.setRegistration(registration);

        student.setPassword(passwordEncoder.encode(student.getPassword()));

        return studentRepository.save(student);
    }

    @Override
    public Student getById(Long id) {
        return studentRepository.getReferenceById(id);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public String associateStudentToCourse(StudentCourseDto studentCourseDto) {
        Course course = courseRepository.findById(studentCourseDto.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", studentCourseDto.getCourseId()));
        Student student = studentRepository.findById(studentCourseDto.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentCourseDto.getStudentId()));

        Long hasRegister = studentCoursesRepository.hasRegistersStudentToCourse(student.getId(), course.getId());

        if (hasRegister > 0) {
            throw new IllegalArgumentException("Associação desse aluno com esse curso já existe.");
        }

        StudentCourses studentCourses = new StudentCourses();
        studentCourses.setCourse(course);
        studentCourses.setStudent(student);
        studentCourses.setFinish(false);
        studentCourses.setLocked(false);

        StudentCourses student1 = studentCoursesRepository.save(studentCourses);

        if (student1 == null) {
            throw new IllegalArgumentException("Estudante " + student.getName() + " não associado ao curso " + course.getDescription());
        } else {
            return "Estudante " + student.getName() + " associado ao curso " + course.getDescription();
        }
    }

    @Override
    public String changeLockedStudentCourse(LockedDto lockedDto) {
        StudentCourses studentCourses = studentCoursesRepository.getStudentCouses(lockedDto.getStudentId(),
                lockedDto.getCourseId());
        studentCourses.setLocked(lockedDto.isLocked());

        studentCoursesRepository.save(studentCourses);

        if (lockedDto.isLocked()) {
            return "Curso trancado com sucesso";
        } else {
            return "Curso destrancado com sucesso";
        }
    }

    @Override
    public String changeFinishStudentCourse(FinishDto finishDto) {
        StudentCourses studentCourses = studentCoursesRepository.getStudentCouses(finishDto.getStudentId(),
                finishDto.getCourseId());
        studentCourses.setFinish(finishDto.isFinish());

        studentCoursesRepository.save(studentCourses);
        return "Status de finalização do curso feito com sucesso.";
    }

    @Override
    public String authenticateStudentToken(String registration, String password) {
        Student student = studentRepository.findByRegistration(registration)
                .orElseThrow(() -> new BadCredentialsException("Invalid registration or password"));

        if (!passwordEncoder.matches(password, student.getPassword())) {
            log.warn("Bad credentials for {}", registration);
            throw new BadCredentialsException("Invalid registration or password");
        }

        String token = jwtService.generateToken(student.getRegistration(), student.getId());
        log.info("Issued token for registration {} (id={})", student.getRegistration(), student.getId());
        return token;
    }

    static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String resource, String field, Object value) {
            super(String.format("%s not found with %s : '%s'", resource, field, value));
        }
    }
}
