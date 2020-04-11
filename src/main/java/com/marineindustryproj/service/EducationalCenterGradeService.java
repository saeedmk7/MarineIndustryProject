package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.EducationalCenterGradeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EducationalCenterGrade.
 */
public interface EducationalCenterGradeService {

    /**
     * Save a educationalCenterGrade.
     *
     * @param educationalCenterGradeDTO the entity to save
     * @return the persisted entity
     */
    EducationalCenterGradeDTO save(EducationalCenterGradeDTO educationalCenterGradeDTO);

    EducationalCenterGradeDTO saveWithScore(EducationalCenterGradeDTO educationalCenterGradeDTO);

    /**
     * Get all the educationalCenterGrades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EducationalCenterGradeDTO> findAll(Pageable pageable);

    /**
     * Get all the EducationalCenterGrade with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<EducationalCenterGradeDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" educationalCenterGrade.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EducationalCenterGradeDTO> findOne(Long id);

    /**
     * Delete the "id" educationalCenterGrade.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
