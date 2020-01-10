package com.marineindustryproj.repository;

import com.marineindustryproj.domain.MediaAwarenessReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the MediaAwarenessReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MediaAwarenessReportRepository extends JpaRepository<MediaAwarenessReport, Long>, JpaSpecificationExecutor<MediaAwarenessReport> {

    @Query(value = "select distinct media_awareness_report from MediaAwarenessReport media_awareness_report left join fetch media_awareness_report.documents",
        countQuery = "select count(distinct media_awareness_report) from MediaAwarenessReport media_awareness_report")
    Page<MediaAwarenessReport> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct media_awareness_report from MediaAwarenessReport media_awareness_report left join fetch media_awareness_report.documents")
    List<MediaAwarenessReport> findAllWithEagerRelationships();

    @Query("select media_awareness_report from MediaAwarenessReport media_awareness_report left join fetch media_awareness_report.documents where media_awareness_report.id =:id")
    Optional<MediaAwarenessReport> findOneWithEagerRelationships(@Param("id") Long id);

}
