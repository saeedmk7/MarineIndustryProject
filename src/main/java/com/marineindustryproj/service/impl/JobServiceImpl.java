package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.service.*;
import com.marineindustryproj.repository.JobRepository;
import com.marineindustryproj.service.dto.*;
import com.marineindustryproj.service.dto.customs.JobMinDTO;
import com.marineindustryproj.service.mapper.DocumentMapper;
import com.marineindustryproj.service.mapper.EducationalModuleJobMapper;
import com.marineindustryproj.service.mapper.JobMapper;
import com.marineindustryproj.service.mapper.MainTaskMapper;
import io.github.jhipster.service.filter.LongFilter;
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

    private final EducationalModuleJobQueryService educationalModuleJobQueryService;

    private final EducationalModuleJobMapper educationalModuleJobMapper;

    private final DocumentMapper documentMapper;

    private final DocumentQueryService documentQueryService;

    private final PersonQueryService personQueryService;

    private final PersonService personService;

    private final MainTaskQueryService mainTaskQueryService;

    private final MainTaskMapper mainTaskMapper;

    private final CacheManager cacheManager;

    public JobServiceImpl(JobRepository jobRepository,
                          JobQueryService jobQueryService, JobMapper jobMapper,
                          EducationalModuleJobQueryService educationalModuleJobQueryService, EducationalModuleJobMapper educationalModuleJobMapper, DocumentMapper documentMapper, DocumentQueryService documentQueryService, PersonQueryService personQueryService, PersonService personService, MainTaskQueryService mainTaskQueryService, MainTaskMapper mainTaskMapper, CacheManager cacheManager) {
        this.jobRepository = jobRepository;
        this.jobQueryService = jobQueryService;
        this.jobMapper = jobMapper;
        this.educationalModuleJobQueryService = educationalModuleJobQueryService;
        this.educationalModuleJobMapper = educationalModuleJobMapper;
        this.documentMapper = documentMapper;
        this.documentQueryService = documentQueryService;
        this.personQueryService = personQueryService;
        this.personService = personService;
        this.mainTaskQueryService = mainTaskQueryService;
        this.mainTaskMapper = mainTaskMapper;
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
                LongFilter jobId = new LongFilter();
                jobId.setEquals(dto.getId());

                EducationalModuleJobCriteria educationalModuleJobCriteria = new EducationalModuleJobCriteria();
                educationalModuleJobCriteria.setJobId(jobId);
                List<EducationalModuleJobDTO> educationalModuleJobs = educationalModuleJobQueryService.findByCriteria(educationalModuleJobCriteria);
                for (EducationalModuleJobDTO educationalModuleJobDTO : educationalModuleJobs) {
                    EducationalModuleJob educationalModuleJob = educationalModuleJobMapper.toEntity(educationalModuleJobDTO);
                    job.addEducationalModuleJob(educationalModuleJob);
                }
                
                DocumentCriteria documentCriteria = new DocumentCriteria();
                documentCriteria.setJobId(jobId);
                List<DocumentDTO> documents = documentQueryService.findByCriteria(documentCriteria);
                for (DocumentDTO documentDTO : documents) {
                    Document document = documentMapper.toEntity(documentDTO);
                    job.addDocument(document);
                }

                MainTaskCriteria mainTaskCriteria = new MainTaskCriteria();
                mainTaskCriteria.setJobId(jobId);
                List<MainTaskDTO> mainTasks = mainTaskQueryService.findByCriteria(mainTaskCriteria);
                for (MainTaskDTO mainTaskDTO : mainTasks) {
                    MainTask mainTask = mainTaskMapper.toEntity(mainTaskDTO);
                    job.addMainTask(mainTask);
                }

                PersonCriteria jobPersonCriteria = new PersonCriteria();
                jobPersonCriteria.setJobId(jobId);
                List<PersonDTO> jobPersons = personQueryService.findByCriteria(jobPersonCriteria);
                for (PersonDTO jobPersonDTO : jobPersons) {
                    jobPersonDTO.setJobId(job.getId());
                    personService.save(jobPersonDTO);
                }
                PersonCriteria practicalJobPersonCriteria = new PersonCriteria();
                practicalJobPersonCriteria.setPracticaljobId(jobId);
                List<PersonDTO> practicalJobPersons = personQueryService.findByCriteria(practicalJobPersonCriteria);
                for (PersonDTO practicalJobPersonDTO : practicalJobPersons) {
                    practicalJobPersonDTO.setPracticaljobId(job.getId());
                    personService.save(practicalJobPersonDTO);
                }
                /*for (Document document : dtoJob.getDocuments()) {
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
                }*/
                this.delete(dto.getId());
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
