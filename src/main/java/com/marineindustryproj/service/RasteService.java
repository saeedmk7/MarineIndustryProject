package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.RasteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Raste.
 */
public interface RasteService {

    /**
     * Save a raste.
     *
     * @param rasteDTO the entity to save
     * @return the persisted entity
     */
    RasteDTO save(RasteDTO rasteDTO);

    /**
     * Get all the rastes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RasteDTO> findAll(Pageable pageable);


    /**
     * Get the "id" raste.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RasteDTO> findOne(Long id);

    /**
     * Delete the "id" raste.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
