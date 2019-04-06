package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.ToolsAndFacilityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ToolsAndFacility.
 */
public interface ToolsAndFacilityService {

    /**
     * Save a toolsAndFacility.
     *
     * @param toolsAndFacilityDTO the entity to save
     * @return the persisted entity
     */
    ToolsAndFacilityDTO save(ToolsAndFacilityDTO toolsAndFacilityDTO);

    /**
     * Get all the toolsAndFacilities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ToolsAndFacilityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" toolsAndFacility.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ToolsAndFacilityDTO> findOne(Long id);

    /**
     * Delete the "id" toolsAndFacility.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
