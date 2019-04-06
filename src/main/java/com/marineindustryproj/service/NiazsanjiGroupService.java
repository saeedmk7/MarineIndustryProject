package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.NiazsanjiGroupDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing NiazsanjiGroup.
 */
public interface NiazsanjiGroupService {

    /**
     * Save a niazsanjiGroup.
     *
     * @param niazsanjiGroupDTO the entity to save
     * @return the persisted entity
     */
    NiazsanjiGroupDTO save(NiazsanjiGroupDTO niazsanjiGroupDTO);

    /**
     * Get all the niazsanjiGroups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NiazsanjiGroupDTO> findAll(Pageable pageable);

    /**
     * Get all the NiazsanjiGroup with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<NiazsanjiGroupDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" niazsanjiGroup.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NiazsanjiGroupDTO> findOne(Long id);

    /**
     * Delete the "id" niazsanjiGroup.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
