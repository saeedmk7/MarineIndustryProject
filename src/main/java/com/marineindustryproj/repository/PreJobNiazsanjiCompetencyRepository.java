package com.marineindustryproj.repository;

import com.marineindustryproj.domain.PreJobNiazsanjiCompetency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the PreJobNiazsanjiCompetency entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PreJobNiazsanjiCompetencyRepository extends JpaRepository<PreJobNiazsanjiCompetency, Long>, JpaSpecificationExecutor<PreJobNiazsanjiCompetency> {

    @Query(value = "select distinct pre_job_niazsanji_competency from PreJobNiazsanjiCompetency pre_job_niazsanji_competency left join fetch pre_job_niazsanji_competency.teachingApproaches",
        countQuery = "select count(distinct pre_job_niazsanji_competency) from PreJobNiazsanjiCompetency pre_job_niazsanji_competency")
    Page<PreJobNiazsanjiCompetency> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct pre_job_niazsanji_competency from PreJobNiazsanjiCompetency pre_job_niazsanji_competency left join fetch pre_job_niazsanji_competency.teachingApproaches")
    List<PreJobNiazsanjiCompetency> findAllWithEagerRelationships();

    @Query("select pre_job_niazsanji_competency from PreJobNiazsanjiCompetency pre_job_niazsanji_competency left join fetch pre_job_niazsanji_competency.teachingApproaches where pre_job_niazsanji_competency.id =:id")
    Optional<PreJobNiazsanjiCompetency> findOneWithEagerRelationships(@Param("id") Long id);

}
