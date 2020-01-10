package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.PreJobNiazsanjiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PreJobNiazsanji.
 */
public interface PreJobNiazsanjiService {

    /**
     * Save a preJobNiazsanji.
     *
     * @param preJobNiazsanjiDTO the entity to save
     * @return the persisted entity
     */
    PreJobNiazsanjiDTO save(PreJobNiazsanjiDTO preJobNiazsanjiDTO);

    PreJobNiazsanjiDTO finalize(PreJobNiazsanjiDTO preJobNiazsanjiDTO) throws Exception;

    /**
     * Get all the preJobNiazsanjis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PreJobNiazsanjiDTO> findAll(Pageable pageable);

    /**
     * Get all the PreJobNiazsanji with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<PreJobNiazsanjiDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" preJobNiazsanji.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PreJobNiazsanjiDTO> findOne(Long id);

    /**
     * Delete the "id" preJobNiazsanji.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
