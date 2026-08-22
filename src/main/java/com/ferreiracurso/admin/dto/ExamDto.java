package com.ferreiracurso.admin.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ExamDto {

    private Long id;
    private Long teacherId;
    private Long subjectId;
    private LocalDate deadline;
}
