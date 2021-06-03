package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.CapitationDTO;

import com.marineindustryproj.service.dto.customs.CapitationReportModels.CapitationReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Capitation.
 */
public interface CapitationService {

    /**
     * Save a capitation.
     *
     * @param capitationDTO the entity to save
     * @return the persisted entity
     */
    CapitationDTO save(CapitationDTO capitationDTO);

    /**
     * Get all the capitations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CapitationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" capitation.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CapitationDTO> findOne(Long id);

    /**
     * Delete the "id" capitation.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    CapitationReport getCapitationReport(Integer niazsanjiYear, long organizationChartId) throws Exception;
}
