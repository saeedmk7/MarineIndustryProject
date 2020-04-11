package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.EvaluatorOpinionService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.EvaluatorOpinionDTO;
import com.marineindustryproj.service.dto.EvaluatorOpinionCriteria;
import com.marineindustryproj.service.EvaluatorOpinionQueryService;
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
 * REST controller for managing EvaluatorOpinion.
 */
@RestController
@RequestMapping("/api")
public class EvaluatorOpinionResource {

    private final Logger log = LoggerFactory.getLogger(EvaluatorOpinionResource.class);

    private static final String ENTITY_NAME = "evaluatorOpinion";

    private final EvaluatorOpinionService evaluatorOpinionService;

    private final EvaluatorOpinionQueryService evaluatorOpinionQueryService;

    public EvaluatorOpinionResource(EvaluatorOpinionService evaluatorOpinionService, EvaluatorOpinionQueryService evaluatorOpinionQueryService) {
        this.evaluatorOpinionService = evaluatorOpinionService;
        this.evaluatorOpinionQueryService = evaluatorOpinionQueryService;
    }

    /**
     * POST  /evaluator-opinions : Create a new evaluatorOpinion.
     *
     * @param evaluatorOpinionDTO the evaluatorOpinionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new evaluatorOpinionDTO, or with status 400 (Bad Request) if the evaluatorOpinion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/evaluator-opinions")
    @Timed
    public ResponseEntity<EvaluatorOpinionDTO> createEvaluatorOpinion(@Valid @RequestBody EvaluatorOpinionDTO evaluatorOpinionDTO) throws URISyntaxException {
        log.debug("REST request to save EvaluatorOpinion : {}", evaluatorOpinionDTO);
        if (evaluatorOpinionDTO.getId() != null) {
            throw new BadRequestAlertException("A new evaluatorOpinion cannot already have an ID", ENTITY_NAME, "idexists");
        }

        evaluatorOpinionDTO.setCreateDate(ZonedDateTime.now());
        evaluatorOpinionDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        EvaluatorOpinionDTO result = evaluatorOpinionService.save(evaluatorOpinionDTO);
        return ResponseEntity.created(new URI("/api/evaluator-opinions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /evaluator-opinions : Updates an existing evaluatorOpinion.
     *
     * @param evaluatorOpinionDTO the evaluatorOpinionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated evaluatorOpinionDTO,
     * or with status 400 (Bad Request) if the evaluatorOpinionDTO is not valid,
     * or with status 500 (Internal Server Error) if the evaluatorOpinionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/evaluator-opinions")
    @Timed
    public ResponseEntity<EvaluatorOpinionDTO> updateEvaluatorOpinion(@Valid @RequestBody EvaluatorOpinionDTO evaluatorOpinionDTO) throws URISyntaxException {
        log.debug("REST request to update EvaluatorOpinion : {}", evaluatorOpinionDTO);
        if (evaluatorOpinionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        EvaluatorOpinionDTO evaluatorOpinion = evaluatorOpinionService.findOne(evaluatorOpinionDTO.getId()).get();

        evaluatorOpinionDTO.setCreateUserLogin(evaluatorOpinion.getCreateUserLogin());
        evaluatorOpinionDTO.setCreateDate(evaluatorOpinion.getCreateDate());
        evaluatorOpinionDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        evaluatorOpinionDTO.setModifyDate(ZonedDateTime.now());

        EvaluatorOpinionDTO result = evaluatorOpinionService.save(evaluatorOpinionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, evaluatorOpinionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /evaluator-opinions : get all the evaluatorOpinions.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of evaluatorOpinions in body
     */
    @GetMapping("/evaluator-opinions")
    @Timed
    public ResponseEntity<List<EvaluatorOpinionDTO>> getAllEvaluatorOpinions(EvaluatorOpinionCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EvaluatorOpinions by criteria: {}", criteria);
        Page<EvaluatorOpinionDTO> page = evaluatorOpinionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/evaluator-opinions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /evaluator-opinions/count : count all the evaluatorOpinions.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/evaluator-opinions/count")
    @Timed
    public ResponseEntity<Long> countEvaluatorOpinions(EvaluatorOpinionCriteria criteria) {
        log.debug("REST request to count EvaluatorOpinions by criteria: {}", criteria);
        return ResponseEntity.ok().body(evaluatorOpinionQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /evaluator-opinions/:id : get the "id" evaluatorOpinion.
     *
     * @param id the id of the evaluatorOpinionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the evaluatorOpinionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/evaluator-opinions/{id}")
    @Timed
    public ResponseEntity<EvaluatorOpinionDTO> getEvaluatorOpinion(@PathVariable Long id) {
        log.debug("REST request to get EvaluatorOpinion : {}", id);
        Optional<EvaluatorOpinionDTO> evaluatorOpinionDTO = evaluatorOpinionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(evaluatorOpinionDTO);
    }

    /**
     * DELETE  /evaluator-opinions/:id : delete the "id" evaluatorOpinion.
     *
     * @param id the id of the evaluatorOpinionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/evaluator-opinions/{id}")
    @Timed
    public ResponseEntity<Void> deleteEvaluatorOpinion(@PathVariable Long id) {
        log.debug("REST request to delete EvaluatorOpinion : {}", id);
        evaluatorOpinionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
