package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.PriorityCriteriaValueDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PriorityCriteriaValue.
 */
public interface PriorityCriteriaValueService {

    /**
     * Save a priorityCriteriaValue.
     *
     * @param priorityCriteriaValueDTO the entity to save
     * @return the persisted entity
     */
    PriorityCriteriaValueDTO save(PriorityCriteriaValueDTO priorityCriteriaValueDTO);
    PriorityCriteriaValueDTO saveBulk(PriorityCriteriaValueDTO[] priorityCriteriaValueDTOS);

    /**
     * Get all the priorityCriteriaValues.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PriorityCriteriaValueDTO> findAll(Pageable pageable);


    /**
     * Get the "id" priorityCriteriaValue.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PriorityCriteriaValueDTO> findOne(Long id);

    /**
     * Delete the "id" priorityCriteriaValue.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
