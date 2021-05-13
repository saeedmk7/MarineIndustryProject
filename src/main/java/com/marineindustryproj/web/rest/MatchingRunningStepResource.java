package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.MatchingRunningStepService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.MatchingRunningStepDTO;
import com.marineindustryproj.service.dto.MatchingRunningStepCriteria;
import com.marineindustryproj.service.MatchingRunningStepQueryService;
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

/**
 * REST controller for managing MatchingRunningStep.
 */
@RestController
@RequestMapping("/api")
public class MatchingRunningStepResource {

    private final Logger log = LoggerFactory.getLogger(MatchingRunningStepResource.class);

    private static final String ENTITY_NAME = "matchingRunningStep";

    private final MatchingRunningStepService matchingRunningStepService;

    private final MatchingRunningStepQueryService matchingRunningStepQueryService;

    public MatchingRunningStepResource(MatchingRunningStepService matchingRunningStepService, MatchingRunningStepQueryService matchingRunningStepQueryService) {
        this.matchingRunningStepService = matchingRunningStepService;
        this.matchingRunningStepQueryService = matchingRunningStepQueryService;
    }

    /**
     * POST  /matching-running-steps : Create a new matchingRunningStep.
     *
     * @param matchingRunningStepDTO the matchingRunningStepDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new matchingRunningStepDTO, or with status 400 (Bad Request) if the matchingRunningStep has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/matching-running-steps")
    @Timed
    public ResponseEntity<MatchingRunningStepDTO> createMatchingRunningStep(@Valid @RequestBody MatchingRunningStepDTO matchingRunningStepDTO) throws URISyntaxException {
        log.debug("REST request to save MatchingRunningStep : {}", matchingRunningStepDTO);
        if (matchingRunningStepDTO.getId() != null) {
            throw new BadRequestAlertException("A new matchingRunningStep cannot already have an ID", ENTITY_NAME, "idexists");
        }

        matchingRunningStepDTO.setCreateDate(ZonedDateTime.now());
        matchingRunningStepDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        MatchingRunningStepDTO result = matchingRunningStepService.save(matchingRunningStepDTO);
        return ResponseEntity.created(new URI("/api/matching-running-steps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /matching-running-steps : Updates an existing matchingRunningStep.
     *
     * @param matchingRunningStepDTO the matchingRunningStepDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated matchingRunningStepDTO,
     * or with status 400 (Bad Request) if the matchingRunningStepDTO is not valid,
     * or with status 500 (Internal Server Error) if the matchingRunningStepDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/matching-running-steps")
    @Timed
    public ResponseEntity<MatchingRunningStepDTO> updateMatchingRunningStep(@Valid @RequestBody MatchingRunningStepDTO matchingRunningStepDTO) throws URISyntaxException {
        log.debug("REST request to update MatchingRunningStep : {}", matchingRunningStepDTO);
        if (matchingRunningStepDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        MatchingRunningStepDTO matchingRunningStep = matchingRunningStepService.findOne(matchingRunningStepDTO.getId()).get();

        if(matchingRunningStep.getCreateUserLogin() == null)
            matchingRunningStepDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        else
            matchingRunningStepDTO.setCreateUserLogin(matchingRunningStep.getCreateUserLogin());
        matchingRunningStepDTO.setCreateDate(matchingRunningStep.getCreateDate());
        matchingRunningStepDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        matchingRunningStepDTO.setModifyDate(ZonedDateTime.now());


        MatchingRunningStepDTO result = matchingRunningStepService.save(matchingRunningStepDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, matchingRunningStepDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /matching-running-steps : get all the matchingRunningSteps.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of matchingRunningSteps in body
     */
    @GetMapping("/matching-running-steps")
    @Timed
    public ResponseEntity<List<MatchingRunningStepDTO>> getAllMatchingRunningSteps(MatchingRunningStepCriteria criteria, Pageable pageable) {
        log.debug("REST request to get MatchingRunningSteps by criteria: {}", criteria);
        Page<MatchingRunningStepDTO> page = matchingRunningStepQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/matching-running-steps");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /matching-running-steps/count : count all the matchingRunningSteps.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/matching-running-steps/count")
    @Timed
    public ResponseEntity<Long> countMatchingRunningSteps(MatchingRunningStepCriteria criteria) {
        log.debug("REST request to count MatchingRunningSteps by criteria: {}", criteria);
        return ResponseEntity.ok().body(matchingRunningStepQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /matching-running-steps/:id : get the "id" matchingRunningStep.
     *
     * @param id the id of the matchingRunningStepDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the matchingRunningStepDTO, or with status 404 (Not Found)
     */
    @GetMapping("/matching-running-steps/{id}")
    @Timed
    public ResponseEntity<MatchingRunningStepDTO> getMatchingRunningStep(@PathVariable Long id) {
        log.debug("REST request to get MatchingRunningStep : {}", id);
        Optional<MatchingRunningStepDTO> matchingRunningStepDTO = matchingRunningStepService.findOne(id);
        return ResponseUtil.wrapOrNotFound(matchingRunningStepDTO);
    }

    /**
     * DELETE  /matching-running-steps/:id : delete the "id" matchingRunningStep.
     *
     * @param id the id of the matchingRunningStepDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/matching-running-steps/{id}")
    @Timed
    public ResponseEntity<Void> deleteMatchingRunningStep(@PathVariable Long id) {
        log.debug("REST request to delete MatchingRunningStep : {}", id);
        matchingRunningStepService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
