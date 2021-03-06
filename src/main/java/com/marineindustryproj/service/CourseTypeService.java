package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.CourseTypeDTO;

import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing CourseType.
 */
public interface CourseTypeService {

    /**
     * Save a courseType.
     *
     * @param courseTypeDTO the entity to save
     * @return the persisted entity
     */
    CourseTypeDTO save(CourseTypeDTO courseTypeDTO);

    /**
     * Get all the courseTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CourseTypeDTO> findAll(Pageable pageable);

    /**
     * Get all the courseTypes.
     *
     * @return the list of entities
     */
    List<CourseTypeDTO> findAll();


    /**
     * Get the "id" courseType.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CourseTypeDTO> findOne(Long id);

    /**
     * Delete the "id" courseType.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
