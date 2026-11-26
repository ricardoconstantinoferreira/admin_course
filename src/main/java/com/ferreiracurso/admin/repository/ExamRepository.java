package com.ferreiracurso.admin.repository;

import com.ferreiracurso.admin.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    @Query(value = "select e.* from exam e \n" +
            "inner join teacher t on t.id = e.teacher_id \n" +
            "where CURDATE() > e.deadline and notification_send_teacher = 0", nativeQuery = true)
    List<Exam> findExpiredExamToTeacher();

    @Query(value = "select * from exam \n" +
                   "where CURDATE() > deadline and notification_send_student = 0", nativeQuery = true)
    List<Exam> findExpiredExamToStudent();
}
