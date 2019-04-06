package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.SubTaskDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SubTask.
 */
public interface SubTaskService {

    /**
     * Save a subTask.
     *
     * @param subTaskDTO the entity to save
     * @return the persisted entity
     */
    SubTaskDTO save(SubTaskDTO subTaskDTO);

    /**
     * Get all the subTasks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SubTaskDTO> findAll(Pageable pageable);


    /**
     * Get the "id" subTask.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SubTaskDTO> findOne(Long id);

    /**
     * Delete the "id" subTask.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
