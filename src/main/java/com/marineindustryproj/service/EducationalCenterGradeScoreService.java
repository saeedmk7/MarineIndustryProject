package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.EducationalCenterGradeScoreDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing EducationalCenterGradeScore.
 */
public interface EducationalCenterGradeScoreService {

    /**
     * Save a educationalCenterGradeScore.
     *
     * @param educationalCenterGradeScoreDTO the entity to save
     * @return the persisted entity
     */
    EducationalCenterGradeScoreDTO save(EducationalCenterGradeScoreDTO educationalCenterGradeScoreDTO);

    /**
     * Get all the educationalCenterGradeScores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<EducationalCenterGradeScoreDTO> findAll(Pageable pageable);


    /**
     * Get the "id" educationalCenterGradeScore.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EducationalCenterGradeScoreDTO> findOne(Long id);

    /**
     * Delete the "id" educationalCenterGradeScore.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
