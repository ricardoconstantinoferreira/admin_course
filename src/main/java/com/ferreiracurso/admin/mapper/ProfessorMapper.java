package com.ferreiracurso.admin.mapper;

import com.ferreiracurso.admin.dto.ProfessorDto;
import com.ferreiracurso.admin.dto.SubjectDto;
import com.ferreiracurso.admin.model.Professor;
import com.ferreiracurso.admin.model.Subject;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProfessorMapper {

    public ProfessorDto toDto(Professor professor) {
        ProfessorDto dto = new ProfessorDto();
        dto.setId(professor.getId());
        dto.setName(professor.getName());
        dto.setSpecialization(professor.getSpecialization());
        dto.setSalary(professor.getSalary());
        Set<SubjectDto> subjects = professor.getSubjects().stream()
                .map(s -> new SubjectDto(s.getId(), s.getDescription()))
                .collect(Collectors.toSet());
        dto.setSubjects(subjects);
        return dto;
    }

    public Professor toEntity(Professor professor, com.ferreiracurso.admin.dto.CreateProfessorRequest req) {
        professor.setName(req.getName());
        professor.setSpecialization(req.getSpecialization());
        professor.setSalary(req.getSalary());
        return professor;
    }
}
