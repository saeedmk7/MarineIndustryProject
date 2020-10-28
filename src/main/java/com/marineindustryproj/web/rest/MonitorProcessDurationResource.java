package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.MonitorProcessDurationService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.MonitorProcessDurationDTO;
import com.marineindustryproj.service.dto.MonitorProcessDurationCriteria;
import com.marineindustryproj.service.MonitorProcessDurationQueryService;
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
 * REST controller for managing MonitorProcessDuration.
 */
@RestController
@RequestMapping("/api")
public class MonitorProcessDurationResource {

    private final Logger log = LoggerFactory.getLogger(MonitorProcessDurationResource.class);

    private static final String ENTITY_NAME = "monitorProcessDuration";

    private final MonitorProcessDurationService monitorProcessDurationService;

    private final MonitorProcessDurationQueryService monitorProcessDurationQueryService;

    public MonitorProcessDurationResource(MonitorProcessDurationService monitorProcessDurationService, MonitorProcessDurationQueryService monitorProcessDurationQueryService) {
        this.monitorProcessDurationService = monitorProcessDurationService;
        this.monitorProcessDurationQueryService = monitorProcessDurationQueryService;
    }

    /**
     * POST  /monitor-process-durations : Create a new monitorProcessDuration.
     *
     * @param monitorProcessDurationDTO the monitorProcessDurationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new monitorProcessDurationDTO, or with status 400 (Bad Request) if the monitorProcessDuration has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/monitor-process-durations")
    @Timed
    public ResponseEntity<MonitorProcessDurationDTO> createMonitorProcessDuration(@Valid @RequestBody MonitorProcessDurationDTO monitorProcessDurationDTO) throws URISyntaxException {
        log.debug("REST request to save MonitorProcessDuration : {}", monitorProcessDurationDTO);
        if (monitorProcessDurationDTO.getId() != null) {
            throw new BadRequestAlertException("A new monitorProcessDuration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        monitorProcessDurationDTO.setCreateDate(ZonedDateTime.now());
        monitorProcessDurationDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        MonitorProcessDurationDTO result = monitorProcessDurationService.save(monitorProcessDurationDTO);
        return ResponseEntity.created(new URI("/api/monitor-process-durations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /monitor-process-durations : Updates an existing monitorProcessDuration.
     *
     * @param monitorProcessDurationDTO the monitorProcessDurationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated monitorProcessDurationDTO,
     * or with status 400 (Bad Request) if the monitorProcessDurationDTO is not valid,
     * or with status 500 (Internal Server Error) if the monitorProcessDurationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/monitor-process-durations")
    @Timed
    public ResponseEntity<MonitorProcessDurationDTO> updateMonitorProcessDuration(@Valid @RequestBody MonitorProcessDurationDTO monitorProcessDurationDTO) throws URISyntaxException {
        log.debug("REST request to update MonitorProcessDuration : {}", monitorProcessDurationDTO);
        if (monitorProcessDurationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MonitorProcessDurationDTO monitorProcessDuration = monitorProcessDurationService.findOne(monitorProcessDurationDTO.getId()).get();

        monitorProcessDurationDTO.setCreateUserLogin(monitorProcessDuration.getCreateUserLogin());
        monitorProcessDurationDTO.setCreateDate(monitorProcessDuration.getCreateDate());
        monitorProcessDurationDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        monitorProcessDurationDTO.setModifyDate(ZonedDateTime.now());

        MonitorProcessDurationDTO result = monitorProcessDurationService.save(monitorProcessDurationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, monitorProcessDurationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /monitor-process-durations : get all the monitorProcessDurations.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of monitorProcessDurations in body
     */
    @GetMapping("/monitor-process-durations")
    @Timed
    public ResponseEntity<List<MonitorProcessDurationDTO>> getAllMonitorProcessDurations(MonitorProcessDurationCriteria criteria, Pageable pageable) {
        log.debug("REST request to get MonitorProcessDurations by criteria: {}", criteria);
        Page<MonitorProcessDurationDTO> page = monitorProcessDurationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/monitor-process-durations");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /monitor-process-durations/count : count all the monitorProcessDurations.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/monitor-process-durations/count")
    @Timed
    public ResponseEntity<Long> countMonitorProcessDurations(MonitorProcessDurationCriteria criteria) {
        log.debug("REST request to count MonitorProcessDurations by criteria: {}", criteria);
        return ResponseEntity.ok().body(monitorProcessDurationQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /monitor-process-durations/:id : get the "id" monitorProcessDuration.
     *
     * @param id the id of the monitorProcessDurationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the monitorProcessDurationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/monitor-process-durations/{id}")
    @Timed
    public ResponseEntity<MonitorProcessDurationDTO> getMonitorProcessDuration(@PathVariable Long id) {
        log.debug("REST request to get MonitorProcessDuration : {}", id);
        Optional<MonitorProcessDurationDTO> monitorProcessDurationDTO = monitorProcessDurationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(monitorProcessDurationDTO);
    }

    /**
     * DELETE  /monitor-process-durations/:id : delete the "id" monitorProcessDuration.
     *
     * @param id the id of the monitorProcessDurationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/monitor-process-durations/{id}")
    @Timed
    public ResponseEntity<Void> deleteMonitorProcessDuration(@PathVariable Long id) {
        log.debug("REST request to delete MonitorProcessDuration : {}", id);
        monitorProcessDurationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
