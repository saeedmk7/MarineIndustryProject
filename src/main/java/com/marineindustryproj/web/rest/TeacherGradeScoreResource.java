package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.TeacherGradeScoreService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.TeacherGradeScoreDTO;
import com.marineindustryproj.service.dto.TeacherGradeScoreCriteria;
import com.marineindustryproj.service.TeacherGradeScoreQueryService;
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

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TeacherGradeScore.
 */
@RestController
@RequestMapping("/api")
public class TeacherGradeScoreResource {

    private final Logger log = LoggerFactory.getLogger(TeacherGradeScoreResource.class);

    private static final String ENTITY_NAME = "teacherGradeScore";

    private final TeacherGradeScoreService teacherGradeScoreService;

    private final TeacherGradeScoreQueryService teacherGradeScoreQueryService;

    public TeacherGradeScoreResource(TeacherGradeScoreService teacherGradeScoreService, TeacherGradeScoreQueryService teacherGradeScoreQueryService) {
        this.teacherGradeScoreService = teacherGradeScoreService;
        this.teacherGradeScoreQueryService = teacherGradeScoreQueryService;
    }

    /**
     * POST  /teacher-grade-scores : Create a new teacherGradeScore.
     *
     * @param teacherGradeScoreDTO the teacherGradeScoreDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new teacherGradeScoreDTO, or with status 400 (Bad Request) if the teacherGradeScore has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/teacher-grade-scores")
    @Timed
    public ResponseEntity<TeacherGradeScoreDTO> createTeacherGradeScore(@Valid @RequestBody TeacherGradeScoreDTO teacherGradeScoreDTO) throws URISyntaxException {
        log.debug("REST request to save TeacherGradeScore : {}", teacherGradeScoreDTO);
        if (teacherGradeScoreDTO.getId() != null) {
            throw new BadRequestAlertException("A new teacherGradeScore cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TeacherGradeScoreDTO result = teacherGradeScoreService.save(teacherGradeScoreDTO);
        return ResponseEntity.created(new URI("/api/teacher-grade-scores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /teacher-grade-scores : Updates an existing teacherGradeScore.
     *
     * @param teacherGradeScoreDTO the teacherGradeScoreDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated teacherGradeScoreDTO,
     * or with status 400 (Bad Request) if the teacherGradeScoreDTO is not valid,
     * or with status 500 (Internal Server Error) if the teacherGradeScoreDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/teacher-grade-scores")
    @Timed
    public ResponseEntity<TeacherGradeScoreDTO> updateTeacherGradeScore(@Valid @RequestBody TeacherGradeScoreDTO teacherGradeScoreDTO) throws URISyntaxException {
        log.debug("REST request to update TeacherGradeScore : {}", teacherGradeScoreDTO);
        if (teacherGradeScoreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TeacherGradeScoreDTO result = teacherGradeScoreService.save(teacherGradeScoreDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, teacherGradeScoreDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /teacher-grade-scores : get all the teacherGradeScores.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of teacherGradeScores in body
     */
    @GetMapping("/teacher-grade-scores")
    @Timed
    public ResponseEntity<List<TeacherGradeScoreDTO>> getAllTeacherGradeScores(TeacherGradeScoreCriteria criteria, Pageable pageable) {
        log.debug("REST request to get TeacherGradeScores by criteria: {}", criteria);
        Page<TeacherGradeScoreDTO> page = teacherGradeScoreQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/teacher-grade-scores");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /teacher-grade-scores/count : count all the teacherGradeScores.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/teacher-grade-scores/count")
    @Timed
    public ResponseEntity<Long> countTeacherGradeScores(TeacherGradeScoreCriteria criteria) {
        log.debug("REST request to count TeacherGradeScores by criteria: {}", criteria);
        return ResponseEntity.ok().body(teacherGradeScoreQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /teacher-grade-scores/:id : get the "id" teacherGradeScore.
     *
     * @param id the id of the teacherGradeScoreDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the teacherGradeScoreDTO, or with status 404 (Not Found)
     */
    @GetMapping("/teacher-grade-scores/{id}")
    @Timed
    public ResponseEntity<TeacherGradeScoreDTO> getTeacherGradeScore(@PathVariable Long id) {
        log.debug("REST request to get TeacherGradeScore : {}", id);
        Optional<TeacherGradeScoreDTO> teacherGradeScoreDTO = teacherGradeScoreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(teacherGradeScoreDTO);
    }

    /**
     * DELETE  /teacher-grade-scores/:id : delete the "id" teacherGradeScore.
     *
     * @param id the id of the teacherGradeScoreDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/teacher-grade-scores/{id}")
    @Timed
    public ResponseEntity<Void> deleteTeacherGradeScore(@PathVariable Long id) {
        log.debug("REST request to delete TeacherGradeScore : {}", id);
        teacherGradeScoreService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
