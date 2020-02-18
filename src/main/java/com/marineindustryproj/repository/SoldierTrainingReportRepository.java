package com.marineindustryproj.repository;

import com.marineindustryproj.domain.SoldierTrainingReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the SoldierTrainingReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SoldierTrainingReportRepository extends JpaRepository<SoldierTrainingReport, Long>, JpaSpecificationExecutor<SoldierTrainingReport> {

    @Query(value = "select distinct soldier_training_report from SoldierTrainingReport soldier_training_report left join fetch soldier_training_report.documents",
        countQuery = "select count(distinct soldier_training_report) from SoldierTrainingReport soldier_training_report")
    Page<SoldierTrainingReport> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct soldier_training_report from SoldierTrainingReport soldier_training_report left join fetch soldier_training_report.documents")
    List<SoldierTrainingReport> findAllWithEagerRelationships();

    @Query("select soldier_training_report from SoldierTrainingReport soldier_training_report left join fetch soldier_training_report.documents where soldier_training_report.id =:id")
    Optional<SoldierTrainingReport> findOneWithEagerRelationships(@Param("id") Long id);

}
