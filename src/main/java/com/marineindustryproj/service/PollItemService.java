package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.PollItemDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PollItem.
 */
public interface PollItemService {

    /**
     * Save a pollItem.
     *
     * @param pollItemDTO the entity to save
     * @return the persisted entity
     */
    PollItemDTO save(PollItemDTO pollItemDTO);

    /**
     * Get all the pollItems.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PollItemDTO> findAll(Pageable pageable);


    /**
     * Get the "id" pollItem.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PollItemDTO> findOne(Long id);

    /**
     * Delete the "id" pollItem.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
