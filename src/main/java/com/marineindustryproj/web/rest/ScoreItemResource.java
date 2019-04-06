package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.ScoreItemService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.ScoreItemDTO;
import com.marineindustryproj.service.dto.ScoreItemCriteria;
import com.marineindustryproj.service.ScoreItemQueryService;
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
 * REST controller for managing ScoreItem.
 */
@RestController
@RequestMapping("/api")
public class ScoreItemResource {

    private final Logger log = LoggerFactory.getLogger(ScoreItemResource.class);

    private static final String ENTITY_NAME = "scoreItem";

    private final ScoreItemService scoreItemService;

    private final ScoreItemQueryService scoreItemQueryService;

    public ScoreItemResource(ScoreItemService scoreItemService, ScoreItemQueryService scoreItemQueryService) {
        this.scoreItemService = scoreItemService;
        this.scoreItemQueryService = scoreItemQueryService;
    }

    /**
     * POST  /score-items : Create a new scoreItem.
     *
     * @param scoreItemDTO the scoreItemDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new scoreItemDTO, or with status 400 (Bad Request) if the scoreItem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/score-items")
    @Timed
    public ResponseEntity<ScoreItemDTO> createScoreItem(@Valid @RequestBody ScoreItemDTO scoreItemDTO) throws URISyntaxException {
        log.debug("REST request to save ScoreItem : {}", scoreItemDTO);
        if (scoreItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new scoreItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ScoreItemDTO result = scoreItemService.save(scoreItemDTO);
        return ResponseEntity.created(new URI("/api/score-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /score-items : Updates an existing scoreItem.
     *
     * @param scoreItemDTO the scoreItemDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated scoreItemDTO,
     * or with status 400 (Bad Request) if the scoreItemDTO is not valid,
     * or with status 500 (Internal Server Error) if the scoreItemDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/score-items")
    @Timed
    public ResponseEntity<ScoreItemDTO> updateScoreItem(@Valid @RequestBody ScoreItemDTO scoreItemDTO) throws URISyntaxException {
        log.debug("REST request to update ScoreItem : {}", scoreItemDTO);
        if (scoreItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ScoreItemDTO result = scoreItemService.save(scoreItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, scoreItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /score-items : get all the scoreItems.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of scoreItems in body
     */
    @GetMapping("/score-items")
    @Timed
    public ResponseEntity<List<ScoreItemDTO>> getAllScoreItems(ScoreItemCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ScoreItems by criteria: {}", criteria);
        Page<ScoreItemDTO> page = scoreItemQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/score-items");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /score-items/count : count all the scoreItems.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/score-items/count")
    @Timed
    public ResponseEntity<Long> countScoreItems (ScoreItemCriteria criteria) {
        log.debug("REST request to count ScoreItems by criteria: {}", criteria);
        return ResponseEntity.ok().body(scoreItemQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /score-items/:id : get the "id" scoreItem.
     *
     * @param id the id of the scoreItemDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the scoreItemDTO, or with status 404 (Not Found)
     */
    @GetMapping("/score-items/{id}")
    @Timed
    public ResponseEntity<ScoreItemDTO> getScoreItem(@PathVariable Long id) {
        log.debug("REST request to get ScoreItem : {}", id);
        Optional<ScoreItemDTO> scoreItemDTO = scoreItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(scoreItemDTO);
    }

    /**
     * DELETE  /score-items/:id : delete the "id" scoreItem.
     *
     * @param id the id of the scoreItemDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/score-items/{id}")
    @Timed
    public ResponseEntity<Void> deleteScoreItem(@PathVariable Long id) {
        log.debug("REST request to delete ScoreItem : {}", id);
        scoreItemService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
