package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.RunPhaseService;
import com.marineindustryproj.domain.RunPhase;
import com.marineindustryproj.repository.RunPhaseRepository;
import com.marineindustryproj.service.dto.RunPhaseDTO;
import com.marineindustryproj.service.mapper.RunPhaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing RunPhase.
 */
@Service
@Transactional
public class RunPhaseServiceImpl implements RunPhaseService {

    private final Logger log = LoggerFactory.getLogger(RunPhaseServiceImpl.class);

    private final RunPhaseRepository runPhaseRepository;

    private final RunPhaseMapper runPhaseMapper;

    public RunPhaseServiceImpl(RunPhaseRepository runPhaseRepository, RunPhaseMapper runPhaseMapper) {
        this.runPhaseRepository = runPhaseRepository;
        this.runPhaseMapper = runPhaseMapper;
    }

    /**
     * Save a runPhase.
     *
     * @param runPhaseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RunPhaseDTO save(RunPhaseDTO runPhaseDTO) {
        log.debug("Request to save RunPhase : {}", runPhaseDTO);

        RunPhase runPhase = runPhaseMapper.toEntity(runPhaseDTO);
        runPhase = runPhaseRepository.save(runPhase);
        return runPhaseMapper.toDto(runPhase);
    }

    /**
     * Get all the runPhases.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RunPhaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RunPhases");
        return runPhaseRepository.findAll(pageable)
            .map(runPhaseMapper::toDto);
    }

    /**
     * Get all the RunPhase with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<RunPhaseDTO> findAllWithEagerRelationships(Pageable pageable) {
        return runPhaseRepository.findAllWithEagerRelationships(pageable).map(runPhaseMapper::toDto);
    }
    

    /**
     * Get one runPhase by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RunPhaseDTO> findOne(Long id) {
        log.debug("Request to get RunPhase : {}", id);
        return runPhaseRepository.findOneWithEagerRelationships(id)
            .map(runPhaseMapper::toDto);
    }

    /**
     * Delete the runPhase by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RunPhase : {}", id);
        runPhaseRepository.deleteById(id);
    }
}
