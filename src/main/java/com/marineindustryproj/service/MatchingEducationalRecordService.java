package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.MatchingEducationalRecordDTO;

import com.marineindustryproj.service.dto.customs.MatchingEducationalRecordModels.MatchingEducationalRecordSaveDataModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing MatchingEducationalRecord.
 */
public interface MatchingEducationalRecordService {

    /**
     * Save a matchingEducationalRecord.
     *
     * @param matchingEducationalRecordDTO the entity to save
     * @return the persisted entity
     */
    MatchingEducationalRecordDTO save(MatchingEducationalRecordDTO matchingEducationalRecordDTO);

    MatchingEducationalRecordDTO saveDataModel(MatchingEducationalRecordSaveDataModel matchingEducationalRecordSaveDataModel);

    /**
     * Get all the matchingEducationalRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MatchingEducationalRecordDTO> findAll(Pageable pageable);

    /**
     * Get all the MatchingEducationalRecord with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<MatchingEducationalRecordDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" matchingEducationalRecord.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MatchingEducationalRecordDTO> findOne(Long id);

    /**
     * Delete the "id" matchingEducationalRecord.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
