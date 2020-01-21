package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.InstructionAuthorityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing InstructionAuthority.
 */
public interface InstructionAuthorityService {

    /**
     * Save a instructionAuthority.
     *
     * @param instructionAuthorityDTO the entity to save
     * @return the persisted entity
     */
    InstructionAuthorityDTO save(InstructionAuthorityDTO instructionAuthorityDTO);

    /**
     * Get all the instructionAuthorities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<InstructionAuthorityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" instructionAuthority.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<InstructionAuthorityDTO> findOne(Long id);

    /**
     * Delete the "id" instructionAuthority.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
