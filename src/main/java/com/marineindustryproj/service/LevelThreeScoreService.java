package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.LevelThreeScoreDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing LevelThreeScore.
 */
public interface LevelThreeScoreService {

    /**
     * Save a levelThreeScore.
     *
     * @param levelThreeScoreDTO the entity to save
     * @return the persisted entity
     */
    LevelThreeScoreDTO save(LevelThreeScoreDTO levelThreeScoreDTO);

    /**
     * Get all the levelThreeScores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LevelThreeScoreDTO> findAll(Pageable pageable);


    /**
     * Get the "id" levelThreeScore.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LevelThreeScoreDTO> findOne(Long id);

    /**
     * Delete the "id" levelThreeScore.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
