package com.marineindustryproj.repository;

import com.marineindustryproj.domain.PreJobNiazsanji;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the PreJobNiazsanji entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PreJobNiazsanjiRepository extends JpaRepository<PreJobNiazsanji, Long>, JpaSpecificationExecutor<PreJobNiazsanji> {

    @Query(value = "select distinct pre_job_niazsanji from PreJobNiazsanji pre_job_niazsanji left join fetch pre_job_niazsanji.documents left join fetch pre_job_niazsanji.people",
        countQuery = "select count(distinct pre_job_niazsanji) from PreJobNiazsanji pre_job_niazsanji")
    Page<PreJobNiazsanji> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct pre_job_niazsanji from PreJobNiazsanji pre_job_niazsanji left join fetch pre_job_niazsanji.documents left join fetch pre_job_niazsanji.people")
    List<PreJobNiazsanji> findAllWithEagerRelationships();

    @Query("select pre_job_niazsanji from PreJobNiazsanji pre_job_niazsanji left join fetch pre_job_niazsanji.documents left join fetch pre_job_niazsanji.people where pre_job_niazsanji.id =:id")
    Optional<PreJobNiazsanji> findOneWithEagerRelationships(@Param("id") Long id);

}
