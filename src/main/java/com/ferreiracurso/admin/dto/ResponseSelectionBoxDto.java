package com.ferreiracurso.admin.dto;

import com.ferreiracurso.admin.model.enums.TypeQuestion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ResponseSelectionBoxDto {
    private Long questionId;
    private Long studentId;
    private TypeQuestion typeQuestion;
    private List<Long> optionsId;
}
