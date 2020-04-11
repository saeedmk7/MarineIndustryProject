package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.LevelFourScoreDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing LevelFourScore.
 */
public interface LevelFourScoreService {

    /**
     * Save a levelFourScore.
     *
     * @param levelFourScoreDTO the entity to save
     * @return the persisted entity
     */
    LevelFourScoreDTO save(LevelFourScoreDTO levelFourScoreDTO);

    /**
     * Get all the levelFourScores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LevelFourScoreDTO> findAll(Pageable pageable);


    /**
     * Get the "id" levelFourScore.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LevelFourScoreDTO> findOne(Long id);

    /**
     * Delete the "id" levelFourScore.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
