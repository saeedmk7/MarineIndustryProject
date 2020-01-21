package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.RequestOtherNiazsanjiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RequestOtherNiazsanji.
 */
public interface RequestOtherNiazsanjiService {

    /**
     * Save a requestOtherNiazsanji.
     *
     * @param requestOtherNiazsanjiDTO the entity to save
     * @return the persisted entity
     */
    RequestOtherNiazsanjiDTO save(RequestOtherNiazsanjiDTO requestOtherNiazsanjiDTO);

    RequestOtherNiazsanjiDTO finalize(RequestOtherNiazsanjiDTO requestOtherNiazsanjiDTO);
    /**
     * Get all the requestOtherNiazsanjis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RequestOtherNiazsanjiDTO> findAll(Pageable pageable);

    /**
     * Get all the RequestOtherNiazsanji with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<RequestOtherNiazsanjiDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" requestOtherNiazsanji.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RequestOtherNiazsanjiDTO> findOne(Long id);

    /**
     * Delete the "id" requestOtherNiazsanji.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
