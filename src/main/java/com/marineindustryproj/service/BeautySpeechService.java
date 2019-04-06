package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.BeautySpeechDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing BeautySpeech.
 */
public interface BeautySpeechService {

    /**
     * Save a beautySpeech.
     *
     * @param beautySpeechDTO the entity to save
     * @return the persisted entity
     */
    BeautySpeechDTO save(BeautySpeechDTO beautySpeechDTO);

    /**
     * Get all the beautySpeeches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BeautySpeechDTO> findAll(Pageable pageable);


    /**
     * Get the "id" beautySpeech.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BeautySpeechDTO> findOne(Long id);

    /**
     * Delete the "id" beautySpeech.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
