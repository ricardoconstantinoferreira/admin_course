package com.ferreiracurso.admin.repository;

import com.ferreiracurso.admin.model.QuestionMultipleChoise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionMultipleChoiseRepository extends JpaRepository<QuestionMultipleChoise, Long> {

    List<QuestionMultipleChoise> findByQuestionId(Long questionId);
}
