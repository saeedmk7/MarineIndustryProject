package com.marineindustryproj.repository;

import com.marineindustryproj.domain.ApplicationProcessStep;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ApplicationProcessStep entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApplicationProcessStepRepository extends JpaRepository<ApplicationProcessStep, Long>, JpaSpecificationExecutor<ApplicationProcessStep> {

}
