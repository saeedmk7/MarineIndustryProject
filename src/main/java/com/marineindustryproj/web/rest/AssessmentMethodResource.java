package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.AssessmentMethodService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.AssessmentMethodDTO;
import com.marineindustryproj.service.dto.AssessmentMethodCriteria;
import com.marineindustryproj.service.AssessmentMethodQueryService;
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
 * REST controller for managing AssessmentMethod.
 */
@RestController
@RequestMapping("/api")
public class AssessmentMethodResource {

    private final Logger log = LoggerFactory.getLogger(AssessmentMethodResource.class);

    private static final String ENTITY_NAME = "assessmentMethod";

    private final AssessmentMethodService assessmentMethodService;

    private final AssessmentMethodQueryService assessmentMethodQueryService;

    public AssessmentMethodResource(AssessmentMethodService assessmentMethodService, AssessmentMethodQueryService assessmentMethodQueryService) {
        this.assessmentMethodService = assessmentMethodService;
        this.assessmentMethodQueryService = assessmentMethodQueryService;
    }

    /**
     * POST  /assessment-methods : Create a new assessmentMethod.
     *
     * @param assessmentMethodDTO the assessmentMethodDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assessmentMethodDTO, or with status 400 (Bad Request) if the assessmentMethod has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/assessment-methods")
    @Timed
    public ResponseEntity<AssessmentMethodDTO> createAssessmentMethod(@Valid @RequestBody AssessmentMethodDTO assessmentMethodDTO) throws URISyntaxException {
        log.debug("REST request to save AssessmentMethod : {}", assessmentMethodDTO);
        if (assessmentMethodDTO.getId() != null) {
            throw new BadRequestAlertException("A new assessmentMethod cannot already have an ID", ENTITY_NAME, "idexists");
        }

        assessmentMethodDTO.setCreateDate(ZonedDateTime.now());
        assessmentMethodDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        AssessmentMethodDTO result = assessmentMethodService.save(assessmentMethodDTO);
        return ResponseEntity.created(new URI("/api/assessment-methods/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /assessment-methods : Updates an existing assessmentMethod.
     *
     * @param assessmentMethodDTO the assessmentMethodDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assessmentMethodDTO,
     * or with status 400 (Bad Request) if the assessmentMethodDTO is not valid,
     * or with status 500 (Internal Server Error) if the assessmentMethodDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/assessment-methods")
    @Timed
    public ResponseEntity<AssessmentMethodDTO> updateAssessmentMethod(@Valid @RequestBody AssessmentMethodDTO assessmentMethodDTO) throws URISyntaxException {
        log.debug("REST request to update AssessmentMethod : {}", assessmentMethodDTO);
        if (assessmentMethodDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        AssessmentMethodDTO assessmentMethod = assessmentMethodService.findOne(assessmentMethodDTO.getId()).get();

        assessmentMethodDTO.setCreateUserLogin(assessmentMethod.getCreateUserLogin());
        assessmentMethodDTO.setCreateDate(assessmentMethod.getCreateDate());
        assessmentMethodDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        assessmentMethodDTO.setModifyDate(ZonedDateTime.now());

        AssessmentMethodDTO result = assessmentMethodService.save(assessmentMethodDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assessmentMethodDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /assessment-methods : get all the assessmentMethods.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of assessmentMethods in body
     */
    @GetMapping("/assessment-methods")
    @Timed
    public ResponseEntity<List<AssessmentMethodDTO>> getAllAssessmentMethods(AssessmentMethodCriteria criteria, Pageable pageable) {
        log.debug("REST request to get AssessmentMethods by criteria: {}", criteria);
        Page<AssessmentMethodDTO> page = assessmentMethodQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/assessment-methods");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /assessment-methods/count : count all the assessmentMethods.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/assessment-methods/count")
    @Timed
    public ResponseEntity<Long> countAssessmentMethods(AssessmentMethodCriteria criteria) {
        log.debug("REST request to count AssessmentMethods by criteria: {}", criteria);
        return ResponseEntity.ok().body(assessmentMethodQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /assessment-methods/:id : get the "id" assessmentMethod.
     *
     * @param id the id of the assessmentMethodDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assessmentMethodDTO, or with status 404 (Not Found)
     */
    @GetMapping("/assessment-methods/{id}")
    @Timed
    public ResponseEntity<AssessmentMethodDTO> getAssessmentMethod(@PathVariable Long id) {
        log.debug("REST request to get AssessmentMethod : {}", id);
        Optional<AssessmentMethodDTO> assessmentMethodDTO = assessmentMethodService.findOne(id);
        return ResponseUtil.wrapOrNotFound(assessmentMethodDTO);
    }

    /**
     * DELETE  /assessment-methods/:id : delete the "id" assessmentMethod.
     *
     * @param id the id of the assessmentMethodDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/assessment-methods/{id}")
    @Timed
    public ResponseEntity<Void> deleteAssessmentMethod(@PathVariable Long id) {
        log.debug("REST request to delete AssessmentMethod : {}", id);
        assessmentMethodService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
