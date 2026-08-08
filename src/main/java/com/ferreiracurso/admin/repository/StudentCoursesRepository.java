package com.ferreiracurso.admin.repository;

import com.ferreiracurso.admin.model.StudentCourses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCoursesRepository extends JpaRepository<StudentCourses, Long> {

    @Query(value = "select count(*) as hasValue from student_courses " +
            "where course_id = :courseId and student_id = :studentId", nativeQuery = true)
    Long hasRegistersStudentToCourse(@Param("studentId") Long studentId, @Param("courseId") Long courseId);

    @Query(value = "select id, finish, locked, course_id, student_id from student_courses " +
            "where course_id = :courseId and student_id = :studentId", nativeQuery = true)
    StudentCourses getStudentCouses(@Param("studentId") Long studentId, @Param("courseId") Long courseId);
}
