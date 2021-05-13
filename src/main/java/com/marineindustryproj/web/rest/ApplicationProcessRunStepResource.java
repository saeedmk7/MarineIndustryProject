package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.ApplicationProcessRunStepService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.ApplicationProcessRunStepDTO;
import com.marineindustryproj.service.dto.ApplicationProcessRunStepCriteria;
import com.marineindustryproj.service.ApplicationProcessRunStepQueryService;
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
 * REST controller for managing ApplicationProcessRunStep.
 */
@RestController
@RequestMapping("/api")
public class ApplicationProcessRunStepResource {

    private final Logger log = LoggerFactory.getLogger(ApplicationProcessRunStepResource.class);

    private static final String ENTITY_NAME = "applicationProcessRunStep";

    private final ApplicationProcessRunStepService applicationProcessRunStepService;

    private final ApplicationProcessRunStepQueryService applicationProcessRunStepQueryService;

    public ApplicationProcessRunStepResource(ApplicationProcessRunStepService applicationProcessRunStepService, ApplicationProcessRunStepQueryService applicationProcessRunStepQueryService) {
        this.applicationProcessRunStepService = applicationProcessRunStepService;
        this.applicationProcessRunStepQueryService = applicationProcessRunStepQueryService;
    }

    /**
     * POST  /application-process-run-steps : Create a new applicationProcessRunStep.
     *
     * @param applicationProcessRunStepDTO the applicationProcessRunStepDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new applicationProcessRunStepDTO, or with status 400 (Bad Request) if the applicationProcessRunStep has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/application-process-run-steps")
    @Timed
    public ResponseEntity<ApplicationProcessRunStepDTO> createApplicationProcessRunStep(@Valid @RequestBody ApplicationProcessRunStepDTO applicationProcessRunStepDTO) throws URISyntaxException {
        log.debug("REST request to save ApplicationProcessRunStep : {}", applicationProcessRunStepDTO);
        if (applicationProcessRunStepDTO.getId() != null) {
            throw new BadRequestAlertException("A new applicationProcessRunStep cannot already have an ID", ENTITY_NAME, "idexists");
        }

        applicationProcessRunStepDTO.setCreateDate(ZonedDateTime.now());
        applicationProcessRunStepDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        ApplicationProcessRunStepDTO result = applicationProcessRunStepService.save(applicationProcessRunStepDTO);
        return ResponseEntity.created(new URI("/api/application-process-run-steps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /application-process-run-steps : Updates an existing applicationProcessRunStep.
     *
     * @param applicationProcessRunStepDTO the applicationProcessRunStepDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated applicationProcessRunStepDTO,
     * or with status 400 (Bad Request) if the applicationProcessRunStepDTO is not valid,
     * or with status 500 (Internal Server Error) if the applicationProcessRunStepDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/application-process-run-steps")
    @Timed
    public ResponseEntity<ApplicationProcessRunStepDTO> updateApplicationProcessRunStep(@Valid @RequestBody ApplicationProcessRunStepDTO applicationProcessRunStepDTO) throws URISyntaxException {
        log.debug("REST request to update ApplicationProcessRunStep : {}", applicationProcessRunStepDTO);
        if (applicationProcessRunStepDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        ApplicationProcessRunStepDTO applicationProcessRunStep = applicationProcessRunStepService.findOne(applicationProcessRunStepDTO.getId()).get();

        if(applicationProcessRunStep.getCreateUserLogin() == null)
            applicationProcessRunStepDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        else
            applicationProcessRunStepDTO.setCreateUserLogin(applicationProcessRunStep.getCreateUserLogin());
        applicationProcessRunStepDTO.setCreateDate(applicationProcessRunStep.getCreateDate());
        applicationProcessRunStepDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        applicationProcessRunStepDTO.setModifyDate(ZonedDateTime.now());

        ApplicationProcessRunStepDTO result = applicationProcessRunStepService.save(applicationProcessRunStepDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, applicationProcessRunStepDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /application-process-run-steps : get all the applicationProcessRunSteps.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of applicationProcessRunSteps in body
     */
    @GetMapping("/application-process-run-steps")
    @Timed
    public ResponseEntity<List<ApplicationProcessRunStepDTO>> getAllApplicationProcessRunSteps(ApplicationProcessRunStepCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ApplicationProcessRunSteps by criteria: {}", criteria);
        Page<ApplicationProcessRunStepDTO> page = applicationProcessRunStepQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/application-process-run-steps");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /application-process-run-steps/count : count all the applicationProcessRunSteps.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/application-process-run-steps/count")
    @Timed
    public ResponseEntity<Long> countApplicationProcessRunSteps(ApplicationProcessRunStepCriteria criteria) {
        log.debug("REST request to count ApplicationProcessRunSteps by criteria: {}", criteria);
        return ResponseEntity.ok().body(applicationProcessRunStepQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /application-process-run-steps/:id : get the "id" applicationProcessRunStep.
     *
     * @param id the id of the applicationProcessRunStepDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the applicationProcessRunStepDTO, or with status 404 (Not Found)
     */
    @GetMapping("/application-process-run-steps/{id}")
    @Timed
    public ResponseEntity<ApplicationProcessRunStepDTO> getApplicationProcessRunStep(@PathVariable Long id) {
        log.debug("REST request to get ApplicationProcessRunStep : {}", id);
        Optional<ApplicationProcessRunStepDTO> applicationProcessRunStepDTO = applicationProcessRunStepService.findOne(id);
        return ResponseUtil.wrapOrNotFound(applicationProcessRunStepDTO);
    }

    /**
     * DELETE  /application-process-run-steps/:id : delete the "id" applicationProcessRunStep.
     *
     * @param id the id of the applicationProcessRunStepDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/application-process-run-steps/{id}")
    @Timed
    public ResponseEntity<Void> deleteApplicationProcessRunStep(@PathVariable Long id) {
        log.debug("REST request to delete ApplicationProcessRunStep : {}", id);
        applicationProcessRunStepService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
