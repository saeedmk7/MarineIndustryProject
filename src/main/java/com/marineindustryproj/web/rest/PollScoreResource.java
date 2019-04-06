package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.PollScoreService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.PollScoreDTO;
import com.marineindustryproj.service.dto.PollScoreCriteria;
import com.marineindustryproj.service.PollScoreQueryService;
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
 * REST controller for managing PollScore.
 */
@RestController
@RequestMapping("/api")
public class PollScoreResource {

    private final Logger log = LoggerFactory.getLogger(PollScoreResource.class);

    private static final String ENTITY_NAME = "pollScore";

    private final PollScoreService pollScoreService;

    private final PollScoreQueryService pollScoreQueryService;

    public PollScoreResource(PollScoreService pollScoreService, PollScoreQueryService pollScoreQueryService) {
        this.pollScoreService = pollScoreService;
        this.pollScoreQueryService = pollScoreQueryService;
    }

    /**
     * POST  /poll-scores : Create a new pollScore.
     *
     * @param pollScoreDTO the pollScoreDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pollScoreDTO, or with status 400 (Bad Request) if the pollScore has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/poll-scores")
    @Timed
    public ResponseEntity<PollScoreDTO> createPollScore(@Valid @RequestBody PollScoreDTO pollScoreDTO) throws URISyntaxException {
        log.debug("REST request to save PollScore : {}", pollScoreDTO);
        if (pollScoreDTO.getId() != null) {
            throw new BadRequestAlertException("A new pollScore cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PollScoreDTO result = pollScoreService.save(pollScoreDTO);
        return ResponseEntity.created(new URI("/api/poll-scores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /poll-scores : Updates an existing pollScore.
     *
     * @param pollScoreDTO the pollScoreDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pollScoreDTO,
     * or with status 400 (Bad Request) if the pollScoreDTO is not valid,
     * or with status 500 (Internal Server Error) if the pollScoreDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/poll-scores")
    @Timed
    public ResponseEntity<PollScoreDTO> updatePollScore(@Valid @RequestBody PollScoreDTO pollScoreDTO) throws URISyntaxException {
        log.debug("REST request to update PollScore : {}", pollScoreDTO);
        if (pollScoreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PollScoreDTO result = pollScoreService.save(pollScoreDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pollScoreDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /poll-scores : get all the pollScores.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of pollScores in body
     */
    @GetMapping("/poll-scores")
    @Timed
    public ResponseEntity<List<PollScoreDTO>> getAllPollScores(PollScoreCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PollScores by criteria: {}", criteria);
        Page<PollScoreDTO> page = pollScoreQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/poll-scores");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /poll-scores/count : count all the pollScores.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/poll-scores/count")
    @Timed
    public ResponseEntity<Long> countPollScores (PollScoreCriteria criteria) {
        log.debug("REST request to count PollScores by criteria: {}", criteria);
        return ResponseEntity.ok().body(pollScoreQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /poll-scores/:id : get the "id" pollScore.
     *
     * @param id the id of the pollScoreDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pollScoreDTO, or with status 404 (Not Found)
     */
    @GetMapping("/poll-scores/{id}")
    @Timed
    public ResponseEntity<PollScoreDTO> getPollScore(@PathVariable Long id) {
        log.debug("REST request to get PollScore : {}", id);
        Optional<PollScoreDTO> pollScoreDTO = pollScoreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pollScoreDTO);
    }

    /**
     * DELETE  /poll-scores/:id : delete the "id" pollScore.
     *
     * @param id the id of the pollScoreDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/poll-scores/{id}")
    @Timed
    public ResponseEntity<Void> deletePollScore(@PathVariable Long id) {
        log.debug("REST request to delete PollScore : {}", id);
        pollScoreService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
