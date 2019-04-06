package com.marineindustryproj.repository;

import com.marineindustryproj.domain.Job;
import com.marineindustryproj.service.dto.customs.JobMinDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Job entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {

    String ALL_JOB_CACHE = "allJob";

    @Query(value = "select distinct job from Job job left join fetch job.documents",
        countQuery = "select count(distinct job) from Job job")
    Page<Job> findAllWithEagerRelationships(Pageable pageable);

    @Cacheable(cacheNames = ALL_JOB_CACHE)
    @Query(value = "select new com.marineindustryproj.service.dto.customs.JobMinDTO(job.id, job.title, job.jobKey, job.jobCode, job.first3JobCode) from Job job")
    List<JobMinDTO> findAllFromCache();

    @Query(value = "select distinct job from Job job left join fetch job.documents")
    List<Job> findAllWithEagerRelationships();

    @Query("select job from Job job left join fetch job.documents where job.id =:id")
    Optional<Job> findOneWithEagerRelationships(@Param("id") Long id);



}
