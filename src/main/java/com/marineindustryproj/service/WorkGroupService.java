package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.WorkGroupDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing WorkGroup.
 */
public interface WorkGroupService {

    /**
     * Save a workGroup.
     *
     * @param workGroupDTO the entity to save
     * @return the persisted entity
     */
    WorkGroupDTO save(WorkGroupDTO workGroupDTO);

    /**
     * Get all the workGroups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<WorkGroupDTO> findAll(Pageable pageable);


    /**
     * Get the "id" workGroup.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<WorkGroupDTO> findOne(Long id);

    /**
     * Delete the "id" workGroup.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
