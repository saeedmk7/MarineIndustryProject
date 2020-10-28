package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.TeacherCriteriaGroupService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.TeacherCriteriaGroupDTO;
import com.marineindustryproj.service.dto.TeacherCriteriaGroupCriteria;
import com.marineindustryproj.service.TeacherCriteriaGroupQueryService;
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
 * REST controller for managing TeacherCriteriaGroup.
 */
@RestController
@RequestMapping("/api")
public class TeacherCriteriaGroupResource {

    private final Logger log = LoggerFactory.getLogger(TeacherCriteriaGroupResource.class);

    private static final String ENTITY_NAME = "teacherCriteriaGroup";

    private final TeacherCriteriaGroupService teacherCriteriaGroupService;

    private final TeacherCriteriaGroupQueryService teacherCriteriaGroupQueryService;

    public TeacherCriteriaGroupResource(TeacherCriteriaGroupService teacherCriteriaGroupService, TeacherCriteriaGroupQueryService teacherCriteriaGroupQueryService) {
        this.teacherCriteriaGroupService = teacherCriteriaGroupService;
        this.teacherCriteriaGroupQueryService = teacherCriteriaGroupQueryService;
    }

    /**
     * POST  /teacher-criteria-groups : Create a new teacherCriteriaGroup.
     *
     * @param teacherCriteriaGroupDTO the teacherCriteriaGroupDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new teacherCriteriaGroupDTO, or with status 400 (Bad Request) if the teacherCriteriaGroup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/teacher-criteria-groups")
    @Timed
    public ResponseEntity<TeacherCriteriaGroupDTO> createTeacherCriteriaGroup(@Valid @RequestBody TeacherCriteriaGroupDTO teacherCriteriaGroupDTO) throws URISyntaxException {
        log.debug("REST request to save TeacherCriteriaGroup : {}", teacherCriteriaGroupDTO);
        if (teacherCriteriaGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new teacherCriteriaGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        teacherCriteriaGroupDTO.setCreateDate(ZonedDateTime.now());
        teacherCriteriaGroupDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        TeacherCriteriaGroupDTO result = teacherCriteriaGroupService.save(teacherCriteriaGroupDTO);
        return ResponseEntity.created(new URI("/api/teacher-criteria-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /teacher-criteria-groups : Updates an existing teacherCriteriaGroup.
     *
     * @param teacherCriteriaGroupDTO the teacherCriteriaGroupDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated teacherCriteriaGroupDTO,
     * or with status 400 (Bad Request) if the teacherCriteriaGroupDTO is not valid,
     * or with status 500 (Internal Server Error) if the teacherCriteriaGroupDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/teacher-criteria-groups")
    @Timed
    public ResponseEntity<TeacherCriteriaGroupDTO> updateTeacherCriteriaGroup(@Valid @RequestBody TeacherCriteriaGroupDTO teacherCriteriaGroupDTO) throws URISyntaxException {
        log.debug("REST request to update TeacherCriteriaGroup : {}", teacherCriteriaGroupDTO);
        if (teacherCriteriaGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        TeacherCriteriaGroupDTO teacherCriteriaGroup = teacherCriteriaGroupService.findOne(teacherCriteriaGroupDTO.getId()).get();

        teacherCriteriaGroupDTO.setCreateUserLogin(teacherCriteriaGroup.getCreateUserLogin());
        teacherCriteriaGroupDTO.setCreateDate(teacherCriteriaGroup.getCreateDate());
        teacherCriteriaGroupDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        teacherCriteriaGroupDTO.setModifyDate(ZonedDateTime.now());

        TeacherCriteriaGroupDTO result = teacherCriteriaGroupService.save(teacherCriteriaGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, teacherCriteriaGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /teacher-criteria-groups : get all the teacherCriteriaGroups.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of teacherCriteriaGroups in body
     */
    @GetMapping("/teacher-criteria-groups")
    @Timed
    public ResponseEntity<List<TeacherCriteriaGroupDTO>> getAllTeacherCriteriaGroups(TeacherCriteriaGroupCriteria criteria, Pageable pageable) {
        log.debug("REST request to get TeacherCriteriaGroups by criteria: {}", criteria);
        Page<TeacherCriteriaGroupDTO> page = teacherCriteriaGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/teacher-criteria-groups");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /teacher-criteria-groups/count : count all the teacherCriteriaGroups.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/teacher-criteria-groups/count")
    @Timed
    public ResponseEntity<Long> countTeacherCriteriaGroups(TeacherCriteriaGroupCriteria criteria) {
        log.debug("REST request to count TeacherCriteriaGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(teacherCriteriaGroupQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /teacher-criteria-groups/:id : get the "id" teacherCriteriaGroup.
     *
     * @param id the id of the teacherCriteriaGroupDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the teacherCriteriaGroupDTO, or with status 404 (Not Found)
     */
    @GetMapping("/teacher-criteria-groups/{id}")
    @Timed
    public ResponseEntity<TeacherCriteriaGroupDTO> getTeacherCriteriaGroup(@PathVariable Long id) {
        log.debug("REST request to get TeacherCriteriaGroup : {}", id);
        Optional<TeacherCriteriaGroupDTO> teacherCriteriaGroupDTO = teacherCriteriaGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(teacherCriteriaGroupDTO);
    }

    /**
     * DELETE  /teacher-criteria-groups/:id : delete the "id" teacherCriteriaGroup.
     *
     * @param id the id of the teacherCriteriaGroupDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/teacher-criteria-groups/{id}")
    @Timed
    public ResponseEntity<Void> deleteTeacherCriteriaGroup(@PathVariable Long id) {
        log.debug("REST request to delete TeacherCriteriaGroup : {}", id);
        teacherCriteriaGroupService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
