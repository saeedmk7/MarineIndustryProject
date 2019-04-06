package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.PollService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.PollDTO;
import com.marineindustryproj.service.dto.PollCriteria;
import com.marineindustryproj.service.PollQueryService;
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
 * REST controller for managing Poll.
 */
@RestController
@RequestMapping("/api")
public class PollResource {

    private final Logger log = LoggerFactory.getLogger(PollResource.class);

    private static final String ENTITY_NAME = "poll";

    private final PollService pollService;

    private final PollQueryService pollQueryService;

    public PollResource(PollService pollService, PollQueryService pollQueryService) {
        this.pollService = pollService;
        this.pollQueryService = pollQueryService;
    }

    /**
     * POST  /polls : Create a new poll.
     *
     * @param pollDTO the pollDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pollDTO, or with status 400 (Bad Request) if the poll has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/polls")
    @Timed
    public ResponseEntity<PollDTO> createPoll(@Valid @RequestBody PollDTO pollDTO) throws URISyntaxException {
        log.debug("REST request to save Poll : {}", pollDTO);
        if (pollDTO.getId() != null) {
            throw new BadRequestAlertException("A new poll cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PollDTO result = pollService.save(pollDTO);
        return ResponseEntity.created(new URI("/api/polls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /polls : Updates an existing poll.
     *
     * @param pollDTO the pollDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pollDTO,
     * or with status 400 (Bad Request) if the pollDTO is not valid,
     * or with status 500 (Internal Server Error) if the pollDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/polls")
    @Timed
    public ResponseEntity<PollDTO> updatePoll(@Valid @RequestBody PollDTO pollDTO) throws URISyntaxException {
        log.debug("REST request to update Poll : {}", pollDTO);
        if (pollDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PollDTO result = pollService.save(pollDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pollDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /polls : get all the polls.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of polls in body
     */
    @GetMapping("/polls")
    @Timed
    public ResponseEntity<List<PollDTO>> getAllPolls(PollCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Polls by criteria: {}", criteria);
        Page<PollDTO> page = pollQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/polls");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /polls/count : count all the polls.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/polls/count")
    @Timed
    public ResponseEntity<Long> countPolls (PollCriteria criteria) {
        log.debug("REST request to count Polls by criteria: {}", criteria);
        return ResponseEntity.ok().body(pollQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /polls/:id : get the "id" poll.
     *
     * @param id the id of the pollDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pollDTO, or with status 404 (Not Found)
     */
    @GetMapping("/polls/{id}")
    @Timed
    public ResponseEntity<PollDTO> getPoll(@PathVariable Long id) {
        log.debug("REST request to get Poll : {}", id);
        Optional<PollDTO> pollDTO = pollService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pollDTO);
    }

    /**
     * DELETE  /polls/:id : delete the "id" poll.
     *
     * @param id the id of the pollDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/polls/{id}")
    @Timed
    public ResponseEntity<Void> deletePoll(@PathVariable Long id) {
        log.debug("REST request to delete Poll : {}", id);
        pollService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
