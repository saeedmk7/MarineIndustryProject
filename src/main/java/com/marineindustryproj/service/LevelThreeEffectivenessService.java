package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.LevelThreeEffectivenessDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing LevelThreeEffectiveness.
 */
public interface LevelThreeEffectivenessService {

    /**
     * Save a levelThreeEffectiveness.
     *
     * @param levelThreeEffectivenessDTO the entity to save
     * @return the persisted entity
     */
    LevelThreeEffectivenessDTO save(LevelThreeEffectivenessDTO levelThreeEffectivenessDTO);

    LevelThreeEffectivenessDTO saveWithScore(LevelThreeEffectivenessDTO levelThreeEffectivenessDTO);

    Boolean completeLevel(Long finalNiazsanjiReportId) throws Exception;

    /**
     * Get all the levelThreeEffectivenesses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LevelThreeEffectivenessDTO> findAll(Pageable pageable);

    /**
     * Get all the LevelThreeEffectiveness with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<LevelThreeEffectivenessDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" levelThreeEffectiveness.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LevelThreeEffectivenessDTO> findOne(Long id);

    /**
     * Delete the "id" levelThreeEffectiveness.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
