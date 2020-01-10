package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.JobNiazsanjiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing JobNiazsanji.
 */
public interface JobNiazsanjiService {

    /**
     * Save a jobNiazsanji.
     *
     * @param jobNiazsanjiDTO the entity to save
     * @return the persisted entity
     */
    JobNiazsanjiDTO save(JobNiazsanjiDTO jobNiazsanjiDTO);

    JobNiazsanjiDTO finalize(JobNiazsanjiDTO jobNiazsanjiDTO);

    /**
     * Get all the jobNiazsanjis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<JobNiazsanjiDTO> findAll(Pageable pageable);

    /**
     * Get all the JobNiazsanji with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<JobNiazsanjiDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" jobNiazsanji.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<JobNiazsanjiDTO> findOne(Long id);

    /**
     * Delete the "id" jobNiazsanji.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
