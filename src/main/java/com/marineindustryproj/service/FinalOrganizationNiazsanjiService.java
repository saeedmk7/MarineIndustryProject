package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.FinalOrganizationNiazsanjiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing FinalOrganizationNiazsanji.
 */
public interface FinalOrganizationNiazsanjiService {

    /**
     * Save a finalOrganizationNiazsanji.
     *
     * @param finalOrganizationNiazsanjiDTO the entity to save
     * @return the persisted entity
     */
    FinalOrganizationNiazsanjiDTO save(FinalOrganizationNiazsanjiDTO finalOrganizationNiazsanjiDTO);

    /**
     * finalize a finalOrganizationNiazsanji.
     *
     * @param finalOrganizationNiazsanjiDTO the entity to finalize
     * @return the persisted entity
     */
    FinalOrganizationNiazsanjiDTO finalize(FinalOrganizationNiazsanjiDTO finalOrganizationNiazsanjiDTO);

    /**
     * Get all the finalOrganizationNiazsanjis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FinalOrganizationNiazsanjiDTO> findAll(Pageable pageable);

    /**
     * Get all the FinalOrganizationNiazsanji with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<FinalOrganizationNiazsanjiDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" finalOrganizationNiazsanji.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FinalOrganizationNiazsanjiDTO> findOne(Long id);

    /**
     * Delete the "id" finalOrganizationNiazsanji.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
