package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.RadehDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Radeh.
 */
public interface RadehService {

    /**
     * Save a radeh.
     *
     * @param radehDTO the entity to save
     * @return the persisted entity
     */
    RadehDTO save(RadehDTO radehDTO);

    /**
     * Get all the radehs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RadehDTO> findAll(Pageable pageable);


    /**
     * Get the "id" radeh.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RadehDTO> findOne(Long id);

    /**
     * Delete the "id" radeh.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
