package com.marineindustryproj.repository;

import com.marineindustryproj.domain.MonitorLearningProcessLevel;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MonitorLearningProcessLevel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MonitorLearningProcessLevelRepository extends JpaRepository<MonitorLearningProcessLevel, Long>, JpaSpecificationExecutor<MonitorLearningProcessLevel> {

}
