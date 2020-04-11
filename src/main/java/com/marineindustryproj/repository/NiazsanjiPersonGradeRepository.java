package com.marineindustryproj.repository;

import com.marineindustryproj.domain.NiazsanjiPersonGrade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the NiazsanjiPersonGrade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NiazsanjiPersonGradeRepository extends JpaRepository<NiazsanjiPersonGrade, Long>, JpaSpecificationExecutor<NiazsanjiPersonGrade> {

    @Query(value = "select distinct niazsanji_person_grade from NiazsanjiPersonGrade niazsanji_person_grade left join fetch niazsanji_person_grade.documents",
        countQuery = "select count(distinct niazsanji_person_grade) from NiazsanjiPersonGrade niazsanji_person_grade")
    Page<NiazsanjiPersonGrade> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct niazsanji_person_grade from NiazsanjiPersonGrade niazsanji_person_grade left join fetch niazsanji_person_grade.documents")
    List<NiazsanjiPersonGrade> findAllWithEagerRelationships();

    @Query("select niazsanji_person_grade from NiazsanjiPersonGrade niazsanji_person_grade left join fetch niazsanji_person_grade.documents where niazsanji_person_grade.id =:id")
    Optional<NiazsanjiPersonGrade> findOneWithEagerRelationships(@Param("id") Long id);

}
