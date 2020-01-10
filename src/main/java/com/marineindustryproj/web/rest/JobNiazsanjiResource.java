package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.domain.JobNiazsanji;
import com.marineindustryproj.domain.PreJobNiazsanji;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.JobNiazsanjiService;
import com.marineindustryproj.service.PersonService;
import com.marineindustryproj.service.PreJobNiazsanjiService;
import com.marineindustryproj.service.dto.PersonDTO;
import com.marineindustryproj.service.dto.PreJobNiazsanjiDTO;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.JobNiazsanjiDTO;
import com.marineindustryproj.service.dto.JobNiazsanjiCriteria;
import com.marineindustryproj.service.JobNiazsanjiQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing JobNiazsanji.
 */
@RestController
@RequestMapping("/api")
public class JobNiazsanjiResource {

    private final Logger log = LoggerFactory.getLogger(JobNiazsanjiResource.class);

    private static final String ENTITY_NAME = "jobNiazsanji";

    private final JobNiazsanjiService jobNiazsanjiService;

    private final JobNiazsanjiQueryService jobNiazsanjiQueryService;

    private final PreJobNiazsanjiService preJobNiazsanjiService;

    private final PersonService personService;

    public JobNiazsanjiResource(JobNiazsanjiService jobNiazsanjiService, JobNiazsanjiQueryService jobNiazsanjiQueryService, PreJobNiazsanjiService preJobNiazsanjiService, PersonService personService) {
        this.jobNiazsanjiService = jobNiazsanjiService;
        this.jobNiazsanjiQueryService = jobNiazsanjiQueryService;
        this.preJobNiazsanjiService = preJobNiazsanjiService;
        this.personService = personService;
    }

    /**
     * POST  /job-niazsanjis : Create a new jobNiazsanji.
     *
     * @param jobNiazsanjiDTO the jobNiazsanjiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new jobNiazsanjiDTO, or with status 400 (Bad Request) if the jobNiazsanji has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/job-niazsanjis")
    @Timed
    public ResponseEntity<JobNiazsanjiDTO> createJobNiazsanji(@Valid @RequestBody JobNiazsanjiDTO jobNiazsanjiDTO) throws URISyntaxException {
        log.debug("REST request to save JobNiazsanji : {}", jobNiazsanjiDTO);
        if (jobNiazsanjiDTO.getId() != null) {
            throw new BadRequestAlertException("A new jobNiazsanji cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PersonDTO personDTO = personService.findOne(jobNiazsanjiDTO.getPersonId()).get();
        jobNiazsanjiDTO.setCreateDate(ZonedDateTime.now());
        jobNiazsanjiDTO.setCreateUserLogin(personDTO.getNationalId());
        JobNiazsanjiDTO result = jobNiazsanjiService.save(jobNiazsanjiDTO);
        return ResponseEntity.created(new URI("/api/job-niazsanjis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * POST  /job-niazsanjis : Create a new jobNiazsanji.
     *
     * @param jobNiazsanjiDTO the jobNiazsanjiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new jobNiazsanjiDTO, or with status 400 (Bad Request) if the jobNiazsanji has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/finalize-job-niazsanji")
    @Timed
    public ResponseEntity<JobNiazsanjiDTO> finalizeJobNiazsanji(@Valid @RequestBody JobNiazsanjiDTO jobNiazsanjiDTO) throws URISyntaxException {
        log.debug("REST request to finalize JobNiazsanji : {}", jobNiazsanjiDTO);
        if (jobNiazsanjiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        JobNiazsanjiDTO jobNiazsanji = jobNiazsanjiService.findOne(jobNiazsanjiDTO.getId()).get();

        jobNiazsanjiDTO.setCreateUserLogin(jobNiazsanji.getCreateUserLogin());
        jobNiazsanjiDTO.setCreateDate(jobNiazsanji.getCreateDate());
        jobNiazsanjiDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        jobNiazsanjiDTO.setModifyDate(ZonedDateTime.now());
        jobNiazsanjiDTO.setStatus(30);
        jobNiazsanjiDTO.setChangeStatusUserLogin(SecurityUtils.getCurrentUserLogin().get());

        JobNiazsanjiDTO result = jobNiazsanjiService.finalize(jobNiazsanjiDTO);
        return ResponseEntity.created(new URI("/api/job-niazsanjis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /job-niazsanjis : Updates an existing jobNiazsanji.
     *
     * @param jobNiazsanjiDTO the jobNiazsanjiDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated jobNiazsanjiDTO,
     * or with status 400 (Bad Request) if the jobNiazsanjiDTO is not valid,
     * or with status 500 (Internal Server Error) if the jobNiazsanjiDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/job-niazsanjis")
    @Timed
    public ResponseEntity<JobNiazsanjiDTO> updateJobNiazsanji(@Valid @RequestBody JobNiazsanjiDTO jobNiazsanjiDTO) throws URISyntaxException {
        log.debug("REST request to update JobNiazsanji : {}", jobNiazsanjiDTO);
        if (jobNiazsanjiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        JobNiazsanjiDTO jobNiazsanji = jobNiazsanjiService.findOne(jobNiazsanjiDTO.getId()).get();

        jobNiazsanjiDTO.setCreateUserLogin(jobNiazsanji.getCreateUserLogin());
        jobNiazsanjiDTO.setCreateDate(jobNiazsanji.getCreateDate());
        jobNiazsanjiDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        jobNiazsanjiDTO.setModifyDate(ZonedDateTime.now());

        JobNiazsanjiDTO result = jobNiazsanjiService.save(jobNiazsanjiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, jobNiazsanjiDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /job-niazsanjis : get all the jobNiazsanjis.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of jobNiazsanjis in body
     */
    @GetMapping("/job-niazsanjis")
    @Timed
    public ResponseEntity<List<JobNiazsanjiDTO>> getAllJobNiazsanjis(JobNiazsanjiCriteria criteria, Pageable pageable) {
        log.debug("REST request to get JobNiazsanjis by criteria: {}", criteria);
        Page<JobNiazsanjiDTO> page = jobNiazsanjiQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/job-niazsanjis");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PutMapping("/job-niazsanjis/toggleImportantMessage/{id}/{type}")
    @Timed
    public ResponseEntity<JobNiazsanjiDTO> toggleImportantMessage(@PathVariable long id, @PathVariable boolean type) throws URISyntaxException {
        JobNiazsanjiDTO jobNiazsanji = jobNiazsanjiService.findOne(id).get();

        jobNiazsanji.setModifyDate(ZonedDateTime.now());
        jobNiazsanji.setHasImportantMessage(type);
        JobNiazsanjiDTO result = jobNiazsanjiService.save(jobNiazsanji);

        Long preJobNiazsanjiId = jobNiazsanji.getPreJobNiazsanjiId();
        PreJobNiazsanjiDTO preJobNiazsanji = preJobNiazsanjiService.findOne(preJobNiazsanjiId).get();
        preJobNiazsanji.setModifyDate(ZonedDateTime.now());
        preJobNiazsanji.setHasImportantMessage(type);
        preJobNiazsanjiService.save(preJobNiazsanji);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, jobNiazsanji.getId().toString()))
            .body(result);
    }

    /**
    * GET  /job-niazsanjis/count : count all the jobNiazsanjis.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/job-niazsanjis/count")
    @Timed
    public ResponseEntity<Long> countJobNiazsanjis(JobNiazsanjiCriteria criteria) {
        log.debug("REST request to count JobNiazsanjis by criteria: {}", criteria);
        return ResponseEntity.ok().body(jobNiazsanjiQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /job-niazsanjis/:id : get the "id" jobNiazsanji.
     *
     * @param id the id of the jobNiazsanjiDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the jobNiazsanjiDTO, or with status 404 (Not Found)
     */
    @GetMapping("/job-niazsanjis/{id}")
    @Timed
    public ResponseEntity<JobNiazsanjiDTO> getJobNiazsanji(@PathVariable Long id) {
        log.debug("REST request to get JobNiazsanji : {}", id);
        Optional<JobNiazsanjiDTO> jobNiazsanjiDTO = jobNiazsanjiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jobNiazsanjiDTO);
    }

    /**
     * DELETE  /job-niazsanjis/:id : delete the "id" jobNiazsanji.
     *
     * @param id the id of the jobNiazsanjiDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/job-niazsanjis/{id}")
    @Timed
    public ResponseEntity<Void> deleteJobNiazsanji(@PathVariable Long id) {
        log.debug("REST request to delete JobNiazsanji : {}", id);
        jobNiazsanjiService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
