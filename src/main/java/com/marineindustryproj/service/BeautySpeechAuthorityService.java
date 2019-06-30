package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.BeautySpeechAuthorityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing BeautySpeechAuthority.
 */
public interface BeautySpeechAuthorityService {

    /**
     * Save a beautySpeechAuthority.
     *
     * @param beautySpeechAuthorityDTO the entity to save
     * @return the persisted entity
     */
    BeautySpeechAuthorityDTO save(BeautySpeechAuthorityDTO beautySpeechAuthorityDTO);

    /**
     * Get all the beautySpeechAuthorities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BeautySpeechAuthorityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" beautySpeechAuthority.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BeautySpeechAuthorityDTO> findOne(Long id);

    /**
     * Delete the "id" beautySpeechAuthority.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
    void deleteByBeautySpeechId(Long id);
}
