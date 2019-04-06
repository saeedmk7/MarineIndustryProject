package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.ToolsAndFacilityService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.ToolsAndFacilityDTO;
import com.marineindustryproj.service.dto.ToolsAndFacilityCriteria;
import com.marineindustryproj.service.ToolsAndFacilityQueryService;
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
 * REST controller for managing ToolsAndFacility.
 */
@RestController
@RequestMapping("/api")
public class ToolsAndFacilityResource {

    private final Logger log = LoggerFactory.getLogger(ToolsAndFacilityResource.class);

    private static final String ENTITY_NAME = "toolsAndFacility";

    private final ToolsAndFacilityService toolsAndFacilityService;

    private final ToolsAndFacilityQueryService toolsAndFacilityQueryService;

    public ToolsAndFacilityResource(ToolsAndFacilityService toolsAndFacilityService, ToolsAndFacilityQueryService toolsAndFacilityQueryService) {
        this.toolsAndFacilityService = toolsAndFacilityService;
        this.toolsAndFacilityQueryService = toolsAndFacilityQueryService;
    }

    /**
     * POST  /tools-and-facilities : Create a new toolsAndFacility.
     *
     * @param toolsAndFacilityDTO the toolsAndFacilityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new toolsAndFacilityDTO, or with status 400 (Bad Request) if the toolsAndFacility has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tools-and-facilities")
    @Timed
    public ResponseEntity<ToolsAndFacilityDTO> createToolsAndFacility(@Valid @RequestBody ToolsAndFacilityDTO toolsAndFacilityDTO) throws URISyntaxException {
        log.debug("REST request to save ToolsAndFacility : {}", toolsAndFacilityDTO);
        if (toolsAndFacilityDTO.getId() != null) {
            throw new BadRequestAlertException("A new toolsAndFacility cannot already have an ID", ENTITY_NAME, "idexists");
        }
        toolsAndFacilityDTO.setCreateDate(ZonedDateTime.now());
        toolsAndFacilityDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        ToolsAndFacilityDTO result = toolsAndFacilityService.save(toolsAndFacilityDTO);
        return ResponseEntity.created(new URI("/api/tools-and-facilities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tools-and-facilities : Updates an existing toolsAndFacility.
     *
     * @param toolsAndFacilityDTO the toolsAndFacilityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated toolsAndFacilityDTO,
     * or with status 400 (Bad Request) if the toolsAndFacilityDTO is not valid,
     * or with status 500 (Internal Server Error) if the toolsAndFacilityDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tools-and-facilities")
    @Timed
    public ResponseEntity<ToolsAndFacilityDTO> updateToolsAndFacility(@Valid @RequestBody ToolsAndFacilityDTO toolsAndFacilityDTO) throws URISyntaxException {
        log.debug("REST request to update ToolsAndFacility : {}", toolsAndFacilityDTO);
        if (toolsAndFacilityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ToolsAndFacilityDTO toolsAndFacility = toolsAndFacilityService.findOne(toolsAndFacilityDTO.getId()).get();

        toolsAndFacilityDTO.setCreateUserLogin(toolsAndFacility.getCreateUserLogin());
        toolsAndFacilityDTO.setCreateDate(toolsAndFacility.getCreateDate());
        toolsAndFacilityDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        toolsAndFacilityDTO.setModifyDate(ZonedDateTime.now());
        ToolsAndFacilityDTO result = toolsAndFacilityService.save(toolsAndFacilityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, toolsAndFacilityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tools-and-facilities : get all the toolsAndFacilities.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of toolsAndFacilities in body
     */
    @GetMapping("/tools-and-facilities")
    @Timed
    public ResponseEntity<List<ToolsAndFacilityDTO>> getAllToolsAndFacilities(ToolsAndFacilityCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ToolsAndFacilities by criteria: {}", criteria);
        Page<ToolsAndFacilityDTO> page = toolsAndFacilityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tools-and-facilities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /tools-and-facilities/count : count all the toolsAndFacilities.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/tools-and-facilities/count")
    @Timed
    public ResponseEntity<Long> countToolsAndFacilities (ToolsAndFacilityCriteria criteria) {
        log.debug("REST request to count ToolsAndFacilities by criteria: {}", criteria);
        return ResponseEntity.ok().body(toolsAndFacilityQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /tools-and-facilities/:id : get the "id" toolsAndFacility.
     *
     * @param id the id of the toolsAndFacilityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the toolsAndFacilityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tools-and-facilities/{id}")
    @Timed
    public ResponseEntity<ToolsAndFacilityDTO> getToolsAndFacility(@PathVariable Long id) {
        log.debug("REST request to get ToolsAndFacility : {}", id);
        Optional<ToolsAndFacilityDTO> toolsAndFacilityDTO = toolsAndFacilityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(toolsAndFacilityDTO);
    }

    /**
     * DELETE  /tools-and-facilities/:id : delete the "id" toolsAndFacility.
     *
     * @param id the id of the toolsAndFacilityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tools-and-facilities/{id}")
    @Timed
    public ResponseEntity<Void> deleteToolsAndFacility(@PathVariable Long id) {
        log.debug("REST request to delete ToolsAndFacility : {}", id);
        toolsAndFacilityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
