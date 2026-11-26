package com.ferreiracurso.admin.service;

import com.ferreiracurso.admin.dto.AssociateTeacherSubjects;
import com.ferreiracurso.admin.dto.TeacherDto;
import com.ferreiracurso.admin.model.Teacher;

import java.util.List;

public interface TeacherService {
    TeacherDto create(TeacherDto request);
    TeacherDto getById(Long id);
    List<TeacherDto> getAll();
    TeacherDto update(Long id, TeacherDto request);
    void delete(Long id);
    Teacher associate(AssociateTeacherSubjects associateTeacherSubjects);
    Teacher exitTeacher(Long id);
}
