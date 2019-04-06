package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.domain.Teacher;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.TeacherService;
import com.marineindustryproj.service.dto.customs.TeacherMinDTO;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.TeacherDTO;
import com.marineindustryproj.service.dto.TeacherCriteria;
import com.marineindustryproj.service.TeacherQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
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
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing Teacher.
 */
@RestController
@RequestMapping("/api")
public class TeacherResource {

    private final Logger log = LoggerFactory.getLogger(TeacherResource.class);

    private static final String ENTITY_NAME = "teacher";

    private final TeacherService teacherService;

    private final TeacherQueryService teacherQueryService;

    public TeacherResource(TeacherService teacherService,
                           TeacherQueryService teacherQueryService) {
        this.teacherService = teacherService;
        this.teacherQueryService = teacherQueryService;
    }

    /**
     * POST  /teachers : Create a new teacher.
     *
     * @param teacherDTO the teacherDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new teacherDTO, or with status 400 (Bad Request) if the teacher has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/teachers")
    @Timed
    public ResponseEntity<TeacherDTO> createTeacher(@Valid @RequestBody TeacherDTO teacherDTO) throws URISyntaxException {
        log.debug("REST request to save Teacher : {}", teacherDTO);
        if (teacherDTO.getId() != null) {
            throw new BadRequestAlertException("A new teacher cannot already have an ID", ENTITY_NAME, "idexists");
        }
        teacherDTO.setId(teacherDTO.getCode());
        teacherDTO.setCreateDate(ZonedDateTime.now());
        teacherDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        TeacherDTO result = teacherService.save(teacherDTO);
        return ResponseEntity.created(new URI("/api/teachers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /teachers : Updates an existing teacher.
     *
     * @param teacherDTO the teacherDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated teacherDTO,
     * or with status 400 (Bad Request) if the teacherDTO is not valid,
     * or with status 500 (Internal Server Error) if the teacherDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/teachers")
    @Timed
    public ResponseEntity<TeacherDTO> updateTeacher(@Valid @RequestBody TeacherDTO teacherDTO) throws URISyntaxException {
        log.debug("REST request to update Teacher : {}", teacherDTO);
        if (teacherDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        TeacherDTO teacher = teacherService.findOne(teacherDTO.getId()).get();

        teacherDTO.setId(teacherDTO.getCode());
        teacherDTO.setCreateUserLogin(teacher.getCreateUserLogin());
        teacherDTO.setCreateDate(teacher.getCreateDate());
        teacherDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        teacherDTO.setModifyDate(ZonedDateTime.now());

        TeacherDTO result = teacherService.save(teacherDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, teacherDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /teachers : get all the teachers.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of teachers in body
     */
    @GetMapping("/teachers")
    @Timed
    public ResponseEntity<List<TeacherDTO>> getAllTeachers(TeacherCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Teachers by criteria: {}", criteria);
        Page<TeacherDTO> page = teacherQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/teachers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /teachers : get all the teachers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of teachers in body
     */
    @GetMapping("/teachers/all")
    @Timed
    public ResponseEntity<List<TeacherMinDTO>> getAllTeachers() {
        log.debug("REST request to get Teachers");
        //Page<TeacherDTO> page = teacherQueryService.findByCriteria(criteria, pageable);
        List<TeacherMinDTO> teachers = teacherService.findAllFromCache();
        //HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/teachers");
        return ResponseEntity.ok().body(teachers);
    }

    /**
    * GET  /teachers/count : count all the teachers.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/teachers/count")
    @Timed
    public ResponseEntity<Long> countTeachers (TeacherCriteria criteria) {
        log.debug("REST request to count Teachers by criteria: {}", criteria);
        return ResponseEntity.ok().body(teacherQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /teachers/:id : get the "id" teacher.
     *
     * @param id the id of the teacherDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the teacherDTO, or with status 404 (Not Found)
     */
    @GetMapping("/teachers/{id}")
    @Timed
    public ResponseEntity<TeacherDTO> getTeacher(@PathVariable Long id) {
        log.debug("REST request to get Teacher : {}", id);
        Optional<TeacherDTO> teacherDTO = teacherService.findOne(id);
        return ResponseUtil.wrapOrNotFound(teacherDTO);
    }

    /**
     * DELETE  /teachers/:id : delete the "id" teacher.
     *
     * @param id the id of the teacherDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/teachers/{id}")
    @Timed
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        log.debug("REST request to delete Teacher : {}", id);
        teacherService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
