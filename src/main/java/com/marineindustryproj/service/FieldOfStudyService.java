package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.FieldOfStudyDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing FieldOfStudy.
 */
public interface FieldOfStudyService {

    /**
     * Save a fieldOfStudy.
     *
     * @param fieldOfStudyDTO the entity to save
     * @return the persisted entity
     */
    FieldOfStudyDTO save(FieldOfStudyDTO fieldOfStudyDTO);

    /**
     * Get all the fieldOfStudies.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FieldOfStudyDTO> findAll(Pageable pageable);


    /**
     * Get the "id" fieldOfStudy.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FieldOfStudyDTO> findOne(Long id);

    /**
     * Delete the "id" fieldOfStudy.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
