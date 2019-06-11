package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.JobRecordService;
import com.marineindustryproj.service.dto.MainTaskDTO;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.JobRecordDTO;
import com.marineindustryproj.service.dto.JobRecordCriteria;
import com.marineindustryproj.service.JobRecordQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST controller for managing JobRecord.
 */
@RestController
@RequestMapping("/api")
public class JobRecordResource {

    private final Logger log = LoggerFactory.getLogger(JobRecordResource.class);

    private static final String ENTITY_NAME = "jobRecord";

    private final JobRecordService jobRecordService;

    private final JobRecordQueryService jobRecordQueryService;

    public JobRecordResource(JobRecordService jobRecordService, JobRecordQueryService jobRecordQueryService) {
        this.jobRecordService = jobRecordService;
        this.jobRecordQueryService = jobRecordQueryService;
    }

    /**
     * POST  /job-records : Create a new jobRecord.
     *
     * @param jobRecordDTO the jobRecordDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new jobRecordDTO, or with status 400 (Bad Request) if the jobRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/job-records")
    @Timed
    public ResponseEntity<JobRecordDTO> createJobRecord(@Valid @RequestBody JobRecordDTO jobRecordDTO) throws URISyntaxException {
        log.debug("REST request to save JobRecord : {}", jobRecordDTO);
        if (jobRecordDTO.getId() != null) {
            throw new BadRequestAlertException("A new jobRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }

        jobRecordDTO.setGuid(UUID.randomUUID().toString());
        jobRecordDTO.setCreateDate(ZonedDateTime.now());
        jobRecordDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        JobRecordDTO result = jobRecordService.save(jobRecordDTO);
        return ResponseEntity.created(new URI("/api/job-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /job-records : Updates an existing jobRecord.
     *
     * @param jobRecordDTO the jobRecordDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated jobRecordDTO,
     * or with status 400 (Bad Request) if the jobRecordDTO is not valid,
     * or with status 500 (Internal Server Error) if the jobRecordDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/job-records")
    @Timed
    public ResponseEntity<JobRecordDTO> updateJobRecord(@Valid @RequestBody JobRecordDTO jobRecordDTO) throws URISyntaxException {
        log.debug("REST request to update JobRecord : {}", jobRecordDTO);
        if (jobRecordDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        JobRecordDTO jobRecord = jobRecordService.findOne(jobRecordDTO.getId()).get();

        jobRecordDTO.setGuid(jobRecord.getGuid());
        jobRecordDTO.setCreateUserLogin(jobRecord.getCreateUserLogin());
        jobRecordDTO.setCreateDate(jobRecord.getCreateDate());
        jobRecordDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        jobRecordDTO.setModifyDate(ZonedDateTime.now());

        JobRecordDTO result = jobRecordService.save(jobRecordDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, jobRecordDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /job-records : get all the jobRecords.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of jobRecords in body
     */
    @GetMapping("/job-records")
    @Timed
    public ResponseEntity<List<JobRecordDTO>> getAllJobRecords(JobRecordCriteria criteria, Pageable pageable) {
        log.debug("REST request to get JobRecords by criteria: {}", criteria);
        Page<JobRecordDTO> page = jobRecordQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/job-records");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /job-records/count : count all the jobRecords.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/job-records/count")
    @Timed
    public ResponseEntity<Long> countJobRecords(JobRecordCriteria criteria) {
        log.debug("REST request to count JobRecords by criteria: {}", criteria);
        return ResponseEntity.ok().body(jobRecordQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /job-records/:id : get the "id" jobRecord.
     *
     * @param id the id of the jobRecordDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the jobRecordDTO, or with status 404 (Not Found)
     */
    @GetMapping("/job-records/{id}")
    @Timed
    public ResponseEntity<JobRecordDTO> getJobRecord(@PathVariable Long id) {
        log.debug("REST request to get JobRecord : {}", id);
        Optional<JobRecordDTO> jobRecordDTO = jobRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jobRecordDTO);
    }

    /**
     * DELETE  /job-records/:id : delete the "id" jobRecord.
     *
     * @param id the id of the jobRecordDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/job-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteJobRecord(@PathVariable Long id) {
        log.debug("REST request to delete JobRecord : {}", id);
        jobRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
