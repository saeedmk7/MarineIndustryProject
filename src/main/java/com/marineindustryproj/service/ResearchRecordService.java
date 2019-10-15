package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.ResearchRecordDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ResearchRecord.
 */
public interface ResearchRecordService {

    /**
     * Save a researchRecord.
     *
     * @param researchRecordDTO the entity to save
     * @return the persisted entity
     */
    ResearchRecordDTO save(ResearchRecordDTO researchRecordDTO);

    /**
     * Get all the researchRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ResearchRecordDTO> findAll(Pageable pageable);


    /**
     * Get the "id" researchRecord.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ResearchRecordDTO> findOne(Long id);

    /**
     * Delete the "id" researchRecord.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
