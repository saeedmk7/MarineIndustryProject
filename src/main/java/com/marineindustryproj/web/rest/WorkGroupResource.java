package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.WorkGroupService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.WorkGroupDTO;
import com.marineindustryproj.service.dto.WorkGroupCriteria;
import com.marineindustryproj.service.WorkGroupQueryService;
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
 * REST controller for managing WorkGroup.
 */
@RestController
@RequestMapping("/api")
public class WorkGroupResource {

    private final Logger log = LoggerFactory.getLogger(WorkGroupResource.class);

    private static final String ENTITY_NAME = "workGroup";

    private final WorkGroupService workGroupService;

    private final WorkGroupQueryService workGroupQueryService;

    public WorkGroupResource(WorkGroupService workGroupService, WorkGroupQueryService workGroupQueryService) {
        this.workGroupService = workGroupService;
        this.workGroupQueryService = workGroupQueryService;
    }

    /**
     * POST  /work-groups : Create a new workGroup.
     *
     * @param workGroupDTO the workGroupDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workGroupDTO, or with status 400 (Bad Request) if the workGroup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-groups")
    @Timed
    public ResponseEntity<WorkGroupDTO> createWorkGroup(@Valid @RequestBody WorkGroupDTO workGroupDTO) throws URISyntaxException {
        log.debug("REST request to save WorkGroup : {}", workGroupDTO);
        if (workGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new workGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }

        workGroupDTO.setCreateDate(ZonedDateTime.now());
        workGroupDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        WorkGroupDTO result = workGroupService.save(workGroupDTO);
        return ResponseEntity.created(new URI("/api/work-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /work-groups : Updates an existing workGroup.
     *
     * @param workGroupDTO the workGroupDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated workGroupDTO,
     * or with status 400 (Bad Request) if the workGroupDTO is not valid,
     * or with status 500 (Internal Server Error) if the workGroupDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/work-groups")
    @Timed
    public ResponseEntity<WorkGroupDTO> updateWorkGroup(@Valid @RequestBody WorkGroupDTO workGroupDTO) throws URISyntaxException {
        log.debug("REST request to update WorkGroup : {}", workGroupDTO);
        if (workGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        WorkGroupDTO workGroup = workGroupService.findOne(workGroupDTO.getId()).get();

        workGroupDTO.setCreateUserLogin(workGroup.getCreateUserLogin());
        workGroupDTO.setCreateDate(workGroup.getCreateDate());
        workGroupDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        workGroupDTO.setModifyDate(ZonedDateTime.now());

        WorkGroupDTO result = workGroupService.save(workGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, workGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /work-groups : get all the workGroups.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of workGroups in body
     */
    @GetMapping("/work-groups")
    @Timed
    public ResponseEntity<List<WorkGroupDTO>> getAllWorkGroups(WorkGroupCriteria criteria, Pageable pageable) {
        log.debug("REST request to get WorkGroups by criteria: {}", criteria);
        Page<WorkGroupDTO> page = workGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/work-groups");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /work-groups/count : count all the workGroups.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/work-groups/count")
    @Timed
    public ResponseEntity<Long> countWorkGroups (WorkGroupCriteria criteria) {
        log.debug("REST request to count WorkGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(workGroupQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /work-groups/:id : get the "id" workGroup.
     *
     * @param id the id of the workGroupDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workGroupDTO, or with status 404 (Not Found)
     */
    @GetMapping("/work-groups/{id}")
    @Timed
    public ResponseEntity<WorkGroupDTO> getWorkGroup(@PathVariable Long id) {
        log.debug("REST request to get WorkGroup : {}", id);
        Optional<WorkGroupDTO> workGroupDTO = workGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(workGroupDTO);
    }

    /**
     * DELETE  /work-groups/:id : delete the "id" workGroup.
     *
     * @param id the id of the workGroupDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/work-groups/{id}")
    @Timed
    public ResponseEntity<Void> deleteWorkGroup(@PathVariable Long id) {
        log.debug("REST request to delete WorkGroup : {}", id);
        workGroupService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
