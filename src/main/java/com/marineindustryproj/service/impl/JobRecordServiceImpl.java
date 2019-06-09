package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.JobRecordService;
import com.marineindustryproj.domain.JobRecord;
import com.marineindustryproj.repository.JobRecordRepository;
import com.marineindustryproj.service.dto.JobRecordDTO;
import com.marineindustryproj.service.mapper.JobRecordMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing JobRecord.
 */
@Service
@Transactional
public class JobRecordServiceImpl implements JobRecordService {

    private final Logger log = LoggerFactory.getLogger(JobRecordServiceImpl.class);

    private final JobRecordRepository jobRecordRepository;

    private final JobRecordMapper jobRecordMapper;

    public JobRecordServiceImpl(JobRecordRepository jobRecordRepository, JobRecordMapper jobRecordMapper) {
        this.jobRecordRepository = jobRecordRepository;
        this.jobRecordMapper = jobRecordMapper;
    }

    /**
     * Save a jobRecord.
     *
     * @param jobRecordDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public JobRecordDTO save(JobRecordDTO jobRecordDTO) {
        log.debug("Request to save JobRecord : {}", jobRecordDTO);

        JobRecord jobRecord = jobRecordMapper.toEntity(jobRecordDTO);
        jobRecord = jobRecordRepository.save(jobRecord);
        return jobRecordMapper.toDto(jobRecord);
    }

    /**
     * Get all the jobRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<JobRecordDTO> findAll(Pageable pageable) {
        log.debug("Request to get all JobRecords");
        return jobRecordRepository.findAll(pageable)
            .map(jobRecordMapper::toDto);
    }


    /**
     * Get one jobRecord by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<JobRecordDTO> findOne(Long id) {
        log.debug("Request to get JobRecord : {}", id);
        return jobRecordRepository.findById(id)
            .map(jobRecordMapper::toDto);
    }

    /**
     * Delete the jobRecord by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete JobRecord : {}", id);
        jobRecordRepository.deleteById(id);
    }
}
