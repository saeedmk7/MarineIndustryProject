package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.MainTaskDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing MainTask.
 */
public interface MainTaskService {

    /**
     * Save a mainTask.
     *
     * @param mainTaskDTO the entity to save
     * @return the persisted entity
     */
    MainTaskDTO save(MainTaskDTO mainTaskDTO);

    /**
     * Get all the mainTasks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MainTaskDTO> findAll(Pageable pageable);

    /**
     * Get all the MainTask with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<MainTaskDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" mainTask.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MainTaskDTO> findOne(Long id);

    /**
     * Delete the "id" mainTask.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
