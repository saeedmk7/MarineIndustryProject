package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.DesignNiazsanjiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing DesignNiazsanji.
 */
public interface DesignNiazsanjiService {

    /**
     * Save a designNiazsanji.
     *
     * @param designNiazsanjiDTO the entity to save
     * @return the persisted entity
     */
    DesignNiazsanjiDTO save(DesignNiazsanjiDTO designNiazsanjiDTO);

    /**
     * Get all the designNiazsanjis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DesignNiazsanjiDTO> findAll(Pageable pageable);

    /**
     * Get all the DesignNiazsanji with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<DesignNiazsanjiDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" designNiazsanji.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DesignNiazsanjiDTO> findOne(Long id);

    /**
     * Delete the "id" designNiazsanji.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
