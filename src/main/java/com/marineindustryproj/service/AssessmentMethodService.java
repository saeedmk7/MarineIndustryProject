package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.AssessmentMethodDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AssessmentMethod.
 */
public interface AssessmentMethodService {

    /**
     * Save a assessmentMethod.
     *
     * @param assessmentMethodDTO the entity to save
     * @return the persisted entity
     */
    AssessmentMethodDTO save(AssessmentMethodDTO assessmentMethodDTO);

    /**
     * Get all the assessmentMethods.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AssessmentMethodDTO> findAll(Pageable pageable);


    /**
     * Get the "id" assessmentMethod.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AssessmentMethodDTO> findOne(Long id);

    /**
     * Delete the "id" assessmentMethod.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
