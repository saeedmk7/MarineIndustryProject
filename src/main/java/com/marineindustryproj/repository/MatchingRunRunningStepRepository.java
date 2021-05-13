package com.marineindustryproj.repository;

import com.marineindustryproj.domain.MatchingRunRunningStep;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MatchingRunRunningStep entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MatchingRunRunningStepRepository extends JpaRepository<MatchingRunRunningStep, Long>, JpaSpecificationExecutor<MatchingRunRunningStep> {

}
