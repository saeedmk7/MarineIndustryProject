package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.RequestOrganizationNiazsanjiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RequestOrganizationNiazsanji.
 */
public interface RequestOrganizationNiazsanjiService {

    /**
     * Save a requestOrganizationNiazsanji.
     *
     * @param requestOrganizationNiazsanjiDTO the entity to save
     * @return the persisted entity
     */
    RequestOrganizationNiazsanjiDTO save(RequestOrganizationNiazsanjiDTO requestOrganizationNiazsanjiDTO);

    /**
     * Finalize a requestOrganizationNiazsanji.
     *
     * @param requestOrganizationNiazsanjiDTO the entity to save
     * @return the persisted entity
     */
    RequestOrganizationNiazsanjiDTO finalize(RequestOrganizationNiazsanjiDTO requestOrganizationNiazsanjiDTO);

    /**
     * Get all the requestOrganizationNiazsanjis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RequestOrganizationNiazsanjiDTO> findAll(Pageable pageable);

    /**
     * Get all the RequestOrganizationNiazsanji with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<RequestOrganizationNiazsanjiDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" requestOrganizationNiazsanji.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RequestOrganizationNiazsanjiDTO> findOne(Long id);

    /**
     * Delete the "id" requestOrganizationNiazsanji.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
