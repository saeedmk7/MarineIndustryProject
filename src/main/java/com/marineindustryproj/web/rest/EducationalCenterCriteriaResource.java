package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.EducationalCenterCriteriaService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.EducationalCenterCriteriaDTO;
import com.marineindustryproj.service.dto.EducationalCenterCriteriaCriteria;
import com.marineindustryproj.service.EducationalCenterCriteriaQueryService;
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
 * REST controller for managing EducationalCenterCriteria.
 */
@RestController
@RequestMapping("/api")
public class EducationalCenterCriteriaResource {

    private final Logger log = LoggerFactory.getLogger(EducationalCenterCriteriaResource.class);

    private static final String ENTITY_NAME = "educationalCenterCriteria";

    private final EducationalCenterCriteriaService educationalCenterCriteriaService;

    private final EducationalCenterCriteriaQueryService educationalCenterCriteriaQueryService;

    public EducationalCenterCriteriaResource(EducationalCenterCriteriaService educationalCenterCriteriaService, EducationalCenterCriteriaQueryService educationalCenterCriteriaQueryService) {
        this.educationalCenterCriteriaService = educationalCenterCriteriaService;
        this.educationalCenterCriteriaQueryService = educationalCenterCriteriaQueryService;
    }

    /**
     * POST  /educational-center-criteria : Create a new educationalCenterCriteria.
     *
     * @param educationalCenterCriteriaDTO the educationalCenterCriteriaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new educationalCenterCriteriaDTO, or with status 400 (Bad Request) if the educationalCenterCriteria has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/educational-center-criteria")
    @Timed
    public ResponseEntity<EducationalCenterCriteriaDTO> createEducationalCenterCriteria(@Valid @RequestBody EducationalCenterCriteriaDTO educationalCenterCriteriaDTO) throws URISyntaxException {
        log.debug("REST request to save EducationalCenterCriteria : {}", educationalCenterCriteriaDTO);
        if (educationalCenterCriteriaDTO.getId() != null) {
            throw new BadRequestAlertException("A new educationalCenterCriteria cannot already have an ID", ENTITY_NAME, "idexists");
        }
        educationalCenterCriteriaDTO.setCreateDate(ZonedDateTime.now());
        educationalCenterCriteriaDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        EducationalCenterCriteriaDTO result = educationalCenterCriteriaService.save(educationalCenterCriteriaDTO);
        return ResponseEntity.created(new URI("/api/educational-center-criteria/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /educational-center-criteria : Updates an existing educationalCenterCriteria.
     *
     * @param educationalCenterCriteriaDTO the educationalCenterCriteriaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated educationalCenterCriteriaDTO,
     * or with status 400 (Bad Request) if the educationalCenterCriteriaDTO is not valid,
     * or with status 500 (Internal Server Error) if the educationalCenterCriteriaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/educational-center-criteria")
    @Timed
    public ResponseEntity<EducationalCenterCriteriaDTO> updateEducationalCenterCriteria(@Valid @RequestBody EducationalCenterCriteriaDTO educationalCenterCriteriaDTO) throws URISyntaxException {
        log.debug("REST request to update EducationalCenterCriteria : {}", educationalCenterCriteriaDTO);
        if (educationalCenterCriteriaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        EducationalCenterCriteriaDTO educationalCenterCriteria = educationalCenterCriteriaService.findOne(educationalCenterCriteriaDTO.getId()).get();

        educationalCenterCriteriaDTO.setCreateUserLogin(educationalCenterCriteria.getCreateUserLogin());
        educationalCenterCriteriaDTO.setCreateDate(educationalCenterCriteria.getCreateDate());
        educationalCenterCriteriaDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        educationalCenterCriteriaDTO.setModifyDate(ZonedDateTime.now());

        EducationalCenterCriteriaDTO result = educationalCenterCriteriaService.save(educationalCenterCriteriaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, educationalCenterCriteriaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /educational-center-criteria : get all the educationalCenterCriteria.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of educationalCenterCriteria in body
     */
    @GetMapping("/educational-center-criteria")
    @Timed
    public ResponseEntity<List<EducationalCenterCriteriaDTO>> getAllEducationalCenterCriteria(EducationalCenterCriteriaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EducationalCenterCriteria by criteria: {}", criteria);
        Page<EducationalCenterCriteriaDTO> page = educationalCenterCriteriaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/educational-center-criteria");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /educational-center-criteria/count : count all the educationalCenterCriteria.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/educational-center-criteria/count")
    @Timed
    public ResponseEntity<Long> countEducationalCenterCriteria(EducationalCenterCriteriaCriteria criteria) {
        log.debug("REST request to count EducationalCenterCriteria by criteria: {}", criteria);
        return ResponseEntity.ok().body(educationalCenterCriteriaQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /educational-center-criteria/:id : get the "id" educationalCenterCriteria.
     *
     * @param id the id of the educationalCenterCriteriaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the educationalCenterCriteriaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/educational-center-criteria/{id}")
    @Timed
    public ResponseEntity<EducationalCenterCriteriaDTO> getEducationalCenterCriteria(@PathVariable Long id) {
        log.debug("REST request to get EducationalCenterCriteria : {}", id);
        Optional<EducationalCenterCriteriaDTO> educationalCenterCriteriaDTO = educationalCenterCriteriaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(educationalCenterCriteriaDTO);
    }

    /**
     * DELETE  /educational-center-criteria/:id : delete the "id" educationalCenterCriteria.
     *
     * @param id the id of the educationalCenterCriteriaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/educational-center-criteria/{id}")
    @Timed
    public ResponseEntity<Void> deleteEducationalCenterCriteria(@PathVariable Long id) {
        log.debug("REST request to delete EducationalCenterCriteria : {}", id);
        educationalCenterCriteriaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
