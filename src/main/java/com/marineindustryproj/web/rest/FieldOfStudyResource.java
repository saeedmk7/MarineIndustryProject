package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.FieldOfStudyService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.FieldOfStudyDTO;
import com.marineindustryproj.service.dto.FieldOfStudyCriteria;
import com.marineindustryproj.service.FieldOfStudyQueryService;
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
 * REST controller for managing FieldOfStudy.
 */
@RestController
@RequestMapping("/api")
public class FieldOfStudyResource {

    private final Logger log = LoggerFactory.getLogger(FieldOfStudyResource.class);

    private static final String ENTITY_NAME = "fieldOfStudy";

    private final FieldOfStudyService fieldOfStudyService;

    private final FieldOfStudyQueryService fieldOfStudyQueryService;

    public FieldOfStudyResource(FieldOfStudyService fieldOfStudyService, FieldOfStudyQueryService fieldOfStudyQueryService) {
        this.fieldOfStudyService = fieldOfStudyService;
        this.fieldOfStudyQueryService = fieldOfStudyQueryService;
    }

    /**
     * POST  /field-of-studies : Create a new fieldOfStudy.
     *
     * @param fieldOfStudyDTO the fieldOfStudyDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fieldOfStudyDTO, or with status 400 (Bad Request) if the fieldOfStudy has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/field-of-studies")
    @Timed
    public ResponseEntity<FieldOfStudyDTO> createFieldOfStudy(@Valid @RequestBody FieldOfStudyDTO fieldOfStudyDTO) throws URISyntaxException {
        log.debug("REST request to save FieldOfStudy : {}", fieldOfStudyDTO);
        if (fieldOfStudyDTO.getId() != null) {
            throw new BadRequestAlertException("A new fieldOfStudy cannot already have an ID", ENTITY_NAME, "idexists");
        }

        fieldOfStudyDTO.setCreateDate(ZonedDateTime.now());
        fieldOfStudyDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        FieldOfStudyDTO result = fieldOfStudyService.save(fieldOfStudyDTO);
        return ResponseEntity.created(new URI("/api/field-of-studies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /field-of-studies : Updates an existing fieldOfStudy.
     *
     * @param fieldOfStudyDTO the fieldOfStudyDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fieldOfStudyDTO,
     * or with status 400 (Bad Request) if the fieldOfStudyDTO is not valid,
     * or with status 500 (Internal Server Error) if the fieldOfStudyDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/field-of-studies")
    @Timed
    public ResponseEntity<FieldOfStudyDTO> updateFieldOfStudy(@Valid @RequestBody FieldOfStudyDTO fieldOfStudyDTO) throws URISyntaxException {
        log.debug("REST request to update FieldOfStudy : {}", fieldOfStudyDTO);
        if (fieldOfStudyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
         FieldOfStudyDTO fieldOfStudy = fieldOfStudyService.findOne(fieldOfStudyDTO.getId()).get();

        fieldOfStudyDTO.setCreateUserLogin(fieldOfStudy.getCreateUserLogin());
        fieldOfStudyDTO.setCreateDate(fieldOfStudy.getCreateDate());
        fieldOfStudyDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        fieldOfStudyDTO.setModifyDate(ZonedDateTime.now());

        FieldOfStudyDTO result = fieldOfStudyService.save(fieldOfStudyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fieldOfStudyDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /field-of-studies : get all the fieldOfStudies.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of fieldOfStudies in body
     */
    @GetMapping("/field-of-studies")
    @Timed
    public ResponseEntity<List<FieldOfStudyDTO>> getAllFieldOfStudies(FieldOfStudyCriteria criteria, Pageable pageable) {
        log.debug("REST request to get FieldOfStudies by criteria: {}", criteria);
        Page<FieldOfStudyDTO> page = fieldOfStudyQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/field-of-studies");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /field-of-studies/count : count all the fieldOfStudies.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/field-of-studies/count")
    @Timed
    public ResponseEntity<Long> countFieldOfStudies (FieldOfStudyCriteria criteria) {
        log.debug("REST request to count FieldOfStudies by criteria: {}", criteria);
        return ResponseEntity.ok().body(fieldOfStudyQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /field-of-studies/:id : get the "id" fieldOfStudy.
     *
     * @param id the id of the fieldOfStudyDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fieldOfStudyDTO, or with status 404 (Not Found)
     */
    @GetMapping("/field-of-studies/{id}")
    @Timed
    public ResponseEntity<FieldOfStudyDTO> getFieldOfStudy(@PathVariable Long id) {
        log.debug("REST request to get FieldOfStudy : {}", id);
        Optional<FieldOfStudyDTO> fieldOfStudyDTO = fieldOfStudyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fieldOfStudyDTO);
    }

    /**
     * DELETE  /field-of-studies/:id : delete the "id" fieldOfStudy.
     *
     * @param id the id of the fieldOfStudyDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/field-of-studies/{id}")
    @Timed
    public ResponseEntity<Void> deleteFieldOfStudy(@PathVariable Long id) {
        log.debug("REST request to delete FieldOfStudy : {}", id);
        fieldOfStudyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
