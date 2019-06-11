package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.EducationalRecordService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.EducationalRecordDTO;
import com.marineindustryproj.service.dto.EducationalRecordCriteria;
import com.marineindustryproj.service.EducationalRecordQueryService;
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
 * REST controller for managing EducationalRecord.
 */
@RestController
@RequestMapping("/api")
public class EducationalRecordResource {

    private final Logger log = LoggerFactory.getLogger(EducationalRecordResource.class);

    private static final String ENTITY_NAME = "educationalRecord";

    private final EducationalRecordService educationalRecordService;

    private final EducationalRecordQueryService educationalRecordQueryService;

    public EducationalRecordResource(EducationalRecordService educationalRecordService, EducationalRecordQueryService educationalRecordQueryService) {
        this.educationalRecordService = educationalRecordService;
        this.educationalRecordQueryService = educationalRecordQueryService;
    }

    /**
     * POST  /educational-records : Create a new educationalRecord.
     *
     * @param educationalRecordDTO the educationalRecordDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new educationalRecordDTO, or with status 400 (Bad Request) if the educationalRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/educational-records")
    @Timed
    public ResponseEntity<EducationalRecordDTO> createEducationalRecord(@Valid @RequestBody EducationalRecordDTO educationalRecordDTO) throws URISyntaxException {
        log.debug("REST request to save EducationalRecord : {}", educationalRecordDTO);
        if (educationalRecordDTO.getId() != null) {
            throw new BadRequestAlertException("A new educationalRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }

        educationalRecordDTO.setGuid(UUID.randomUUID().toString());
        educationalRecordDTO.setCreateDate(ZonedDateTime.now());
        educationalRecordDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        EducationalRecordDTO result = educationalRecordService.save(educationalRecordDTO);
        return ResponseEntity.created(new URI("/api/educational-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /educational-records : Updates an existing educationalRecord.
     *
     * @param educationalRecordDTO the educationalRecordDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated educationalRecordDTO,
     * or with status 400 (Bad Request) if the educationalRecordDTO is not valid,
     * or with status 500 (Internal Server Error) if the educationalRecordDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/educational-records")
    @Timed
    public ResponseEntity<EducationalRecordDTO> updateEducationalRecord(@Valid @RequestBody EducationalRecordDTO educationalRecordDTO) throws URISyntaxException {
        log.debug("REST request to update EducationalRecord : {}", educationalRecordDTO);
        if (educationalRecordDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        EducationalRecordDTO educationalRecord = educationalRecordService.findOne(educationalRecordDTO.getId()).get();

        educationalRecordDTO.setGuid(educationalRecord.getGuid());
        educationalRecordDTO.setCreateUserLogin(educationalRecord.getCreateUserLogin());
        educationalRecordDTO.setCreateDate(educationalRecord.getCreateDate());
        educationalRecordDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        educationalRecordDTO.setModifyDate(ZonedDateTime.now());

        EducationalRecordDTO result = educationalRecordService.save(educationalRecordDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, educationalRecordDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /educational-records : get all the educationalRecords.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of educationalRecords in body
     */
    @GetMapping("/educational-records")
    @Timed
    public ResponseEntity<List<EducationalRecordDTO>> getAllEducationalRecords(EducationalRecordCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EducationalRecords by criteria: {}", criteria);
        Page<EducationalRecordDTO> page = educationalRecordQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/educational-records");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /educational-records/count : count all the educationalRecords.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/educational-records/count")
    @Timed
    public ResponseEntity<Long> countEducationalRecords(EducationalRecordCriteria criteria) {
        log.debug("REST request to count EducationalRecords by criteria: {}", criteria);
        return ResponseEntity.ok().body(educationalRecordQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /educational-records/:id : get the "id" educationalRecord.
     *
     * @param id the id of the educationalRecordDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the educationalRecordDTO, or with status 404 (Not Found)
     */
    @GetMapping("/educational-records/{id}")
    @Timed
    public ResponseEntity<EducationalRecordDTO> getEducationalRecord(@PathVariable Long id) {
        log.debug("REST request to get EducationalRecord : {}", id);
        Optional<EducationalRecordDTO> educationalRecordDTO = educationalRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(educationalRecordDTO);
    }

    /**
     * DELETE  /educational-records/:id : delete the "id" educationalRecord.
     *
     * @param id the id of the educationalRecordDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/educational-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteEducationalRecord(@PathVariable Long id) {
        log.debug("REST request to delete EducationalRecord : {}", id);
        educationalRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
