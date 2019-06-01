package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.JamHelpDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing JamHelp.
 */
public interface JamHelpService {

    /**
     * Save a jamHelp.
     *
     * @param jamHelpDTO the entity to save
     * @return the persisted entity
     */
    JamHelpDTO save(JamHelpDTO jamHelpDTO);

    /**
     * Get all the jamHelps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<JamHelpDTO> findAll(Pageable pageable);


    /**
     * Get the "id" jamHelp.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<JamHelpDTO> findOne(Long id);

    /**
     * Delete the "id" jamHelp.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
