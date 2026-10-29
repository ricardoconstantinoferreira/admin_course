package com.ferreiracurso.admin.repository;

import com.ferreiracurso.admin.dto.QuestionAnswerResponse;
import com.ferreiracurso.admin.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    int TYPE_DESCRIPTION = 0;

    @Query(value = "select sum(points) as countPoints from question " +
            "where exam_id = :examId", nativeQuery = true)
    Long countPoints(@Param("examId") Long examId);

    @Query(value = "select q.id, q.description as description, q.type_question as typeQuestion from question q \n" +
            "inner join exam e on e.id = q.exam_id \n" +
            "inner join subjects s on s.id = e.subject_id \n" +
            "inner join course_subject cs on cs.subject_id = e.subject_id \n" +
            "inner join student_courses sc on sc.course_id = cs.course_id \n" +
            "where sc.student_id = :studentId and cs.subject_id = :subjectId \n" +
            "order by typeQuestion", nativeQuery = true)
    List<QuestionAnswerResponse> getQuestionDescription(@Param("studentId") Long studentId, @Param("subjectId") Long subjectId);

    @Query(value = "select q.id, q.description as description, q.type_question as typeQuestion, q.points as points from question q \n" +
            "inner join exam e on e.id = q.exam_id \n" +
            "inner join course_subject cs on cs.subject_id = e.subject_id \n" +
            "inner join student_courses sc on sc.course_id = cs.course_id \n" +
            "where sc.student_id = :studentId and e.id = :examId and q.type_question = :typeQuestion", nativeQuery = true)
    List<QuestionAnswerResponse> getQuestionOptions(
            @Param("studentId") Long studentId,
            @Param("examId") Long examId,
            @Param("typeQuestion") Integer typeQuestion
    );

    @Query(value = "select q.id, q.description as description, q.type_question as typeQuestion, q.points as points from question q \n" +
            "inner join exam e on e.id = q.exam_id \n" +
            "inner join course_subject cs on cs.subject_id = e.subject_id \n" +
            "inner join student_courses sc on sc.course_id = cs.course_id \n" +
            "where sc.student_id = :studentId and e.id = :examId and \n"  +
            "q.id = :questionId and q.type_question = " + TYPE_DESCRIPTION, nativeQuery = true)
    QuestionAnswerResponse getQuestionDescriptionCorrector(
            @Param("studentId") Long studentId,
            @Param("examId") Long examId,
            @Param("questionId") Long questionId
    );
}
