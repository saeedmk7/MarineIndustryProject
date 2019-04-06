package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.DesignAndPlanningService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.DesignAndPlanningDTO;
import com.marineindustryproj.service.dto.DesignAndPlanningCriteria;
import com.marineindustryproj.service.DesignAndPlanningQueryService;
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
 * REST controller for managing DesignAndPlanning.
 */
@RestController
@RequestMapping("/api")
public class DesignAndPlanningResource {

    private final Logger log = LoggerFactory.getLogger(DesignAndPlanningResource.class);

    private static final String ENTITY_NAME = "designAndPlanning";

    private final DesignAndPlanningService designAndPlanningService;

    private final DesignAndPlanningQueryService designAndPlanningQueryService;

    public DesignAndPlanningResource(DesignAndPlanningService designAndPlanningService, DesignAndPlanningQueryService designAndPlanningQueryService) {
        this.designAndPlanningService = designAndPlanningService;
        this.designAndPlanningQueryService = designAndPlanningQueryService;
    }

    /**
     * POST  /design-and-plannings : Create a new designAndPlanning.
     *
     * @param designAndPlanningDTO the designAndPlanningDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new designAndPlanningDTO, or with status 400 (Bad Request) if the designAndPlanning has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/design-and-plannings")
    @Timed
    public ResponseEntity<DesignAndPlanningDTO> createDesignAndPlanning(@Valid @RequestBody DesignAndPlanningDTO designAndPlanningDTO) throws URISyntaxException {
        log.debug("REST request to save DesignAndPlanning : {}", designAndPlanningDTO);
        if (designAndPlanningDTO.getId() != null) {
            throw new BadRequestAlertException("A new designAndPlanning cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DesignAndPlanningDTO result = designAndPlanningService.save(designAndPlanningDTO);
        return ResponseEntity.created(new URI("/api/design-and-plannings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /design-and-plannings : Updates an existing designAndPlanning.
     *
     * @param designAndPlanningDTO the designAndPlanningDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated designAndPlanningDTO,
     * or with status 400 (Bad Request) if the designAndPlanningDTO is not valid,
     * or with status 500 (Internal Server Error) if the designAndPlanningDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/design-and-plannings")
    @Timed
    public ResponseEntity<DesignAndPlanningDTO> updateDesignAndPlanning(@Valid @RequestBody DesignAndPlanningDTO designAndPlanningDTO) throws URISyntaxException {
        log.debug("REST request to update DesignAndPlanning : {}", designAndPlanningDTO);
        if (designAndPlanningDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DesignAndPlanningDTO result = designAndPlanningService.save(designAndPlanningDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, designAndPlanningDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /design-and-plannings : get all the designAndPlannings.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of designAndPlannings in body
     */
    @GetMapping("/design-and-plannings")
    @Timed
    public ResponseEntity<List<DesignAndPlanningDTO>> getAllDesignAndPlannings(DesignAndPlanningCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DesignAndPlannings by criteria: {}", criteria);
        Page<DesignAndPlanningDTO> page = designAndPlanningQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/design-and-plannings");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /design-and-plannings/count : count all the designAndPlannings.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/design-and-plannings/count")
    @Timed
    public ResponseEntity<Long> countDesignAndPlannings (DesignAndPlanningCriteria criteria) {
        log.debug("REST request to count DesignAndPlannings by criteria: {}", criteria);
        return ResponseEntity.ok().body(designAndPlanningQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /design-and-plannings/:id : get the "id" designAndPlanning.
     *
     * @param id the id of the designAndPlanningDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the designAndPlanningDTO, or with status 404 (Not Found)
     */
    @GetMapping("/design-and-plannings/{id}")
    @Timed
    public ResponseEntity<DesignAndPlanningDTO> getDesignAndPlanning(@PathVariable Long id) {
        log.debug("REST request to get DesignAndPlanning : {}", id);
        Optional<DesignAndPlanningDTO> designAndPlanningDTO = designAndPlanningService.findOne(id);
        return ResponseUtil.wrapOrNotFound(designAndPlanningDTO);
    }

    /**
     * DELETE  /design-and-plannings/:id : delete the "id" designAndPlanning.
     *
     * @param id the id of the designAndPlanningDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/design-and-plannings/{id}")
    @Timed
    public ResponseEntity<Void> deleteDesignAndPlanning(@PathVariable Long id) {
        log.debug("REST request to delete DesignAndPlanning : {}", id);
        designAndPlanningService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
