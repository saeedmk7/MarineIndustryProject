package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.FixCompetencyDeficiencyDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing FixCompetencyDeficiency.
 */
public interface FixCompetencyDeficiencyService {

    /**
     * Save a fixCompetencyDeficiency.
     *
     * @param fixCompetencyDeficiencyDTO the entity to save
     * @return the persisted entity
     */
    FixCompetencyDeficiencyDTO save(FixCompetencyDeficiencyDTO fixCompetencyDeficiencyDTO);

    /**
     * Get all the fixCompetencyDeficiencies.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FixCompetencyDeficiencyDTO> findAll(Pageable pageable);


    /**
     * Get the "id" fixCompetencyDeficiency.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FixCompetencyDeficiencyDTO> findOne(Long id);

    /**
     * Delete the "id" fixCompetencyDeficiency.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
