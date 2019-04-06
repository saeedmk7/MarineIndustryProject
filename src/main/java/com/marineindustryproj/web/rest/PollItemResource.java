package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.PollItemService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.PollItemDTO;
import com.marineindustryproj.service.dto.PollItemCriteria;
import com.marineindustryproj.service.PollItemQueryService;
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
 * REST controller for managing PollItem.
 */
@RestController
@RequestMapping("/api")
public class PollItemResource {

    private final Logger log = LoggerFactory.getLogger(PollItemResource.class);

    private static final String ENTITY_NAME = "pollItem";

    private final PollItemService pollItemService;

    private final PollItemQueryService pollItemQueryService;

    public PollItemResource(PollItemService pollItemService, PollItemQueryService pollItemQueryService) {
        this.pollItemService = pollItemService;
        this.pollItemQueryService = pollItemQueryService;
    }

    /**
     * POST  /poll-items : Create a new pollItem.
     *
     * @param pollItemDTO the pollItemDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pollItemDTO, or with status 400 (Bad Request) if the pollItem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/poll-items")
    @Timed
    public ResponseEntity<PollItemDTO> createPollItem(@Valid @RequestBody PollItemDTO pollItemDTO) throws URISyntaxException {
        log.debug("REST request to save PollItem : {}", pollItemDTO);
        if (pollItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new pollItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PollItemDTO result = pollItemService.save(pollItemDTO);
        return ResponseEntity.created(new URI("/api/poll-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /poll-items : Updates an existing pollItem.
     *
     * @param pollItemDTO the pollItemDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pollItemDTO,
     * or with status 400 (Bad Request) if the pollItemDTO is not valid,
     * or with status 500 (Internal Server Error) if the pollItemDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/poll-items")
    @Timed
    public ResponseEntity<PollItemDTO> updatePollItem(@Valid @RequestBody PollItemDTO pollItemDTO) throws URISyntaxException {
        log.debug("REST request to update PollItem : {}", pollItemDTO);
        if (pollItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PollItemDTO result = pollItemService.save(pollItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pollItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /poll-items : get all the pollItems.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of pollItems in body
     */
    @GetMapping("/poll-items")
    @Timed
    public ResponseEntity<List<PollItemDTO>> getAllPollItems(PollItemCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PollItems by criteria: {}", criteria);
        Page<PollItemDTO> page = pollItemQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/poll-items");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /poll-items/count : count all the pollItems.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/poll-items/count")
    @Timed
    public ResponseEntity<Long> countPollItems (PollItemCriteria criteria) {
        log.debug("REST request to count PollItems by criteria: {}", criteria);
        return ResponseEntity.ok().body(pollItemQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /poll-items/:id : get the "id" pollItem.
     *
     * @param id the id of the pollItemDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pollItemDTO, or with status 404 (Not Found)
     */
    @GetMapping("/poll-items/{id}")
    @Timed
    public ResponseEntity<PollItemDTO> getPollItem(@PathVariable Long id) {
        log.debug("REST request to get PollItem : {}", id);
        Optional<PollItemDTO> pollItemDTO = pollItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pollItemDTO);
    }

    /**
     * DELETE  /poll-items/:id : delete the "id" pollItem.
     *
     * @param id the id of the pollItemDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/poll-items/{id}")
    @Timed
    public ResponseEntity<Void> deletePollItem(@PathVariable Long id) {
        log.debug("REST request to delete PollItem : {}", id);
        pollItemService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
