package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.MahiatCourseService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.MahiatCourseDTO;
import com.marineindustryproj.service.dto.MahiatCourseCriteria;
import com.marineindustryproj.service.MahiatCourseQueryService;
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
 * REST controller for managing MahiatCourse.
 */
@RestController
@RequestMapping("/api")
public class MahiatCourseResource {

    private final Logger log = LoggerFactory.getLogger(MahiatCourseResource.class);

    private static final String ENTITY_NAME = "mahiatCourse";

    private final MahiatCourseService mahiatCourseService;

    private final MahiatCourseQueryService mahiatCourseQueryService;

    public MahiatCourseResource(MahiatCourseService mahiatCourseService, MahiatCourseQueryService mahiatCourseQueryService) {
        this.mahiatCourseService = mahiatCourseService;
        this.mahiatCourseQueryService = mahiatCourseQueryService;
    }

    /**
     * POST  /mahiat-courses : Create a new mahiatCourse.
     *
     * @param mahiatCourseDTO the mahiatCourseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mahiatCourseDTO, or with status 400 (Bad Request) if the mahiatCourse has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/mahiat-courses")
    @Timed
    public ResponseEntity<MahiatCourseDTO> createMahiatCourse(@Valid @RequestBody MahiatCourseDTO mahiatCourseDTO) throws URISyntaxException {
        log.debug("REST request to save MahiatCourse : {}", mahiatCourseDTO);
        if (mahiatCourseDTO.getId() != null) {
            throw new BadRequestAlertException("A new mahiatCourse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MahiatCourseDTO result = mahiatCourseService.save(mahiatCourseDTO);
        return ResponseEntity.created(new URI("/api/mahiat-courses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mahiat-courses : Updates an existing mahiatCourse.
     *
     * @param mahiatCourseDTO the mahiatCourseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mahiatCourseDTO,
     * or with status 400 (Bad Request) if the mahiatCourseDTO is not valid,
     * or with status 500 (Internal Server Error) if the mahiatCourseDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/mahiat-courses")
    @Timed
    public ResponseEntity<MahiatCourseDTO> updateMahiatCourse(@Valid @RequestBody MahiatCourseDTO mahiatCourseDTO) throws URISyntaxException {
        log.debug("REST request to update MahiatCourse : {}", mahiatCourseDTO);
        if (mahiatCourseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MahiatCourseDTO result = mahiatCourseService.save(mahiatCourseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mahiatCourseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mahiat-courses : get all the mahiatCourses.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of mahiatCourses in body
     */
    @GetMapping("/mahiat-courses")
    @Timed
    public ResponseEntity<List<MahiatCourseDTO>> getAllMahiatCourses(MahiatCourseCriteria criteria, Pageable pageable) {
        log.debug("REST request to get MahiatCourses by criteria: {}", criteria);
        Page<MahiatCourseDTO> page = mahiatCourseQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mahiat-courses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /mahiat-courses/count : count all the mahiatCourses.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/mahiat-courses/count")
    @Timed
    public ResponseEntity<Long> countMahiatCourses (MahiatCourseCriteria criteria) {
        log.debug("REST request to count MahiatCourses by criteria: {}", criteria);
        return ResponseEntity.ok().body(mahiatCourseQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /mahiat-courses/:id : get the "id" mahiatCourse.
     *
     * @param id the id of the mahiatCourseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mahiatCourseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/mahiat-courses/{id}")
    @Timed
    public ResponseEntity<MahiatCourseDTO> getMahiatCourse(@PathVariable Long id) {
        log.debug("REST request to get MahiatCourse : {}", id);
        Optional<MahiatCourseDTO> mahiatCourseDTO = mahiatCourseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mahiatCourseDTO);
    }

    /**
     * DELETE  /mahiat-courses/:id : delete the "id" mahiatCourse.
     *
     * @param id the id of the mahiatCourseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/mahiat-courses/{id}")
    @Timed
    public ResponseEntity<Void> deleteMahiatCourse(@PathVariable Long id) {
        log.debug("REST request to delete MahiatCourse : {}", id);
        mahiatCourseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
