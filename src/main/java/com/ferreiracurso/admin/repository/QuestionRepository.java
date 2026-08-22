package com.ferreiracurso.admin.repository;

import com.ferreiracurso.admin.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "select sum(points) as countPoints from question " +
            "where exam_id = :examId", nativeQuery = true)
    Long countPoints(@Param("examId") Long examId);
}
