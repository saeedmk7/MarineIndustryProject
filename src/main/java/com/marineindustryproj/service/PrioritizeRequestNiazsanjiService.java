package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.PrioritizeRequestNiazsanjiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PrioritizeRequestNiazsanji.
 */
public interface PrioritizeRequestNiazsanjiService {

    /**
     * Save a prioritizeRequestNiazsanji.
     *
     * @param prioritizeRequestNiazsanjiDTO the entity to save
     * @return the persisted entity
     */
    PrioritizeRequestNiazsanjiDTO save(PrioritizeRequestNiazsanjiDTO prioritizeRequestNiazsanjiDTO);

    PrioritizeRequestNiazsanjiDTO finalize(PrioritizeRequestNiazsanjiDTO prioritizeRequestNiazsanjiDTO);

    /**
     * Get all the prioritizeRequestNiazsanjis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PrioritizeRequestNiazsanjiDTO> findAll(Pageable pageable);

    /**
     * Get all the PrioritizeRequestNiazsanji with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<PrioritizeRequestNiazsanjiDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" prioritizeRequestNiazsanji.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PrioritizeRequestNiazsanjiDTO> findOne(Long id);

    /**
     * Delete the "id" prioritizeRequestNiazsanji.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
