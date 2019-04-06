package com.marineindustryproj.repository;

import com.marineindustryproj.domain.EducationalHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EducationalHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EducationalHistoryRepository extends JpaRepository<EducationalHistory, Long>, JpaSpecificationExecutor<EducationalHistory> {

}
