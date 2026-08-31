package com.ferreiracurso.admin.repository;

import com.ferreiracurso.admin.model.ResponseMultipleChoise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseMultipleChoiseRepository extends JpaRepository<ResponseMultipleChoise, Long> {
}
