package com.marineindustryproj.repository;

import com.marineindustryproj.domain.MatchingRunningStep;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MatchingRunningStep entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MatchingRunningStepRepository extends JpaRepository<MatchingRunningStep, Long>, JpaSpecificationExecutor<MatchingRunningStep> {

}
