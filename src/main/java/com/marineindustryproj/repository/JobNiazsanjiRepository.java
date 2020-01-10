package com.marineindustryproj.repository;

import com.marineindustryproj.domain.JobNiazsanji;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the JobNiazsanji entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobNiazsanjiRepository extends JpaRepository<JobNiazsanji, Long>, JpaSpecificationExecutor<JobNiazsanji> {

    @Query(value = "select distinct job_niazsanji from JobNiazsanji job_niazsanji left join fetch job_niazsanji.documents left join fetch job_niazsanji.restrictions",
        countQuery = "select count(distinct job_niazsanji) from JobNiazsanji job_niazsanji")
    Page<JobNiazsanji> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct job_niazsanji from JobNiazsanji job_niazsanji left join fetch job_niazsanji.documents left join fetch job_niazsanji.restrictions")
    List<JobNiazsanji> findAllWithEagerRelationships();

    @Query("select job_niazsanji from JobNiazsanji job_niazsanji left join fetch job_niazsanji.documents left join fetch job_niazsanji.restrictions where job_niazsanji.id =:id")
    Optional<JobNiazsanji> findOneWithEagerRelationships(@Param("id") Long id);

}
