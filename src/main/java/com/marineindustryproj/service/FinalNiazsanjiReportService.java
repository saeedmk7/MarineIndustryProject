package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.FinalNiazsanjiReportDTO;

import com.marineindustryproj.service.dto.customs.*;
import com.marineindustryproj.service.dto.customs.EffectivenessPhaseModels.FinalEffectivenessPhaseReportModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing FinalNiazsanjiReport.
 */
public interface FinalNiazsanjiReportService {

    List<ChartResultDetail> getChartResultDetail(Integer niazsanjiYear, Long rootOrgId);

    /**
     * Save a finalNiazsanjiReport.
     *
     * @param finalNiazsanjiReportDTO the entity to save
     * @return the persisted entity
     */
    FinalNiazsanjiReportDTO save(FinalNiazsanjiReportDTO finalNiazsanjiReportDTO);

    FinalNiazsanjiReportDTO setEffectivenessPhaseLevel(FinalNiazsanjiReportDTO finalNiazsanjiReportDTO) throws Exception;
    /**
     * Save a finalNiazsanjiReport.
     *
     * @param finalNiazsanjiReportDTO the entity to save
     * @return the persisted entity
     */
    FinalNiazsanjiReportDTO saveAndComplete(FinalNiazsanjiReportDTO finalNiazsanjiReportDTO) throws Exception;
    /**
     * Get all the finalNiazsanjiReports.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FinalNiazsanjiReportDTO> findAll(Pageable pageable);

    /**
     * Get all the FinalNiazsanjiReport with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<FinalNiazsanjiReportDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" finalNiazsanjiReport.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FinalNiazsanjiReportDTO> findOne(Long id);

    List<ChartResult> getChartResult(Integer niazsanjiYear);

    HomePageNiazsanjiReport getHomePageNiazsanjiReport(Long personId);

    List<PlanningAndRunMonthReport> getPlanningAndRunMonthReport(Integer niazsanjiYear, Integer reportType, Long rootOrgId);

    HomePagePersonHourChart getHomePagePersonHourChart(Long personId);

    HomePageReport getHomePageReport(Integer niazsanjiYear, HomePageReportType homePageReportType);

    List<HomePagePersonEducationalModule> getHomePagePersonEducationalModules(Long personId);
    /**
     * Delete the "id" finalNiazsanjiReport.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    List<FinalEffectivenessPhaseReportModel> getFinalEffectivenessPhaseReport(Integer reportYear, List<Long> organizationChartIds);
}
