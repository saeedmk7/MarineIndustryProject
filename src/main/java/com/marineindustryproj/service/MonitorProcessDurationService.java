package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.MonitorProcessDurationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing MonitorProcessDuration.
 */
public interface MonitorProcessDurationService {

    /**
     * Save a monitorProcessDuration.
     *
     * @param monitorProcessDurationDTO the entity to save
     * @return the persisted entity
     */
    MonitorProcessDurationDTO save(MonitorProcessDurationDTO monitorProcessDurationDTO);

    /**
     * Get all the monitorProcessDurations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MonitorProcessDurationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" monitorProcessDuration.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MonitorProcessDurationDTO> findOne(Long id);

    /**
     * Delete the "id" monitorProcessDuration.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
