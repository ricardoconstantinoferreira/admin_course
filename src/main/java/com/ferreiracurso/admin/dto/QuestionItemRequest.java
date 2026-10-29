package com.ferreiracurso.admin.dto;

import jakarta.validation.constraints.NotNull;

public record QuestionItemRequest(
    @NotNull(message = "O ID da questão é obrigatório")
    Long questionId,

    @NotNull(message = "O percentual é obrigatório")
    Double percentage
) {}
