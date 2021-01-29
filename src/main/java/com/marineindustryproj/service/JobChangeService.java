package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.JobChangeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing JobChange.
 */
public interface JobChangeService {

    /**
     * Save a jobChange.
     *
     * @param jobChangeDTO the entity to save
     * @return the persisted entity
     */
    JobChangeDTO save(JobChangeDTO jobChangeDTO);

    /**
     * Get all the jobChanges.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<JobChangeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" jobChange.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<JobChangeDTO> findOne(Long id);

    /**
     * Delete the "id" jobChange.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
