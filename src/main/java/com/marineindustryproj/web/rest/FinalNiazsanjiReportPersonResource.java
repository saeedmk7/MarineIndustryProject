package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.FinalNiazsanjiReportPersonService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportPersonDTO;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportPersonCriteria;
import com.marineindustryproj.service.FinalNiazsanjiReportPersonQueryService;
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
 * REST controller for managing FinalNiazsanjiReportPerson.
 */
@RestController
@RequestMapping("/api")
public class FinalNiazsanjiReportPersonResource {

    private final Logger log = LoggerFactory.getLogger(FinalNiazsanjiReportPersonResource.class);

    private static final String ENTITY_NAME = "finalNiazsanjiReportPerson";

    private final FinalNiazsanjiReportPersonService finalNiazsanjiReportPersonService;

    private final FinalNiazsanjiReportPersonQueryService finalNiazsanjiReportPersonQueryService;

    public FinalNiazsanjiReportPersonResource(FinalNiazsanjiReportPersonService finalNiazsanjiReportPersonService, FinalNiazsanjiReportPersonQueryService finalNiazsanjiReportPersonQueryService) {
        this.finalNiazsanjiReportPersonService = finalNiazsanjiReportPersonService;
        this.finalNiazsanjiReportPersonQueryService = finalNiazsanjiReportPersonQueryService;
    }

    /**
     * POST  /final-niazsanji-report-people : Create a new finalNiazsanjiReportPerson.
     *
     * @param finalNiazsanjiReportPersonDTO the finalNiazsanjiReportPersonDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new finalNiazsanjiReportPersonDTO, or with status 400 (Bad Request) if the finalNiazsanjiReportPerson has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/final-niazsanji-report-people")
    @Timed
    public ResponseEntity<FinalNiazsanjiReportPersonDTO> createFinalNiazsanjiReportPerson(@Valid @RequestBody FinalNiazsanjiReportPersonDTO finalNiazsanjiReportPersonDTO) throws URISyntaxException {
        log.debug("REST request to save FinalNiazsanjiReportPerson : {}", finalNiazsanjiReportPersonDTO);
        if (finalNiazsanjiReportPersonDTO.getId() != null) {
            throw new BadRequestAlertException("A new finalNiazsanjiReportPerson cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FinalNiazsanjiReportPersonDTO result = finalNiazsanjiReportPersonService.save(finalNiazsanjiReportPersonDTO);
        return ResponseEntity.created(new URI("/api/final-niazsanji-report-people/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /final-niazsanji-report-people : Updates an existing finalNiazsanjiReportPerson.
     *
     * @param finalNiazsanjiReportPersonDTO the finalNiazsanjiReportPersonDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated finalNiazsanjiReportPersonDTO,
     * or with status 400 (Bad Request) if the finalNiazsanjiReportPersonDTO is not valid,
     * or with status 500 (Internal Server Error) if the finalNiazsanjiReportPersonDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/final-niazsanji-report-people")
    @Timed
    public ResponseEntity<FinalNiazsanjiReportPersonDTO> updateFinalNiazsanjiReportPerson(@Valid @RequestBody FinalNiazsanjiReportPersonDTO finalNiazsanjiReportPersonDTO) throws URISyntaxException {
        log.debug("REST request to update FinalNiazsanjiReportPerson : {}", finalNiazsanjiReportPersonDTO);
        if (finalNiazsanjiReportPersonDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FinalNiazsanjiReportPersonDTO result = finalNiazsanjiReportPersonService.save(finalNiazsanjiReportPersonDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, finalNiazsanjiReportPersonDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /final-niazsanji-report-people : get all the finalNiazsanjiReportPeople.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of finalNiazsanjiReportPeople in body
     */
    @GetMapping("/final-niazsanji-report-people")
    @Timed
    public ResponseEntity<List<FinalNiazsanjiReportPersonDTO>> getAllFinalNiazsanjiReportPeople(FinalNiazsanjiReportPersonCriteria criteria, Pageable pageable) {
        log.debug("REST request to get FinalNiazsanjiReportPeople by criteria: {}", criteria);
        Page<FinalNiazsanjiReportPersonDTO> page = finalNiazsanjiReportPersonQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/final-niazsanji-report-people");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /final-niazsanji-report-people/count : count all the finalNiazsanjiReportPeople.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/final-niazsanji-report-people/count")
    @Timed
    public ResponseEntity<Long> countFinalNiazsanjiReportPeople (FinalNiazsanjiReportPersonCriteria criteria) {
        log.debug("REST request to count FinalNiazsanjiReportPeople by criteria: {}", criteria);
        return ResponseEntity.ok().body(finalNiazsanjiReportPersonQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /final-niazsanji-report-people/:id : get the "id" finalNiazsanjiReportPerson.
     *
     * @param id the id of the finalNiazsanjiReportPersonDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the finalNiazsanjiReportPersonDTO, or with status 404 (Not Found)
     */
    @GetMapping("/final-niazsanji-report-people/{id}")
    @Timed
    public ResponseEntity<FinalNiazsanjiReportPersonDTO> getFinalNiazsanjiReportPerson(@PathVariable Long id) {
        log.debug("REST request to get FinalNiazsanjiReportPerson : {}", id);
        Optional<FinalNiazsanjiReportPersonDTO> finalNiazsanjiReportPersonDTO = finalNiazsanjiReportPersonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(finalNiazsanjiReportPersonDTO);
    }

    /**
     * DELETE  /final-niazsanji-report-people/:id : delete the "id" finalNiazsanjiReportPerson.
     *
     * @param id the id of the finalNiazsanjiReportPersonDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/final-niazsanji-report-people/{id}")
    @Timed
    public ResponseEntity<Void> deleteFinalNiazsanjiReportPerson(@PathVariable Long id) {
        log.debug("REST request to delete FinalNiazsanjiReportPerson : {}", id);
        finalNiazsanjiReportPersonService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
