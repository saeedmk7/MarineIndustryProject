package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.JobRecordDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing JobRecord.
 */
public interface JobRecordService {

    /**
     * Save a jobRecord.
     *
     * @param jobRecordDTO the entity to save
     * @return the persisted entity
     */
    JobRecordDTO save(JobRecordDTO jobRecordDTO);

    /**
     * Get all the jobRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<JobRecordDTO> findAll(Pageable pageable);


    /**
     * Get the "id" jobRecord.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<JobRecordDTO> findOne(Long id);

    /**
     * Delete the "id" jobRecord.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
