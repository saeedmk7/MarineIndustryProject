package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.MonitorLearningProcessGradeService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.MonitorLearningProcessGradeDTO;
import com.marineindustryproj.service.dto.MonitorLearningProcessGradeCriteria;
import com.marineindustryproj.service.MonitorLearningProcessGradeQueryService;
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
 * REST controller for managing MonitorLearningProcessGrade.
 */
@RestController
@RequestMapping("/api")
public class MonitorLearningProcessGradeResource {

    private final Logger log = LoggerFactory.getLogger(MonitorLearningProcessGradeResource.class);

    private static final String ENTITY_NAME = "monitorLearningProcessGrade";

    private final MonitorLearningProcessGradeService monitorLearningProcessGradeService;

    private final MonitorLearningProcessGradeQueryService monitorLearningProcessGradeQueryService;

    public MonitorLearningProcessGradeResource(MonitorLearningProcessGradeService monitorLearningProcessGradeService, MonitorLearningProcessGradeQueryService monitorLearningProcessGradeQueryService) {
        this.monitorLearningProcessGradeService = monitorLearningProcessGradeService;
        this.monitorLearningProcessGradeQueryService = monitorLearningProcessGradeQueryService;
    }

    /**
     * POST  /monitor-learning-process-grades : Create a new monitorLearningProcessGrade.
     *
     * @param monitorLearningProcessGradeDTO the monitorLearningProcessGradeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new monitorLearningProcessGradeDTO, or with status 400 (Bad Request) if the monitorLearningProcessGrade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/monitor-learning-process-grades")
    @Timed
    public ResponseEntity<MonitorLearningProcessGradeDTO> createMonitorLearningProcessGrade(@Valid @RequestBody MonitorLearningProcessGradeDTO monitorLearningProcessGradeDTO) throws URISyntaxException {
        log.debug("REST request to save MonitorLearningProcessGrade : {}", monitorLearningProcessGradeDTO);
        if (monitorLearningProcessGradeDTO.getId() != null) {
            throw new BadRequestAlertException("A new monitorLearningProcessGrade cannot already have an ID", ENTITY_NAME, "idexists");
        }

        monitorLearningProcessGradeDTO.setCreateDate(ZonedDateTime.now());
        monitorLearningProcessGradeDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        MonitorLearningProcessGradeDTO result = monitorLearningProcessGradeService.save(monitorLearningProcessGradeDTO);
        return ResponseEntity.created(new URI("/api/monitor-learning-process-grades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /monitor-learning-process-grades : Updates an existing monitorLearningProcessGrade.
     *
     * @param monitorLearningProcessGradeDTO the monitorLearningProcessGradeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated monitorLearningProcessGradeDTO,
     * or with status 400 (Bad Request) if the monitorLearningProcessGradeDTO is not valid,
     * or with status 500 (Internal Server Error) if the monitorLearningProcessGradeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/monitor-learning-process-grades")
    @Timed
    public ResponseEntity<MonitorLearningProcessGradeDTO> updateMonitorLearningProcessGrade(@Valid @RequestBody MonitorLearningProcessGradeDTO monitorLearningProcessGradeDTO) throws URISyntaxException {
        log.debug("REST request to update MonitorLearningProcessGrade : {}", monitorLearningProcessGradeDTO);
        if (monitorLearningProcessGradeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        MonitorLearningProcessGradeDTO monitorLearningProcessGrade = monitorLearningProcessGradeService.findOne(monitorLearningProcessGradeDTO.getId()).get();

        monitorLearningProcessGradeDTO.setCreateUserLogin(monitorLearningProcessGrade.getCreateUserLogin());
        monitorLearningProcessGradeDTO.setCreateDate(monitorLearningProcessGrade.getCreateDate());
        monitorLearningProcessGradeDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        monitorLearningProcessGradeDTO.setModifyDate(ZonedDateTime.now());

        MonitorLearningProcessGradeDTO result = monitorLearningProcessGradeService.save(monitorLearningProcessGradeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, monitorLearningProcessGradeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /monitor-learning-process-grades : get all the monitorLearningProcessGrades.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of monitorLearningProcessGrades in body
     */
    @GetMapping("/monitor-learning-process-grades")
    @Timed
    public ResponseEntity<List<MonitorLearningProcessGradeDTO>> getAllMonitorLearningProcessGrades(MonitorLearningProcessGradeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get MonitorLearningProcessGrades by criteria: {}", criteria);
        Page<MonitorLearningProcessGradeDTO> page = monitorLearningProcessGradeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/monitor-learning-process-grades");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /monitor-learning-process-grades/count : count all the monitorLearningProcessGrades.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/monitor-learning-process-grades/count")
    @Timed
    public ResponseEntity<Long> countMonitorLearningProcessGrades(MonitorLearningProcessGradeCriteria criteria) {
        log.debug("REST request to count MonitorLearningProcessGrades by criteria: {}", criteria);
        return ResponseEntity.ok().body(monitorLearningProcessGradeQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /monitor-learning-process-grades/:id : get the "id" monitorLearningProcessGrade.
     *
     * @param id the id of the monitorLearningProcessGradeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the monitorLearningProcessGradeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/monitor-learning-process-grades/{id}")
    @Timed
    public ResponseEntity<MonitorLearningProcessGradeDTO> getMonitorLearningProcessGrade(@PathVariable Long id) {
        log.debug("REST request to get MonitorLearningProcessGrade : {}", id);
        Optional<MonitorLearningProcessGradeDTO> monitorLearningProcessGradeDTO = monitorLearningProcessGradeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(monitorLearningProcessGradeDTO);
    }

    /**
     * DELETE  /monitor-learning-process-grades/:id : delete the "id" monitorLearningProcessGrade.
     *
     * @param id the id of the monitorLearningProcessGradeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/monitor-learning-process-grades/{id}")
    @Timed
    public ResponseEntity<Void> deleteMonitorLearningProcessGrade(@PathVariable Long id) {
        log.debug("REST request to delete MonitorLearningProcessGrade : {}", id);
        monitorLearningProcessGradeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
