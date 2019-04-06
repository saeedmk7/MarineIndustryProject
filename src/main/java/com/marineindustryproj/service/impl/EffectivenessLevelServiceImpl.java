package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.EffectivenessLevelService;
import com.marineindustryproj.domain.EffectivenessLevel;
import com.marineindustryproj.repository.EffectivenessLevelRepository;
import com.marineindustryproj.service.dto.EffectivenessLevelDTO;
import com.marineindustryproj.service.mapper.EffectivenessLevelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EffectivenessLevel.
 */
@Service
@Transactional
public class EffectivenessLevelServiceImpl implements EffectivenessLevelService {

    private final Logger log = LoggerFactory.getLogger(EffectivenessLevelServiceImpl.class);

    private final EffectivenessLevelRepository effectivenessLevelRepository;

    private final EffectivenessLevelMapper effectivenessLevelMapper;

    public EffectivenessLevelServiceImpl(EffectivenessLevelRepository effectivenessLevelRepository, EffectivenessLevelMapper effectivenessLevelMapper) {
        this.effectivenessLevelRepository = effectivenessLevelRepository;
        this.effectivenessLevelMapper = effectivenessLevelMapper;
    }

    /**
     * Save a effectivenessLevel.
     *
     * @param effectivenessLevelDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EffectivenessLevelDTO save(EffectivenessLevelDTO effectivenessLevelDTO) {
        log.debug("Request to save EffectivenessLevel : {}", effectivenessLevelDTO);

        EffectivenessLevel effectivenessLevel = effectivenessLevelMapper.toEntity(effectivenessLevelDTO);
        effectivenessLevel = effectivenessLevelRepository.save(effectivenessLevel);
        return effectivenessLevelMapper.toDto(effectivenessLevel);
    }

    /**
     * Get all the effectivenessLevels.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EffectivenessLevelDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EffectivenessLevels");
        return effectivenessLevelRepository.findAll(pageable)
            .map(effectivenessLevelMapper::toDto);
    }


    /**
     * Get one effectivenessLevel by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EffectivenessLevelDTO> findOne(Long id) {
        log.debug("Request to get EffectivenessLevel : {}", id);
        return effectivenessLevelRepository.findById(id)
            .map(effectivenessLevelMapper::toDto);
    }

    /**
     * Delete the effectivenessLevel by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EffectivenessLevel : {}", id);
        effectivenessLevelRepository.deleteById(id);
    }
}
