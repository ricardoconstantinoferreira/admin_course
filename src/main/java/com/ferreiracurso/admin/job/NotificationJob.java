package com.ferreiracurso.admin.job;

import com.ferreiracurso.admin.dto.TeacherDto;
import com.ferreiracurso.admin.mapper.ExamMapper;
import com.ferreiracurso.admin.model.Exam;
import com.ferreiracurso.admin.model.StudentRegistrationEmailResponse;
import com.ferreiracurso.admin.model.StudentRegistrationResponse;
import com.ferreiracurso.admin.service.CourseService;
import com.ferreiracurso.admin.service.EmailService;
import com.ferreiracurso.admin.service.ExamService;
import com.ferreiracurso.admin.service.TeacherService;
import com.resend.core.exception.ResendException;
import lombok.AllArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class NotificationJob {

    private final ExamService examService;
    private final CourseService courseService;
    private final EmailService emailService;
    private final TeacherService teacherService;

    @Scheduled(cron = "0 0 8 * * *")
    public void sendMailToTeacher() {
        List<Exam> exams = examService.findExpiredExamToTeacher();

        if (!exams.isEmpty()) {
            for (Exam exam: exams) {
                List<StudentRegistrationResponse> studentsNoExam = courseService.getStudentExamNoDone(exam.getId());
                TeacherDto teacherDto = teacherService.getById(exam.getTeacher().getId());

                sendMessageToTeacher(studentsNoExam, exam.getTitle(), teacherDto);

                exam.setNotification_send_teacher(true);
                examService.addNotification(exam);
            }
        }
    }

    @Scheduled(fixedRate = 10000)
    public void sendMailToStudent() {
        List<Exam> exams = examService.findExpiredExamToStudent();

        if (!exams.isEmpty()) {
            for (Exam exam : exams) {
                List<StudentRegistrationEmailResponse> studentEmails = courseService.getStudentEmail(exam.getId());

                if (!studentEmails.isEmpty()) {

                    for (StudentRegistrationEmailResponse studentEmail: studentEmails) {
                        sendMessageToStudent(studentEmail.getName(), studentEmail.getEmail(), exam.getTitle());
                    }

                    exam.setNotification_send_student(true);
                    examService.addNotification(exam);
                }
            }
        }
    }

    private void sendMessageToStudent(String name, String email, String title) {
        try {
            final Context ctx = new Context(LocaleContextHolder.getLocale());
            ctx.setVariable("name", name);
            ctx.setVariable("title", title);

            emailService.notification(ctx, "exam-deadline-expired-student", email, "Prazo da prova vencido.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ResendException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMessageToTeacher(List<StudentRegistrationResponse> studentsNoExam, String title, TeacherDto teacherDto) {
        try {
            List<String> student = new ArrayList<>();
            for(StudentRegistrationResponse studentRegistrationResponse: studentsNoExam) {
                student.add(studentRegistrationResponse.getName() + " - " + studentRegistrationResponse.getRegistration());
            }

            final Context ctx = new Context(LocaleContextHolder.getLocale());
            ctx.setVariable("teacher", teacherDto.getName());
            ctx.setVariable("title", title);
            ctx.setVariable("students", student);

            emailService.notification(ctx, "exam-deadline-expired", teacherDto.getEmail(), "Prazo da prova vencido.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ResendException e) {
            throw new RuntimeException(e);
        }
    }
}
