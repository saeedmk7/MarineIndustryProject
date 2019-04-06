package com.marineindustryproj.repository;

import com.marineindustryproj.domain.CourseLocation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CourseLocation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CourseLocationRepository extends JpaRepository<CourseLocation, Long>, JpaSpecificationExecutor<CourseLocation> {

}
