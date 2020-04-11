package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.NiazsanjiPersonGradeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing NiazsanjiPersonGrade.
 */
public interface NiazsanjiPersonGradeService {

    /**
     * Save a niazsanjiPersonGrade.
     *
     * @param niazsanjiPersonGradeDTO the entity to save
     * @return the persisted entity
     */
    NiazsanjiPersonGradeDTO save(NiazsanjiPersonGradeDTO niazsanjiPersonGradeDTO);

    NiazsanjiPersonGradeDTO saveWithScore(NiazsanjiPersonGradeDTO niazsanjiPersonGradeDTO);

    Boolean completeLevel(Long finalNiazsanjiReportId) throws Exception;

    /**
     * Get all the niazsanjiPersonGrades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NiazsanjiPersonGradeDTO> findAll(Pageable pageable);

    /**
     * Get all the NiazsanjiPersonGrade with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<NiazsanjiPersonGradeDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" niazsanjiPersonGrade.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NiazsanjiPersonGradeDTO> findOne(Long id);

    /**
     * Delete the "id" niazsanjiPersonGrade.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
