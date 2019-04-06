package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.TeachingApproachDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TeachingApproach.
 */
public interface TeachingApproachService {

    /**
     * Save a teachingApproach.
     *
     * @param teachingApproachDTO the entity to save
     * @return the persisted entity
     */
    TeachingApproachDTO save(TeachingApproachDTO teachingApproachDTO);

    /**
     * Get all the teachingApproaches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TeachingApproachDTO> findAll(Pageable pageable);


    /**
     * Get the "id" teachingApproach.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TeachingApproachDTO> findOne(Long id);

    /**
     * Delete the "id" teachingApproach.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
