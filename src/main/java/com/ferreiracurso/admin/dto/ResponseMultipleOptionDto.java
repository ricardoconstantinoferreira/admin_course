package com.ferreiracurso.admin.dto;

import com.ferreiracurso.admin.model.enums.TypeQuestion;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseMultipleOptionDto {
    private Long questionId;
    private Long studentId;
    private TypeQuestion typeQuestion;
    private Long optionId;
}
