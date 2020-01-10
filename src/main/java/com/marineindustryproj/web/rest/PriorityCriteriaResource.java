package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.PriorityCriteriaService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.PriorityCriteriaDTO;
import com.marineindustryproj.service.dto.PriorityCriteriaCriteria;
import com.marineindustryproj.service.PriorityCriteriaQueryService;
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
 * REST controller for managing PriorityCriteria.
 */
@RestController
@RequestMapping("/api")
public class PriorityCriteriaResource {

    private final Logger log = LoggerFactory.getLogger(PriorityCriteriaResource.class);

    private static final String ENTITY_NAME = "priorityCriteria";

    private final PriorityCriteriaService priorityCriteriaService;

    private final PriorityCriteriaQueryService priorityCriteriaQueryService;

    public PriorityCriteriaResource(PriorityCriteriaService priorityCriteriaService, PriorityCriteriaQueryService priorityCriteriaQueryService) {
        this.priorityCriteriaService = priorityCriteriaService;
        this.priorityCriteriaQueryService = priorityCriteriaQueryService;
    }

    /**
     * POST  /priority-criteria : Create a new priorityCriteria.
     *
     * @param priorityCriteriaDTO the priorityCriteriaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new priorityCriteriaDTO, or with status 400 (Bad Request) if the priorityCriteria has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/priority-criteria")
    @Timed
    public ResponseEntity<PriorityCriteriaDTO> createPriorityCriteria(@Valid @RequestBody PriorityCriteriaDTO priorityCriteriaDTO) throws URISyntaxException {
        log.debug("REST request to save PriorityCriteria : {}", priorityCriteriaDTO);
        if (priorityCriteriaDTO.getId() != null) {
            throw new BadRequestAlertException("A new priorityCriteria cannot already have an ID", ENTITY_NAME, "idexists");
        }
        priorityCriteriaDTO.setCreateDate(ZonedDateTime.now());
        priorityCriteriaDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        PriorityCriteriaDTO result = priorityCriteriaService.save(priorityCriteriaDTO);
        return ResponseEntity.created(new URI("/api/priority-criteria/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /priority-criteria : Updates an existing priorityCriteria.
     *
     * @param priorityCriteriaDTO the priorityCriteriaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated priorityCriteriaDTO,
     * or with status 400 (Bad Request) if the priorityCriteriaDTO is not valid,
     * or with status 500 (Internal Server Error) if the priorityCriteriaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/priority-criteria")
    @Timed
    public ResponseEntity<PriorityCriteriaDTO> updatePriorityCriteria(@Valid @RequestBody PriorityCriteriaDTO priorityCriteriaDTO) throws URISyntaxException {
        log.debug("REST request to update PriorityCriteria : {}", priorityCriteriaDTO);
        if (priorityCriteriaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PriorityCriteriaDTO priorityCriteria = priorityCriteriaService.findOne(priorityCriteriaDTO.getId()).get();

        priorityCriteriaDTO.setCreateUserLogin(priorityCriteria.getCreateUserLogin());
        priorityCriteriaDTO.setCreateDate(priorityCriteria.getCreateDate());
        priorityCriteriaDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        priorityCriteriaDTO.setModifyDate(ZonedDateTime.now());
        PriorityCriteriaDTO result = priorityCriteriaService.save(priorityCriteriaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, priorityCriteriaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /priority-criteria : get all the priorityCriteria.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of priorityCriteria in body
     */
    @GetMapping("/priority-criteria")
    @Timed
    public ResponseEntity<List<PriorityCriteriaDTO>> getAllPriorityCriteria(PriorityCriteriaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PriorityCriteria by criteria: {}", criteria);
        Page<PriorityCriteriaDTO> page = priorityCriteriaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/priority-criteria");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /priority-criteria/count : count all the priorityCriteria.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/priority-criteria/count")
    @Timed
    public ResponseEntity<Long> countPriorityCriteria(PriorityCriteriaCriteria criteria) {
        log.debug("REST request to count PriorityCriteria by criteria: {}", criteria);
        return ResponseEntity.ok().body(priorityCriteriaQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /priority-criteria/:id : get the "id" priorityCriteria.
     *
     * @param id the id of the priorityCriteriaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the priorityCriteriaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/priority-criteria/{id}")
    @Timed
    public ResponseEntity<PriorityCriteriaDTO> getPriorityCriteria(@PathVariable Long id) {
        log.debug("REST request to get PriorityCriteria : {}", id);
        Optional<PriorityCriteriaDTO> priorityCriteriaDTO = priorityCriteriaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(priorityCriteriaDTO);
    }

    /**
     * DELETE  /priority-criteria/:id : delete the "id" priorityCriteria.
     *
     * @param id the id of the priorityCriteriaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/priority-criteria/{id}")
    @Timed
    public ResponseEntity<Void> deletePriorityCriteria(@PathVariable Long id) {
        log.debug("REST request to delete PriorityCriteria : {}", id);
        priorityCriteriaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
