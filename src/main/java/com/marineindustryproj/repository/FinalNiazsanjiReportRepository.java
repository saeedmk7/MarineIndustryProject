package com.marineindustryproj.repository;

import com.marineindustryproj.domain.FinalNiazsanjiReport;
import com.marineindustryproj.service.dto.customs.FinalNiazsanjiReportCustomDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the FinalNiazsanjiReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FinalNiazsanjiReportRepository extends JpaRepository<FinalNiazsanjiReport, Long>, JpaSpecificationExecutor<FinalNiazsanjiReport> {

    String ALL_FINALNIAZSANJIREPORT_CACHE = "allFinalNiazsanjiReport";

    @Query(value = "select distinct final_niazsanji_report from FinalNiazsanjiReport final_niazsanji_report left join fetch final_niazsanji_report.documents",
        countQuery = "select count(distinct final_niazsanji_report) from FinalNiazsanjiReport final_niazsanji_report")
    Page<FinalNiazsanjiReport> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct final_niazsanji_report from FinalNiazsanjiReport final_niazsanji_report left join fetch final_niazsanji_report.documents")
    List<FinalNiazsanjiReport> findAllWithEagerRelationships();

    @Query("select final_niazsanji_report from FinalNiazsanjiReport final_niazsanji_report left join fetch final_niazsanji_report.documents where final_niazsanji_report.id =:id")
    Optional<FinalNiazsanjiReport> findOneWithEagerRelationships(@Param("id") Long id);

    @Query("select new com.marineindustryproj.service.dto.customs.FinalNiazsanjiReportCustomDTO(finalNiazsanjiReport.id, finalNiazsanjiReport.priceCost, finalNiazsanjiReport.finalizeCost,finalNiazsanjiReport.educationalModule, finalNiazsanjiReport.status) from FinalNiazsanjiReport finalNiazsanjiReport where finalNiazsanjiReport.niazsanjiYear = :niazsanjiYear and finalNiazsanjiReport.organizationChart.id in :orgChartIds")
    List<FinalNiazsanjiReportCustomDTO> findAllFromCache(@Param("orgChartIds") List<Long> orgChartIds, @Param("niazsanjiYear") Integer niazsanjiYear);

    @Query("select final_niazsanji_report from FinalNiazsanjiReport final_niazsanji_report where final_niazsanji_report.niazsanjiYear =:niazsanjiYear")
    List<FinalNiazsanjiReport> findAllByNiazsanjiYear(@Param("niazsanjiYear") Integer niazsanjiYear);

}
