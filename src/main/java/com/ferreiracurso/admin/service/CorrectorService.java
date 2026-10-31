package com.ferreiracurso.admin.service;

import com.ferreiracurso.admin.dto.CorrectorRequest;
import com.ferreiracurso.admin.model.Corrector;

import java.math.BigDecimal;

public interface CorrectorService {

    Long getPointsOptions(Long studentId, Long examId);
    Long getPointsOptionsBox(Long studentId, Long examId);
    double getPointsDescriptions(CorrectorRequest correctorRequests);
    Corrector save(Long examId, Long studentId, BigDecimal points);
}
