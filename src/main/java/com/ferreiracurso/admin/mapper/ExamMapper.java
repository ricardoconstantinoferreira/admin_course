package com.ferreiracurso.admin.mapper;

import com.ferreiracurso.admin.dto.ExamDto;
import com.ferreiracurso.admin.model.Exam;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExamMapper {

    ExamDto toDto(Exam exam);
}
