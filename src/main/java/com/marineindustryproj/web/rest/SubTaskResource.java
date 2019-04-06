package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.SubTaskService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.SubTaskDTO;
import com.marineindustryproj.service.dto.SubTaskCriteria;
import com.marineindustryproj.service.SubTaskQueryService;
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
 * REST controller for managing SubTask.
 */
@RestController
@RequestMapping("/api")
public class SubTaskResource {

    private final Logger log = LoggerFactory.getLogger(SubTaskResource.class);

    private static final String ENTITY_NAME = "subTask";

    private final SubTaskService subTaskService;

    private final SubTaskQueryService subTaskQueryService;

    public SubTaskResource(SubTaskService subTaskService, SubTaskQueryService subTaskQueryService) {
        this.subTaskService = subTaskService;
        this.subTaskQueryService = subTaskQueryService;
    }

    /**
     * POST  /sub-tasks : Create a new subTask.
     *
     * @param subTaskDTO the subTaskDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new subTaskDTO, or with status 400 (Bad Request) if the subTask has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sub-tasks")
    @Timed
    public ResponseEntity<SubTaskDTO> createSubTask(@Valid @RequestBody SubTaskDTO subTaskDTO) throws URISyntaxException {
        log.debug("REST request to save SubTask : {}", subTaskDTO);
        if (subTaskDTO.getId() != null) {
            throw new BadRequestAlertException("A new subTask cannot already have an ID", ENTITY_NAME, "idexists");
        }
        subTaskDTO.setCreateDate(ZonedDateTime.now());
        subTaskDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        SubTaskDTO result = subTaskService.save(subTaskDTO);
        return ResponseEntity.created(new URI("/api/sub-tasks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sub-tasks : Updates an existing subTask.
     *
     * @param subTaskDTO the subTaskDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated subTaskDTO,
     * or with status 400 (Bad Request) if the subTaskDTO is not valid,
     * or with status 500 (Internal Server Error) if the subTaskDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sub-tasks")
    @Timed
    public ResponseEntity<SubTaskDTO> updateSubTask(@Valid @RequestBody SubTaskDTO subTaskDTO) throws URISyntaxException {
        log.debug("REST request to update SubTask : {}", subTaskDTO);
        if (subTaskDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SubTaskDTO subTask = subTaskService.findOne(subTaskDTO.getId()).get();

        subTaskDTO.setCreateUserLogin(subTask.getCreateUserLogin());
        subTaskDTO.setCreateDate(subTask.getCreateDate());
        subTaskDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        subTaskDTO.setModifyDate(ZonedDateTime.now());
        SubTaskDTO result = subTaskService.save(subTaskDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, subTaskDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sub-tasks : get all the subTasks.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of subTasks in body
     */
    @GetMapping("/sub-tasks")
    @Timed
    public ResponseEntity<List<SubTaskDTO>> getAllSubTasks(SubTaskCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SubTasks by criteria: {}", criteria);
        Page<SubTaskDTO> page = subTaskQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sub-tasks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /sub-tasks/count : count all the subTasks.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/sub-tasks/count")
    @Timed
    public ResponseEntity<Long> countSubTasks (SubTaskCriteria criteria) {
        log.debug("REST request to count SubTasks by criteria: {}", criteria);
        return ResponseEntity.ok().body(subTaskQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /sub-tasks/:id : get the "id" subTask.
     *
     * @param id the id of the subTaskDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the subTaskDTO, or with status 404 (Not Found)
     */
    @GetMapping("/sub-tasks/{id}")
    @Timed
    public ResponseEntity<SubTaskDTO> getSubTask(@PathVariable Long id) {
        log.debug("REST request to get SubTask : {}", id);
        Optional<SubTaskDTO> subTaskDTO = subTaskService.findOne(id);
        return ResponseUtil.wrapOrNotFound(subTaskDTO);
    }

    /**
     * DELETE  /sub-tasks/:id : delete the "id" subTask.
     *
     * @param id the id of the subTaskDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sub-tasks/{id}")
    @Timed
    public ResponseEntity<Void> deleteSubTask(@PathVariable Long id) {
        log.debug("REST request to delete SubTask : {}", id);
        subTaskService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
