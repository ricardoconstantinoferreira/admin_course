package com.ferreiracurso.admin.repository;

import com.ferreiracurso.admin.dto.ResponseCorrect;
import com.ferreiracurso.admin.dto.ResponseCorrectBox;
import com.ferreiracurso.admin.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {

    @Query(value = "select rmc.option_id as response, qmc.id as correct_question from response_multiple_choise rmc \n" +
            "inner join question_multiple_choise qmc on qmc.question_id = rmc.question_id \n" +
            "where rmc.question_id = :questionId and qmc.is_correct = 1", nativeQuery = true)
    ResponseCorrect getResponseOptionsChoise(@Param("questionId") Long questionId);

    @Query(value = "select rsb.option_ids as response, qsb.id as correct_question from response_selection_box rsb \n" +
            "inner join question_selection_box qsb on qsb.question_id = rsb.question_id \n" +
            "where rsb.question_id = :questionId and qsb.is_correct = 1", nativeQuery = true)
    List<ResponseCorrectBox> getResponseOptionsBox(@Param("questionId") Long questionId);

    Response findByQuestionId(Long questionId);
}
