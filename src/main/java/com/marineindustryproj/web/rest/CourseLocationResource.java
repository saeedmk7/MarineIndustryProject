package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.CourseLocationService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.CourseLocationDTO;
import com.marineindustryproj.service.dto.CourseLocationCriteria;
import com.marineindustryproj.service.CourseLocationQueryService;
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
 * REST controller for managing CourseLocation.
 */
@RestController
@RequestMapping("/api")
public class CourseLocationResource {

    private final Logger log = LoggerFactory.getLogger(CourseLocationResource.class);

    private static final String ENTITY_NAME = "courseLocation";

    private final CourseLocationService courseLocationService;

    private final CourseLocationQueryService courseLocationQueryService;

    public CourseLocationResource(CourseLocationService courseLocationService, CourseLocationQueryService courseLocationQueryService) {
        this.courseLocationService = courseLocationService;
        this.courseLocationQueryService = courseLocationQueryService;
    }

    /**
     * POST  /course-locations : Create a new courseLocation.
     *
     * @param courseLocationDTO the courseLocationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new courseLocationDTO, or with status 400 (Bad Request) if the courseLocation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/course-locations")
    @Timed
    public ResponseEntity<CourseLocationDTO> createCourseLocation(@Valid @RequestBody CourseLocationDTO courseLocationDTO) throws URISyntaxException {
        log.debug("REST request to save CourseLocation : {}", courseLocationDTO);
        if (courseLocationDTO.getId() != null) {
            throw new BadRequestAlertException("A new courseLocation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        courseLocationDTO.setCreateDate(ZonedDateTime.now());
        courseLocationDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        CourseLocationDTO result = courseLocationService.save(courseLocationDTO);
        return ResponseEntity.created(new URI("/api/course-locations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /course-locations : Updates an existing courseLocation.
     *
     * @param courseLocationDTO the courseLocationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated courseLocationDTO,
     * or with status 400 (Bad Request) if the courseLocationDTO is not valid,
     * or with status 500 (Internal Server Error) if the courseLocationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/course-locations")
    @Timed
    public ResponseEntity<CourseLocationDTO> updateCourseLocation(@Valid @RequestBody CourseLocationDTO courseLocationDTO) throws URISyntaxException {
        log.debug("REST request to update CourseLocation : {}", courseLocationDTO);
        if (courseLocationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CourseLocationDTO courseLocation = courseLocationService.findOne(courseLocationDTO.getId()).get();

        courseLocationDTO.setCreateUserLogin(courseLocation.getCreateUserLogin());
        courseLocationDTO.setCreateDate(courseLocation.getCreateDate());
        courseLocationDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        courseLocationDTO.setModifyDate(ZonedDateTime.now());
        CourseLocationDTO result = courseLocationService.save(courseLocationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, courseLocationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /course-locations : get all the courseLocations.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of courseLocations in body
     */
    @GetMapping("/course-locations")
    @Timed
    public ResponseEntity<List<CourseLocationDTO>> getAllCourseLocations(CourseLocationCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CourseLocations by criteria: {}", criteria);
        Page<CourseLocationDTO> page = courseLocationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/course-locations");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /course-locations/count : count all the courseLocations.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/course-locations/count")
    @Timed
    public ResponseEntity<Long> countCourseLocations (CourseLocationCriteria criteria) {
        log.debug("REST request to count CourseLocations by criteria: {}", criteria);
        return ResponseEntity.ok().body(courseLocationQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /course-locations/:id : get the "id" courseLocation.
     *
     * @param id the id of the courseLocationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the courseLocationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/course-locations/{id}")
    @Timed
    public ResponseEntity<CourseLocationDTO> getCourseLocation(@PathVariable Long id) {
        log.debug("REST request to get CourseLocation : {}", id);
        Optional<CourseLocationDTO> courseLocationDTO = courseLocationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(courseLocationDTO);
    }

    /**
     * DELETE  /course-locations/:id : delete the "id" courseLocation.
     *
     * @param id the id of the courseLocationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/course-locations/{id}")
    @Timed
    public ResponseEntity<Void> deleteCourseLocation(@PathVariable Long id) {
        log.debug("REST request to delete CourseLocation : {}", id);
        courseLocationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
