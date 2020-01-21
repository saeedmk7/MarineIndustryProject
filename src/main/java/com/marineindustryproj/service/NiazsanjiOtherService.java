package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.NiazsanjiOtherDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing NiazsanjiOther.
 */
public interface NiazsanjiOtherService {

    /**
     * Save a niazsanjiOther.
     *
     * @param niazsanjiOtherDTO the entity to save
     * @return the persisted entity
     */
    NiazsanjiOtherDTO save(NiazsanjiOtherDTO niazsanjiOtherDTO);

    NiazsanjiOtherDTO finalize(NiazsanjiOtherDTO niazsanjiOtherDTO);
    /**
     * Get all the niazsanjiOthers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NiazsanjiOtherDTO> findAll(Pageable pageable);

    /**
     * Get all the NiazsanjiOther with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<NiazsanjiOtherDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" niazsanjiOther.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NiazsanjiOtherDTO> findOne(Long id);

    /**
     * Delete the "id" niazsanjiOther.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
