package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.ScientificWorkGroupDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ScientificWorkGroup.
 */
public interface ScientificWorkGroupService {

    /**
     * Save a scientificWorkGroup.
     *
     * @param scientificWorkGroupDTO the entity to save
     * @return the persisted entity
     */
    ScientificWorkGroupDTO save(ScientificWorkGroupDTO scientificWorkGroupDTO);

    /**
     * Get all the scientificWorkGroups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ScientificWorkGroupDTO> findAll(Pageable pageable);


    /**
     * Get the "id" scientificWorkGroup.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ScientificWorkGroupDTO> findOne(Long id);

    /**
     * Delete the "id" scientificWorkGroup.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
