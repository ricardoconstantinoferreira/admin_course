package com.ferreiracurso.admin.mapper;

import com.ferreiracurso.admin.dto.CurriculumDto;
import com.ferreiracurso.admin.model.Course;
import com.ferreiracurso.admin.model.Curriculum;
import org.springframework.stereotype.Component;

/**
 * Mapper to convert between Curriculum entity and CurriculumDto.
 * Keeps controllers/services decoupled from JPA entities.
 */
@Component
public class CurriculumMapper {

    public CurriculumDto toDto(Curriculum c) {
        CurriculumDto dto = new CurriculumDto();
        dto.setId(c.getId());
        dto.setDescription(c.getDescription());
        Course course = c.getCourse();
        if (course != null) {
            dto.setCourseId(course.getId());
            dto.setCourseDescription(course.getDescription());
        }
        return dto;
    }

    public Curriculum toEntity(Curriculum curriculum, com.ferreiracurso.admin.dto.CreateCurriculumRequest req, Course course) {
        curriculum.setDescription(req.getDescription());
        curriculum.setCourse(course);
        return curriculum;
    }
}
