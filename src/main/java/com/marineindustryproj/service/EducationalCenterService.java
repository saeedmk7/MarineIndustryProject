package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.EducationalCenterDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EducationalCenter.
 */
public interface EducationalCenterService {

    /**
     * Save a educationalCenter.
     *
     * @param educationalCenterDTO the entity to save
     * @return the persisted entity
     */
    EducationalCenterDTO save(EducationalCenterDTO educationalCenterDTO);

    /**
     * Get all the educationalCenters.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EducationalCenterDTO> findAll(Pageable pageable);

    /**
     * Get all the EducationalCenter with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<EducationalCenterDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" educationalCenter.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EducationalCenterDTO> findOne(Long id);

    /**
     * Delete the "id" educationalCenter.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
