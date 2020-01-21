package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.NiazsanjiInputDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing NiazsanjiInput.
 */
public interface NiazsanjiInputService {

    /**
     * Save a niazsanjiInput.
     *
     * @param niazsanjiInputDTO the entity to save
     * @return the persisted entity
     */
    NiazsanjiInputDTO save(NiazsanjiInputDTO niazsanjiInputDTO);

    /**
     * Get all the niazsanjiInputs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NiazsanjiInputDTO> findAll(Pageable pageable);


    /**
     * Get the "id" niazsanjiInput.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NiazsanjiInputDTO> findOne(Long id);

    /**
     * Delete the "id" niazsanjiInput.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
