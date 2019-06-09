package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.EducationalRecordDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EducationalRecord.
 */
public interface EducationalRecordService {

    /**
     * Save a educationalRecord.
     *
     * @param educationalRecordDTO the entity to save
     * @return the persisted entity
     */
    EducationalRecordDTO save(EducationalRecordDTO educationalRecordDTO);

    /**
     * Get all the educationalRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EducationalRecordDTO> findAll(Pageable pageable);


    /**
     * Get the "id" educationalRecord.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EducationalRecordDTO> findOne(Long id);

    /**
     * Delete the "id" educationalRecord.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
