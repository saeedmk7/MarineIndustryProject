package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.EvaluateCriteriaDataDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EvaluateCriteriaData.
 */
public interface EvaluateCriteriaDataService {

    /**
     * Save a evaluateCriteriaData.
     *
     * @param evaluateCriteriaDataDTO the entity to save
     * @return the persisted entity
     */
    EvaluateCriteriaDataDTO save(EvaluateCriteriaDataDTO evaluateCriteriaDataDTO);

    /**
     * Get all the evaluateCriteriaData.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EvaluateCriteriaDataDTO> findAll(Pageable pageable);

    /**
     * Get all the EvaluateCriteriaData with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<EvaluateCriteriaDataDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" evaluateCriteriaData.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EvaluateCriteriaDataDTO> findOne(Long id);

    /**
     * Delete the "id" evaluateCriteriaData.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
