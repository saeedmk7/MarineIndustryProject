package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.TeachingRecordDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TeachingRecord.
 */
public interface TeachingRecordService {

    /**
     * Save a teachingRecord.
     *
     * @param teachingRecordDTO the entity to save
     * @return the persisted entity
     */
    TeachingRecordDTO save(TeachingRecordDTO teachingRecordDTO);

    /**
     * Get all the teachingRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TeachingRecordDTO> findAll(Pageable pageable);


    /**
     * Get the "id" teachingRecord.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TeachingRecordDTO> findOne(Long id);

    /**
     * Delete the "id" teachingRecord.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
