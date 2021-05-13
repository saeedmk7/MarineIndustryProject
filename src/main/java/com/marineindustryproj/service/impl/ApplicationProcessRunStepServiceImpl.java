package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.ApplicationProcessRunStepService;
import com.marineindustryproj.domain.ApplicationProcessRunStep;
import com.marineindustryproj.repository.ApplicationProcessRunStepRepository;
import com.marineindustryproj.service.dto.ApplicationProcessRunStepDTO;
import com.marineindustryproj.service.mapper.ApplicationProcessRunStepMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ApplicationProcessRunStep.
 */
@Service
@Transactional
public class ApplicationProcessRunStepServiceImpl implements ApplicationProcessRunStepService {

    private final Logger log = LoggerFactory.getLogger(ApplicationProcessRunStepServiceImpl.class);

    private final ApplicationProcessRunStepRepository applicationProcessRunStepRepository;

    private final ApplicationProcessRunStepMapper applicationProcessRunStepMapper;

    public ApplicationProcessRunStepServiceImpl(ApplicationProcessRunStepRepository applicationProcessRunStepRepository, ApplicationProcessRunStepMapper applicationProcessRunStepMapper) {
        this.applicationProcessRunStepRepository = applicationProcessRunStepRepository;
        this.applicationProcessRunStepMapper = applicationProcessRunStepMapper;
    }

    /**
     * Save a applicationProcessRunStep.
     *
     * @param applicationProcessRunStepDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ApplicationProcessRunStepDTO save(ApplicationProcessRunStepDTO applicationProcessRunStepDTO) {
        log.debug("Request to save ApplicationProcessRunStep : {}", applicationProcessRunStepDTO);

        ApplicationProcessRunStep applicationProcessRunStep = applicationProcessRunStepMapper.toEntity(applicationProcessRunStepDTO);
        applicationProcessRunStep = applicationProcessRunStepRepository.save(applicationProcessRunStep);
        return applicationProcessRunStepMapper.toDto(applicationProcessRunStep);
    }

    /**
     * Get all the applicationProcessRunSteps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ApplicationProcessRunStepDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ApplicationProcessRunSteps");
        return applicationProcessRunStepRepository.findAll(pageable)
            .map(applicationProcessRunStepMapper::toDto);
    }


    /**
     * Get one applicationProcessRunStep by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ApplicationProcessRunStepDTO> findOne(Long id) {
        log.debug("Request to get ApplicationProcessRunStep : {}", id);
        return applicationProcessRunStepRepository.findById(id)
            .map(applicationProcessRunStepMapper::toDto);
    }

    /**
     * Delete the applicationProcessRunStep by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApplicationProcessRunStep : {}", id);
        applicationProcessRunStepRepository.deleteById(id);
    }
}
