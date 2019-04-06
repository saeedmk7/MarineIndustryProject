package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.WorkUnitDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing WorkUnit.
 */
public interface WorkUnitService {

    /**
     * Save a workUnit.
     *
     * @param workUnitDTO the entity to save
     * @return the persisted entity
     */
    WorkUnitDTO save(WorkUnitDTO workUnitDTO);

    /**
     * Get all the workUnits.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<WorkUnitDTO> findAll(Pageable pageable);


    /**
     * Get the "id" workUnit.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<WorkUnitDTO> findOne(Long id);

    /**
     * Delete the "id" workUnit.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
