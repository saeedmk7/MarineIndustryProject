package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.CourseLocationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CourseLocation.
 */
public interface CourseLocationService {

    /**
     * Save a courseLocation.
     *
     * @param courseLocationDTO the entity to save
     * @return the persisted entity
     */
    CourseLocationDTO save(CourseLocationDTO courseLocationDTO);

    /**
     * Get all the courseLocations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CourseLocationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" courseLocation.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CourseLocationDTO> findOne(Long id);

    /**
     * Delete the "id" courseLocation.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
