package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.PriorityCriteriaValueService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.PriorityCriteriaValueDTO;
import com.marineindustryproj.service.dto.PriorityCriteriaValueCriteria;
import com.marineindustryproj.service.PriorityCriteriaValueQueryService;
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
 * REST controller for managing PriorityCriteriaValue.
 */
@RestController
@RequestMapping("/api")
public class PriorityCriteriaValueResource {

    private final Logger log = LoggerFactory.getLogger(PriorityCriteriaValueResource.class);

    private static final String ENTITY_NAME = "priorityCriteriaValue";

    private final PriorityCriteriaValueService priorityCriteriaValueService;

    private final PriorityCriteriaValueQueryService priorityCriteriaValueQueryService;

    public PriorityCriteriaValueResource(PriorityCriteriaValueService priorityCriteriaValueService, PriorityCriteriaValueQueryService priorityCriteriaValueQueryService) {
        this.priorityCriteriaValueService = priorityCriteriaValueService;
        this.priorityCriteriaValueQueryService = priorityCriteriaValueQueryService;
    }

    /**
     * POST  /priority-criteria-values : Create a new priorityCriteriaValue.
     *
     * @param priorityCriteriaValueDTO the priorityCriteriaValueDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new priorityCriteriaValueDTO, or with status 400 (Bad Request) if the priorityCriteriaValue has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/priority-criteria-values")
    @Timed
    public ResponseEntity<PriorityCriteriaValueDTO> createPriorityCriteriaValue(@Valid @RequestBody PriorityCriteriaValueDTO priorityCriteriaValueDTO) throws URISyntaxException {
        log.debug("REST request to save PriorityCriteriaValue : {}", priorityCriteriaValueDTO);
        if (priorityCriteriaValueDTO.getId() != null) {
            throw new BadRequestAlertException("A new priorityCriteriaValue cannot already have an ID", ENTITY_NAME, "idexists");
        }
        priorityCriteriaValueDTO.setCreateDate(ZonedDateTime.now());
        priorityCriteriaValueDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        PriorityCriteriaValueDTO result = priorityCriteriaValueService.save(priorityCriteriaValueDTO);
        return ResponseEntity.created(new URI("/api/priority-criteria-values/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /priority-criteria-values : Updates an existing priorityCriteriaValue.
     *
     * @param priorityCriteriaValueDTO the priorityCriteriaValueDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated priorityCriteriaValueDTO,
     * or with status 400 (Bad Request) if the priorityCriteriaValueDTO is not valid,
     * or with status 500 (Internal Server Error) if the priorityCriteriaValueDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/priority-criteria-values")
    @Timed
    public ResponseEntity<PriorityCriteriaValueDTO> updatePriorityCriteriaValue(@Valid @RequestBody PriorityCriteriaValueDTO priorityCriteriaValueDTO) throws URISyntaxException {
        log.debug("REST request to update PriorityCriteriaValue : {}", priorityCriteriaValueDTO);
        if (priorityCriteriaValueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PriorityCriteriaValueDTO priorityCriteriaValue = priorityCriteriaValueService.findOne(priorityCriteriaValueDTO.getId()).get();

        priorityCriteriaValueDTO.setCreateUserLogin(priorityCriteriaValue.getCreateUserLogin());
        priorityCriteriaValueDTO.setCreateDate(priorityCriteriaValue.getCreateDate());
        priorityCriteriaValueDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        priorityCriteriaValueDTO.setModifyDate(ZonedDateTime.now());
        PriorityCriteriaValueDTO result = priorityCriteriaValueService.save(priorityCriteriaValueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, priorityCriteriaValueDTO.getId().toString()))
            .body(result);
    }

    @PutMapping("/priority-criteria-values/bulk")
    @Timed
    public ResponseEntity<PriorityCriteriaValueDTO> updatePriorityCriteriaValueBulk(@Valid @RequestBody PriorityCriteriaValueDTO[] priorityCriteriaValueDTOS) throws URISyntaxException {
        PriorityCriteriaValueDTO result = priorityCriteriaValueService.saveBulk(priorityCriteriaValueDTOS);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * GET  /priority-criteria-values : get all the priorityCriteriaValues.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of priorityCriteriaValues in body
     */
    @GetMapping("/priority-criteria-values")
    @Timed
    public ResponseEntity<List<PriorityCriteriaValueDTO>> getAllPriorityCriteriaValues(PriorityCriteriaValueCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PriorityCriteriaValues by criteria: {}", criteria);
        Page<PriorityCriteriaValueDTO> page = priorityCriteriaValueQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/priority-criteria-values");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /priority-criteria-values/count : count all the priorityCriteriaValues.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/priority-criteria-values/count")
    @Timed
    public ResponseEntity<Long> countPriorityCriteriaValues(PriorityCriteriaValueCriteria criteria) {
        log.debug("REST request to count PriorityCriteriaValues by criteria: {}", criteria);
        return ResponseEntity.ok().body(priorityCriteriaValueQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /priority-criteria-values/:id : get the "id" priorityCriteriaValue.
     *
     * @param id the id of the priorityCriteriaValueDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the priorityCriteriaValueDTO, or with status 404 (Not Found)
     */
    @GetMapping("/priority-criteria-values/{id}")
    @Timed
    public ResponseEntity<PriorityCriteriaValueDTO> getPriorityCriteriaValue(@PathVariable Long id) {
        log.debug("REST request to get PriorityCriteriaValue : {}", id);
        Optional<PriorityCriteriaValueDTO> priorityCriteriaValueDTO = priorityCriteriaValueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(priorityCriteriaValueDTO);
    }

    /**
     * DELETE  /priority-criteria-values/:id : delete the "id" priorityCriteriaValue.
     *
     * @param id the id of the priorityCriteriaValueDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/priority-criteria-values/{id}")
    @Timed
    public ResponseEntity<Void> deletePriorityCriteriaValue(@PathVariable Long id) {
        log.debug("REST request to delete PriorityCriteriaValue : {}", id);
        priorityCriteriaValueService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
