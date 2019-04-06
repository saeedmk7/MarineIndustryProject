package com.marineindustryproj.repository;

import com.marineindustryproj.domain.RunRunningStep;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RunRunningStep entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RunRunningStepRepository extends JpaRepository<RunRunningStep, Long>, JpaSpecificationExecutor<RunRunningStep> {

}
