package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.ActivityAreaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ActivityArea.
 */
public interface ActivityAreaService {

    /**
     * Save a activityArea.
     *
     * @param activityAreaDTO the entity to save
     * @return the persisted entity
     */
    ActivityAreaDTO save(ActivityAreaDTO activityAreaDTO);

    /**
     * Get all the activityAreas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ActivityAreaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" activityArea.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ActivityAreaDTO> findOne(Long id);

    /**
     * Delete the "id" activityArea.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
