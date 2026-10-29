package com.ferreiracurso.admin.controller;

import com.ferreiracurso.admin.dto.CorrectorRequest;
import com.ferreiracurso.admin.model.Corrector;
import com.ferreiracurso.admin.service.CorrectorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@AllArgsConstructor
@RestController
@RequestMapping("/api/corrector")
public class CorrectorController {

    private final CorrectorService correctorService;

    @PostMapping
    public ResponseEntity<Corrector> correctorExam(@RequestBody CorrectorRequest correctorRequests) {

        Long pointOptions = correctorService.getPointsOptions(correctorRequests.studentId(), correctorRequests.examId());
        Long pointOptionsBox = correctorService.getPointsOptionsBox(correctorRequests.studentId(), correctorRequests.examId());
        double pointsDescriptions = correctorService.getPointsDescriptions(correctorRequests);

        BigDecimal points = BigDecimal.valueOf(pointOptions + pointOptionsBox + pointsDescriptions);

        return ResponseEntity.status(HttpStatus.OK).body(
                correctorService.save(correctorRequests.examId(),
                    correctorRequests.studentId(),
                    points
                )
        );
    }
}
