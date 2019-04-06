package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.EffectivenessIndexService;
import com.marineindustryproj.domain.EffectivenessIndex;
import com.marineindustryproj.repository.EffectivenessIndexRepository;
import com.marineindustryproj.service.dto.EffectivenessIndexDTO;
import com.marineindustryproj.service.mapper.EffectivenessIndexMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EffectivenessIndex.
 */
@Service
@Transactional
public class EffectivenessIndexServiceImpl implements EffectivenessIndexService {

    private final Logger log = LoggerFactory.getLogger(EffectivenessIndexServiceImpl.class);

    private final EffectivenessIndexRepository effectivenessIndexRepository;

    private final EffectivenessIndexMapper effectivenessIndexMapper;

    public EffectivenessIndexServiceImpl(EffectivenessIndexRepository effectivenessIndexRepository, EffectivenessIndexMapper effectivenessIndexMapper) {
        this.effectivenessIndexRepository = effectivenessIndexRepository;
        this.effectivenessIndexMapper = effectivenessIndexMapper;
    }

    /**
     * Save a effectivenessIndex.
     *
     * @param effectivenessIndexDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EffectivenessIndexDTO save(EffectivenessIndexDTO effectivenessIndexDTO) {
        log.debug("Request to save EffectivenessIndex : {}", effectivenessIndexDTO);

        EffectivenessIndex effectivenessIndex = effectivenessIndexMapper.toEntity(effectivenessIndexDTO);
        effectivenessIndex = effectivenessIndexRepository.save(effectivenessIndex);
        return effectivenessIndexMapper.toDto(effectivenessIndex);
    }

    /**
     * Get all the effectivenessIndices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EffectivenessIndexDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EffectivenessIndices");
        return effectivenessIndexRepository.findAll(pageable)
            .map(effectivenessIndexMapper::toDto);
    }


    /**
     * Get one effectivenessIndex by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EffectivenessIndexDTO> findOne(Long id) {
        log.debug("Request to get EffectivenessIndex : {}", id);
        return effectivenessIndexRepository.findById(id)
            .map(effectivenessIndexMapper::toDto);
    }

    /**
     * Delete the effectivenessIndex by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EffectivenessIndex : {}", id);
        effectivenessIndexRepository.deleteById(id);
    }
}
