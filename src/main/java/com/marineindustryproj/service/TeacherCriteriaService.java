package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.TeacherCriteriaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TeacherCriteria.
 */
public interface TeacherCriteriaService {

    /**
     * Save a teacherCriteria.
     *
     * @param teacherCriteriaDTO the entity to save
     * @return the persisted entity
     */
    TeacherCriteriaDTO save(TeacherCriteriaDTO teacherCriteriaDTO);

    /**
     * Get all the teacherCriteria.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TeacherCriteriaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" teacherCriteria.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TeacherCriteriaDTO> findOne(Long id);

    /**
     * Delete the "id" teacherCriteria.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
