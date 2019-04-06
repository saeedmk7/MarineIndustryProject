package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.ScoreItemDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ScoreItem.
 */
public interface ScoreItemService {

    /**
     * Save a scoreItem.
     *
     * @param scoreItemDTO the entity to save
     * @return the persisted entity
     */
    ScoreItemDTO save(ScoreItemDTO scoreItemDTO);

    /**
     * Get all the scoreItems.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ScoreItemDTO> findAll(Pageable pageable);


    /**
     * Get the "id" scoreItem.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ScoreItemDTO> findOne(Long id);

    /**
     * Delete the "id" scoreItem.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
