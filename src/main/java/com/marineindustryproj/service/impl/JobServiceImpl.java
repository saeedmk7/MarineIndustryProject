package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.JobQueryService;
import com.marineindustryproj.service.JobService;
import com.marineindustryproj.repository.JobRepository;
import com.marineindustryproj.service.dto.DocumentDTO;
import com.marineindustryproj.service.dto.JobCriteria;
import com.marineindustryproj.service.dto.JobDTO;
import com.marineindustryproj.service.dto.customs.JobMinDTO;
import com.marineindustryproj.service.mapper.JobMapper;
import io.github.jhipster.service.filter.StringFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Service Implementation for managing Job.
 */
@Service
@Transactional
public class JobServiceImpl implements JobService {

    private final Logger log = LoggerFactory.getLogger(JobServiceImpl.class);

    private final JobRepository jobRepository;

    private final JobQueryService jobQueryService;

    private final JobMapper jobMapper;

    private final CacheManager cacheManager;

    public JobServiceImpl(JobRepository jobRepository,
                          JobQueryService jobQueryService, JobMapper jobMapper,
                          CacheManager cacheManager) {
        this.jobRepository = jobRepository;
        this.jobQueryService = jobQueryService;
        this.jobMapper = jobMapper;
        this.cacheManager = cacheManager;
    }

    /**
     * Save a job.
     *
     * @param jobDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public JobDTO save(JobDTO jobDTO) {
        log.debug("Request to save Job : {}", jobDTO);

        Job job = jobMapper.toEntity(jobDTO);
        job = jobRepository.save(job);
        clearJobCaches();
        return jobMapper.toDto(job);
    }
    @Override
    public JobDTO aggregateJob(JobDTO jobDTO) {
        log.debug("Request to Aggregate Job : {}", jobDTO);

        Job job = jobMapper.toEntity(jobDTO);

        JobCriteria criteria = new JobCriteria();
        StringFilter titleFilter = new StringFilter();
        titleFilter.setEquals(job.getTitle());
        criteria.setTitle(titleFilter);
        List<JobDTO> jobDTOS = jobQueryService.findByCriteria(criteria);
        jobDTOS.remove(jobDTO);
        if(!jobDTOS.isEmpty()) {
            for (JobDTO dto : jobDTOS) {
                Job dtoJob = jobMapper.toEntity(dto);
                for (EducationalModuleJob educationalModuleJob : dtoJob.getEducationalModuleJobs()) {
                    job.addEducationalModuleJob(educationalModuleJob);
                }
                for (Document document : dtoJob.getDocuments()) {
                    job.addDocument(document);
                }
                for (Person jobPerson : dtoJob.getJobPeople()) {
                    job.addJobPerson(jobPerson);
                }
                for (MainTask mainTask : dtoJob.getMainTasks()) {
                    job.addMainTask(mainTask);
                }
                for (Person practicaljobPerson : dtoJob.getPracticaljobPeople()) {
                    job.addPracticaljobPerson(practicaljobPerson);
                }
                jobRepository.delete(dtoJob);
            }
        }
        job = jobRepository.save(job);
        clearJobCaches();
        return jobMapper.toDto(job);
    }

    /**
     * Get all the jobs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<JobDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Jobs");
        return jobRepository.findAll(pageable)
            .map(jobMapper::toDto);
    }

    /**
     * Get all the jobs.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<JobMinDTO> findAllFromCache() {
        log.debug("Request to get all Jobs");
        return jobRepository.findAllFromCache();
    }

    /**
     * Get all the Job with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<JobDTO> findAllWithEagerRelationships(Pageable pageable) {
        return jobRepository.findAllWithEagerRelationships(pageable).map(jobMapper::toDto);
    }
    

    /**
     * Get one job by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<JobDTO> findOne(Long id) {
        log.debug("Request to get Job : {}", id);
        return jobRepository.findOneWithEagerRelationships(id)
            .map(jobMapper::toDto);
    }

    /**
     * Delete the job by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Job : {}", id);
        jobRepository.deleteById(id);
        clearJobCaches();
    }
    private void clearJobCaches() {
        Objects.requireNonNull(cacheManager.getCache(jobRepository.ALL_JOB_CACHE)).clear();
    }
}
