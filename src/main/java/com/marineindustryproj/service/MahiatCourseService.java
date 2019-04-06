package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.MahiatCourseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing MahiatCourse.
 */
public interface MahiatCourseService {

    /**
     * Save a mahiatCourse.
     *
     * @param mahiatCourseDTO the entity to save
     * @return the persisted entity
     */
    MahiatCourseDTO save(MahiatCourseDTO mahiatCourseDTO);

    /**
     * Get all the mahiatCourses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MahiatCourseDTO> findAll(Pageable pageable);


    /**
     * Get the "id" mahiatCourse.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MahiatCourseDTO> findOne(Long id);

    /**
     * Delete the "id" mahiatCourse.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
