package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.EducationalModuleJobDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing EducationalModuleJob.
 */
public interface EducationalModuleJobService {

    /**
     * Save a educationalModuleJob.
     *
     * @param educationalModuleJobDTO the entity to save
     * @return the persisted entity
     */
    EducationalModuleJobDTO save(EducationalModuleJobDTO educationalModuleJobDTO);

    /**
     * Save a educationalModuleJob.
     *
     * @param educationalModuleJobDTOS the entity to save
     * @return the persisted entity
     */
    EducationalModuleJobDTO bulkSave(List<EducationalModuleJobDTO> educationalModuleJobDTOS);

    /**
     * Get all the educationalModuleJobs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EducationalModuleJobDTO> findAll(Pageable pageable);


    /**
     * Get the "id" educationalModuleJob.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EducationalModuleJobDTO> findOne(Long id);

    /**
     * Delete the "id" educationalModuleJob.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    /**
     * Delete the "jobId" educationalModuleJob.
     *
     * @param jobId the jobId of the entity
     */
    void deleteByJobId(Long jobId);
}
