package com.ferreiracurso.admin.service;

import com.ferreiracurso.admin.dto.AssociateTeacherSubjects;
import com.ferreiracurso.admin.dto.TeacherDto;
import com.ferreiracurso.admin.dto.CreateTeacherRequest;
import com.ferreiracurso.admin.model.Teacher;

import java.util.List;

public interface TeacherService {
    TeacherDto create(CreateTeacherRequest request);
    TeacherDto getById(Long id);
    List<TeacherDto> getAll();
    TeacherDto update(Long id, CreateTeacherRequest request);
    void delete(Long id);
    Teacher associate(AssociateTeacherSubjects associateTeacherSubjects);
}
