package com.ferreiracurso.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DescriptionOptionDto {
    private Long id;
    private String description;
    private Boolean isCorrect;
}
