package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.TeacherGradeService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.TeacherGradeDTO;
import com.marineindustryproj.service.dto.TeacherGradeCriteria;
import com.marineindustryproj.service.TeacherGradeQueryService;
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
 * REST controller for managing TeacherGrade.
 */
@RestController
@RequestMapping("/api")
public class TeacherGradeResource {

    private final Logger log = LoggerFactory.getLogger(TeacherGradeResource.class);

    private static final String ENTITY_NAME = "teacherGrade";

    private final TeacherGradeService teacherGradeService;

    private final TeacherGradeQueryService teacherGradeQueryService;

    public TeacherGradeResource(TeacherGradeService teacherGradeService, TeacherGradeQueryService teacherGradeQueryService) {
        this.teacherGradeService = teacherGradeService;
        this.teacherGradeQueryService = teacherGradeQueryService;
    }

    /**
     * POST  /teacher-grades : Create a new teacherGrade.
     *
     * @param teacherGradeDTO the teacherGradeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new teacherGradeDTO, or with status 400 (Bad Request) if the teacherGrade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/teacher-grades")
    @Timed
    public ResponseEntity<TeacherGradeDTO> createTeacherGrade(@Valid @RequestBody TeacherGradeDTO teacherGradeDTO) throws URISyntaxException {
        log.debug("REST request to save TeacherGrade : {}", teacherGradeDTO);
        if (teacherGradeDTO.getId() != null) {
            throw new BadRequestAlertException("A new teacherGrade cannot already have an ID", ENTITY_NAME, "idexists");
        }

        teacherGradeDTO.setCreateDate(ZonedDateTime.now());
        teacherGradeDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        TeacherGradeDTO result = teacherGradeService.saveWithScore(teacherGradeDTO);
        return ResponseEntity.created(new URI("/api/teacher-grades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /teacher-grades : Updates an existing teacherGrade.
     *
     * @param teacherGradeDTO the teacherGradeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated teacherGradeDTO,
     * or with status 400 (Bad Request) if the teacherGradeDTO is not valid,
     * or with status 500 (Internal Server Error) if the teacherGradeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/teacher-grades")
    @Timed
    public ResponseEntity<TeacherGradeDTO> updateTeacherGrade(@Valid @RequestBody TeacherGradeDTO teacherGradeDTO) throws URISyntaxException {
        log.debug("REST request to update TeacherGrade : {}", teacherGradeDTO);
        if (teacherGradeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        TeacherGradeDTO teacherGrade = teacherGradeService.findOne(teacherGradeDTO.getId()).get();

        teacherGradeDTO.setCreateUserLogin(teacherGrade.getCreateUserLogin());
        teacherGradeDTO.setCreateDate(teacherGrade.getCreateDate());
        teacherGradeDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        teacherGradeDTO.setModifyDate(ZonedDateTime.now());

        TeacherGradeDTO result = teacherGradeService.saveWithScore(teacherGradeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, teacherGradeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /teacher-grades : get all the teacherGrades.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of teacherGrades in body
     */
    @GetMapping("/teacher-grades")
    @Timed
    public ResponseEntity<List<TeacherGradeDTO>> getAllTeacherGrades(TeacherGradeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get TeacherGrades by criteria: {}", criteria);
        Page<TeacherGradeDTO> page = teacherGradeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/teacher-grades");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /teacher-grades/count : count all the teacherGrades.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/teacher-grades/count")
    @Timed
    public ResponseEntity<Long> countTeacherGrades(TeacherGradeCriteria criteria) {
        log.debug("REST request to count TeacherGrades by criteria: {}", criteria);
        return ResponseEntity.ok().body(teacherGradeQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /teacher-grades/:id : get the "id" teacherGrade.
     *
     * @param id the id of the teacherGradeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the teacherGradeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/teacher-grades/{id}")
    @Timed
    public ResponseEntity<TeacherGradeDTO> getTeacherGrade(@PathVariable Long id) {
        log.debug("REST request to get TeacherGrade : {}", id);
        Optional<TeacherGradeDTO> teacherGradeDTO = teacherGradeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(teacherGradeDTO);
    }

    /**
     * DELETE  /teacher-grades/:id : delete the "id" teacherGrade.
     *
     * @param id the id of the teacherGradeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/teacher-grades/{id}")
    @Timed
    public ResponseEntity<Void> deleteTeacherGrade(@PathVariable Long id) {
        log.debug("REST request to delete TeacherGrade : {}", id);
        teacherGradeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
