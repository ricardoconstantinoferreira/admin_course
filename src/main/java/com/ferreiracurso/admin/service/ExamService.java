package com.ferreiracurso.admin.service;

import com.ferreiracurso.admin.dto.ExamDto;
import com.ferreiracurso.admin.model.Exam;

import java.time.LocalDate;

public interface ExamService {

    Exam save(ExamDto examDto);

    Exam getById(Long id);

    void deleteById(Long id);

    Exam updateDeadline(Long id, LocalDate deadline);
}
