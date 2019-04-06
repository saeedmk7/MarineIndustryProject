package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.ActivityAreaService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.ActivityAreaDTO;
import com.marineindustryproj.service.dto.ActivityAreaCriteria;
import com.marineindustryproj.service.ActivityAreaQueryService;
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
 * REST controller for managing ActivityArea.
 */
@RestController
@RequestMapping("/api")
public class ActivityAreaResource {

    private final Logger log = LoggerFactory.getLogger(ActivityAreaResource.class);

    private static final String ENTITY_NAME = "activityArea";

    private final ActivityAreaService activityAreaService;

    private final ActivityAreaQueryService activityAreaQueryService;

    public ActivityAreaResource(ActivityAreaService activityAreaService, ActivityAreaQueryService activityAreaQueryService) {
        this.activityAreaService = activityAreaService;
        this.activityAreaQueryService = activityAreaQueryService;
    }

    /**
     * POST  /activity-areas : Create a new activityArea.
     *
     * @param activityAreaDTO the activityAreaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new activityAreaDTO, or with status 400 (Bad Request) if the activityArea has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/activity-areas")
    @Timed
    public ResponseEntity<ActivityAreaDTO> createActivityArea(@Valid @RequestBody ActivityAreaDTO activityAreaDTO) throws URISyntaxException {
        log.debug("REST request to save ActivityArea : {}", activityAreaDTO);
        if (activityAreaDTO.getId() != null) {
            throw new BadRequestAlertException("A new activityArea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        activityAreaDTO.setCreateDate(ZonedDateTime.now());
        activityAreaDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        ActivityAreaDTO result = activityAreaService.save(activityAreaDTO);
        return ResponseEntity.created(new URI("/api/activity-areas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /activity-areas : Updates an existing activityArea.
     *
     * @param activityAreaDTO the activityAreaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated activityAreaDTO,
     * or with status 400 (Bad Request) if the activityAreaDTO is not valid,
     * or with status 500 (Internal Server Error) if the activityAreaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/activity-areas")
    @Timed
    public ResponseEntity<ActivityAreaDTO> updateActivityArea(@Valid @RequestBody ActivityAreaDTO activityAreaDTO) throws URISyntaxException {
        log.debug("REST request to update ActivityArea : {}", activityAreaDTO);
        if (activityAreaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ActivityAreaDTO activityArea = activityAreaService.findOne(activityAreaDTO.getId()).get();

        activityAreaDTO.setCreateUserLogin(activityArea.getCreateUserLogin());
        activityAreaDTO.setCreateDate(activityArea.getCreateDate());
        activityAreaDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        activityAreaDTO.setModifyDate(ZonedDateTime.now());
        ActivityAreaDTO result = activityAreaService.save(activityAreaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, activityAreaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /activity-areas : get all the activityAreas.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of activityAreas in body
     */
    @GetMapping("/activity-areas")
    @Timed
    public ResponseEntity<List<ActivityAreaDTO>> getAllActivityAreas(ActivityAreaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ActivityAreas by criteria: {}", criteria);
        Page<ActivityAreaDTO> page = activityAreaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/activity-areas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /activity-areas/count : count all the activityAreas.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/activity-areas/count")
    @Timed
    public ResponseEntity<Long> countActivityAreas (ActivityAreaCriteria criteria) {
        log.debug("REST request to count ActivityAreas by criteria: {}", criteria);
        return ResponseEntity.ok().body(activityAreaQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /activity-areas/:id : get the "id" activityArea.
     *
     * @param id the id of the activityAreaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the activityAreaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/activity-areas/{id}")
    @Timed
    public ResponseEntity<ActivityAreaDTO> getActivityArea(@PathVariable Long id) {
        log.debug("REST request to get ActivityArea : {}", id);
        Optional<ActivityAreaDTO> activityAreaDTO = activityAreaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(activityAreaDTO);
    }

    /**
     * DELETE  /activity-areas/:id : delete the "id" activityArea.
     *
     * @param id the id of the activityAreaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/activity-areas/{id}")
    @Timed
    public ResponseEntity<Void> deleteActivityArea(@PathVariable Long id) {
        log.debug("REST request to delete ActivityArea : {}", id);
        activityAreaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
