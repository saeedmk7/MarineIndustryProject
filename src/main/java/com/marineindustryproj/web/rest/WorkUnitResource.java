package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.WorkUnitService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.WorkUnitDTO;
import com.marineindustryproj.service.dto.WorkUnitCriteria;
import com.marineindustryproj.service.WorkUnitQueryService;
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
 * REST controller for managing WorkUnit.
 */
@RestController
@RequestMapping("/api")
public class WorkUnitResource {

    private final Logger log = LoggerFactory.getLogger(WorkUnitResource.class);

    private static final String ENTITY_NAME = "workUnit";

    private final WorkUnitService workUnitService;

    private final WorkUnitQueryService workUnitQueryService;

    public WorkUnitResource(WorkUnitService workUnitService, WorkUnitQueryService workUnitQueryService) {
        this.workUnitService = workUnitService;
        this.workUnitQueryService = workUnitQueryService;
    }

    /**
     * POST  /work-units : Create a new workUnit.
     *
     * @param workUnitDTO the workUnitDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workUnitDTO, or with status 400 (Bad Request) if the workUnit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-units")
    @Timed
    public ResponseEntity<WorkUnitDTO> createWorkUnit(@Valid @RequestBody WorkUnitDTO workUnitDTO) throws URISyntaxException {
        log.debug("REST request to save WorkUnit : {}", workUnitDTO);
        if (workUnitDTO.getId() != null) {
            throw new BadRequestAlertException("A new workUnit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        workUnitDTO.setCreateDate(ZonedDateTime.now());
        workUnitDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        WorkUnitDTO result = workUnitService.save(workUnitDTO);
        return ResponseEntity.created(new URI("/api/work-units/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /work-units : Updates an existing workUnit.
     *
     * @param workUnitDTO the workUnitDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated workUnitDTO,
     * or with status 400 (Bad Request) if the workUnitDTO is not valid,
     * or with status 500 (Internal Server Error) if the workUnitDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/work-units")
    @Timed
    public ResponseEntity<WorkUnitDTO> updateWorkUnit(@Valid @RequestBody WorkUnitDTO workUnitDTO) throws URISyntaxException {
        log.debug("REST request to update WorkUnit : {}", workUnitDTO);
        if (workUnitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WorkUnitDTO result = workUnitService.save(workUnitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, workUnitDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /work-units : get all the workUnits.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of workUnits in body
     */
    @GetMapping("/work-units")
    @Timed
    public ResponseEntity<List<WorkUnitDTO>> getAllWorkUnits(WorkUnitCriteria criteria, Pageable pageable) {
        log.debug("REST request to get WorkUnits by criteria: {}", criteria);
        Page<WorkUnitDTO> page = workUnitQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/work-units");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /work-units/count : count all the workUnits.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/work-units/count")
    @Timed
    public ResponseEntity<Long> countWorkUnits (WorkUnitCriteria criteria) {
        log.debug("REST request to count WorkUnits by criteria: {}", criteria);
        return ResponseEntity.ok().body(workUnitQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /work-units/:id : get the "id" workUnit.
     *
     * @param id the id of the workUnitDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workUnitDTO, or with status 404 (Not Found)
     */
    @GetMapping("/work-units/{id}")
    @Timed
    public ResponseEntity<WorkUnitDTO> getWorkUnit(@PathVariable Long id) {
        log.debug("REST request to get WorkUnit : {}", id);
        Optional<WorkUnitDTO> workUnitDTO = workUnitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(workUnitDTO);
    }

    /**
     * DELETE  /work-units/:id : delete the "id" workUnit.
     *
     * @param id the id of the workUnitDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/work-units/{id}")
    @Timed
    public ResponseEntity<Void> deleteWorkUnit(@PathVariable Long id) {
        log.debug("REST request to delete WorkUnit : {}", id);
        workUnitService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
