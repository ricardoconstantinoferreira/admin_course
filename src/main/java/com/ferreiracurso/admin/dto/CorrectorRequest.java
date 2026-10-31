package com.ferreiracurso.admin.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CorrectorRequest (
    @NotNull(message = "O ID do estudante é obrigatório")
    Long studentId,

    @NotNull(message = "O ID do exame é obrigatório")
    Long examId,

    @NotEmpty(message = "A lista de questões não pode estar vazia")
    List<QuestionItemRequest> questions
){
}
