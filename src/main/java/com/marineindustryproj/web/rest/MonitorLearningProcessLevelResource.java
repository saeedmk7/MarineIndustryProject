package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.MonitorLearningProcessLevelService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.MonitorLearningProcessLevelDTO;
import com.marineindustryproj.service.dto.MonitorLearningProcessLevelCriteria;
import com.marineindustryproj.service.MonitorLearningProcessLevelQueryService;
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
 * REST controller for managing MonitorLearningProcessLevel.
 */
@RestController
@RequestMapping("/api")
public class MonitorLearningProcessLevelResource {

    private final Logger log = LoggerFactory.getLogger(MonitorLearningProcessLevelResource.class);

    private static final String ENTITY_NAME = "monitorLearningProcessLevel";

    private final MonitorLearningProcessLevelService monitorLearningProcessLevelService;

    private final MonitorLearningProcessLevelQueryService monitorLearningProcessLevelQueryService;

    public MonitorLearningProcessLevelResource(MonitorLearningProcessLevelService monitorLearningProcessLevelService, MonitorLearningProcessLevelQueryService monitorLearningProcessLevelQueryService) {
        this.monitorLearningProcessLevelService = monitorLearningProcessLevelService;
        this.monitorLearningProcessLevelQueryService = monitorLearningProcessLevelQueryService;
    }

    /**
     * POST  /monitor-learning-process-levels : Create a new monitorLearningProcessLevel.
     *
     * @param monitorLearningProcessLevelDTO the monitorLearningProcessLevelDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new monitorLearningProcessLevelDTO, or with status 400 (Bad Request) if the monitorLearningProcessLevel has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/monitor-learning-process-levels")
    @Timed
    public ResponseEntity<MonitorLearningProcessLevelDTO> createMonitorLearningProcessLevel(@Valid @RequestBody MonitorLearningProcessLevelDTO monitorLearningProcessLevelDTO) throws URISyntaxException {
        log.debug("REST request to save MonitorLearningProcessLevel : {}", monitorLearningProcessLevelDTO);
        if (monitorLearningProcessLevelDTO.getId() != null) {
            throw new BadRequestAlertException("A new monitorLearningProcessLevel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        monitorLearningProcessLevelDTO.setCreateDate(ZonedDateTime.now());
        monitorLearningProcessLevelDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        MonitorLearningProcessLevelDTO result = monitorLearningProcessLevelService.save(monitorLearningProcessLevelDTO);
        return ResponseEntity.created(new URI("/api/monitor-learning-process-levels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /monitor-learning-process-levels : Updates an existing monitorLearningProcessLevel.
     *
     * @param monitorLearningProcessLevelDTO the monitorLearningProcessLevelDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated monitorLearningProcessLevelDTO,
     * or with status 400 (Bad Request) if the monitorLearningProcessLevelDTO is not valid,
     * or with status 500 (Internal Server Error) if the monitorLearningProcessLevelDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/monitor-learning-process-levels")
    @Timed
    public ResponseEntity<MonitorLearningProcessLevelDTO> updateMonitorLearningProcessLevel(@Valid @RequestBody MonitorLearningProcessLevelDTO monitorLearningProcessLevelDTO) throws URISyntaxException {
        log.debug("REST request to update MonitorLearningProcessLevel : {}", monitorLearningProcessLevelDTO);
        if (monitorLearningProcessLevelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MonitorLearningProcessLevelDTO monitorLearningProcessLevel = monitorLearningProcessLevelService.findOne(monitorLearningProcessLevelDTO.getId()).get();

        monitorLearningProcessLevelDTO.setCreateUserLogin(monitorLearningProcessLevel.getCreateUserLogin());
        monitorLearningProcessLevelDTO.setCreateDate(monitorLearningProcessLevel.getCreateDate());
        monitorLearningProcessLevelDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        monitorLearningProcessLevelDTO.setModifyDate(ZonedDateTime.now());

        MonitorLearningProcessLevelDTO result = monitorLearningProcessLevelService.save(monitorLearningProcessLevelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, monitorLearningProcessLevelDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /monitor-learning-process-levels : get all the monitorLearningProcessLevels.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of monitorLearningProcessLevels in body
     */
    @GetMapping("/monitor-learning-process-levels")
    @Timed
    public ResponseEntity<List<MonitorLearningProcessLevelDTO>> getAllMonitorLearningProcessLevels(MonitorLearningProcessLevelCriteria criteria, Pageable pageable) {
        log.debug("REST request to get MonitorLearningProcessLevels by criteria: {}", criteria);
        Page<MonitorLearningProcessLevelDTO> page = monitorLearningProcessLevelQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/monitor-learning-process-levels");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /monitor-learning-process-levels/count : count all the monitorLearningProcessLevels.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/monitor-learning-process-levels/count")
    @Timed
    public ResponseEntity<Long> countMonitorLearningProcessLevels(MonitorLearningProcessLevelCriteria criteria) {
        log.debug("REST request to count MonitorLearningProcessLevels by criteria: {}", criteria);
        return ResponseEntity.ok().body(monitorLearningProcessLevelQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /monitor-learning-process-levels/:id : get the "id" monitorLearningProcessLevel.
     *
     * @param id the id of the monitorLearningProcessLevelDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the monitorLearningProcessLevelDTO, or with status 404 (Not Found)
     */
    @GetMapping("/monitor-learning-process-levels/{id}")
    @Timed
    public ResponseEntity<MonitorLearningProcessLevelDTO> getMonitorLearningProcessLevel(@PathVariable Long id) {
        log.debug("REST request to get MonitorLearningProcessLevel : {}", id);
        Optional<MonitorLearningProcessLevelDTO> monitorLearningProcessLevelDTO = monitorLearningProcessLevelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(monitorLearningProcessLevelDTO);
    }

    /**
     * DELETE  /monitor-learning-process-levels/:id : delete the "id" monitorLearningProcessLevel.
     *
     * @param id the id of the monitorLearningProcessLevelDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/monitor-learning-process-levels/{id}")
    @Timed
    public ResponseEntity<Void> deleteMonitorLearningProcessLevel(@PathVariable Long id) {
        log.debug("REST request to delete MonitorLearningProcessLevel : {}", id);
        monitorLearningProcessLevelService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
