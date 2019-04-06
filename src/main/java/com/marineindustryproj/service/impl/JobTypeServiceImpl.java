package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.JobTypeService;
import com.marineindustryproj.domain.JobType;
import com.marineindustryproj.repository.JobTypeRepository;
import com.marineindustryproj.service.dto.JobTypeDTO;
import com.marineindustryproj.service.mapper.JobTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing JobType.
 */
@Service
@Transactional
public class JobTypeServiceImpl implements JobTypeService {

    private final Logger log = LoggerFactory.getLogger(JobTypeServiceImpl.class);

    private final JobTypeRepository jobTypeRepository;

    private final JobTypeMapper jobTypeMapper;

    public JobTypeServiceImpl(JobTypeRepository jobTypeRepository, JobTypeMapper jobTypeMapper) {
        this.jobTypeRepository = jobTypeRepository;
        this.jobTypeMapper = jobTypeMapper;
    }

    /**
     * Save a jobType.
     *
     * @param jobTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public JobTypeDTO save(JobTypeDTO jobTypeDTO) {
        log.debug("Request to save JobType : {}", jobTypeDTO);

        JobType jobType = jobTypeMapper.toEntity(jobTypeDTO);
        jobType = jobTypeRepository.save(jobType);
        return jobTypeMapper.toDto(jobType);
    }

    /**
     * Get all the jobTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<JobTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all JobTypes");
        return jobTypeRepository.findAll(pageable)
            .map(jobTypeMapper::toDto);
    }


    /**
     * Get one jobType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<JobTypeDTO> findOne(Long id) {
        log.debug("Request to get JobType : {}", id);
        return jobTypeRepository.findById(id)
            .map(jobTypeMapper::toDto);
    }

    /**
     * Delete the jobType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete JobType : {}", id);
        jobTypeRepository.deleteById(id);
    }
}
