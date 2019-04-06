package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.JobTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing JobType.
 */
public interface JobTypeService {

    /**
     * Save a jobType.
     *
     * @param jobTypeDTO the entity to save
     * @return the persisted entity
     */
    JobTypeDTO save(JobTypeDTO jobTypeDTO);

    /**
     * Get all the jobTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<JobTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" jobType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<JobTypeDTO> findOne(Long id);

    /**
     * Delete the "id" jobType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
