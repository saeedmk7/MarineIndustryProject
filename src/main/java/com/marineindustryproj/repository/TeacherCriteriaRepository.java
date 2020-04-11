package com.marineindustryproj.repository;

import com.marineindustryproj.domain.TeacherCriteria;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TeacherCriteria entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TeacherCriteriaRepository extends JpaRepository<TeacherCriteria, Long>, JpaSpecificationExecutor<TeacherCriteria> {

}
