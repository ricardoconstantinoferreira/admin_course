package com.ferreiracurso.admin.repository;

import com.ferreiracurso.admin.model.Course;
import com.ferreiracurso.admin.model.StudentRegistrationEmailResponse;
import com.ferreiracurso.admin.model.StudentRegistrationResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(value = "select s.name, s.registration \n" +
            "from courses c \n" +
            "inner join course_subject cs on cs.course_id = c.id \n" +
            "inner join student_courses sc on sc.course_id = c.id \n" +
            "inner join student s on s.id = sc.student_id \n" +
            "inner join subjects sub on sub.id = cs.subject_id \n" +
            "inner join exam e on e.subject_id = sub.id \n" +
            "left join corrector co on co.student_id = s.id \n" +
            "where e.id = :examId and co.student_id is null and notification_send_teacher = 0", nativeQuery = true)
    List<StudentRegistrationResponse> getStudentExamNoDone(@Param("examId") Long examId);

    @Query(value = "select s.name, s.email \n" +
            "from courses c \n" +
            "inner join course_subject cs on cs.course_id = c.id \n" +
            "inner join student_courses sc on sc.course_id = c.id \n" +
            "inner join student s on s.id = sc.student_id \n" +
            "inner join subjects sub on sub.id = cs.subject_id \n" +
            "inner join exam e on e.subject_id = sub.id \n" +
            "left join corrector co on co.student_id = s.id \n" +
            "where e.id = :examId and co.student_id is null and e.notification_send_student = 0", nativeQuery = true)
    List<StudentRegistrationEmailResponse> getStudentEmail(@Param("examId") Long examId);
}
