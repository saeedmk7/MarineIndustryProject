package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.TeacherCriteriaGroupDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing TeacherCriteriaGroup.
 */
public interface TeacherCriteriaGroupService {

    /**
     * Save a teacherCriteriaGroup.
     *
     * @param teacherCriteriaGroupDTO the entity to save
     * @return the persisted entity
     */
    TeacherCriteriaGroupDTO save(TeacherCriteriaGroupDTO teacherCriteriaGroupDTO);

    /**
     * Get all the teacherCriteriaGroups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TeacherCriteriaGroupDTO> findAll(Pageable pageable);


    /**
     * Get the "id" teacherCriteriaGroup.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TeacherCriteriaGroupDTO> findOne(Long id);

    /**
     * Delete the "id" teacherCriteriaGroup.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
