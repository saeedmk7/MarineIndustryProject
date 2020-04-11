package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.TeacherGradeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TeacherGrade.
 */
public interface TeacherGradeService {

    /**
     * Save a teacherGrade.
     *
     * @param teacherGradeDTO the entity to save
     * @return the persisted entity
     */
    TeacherGradeDTO save(TeacherGradeDTO teacherGradeDTO);

    TeacherGradeDTO saveWithScore(TeacherGradeDTO teacherGradeDTO);

    /**
     * Get all the teacherGrades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TeacherGradeDTO> findAll(Pageable pageable);

    /**
     * Get all the TeacherGrade with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<TeacherGradeDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" teacherGrade.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TeacherGradeDTO> findOne(Long id);

    /**
     * Delete the "id" teacherGrade.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
