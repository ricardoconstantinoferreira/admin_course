package com.ferreiracurso.admin.repository;

import com.ferreiracurso.admin.dto.ResponseCorrect;
import com.ferreiracurso.admin.model.ResponseMultipleChoise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseMultipleChoiseRepository extends JpaRepository<ResponseMultipleChoise, Long> {

    @Query(value = "select rmc.option_id as response, qmc.id as correct_question from response_multiple_choise rmc \n" +
            "inner join question_multiple_choise qmc on qmc.question_id = rmc.question_id \n" +
            "where rmc.question_id = :questionId and qmc.is_correct = 1", nativeQuery = true)
    ResponseCorrect getResponseCorrect(@Param("questionId") Long questionId);
}
