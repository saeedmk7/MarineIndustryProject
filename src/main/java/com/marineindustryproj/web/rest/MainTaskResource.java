package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.MainTaskService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.MainTaskDTO;
import com.marineindustryproj.service.dto.MainTaskCriteria;
import com.marineindustryproj.service.MainTaskQueryService;
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
 * REST controller for managing MainTask.
 */
@RestController
@RequestMapping("/api")
public class MainTaskResource {

    private final Logger log = LoggerFactory.getLogger(MainTaskResource.class);

    private static final String ENTITY_NAME = "mainTask";

    private final MainTaskService mainTaskService;

    private final MainTaskQueryService mainTaskQueryService;

    public MainTaskResource(MainTaskService mainTaskService, MainTaskQueryService mainTaskQueryService) {
        this.mainTaskService = mainTaskService;
        this.mainTaskQueryService = mainTaskQueryService;
    }

    /**
     * POST  /main-tasks : Create a new mainTask.
     *
     * @param mainTaskDTO the mainTaskDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mainTaskDTO, or with status 400 (Bad Request) if the mainTask has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/main-tasks")
    @Timed
    public ResponseEntity<MainTaskDTO> createMainTask(@Valid @RequestBody MainTaskDTO mainTaskDTO) throws URISyntaxException {
        log.debug("REST request to save MainTask : {}", mainTaskDTO);
        if (mainTaskDTO.getId() != null) {
            throw new BadRequestAlertException("A new mainTask cannot already have an ID", ENTITY_NAME, "idexists");
        }

        mainTaskDTO.setCreateDate(ZonedDateTime.now());
        mainTaskDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        MainTaskDTO result = mainTaskService.save(mainTaskDTO);
        return ResponseEntity.created(new URI("/api/main-tasks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /main-tasks : Updates an existing mainTask.
     *
     * @param mainTaskDTO the mainTaskDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mainTaskDTO,
     * or with status 400 (Bad Request) if the mainTaskDTO is not valid,
     * or with status 500 (Internal Server Error) if the mainTaskDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/main-tasks")
    @Timed
    public ResponseEntity<MainTaskDTO> updateMainTask(@Valid @RequestBody MainTaskDTO mainTaskDTO) throws URISyntaxException {
        log.debug("REST request to update MainTask : {}", mainTaskDTO);
        if (mainTaskDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        MainTaskDTO mainTask = mainTaskService.findOne(mainTaskDTO.getId()).get();

        mainTaskDTO.setCreateUserLogin(mainTask.getCreateUserLogin());
        mainTaskDTO.setCreateDate(mainTask.getCreateDate());
        mainTaskDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        mainTaskDTO.setModifyDate(ZonedDateTime.now());

        MainTaskDTO result = mainTaskService.save(mainTaskDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mainTaskDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /main-tasks : get all the mainTasks.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of mainTasks in body
     */
    @GetMapping("/main-tasks")
    @Timed
    public ResponseEntity<List<MainTaskDTO>> getAllMainTasks(MainTaskCriteria criteria, Pageable pageable) {
        log.debug("REST request to get MainTasks by criteria: {}", criteria);
        Page<MainTaskDTO> page = mainTaskQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/main-tasks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /main-tasks/count : count all the mainTasks.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/main-tasks/count")
    @Timed
    public ResponseEntity<Long> countMainTasks (MainTaskCriteria criteria) {
        log.debug("REST request to count MainTasks by criteria: {}", criteria);
        return ResponseEntity.ok().body(mainTaskQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /main-tasks/:id : get the "id" mainTask.
     *
     * @param id the id of the mainTaskDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mainTaskDTO, or with status 404 (Not Found)
     */
    @GetMapping("/main-tasks/{id}")
    @Timed
    public ResponseEntity<MainTaskDTO> getMainTask(@PathVariable Long id) {
        log.debug("REST request to get MainTask : {}", id);
        Optional<MainTaskDTO> mainTaskDTO = mainTaskService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mainTaskDTO);
    }

    /**
     * DELETE  /main-tasks/:id : delete the "id" mainTask.
     *
     * @param id the id of the mainTaskDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/main-tasks/{id}")
    @Timed
    public ResponseEntity<Void> deleteMainTask(@PathVariable Long id) {
        log.debug("REST request to delete MainTask : {}", id);
        mainTaskService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
