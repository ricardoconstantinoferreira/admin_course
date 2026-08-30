package com.ferreiracurso.admin.mapper;

import com.ferreiracurso.admin.dto.ResponseDto;
import com.ferreiracurso.admin.model.Response;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponseMapper {

    Response toEntity(ResponseDto responseDto);
}
