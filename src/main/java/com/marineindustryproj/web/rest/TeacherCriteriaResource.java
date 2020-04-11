package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.TeacherCriteriaService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.TeacherCriteriaDTO;
import com.marineindustryproj.service.dto.TeacherCriteriaCriteria;
import com.marineindustryproj.service.TeacherCriteriaQueryService;
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
 * REST controller for managing TeacherCriteria.
 */
@RestController
@RequestMapping("/api")
public class TeacherCriteriaResource {

    private final Logger log = LoggerFactory.getLogger(TeacherCriteriaResource.class);

    private static final String ENTITY_NAME = "teacherCriteria";

    private final TeacherCriteriaService teacherCriteriaService;

    private final TeacherCriteriaQueryService teacherCriteriaQueryService;

    public TeacherCriteriaResource(TeacherCriteriaService teacherCriteriaService, TeacherCriteriaQueryService teacherCriteriaQueryService) {
        this.teacherCriteriaService = teacherCriteriaService;
        this.teacherCriteriaQueryService = teacherCriteriaQueryService;
    }

    /**
     * POST  /teacher-criteria : Create a new teacherCriteria.
     *
     * @param teacherCriteriaDTO the teacherCriteriaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new teacherCriteriaDTO, or with status 400 (Bad Request) if the teacherCriteria has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/teacher-criteria")
    @Timed
    public ResponseEntity<TeacherCriteriaDTO> createTeacherCriteria(@Valid @RequestBody TeacherCriteriaDTO teacherCriteriaDTO) throws URISyntaxException {
        log.debug("REST request to save TeacherCriteria : {}", teacherCriteriaDTO);
        if (teacherCriteriaDTO.getId() != null) {
            throw new BadRequestAlertException("A new teacherCriteria cannot already have an ID", ENTITY_NAME, "idexists");
        }

        teacherCriteriaDTO.setCreateDate(ZonedDateTime.now());
        teacherCriteriaDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        TeacherCriteriaDTO result = teacherCriteriaService.save(teacherCriteriaDTO);
        return ResponseEntity.created(new URI("/api/teacher-criteria/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /teacher-criteria : Updates an existing teacherCriteria.
     *
     * @param teacherCriteriaDTO the teacherCriteriaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated teacherCriteriaDTO,
     * or with status 400 (Bad Request) if the teacherCriteriaDTO is not valid,
     * or with status 500 (Internal Server Error) if the teacherCriteriaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/teacher-criteria")
    @Timed
    public ResponseEntity<TeacherCriteriaDTO> updateTeacherCriteria(@Valid @RequestBody TeacherCriteriaDTO teacherCriteriaDTO) throws URISyntaxException {
        log.debug("REST request to update TeacherCriteria : {}", teacherCriteriaDTO);
        if (teacherCriteriaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        TeacherCriteriaDTO teacherCriteria = teacherCriteriaService.findOne(teacherCriteriaDTO.getId()).get();

        teacherCriteriaDTO.setCreateUserLogin(teacherCriteria.getCreateUserLogin());
        teacherCriteriaDTO.setCreateDate(teacherCriteria.getCreateDate());
        teacherCriteriaDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        teacherCriteriaDTO.setModifyDate(ZonedDateTime.now());

        TeacherCriteriaDTO result = teacherCriteriaService.save(teacherCriteriaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, teacherCriteriaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /teacher-criteria : get all the teacherCriteria.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of teacherCriteria in body
     */
    @GetMapping("/teacher-criteria")
    @Timed
    public ResponseEntity<List<TeacherCriteriaDTO>> getAllTeacherCriteria(TeacherCriteriaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get TeacherCriteria by criteria: {}", criteria);
        Page<TeacherCriteriaDTO> page = teacherCriteriaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/teacher-criteria");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /teacher-criteria/count : count all the teacherCriteria.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/teacher-criteria/count")
    @Timed
    public ResponseEntity<Long> countTeacherCriteria(TeacherCriteriaCriteria criteria) {
        log.debug("REST request to count TeacherCriteria by criteria: {}", criteria);
        return ResponseEntity.ok().body(teacherCriteriaQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /teacher-criteria/:id : get the "id" teacherCriteria.
     *
     * @param id the id of the teacherCriteriaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the teacherCriteriaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/teacher-criteria/{id}")
    @Timed
    public ResponseEntity<TeacherCriteriaDTO> getTeacherCriteria(@PathVariable Long id) {
        log.debug("REST request to get TeacherCriteria : {}", id);
        Optional<TeacherCriteriaDTO> teacherCriteriaDTO = teacherCriteriaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(teacherCriteriaDTO);
    }

    /**
     * DELETE  /teacher-criteria/:id : delete the "id" teacherCriteria.
     *
     * @param id the id of the teacherCriteriaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/teacher-criteria/{id}")
    @Timed
    public ResponseEntity<Void> deleteTeacherCriteria(@PathVariable Long id) {
        log.debug("REST request to delete TeacherCriteria : {}", id);
        teacherCriteriaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
