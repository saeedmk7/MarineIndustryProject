package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.ApplicationProcessStepDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ApplicationProcessStep.
 */
public interface ApplicationProcessStepService {

    /**
     * Save a applicationProcessStep.
     *
     * @param applicationProcessStepDTO the entity to save
     * @return the persisted entity
     */
    ApplicationProcessStepDTO save(ApplicationProcessStepDTO applicationProcessStepDTO);

    /**
     * Get all the applicationProcessSteps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ApplicationProcessStepDTO> findAll(Pageable pageable);


    /**
     * Get the "id" applicationProcessStep.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ApplicationProcessStepDTO> findOne(Long id);

    /**
     * Delete the "id" applicationProcessStep.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
