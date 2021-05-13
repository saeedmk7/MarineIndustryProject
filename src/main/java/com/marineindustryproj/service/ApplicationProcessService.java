package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.ApplicationProcessDTO;

import com.marineindustryproj.service.dto.customs.ApplicationProcessModels.ApplicationProcessSaveDataModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ApplicationProcess.
 */
public interface ApplicationProcessService {

    /**
     * Save a applicationProcess.
     *
     * @param applicationProcessDTO the entity to save
     * @return the persisted entity
     */
    ApplicationProcessDTO save(ApplicationProcessDTO applicationProcessDTO);

    ApplicationProcessDTO saveDataModel(ApplicationProcessSaveDataModel applicationProcessSaveDataModel);

    /**
     * Get all the applicationProcesses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ApplicationProcessDTO> findAll(Pageable pageable);

    /**
     * Get all the ApplicationProcess with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<ApplicationProcessDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" applicationProcess.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ApplicationProcessDTO> findOne(Long id);

    /**
     * Delete the "id" applicationProcess.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
