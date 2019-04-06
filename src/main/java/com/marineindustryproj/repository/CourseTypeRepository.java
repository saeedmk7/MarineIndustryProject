package com.marineindustryproj.repository;

import com.marineindustryproj.domain.CourseType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CourseType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CourseTypeRepository extends JpaRepository<CourseType, Long>, JpaSpecificationExecutor<CourseType> {

}
