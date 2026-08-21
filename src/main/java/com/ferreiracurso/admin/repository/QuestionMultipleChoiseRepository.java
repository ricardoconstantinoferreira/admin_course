package com.ferreiracurso.admin.repository;

import com.ferreiracurso.admin.model.QuestionMultipleChoise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionMultipleChoiseRepository extends JpaRepository<QuestionMultipleChoise, Long> {
}
