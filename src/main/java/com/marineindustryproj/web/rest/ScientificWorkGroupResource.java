package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.ScientificWorkGroupService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.ScientificWorkGroupDTO;
import com.marineindustryproj.service.dto.ScientificWorkGroupCriteria;
import com.marineindustryproj.service.ScientificWorkGroupQueryService;
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

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ScientificWorkGroup.
 */
@RestController
@RequestMapping("/api")
public class ScientificWorkGroupResource {

    private final Logger log = LoggerFactory.getLogger(ScientificWorkGroupResource.class);

    private static final String ENTITY_NAME = "scientificWorkGroup";

    private final ScientificWorkGroupService scientificWorkGroupService;

    private final ScientificWorkGroupQueryService scientificWorkGroupQueryService;

    public ScientificWorkGroupResource(ScientificWorkGroupService scientificWorkGroupService, ScientificWorkGroupQueryService scientificWorkGroupQueryService) {
        this.scientificWorkGroupService = scientificWorkGroupService;
        this.scientificWorkGroupQueryService = scientificWorkGroupQueryService;
    }

    /**
     * POST  /scientific-work-groups : Create a new scientificWorkGroup.
     *
     * @param scientificWorkGroupDTO the scientificWorkGroupDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new scientificWorkGroupDTO, or with status 400 (Bad Request) if the scientificWorkGroup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/scientific-work-groups")
    @Timed
    public ResponseEntity<ScientificWorkGroupDTO> createScientificWorkGroup(@Valid @RequestBody ScientificWorkGroupDTO scientificWorkGroupDTO) throws URISyntaxException {
        log.debug("REST request to save ScientificWorkGroup : {}", scientificWorkGroupDTO);
        if (scientificWorkGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new scientificWorkGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ScientificWorkGroupDTO result = scientificWorkGroupService.save(scientificWorkGroupDTO);
        return ResponseEntity.created(new URI("/api/scientific-work-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /scientific-work-groups : Updates an existing scientificWorkGroup.
     *
     * @param scientificWorkGroupDTO the scientificWorkGroupDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated scientificWorkGroupDTO,
     * or with status 400 (Bad Request) if the scientificWorkGroupDTO is not valid,
     * or with status 500 (Internal Server Error) if the scientificWorkGroupDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/scientific-work-groups")
    @Timed
    public ResponseEntity<ScientificWorkGroupDTO> updateScientificWorkGroup(@Valid @RequestBody ScientificWorkGroupDTO scientificWorkGroupDTO) throws URISyntaxException {
        log.debug("REST request to update ScientificWorkGroup : {}", scientificWorkGroupDTO);
        if (scientificWorkGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ScientificWorkGroupDTO result = scientificWorkGroupService.save(scientificWorkGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, scientificWorkGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /scientific-work-groups : get all the scientificWorkGroups.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of scientificWorkGroups in body
     */
    @GetMapping("/scientific-work-groups")
    @Timed
    public ResponseEntity<List<ScientificWorkGroupDTO>> getAllScientificWorkGroups(ScientificWorkGroupCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ScientificWorkGroups by criteria: {}", criteria);
        Page<ScientificWorkGroupDTO> page = scientificWorkGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/scientific-work-groups");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /scientific-work-groups/count : count all the scientificWorkGroups.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/scientific-work-groups/count")
    @Timed
    public ResponseEntity<Long> countScientificWorkGroups (ScientificWorkGroupCriteria criteria) {
        log.debug("REST request to count ScientificWorkGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(scientificWorkGroupQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /scientific-work-groups/:id : get the "id" scientificWorkGroup.
     *
     * @param id the id of the scientificWorkGroupDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the scientificWorkGroupDTO, or with status 404 (Not Found)
     */
    @GetMapping("/scientific-work-groups/{id}")
    @Timed
    public ResponseEntity<ScientificWorkGroupDTO> getScientificWorkGroup(@PathVariable Long id) {
        log.debug("REST request to get ScientificWorkGroup : {}", id);
        Optional<ScientificWorkGroupDTO> scientificWorkGroupDTO = scientificWorkGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(scientificWorkGroupDTO);
    }

    /**
     * DELETE  /scientific-work-groups/:id : delete the "id" scientificWorkGroup.
     *
     * @param id the id of the scientificWorkGroupDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/scientific-work-groups/{id}")
    @Timed
    public ResponseEntity<Void> deleteScientificWorkGroup(@PathVariable Long id) {
        log.debug("REST request to delete ScientificWorkGroup : {}", id);
        scientificWorkGroupService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
