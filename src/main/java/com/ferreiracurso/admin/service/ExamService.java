package com.ferreiracurso.admin.service;

import com.ferreiracurso.admin.dto.ExamDto;
import com.ferreiracurso.admin.model.Exam;

public interface ExamService {

    Exam save(ExamDto examDto);

    Exam getById(Long id);
}
