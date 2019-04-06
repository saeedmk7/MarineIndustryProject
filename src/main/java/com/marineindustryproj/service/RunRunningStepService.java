package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.RunRunningStepDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RunRunningStep.
 */
public interface RunRunningStepService {

    /**
     * Save a runRunningStep.
     *
     * @param runRunningStepDTO the entity to save
     * @return the persisted entity
     */
    RunRunningStepDTO save(RunRunningStepDTO runRunningStepDTO);

    /**
     * Get all the runRunningSteps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RunRunningStepDTO> findAll(Pageable pageable);


    /**
     * Get the "id" runRunningStep.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RunRunningStepDTO> findOne(Long id);

    /**
     * Delete the "id" runRunningStep.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
