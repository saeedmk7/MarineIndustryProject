package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.EducationalCenterCriteriaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EducationalCenterCriteria.
 */
public interface EducationalCenterCriteriaService {

    /**
     * Save a educationalCenterCriteria.
     *
     * @param educationalCenterCriteriaDTO the entity to save
     * @return the persisted entity
     */
    EducationalCenterCriteriaDTO save(EducationalCenterCriteriaDTO educationalCenterCriteriaDTO);

    /**
     * Get all the educationalCenterCriteria.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EducationalCenterCriteriaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" educationalCenterCriteria.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EducationalCenterCriteriaDTO> findOne(Long id);

    /**
     * Delete the "id" educationalCenterCriteria.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
