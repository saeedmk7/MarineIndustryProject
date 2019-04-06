package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.ConditionsOfParticipantDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ConditionsOfParticipant.
 */
public interface ConditionsOfParticipantService {

    /**
     * Save a conditionsOfParticipant.
     *
     * @param conditionsOfParticipantDTO the entity to save
     * @return the persisted entity
     */
    ConditionsOfParticipantDTO save(ConditionsOfParticipantDTO conditionsOfParticipantDTO);

    /**
     * Get all the conditionsOfParticipants.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ConditionsOfParticipantDTO> findAll(Pageable pageable);


    /**
     * Get the "id" conditionsOfParticipant.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ConditionsOfParticipantDTO> findOne(Long id);

    /**
     * Delete the "id" conditionsOfParticipant.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
