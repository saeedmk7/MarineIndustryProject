package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.EducationalHistoryDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EducationalHistory.
 */
public interface EducationalHistoryService {

    /**
     * Save a educationalHistory.
     *
     * @param educationalHistoryDTO the entity to save
     * @return the persisted entity
     */
    EducationalHistoryDTO save(EducationalHistoryDTO educationalHistoryDTO);

    /**
     * Get all the educationalHistories.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EducationalHistoryDTO> findAll(Pageable pageable);


    /**
     * Get the "id" educationalHistory.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EducationalHistoryDTO> findOne(Long id);

    /**
     * Delete the "id" educationalHistory.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
