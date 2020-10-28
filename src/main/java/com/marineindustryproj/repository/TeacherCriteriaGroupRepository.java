package com.marineindustryproj.repository;

import com.marineindustryproj.domain.TeacherCriteriaGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TeacherCriteriaGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TeacherCriteriaGroupRepository extends JpaRepository<TeacherCriteriaGroup, Long>, JpaSpecificationExecutor<TeacherCriteriaGroup> {

}
