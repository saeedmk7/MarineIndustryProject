package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.JamHelpAuthorityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing JamHelpAuthority.
 */
public interface JamHelpAuthorityService {

    /**
     * Save a jamHelpAuthority.
     *
     * @param jamHelpAuthorityDTO the entity to save
     * @return the persisted entity
     */
    JamHelpAuthorityDTO save(JamHelpAuthorityDTO jamHelpAuthorityDTO);

    /**
     * Get all the jamHelpAuthorities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<JamHelpAuthorityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" jamHelpAuthority.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<JamHelpAuthorityDTO> findOne(Long id);

    /**
     * Delete the "id" jamHelpAuthority.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    void deleteByJamHelpId(Long id);
}
