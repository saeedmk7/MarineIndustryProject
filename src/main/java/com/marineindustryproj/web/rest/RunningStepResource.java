package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.RunningStepService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.RunningStepDTO;
import com.marineindustryproj.service.dto.RunningStepCriteria;
import com.marineindustryproj.service.RunningStepQueryService;
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
 * REST controller for managing RunningStep.
 */
@RestController
@RequestMapping("/api")
public class RunningStepResource {

    private final Logger log = LoggerFactory.getLogger(RunningStepResource.class);

    private static final String ENTITY_NAME = "runningStep";

    private final RunningStepService runningStepService;

    private final RunningStepQueryService runningStepQueryService;

    public RunningStepResource(RunningStepService runningStepService, RunningStepQueryService runningStepQueryService) {
        this.runningStepService = runningStepService;
        this.runningStepQueryService = runningStepQueryService;
    }

    /**
     * POST  /running-steps : Create a new runningStep.
     *
     * @param runningStepDTO the runningStepDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new runningStepDTO, or with status 400 (Bad Request) if the runningStep has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/running-steps")
    @Timed
    public ResponseEntity<RunningStepDTO> createRunningStep(@Valid @RequestBody RunningStepDTO runningStepDTO) throws URISyntaxException {
        log.debug("REST request to save RunningStep : {}", runningStepDTO);
        if (runningStepDTO.getId() != null) {
            throw new BadRequestAlertException("A new runningStep cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RunningStepDTO result = runningStepService.save(runningStepDTO);
        return ResponseEntity.created(new URI("/api/running-steps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /running-steps : Updates an existing runningStep.
     *
     * @param runningStepDTO the runningStepDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated runningStepDTO,
     * or with status 400 (Bad Request) if the runningStepDTO is not valid,
     * or with status 500 (Internal Server Error) if the runningStepDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/running-steps")
    @Timed
    public ResponseEntity<RunningStepDTO> updateRunningStep(@Valid @RequestBody RunningStepDTO runningStepDTO) throws URISyntaxException {
        log.debug("REST request to update RunningStep : {}", runningStepDTO);
        if (runningStepDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RunningStepDTO result = runningStepService.save(runningStepDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, runningStepDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /running-steps : get all the runningSteps.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of runningSteps in body
     */
    @GetMapping("/running-steps")
    @Timed
    public ResponseEntity<List<RunningStepDTO>> getAllRunningSteps(RunningStepCriteria criteria, Pageable pageable) {
        log.debug("REST request to get RunningSteps by criteria: {}", criteria);
        Page<RunningStepDTO> page = runningStepQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/running-steps");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /running-steps/count : count all the runningSteps.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/running-steps/count")
    @Timed
    public ResponseEntity<Long> countRunningSteps (RunningStepCriteria criteria) {
        log.debug("REST request to count RunningSteps by criteria: {}", criteria);
        return ResponseEntity.ok().body(runningStepQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /running-steps/:id : get the "id" runningStep.
     *
     * @param id the id of the runningStepDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the runningStepDTO, or with status 404 (Not Found)
     */
    @GetMapping("/running-steps/{id}")
    @Timed
    public ResponseEntity<RunningStepDTO> getRunningStep(@PathVariable Long id) {
        log.debug("REST request to get RunningStep : {}", id);
        Optional<RunningStepDTO> runningStepDTO = runningStepService.findOne(id);
        return ResponseUtil.wrapOrNotFound(runningStepDTO);
    }

    /**
     * DELETE  /running-steps/:id : delete the "id" runningStep.
     *
     * @param id the id of the runningStepDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/running-steps/{id}")
    @Timed
    public ResponseEntity<Void> deleteRunningStep(@PathVariable Long id) {
        log.debug("REST request to delete RunningStep : {}", id);
        runningStepService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
