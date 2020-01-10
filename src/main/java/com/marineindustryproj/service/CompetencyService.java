package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.CompetencyDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Competency.
 */
public interface CompetencyService {

    /**
     * Save a competency.
     *
     * @param competencyDTO the entity to save
     * @return the persisted entity
     */
    CompetencyDTO save(CompetencyDTO competencyDTO);

    /**
     * Get all the competencies.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CompetencyDTO> findAll(Pageable pageable);


    /**
     * Get the "id" competency.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CompetencyDTO> findOne(Long id);

    /**
     * Delete the "id" competency.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
