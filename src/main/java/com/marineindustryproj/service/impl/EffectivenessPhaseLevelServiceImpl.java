package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.EffectivenessPhaseLevelService;
import com.marineindustryproj.domain.EffectivenessPhaseLevel;
import com.marineindustryproj.repository.EffectivenessPhaseLevelRepository;
import com.marineindustryproj.service.dto.EffectivenessPhaseLevelDTO;
import com.marineindustryproj.service.mapper.EffectivenessPhaseLevelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EffectivenessPhaseLevel.
 */
@Service
@Transactional
public class EffectivenessPhaseLevelServiceImpl implements EffectivenessPhaseLevelService {

    private final Logger log = LoggerFactory.getLogger(EffectivenessPhaseLevelServiceImpl.class);

    private final EffectivenessPhaseLevelRepository effectivenessPhaseLevelRepository;

    private final EffectivenessPhaseLevelMapper effectivenessPhaseLevelMapper;

    public EffectivenessPhaseLevelServiceImpl(EffectivenessPhaseLevelRepository effectivenessPhaseLevelRepository, EffectivenessPhaseLevelMapper effectivenessPhaseLevelMapper) {
        this.effectivenessPhaseLevelRepository = effectivenessPhaseLevelRepository;
        this.effectivenessPhaseLevelMapper = effectivenessPhaseLevelMapper;
    }

    /**
     * Save a effectivenessPhaseLevel.
     *
     * @param effectivenessPhaseLevelDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EffectivenessPhaseLevelDTO save(EffectivenessPhaseLevelDTO effectivenessPhaseLevelDTO) {
        log.debug("Request to save EffectivenessPhaseLevel : {}", effectivenessPhaseLevelDTO);

        EffectivenessPhaseLevel effectivenessPhaseLevel = effectivenessPhaseLevelMapper.toEntity(effectivenessPhaseLevelDTO);
        effectivenessPhaseLevel = effectivenessPhaseLevelRepository.save(effectivenessPhaseLevel);
        return effectivenessPhaseLevelMapper.toDto(effectivenessPhaseLevel);
    }

    /**
     * Get all the effectivenessPhaseLevels.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EffectivenessPhaseLevelDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EffectivenessPhaseLevels");
        return effectivenessPhaseLevelRepository.findAll(pageable)
            .map(effectivenessPhaseLevelMapper::toDto);
    }


    /**
     * Get one effectivenessPhaseLevel by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EffectivenessPhaseLevelDTO> findOne(Long id) {
        log.debug("Request to get EffectivenessPhaseLevel : {}", id);
        return effectivenessPhaseLevelRepository.findById(id)
            .map(effectivenessPhaseLevelMapper::toDto);
    }

    /**
     * Delete the effectivenessPhaseLevel by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EffectivenessPhaseLevel : {}", id);
        effectivenessPhaseLevelRepository.deleteById(id);
    }
}
