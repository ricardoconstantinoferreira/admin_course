package com.ferreiracurso.admin.controller;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/importer")
public class ImporterController {

    @PostMapping
    public ResponseEntity<String> importer(@RequestParam("file") MultipartFile file) {
        List<String> tabs = new ArrayList<>();

        try (InputStream is = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(is);

            for (Sheet sheet: workbook) {
                String subject = sheet.getSheetName();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
