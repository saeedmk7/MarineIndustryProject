package com.marineindustryproj.repository;

import com.marineindustryproj.domain.TeachingRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TeachingRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TeachingRecordRepository extends JpaRepository<TeachingRecord, Long>, JpaSpecificationExecutor<TeachingRecord> {

}
