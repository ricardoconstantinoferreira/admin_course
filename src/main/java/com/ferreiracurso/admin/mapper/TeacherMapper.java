package com.ferreiracurso.admin.mapper;

import com.ferreiracurso.admin.dto.TeacherDto;
import com.ferreiracurso.admin.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    @Mapping(target = "admissionDate", source = "admissionDate", dateFormat = "dd/MM/yyyy HH:mm:ss")
    Teacher toEntity(TeacherDto teacherDto);
}
