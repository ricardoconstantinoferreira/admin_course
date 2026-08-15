package com.ferreiracurso.admin.service;

import com.ferreiracurso.admin.dto.CourseDto;
import com.ferreiracurso.admin.dto.CreateCourseRequest;
import com.ferreiracurso.admin.model.Subject;

public interface ImporterService {

    Subject importerSubject(String description);

    CourseDto importerCourse(CreateCourseRequest createCourseRequest);
}
