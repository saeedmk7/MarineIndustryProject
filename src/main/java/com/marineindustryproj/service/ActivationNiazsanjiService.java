package com.marineindustryproj.service;

import com.marineindustryproj.domain.enumeration.NiazSanjiSource;
import com.marineindustryproj.service.dto.ActivationNiazsanjiDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ActivationNiazsanji.
 */
public interface ActivationNiazsanjiService {

    /**
     * Save a activationNiazsanji.
     *
     * @param activationNiazsanjiDTO the entity to save
     * @return the persisted entity
     */
    ActivationNiazsanjiDTO save(ActivationNiazsanjiDTO activationNiazsanjiDTO);

    /**
     * Get all the activationNiazsanjis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ActivationNiazsanjiDTO> findAll(Pageable pageable);


    /**
     * Get the "id" activationNiazsanji.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ActivationNiazsanjiDTO> findOne(Long id);

    /**
     * Delete the "id" activationNiazsanji.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
