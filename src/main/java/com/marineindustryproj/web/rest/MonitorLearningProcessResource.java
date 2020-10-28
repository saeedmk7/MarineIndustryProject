package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.MonitorLearningProcessService;
import com.marineindustryproj.service.dto.MonitorLearningProcessGradeDTO;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.MonitorLearningProcessDTO;
import com.marineindustryproj.service.dto.MonitorLearningProcessCriteria;
import com.marineindustryproj.service.MonitorLearningProcessQueryService;
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
 * REST controller for managing MonitorLearningProcess.
 */
@RestController
@RequestMapping("/api")
public class MonitorLearningProcessResource {

    private final Logger log = LoggerFactory.getLogger(MonitorLearningProcessResource.class);

    private static final String ENTITY_NAME = "monitorLearningProcess";

    private final MonitorLearningProcessService monitorLearningProcessService;

    private final MonitorLearningProcessQueryService monitorLearningProcessQueryService;

    public MonitorLearningProcessResource(MonitorLearningProcessService monitorLearningProcessService, MonitorLearningProcessQueryService monitorLearningProcessQueryService) {
        this.monitorLearningProcessService = monitorLearningProcessService;
        this.monitorLearningProcessQueryService = monitorLearningProcessQueryService;
    }

    /**
     * POST  /monitor-learning-processes : Create a new monitorLearningProcess.
     *
     * @param monitorLearningProcessDTO the monitorLearningProcessDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new monitorLearningProcessDTO, or with status 400 (Bad Request) if the monitorLearningProcess has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/monitor-learning-processes")
    @Timed
    public ResponseEntity<MonitorLearningProcessDTO> createMonitorLearningProcess(@Valid @RequestBody MonitorLearningProcessDTO monitorLearningProcessDTO) throws URISyntaxException {
        log.debug("REST request to save MonitorLearningProcess : {}", monitorLearningProcessDTO);
        if (monitorLearningProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new monitorLearningProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        monitorLearningProcessDTO.setCreateDate(ZonedDateTime.now());
        monitorLearningProcessDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        for (MonitorLearningProcessGradeDTO monitorLearningProcessGrade : monitorLearningProcessDTO.getMonitorLearningProcessGrades()) {
            monitorLearningProcessGrade.setCreateDate(ZonedDateTime.now());
            monitorLearningProcessGrade.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        }

        MonitorLearningProcessDTO result = monitorLearningProcessService.save(monitorLearningProcessDTO);
        return ResponseEntity.created(new URI("/api/monitor-learning-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /monitor-learning-processes : Updates an existing monitorLearningProcess.
     *
     * @param monitorLearningProcessDTO the monitorLearningProcessDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated monitorLearningProcessDTO,
     * or with status 400 (Bad Request) if the monitorLearningProcessDTO is not valid,
     * or with status 500 (Internal Server Error) if the monitorLearningProcessDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/monitor-learning-processes")
    @Timed
    public ResponseEntity<MonitorLearningProcessDTO> updateMonitorLearningProcess(@Valid @RequestBody MonitorLearningProcessDTO monitorLearningProcessDTO) throws URISyntaxException {
        log.debug("REST request to update MonitorLearningProcess : {}", monitorLearningProcessDTO);
        if (monitorLearningProcessDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MonitorLearningProcessDTO monitorLearningProcess = monitorLearningProcessService.findOne(monitorLearningProcessDTO.getId()).get();

        monitorLearningProcessDTO.setCreateUserLogin(monitorLearningProcess.getCreateUserLogin());
        monitorLearningProcessDTO.setCreateDate(monitorLearningProcess.getCreateDate());
        monitorLearningProcessDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        monitorLearningProcessDTO.setModifyDate(ZonedDateTime.now());

        MonitorLearningProcessDTO result = monitorLearningProcessService.save(monitorLearningProcessDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, monitorLearningProcessDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /monitor-learning-processes : get all the monitorLearningProcesses.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of monitorLearningProcesses in body
     */
    @GetMapping("/monitor-learning-processes")
    @Timed
    public ResponseEntity<List<MonitorLearningProcessDTO>> getAllMonitorLearningProcesses(MonitorLearningProcessCriteria criteria, Pageable pageable) {
        log.debug("REST request to get MonitorLearningProcesses by criteria: {}", criteria);
        Page<MonitorLearningProcessDTO> page = monitorLearningProcessQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/monitor-learning-processes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /monitor-learning-processes/count : count all the monitorLearningProcesses.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/monitor-learning-processes/count")
    @Timed
    public ResponseEntity<Long> countMonitorLearningProcesses(MonitorLearningProcessCriteria criteria) {
        log.debug("REST request to count MonitorLearningProcesses by criteria: {}", criteria);
        return ResponseEntity.ok().body(monitorLearningProcessQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /monitor-learning-processes/:id : get the "id" monitorLearningProcess.
     *
     * @param id the id of the monitorLearningProcessDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the monitorLearningProcessDTO, or with status 404 (Not Found)
     */
    @GetMapping("/monitor-learning-processes/{id}")
    @Timed
    public ResponseEntity<MonitorLearningProcessDTO> getMonitorLearningProcess(@PathVariable Long id) {
        log.debug("REST request to get MonitorLearningProcess : {}", id);
        Optional<MonitorLearningProcessDTO> monitorLearningProcessDTO = monitorLearningProcessService.findOne(id);
        return ResponseUtil.wrapOrNotFound(monitorLearningProcessDTO);
    }

    /**
     * DELETE  /monitor-learning-processes/:id : delete the "id" monitorLearningProcess.
     *
     * @param id the id of the monitorLearningProcessDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/monitor-learning-processes/{id}")
    @Timed
    public ResponseEntity<Void> deleteMonitorLearningProcess(@PathVariable Long id) {
        log.debug("REST request to delete MonitorLearningProcess : {}", id);
        monitorLearningProcessService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
