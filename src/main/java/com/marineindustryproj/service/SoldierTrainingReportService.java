package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.SoldierTrainingReportDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SoldierTrainingReport.
 */
public interface SoldierTrainingReportService {

    /**
     * Save a soldierTrainingReport.
     *
     * @param soldierTrainingReportDTO the entity to save
     * @return the persisted entity
     */
    SoldierTrainingReportDTO save(SoldierTrainingReportDTO soldierTrainingReportDTO);

    /**
     * Get all the soldierTrainingReports.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SoldierTrainingReportDTO> findAll(Pageable pageable);

    /**
     * Get all the SoldierTrainingReport with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<SoldierTrainingReportDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" soldierTrainingReport.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SoldierTrainingReportDTO> findOne(Long id);

    /**
     * Delete the "id" soldierTrainingReport.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
