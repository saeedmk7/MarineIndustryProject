package com.marineindustryproj.repository;

import com.marineindustryproj.domain.EvaluationMethod;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EvaluationMethod entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EvaluationMethodRepository extends JpaRepository<EvaluationMethod, Long>, JpaSpecificationExecutor<EvaluationMethod> {

}
