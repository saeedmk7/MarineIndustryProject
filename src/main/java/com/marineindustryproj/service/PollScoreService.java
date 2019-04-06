package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.PollScoreDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PollScore.
 */
public interface PollScoreService {

    /**
     * Save a pollScore.
     *
     * @param pollScoreDTO the entity to save
     * @return the persisted entity
     */
    PollScoreDTO save(PollScoreDTO pollScoreDTO);

    /**
     * Get all the pollScores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PollScoreDTO> findAll(Pageable pageable);


    /**
     * Get the "id" pollScore.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PollScoreDTO> findOne(Long id);

    /**
     * Delete the "id" pollScore.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
