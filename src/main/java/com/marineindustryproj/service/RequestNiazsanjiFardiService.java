package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.RequestNiazsanjiFardiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RequestNiazsanjiFardi.
 */
public interface RequestNiazsanjiFardiService {

    /**
     * Save a requestNiazsanjiFardi.
     *
     * @param requestNiazsanjiFardiDTO the entity to save
     * @return the persisted entity
     */
    RequestNiazsanjiFardiDTO save(RequestNiazsanjiFardiDTO requestNiazsanjiFardiDTO);
    /**
     * Finalize a requestNiazsanjiFardi.
     *
     * @param requestNiazsanjiFardiDTO the entity to save
     * @return the persisted entity
     */
    RequestNiazsanjiFardiDTO finalize(RequestNiazsanjiFardiDTO requestNiazsanjiFardiDTO);
    /**
     * Get all the requestNiazsanjiFardis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RequestNiazsanjiFardiDTO> findAll(Pageable pageable);

    /**
     * Get all the RequestNiazsanjiFardi with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<RequestNiazsanjiFardiDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" requestNiazsanjiFardi.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RequestNiazsanjiFardiDTO> findOne(Long id);

    /**
     * Delete the "id" requestNiazsanjiFardi.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
