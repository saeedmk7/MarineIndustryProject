package com.marineindustryproj.repository;

import com.marineindustryproj.domain.EvaluatorOpinion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EvaluatorOpinion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EvaluatorOpinionRepository extends JpaRepository<EvaluatorOpinion, Long>, JpaSpecificationExecutor<EvaluatorOpinion> {

}
