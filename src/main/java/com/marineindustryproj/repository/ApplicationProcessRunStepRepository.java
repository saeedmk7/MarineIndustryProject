package com.marineindustryproj.repository;

import com.marineindustryproj.domain.ApplicationProcessRunStep;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ApplicationProcessRunStep entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApplicationProcessRunStepRepository extends JpaRepository<ApplicationProcessRunStep, Long>, JpaSpecificationExecutor<ApplicationProcessRunStep> {

}
