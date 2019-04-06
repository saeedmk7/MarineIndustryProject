package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.ServiceUnitDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ServiceUnit.
 */
public interface ServiceUnitService {

    /**
     * Save a serviceUnit.
     *
     * @param serviceUnitDTO the entity to save
     * @return the persisted entity
     */
    ServiceUnitDTO save(ServiceUnitDTO serviceUnitDTO);

    /**
     * Get all the serviceUnits.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ServiceUnitDTO> findAll(Pageable pageable);


    /**
     * Get the "id" serviceUnit.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ServiceUnitDTO> findOne(Long id);

    /**
     * Delete the "id" serviceUnit.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
