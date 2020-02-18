package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.HeadlineService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.HeadlineDTO;
import com.marineindustryproj.service.dto.HeadlineCriteria;
import com.marineindustryproj.service.HeadlineQueryService;
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
 * REST controller for managing Headline.
 */
@RestController
@RequestMapping("/api")
public class HeadlineResource {

    private final Logger log = LoggerFactory.getLogger(HeadlineResource.class);

    private static final String ENTITY_NAME = "headline";

    private final HeadlineService headlineService;

    private final HeadlineQueryService headlineQueryService;

    public HeadlineResource(HeadlineService headlineService, HeadlineQueryService headlineQueryService) {
        this.headlineService = headlineService;
        this.headlineQueryService = headlineQueryService;
    }

    /**
     * POST  /headlines : Create a new headline.
     *
     * @param headlineDTO the headlineDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new headlineDTO, or with status 400 (Bad Request) if the headline has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/headlines")
    @Timed
    public ResponseEntity<HeadlineDTO> createHeadline(@Valid @RequestBody HeadlineDTO headlineDTO) throws URISyntaxException {
        log.debug("REST request to save Headline : {}", headlineDTO);
        if (headlineDTO.getId() != null) {
            throw new BadRequestAlertException("A new headline cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HeadlineDTO result = headlineService.save(headlineDTO);
        return ResponseEntity.created(new URI("/api/headlines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /headlines : Updates an existing headline.
     *
     * @param headlineDTO the headlineDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated headlineDTO,
     * or with status 400 (Bad Request) if the headlineDTO is not valid,
     * or with status 500 (Internal Server Error) if the headlineDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/headlines")
    @Timed
    public ResponseEntity<HeadlineDTO> updateHeadline(@Valid @RequestBody HeadlineDTO headlineDTO) throws URISyntaxException {
        log.debug("REST request to update Headline : {}", headlineDTO);
        if (headlineDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HeadlineDTO result = headlineService.save(headlineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, headlineDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /headlines : get all the headlines.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of headlines in body
     */
    @GetMapping("/headlines")
    @Timed
    public ResponseEntity<List<HeadlineDTO>> getAllHeadlines(HeadlineCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Headlines by criteria: {}", criteria);
        Page<HeadlineDTO> page = headlineQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/headlines");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /headlines/count : count all the headlines.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/headlines/count")
    @Timed
    public ResponseEntity<Long> countHeadlines(HeadlineCriteria criteria) {
        log.debug("REST request to count Headlines by criteria: {}", criteria);
        return ResponseEntity.ok().body(headlineQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /headlines/:id : get the "id" headline.
     *
     * @param id the id of the headlineDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the headlineDTO, or with status 404 (Not Found)
     */
    @GetMapping("/headlines/{id}")
    @Timed
    public ResponseEntity<HeadlineDTO> getHeadline(@PathVariable Long id) {
        log.debug("REST request to get Headline : {}", id);
        Optional<HeadlineDTO> headlineDTO = headlineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(headlineDTO);
    }

    /**
     * DELETE  /headlines/:id : delete the "id" headline.
     *
     * @param id the id of the headlineDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/headlines/{id}")
    @Timed
    public ResponseEntity<Void> deleteHeadline(@PathVariable Long id) {
        log.debug("REST request to delete Headline : {}", id);
        headlineService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
