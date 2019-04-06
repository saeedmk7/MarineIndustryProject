package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.WorkIndustryDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing WorkIndustry.
 */
public interface WorkIndustryService {

    /**
     * Save a workIndustry.
     *
     * @param workIndustryDTO the entity to save
     * @return the persisted entity
     */
    WorkIndustryDTO save(WorkIndustryDTO workIndustryDTO);

    /**
     * Get all the workIndustries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<WorkIndustryDTO> findAll(Pageable pageable);


    /**
     * Get the "id" workIndustry.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<WorkIndustryDTO> findOne(Long id);

    /**
     * Delete the "id" workIndustry.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
