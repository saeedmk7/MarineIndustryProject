package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.JobChangeService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.JobChangeDTO;
import com.marineindustryproj.service.dto.JobChangeCriteria;
import com.marineindustryproj.service.JobChangeQueryService;
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
 * REST controller for managing JobChange.
 */
@RestController
@RequestMapping("/api")
public class JobChangeResource {

    private final Logger log = LoggerFactory.getLogger(JobChangeResource.class);

    private static final String ENTITY_NAME = "jobChange";

    private final JobChangeService jobChangeService;

    private final JobChangeQueryService jobChangeQueryService;

    public JobChangeResource(JobChangeService jobChangeService, JobChangeQueryService jobChangeQueryService) {
        this.jobChangeService = jobChangeService;
        this.jobChangeQueryService = jobChangeQueryService;
    }

    /**
     * POST  /job-changes : Create a new jobChange.
     *
     * @param jobChangeDTO the jobChangeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new jobChangeDTO, or with status 400 (Bad Request) if the jobChange has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/job-changes")
    @Timed
    public ResponseEntity<JobChangeDTO> createJobChange(@Valid @RequestBody JobChangeDTO jobChangeDTO) throws URISyntaxException {
        log.debug("REST request to save JobChange : {}", jobChangeDTO);
        if (jobChangeDTO.getId() != null) {
            throw new BadRequestAlertException("A new jobChange cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JobChangeDTO result = jobChangeService.save(jobChangeDTO);
        return ResponseEntity.created(new URI("/api/job-changes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /job-changes : Updates an existing jobChange.
     *
     * @param jobChangeDTO the jobChangeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated jobChangeDTO,
     * or with status 400 (Bad Request) if the jobChangeDTO is not valid,
     * or with status 500 (Internal Server Error) if the jobChangeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/job-changes")
    @Timed
    public ResponseEntity<JobChangeDTO> updateJobChange(@Valid @RequestBody JobChangeDTO jobChangeDTO) throws URISyntaxException {
        log.debug("REST request to update JobChange : {}", jobChangeDTO);
        if (jobChangeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        JobChangeDTO result = jobChangeService.save(jobChangeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, jobChangeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /job-changes : get all the jobChanges.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of jobChanges in body
     */
    @GetMapping("/job-changes")
    @Timed
    public ResponseEntity<List<JobChangeDTO>> getAllJobChanges(JobChangeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get JobChanges by criteria: {}", criteria);
        Page<JobChangeDTO> page = jobChangeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/job-changes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /job-changes/count : count all the jobChanges.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/job-changes/count")
    @Timed
    public ResponseEntity<Long> countJobChanges(JobChangeCriteria criteria) {
        log.debug("REST request to count JobChanges by criteria: {}", criteria);
        return ResponseEntity.ok().body(jobChangeQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /job-changes/:id : get the "id" jobChange.
     *
     * @param id the id of the jobChangeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the jobChangeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/job-changes/{id}")
    @Timed
    public ResponseEntity<JobChangeDTO> getJobChange(@PathVariable Long id) {
        log.debug("REST request to get JobChange : {}", id);
        Optional<JobChangeDTO> jobChangeDTO = jobChangeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jobChangeDTO);
    }

    /**
     * DELETE  /job-changes/:id : delete the "id" jobChange.
     *
     * @param id the id of the jobChangeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/job-changes/{id}")
    @Timed
    public ResponseEntity<Void> deleteJobChange(@PathVariable Long id) {
        log.debug("REST request to delete JobChange : {}", id);
        jobChangeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
