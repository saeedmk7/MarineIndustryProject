package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.RequestEducationalModuleDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing RequestEducationalModule.
 */
public interface RequestEducationalModuleService {

    /**
     * Save a requestEducationalModule.
     *
     * @param requestEducationalModuleDTO the entity to save
     * @return the persisted entity
     */
    RequestEducationalModuleDTO save(RequestEducationalModuleDTO requestEducationalModuleDTO);

    /**
     * Get all the requestEducationalModules.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RequestEducationalModuleDTO> findAll(Pageable pageable);

    /**
     * Get all the RequestEducationalModule with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<RequestEducationalModuleDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" requestEducationalModule.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RequestEducationalModuleDTO> findOne(Long id);

    /**
     * Delete the "id" requestEducationalModule.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
