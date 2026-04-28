package com.ferreiracurso.admin.mapper;

import com.ferreiracurso.admin.dto.CourseDto;
import com.ferreiracurso.admin.dto.SubjectDto;
import com.ferreiracurso.admin.model.Course;
import com.ferreiracurso.admin.model.Subject;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    public CourseDto toDto(Course course) {
        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setDescription(course.getDescription());
        dto.setTotalTime(course.getTotalTime());
        dto.setPrice(course.getPrice());
        Set<SubjectDto> subjects = course.getSubjects().stream()
                .map(s -> new SubjectDto(s.getId(), s.getDescription()))
                .collect(Collectors.toSet());
        dto.setSubjects(subjects);
        return dto;
    }

    public Course toEntity(Course course, com.ferreiracurso.admin.dto.CreateCourseRequest req) {
        course.setDescription(req.getDescription());
        course.setTotalTime(req.getTotalTime());
        course.setPrice(req.getPrice());
        return course;
    }
}
