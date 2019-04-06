package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.EmploymentTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EmploymentType.
 */
public interface EmploymentTypeService {

    /**
     * Save a employmentType.
     *
     * @param employmentTypeDTO the entity to save
     * @return the persisted entity
     */
    EmploymentTypeDTO save(EmploymentTypeDTO employmentTypeDTO);

    /**
     * Get all the employmentTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EmploymentTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" employmentType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EmploymentTypeDTO> findOne(Long id);

    /**
     * Delete the "id" employmentType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
