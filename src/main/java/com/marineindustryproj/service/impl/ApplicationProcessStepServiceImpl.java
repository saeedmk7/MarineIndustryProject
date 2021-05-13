package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.ApplicationProcessStepService;
import com.marineindustryproj.domain.ApplicationProcessStep;
import com.marineindustryproj.repository.ApplicationProcessStepRepository;
import com.marineindustryproj.service.dto.ApplicationProcessStepDTO;
import com.marineindustryproj.service.mapper.ApplicationProcessStepMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ApplicationProcessStep.
 */
@Service
@Transactional
public class ApplicationProcessStepServiceImpl implements ApplicationProcessStepService {

    private final Logger log = LoggerFactory.getLogger(ApplicationProcessStepServiceImpl.class);

    private final ApplicationProcessStepRepository applicationProcessStepRepository;

    private final ApplicationProcessStepMapper applicationProcessStepMapper;

    public ApplicationProcessStepServiceImpl(ApplicationProcessStepRepository applicationProcessStepRepository, ApplicationProcessStepMapper applicationProcessStepMapper) {
        this.applicationProcessStepRepository = applicationProcessStepRepository;
        this.applicationProcessStepMapper = applicationProcessStepMapper;
    }

    /**
     * Save a applicationProcessStep.
     *
     * @param applicationProcessStepDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ApplicationProcessStepDTO save(ApplicationProcessStepDTO applicationProcessStepDTO) {
        log.debug("Request to save ApplicationProcessStep : {}", applicationProcessStepDTO);

        ApplicationProcessStep applicationProcessStep = applicationProcessStepMapper.toEntity(applicationProcessStepDTO);
        applicationProcessStep = applicationProcessStepRepository.save(applicationProcessStep);
        return applicationProcessStepMapper.toDto(applicationProcessStep);
    }

    /**
     * Get all the applicationProcessSteps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ApplicationProcessStepDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ApplicationProcessSteps");
        return applicationProcessStepRepository.findAll(pageable)
            .map(applicationProcessStepMapper::toDto);
    }


    /**
     * Get one applicationProcessStep by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ApplicationProcessStepDTO> findOne(Long id) {
        log.debug("Request to get ApplicationProcessStep : {}", id);
        return applicationProcessStepRepository.findById(id)
            .map(applicationProcessStepMapper::toDto);
    }

    /**
     * Delete the applicationProcessStep by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApplicationProcessStep : {}", id);
        applicationProcessStepRepository.deleteById(id);
    }
}
