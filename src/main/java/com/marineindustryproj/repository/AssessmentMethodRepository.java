package com.marineindustryproj.repository;

import com.marineindustryproj.domain.AssessmentMethod;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AssessmentMethod entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssessmentMethodRepository extends JpaRepository<AssessmentMethod, Long>, JpaSpecificationExecutor<AssessmentMethod> {

}
