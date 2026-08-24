package com.ferreiracurso.admin.model;

import com.ferreiracurso.admin.dto.OptionsResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ExamQuestionOption {

    private Long id;

    private String description;

    private List<OptionsResponse> options;
}
