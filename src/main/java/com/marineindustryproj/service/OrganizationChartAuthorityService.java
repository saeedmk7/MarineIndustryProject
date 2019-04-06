package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.OrganizationChartAuthorityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing OrganizationChartAuthority.
 */
public interface OrganizationChartAuthorityService {

    /**
     * Save a organizationChartAuthority.
     *
     * @param organizationChartAuthorityDTO the entity to save
     * @return the persisted entity
     */
    OrganizationChartAuthorityDTO save(OrganizationChartAuthorityDTO organizationChartAuthorityDTO);

    /**
     * Get all the organizationChartAuthorities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrganizationChartAuthorityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" organizationChartAuthority.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OrganizationChartAuthorityDTO> findOne(Long id);

    /**
     * Delete the "id" organizationChartAuthority.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
