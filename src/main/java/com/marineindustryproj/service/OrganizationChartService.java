package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.OrganizationChartDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing OrganizationChart.
 */
public interface OrganizationChartService {

    /**
     * Save a organizationChart.
     *
     * @param organizationChartDTO the entity to save
     * @return the persisted entity
     */
    OrganizationChartDTO save(OrganizationChartDTO organizationChartDTO);

    /**
     * Get all the organizationCharts.
     *
     * @return the list of entities
     */
    List<OrganizationChartDTO> findAll();


    /**
     * Get the "id" organizationChart.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OrganizationChartDTO> findOne(Long id);

    /**
     * Delete the "id" organizationChart.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
