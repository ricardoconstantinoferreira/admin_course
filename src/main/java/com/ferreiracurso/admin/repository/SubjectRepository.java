package com.ferreiracurso.admin.repository;

import com.ferreiracurso.admin.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Subject repository using Spring Data JPA.
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
