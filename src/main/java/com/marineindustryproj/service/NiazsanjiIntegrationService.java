package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.NiazsanjiIntegrationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing NiazsanjiIntegration.
 */
public interface NiazsanjiIntegrationService {

    /**
     * Save a niazsanjiIntegration.
     *
     * @param niazsanjiIntegrationDTO the entity to save
     * @return the persisted entity
     */
    NiazsanjiIntegrationDTO save(NiazsanjiIntegrationDTO niazsanjiIntegrationDTO);
    NiazsanjiIntegrationDTO finalize(NiazsanjiIntegrationDTO niazsanjiIntegrationDTO);

    /**
     * Get all the niazsanjiIntegrations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NiazsanjiIntegrationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" niazsanjiIntegration.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NiazsanjiIntegrationDTO> findOne(Long id);

    /**
     * Delete the "id" niazsanjiIntegration.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
