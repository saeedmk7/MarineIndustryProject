package com.marineindustryproj.repository;

import com.marineindustryproj.domain.MahiatCourse;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MahiatCourse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MahiatCourseRepository extends JpaRepository<MahiatCourse, Long>, JpaSpecificationExecutor<MahiatCourse> {

}
