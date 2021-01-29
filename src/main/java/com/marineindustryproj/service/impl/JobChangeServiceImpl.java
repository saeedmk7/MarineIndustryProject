package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.JobChangeService;
import com.marineindustryproj.domain.JobChange;
import com.marineindustryproj.repository.JobChangeRepository;
import com.marineindustryproj.service.dto.JobChangeDTO;
import com.marineindustryproj.service.mapper.JobChangeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing JobChange.
 */
@Service
@Transactional
public class JobChangeServiceImpl implements JobChangeService {

    private final Logger log = LoggerFactory.getLogger(JobChangeServiceImpl.class);

    private final JobChangeRepository jobChangeRepository;

    private final JobChangeMapper jobChangeMapper;

    public JobChangeServiceImpl(JobChangeRepository jobChangeRepository, JobChangeMapper jobChangeMapper) {
        this.jobChangeRepository = jobChangeRepository;
        this.jobChangeMapper = jobChangeMapper;
    }

    /**
     * Save a jobChange.
     *
     * @param jobChangeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public JobChangeDTO save(JobChangeDTO jobChangeDTO) {
        log.debug("Request to save JobChange : {}", jobChangeDTO);

        JobChange jobChange = jobChangeMapper.toEntity(jobChangeDTO);
        jobChange = jobChangeRepository.save(jobChange);
        return jobChangeMapper.toDto(jobChange);
    }

    /**
     * Get all the jobChanges.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<JobChangeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all JobChanges");
        return jobChangeRepository.findAll(pageable)
            .map(jobChangeMapper::toDto);
    }


    /**
     * Get one jobChange by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<JobChangeDTO> findOne(Long id) {
        log.debug("Request to get JobChange : {}", id);
        return jobChangeRepository.findById(id)
            .map(jobChangeMapper::toDto);
    }

    /**
     * Delete the jobChange by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete JobChange : {}", id);
        jobChangeRepository.deleteById(id);
    }
}
