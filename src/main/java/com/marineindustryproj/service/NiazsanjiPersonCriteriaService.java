package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.NiazsanjiPersonCriteriaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing NiazsanjiPersonCriteria.
 */
public interface NiazsanjiPersonCriteriaService {

    /**
     * Save a niazsanjiPersonCriteria.
     *
     * @param niazsanjiPersonCriteriaDTO the entity to save
     * @return the persisted entity
     */
    NiazsanjiPersonCriteriaDTO save(NiazsanjiPersonCriteriaDTO niazsanjiPersonCriteriaDTO);

    /**
     * Get all the niazsanjiPersonCriteria.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NiazsanjiPersonCriteriaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" niazsanjiPersonCriteria.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NiazsanjiPersonCriteriaDTO> findOne(Long id);

    /**
     * Delete the "id" niazsanjiPersonCriteria.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
