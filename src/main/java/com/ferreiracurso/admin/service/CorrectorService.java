package com.ferreiracurso.admin.service;

import com.ferreiracurso.admin.dto.CorrectorRequest;

public interface CorrectorService {

    Long getPointsOptions(Long studentId, Long examId);
    Long getPointsOptionsBox(Long studentId, Long examId);
    double getPointsDescriptions(CorrectorRequest correctorRequests);
}
