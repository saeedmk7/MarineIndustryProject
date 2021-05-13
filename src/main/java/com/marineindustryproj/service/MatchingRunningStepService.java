package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.MatchingRunningStepDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing MatchingRunningStep.
 */
public interface MatchingRunningStepService {

    /**
     * Save a matchingRunningStep.
     *
     * @param matchingRunningStepDTO the entity to save
     * @return the persisted entity
     */
    MatchingRunningStepDTO save(MatchingRunningStepDTO matchingRunningStepDTO);

    /**
     * Get all the matchingRunningSteps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MatchingRunningStepDTO> findAll(Pageable pageable);


    /**
     * Get the "id" matchingRunningStep.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MatchingRunningStepDTO> findOne(Long id);

    /**
     * Delete the "id" matchingRunningStep.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
