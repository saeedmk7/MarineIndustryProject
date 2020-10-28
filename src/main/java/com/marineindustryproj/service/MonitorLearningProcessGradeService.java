package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.MonitorLearningProcessGradeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing MonitorLearningProcessGrade.
 */
public interface MonitorLearningProcessGradeService {

    /**
     * Save a monitorLearningProcessGrade.
     *
     * @param monitorLearningProcessGradeDTO the entity to save
     * @return the persisted entity
     */
    MonitorLearningProcessGradeDTO save(MonitorLearningProcessGradeDTO monitorLearningProcessGradeDTO);

    /**
     * Get all the monitorLearningProcessGrades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MonitorLearningProcessGradeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" monitorLearningProcessGrade.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MonitorLearningProcessGradeDTO> findOne(Long id);

    /**
     * Delete the "id" monitorLearningProcessGrade.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
