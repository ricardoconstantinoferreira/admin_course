package com.ferreiracurso.admin.controller;

import com.ferreiracurso.admin.dto.CreateCourseRequest;
import com.ferreiracurso.admin.model.Subject;
import com.ferreiracurso.admin.service.ImporterService;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/importer")
public class ImporterController {

    private final ImporterService importerService;

    @PostMapping
    public ResponseEntity<String> importer(@RequestParam("file") MultipartFile file) {

        try (InputStream is = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(is);
            int tabsTotal = workbook.getNumberOfSheets();

            for (int i = 0; i < tabsTotal; i ++) {
                Sheet tabsCurrent = workbook.getSheetAt(i);
                String coursesDescription = tabsCurrent.getSheetName();
                BigDecimal price = null;
                Integer totalTime = null;
                List<Long> subjectsIds = new ArrayList<>();

                CreateCourseRequest createCourseRequest = new CreateCourseRequest();
                createCourseRequest.setDescription(coursesDescription);

                for (Row line: tabsCurrent) {

                    if (line.getRowNum() == 0) {
                        price = BigDecimal.valueOf(line.getCell(1).getNumericCellValue());
                    }

                    if (line.getRowNum() == 1) {
                        totalTime = (int) line.getCell(1).getNumericCellValue();
                    }

                    if (line.getRowNum() >= 3) {
                        String descriptionSubject = line.getCell(0).getStringCellValue();
                        Subject subjectResult = importerService.importerSubject(descriptionSubject);
                        subjectsIds.add(subjectResult.getId());
                    }
                }

                createCourseRequest.setPrice(price);
                createCourseRequest.setTotalTime(totalTime);
                createCourseRequest.setSubjectIds(subjectsIds);

                importerService.importerCourse(createCourseRequest);
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                    "Cursos e Matérias importados com sucesso."
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
