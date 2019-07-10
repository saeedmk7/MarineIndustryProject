package com.marineindustryproj.repository;

import java.util.List;

import com.marineindustryproj.domain.FieldOfStudy;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FieldOfStudy entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FieldOfStudyRepository extends JpaRepository<FieldOfStudy, Long>, JpaSpecificationExecutor<FieldOfStudy> {
    String ALL_FIELDOFSTUDY_CACHE = "allFieldOfStudy";

    @Cacheable(cacheNames = ALL_FIELDOFSTUDY_CACHE)
    List<FieldOfStudy> findAll();
}
