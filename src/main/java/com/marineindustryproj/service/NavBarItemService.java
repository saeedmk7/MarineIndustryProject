package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.NavBarItemDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing NavBarItem.
 */
public interface NavBarItemService {

    /**
     * Save a navBarItem.
     *
     * @param navBarItemDTO the entity to save
     * @return the persisted entity
     */
    NavBarItemDTO save(NavBarItemDTO navBarItemDTO);

    /**
     * Get all the navBarItems.
     *
     * @return the list of entities
     */
    List<NavBarItemDTO> findAll();


    /**
     * Get the "id" navBarItem.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NavBarItemDTO> findOne(Long id);

    /**
     * Delete the "id" navBarItem.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
