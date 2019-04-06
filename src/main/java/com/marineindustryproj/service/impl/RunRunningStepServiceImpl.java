package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.RunRunningStepService;
import com.marineindustryproj.domain.RunRunningStep;
import com.marineindustryproj.repository.RunRunningStepRepository;
import com.marineindustryproj.service.dto.RunRunningStepDTO;
import com.marineindustryproj.service.mapper.RunRunningStepMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing RunRunningStep.
 */
@Service
@Transactional
public class RunRunningStepServiceImpl implements RunRunningStepService {

    private final Logger log = LoggerFactory.getLogger(RunRunningStepServiceImpl.class);

    private final RunRunningStepRepository runRunningStepRepository;

    private final RunRunningStepMapper runRunningStepMapper;

    public RunRunningStepServiceImpl(RunRunningStepRepository runRunningStepRepository, RunRunningStepMapper runRunningStepMapper) {
        this.runRunningStepRepository = runRunningStepRepository;
        this.runRunningStepMapper = runRunningStepMapper;
    }

    /**
     * Save a runRunningStep.
     *
     * @param runRunningStepDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RunRunningStepDTO save(RunRunningStepDTO runRunningStepDTO) {
        log.debug("Request to save RunRunningStep : {}", runRunningStepDTO);

        RunRunningStep runRunningStep = runRunningStepMapper.toEntity(runRunningStepDTO);
        runRunningStep = runRunningStepRepository.save(runRunningStep);
        return runRunningStepMapper.toDto(runRunningStep);
    }

    /**
     * Get all the runRunningSteps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RunRunningStepDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RunRunningSteps");
        return runRunningStepRepository.findAll(pageable)
            .map(runRunningStepMapper::toDto);
    }


    /**
     * Get one runRunningStep by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RunRunningStepDTO> findOne(Long id) {
        log.debug("Request to get RunRunningStep : {}", id);
        return runRunningStepRepository.findById(id)
            .map(runRunningStepMapper::toDto);
    }

    /**
     * Delete the runRunningStep by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RunRunningStep : {}", id);
        runRunningStepRepository.deleteById(id);
    }
}
