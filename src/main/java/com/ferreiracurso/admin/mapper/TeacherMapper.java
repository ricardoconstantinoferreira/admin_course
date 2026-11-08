package com.ferreiracurso.admin.mapper;

import com.ferreiracurso.admin.dto.TeacherDto;
import com.ferreiracurso.admin.model.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    Teacher toEntity(TeacherDto teacherDto);

    TeacherDto toDto(Teacher teacher);
}
