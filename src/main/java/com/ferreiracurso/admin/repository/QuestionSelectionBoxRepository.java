package com.ferreiracurso.admin.repository;

import com.ferreiracurso.admin.dto.OptionsResponse;
import com.ferreiracurso.admin.model.QuestionSelectionBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionSelectionBoxRepository extends JpaRepository<QuestionSelectionBox, Long> {

    List<QuestionSelectionBox> findByQuestionId(Long questionId);

    @Query(value = "select id, description as description, is_correct from question_selection_box \n" +
            "where question_id = :questionId", nativeQuery = true)
    List<OptionsResponse> getOptionsSelectionBox(@Param("questionId") Long questionId);

}
