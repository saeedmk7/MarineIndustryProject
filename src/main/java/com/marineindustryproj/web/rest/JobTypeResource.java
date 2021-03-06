package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.JobTypeService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.JobTypeDTO;
import com.marineindustryproj.service.dto.JobTypeCriteria;
import com.marineindustryproj.service.JobTypeQueryService;
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

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing JobType.
 */
@RestController
@RequestMapping("/api")
public class JobTypeResource {

    private final Logger log = LoggerFactory.getLogger(JobTypeResource.class);

    private static final String ENTITY_NAME = "jobType";

    private final JobTypeService jobTypeService;

    private final JobTypeQueryService jobTypeQueryService;

    public JobTypeResource(JobTypeService jobTypeService, JobTypeQueryService jobTypeQueryService) {
        this.jobTypeService = jobTypeService;
        this.jobTypeQueryService = jobTypeQueryService;
    }

    /**
     * POST  /job-types : Create a new jobType.
     *
     * @param jobTypeDTO the jobTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new jobTypeDTO, or with status 400 (Bad Request) if the jobType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/job-types")
    @Timed
    public ResponseEntity<JobTypeDTO> createJobType(@Valid @RequestBody JobTypeDTO jobTypeDTO) throws URISyntaxException {
        log.debug("REST request to save JobType : {}", jobTypeDTO);
        if (jobTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new jobType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JobTypeDTO result = jobTypeService.save(jobTypeDTO);
        return ResponseEntity.created(new URI("/api/job-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /job-types : Updates an existing jobType.
     *
     * @param jobTypeDTO the jobTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated jobTypeDTO,
     * or with status 400 (Bad Request) if the jobTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the jobTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/job-types")
    @Timed
    public ResponseEntity<JobTypeDTO> updateJobType(@Valid @RequestBody JobTypeDTO jobTypeDTO) throws URISyntaxException {
        log.debug("REST request to update JobType : {}", jobTypeDTO);
        if (jobTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        JobTypeDTO result = jobTypeService.save(jobTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, jobTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /job-types : get all the jobTypes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of jobTypes in body
     */
    @GetMapping("/job-types")
    @Timed
    public ResponseEntity<List<JobTypeDTO>> getAllJobTypes(JobTypeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get JobTypes by criteria: {}", criteria);
        Page<JobTypeDTO> page = jobTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/job-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /job-types/count : count all the jobTypes.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/job-types/count")
    @Timed
    public ResponseEntity<Long> countJobTypes (JobTypeCriteria criteria) {
        log.debug("REST request to count JobTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(jobTypeQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /job-types/:id : get the "id" jobType.
     *
     * @param id the id of the jobTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the jobTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/job-types/{id}")
    @Timed
    public ResponseEntity<JobTypeDTO> getJobType(@PathVariable Long id) {
        log.debug("REST request to get JobType : {}", id);
        Optional<JobTypeDTO> jobTypeDTO = jobTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jobTypeDTO);
    }

    /**
     * DELETE  /job-types/:id : delete the "id" jobType.
     *
     * @param id the id of the jobTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/job-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteJobType(@PathVariable Long id) {
        log.debug("REST request to delete JobType : {}", id);
        jobTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
