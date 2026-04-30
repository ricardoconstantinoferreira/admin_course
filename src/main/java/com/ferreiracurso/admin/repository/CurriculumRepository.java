package com.ferreiracurso.admin.repository;

import com.ferreiracurso.admin.model.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Long> {
    // Check if a curriculum exists for given course id (efficient DB query)
    boolean existsByCourse_Id(Long courseId);

    // Find the curriculum for a given course id
    Optional<Curriculum> findByCourse_Id(Long courseId);
}
