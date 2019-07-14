package com.marineindustryproj.repository;

import com.marineindustryproj.domain.CourseType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the CourseType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CourseTypeRepository extends JpaRepository<CourseType, Long>, JpaSpecificationExecutor<CourseType> {
    String ALL_COURSETYPE_CACHE = "allCourseType";

    @Cacheable(cacheNames = ALL_COURSETYPE_CACHE)
    List<CourseType> findAll();
}
