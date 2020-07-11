package com.marineindustryproj.repository;

import com.marineindustryproj.domain.ReportGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the ReportGenerator entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportGeneratorRepository extends JpaRepository<ReportGenerator, Long>, JpaSpecificationExecutor<ReportGenerator> {

    @Query(value = "select distinct report_generator from ReportGenerator report_generator left join fetch report_generator.organizationCharts",
        countQuery = "select count(distinct report_generator) from ReportGenerator report_generator")
    Page<ReportGenerator> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct report_generator from ReportGenerator report_generator left join fetch report_generator.organizationCharts")
    List<ReportGenerator> findAllWithEagerRelationships();

    @Query("select report_generator from ReportGenerator report_generator left join fetch report_generator.organizationCharts where report_generator.id =:id")
    Optional<ReportGenerator> findOneWithEagerRelationships(@Param("id") Long id);

}
