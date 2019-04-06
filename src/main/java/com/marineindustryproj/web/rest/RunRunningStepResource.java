package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.RunRunningStepService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.RunRunningStepDTO;
import com.marineindustryproj.service.dto.RunRunningStepCriteria;
import com.marineindustryproj.service.RunRunningStepQueryService;
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
 * REST controller for managing RunRunningStep.
 */
@RestController
@RequestMapping("/api")
public class RunRunningStepResource {

    private final Logger log = LoggerFactory.getLogger(RunRunningStepResource.class);

    private static final String ENTITY_NAME = "runRunningStep";

    private final RunRunningStepService runRunningStepService;

    private final RunRunningStepQueryService runRunningStepQueryService;

    public RunRunningStepResource(RunRunningStepService runRunningStepService, RunRunningStepQueryService runRunningStepQueryService) {
        this.runRunningStepService = runRunningStepService;
        this.runRunningStepQueryService = runRunningStepQueryService;
    }

    /**
     * POST  /run-running-steps : Create a new runRunningStep.
     *
     * @param runRunningStepDTO the runRunningStepDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new runRunningStepDTO, or with status 400 (Bad Request) if the runRunningStep has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/run-running-steps")
    @Timed
    public ResponseEntity<RunRunningStepDTO> createRunRunningStep(@Valid @RequestBody RunRunningStepDTO runRunningStepDTO) throws URISyntaxException {
        log.debug("REST request to save RunRunningStep : {}", runRunningStepDTO);
        if (runRunningStepDTO.getId() != null) {
            throw new BadRequestAlertException("A new runRunningStep cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RunRunningStepDTO result = runRunningStepService.save(runRunningStepDTO);
        return ResponseEntity.created(new URI("/api/run-running-steps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /run-running-steps : Updates an existing runRunningStep.
     *
     * @param runRunningStepDTO the runRunningStepDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated runRunningStepDTO,
     * or with status 400 (Bad Request) if the runRunningStepDTO is not valid,
     * or with status 500 (Internal Server Error) if the runRunningStepDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/run-running-steps")
    @Timed
    public ResponseEntity<RunRunningStepDTO> updateRunRunningStep(@Valid @RequestBody RunRunningStepDTO runRunningStepDTO) throws URISyntaxException {
        log.debug("REST request to update RunRunningStep : {}", runRunningStepDTO);
        if (runRunningStepDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RunRunningStepDTO result = runRunningStepService.save(runRunningStepDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, runRunningStepDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /run-running-steps : get all the runRunningSteps.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of runRunningSteps in body
     */
    @GetMapping("/run-running-steps")
    @Timed
    public ResponseEntity<List<RunRunningStepDTO>> getAllRunRunningSteps(RunRunningStepCriteria criteria, Pageable pageable) {
        log.debug("REST request to get RunRunningSteps by criteria: {}", criteria);
        Page<RunRunningStepDTO> page = runRunningStepQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/run-running-steps");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /run-running-steps/count : count all the runRunningSteps.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/run-running-steps/count")
    @Timed
    public ResponseEntity<Long> countRunRunningSteps (RunRunningStepCriteria criteria) {
        log.debug("REST request to count RunRunningSteps by criteria: {}", criteria);
        return ResponseEntity.ok().body(runRunningStepQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /run-running-steps/:id : get the "id" runRunningStep.
     *
     * @param id the id of the runRunningStepDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the runRunningStepDTO, or with status 404 (Not Found)
     */
    @GetMapping("/run-running-steps/{id}")
    @Timed
    public ResponseEntity<RunRunningStepDTO> getRunRunningStep(@PathVariable Long id) {
        log.debug("REST request to get RunRunningStep : {}", id);
        Optional<RunRunningStepDTO> runRunningStepDTO = runRunningStepService.findOne(id);
        return ResponseUtil.wrapOrNotFound(runRunningStepDTO);
    }

    /**
     * DELETE  /run-running-steps/:id : delete the "id" runRunningStep.
     *
     * @param id the id of the runRunningStepDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/run-running-steps/{id}")
    @Timed
    public ResponseEntity<Void> deleteRunRunningStep(@PathVariable Long id) {
        log.debug("REST request to delete RunRunningStep : {}", id);
        runRunningStepService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
