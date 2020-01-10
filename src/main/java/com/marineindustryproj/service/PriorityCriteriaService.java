package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.PriorityCriteriaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PriorityCriteria.
 */
public interface PriorityCriteriaService {

    /**
     * Save a priorityCriteria.
     *
     * @param priorityCriteriaDTO the entity to save
     * @return the persisted entity
     */
    PriorityCriteriaDTO save(PriorityCriteriaDTO priorityCriteriaDTO);

    /**
     * Get all the priorityCriteria.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PriorityCriteriaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" priorityCriteria.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PriorityCriteriaDTO> findOne(Long id);

    /**
     * Delete the "id" priorityCriteria.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
