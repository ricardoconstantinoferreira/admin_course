package com.ferreiracurso.admin.mapper;

import com.ferreiracurso.admin.dto.ResponseSelectionBoxDto;
import com.ferreiracurso.admin.model.ResponseSelectionBox;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponseSelectionBoxMapper {

    ResponseSelectionBox toEntity(ResponseSelectionBoxDto responseSelectionBoxDto);
}
