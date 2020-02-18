package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.EvaluateCriteriaTrainingDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EvaluateCriteriaTraining.
 */
public interface EvaluateCriteriaTrainingService {

    /**
     * Save a evaluateCriteriaTraining.
     *
     * @param evaluateCriteriaTrainingDTO the entity to save
     * @return the persisted entity
     */
    EvaluateCriteriaTrainingDTO save(EvaluateCriteriaTrainingDTO evaluateCriteriaTrainingDTO);

    /**
     * Get all the evaluateCriteriaTrainings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EvaluateCriteriaTrainingDTO> findAll(Pageable pageable);

    /**
     * Get all the EvaluateCriteriaTraining with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<EvaluateCriteriaTrainingDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" evaluateCriteriaTraining.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EvaluateCriteriaTrainingDTO> findOne(Long id);

    /**
     * Delete the "id" evaluateCriteriaTraining.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
