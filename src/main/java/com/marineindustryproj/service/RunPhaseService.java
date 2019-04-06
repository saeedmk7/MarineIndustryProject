package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.RunPhaseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RunPhase.
 */
public interface RunPhaseService {

    /**
     * Save a runPhase.
     *
     * @param runPhaseDTO the entity to save
     * @return the persisted entity
     */
    RunPhaseDTO save(RunPhaseDTO runPhaseDTO);

    /**
     * Get all the runPhases.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RunPhaseDTO> findAll(Pageable pageable);

    /**
     * Get all the RunPhase with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<RunPhaseDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" runPhase.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RunPhaseDTO> findOne(Long id);

    /**
     * Delete the "id" runPhase.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
