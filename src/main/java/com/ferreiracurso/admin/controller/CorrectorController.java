package com.ferreiracurso.admin.controller;

import com.ferreiracurso.admin.dto.CorrectorRequest;
import com.ferreiracurso.admin.service.CorrectorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/corrector")
public class CorrectorController {

    private final CorrectorService correctorService;

    @PostMapping
    public ResponseEntity<String> correctorExam(@RequestBody CorrectorRequest correctorRequests) {

        Long pointOptions = correctorService.getPointsOptions(correctorRequests.studentId(), correctorRequests.examId());
        Long pointOptionsBox = correctorService.getPointsOptionsBox(correctorRequests.studentId(), correctorRequests.examId());
        double pointsDescriptions = correctorService.getPointsDescriptions(correctorRequests);

        return null;
    }
}
