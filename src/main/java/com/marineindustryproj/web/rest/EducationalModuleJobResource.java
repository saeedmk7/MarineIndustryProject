package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.EducationalModuleJobService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.EducationalModuleJobDTO;
import com.marineindustryproj.service.dto.EducationalModuleJobCriteria;
import com.marineindustryproj.service.EducationalModuleJobQueryService;
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
 * REST controller for managing EducationalModuleJob.
 */
@RestController
@RequestMapping("/api")
public class EducationalModuleJobResource {

    private final Logger log = LoggerFactory.getLogger(EducationalModuleJobResource.class);

    private static final String ENTITY_NAME = "educationalModuleJob";

    private final EducationalModuleJobService educationalModuleJobService;

    private final EducationalModuleJobQueryService educationalModuleJobQueryService;

    public EducationalModuleJobResource(EducationalModuleJobService educationalModuleJobService, EducationalModuleJobQueryService educationalModuleJobQueryService) {
        this.educationalModuleJobService = educationalModuleJobService;
        this.educationalModuleJobQueryService = educationalModuleJobQueryService;
    }

    /**
     * POST  /educational-module-jobs : Create a new educationalModuleJob.
     *
     * @param educationalModuleJobDTOS the educationalModuleJobDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new educationalModuleJobDTO, or with status 400 (Bad Request) if the educationalModuleJob has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    /*@PostMapping("/educational-module-jobs")
    @Timed
    public ResponseEntity<EducationalModuleJobDTO> createEducationalModuleJob(@Valid @RequestBody EducationalModuleJobDTO educationalModuleJobDTO) throws URISyntaxException {
        log.debug("REST request to save EducationalModuleJob : {}", educationalModuleJobDTO);
        if (educationalModuleJobDTO.getId() != null) {
            throw new BadRequestAlertException("A new educationalModuleJob cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EducationalModuleJobDTO result = educationalModuleJobService.save(educationalModuleJobDTO);
        return ResponseEntity.created(new URI("/api/educational-module-jobs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }*/

    @PostMapping("/educational-module-jobs")
    @Timed
    public ResponseEntity<EducationalModuleJobDTO> createEducationalModuleJob(@Valid @RequestBody List<EducationalModuleJobDTO> educationalModuleJobDTOS) throws URISyntaxException {
        log.debug("REST request to save EducationalModuleJobs : {}", educationalModuleJobDTOS);
        if (educationalModuleJobDTOS == null) {
            throw new BadRequestAlertException("داده های ارسالی اشتباه است",ENTITY_NAME,"idexists");
        }
        for (EducationalModuleJobDTO educationalModuleJobDTO : educationalModuleJobDTOS) {
            educationalModuleJobDTO.setCreateDate(ZonedDateTime.now());
            educationalModuleJobDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        }
        EducationalModuleJobDTO result = educationalModuleJobService.bulkSave(educationalModuleJobDTOS);
        return ResponseEntity.created(new URI("/api/educational-module-jobs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /educational-module-jobs : Updates an existing educationalModuleJob.
     *
     * @param educationalModuleJobDTO the educationalModuleJobDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated educationalModuleJobDTO,
     * or with status 400 (Bad Request) if the educationalModuleJobDTO is not valid,
     * or with status 500 (Internal Server Error) if the educationalModuleJobDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/educational-module-jobs")
    @Timed
    public ResponseEntity<EducationalModuleJobDTO> updateEducationalModuleJob(@Valid @RequestBody EducationalModuleJobDTO educationalModuleJobDTO) throws URISyntaxException {
        log.debug("REST request to update EducationalModuleJob : {}", educationalModuleJobDTO);
        if (educationalModuleJobDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EducationalModuleJobDTO result = educationalModuleJobService.save(educationalModuleJobDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, educationalModuleJobDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /educational-module-jobs : get all the educationalModuleJobs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of educationalModuleJobs in body
     */
    @GetMapping("/educational-module-jobs")
    @Timed
    public ResponseEntity<List<EducationalModuleJobDTO>> getAllEducationalModuleJobs(EducationalModuleJobCriteria criteria) {
        log.debug("REST request to get EducationalModuleJobs by criteria: {}", criteria);
        List<EducationalModuleJobDTO> list = educationalModuleJobQueryService.findByCriteria(criteria);
        HttpHeaders headers = new HttpHeaders(); // = /HeaderUtil. //PaginationUtil.generatePaginationHttpHeaders(list, "/api/educational-module-jobs");
        return new ResponseEntity<>(list, headers, HttpStatus.OK);
    }
    /*@GetMapping("/educational-module-jobs")
    @Timed
    public ResponseEntity<List<EducationalModuleJobDTO>> getAllEducationalModuleJobs(EducationalModuleJobCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EducationalModuleJobs by criteria: {}", criteria);
        Page<EducationalModuleJobDTO> page = educationalModuleJobQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/educational-module-jobs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }*/

    /**
    * GET  /educational-module-jobs/count : count all the educationalModuleJobs.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/educational-module-jobs/count")
    @Timed
    public ResponseEntity<Long> countEducationalModuleJobs (EducationalModuleJobCriteria criteria) {
        log.debug("REST request to count EducationalModuleJobs by criteria: {}", criteria);
        return ResponseEntity.ok().body(educationalModuleJobQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /educational-module-jobs/:id : get the "id" educationalModuleJob.
     *
     * @param id the id of the educationalModuleJobDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the educationalModuleJobDTO, or with status 404 (Not Found)
     */
    @GetMapping("/educational-module-jobs/{id}")
    @Timed
    public ResponseEntity<EducationalModuleJobDTO> getEducationalModuleJob(@PathVariable Long id) {
        log.debug("REST request to get EducationalModuleJob : {}", id);
        Optional<EducationalModuleJobDTO> educationalModuleJobDTO = educationalModuleJobService.findOne(id);
        return ResponseUtil.wrapOrNotFound(educationalModuleJobDTO);
    }

    /**
     * DELETE  /educational-module-jobs/:id : delete the "id" educationalModuleJob.
     *
     * @param id the id of the educationalModuleJobDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/educational-module-jobs/{id}")
    @Timed
    public ResponseEntity<Void> deleteEducationalModuleJob(@PathVariable Long id) {
        log.debug("REST request to delete EducationalModuleJob : {}", id);
        educationalModuleJobService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
