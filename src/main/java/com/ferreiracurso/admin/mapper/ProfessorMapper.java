package com.ferreiracurso.admin.mapper;

import com.ferreiracurso.admin.dto.CreateTeacherRequest;
import com.ferreiracurso.admin.dto.TeacherDto;
import com.ferreiracurso.admin.dto.SubjectDto;
import com.ferreiracurso.admin.model.Teacher;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProfessorMapper {

    public TeacherDto toDto(Teacher teacher) {
        TeacherDto dto = new TeacherDto();
        dto.setId(teacher.getId());
        dto.setName(teacher.getName());
        dto.setSpecialization(teacher.getSpecialization());
        dto.setSalary(teacher.getSalary());
        Set<SubjectDto> subjects = teacher.getSubjects().stream()
                .map(s -> new SubjectDto(s.getId(), s.getDescription()))
                .collect(Collectors.toSet());
        dto.setSubjects(subjects);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        dto.setAdmissionDate(teacher.getAdmissionDate().format(formatter));
        return dto;
    }

    public Teacher toEntity(Teacher teacher, CreateTeacherRequest req) {
        teacher.setName(req.getName());
        teacher.setSpecialization(req.getSpecialization());
        teacher.setSalary(req.getSalary());
        return teacher;
    }
}
