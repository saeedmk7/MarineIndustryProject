package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.SkillableLevelOfSkillDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing SkillableLevelOfSkill.
 */
public interface SkillableLevelOfSkillService {

    /**
     * Save a skillableLevelOfSkill.
     *
     * @param skillableLevelOfSkillDTO the entity to save
     * @return the persisted entity
     */
    SkillableLevelOfSkillDTO save(SkillableLevelOfSkillDTO skillableLevelOfSkillDTO);

    /**
     * Get all the skillableLevelOfSkills.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SkillableLevelOfSkillDTO> findAll(Pageable pageable);


    /**
     * Get the "id" skillableLevelOfSkill.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SkillableLevelOfSkillDTO> findOne(Long id);

    /**
     * Delete the "id" skillableLevelOfSkill.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
