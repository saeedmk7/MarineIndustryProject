package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.ReportGeneratorAuthorityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ReportGeneratorAuthority.
 */
public interface ReportGeneratorAuthorityService {

    /**
     * Save a reportGeneratorAuthority.
     *
     * @param reportGeneratorAuthorityDTO the entity to save
     * @return the persisted entity
     */
    ReportGeneratorAuthorityDTO save(ReportGeneratorAuthorityDTO reportGeneratorAuthorityDTO);

    /**
     * Get all the reportGeneratorAuthorities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ReportGeneratorAuthorityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" reportGeneratorAuthority.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ReportGeneratorAuthorityDTO> findOne(Long id);

    /**
     * Delete the "id" reportGeneratorAuthority.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    void deleteByReportGeneratorId(Long id);
}
