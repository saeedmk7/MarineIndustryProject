package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.SoldierDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Soldier.
 */
public interface SoldierService {

    /**
     * Save a soldier.
     *
     * @param soldierDTO the entity to save
     * @return the persisted entity
     */
    SoldierDTO save(SoldierDTO soldierDTO);

    /**
     * Get all the soldiers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SoldierDTO> findAll(Pageable pageable);

    /**
     * Get all the Soldier with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<SoldierDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" soldier.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SoldierDTO> findOne(Long id);

    /**
     * Delete the "id" soldier.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
