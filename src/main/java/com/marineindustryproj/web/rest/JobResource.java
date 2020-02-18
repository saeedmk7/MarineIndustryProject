package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.domain.Job;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.JobService;
import com.marineindustryproj.service.StorageService;
import com.marineindustryproj.service.dto.customs.JobMinDTO;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.errors.ErrorConstants;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.JobDTO;
import com.marineindustryproj.service.dto.JobCriteria;
import com.marineindustryproj.service.JobQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Job.
 */
@RestController
@RequestMapping("/api")
public class JobResource {

    private final Logger log = LoggerFactory.getLogger(JobResource.class);

    private static final String ENTITY_NAME = "job";

    private final JobService jobService;

    private final JobQueryService jobQueryService;

    private final StorageService storageService;

    public JobResource(JobService jobService, JobQueryService jobQueryService, StorageService storageService) {
        this.jobService = jobService;
        this.jobQueryService = jobQueryService;
        this.storageService = storageService;
    }

    /**
     * POST  /jobs : Create a new job.
     *
     * @param jobDTO the jobDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new jobDTO, or with status 400 (Bad Request) if the job has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/jobs")
    @Timed
    public ResponseEntity<JobDTO> createJob(@Valid @RequestBody JobDTO jobDTO) throws URISyntaxException {
        log.debug("REST request to save Job : {}", jobDTO);
        if (jobDTO.getId() != null) {
            throw new BadRequestAlertException("A new job cannot already have an ID", ENTITY_NAME, "idexists");
        }
        try {
            if (!jobDTO.getJobCode().isEmpty()) {
                jobDTO.setFirst3JobCode(jobDTO.getJobCode().substring(0,  3));
            }
            jobDTO.setCreateDate(ZonedDateTime.now());
            jobDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

            JobDTO result = jobService.save(jobDTO);
            return ResponseEntity.created(new URI("/api/jobs/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME,
                                                              result.getId().toString()))
                .body(result);
        }
        catch (Exception ex){
            throw new BadRequestAlertException(ErrorConstants.JOBKEY_ALREADY_USED_TYPE,
                                               "JobKey already used!",
                                               "job",
                                               "jobexists");
        }
    }
    @PostMapping("/jobs/jobAggregation")
    @Timed
    public ResponseEntity<JobDTO> jobAggregation(@Valid @RequestBody JobDTO jobDTO) throws URISyntaxException {
        log.debug("REST request to save Job : {}", jobDTO);
        if (jobDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        try {
            JobDTO result = jobService.aggregateJob(jobDTO);
            return ResponseEntity.created(new URI("/api/jobs/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME,
                    result.getId().toString()))
                .body(result);
        }
        catch (Exception ex){
            throw new BadRequestAlertException(ErrorConstants.JOBKEY_ALREADY_USED_TYPE,
                "JobKey already used!",
                "job",
                "jobexists");
        }
    }

    /**
     * PUT  /jobs : Updates an existing job.
     *
     * @param jobDTO the jobDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated jobDTO,
     * or with status 400 (Bad Request) if the jobDTO is not valid,
     * or with status 500 (Internal Server Error) if the jobDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/jobs")
    @Timed
    public ResponseEntity<JobDTO> updateJob(@Valid @RequestBody JobDTO jobDTO) throws URISyntaxException {
        log.debug("REST request to update Job : {}", jobDTO);
        if (jobDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        JobDTO job = jobService.findOne(jobDTO.getId()).get();
        if(!jobDTO.getJobCode().isEmpty())
        {
            jobDTO.setFirst3JobCode(jobDTO.getJobCode().substring(0,3));
        }
        if(jobDTO.getFileDoc() == null)
            jobDTO.setFileDoc(job.getFileDoc());
        jobDTO.setCreateUserLogin(job.getCreateUserLogin());
        jobDTO.setCreateDate(job.getCreateDate());
        jobDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        jobDTO.setModifyDate(ZonedDateTime.now());

        JobDTO result = jobService.save(jobDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, jobDTO.getId().toString()))
            .body(result);
    }


    /**
     * POST  /educational-histories : Create a new educationalHistory.
     *
     * @return the ResponseEntity with status 201 (Created) and with body the new educationalHistoryDTO, or with status 400 (Bad Request) if the educationalHistory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/jobs/upload-file")
    @Timed
    public ResponseEntity<String> uploadFileJob(@Valid @RequestBody @RequestParam("file") MultipartFile file) throws URISyntaxException, Exception {
        String fileDownloadUri;
        try
        {
            String fileName = storageService.storeJobFile(file);

            fileDownloadUri = "api/downloadJobFile/" + fileName;
        }
        catch (Exception ex){
            throw new Exception(ex);
        }

        return ResponseEntity.ok().body(fileDownloadUri);
    }
    /**
     * GET  /jobs : get all the jobs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of jobs in body
     */
    @GetMapping("/jobs")
    @Timed
    public ResponseEntity<List<JobDTO>> getAllJobs(JobCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Jobs by criteria: {}", criteria);
        Page<JobDTO> page = jobQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/jobs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /jobs : get all the jobs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of jobs in body
     */
    @GetMapping("/jobs/all")
    @Timed
    public ResponseEntity<List<JobMinDTO>> getAllJobs() {
        log.debug("REST request to get Jobs");
        List<JobMinDTO> jobs = jobService.findAllFromCache();
        return ResponseEntity.ok().body(jobs);
    }

    /**
    * GET  /jobs/count : count all the jobs.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/jobs/count")
    @Timed
    public ResponseEntity<Long> countJobs (JobCriteria criteria) {
        log.debug("REST request to count Jobs by criteria: {}", criteria);
        return ResponseEntity.ok().body(jobQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /jobs/:id : get the "id" job.
     *
     * @param id the id of the jobDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the jobDTO, or with status 404 (Not Found)
     */
    @GetMapping("/jobs/{id}")
    @Timed
    public ResponseEntity<JobDTO> getJob(@PathVariable Long id) {
        log.debug("REST request to get Job : {}", id);
        Optional<JobDTO> jobDTO = jobService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jobDTO);
    }

    /**
     * DELETE  /jobs/:id : delete the "id" job.
     *
     * @param id the id of the jobDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/jobs/{id}")
    @Timed
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        log.debug("REST request to delete Job : {}", id);
        jobService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
