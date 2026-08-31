package com.ferreiracurso.admin.mapper;

import com.ferreiracurso.admin.dto.ResponseMultipleChoiseDto;
import com.ferreiracurso.admin.model.ResponseMultipleChoise;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponseMultipleChoiseMapper {

    ResponseMultipleChoise toEntity(ResponseMultipleChoiseDto responseMultipleChoiseDto);
}
