package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.NavBarItemAuthorityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing NavBarItemAuthority.
 */
public interface NavBarItemAuthorityService {

    /**
     * Save a navBarItemAuthority.
     *
     * @param navBarItemAuthorityDTO the entity to save
     * @return the persisted entity
     */
    NavBarItemAuthorityDTO save(NavBarItemAuthorityDTO navBarItemAuthorityDTO);

    /**
     * Save a list of navBarItemAuthority.
     *
     * @param navBarItemAuthorityDTOs the entity to save
     * @return the persisted entity
     */
    NavBarItemAuthorityDTO bulkSave(List<NavBarItemAuthorityDTO> navBarItemAuthorityDTOs);

    /**
     * Get all the navBarItemAuthorities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<NavBarItemAuthorityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" navBarItemAuthority.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<NavBarItemAuthorityDTO> findOne(Long id);

    /**
     * Delete the "id" navBarItemAuthority.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Delete the "authority" navBarItemAuthority.
     *
     * @param authority the authority of the entity
     */
    void delete(String authority);
}
