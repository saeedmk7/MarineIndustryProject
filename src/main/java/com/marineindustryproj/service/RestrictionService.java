package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.RestrictionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Restriction.
 */
public interface RestrictionService {

    /**
     * Save a restriction.
     *
     * @param restrictionDTO the entity to save
     * @return the persisted entity
     */
    RestrictionDTO save(RestrictionDTO restrictionDTO);

    /**
     * Get all the restrictions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RestrictionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" restriction.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RestrictionDTO> findOne(Long id);

    /**
     * Delete the "id" restriction.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
