package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.TeachingRecordService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.TeachingRecordDTO;
import com.marineindustryproj.service.dto.TeachingRecordCriteria;
import com.marineindustryproj.service.TeachingRecordQueryService;
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
import java.util.UUID;

/**
 * REST controller for managing TeachingRecord.
 */
@RestController
@RequestMapping("/api")
public class TeachingRecordResource {

    private final Logger log = LoggerFactory.getLogger(TeachingRecordResource.class);

    private static final String ENTITY_NAME = "teachingRecord";

    private final TeachingRecordService teachingRecordService;

    private final TeachingRecordQueryService teachingRecordQueryService;

    public TeachingRecordResource(TeachingRecordService teachingRecordService, TeachingRecordQueryService teachingRecordQueryService) {
        this.teachingRecordService = teachingRecordService;
        this.teachingRecordQueryService = teachingRecordQueryService;
    }

    /**
     * POST  /teaching-records : Create a new teachingRecord.
     *
     * @param teachingRecordDTO the teachingRecordDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new teachingRecordDTO, or with status 400 (Bad Request) if the teachingRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/teaching-records")
    @Timed
    public ResponseEntity<TeachingRecordDTO> createTeachingRecord(@Valid @RequestBody TeachingRecordDTO teachingRecordDTO) throws URISyntaxException {
        log.debug("REST request to save TeachingRecord : {}", teachingRecordDTO);
        if (teachingRecordDTO.getId() != null) {
            throw new BadRequestAlertException("A new teachingRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        teachingRecordDTO.setGuid(UUID.randomUUID().toString());
        teachingRecordDTO.setCreateDate(ZonedDateTime.now());
        teachingRecordDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        TeachingRecordDTO result = teachingRecordService.save(teachingRecordDTO);
        return ResponseEntity.created(new URI("/api/teaching-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /teaching-records : Updates an existing teachingRecord.
     *
     * @param teachingRecordDTO the teachingRecordDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated teachingRecordDTO,
     * or with status 400 (Bad Request) if the teachingRecordDTO is not valid,
     * or with status 500 (Internal Server Error) if the teachingRecordDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/teaching-records")
    @Timed
    public ResponseEntity<TeachingRecordDTO> updateTeachingRecord(@Valid @RequestBody TeachingRecordDTO teachingRecordDTO) throws URISyntaxException {
        log.debug("REST request to update TeachingRecord : {}", teachingRecordDTO);
        if (teachingRecordDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TeachingRecordDTO teachingRecord = teachingRecordService.findOne(teachingRecordDTO.getId()).get();

        teachingRecordDTO.setGuid(teachingRecord.getGuid());
        teachingRecordDTO.setCreateUserLogin(teachingRecord.getCreateUserLogin());
        teachingRecordDTO.setCreateDate(teachingRecord.getCreateDate());
        teachingRecordDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        teachingRecordDTO.setModifyDate(ZonedDateTime.now());
        TeachingRecordDTO result = teachingRecordService.save(teachingRecordDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, teachingRecordDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /teaching-records : get all the teachingRecords.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of teachingRecords in body
     */
    @GetMapping("/teaching-records")
    @Timed
    public ResponseEntity<List<TeachingRecordDTO>> getAllTeachingRecords(TeachingRecordCriteria criteria, Pageable pageable) {
        log.debug("REST request to get TeachingRecords by criteria: {}", criteria);
        Page<TeachingRecordDTO> page = teachingRecordQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/teaching-records");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /teaching-records/count : count all the teachingRecords.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/teaching-records/count")
    @Timed
    public ResponseEntity<Long> countTeachingRecords(TeachingRecordCriteria criteria) {
        log.debug("REST request to count TeachingRecords by criteria: {}", criteria);
        return ResponseEntity.ok().body(teachingRecordQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /teaching-records/:id : get the "id" teachingRecord.
     *
     * @param id the id of the teachingRecordDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the teachingRecordDTO, or with status 404 (Not Found)
     */
    @GetMapping("/teaching-records/{id}")
    @Timed
    public ResponseEntity<TeachingRecordDTO> getTeachingRecord(@PathVariable Long id) {
        log.debug("REST request to get TeachingRecord : {}", id);
        Optional<TeachingRecordDTO> teachingRecordDTO = teachingRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(teachingRecordDTO);
    }

    /**
     * DELETE  /teaching-records/:id : delete the "id" teachingRecord.
     *
     * @param id the id of the teachingRecordDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/teaching-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteTeachingRecord(@PathVariable Long id) {
        log.debug("REST request to delete TeachingRecord : {}", id);
        teachingRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
