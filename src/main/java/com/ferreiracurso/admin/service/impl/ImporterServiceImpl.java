package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.dto.CourseDto;
import com.ferreiracurso.admin.dto.CreateCourseRequest;
import com.ferreiracurso.admin.dto.CreateSubjectRequest;
import com.ferreiracurso.admin.dto.SubjectDto;
import com.ferreiracurso.admin.mapper.SubjectMapper;
import com.ferreiracurso.admin.model.Subject;
import com.ferreiracurso.admin.service.CourseService;
import com.ferreiracurso.admin.service.ImporterService;
import com.ferreiracurso.admin.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ImporterServiceImpl implements ImporterService {

    private final SubjectService subjectService;
    private final CourseService courseService;
    private final SubjectMapper subjectMapper;

    @Override
    public Subject importerSubject(String description) {
        CreateSubjectRequest createSubjectRequest = new CreateSubjectRequest();
        createSubjectRequest.setDescription(description);
        SubjectDto subjectDto = subjectService.create(createSubjectRequest);
        return subjectMapper.toEntity(subjectDto);
    }

    @Override
    public CourseDto importerCourse(CreateCourseRequest createCourseRequest) {
        return courseService.create(createCourseRequest);
    }
}
