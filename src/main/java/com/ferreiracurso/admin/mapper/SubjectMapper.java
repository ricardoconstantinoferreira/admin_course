package com.ferreiracurso.admin.mapper;

import com.ferreiracurso.admin.dto.SubjectDto;
import com.ferreiracurso.admin.model.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    Subject toEntity(SubjectDto subjectDto);
}
