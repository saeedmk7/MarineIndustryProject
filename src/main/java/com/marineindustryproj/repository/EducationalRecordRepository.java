package com.marineindustryproj.repository;

import com.marineindustryproj.domain.EducationalRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EducationalRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EducationalRecordRepository extends JpaRepository<EducationalRecord, Long>, JpaSpecificationExecutor<EducationalRecord> {

}
