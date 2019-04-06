package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.SecurityLevelDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SecurityLevel.
 */
public interface SecurityLevelService {

    /**
     * Save a securityLevel.
     *
     * @param securityLevelDTO the entity to save
     * @return the persisted entity
     */
    SecurityLevelDTO save(SecurityLevelDTO securityLevelDTO);

    /**
     * Get all the securityLevels.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SecurityLevelDTO> findAll(Pageable pageable);


    /**
     * Get the "id" securityLevel.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SecurityLevelDTO> findOne(Long id);

    /**
     * Delete the "id" securityLevel.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
