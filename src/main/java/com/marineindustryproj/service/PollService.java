package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.PollDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Poll.
 */
public interface PollService {

    /**
     * Save a poll.
     *
     * @param pollDTO the entity to save
     * @return the persisted entity
     */
    PollDTO save(PollDTO pollDTO);

    /**
     * Get all the polls.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PollDTO> findAll(Pageable pageable);


    /**
     * Get the "id" poll.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PollDTO> findOne(Long id);

    /**
     * Delete the "id" poll.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
