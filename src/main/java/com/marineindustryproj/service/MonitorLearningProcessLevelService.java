package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.MonitorLearningProcessLevelDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing MonitorLearningProcessLevel.
 */
public interface MonitorLearningProcessLevelService {

    /**
     * Save a monitorLearningProcessLevel.
     *
     * @param monitorLearningProcessLevelDTO the entity to save
     * @return the persisted entity
     */
    MonitorLearningProcessLevelDTO save(MonitorLearningProcessLevelDTO monitorLearningProcessLevelDTO);

    /**
     * Get all the monitorLearningProcessLevels.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MonitorLearningProcessLevelDTO> findAll(Pageable pageable);


    /**
     * Get the "id" monitorLearningProcessLevel.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MonitorLearningProcessLevelDTO> findOne(Long id);

    /**
     * Delete the "id" monitorLearningProcessLevel.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
