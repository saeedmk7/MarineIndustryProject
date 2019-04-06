package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.DesignAndPlanningDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing DesignAndPlanning.
 */
public interface DesignAndPlanningService {

    /**
     * Save a designAndPlanning.
     *
     * @param designAndPlanningDTO the entity to save
     * @return the persisted entity
     */
    DesignAndPlanningDTO save(DesignAndPlanningDTO designAndPlanningDTO);

    /**
     * Get all the designAndPlannings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DesignAndPlanningDTO> findAll(Pageable pageable);

    /**
     * Get all the DesignAndPlanning with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<DesignAndPlanningDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" designAndPlanning.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DesignAndPlanningDTO> findOne(Long id);

    /**
     * Delete the "id" designAndPlanning.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
