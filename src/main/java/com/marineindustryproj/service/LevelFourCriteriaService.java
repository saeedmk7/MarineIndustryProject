package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.LevelFourCriteriaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing LevelFourCriteria.
 */
public interface LevelFourCriteriaService {

    /**
     * Save a levelFourCriteria.
     *
     * @param levelFourCriteriaDTO the entity to save
     * @return the persisted entity
     */
    LevelFourCriteriaDTO save(LevelFourCriteriaDTO levelFourCriteriaDTO);

    /**
     * Get all the levelFourCriteria.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LevelFourCriteriaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" levelFourCriteria.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LevelFourCriteriaDTO> findOne(Long id);

    /**
     * Delete the "id" levelFourCriteria.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
