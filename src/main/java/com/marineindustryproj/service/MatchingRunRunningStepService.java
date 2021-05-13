package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.MatchingRunRunningStepDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing MatchingRunRunningStep.
 */
public interface MatchingRunRunningStepService {

    /**
     * Save a matchingRunRunningStep.
     *
     * @param matchingRunRunningStepDTO the entity to save
     * @return the persisted entity
     */
    MatchingRunRunningStepDTO save(MatchingRunRunningStepDTO matchingRunRunningStepDTO);

    /**
     * Get all the matchingRunRunningSteps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MatchingRunRunningStepDTO> findAll(Pageable pageable);


    /**
     * Get the "id" matchingRunRunningStep.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MatchingRunRunningStepDTO> findOne(Long id);

    /**
     * Delete the "id" matchingRunRunningStep.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
