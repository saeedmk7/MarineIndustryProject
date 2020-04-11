package com.marineindustryproj.repository;

import com.marineindustryproj.domain.EducationalCenterCriteria;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EducationalCenterCriteria entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EducationalCenterCriteriaRepository extends JpaRepository<EducationalCenterCriteria, Long>, JpaSpecificationExecutor<EducationalCenterCriteria> {

}
