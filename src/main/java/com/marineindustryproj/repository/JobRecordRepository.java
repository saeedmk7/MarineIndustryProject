package com.marineindustryproj.repository;

import com.marineindustryproj.domain.JobRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the JobRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobRecordRepository extends JpaRepository<JobRecord, Long>, JpaSpecificationExecutor<JobRecord> {

}
