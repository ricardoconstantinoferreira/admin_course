package com.ferreiracurso.admin.mapper;

import com.ferreiracurso.admin.dto.StudentDto;
import com.ferreiracurso.admin.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "courses", ignore = true)
    Student toEntity(StudentDto studentDto);
}
