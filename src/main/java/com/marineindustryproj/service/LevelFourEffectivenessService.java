package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.LevelFourEffectivenessDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing LevelFourEffectiveness.
 */
public interface LevelFourEffectivenessService {

    /**
     * Save a levelFourEffectiveness.
     *
     * @param levelFourEffectivenessDTO the entity to save
     * @return the persisted entity
     */
    LevelFourEffectivenessDTO save(LevelFourEffectivenessDTO levelFourEffectivenessDTO);

    LevelFourEffectivenessDTO saveWithScore(LevelFourEffectivenessDTO levelFourEffectivenessDTO);

    Boolean completeLevel(Long finalNiazsanjiReportId) throws Exception;

    /**
     * Get all the levelFourEffectivenesses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LevelFourEffectivenessDTO> findAll(Pageable pageable);

    /**
     * Get all the LevelFourEffectiveness with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<LevelFourEffectivenessDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" levelFourEffectiveness.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LevelFourEffectivenessDTO> findOne(Long id);

    /**
     * Delete the "id" levelFourEffectiveness.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
