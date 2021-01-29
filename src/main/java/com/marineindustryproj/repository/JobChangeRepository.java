package com.marineindustryproj.repository;

import com.marineindustryproj.domain.JobChange;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the JobChange entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobChangeRepository extends JpaRepository<JobChange, Long>, JpaSpecificationExecutor<JobChange> {

}
