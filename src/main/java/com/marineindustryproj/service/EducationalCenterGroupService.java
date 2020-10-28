package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.EducationalCenterGroupDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EducationalCenterGroup.
 */
public interface EducationalCenterGroupService {

    /**
     * Save a educationalCenterGroup.
     *
     * @param educationalCenterGroupDTO the entity to save
     * @return the persisted entity
     */
    EducationalCenterGroupDTO save(EducationalCenterGroupDTO educationalCenterGroupDTO);

    /**
     * Get all the educationalCenterGroups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EducationalCenterGroupDTO> findAll(Pageable pageable);


    /**
     * Get the "id" educationalCenterGroup.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EducationalCenterGroupDTO> findOne(Long id);

    /**
     * Delete the "id" educationalCenterGroup.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
