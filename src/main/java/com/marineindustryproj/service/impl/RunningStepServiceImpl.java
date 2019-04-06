package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.RunningStepService;
import com.marineindustryproj.domain.RunningStep;
import com.marineindustryproj.repository.RunningStepRepository;
import com.marineindustryproj.service.dto.RunningStepDTO;
import com.marineindustryproj.service.mapper.RunningStepMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing RunningStep.
 */
@Service
@Transactional
public class RunningStepServiceImpl implements RunningStepService {

    private final Logger log = LoggerFactory.getLogger(RunningStepServiceImpl.class);

    private final RunningStepRepository runningStepRepository;

    private final RunningStepMapper runningStepMapper;

    public RunningStepServiceImpl(RunningStepRepository runningStepRepository, RunningStepMapper runningStepMapper) {
        this.runningStepRepository = runningStepRepository;
        this.runningStepMapper = runningStepMapper;
    }

    /**
     * Save a runningStep.
     *
     * @param runningStepDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RunningStepDTO save(RunningStepDTO runningStepDTO) {
        log.debug("Request to save RunningStep : {}", runningStepDTO);

        RunningStep runningStep = runningStepMapper.toEntity(runningStepDTO);
        runningStep = runningStepRepository.save(runningStep);
        return runningStepMapper.toDto(runningStep);
    }

    /**
     * Get all the runningSteps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RunningStepDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RunningSteps");
        return runningStepRepository.findAll(pageable)
            .map(runningStepMapper::toDto);
    }


    /**
     * Get one runningStep by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RunningStepDTO> findOne(Long id) {
        log.debug("Request to get RunningStep : {}", id);
        return runningStepRepository.findById(id)
            .map(runningStepMapper::toDto);
    }

    /**
     * Delete the runningStep by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RunningStep : {}", id);
        runningStepRepository.deleteById(id);
    }
}
