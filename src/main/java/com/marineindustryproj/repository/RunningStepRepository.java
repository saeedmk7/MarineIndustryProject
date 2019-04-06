package com.marineindustryproj.repository;

import com.marineindustryproj.domain.RunningStep;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RunningStep entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RunningStepRepository extends JpaRepository<RunningStep, Long>, JpaSpecificationExecutor<RunningStep> {

}
