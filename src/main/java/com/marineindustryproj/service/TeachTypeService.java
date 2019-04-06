package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.TeachTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TeachType.
 */
public interface TeachTypeService {

    /**
     * Save a teachType.
     *
     * @param teachTypeDTO the entity to save
     * @return the persisted entity
     */
    TeachTypeDTO save(TeachTypeDTO teachTypeDTO);

    /**
     * Get all the teachTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TeachTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" teachType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TeachTypeDTO> findOne(Long id);

    /**
     * Delete the "id" teachType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
