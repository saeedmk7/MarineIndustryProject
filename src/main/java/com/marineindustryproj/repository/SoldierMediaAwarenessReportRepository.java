package com.marineindustryproj.repository;

import com.marineindustryproj.domain.SoldierMediaAwarenessReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the SoldierMediaAwarenessReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SoldierMediaAwarenessReportRepository extends JpaRepository<SoldierMediaAwarenessReport, Long>, JpaSpecificationExecutor<SoldierMediaAwarenessReport> {

    @Query(value = "select distinct soldier_media_awareness_report from SoldierMediaAwarenessReport soldier_media_awareness_report left join fetch soldier_media_awareness_report.documents",
        countQuery = "select count(distinct soldier_media_awareness_report) from SoldierMediaAwarenessReport soldier_media_awareness_report")
    Page<SoldierMediaAwarenessReport> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct soldier_media_awareness_report from SoldierMediaAwarenessReport soldier_media_awareness_report left join fetch soldier_media_awareness_report.documents")
    List<SoldierMediaAwarenessReport> findAllWithEagerRelationships();

    @Query("select soldier_media_awareness_report from SoldierMediaAwarenessReport soldier_media_awareness_report left join fetch soldier_media_awareness_report.documents where soldier_media_awareness_report.id =:id")
    Optional<SoldierMediaAwarenessReport> findOneWithEagerRelationships(@Param("id") Long id);

}
