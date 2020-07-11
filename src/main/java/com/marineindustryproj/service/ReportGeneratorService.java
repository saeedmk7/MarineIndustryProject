package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.ReportGeneratorDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ReportGenerator.
 */
public interface ReportGeneratorService {

    /**
     * Save a reportGenerator.
     *
     * @param reportGeneratorDTO the entity to save
     * @return the persisted entity
     */
    ReportGeneratorDTO save(ReportGeneratorDTO reportGeneratorDTO);

    /**
     * Get all the reportGenerators.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ReportGeneratorDTO> findAll(Pageable pageable);

    /**
     * Get all the ReportGenerator with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<ReportGeneratorDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" reportGenerator.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ReportGeneratorDTO> findOne(Long id);

    Optional<ReportGeneratorDTO> findOneByGuid(String guid);

    /**
     * Delete the "id" reportGenerator.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
