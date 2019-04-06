package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.TeachTechniqueDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TeachTechnique.
 */
public interface TeachTechniqueService {

    /**
     * Save a teachTechnique.
     *
     * @param teachTechniqueDTO the entity to save
     * @return the persisted entity
     */
    TeachTechniqueDTO save(TeachTechniqueDTO teachTechniqueDTO);

    /**
     * Get all the teachTechniques.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TeachTechniqueDTO> findAll(Pageable pageable);


    /**
     * Get the "id" teachTechnique.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TeachTechniqueDTO> findOne(Long id);

    /**
     * Delete the "id" teachTechnique.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
