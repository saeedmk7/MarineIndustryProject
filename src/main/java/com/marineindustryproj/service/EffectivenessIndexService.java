package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.EffectivenessIndexDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EffectivenessIndex.
 */
public interface EffectivenessIndexService {

    /**
     * Save a effectivenessIndex.
     *
     * @param effectivenessIndexDTO the entity to save
     * @return the persisted entity
     */
    EffectivenessIndexDTO save(EffectivenessIndexDTO effectivenessIndexDTO);

    /**
     * Get all the effectivenessIndices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EffectivenessIndexDTO> findAll(Pageable pageable);


    /**
     * Get the "id" effectivenessIndex.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EffectivenessIndexDTO> findOne(Long id);

    /**
     * Delete the "id" effectivenessIndex.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
