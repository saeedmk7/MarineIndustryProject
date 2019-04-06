package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.TeachApproachDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TeachApproach.
 */
public interface TeachApproachService {

    /**
     * Save a teachApproach.
     *
     * @param teachApproachDTO the entity to save
     * @return the persisted entity
     */
    TeachApproachDTO save(TeachApproachDTO teachApproachDTO);

    /**
     * Get all the teachApproaches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TeachApproachDTO> findAll(Pageable pageable);


    /**
     * Get the "id" teachApproach.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TeachApproachDTO> findOne(Long id);

    /**
     * Delete the "id" teachApproach.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
