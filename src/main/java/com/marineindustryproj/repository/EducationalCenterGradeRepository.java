package com.marineindustryproj.repository;

import com.marineindustryproj.domain.EducationalCenterGrade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the EducationalCenterGrade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EducationalCenterGradeRepository extends JpaRepository<EducationalCenterGrade, Long>, JpaSpecificationExecutor<EducationalCenterGrade> {

    @Query(value = "select distinct educational_center_grade from EducationalCenterGrade educational_center_grade left join fetch educational_center_grade.evaluatorOpinions left join fetch educational_center_grade.documents",
        countQuery = "select count(distinct educational_center_grade) from EducationalCenterGrade educational_center_grade")
    Page<EducationalCenterGrade> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct educational_center_grade from EducationalCenterGrade educational_center_grade left join fetch educational_center_grade.evaluatorOpinions left join fetch educational_center_grade.documents")
    List<EducationalCenterGrade> findAllWithEagerRelationships();

    @Query("select educational_center_grade from EducationalCenterGrade educational_center_grade left join fetch educational_center_grade.evaluatorOpinions left join fetch educational_center_grade.documents where educational_center_grade.id =:id")
    Optional<EducationalCenterGrade> findOneWithEagerRelationships(@Param("id") Long id);

}
