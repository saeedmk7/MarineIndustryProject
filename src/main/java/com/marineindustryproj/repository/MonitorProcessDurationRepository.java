package com.marineindustryproj.repository;

import com.marineindustryproj.domain.MonitorProcessDuration;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MonitorProcessDuration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MonitorProcessDurationRepository extends JpaRepository<MonitorProcessDuration, Long>, JpaSpecificationExecutor<MonitorProcessDuration> {

}
