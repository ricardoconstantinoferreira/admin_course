package com.ferreiracurso.admin.repository;

import com.ferreiracurso.admin.model.Corrector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrectorRepository extends JpaRepository<Corrector, Long> {
}
