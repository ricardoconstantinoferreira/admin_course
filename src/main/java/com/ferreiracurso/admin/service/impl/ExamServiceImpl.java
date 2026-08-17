package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.dto.ExamDto;
import com.ferreiracurso.admin.dto.SubjectDto;
import com.ferreiracurso.admin.dto.TeacherDto;
import com.ferreiracurso.admin.mapper.SubjectMapper;
import com.ferreiracurso.admin.mapper.TeacherMapper;
import com.ferreiracurso.admin.model.Exam;
import com.ferreiracurso.admin.model.Subject;
import com.ferreiracurso.admin.model.Teacher;
import com.ferreiracurso.admin.repository.ExamRepository;
import com.ferreiracurso.admin.service.ExamService;
import com.ferreiracurso.admin.service.SubjectService;
import com.ferreiracurso.admin.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final TeacherService teacherService;
    private final SubjectService subjectService;
    private final TeacherMapper teacherMapper;
    private final SubjectMapper subjectMapper;

    @Override
    public Exam save(ExamDto examDto) {
        SubjectDto subjectDto = subjectService.getById(examDto.getSubjectId());
        TeacherDto teacherDto = teacherService.getById(examDto.getTeacherId());

        Subject subject = subjectMapper.toEntity(subjectDto);
        Teacher teacher = teacherMapper.toEntity(teacherDto);

        Exam exam = new Exam();
        exam.setSubject(subject);
        exam.setTeacher(teacher);

        return examRepository.save(exam);
    }
}
