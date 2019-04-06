package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.EvaluationMethodDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EvaluationMethod.
 */
public interface EvaluationMethodService {

    /**
     * Save a evaluationMethod.
     *
     * @param evaluationMethodDTO the entity to save
     * @return the persisted entity
     */
    EvaluationMethodDTO save(EvaluationMethodDTO evaluationMethodDTO);

    /**
     * Get all the evaluationMethods.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EvaluationMethodDTO> findAll(Pageable pageable);


    /**
     * Get the "id" evaluationMethod.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EvaluationMethodDTO> findOne(Long id);

    /**
     * Delete the "id" evaluationMethod.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
