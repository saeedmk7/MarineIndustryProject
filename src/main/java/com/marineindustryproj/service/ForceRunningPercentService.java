package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.ForceRunningPercentDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ForceRunningPercent.
 */
public interface ForceRunningPercentService {

    /**
     * Save a forceRunningPercent.
     *
     * @param forceRunningPercentDTO the entity to save
     * @return the persisted entity
     */
    ForceRunningPercentDTO save(ForceRunningPercentDTO forceRunningPercentDTO);

    /**
     * Get all the forceRunningPercents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ForceRunningPercentDTO> findAll(Pageable pageable);

    /**
     * Get all the ForceRunningPercent with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<ForceRunningPercentDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" forceRunningPercent.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ForceRunningPercentDTO> findOne(Long id);

    /**
     * Delete the "id" forceRunningPercent.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
