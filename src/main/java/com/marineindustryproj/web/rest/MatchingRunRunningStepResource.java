package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.MatchingRunRunningStepService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.MatchingRunRunningStepDTO;
import com.marineindustryproj.service.dto.MatchingRunRunningStepCriteria;
import com.marineindustryproj.service.MatchingRunRunningStepQueryService;
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
 * REST controller for managing MatchingRunRunningStep.
 */
@RestController
@RequestMapping("/api")
public class MatchingRunRunningStepResource {

    private final Logger log = LoggerFactory.getLogger(MatchingRunRunningStepResource.class);

    private static final String ENTITY_NAME = "matchingRunRunningStep";

    private final MatchingRunRunningStepService matchingRunRunningStepService;

    private final MatchingRunRunningStepQueryService matchingRunRunningStepQueryService;

    public MatchingRunRunningStepResource(MatchingRunRunningStepService matchingRunRunningStepService, MatchingRunRunningStepQueryService matchingRunRunningStepQueryService) {
        this.matchingRunRunningStepService = matchingRunRunningStepService;
        this.matchingRunRunningStepQueryService = matchingRunRunningStepQueryService;
    }

    /**
     * POST  /matching-run-running-steps : Create a new matchingRunRunningStep.
     *
     * @param matchingRunRunningStepDTO the matchingRunRunningStepDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new matchingRunRunningStepDTO, or with status 400 (Bad Request) if the matchingRunRunningStep has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/matching-run-running-steps")
    @Timed
    public ResponseEntity<MatchingRunRunningStepDTO> createMatchingRunRunningStep(@Valid @RequestBody MatchingRunRunningStepDTO matchingRunRunningStepDTO) throws URISyntaxException {
        log.debug("REST request to save MatchingRunRunningStep : {}", matchingRunRunningStepDTO);
        if (matchingRunRunningStepDTO.getId() != null) {
            throw new BadRequestAlertException("A new matchingRunRunningStep cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MatchingRunRunningStepDTO result = matchingRunRunningStepService.save(matchingRunRunningStepDTO);
        return ResponseEntity.created(new URI("/api/matching-run-running-steps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /matching-run-running-steps : Updates an existing matchingRunRunningStep.
     *
     * @param matchingRunRunningStepDTO the matchingRunRunningStepDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated matchingRunRunningStepDTO,
     * or with status 400 (Bad Request) if the matchingRunRunningStepDTO is not valid,
     * or with status 500 (Internal Server Error) if the matchingRunRunningStepDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/matching-run-running-steps")
    @Timed
    public ResponseEntity<MatchingRunRunningStepDTO> updateMatchingRunRunningStep(@Valid @RequestBody MatchingRunRunningStepDTO matchingRunRunningStepDTO) throws URISyntaxException {
        log.debug("REST request to update MatchingRunRunningStep : {}", matchingRunRunningStepDTO);
        if (matchingRunRunningStepDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MatchingRunRunningStepDTO result = matchingRunRunningStepService.save(matchingRunRunningStepDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, matchingRunRunningStepDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /matching-run-running-steps : get all the matchingRunRunningSteps.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of matchingRunRunningSteps in body
     */
    @GetMapping("/matching-run-running-steps")
    @Timed
    public ResponseEntity<List<MatchingRunRunningStepDTO>> getAllMatchingRunRunningSteps(MatchingRunRunningStepCriteria criteria, Pageable pageable) {
        log.debug("REST request to get MatchingRunRunningSteps by criteria: {}", criteria);
        Page<MatchingRunRunningStepDTO> page = matchingRunRunningStepQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/matching-run-running-steps");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /matching-run-running-steps/count : count all the matchingRunRunningSteps.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/matching-run-running-steps/count")
    @Timed
    public ResponseEntity<Long> countMatchingRunRunningSteps(MatchingRunRunningStepCriteria criteria) {
        log.debug("REST request to count MatchingRunRunningSteps by criteria: {}", criteria);
        return ResponseEntity.ok().body(matchingRunRunningStepQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /matching-run-running-steps/:id : get the "id" matchingRunRunningStep.
     *
     * @param id the id of the matchingRunRunningStepDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the matchingRunRunningStepDTO, or with status 404 (Not Found)
     */
    @GetMapping("/matching-run-running-steps/{id}")
    @Timed
    public ResponseEntity<MatchingRunRunningStepDTO> getMatchingRunRunningStep(@PathVariable Long id) {
        log.debug("REST request to get MatchingRunRunningStep : {}", id);
        Optional<MatchingRunRunningStepDTO> matchingRunRunningStepDTO = matchingRunRunningStepService.findOne(id);
        return ResponseUtil.wrapOrNotFound(matchingRunRunningStepDTO);
    }

    /**
     * DELETE  /matching-run-running-steps/:id : delete the "id" matchingRunRunningStep.
     *
     * @param id the id of the matchingRunRunningStepDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/matching-run-running-steps/{id}")
    @Timed
    public ResponseEntity<Void> deleteMatchingRunRunningStep(@PathVariable Long id) {
        log.debug("REST request to delete MatchingRunRunningStep : {}", id);
        matchingRunRunningStepService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
