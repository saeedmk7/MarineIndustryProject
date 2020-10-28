package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.MonitorLearningProcessDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing MonitorLearningProcess.
 */
public interface MonitorLearningProcessService {

    /**
     * Save a monitorLearningProcess.
     *
     * @param monitorLearningProcessDTO the entity to save
     * @return the persisted entity
     */
    MonitorLearningProcessDTO save(MonitorLearningProcessDTO monitorLearningProcessDTO);

    /**
     * Get all the monitorLearningProcesses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MonitorLearningProcessDTO> findAll(Pageable pageable);

    /**
     * Get all the MonitorLearningProcess with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<MonitorLearningProcessDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" monitorLearningProcess.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MonitorLearningProcessDTO> findOne(Long id);

    /**
     * Delete the "id" monitorLearningProcess.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
