package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.HeadlineDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Headline.
 */
public interface HeadlineService {

    /**
     * Save a headline.
     *
     * @param headlineDTO the entity to save
     * @return the persisted entity
     */
    HeadlineDTO save(HeadlineDTO headlineDTO);

    /**
     * Get all the headlines.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<HeadlineDTO> findAll(Pageable pageable);


    /**
     * Get the "id" headline.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<HeadlineDTO> findOne(Long id);

    /**
     * Delete the "id" headline.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
