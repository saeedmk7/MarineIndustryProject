package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.LevelThreeCriteriaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing LevelThreeCriteria.
 */
public interface LevelThreeCriteriaService {

    /**
     * Save a levelThreeCriteria.
     *
     * @param levelThreeCriteriaDTO the entity to save
     * @return the persisted entity
     */
    LevelThreeCriteriaDTO save(LevelThreeCriteriaDTO levelThreeCriteriaDTO);

    /**
     * Get all the levelThreeCriteria.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LevelThreeCriteriaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" levelThreeCriteria.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LevelThreeCriteriaDTO> findOne(Long id);

    /**
     * Delete the "id" levelThreeCriteria.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
