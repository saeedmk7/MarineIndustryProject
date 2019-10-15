package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.ForceRunningPercentService;
import com.marineindustryproj.domain.ForceRunningPercent;
import com.marineindustryproj.repository.ForceRunningPercentRepository;
import com.marineindustryproj.service.dto.ForceRunningPercentDTO;
import com.marineindustryproj.service.mapper.ForceRunningPercentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ForceRunningPercent.
 */
@Service
@Transactional
public class ForceRunningPercentServiceImpl implements ForceRunningPercentService {

    private final Logger log = LoggerFactory.getLogger(ForceRunningPercentServiceImpl.class);

    private final ForceRunningPercentRepository forceRunningPercentRepository;

    private final ForceRunningPercentMapper forceRunningPercentMapper;

    public ForceRunningPercentServiceImpl(ForceRunningPercentRepository forceRunningPercentRepository, ForceRunningPercentMapper forceRunningPercentMapper) {
        this.forceRunningPercentRepository = forceRunningPercentRepository;
        this.forceRunningPercentMapper = forceRunningPercentMapper;
    }

    /**
     * Save a forceRunningPercent.
     *
     * @param forceRunningPercentDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ForceRunningPercentDTO save(ForceRunningPercentDTO forceRunningPercentDTO) {
        log.debug("Request to save ForceRunningPercent : {}", forceRunningPercentDTO);

        ForceRunningPercent forceRunningPercent = forceRunningPercentMapper.toEntity(forceRunningPercentDTO);
        forceRunningPercent = forceRunningPercentRepository.save(forceRunningPercent);
        return forceRunningPercentMapper.toDto(forceRunningPercent);
    }

    /**
     * Get all the forceRunningPercents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ForceRunningPercentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ForceRunningPercents");
        return forceRunningPercentRepository.findAll(pageable)
            .map(forceRunningPercentMapper::toDto);
    }

    /**
     * Get all the ForceRunningPercent with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<ForceRunningPercentDTO> findAllWithEagerRelationships(Pageable pageable) {
        return forceRunningPercentRepository.findAllWithEagerRelationships(pageable).map(forceRunningPercentMapper::toDto);
    }
    

    /**
     * Get one forceRunningPercent by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ForceRunningPercentDTO> findOne(Long id) {
        log.debug("Request to get ForceRunningPercent : {}", id);
        return forceRunningPercentRepository.findOneWithEagerRelationships(id)
            .map(forceRunningPercentMapper::toDto);
    }

    /**
     * Delete the forceRunningPercent by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ForceRunningPercent : {}", id);
        forceRunningPercentRepository.deleteById(id);
    }
}
