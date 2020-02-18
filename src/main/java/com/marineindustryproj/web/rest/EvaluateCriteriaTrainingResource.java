package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.EvaluateCriteriaTrainingService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.EvaluateCriteriaTrainingDTO;
import com.marineindustryproj.service.dto.EvaluateCriteriaTrainingCriteria;
import com.marineindustryproj.service.EvaluateCriteriaTrainingQueryService;
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
import java.util.UUID;

/**
 * REST controller for managing EvaluateCriteriaTraining.
 */
@RestController
@RequestMapping("/api")
public class EvaluateCriteriaTrainingResource {

    private final Logger log = LoggerFactory.getLogger(EvaluateCriteriaTrainingResource.class);

    private static final String ENTITY_NAME = "evaluateCriteriaTraining";

    private final EvaluateCriteriaTrainingService evaluateCriteriaTrainingService;

    private final EvaluateCriteriaTrainingQueryService evaluateCriteriaTrainingQueryService;

    public EvaluateCriteriaTrainingResource(EvaluateCriteriaTrainingService evaluateCriteriaTrainingService, EvaluateCriteriaTrainingQueryService evaluateCriteriaTrainingQueryService) {
        this.evaluateCriteriaTrainingService = evaluateCriteriaTrainingService;
        this.evaluateCriteriaTrainingQueryService = evaluateCriteriaTrainingQueryService;
    }

    /**
     * POST  /evaluate-criteria-trainings : Create a new evaluateCriteriaTraining.
     *
     * @param evaluateCriteriaTrainingDTO the evaluateCriteriaTrainingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new evaluateCriteriaTrainingDTO, or with status 400 (Bad Request) if the evaluateCriteriaTraining has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/evaluate-criteria-trainings")
    @Timed
    public ResponseEntity<EvaluateCriteriaTrainingDTO> createEvaluateCriteriaTraining(@Valid @RequestBody EvaluateCriteriaTrainingDTO evaluateCriteriaTrainingDTO) throws URISyntaxException {
        log.debug("REST request to save EvaluateCriteriaTraining : {}", evaluateCriteriaTrainingDTO);
        if (evaluateCriteriaTrainingDTO.getId() != null) {
            throw new BadRequestAlertException("A new evaluateCriteriaTraining cannot already have an ID", ENTITY_NAME, "idexists");
        }

        evaluateCriteriaTrainingDTO.setGuid(UUID.randomUUID().toString());
        evaluateCriteriaTrainingDTO.setCreateDate(ZonedDateTime.now());
        evaluateCriteriaTrainingDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        EvaluateCriteriaTrainingDTO result = evaluateCriteriaTrainingService.save(evaluateCriteriaTrainingDTO);
        return ResponseEntity.created(new URI("/api/evaluate-criteria-trainings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /evaluate-criteria-trainings : Updates an existing evaluateCriteriaTraining.
     *
     * @param evaluateCriteriaTrainingDTO the evaluateCriteriaTrainingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated evaluateCriteriaTrainingDTO,
     * or with status 400 (Bad Request) if the evaluateCriteriaTrainingDTO is not valid,
     * or with status 500 (Internal Server Error) if the evaluateCriteriaTrainingDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/evaluate-criteria-trainings")
    @Timed
    public ResponseEntity<EvaluateCriteriaTrainingDTO> updateEvaluateCriteriaTraining(@Valid @RequestBody EvaluateCriteriaTrainingDTO evaluateCriteriaTrainingDTO) throws URISyntaxException {
        log.debug("REST request to update EvaluateCriteriaTraining : {}", evaluateCriteriaTrainingDTO);
        if (evaluateCriteriaTrainingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        EvaluateCriteriaTrainingDTO evaluateCriteriaTraining = evaluateCriteriaTrainingService.findOne(evaluateCriteriaTrainingDTO.getId()).get();

        evaluateCriteriaTrainingDTO.setGuid(evaluateCriteriaTraining.getGuid());
        evaluateCriteriaTrainingDTO.setCreateUserLogin(evaluateCriteriaTraining.getCreateUserLogin());
        evaluateCriteriaTrainingDTO.setCreateDate(evaluateCriteriaTraining.getCreateDate());
        evaluateCriteriaTrainingDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        evaluateCriteriaTrainingDTO.setModifyDate(ZonedDateTime.now());

        EvaluateCriteriaTrainingDTO result = evaluateCriteriaTrainingService.save(evaluateCriteriaTrainingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, evaluateCriteriaTrainingDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /evaluate-criteria-trainings : get all the evaluateCriteriaTrainings.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of evaluateCriteriaTrainings in body
     */
    @GetMapping("/evaluate-criteria-trainings")
    @Timed
    public ResponseEntity<List<EvaluateCriteriaTrainingDTO>> getAllEvaluateCriteriaTrainings(EvaluateCriteriaTrainingCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EvaluateCriteriaTrainings by criteria: {}", criteria);
        Page<EvaluateCriteriaTrainingDTO> page = evaluateCriteriaTrainingQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/evaluate-criteria-trainings");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /evaluate-criteria-trainings/count : count all the evaluateCriteriaTrainings.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/evaluate-criteria-trainings/count")
    @Timed
    public ResponseEntity<Long> countEvaluateCriteriaTrainings(EvaluateCriteriaTrainingCriteria criteria) {
        log.debug("REST request to count EvaluateCriteriaTrainings by criteria: {}", criteria);
        return ResponseEntity.ok().body(evaluateCriteriaTrainingQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /evaluate-criteria-trainings/:id : get the "id" evaluateCriteriaTraining.
     *
     * @param id the id of the evaluateCriteriaTrainingDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the evaluateCriteriaTrainingDTO, or with status 404 (Not Found)
     */
    @GetMapping("/evaluate-criteria-trainings/{id}")
    @Timed
    public ResponseEntity<EvaluateCriteriaTrainingDTO> getEvaluateCriteriaTraining(@PathVariable Long id) {
        log.debug("REST request to get EvaluateCriteriaTraining : {}", id);
        Optional<EvaluateCriteriaTrainingDTO> evaluateCriteriaTrainingDTO = evaluateCriteriaTrainingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(evaluateCriteriaTrainingDTO);
    }

    /**
     * DELETE  /evaluate-criteria-trainings/:id : delete the "id" evaluateCriteriaTraining.
     *
     * @param id the id of the evaluateCriteriaTrainingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/evaluate-criteria-trainings/{id}")
    @Timed
    public ResponseEntity<Void> deleteEvaluateCriteriaTraining(@PathVariable Long id) {
        log.debug("REST request to delete EvaluateCriteriaTraining : {}", id);
        evaluateCriteriaTrainingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
