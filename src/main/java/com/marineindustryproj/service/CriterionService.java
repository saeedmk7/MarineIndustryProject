package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.CriterionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Criterion.
 */
public interface CriterionService {

    /**
     * Save a criterion.
     *
     * @param criterionDTO the entity to save
     * @return the persisted entity
     */
    CriterionDTO save(CriterionDTO criterionDTO);

    /**
     * Get all the criteria.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CriterionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" criterion.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CriterionDTO> findOne(Long id);

    /**
     * Delete the "id" criterion.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
