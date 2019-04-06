package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.SkillableLevelOfSkillService;
import com.marineindustryproj.domain.SkillableLevelOfSkill;
import com.marineindustryproj.repository.SkillableLevelOfSkillRepository;
import com.marineindustryproj.service.dto.SkillableLevelOfSkillDTO;
import com.marineindustryproj.service.mapper.SkillableLevelOfSkillMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing SkillableLevelOfSkill.
 */
@Service
@Transactional
public class SkillableLevelOfSkillServiceImpl implements SkillableLevelOfSkillService {

    private final Logger log = LoggerFactory.getLogger(SkillableLevelOfSkillServiceImpl.class);

    private final SkillableLevelOfSkillRepository skillableLevelOfSkillRepository;

    private final SkillableLevelOfSkillMapper skillableLevelOfSkillMapper;

    public SkillableLevelOfSkillServiceImpl(SkillableLevelOfSkillRepository skillableLevelOfSkillRepository, SkillableLevelOfSkillMapper skillableLevelOfSkillMapper) {
        this.skillableLevelOfSkillRepository = skillableLevelOfSkillRepository;
        this.skillableLevelOfSkillMapper = skillableLevelOfSkillMapper;
    }

    /**
     * Save a skillableLevelOfSkill.
     *
     * @param skillableLevelOfSkillDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SkillableLevelOfSkillDTO save(SkillableLevelOfSkillDTO skillableLevelOfSkillDTO) {
        log.debug("Request to save SkillableLevelOfSkill : {}", skillableLevelOfSkillDTO);

        SkillableLevelOfSkill skillableLevelOfSkill = skillableLevelOfSkillMapper.toEntity(skillableLevelOfSkillDTO);
        skillableLevelOfSkill = skillableLevelOfSkillRepository.save(skillableLevelOfSkill);
        return skillableLevelOfSkillMapper.toDto(skillableLevelOfSkill);
    }

    /**
     * Get all the skillableLevelOfSkills.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SkillableLevelOfSkillDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SkillableLevelOfSkills");
        return skillableLevelOfSkillRepository.findAll(pageable)
            .map(skillableLevelOfSkillMapper::toDto);
    }


    /**
     * Get one skillableLevelOfSkill by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SkillableLevelOfSkillDTO> findOne(Long id) {
        log.debug("Request to get SkillableLevelOfSkill : {}", id);
        return skillableLevelOfSkillRepository.findById(id)
            .map(skillableLevelOfSkillMapper::toDto);
    }

    /**
     * Delete the skillableLevelOfSkill by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SkillableLevelOfSkill : {}", id);
        skillableLevelOfSkillRepository.deleteById(id);
    }
}
