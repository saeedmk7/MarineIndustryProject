package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.WorkIndustryService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.WorkIndustryDTO;
import com.marineindustryproj.service.dto.WorkIndustryCriteria;
import com.marineindustryproj.service.WorkIndustryQueryService;
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
 * REST controller for managing WorkIndustry.
 */
@RestController
@RequestMapping("/api")
public class WorkIndustryResource {

    private final Logger log = LoggerFactory.getLogger(WorkIndustryResource.class);

    private static final String ENTITY_NAME = "workIndustry";

    private final WorkIndustryService workIndustryService;

    private final WorkIndustryQueryService workIndustryQueryService;

    public WorkIndustryResource(WorkIndustryService workIndustryService, WorkIndustryQueryService workIndustryQueryService) {
        this.workIndustryService = workIndustryService;
        this.workIndustryQueryService = workIndustryQueryService;
    }

    /**
     * POST  /work-industries : Create a new workIndustry.
     *
     * @param workIndustryDTO the workIndustryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workIndustryDTO, or with status 400 (Bad Request) if the workIndustry has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-industries")
    @Timed
    public ResponseEntity<WorkIndustryDTO> createWorkIndustry(@Valid @RequestBody WorkIndustryDTO workIndustryDTO) throws URISyntaxException {
        log.debug("REST request to save WorkIndustry : {}", workIndustryDTO);
        if (workIndustryDTO.getId() != null) {
            throw new BadRequestAlertException("A new workIndustry cannot already have an ID", ENTITY_NAME, "idexists");
        }

        workIndustryDTO.setCreateDate(ZonedDateTime.now());
        workIndustryDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        WorkIndustryDTO result = workIndustryService.save(workIndustryDTO);
        return ResponseEntity.created(new URI("/api/work-industries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /work-industries : Updates an existing workIndustry.
     *
     * @param workIndustryDTO the workIndustryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated workIndustryDTO,
     * or with status 400 (Bad Request) if the workIndustryDTO is not valid,
     * or with status 500 (Internal Server Error) if the workIndustryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/work-industries")
    @Timed
    public ResponseEntity<WorkIndustryDTO> updateWorkIndustry(@Valid @RequestBody WorkIndustryDTO workIndustryDTO) throws URISyntaxException {
        log.debug("REST request to update WorkIndustry : {}", workIndustryDTO);
        if (workIndustryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        WorkIndustryDTO person = workIndustryService.findOne(workIndustryDTO.getId()).get();

        workIndustryDTO.setCreateUserLogin(person.getCreateUserLogin());
        workIndustryDTO.setCreateDate(person.getCreateDate());
        workIndustryDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        workIndustryDTO.setModifyDate(ZonedDateTime.now());

        WorkIndustryDTO result = workIndustryService.save(workIndustryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, workIndustryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /work-industries : get all the workIndustries.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of workIndustries in body
     */
    @GetMapping("/work-industries")
    @Timed
    public ResponseEntity<List<WorkIndustryDTO>> getAllWorkIndustries(WorkIndustryCriteria criteria, Pageable pageable) {
        log.debug("REST request to get WorkIndustries by criteria: {}", criteria);
        Page<WorkIndustryDTO> page = workIndustryQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/work-industries");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /work-industries/count : count all the workIndustries.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/work-industries/count")
    @Timed
    public ResponseEntity<Long> countWorkIndustries (WorkIndustryCriteria criteria) {
        log.debug("REST request to count WorkIndustries by criteria: {}", criteria);
        return ResponseEntity.ok().body(workIndustryQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /work-industries/:id : get the "id" workIndustry.
     *
     * @param id the id of the workIndustryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workIndustryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/work-industries/{id}")
    @Timed
    public ResponseEntity<WorkIndustryDTO> getWorkIndustry(@PathVariable Long id) {
        log.debug("REST request to get WorkIndustry : {}", id);
        Optional<WorkIndustryDTO> workIndustryDTO = workIndustryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(workIndustryDTO);
    }

    /**
     * DELETE  /work-industries/:id : delete the "id" workIndustry.
     *
     * @param id the id of the workIndustryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/work-industries/{id}")
    @Timed
    public ResponseEntity<Void> deleteWorkIndustry(@PathVariable Long id) {
        log.debug("REST request to delete WorkIndustry : {}", id);
        workIndustryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
