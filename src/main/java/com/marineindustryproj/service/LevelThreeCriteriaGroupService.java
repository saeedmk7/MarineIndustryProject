package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.LevelThreeCriteriaGroupDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing LevelThreeCriteriaGroup.
 */
public interface LevelThreeCriteriaGroupService {

    /**
     * Save a levelThreeCriteriaGroup.
     *
     * @param levelThreeCriteriaGroupDTO the entity to save
     * @return the persisted entity
     */
    LevelThreeCriteriaGroupDTO save(LevelThreeCriteriaGroupDTO levelThreeCriteriaGroupDTO);

    /**
     * Get all the levelThreeCriteriaGroups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LevelThreeCriteriaGroupDTO> findAll(Pageable pageable);


    /**
     * Get the "id" levelThreeCriteriaGroup.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LevelThreeCriteriaGroupDTO> findOne(Long id);

    /**
     * Delete the "id" levelThreeCriteriaGroup.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
