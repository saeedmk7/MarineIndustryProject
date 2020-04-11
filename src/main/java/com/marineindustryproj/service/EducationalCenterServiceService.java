package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.EducationalCenterServiceDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EducationalCenterService.
 */
public interface EducationalCenterServiceService {

    /**
     * Save a educationalCenterService.
     *
     * @param educationalCenterServiceDTO the entity to save
     * @return the persisted entity
     */
    EducationalCenterServiceDTO save(EducationalCenterServiceDTO educationalCenterServiceDTO);

    /**
     * Get all the educationalCenterServices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EducationalCenterServiceDTO> findAll(Pageable pageable);


    /**
     * Get the "id" educationalCenterService.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EducationalCenterServiceDTO> findOne(Long id);

    /**
     * Delete the "id" educationalCenterService.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
