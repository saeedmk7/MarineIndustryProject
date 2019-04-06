package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.TeachApproachService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.TeachApproachDTO;
import com.marineindustryproj.service.dto.TeachApproachCriteria;
import com.marineindustryproj.service.TeachApproachQueryService;
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
 * REST controller for managing TeachApproach.
 */
@RestController
@RequestMapping("/api")
public class TeachApproachResource {

    private final Logger log = LoggerFactory.getLogger(TeachApproachResource.class);

    private static final String ENTITY_NAME = "teachApproach";

    private final TeachApproachService teachApproachService;

    private final TeachApproachQueryService teachApproachQueryService;

    public TeachApproachResource(TeachApproachService teachApproachService, TeachApproachQueryService teachApproachQueryService) {
        this.teachApproachService = teachApproachService;
        this.teachApproachQueryService = teachApproachQueryService;
    }

    /**
     * POST  /teach-approaches : Create a new teachApproach.
     *
     * @param teachApproachDTO the teachApproachDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new teachApproachDTO, or with status 400 (Bad Request) if the teachApproach has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/teach-approaches")
    @Timed
    public ResponseEntity<TeachApproachDTO> createTeachApproach(@Valid @RequestBody TeachApproachDTO teachApproachDTO) throws URISyntaxException {
        log.debug("REST request to save TeachApproach : {}", teachApproachDTO);
        if (teachApproachDTO.getId() != null) {
            throw new BadRequestAlertException("A new teachApproach cannot already have an ID", ENTITY_NAME, "idexists");
        }
        teachApproachDTO.setCreateDate(ZonedDateTime.now());
        teachApproachDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        TeachApproachDTO result = teachApproachService.save(teachApproachDTO);
        return ResponseEntity.created(new URI("/api/teach-approaches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /teach-approaches : Updates an existing teachApproach.
     *
     * @param teachApproachDTO the teachApproachDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated teachApproachDTO,
     * or with status 400 (Bad Request) if the teachApproachDTO is not valid,
     * or with status 500 (Internal Server Error) if the teachApproachDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/teach-approaches")
    @Timed
    public ResponseEntity<TeachApproachDTO> updateTeachApproach(@Valid @RequestBody TeachApproachDTO teachApproachDTO) throws URISyntaxException {
        log.debug("REST request to update TeachApproach : {}", teachApproachDTO);
        if (teachApproachDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TeachApproachDTO teachApproach = teachApproachService.findOne(teachApproachDTO.getId()).get();

        teachApproachDTO.setCreateUserLogin(teachApproach.getCreateUserLogin());
        teachApproachDTO.setCreateDate(teachApproach.getCreateDate());
        teachApproachDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        teachApproachDTO.setModifyDate(ZonedDateTime.now());
        TeachApproachDTO result = teachApproachService.save(teachApproachDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, teachApproachDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /teach-approaches : get all the teachApproaches.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of teachApproaches in body
     */
    @GetMapping("/teach-approaches")
    @Timed
    public ResponseEntity<List<TeachApproachDTO>> getAllTeachApproaches(TeachApproachCriteria criteria, Pageable pageable) {
        log.debug("REST request to get TeachApproaches by criteria: {}", criteria);
        Page<TeachApproachDTO> page = teachApproachQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/teach-approaches");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /teach-approaches/count : count all the teachApproaches.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/teach-approaches/count")
    @Timed
    public ResponseEntity<Long> countTeachApproaches (TeachApproachCriteria criteria) {
        log.debug("REST request to count TeachApproaches by criteria: {}", criteria);
        return ResponseEntity.ok().body(teachApproachQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /teach-approaches/:id : get the "id" teachApproach.
     *
     * @param id the id of the teachApproachDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the teachApproachDTO, or with status 404 (Not Found)
     */
    @GetMapping("/teach-approaches/{id}")
    @Timed
    public ResponseEntity<TeachApproachDTO> getTeachApproach(@PathVariable Long id) {
        log.debug("REST request to get TeachApproach : {}", id);
        Optional<TeachApproachDTO> teachApproachDTO = teachApproachService.findOne(id);
        return ResponseUtil.wrapOrNotFound(teachApproachDTO);
    }

    /**
     * DELETE  /teach-approaches/:id : delete the "id" teachApproach.
     *
     * @param id the id of the teachApproachDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/teach-approaches/{id}")
    @Timed
    public ResponseEntity<Void> deleteTeachApproach(@PathVariable Long id) {
        log.debug("REST request to delete TeachApproach : {}", id);
        teachApproachService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
