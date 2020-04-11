package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.EffectivenessPhaseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EffectivenessPhase.
 */
public interface EffectivenessPhaseService {

    /**
     * Save a effectivenessPhase.
     *
     * @param effectivenessPhaseDTO the entity to save
     * @return the persisted entity
     */
    EffectivenessPhaseDTO save(EffectivenessPhaseDTO effectivenessPhaseDTO);

    Boolean completeLevelTwo(Long finalNiazsanjiReportId) throws Exception;

    Boolean completeEffectivenessPhase(Long finalNiazsanjiReportId) throws Exception;

    /**
     * Get all the effectivenessPhases.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EffectivenessPhaseDTO> findAll(Pageable pageable);

    /**
     * Get all the EffectivenessPhase with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<EffectivenessPhaseDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" effectivenessPhase.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EffectivenessPhaseDTO> findOne(Long id);

    /**
     * Delete the "id" effectivenessPhase.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
