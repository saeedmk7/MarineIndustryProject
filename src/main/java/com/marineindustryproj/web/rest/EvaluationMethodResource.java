package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.EvaluationMethodService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.EvaluationMethodDTO;
import com.marineindustryproj.service.dto.EvaluationMethodCriteria;
import com.marineindustryproj.service.EvaluationMethodQueryService;
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
 * REST controller for managing EvaluationMethod.
 */
@RestController
@RequestMapping("/api")
public class EvaluationMethodResource {

    private final Logger log = LoggerFactory.getLogger(EvaluationMethodResource.class);

    private static final String ENTITY_NAME = "evaluationMethod";

    private final EvaluationMethodService evaluationMethodService;

    private final EvaluationMethodQueryService evaluationMethodQueryService;

    public EvaluationMethodResource(EvaluationMethodService evaluationMethodService, EvaluationMethodQueryService evaluationMethodQueryService) {
        this.evaluationMethodService = evaluationMethodService;
        this.evaluationMethodQueryService = evaluationMethodQueryService;
    }

    /**
     * POST  /evaluation-methods : Create a new evaluationMethod.
     *
     * @param evaluationMethodDTO the evaluationMethodDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new evaluationMethodDTO, or with status 400 (Bad Request) if the evaluationMethod has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/evaluation-methods")
    @Timed
    public ResponseEntity<EvaluationMethodDTO> createEvaluationMethod(@Valid @RequestBody EvaluationMethodDTO evaluationMethodDTO) throws URISyntaxException {
        log.debug("REST request to save EvaluationMethod : {}", evaluationMethodDTO);
        if (evaluationMethodDTO.getId() != null) {
            throw new BadRequestAlertException("A new evaluationMethod cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EvaluationMethodDTO result = evaluationMethodService.save(evaluationMethodDTO);
        return ResponseEntity.created(new URI("/api/evaluation-methods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /evaluation-methods : Updates an existing evaluationMethod.
     *
     * @param evaluationMethodDTO the evaluationMethodDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated evaluationMethodDTO,
     * or with status 400 (Bad Request) if the evaluationMethodDTO is not valid,
     * or with status 500 (Internal Server Error) if the evaluationMethodDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/evaluation-methods")
    @Timed
    public ResponseEntity<EvaluationMethodDTO> updateEvaluationMethod(@Valid @RequestBody EvaluationMethodDTO evaluationMethodDTO) throws URISyntaxException {
        log.debug("REST request to update EvaluationMethod : {}", evaluationMethodDTO);
        if (evaluationMethodDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EvaluationMethodDTO result = evaluationMethodService.save(evaluationMethodDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, evaluationMethodDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /evaluation-methods : get all the evaluationMethods.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of evaluationMethods in body
     */
    @GetMapping("/evaluation-methods")
    @Timed
    public ResponseEntity<List<EvaluationMethodDTO>> getAllEvaluationMethods(EvaluationMethodCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EvaluationMethods by criteria: {}", criteria);
        Page<EvaluationMethodDTO> page = evaluationMethodQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/evaluation-methods");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /evaluation-methods/count : count all the evaluationMethods.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/evaluation-methods/count")
    @Timed
    public ResponseEntity<Long> countEvaluationMethods (EvaluationMethodCriteria criteria) {
        log.debug("REST request to count EvaluationMethods by criteria: {}", criteria);
        return ResponseEntity.ok().body(evaluationMethodQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /evaluation-methods/:id : get the "id" evaluationMethod.
     *
     * @param id the id of the evaluationMethodDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the evaluationMethodDTO, or with status 404 (Not Found)
     */
    @GetMapping("/evaluation-methods/{id}")
    @Timed
    public ResponseEntity<EvaluationMethodDTO> getEvaluationMethod(@PathVariable Long id) {
        log.debug("REST request to get EvaluationMethod : {}", id);
        Optional<EvaluationMethodDTO> evaluationMethodDTO = evaluationMethodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(evaluationMethodDTO);
    }

    /**
     * DELETE  /evaluation-methods/:id : delete the "id" evaluationMethod.
     *
     * @param id the id of the evaluationMethodDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/evaluation-methods/{id}")
    @Timed
    public ResponseEntity<Void> deleteEvaluationMethod(@PathVariable Long id) {
        log.debug("REST request to delete EvaluationMethod : {}", id);
        evaluationMethodService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
