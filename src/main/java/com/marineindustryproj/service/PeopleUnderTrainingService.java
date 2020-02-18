package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.PeopleUnderTrainingDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing PeopleUnderTraining.
 */
public interface PeopleUnderTrainingService {

    /**
     * Save a peopleUnderTraining.
     *
     * @param peopleUnderTrainingDTO the entity to save
     * @return the persisted entity
     */
    PeopleUnderTrainingDTO save(PeopleUnderTrainingDTO peopleUnderTrainingDTO);

    /**
     * Get all the peopleUnderTrainings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PeopleUnderTrainingDTO> findAll(Pageable pageable);


    /**
     * Get the "id" peopleUnderTraining.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PeopleUnderTrainingDTO> findOne(Long id);

    /**
     * Delete the "id" peopleUnderTraining.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
