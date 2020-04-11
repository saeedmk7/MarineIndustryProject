package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.NiazsanjiPersonGradeScoreDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing NiazsanjiPersonGradeScore.
 */
public interface NiazsanjiPersonGradeScoreService {

    /**
     * Save a niazsanjiPersonGradeScore.
     *
     * @param niazsanjiPersonGradeScoreDTO the entity to save
     * @return the persisted entity
     */
    NiazsanjiPersonGradeScoreDTO save(NiazsanjiPersonGradeScoreDTO niazsanjiPersonGradeScoreDTO);

    /**
     * Get all the niazsanjiPersonGradeScores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NiazsanjiPersonGradeScoreDTO> findAll(Pageable pageable);


    /**
     * Get the "id" niazsanjiPersonGradeScore.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NiazsanjiPersonGradeScoreDTO> findOne(Long id);

    /**
     * Delete the "id" niazsanjiPersonGradeScore.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
