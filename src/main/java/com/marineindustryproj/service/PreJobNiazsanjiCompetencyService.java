package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.PreJobNiazsanjiCompetencyDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PreJobNiazsanjiCompetency.
 */
public interface PreJobNiazsanjiCompetencyService {

    /**
     * Save a preJobNiazsanjiCompetency.
     *
     * @param preJobNiazsanjiCompetencyDTO the entity to save
     * @return the persisted entity
     */
    PreJobNiazsanjiCompetencyDTO save(PreJobNiazsanjiCompetencyDTO preJobNiazsanjiCompetencyDTO);
    PreJobNiazsanjiCompetencyDTO saveBulk(PreJobNiazsanjiCompetencyDTO[] preJobNiazsanjiCompetencyDTOs);

    /**
     * Get all the preJobNiazsanjiCompetencies.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PreJobNiazsanjiCompetencyDTO> findAll(Pageable pageable);

    /**
     * Get all the PreJobNiazsanjiCompetency with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<PreJobNiazsanjiCompetencyDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" preJobNiazsanjiCompetency.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PreJobNiazsanjiCompetencyDTO> findOne(Long id);

    /**
     * Delete the "id" preJobNiazsanjiCompetency.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
