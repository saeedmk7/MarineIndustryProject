package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.SoldierMediaAwarenessReportDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SoldierMediaAwarenessReport.
 */
public interface SoldierMediaAwarenessReportService {

    /**
     * Save a soldierMediaAwarenessReport.
     *
     * @param soldierMediaAwarenessReportDTO the entity to save
     * @return the persisted entity
     */
    SoldierMediaAwarenessReportDTO save(SoldierMediaAwarenessReportDTO soldierMediaAwarenessReportDTO);

    /**
     * Get all the soldierMediaAwarenessReports.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SoldierMediaAwarenessReportDTO> findAll(Pageable pageable);

    /**
     * Get all the SoldierMediaAwarenessReport with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<SoldierMediaAwarenessReportDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" soldierMediaAwarenessReport.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SoldierMediaAwarenessReportDTO> findOne(Long id);

    /**
     * Delete the "id" soldierMediaAwarenessReport.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
