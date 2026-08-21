package com.ferreiracurso.admin.dto;

import com.ferreiracurso.admin.model.enums.TypeQuestion;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class QuestionDto {

    private Long id;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Type Question is required")
    private TypeQuestion typeQuestion;

    private List<DescriptionOptionDto> descriptionOption;

    private Long examId;
}
