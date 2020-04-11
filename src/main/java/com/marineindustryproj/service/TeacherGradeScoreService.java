package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.TeacherGradeScoreDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TeacherGradeScore.
 */
public interface TeacherGradeScoreService {

    /**
     * Save a teacherGradeScore.
     *
     * @param teacherGradeScoreDTO the entity to save
     * @return the persisted entity
     */
    TeacherGradeScoreDTO save(TeacherGradeScoreDTO teacherGradeScoreDTO);

    /**
     * Get all the teacherGradeScores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TeacherGradeScoreDTO> findAll(Pageable pageable);


    /**
     * Get the "id" teacherGradeScore.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TeacherGradeScoreDTO> findOne(Long id);

    /**
     * Delete the "id" teacherGradeScore.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
