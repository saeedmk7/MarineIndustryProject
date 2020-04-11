package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.EffectivenessPhaseLevelDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EffectivenessPhaseLevel.
 */
public interface EffectivenessPhaseLevelService {

    /**
     * Save a effectivenessPhaseLevel.
     *
     * @param effectivenessPhaseLevelDTO the entity to save
     * @return the persisted entity
     */
    EffectivenessPhaseLevelDTO save(EffectivenessPhaseLevelDTO effectivenessPhaseLevelDTO);

    /**
     * Get all the effectivenessPhaseLevels.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EffectivenessPhaseLevelDTO> findAll(Pageable pageable);


    /**
     * Get the "id" effectivenessPhaseLevel.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EffectivenessPhaseLevelDTO> findOne(Long id);

    /**
     * Delete the "id" effectivenessPhaseLevel.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
