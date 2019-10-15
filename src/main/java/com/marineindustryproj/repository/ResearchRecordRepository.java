package com.marineindustryproj.repository;

import com.marineindustryproj.domain.ResearchRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ResearchRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResearchRecordRepository extends JpaRepository<ResearchRecord, Long>, JpaSpecificationExecutor<ResearchRecord> {

}
