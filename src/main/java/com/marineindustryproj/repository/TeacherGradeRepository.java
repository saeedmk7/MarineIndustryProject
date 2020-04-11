package com.marineindustryproj.repository;

import com.marineindustryproj.domain.TeacherGrade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the TeacherGrade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TeacherGradeRepository extends JpaRepository<TeacherGrade, Long>, JpaSpecificationExecutor<TeacherGrade> {

    @Query(value = "select distinct teacher_grade from TeacherGrade teacher_grade left join fetch teacher_grade.documents",
        countQuery = "select count(distinct teacher_grade) from TeacherGrade teacher_grade")
    Page<TeacherGrade> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct teacher_grade from TeacherGrade teacher_grade left join fetch teacher_grade.documents")
    List<TeacherGrade> findAllWithEagerRelationships();

    @Query("select teacher_grade from TeacherGrade teacher_grade left join fetch teacher_grade.documents where teacher_grade.id =:id")
    Optional<TeacherGrade> findOneWithEagerRelationships(@Param("id") Long id);

}
