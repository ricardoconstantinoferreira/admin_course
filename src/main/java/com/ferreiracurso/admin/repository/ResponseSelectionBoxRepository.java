package com.ferreiracurso.admin.repository;

import com.ferreiracurso.admin.model.ResponseSelectionBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseSelectionBoxRepository extends JpaRepository<ResponseSelectionBox, Long> {
}
