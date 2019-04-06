package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.NiazsanjiFardiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing NiazsanjiFardi.
 */
public interface NiazsanjiFardiService {

    /**
     * Save a niazsanjiFardi.
     *
     * @param niazsanjiFardiDTO the entity to save
     * @return the persisted entity
     */
    NiazsanjiFardiDTO save(NiazsanjiFardiDTO niazsanjiFardiDTO);

    NiazsanjiFardiDTO finalize(NiazsanjiFardiDTO niazsanjiFardiDTO);

    /**
     * Get all the niazsanjiFardis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NiazsanjiFardiDTO> findAll(Pageable pageable);

    /**
     * Get all the NiazsanjiFardi with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<NiazsanjiFardiDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" niazsanjiFardi.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NiazsanjiFardiDTO> findOne(Long id);

    /**
     * Delete the "id" niazsanjiFardi.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
