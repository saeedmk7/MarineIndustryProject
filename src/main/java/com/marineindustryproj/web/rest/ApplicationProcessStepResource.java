package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.ApplicationProcessStepService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.ApplicationProcessStepDTO;
import com.marineindustryproj.service.dto.ApplicationProcessStepCriteria;
import com.marineindustryproj.service.ApplicationProcessStepQueryService;
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
 * REST controller for managing ApplicationProcessStep.
 */
@RestController
@RequestMapping("/api")
public class ApplicationProcessStepResource {

    private final Logger log = LoggerFactory.getLogger(ApplicationProcessStepResource.class);

    private static final String ENTITY_NAME = "applicationProcessStep";

    private final ApplicationProcessStepService applicationProcessStepService;

    private final ApplicationProcessStepQueryService applicationProcessStepQueryService;

    public ApplicationProcessStepResource(ApplicationProcessStepService applicationProcessStepService, ApplicationProcessStepQueryService applicationProcessStepQueryService) {
        this.applicationProcessStepService = applicationProcessStepService;
        this.applicationProcessStepQueryService = applicationProcessStepQueryService;
    }

    /**
     * POST  /application-process-steps : Create a new applicationProcessStep.
     *
     * @param applicationProcessStepDTO the applicationProcessStepDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new applicationProcessStepDTO, or with status 400 (Bad Request) if the applicationProcessStep has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/application-process-steps")
    @Timed
    public ResponseEntity<ApplicationProcessStepDTO> createApplicationProcessStep(@Valid @RequestBody ApplicationProcessStepDTO applicationProcessStepDTO) throws URISyntaxException {
        log.debug("REST request to save ApplicationProcessStep : {}", applicationProcessStepDTO);
        if (applicationProcessStepDTO.getId() != null) {
            throw new BadRequestAlertException("A new applicationProcessStep cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ApplicationProcessStepDTO result = applicationProcessStepService.save(applicationProcessStepDTO);
        return ResponseEntity.created(new URI("/api/application-process-steps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /application-process-steps : Updates an existing applicationProcessStep.
     *
     * @param applicationProcessStepDTO the applicationProcessStepDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated applicationProcessStepDTO,
     * or with status 400 (Bad Request) if the applicationProcessStepDTO is not valid,
     * or with status 500 (Internal Server Error) if the applicationProcessStepDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/application-process-steps")
    @Timed
    public ResponseEntity<ApplicationProcessStepDTO> updateApplicationProcessStep(@Valid @RequestBody ApplicationProcessStepDTO applicationProcessStepDTO) throws URISyntaxException {
        log.debug("REST request to update ApplicationProcessStep : {}", applicationProcessStepDTO);
        if (applicationProcessStepDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ApplicationProcessStepDTO result = applicationProcessStepService.save(applicationProcessStepDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, applicationProcessStepDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /application-process-steps : get all the applicationProcessSteps.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of applicationProcessSteps in body
     */
    @GetMapping("/application-process-steps")
    @Timed
    public ResponseEntity<List<ApplicationProcessStepDTO>> getAllApplicationProcessSteps(ApplicationProcessStepCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ApplicationProcessSteps by criteria: {}", criteria);
        Page<ApplicationProcessStepDTO> page = applicationProcessStepQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/application-process-steps");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /application-process-steps/count : count all the applicationProcessSteps.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/application-process-steps/count")
    @Timed
    public ResponseEntity<Long> countApplicationProcessSteps(ApplicationProcessStepCriteria criteria) {
        log.debug("REST request to count ApplicationProcessSteps by criteria: {}", criteria);
        return ResponseEntity.ok().body(applicationProcessStepQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /application-process-steps/:id : get the "id" applicationProcessStep.
     *
     * @param id the id of the applicationProcessStepDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the applicationProcessStepDTO, or with status 404 (Not Found)
     */
    @GetMapping("/application-process-steps/{id}")
    @Timed
    public ResponseEntity<ApplicationProcessStepDTO> getApplicationProcessStep(@PathVariable Long id) {
        log.debug("REST request to get ApplicationProcessStep : {}", id);
        Optional<ApplicationProcessStepDTO> applicationProcessStepDTO = applicationProcessStepService.findOne(id);
        return ResponseUtil.wrapOrNotFound(applicationProcessStepDTO);
    }

    /**
     * DELETE  /application-process-steps/:id : delete the "id" applicationProcessStep.
     *
     * @param id the id of the applicationProcessStepDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/application-process-steps/{id}")
    @Timed
    public ResponseEntity<Void> deleteApplicationProcessStep(@PathVariable Long id) {
        log.debug("REST request to delete ApplicationProcessStep : {}", id);
        applicationProcessStepService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
