package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.TeachTypeService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.TeachTypeDTO;
import com.marineindustryproj.service.dto.TeachTypeCriteria;
import com.marineindustryproj.service.TeachTypeQueryService;
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
 * REST controller for managing TeachType.
 */
@RestController
@RequestMapping("/api")
public class TeachTypeResource {

    private final Logger log = LoggerFactory.getLogger(TeachTypeResource.class);

    private static final String ENTITY_NAME = "teachType";

    private final TeachTypeService teachTypeService;

    private final TeachTypeQueryService teachTypeQueryService;

    public TeachTypeResource(TeachTypeService teachTypeService, TeachTypeQueryService teachTypeQueryService) {
        this.teachTypeService = teachTypeService;
        this.teachTypeQueryService = teachTypeQueryService;
    }

    /**
     * POST  /teach-types : Create a new teachType.
     *
     * @param teachTypeDTO the teachTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new teachTypeDTO, or with status 400 (Bad Request) if the teachType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/teach-types")
    @Timed
    public ResponseEntity<TeachTypeDTO> createTeachType(@Valid @RequestBody TeachTypeDTO teachTypeDTO) throws URISyntaxException {
        log.debug("REST request to save TeachType : {}", teachTypeDTO);
        if (teachTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new teachType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        teachTypeDTO.setCreateDate(ZonedDateTime.now());
        teachTypeDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        TeachTypeDTO result = teachTypeService.save(teachTypeDTO);
        return ResponseEntity.created(new URI("/api/teach-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /teach-types : Updates an existing teachType.
     *
     * @param teachTypeDTO the teachTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated teachTypeDTO,
     * or with status 400 (Bad Request) if the teachTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the teachTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/teach-types")
    @Timed
    public ResponseEntity<TeachTypeDTO> updateTeachType(@Valid @RequestBody TeachTypeDTO teachTypeDTO) throws URISyntaxException {
        log.debug("REST request to update TeachType : {}", teachTypeDTO);
        if (teachTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TeachTypeDTO teachType = teachTypeService.findOne(teachTypeDTO.getId()).get();

        teachTypeDTO.setCreateUserLogin(teachType.getCreateUserLogin());
        teachTypeDTO.setCreateDate(teachType.getCreateDate());
        teachTypeDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        teachTypeDTO.setModifyDate(ZonedDateTime.now());
        TeachTypeDTO result = teachTypeService.save(teachTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, teachTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /teach-types : get all the teachTypes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of teachTypes in body
     */
    @GetMapping("/teach-types")
    @Timed
    public ResponseEntity<List<TeachTypeDTO>> getAllTeachTypes(TeachTypeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get TeachTypes by criteria: {}", criteria);
        Page<TeachTypeDTO> page = teachTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/teach-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /teach-types/count : count all the teachTypes.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/teach-types/count")
    @Timed
    public ResponseEntity<Long> countTeachTypes (TeachTypeCriteria criteria) {
        log.debug("REST request to count TeachTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(teachTypeQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /teach-types/:id : get the "id" teachType.
     *
     * @param id the id of the teachTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the teachTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/teach-types/{id}")
    @Timed
    public ResponseEntity<TeachTypeDTO> getTeachType(@PathVariable Long id) {
        log.debug("REST request to get TeachType : {}", id);
        Optional<TeachTypeDTO> teachTypeDTO = teachTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(teachTypeDTO);
    }

    /**
     * DELETE  /teach-types/:id : delete the "id" teachType.
     *
     * @param id the id of the teachTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/teach-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteTeachType(@PathVariable Long id) {
        log.debug("REST request to delete TeachType : {}", id);
        teachTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
