package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.ApplicationProcessRunStepDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ApplicationProcessRunStep.
 */
public interface ApplicationProcessRunStepService {

    /**
     * Save a applicationProcessRunStep.
     *
     * @param applicationProcessRunStepDTO the entity to save
     * @return the persisted entity
     */
    ApplicationProcessRunStepDTO save(ApplicationProcessRunStepDTO applicationProcessRunStepDTO);

    /**
     * Get all the applicationProcessRunSteps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ApplicationProcessRunStepDTO> findAll(Pageable pageable);


    /**
     * Get the "id" applicationProcessRunStep.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ApplicationProcessRunStepDTO> findOne(Long id);

    /**
     * Delete the "id" applicationProcessRunStep.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
