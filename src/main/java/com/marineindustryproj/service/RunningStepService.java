package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.RunningStepDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RunningStep.
 */
public interface RunningStepService {

    /**
     * Save a runningStep.
     *
     * @param runningStepDTO the entity to save
     * @return the persisted entity
     */
    RunningStepDTO save(RunningStepDTO runningStepDTO);

    /**
     * Get all the runningSteps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RunningStepDTO> findAll(Pageable pageable);


    /**
     * Get the "id" runningStep.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RunningStepDTO> findOne(Long id);

    /**
     * Delete the "id" runningStep.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
