package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.EffectivenessLevelDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EffectivenessLevel.
 */
public interface EffectivenessLevelService {

    /**
     * Save a effectivenessLevel.
     *
     * @param effectivenessLevelDTO the entity to save
     * @return the persisted entity
     */
    EffectivenessLevelDTO save(EffectivenessLevelDTO effectivenessLevelDTO);

    /**
     * Get all the effectivenessLevels.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EffectivenessLevelDTO> findAll(Pageable pageable);


    /**
     * Get the "id" effectivenessLevel.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EffectivenessLevelDTO> findOne(Long id);

    /**
     * Delete the "id" effectivenessLevel.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
