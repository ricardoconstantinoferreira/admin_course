package com.ferreiracurso.admin.repository;

import com.ferreiracurso.admin.model.QuestionSelectionBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionSelectionBoxRepository extends JpaRepository<QuestionSelectionBox, Long> {

    List<QuestionSelectionBox> findByQuestionId(Long questionId);
}
