package com.marineindustryproj.repository;

import com.marineindustryproj.domain.MediaAwarenessReport;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MediaAwarenessReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MediaAwarenessReportRepository extends JpaRepository<MediaAwarenessReport, Long>, JpaSpecificationExecutor<MediaAwarenessReport> {

}
