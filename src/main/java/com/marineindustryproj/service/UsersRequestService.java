package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.UsersRequestDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing UsersRequest.
 */
public interface UsersRequestService {

    /**
     * Save a usersRequest.
     *
     * @param usersRequestDTO the entity to save
     * @return the persisted entity
     */
    UsersRequestDTO save(UsersRequestDTO usersRequestDTO);

    /**
     * Get all the usersRequests.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UsersRequestDTO> findAll(Pageable pageable);

    /**
     * Get all the UsersRequest with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    Page<UsersRequestDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" usersRequest.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UsersRequestDTO> findOne(Long id);

    /**
     * Delete the "id" usersRequest.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
