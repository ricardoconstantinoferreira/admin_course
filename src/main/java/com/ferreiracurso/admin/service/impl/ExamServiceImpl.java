package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.dto.ExamDto;
import com.ferreiracurso.admin.dto.SubjectDto;
import com.ferreiracurso.admin.dto.TeacherDto;
import com.ferreiracurso.admin.mapper.SubjectMapper;
import com.ferreiracurso.admin.mapper.TeacherMapper;
import com.ferreiracurso.admin.model.Exam;
import com.ferreiracurso.admin.model.Question;
import com.ferreiracurso.admin.model.Subject;
import com.ferreiracurso.admin.model.Teacher;
import com.ferreiracurso.admin.repository.ExamRepository;
import com.ferreiracurso.admin.repository.QuestionRepository;
import com.ferreiracurso.admin.service.ExamService;
import com.ferreiracurso.admin.service.SubjectService;
import com.ferreiracurso.admin.service.TeacherService;
import com.ferreiracurso.admin.strategy.QuestionContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final TeacherService teacherService;
    private final SubjectService subjectService;
    private final TeacherMapper teacherMapper;
    private final SubjectMapper subjectMapper;
    private final QuestionRepository questionRepository;
    private final QuestionContext questionContext;

    @Override
    public Exam save(ExamDto examDto) {
        SubjectDto subjectDto = subjectService.getById(examDto.getSubjectId());
        TeacherDto teacherDto = teacherService.getById(examDto.getTeacherId());

        Subject subject = subjectMapper.toEntity(subjectDto);
        Teacher teacher = teacherMapper.toEntity(teacherDto);

        Exam exam = new Exam();
        exam.setSubject(subject);
        exam.setTeacher(teacher);
        exam.setDeadline(examDto.getDeadline());

        return examRepository.save(exam);
    }

    @Override
    public Exam getById(Long id) {
        return examRepository.getReferenceById(id);
    }

    @Override
    public void deleteById(Long id) {
        try {
            Exam exam = getById(id);
            List<Question> questions = exam.getQuestions();

            if (!questions.isEmpty()) {
                for (Question question : questions) {
                    questionContext.executeDelete(question);
                    questionRepository.deleteById(question.getId());
                }
            }

            examRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Exam updateDeadline(Long id, LocalDate deadline) {
        Exam exam = getById(id);
        exam.setDeadline(deadline);

        return examRepository.save(exam);
    }
}
