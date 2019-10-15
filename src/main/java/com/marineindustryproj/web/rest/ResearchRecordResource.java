package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.ResearchRecordService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.ResearchRecordDTO;
import com.marineindustryproj.service.dto.ResearchRecordCriteria;
import com.marineindustryproj.service.ResearchRecordQueryService;
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
 * REST controller for managing ResearchRecord.
 */
@RestController
@RequestMapping("/api")
public class ResearchRecordResource {

    private final Logger log = LoggerFactory.getLogger(ResearchRecordResource.class);

    private static final String ENTITY_NAME = "researchRecord";

    private final ResearchRecordService researchRecordService;

    private final ResearchRecordQueryService researchRecordQueryService;

    public ResearchRecordResource(ResearchRecordService researchRecordService, ResearchRecordQueryService researchRecordQueryService) {
        this.researchRecordService = researchRecordService;
        this.researchRecordQueryService = researchRecordQueryService;
    }

    /**
     * POST  /research-records : Create a new researchRecord.
     *
     * @param researchRecordDTO the researchRecordDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new researchRecordDTO, or with status 400 (Bad Request) if the researchRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/research-records")
    @Timed
    public ResponseEntity<ResearchRecordDTO> createResearchRecord(@Valid @RequestBody ResearchRecordDTO researchRecordDTO) throws URISyntaxException {
        log.debug("REST request to save ResearchRecord : {}", researchRecordDTO);
        if (researchRecordDTO.getId() != null) {
            throw new BadRequestAlertException("A new researchRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        researchRecordDTO.setGuid(UUID.randomUUID().toString());
        researchRecordDTO.setCreateDate(ZonedDateTime.now());
        researchRecordDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        ResearchRecordDTO result = researchRecordService.save(researchRecordDTO);
        return ResponseEntity.created(new URI("/api/research-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /research-records : Updates an existing researchRecord.
     *
     * @param researchRecordDTO the researchRecordDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated researchRecordDTO,
     * or with status 400 (Bad Request) if the researchRecordDTO is not valid,
     * or with status 500 (Internal Server Error) if the researchRecordDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/research-records")
    @Timed
    public ResponseEntity<ResearchRecordDTO> updateResearchRecord(@Valid @RequestBody ResearchRecordDTO researchRecordDTO) throws URISyntaxException {
        log.debug("REST request to update ResearchRecord : {}", researchRecordDTO);
        if (researchRecordDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ResearchRecordDTO researchRecord = researchRecordService.findOne(researchRecordDTO.getId()).get();

        researchRecordDTO.setGuid(researchRecord.getGuid());
        researchRecordDTO.setCreateUserLogin(researchRecord.getCreateUserLogin());
        researchRecordDTO.setCreateDate(researchRecord.getCreateDate());
        researchRecordDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        researchRecordDTO.setModifyDate(ZonedDateTime.now());
        ResearchRecordDTO result = researchRecordService.save(researchRecordDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, researchRecordDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /research-records : get all the researchRecords.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of researchRecords in body
     */
    @GetMapping("/research-records")
    @Timed
    public ResponseEntity<List<ResearchRecordDTO>> getAllResearchRecords(ResearchRecordCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ResearchRecords by criteria: {}", criteria);
        Page<ResearchRecordDTO> page = researchRecordQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/research-records");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /research-records/count : count all the researchRecords.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/research-records/count")
    @Timed
    public ResponseEntity<Long> countResearchRecords(ResearchRecordCriteria criteria) {
        log.debug("REST request to count ResearchRecords by criteria: {}", criteria);
        return ResponseEntity.ok().body(researchRecordQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /research-records/:id : get the "id" researchRecord.
     *
     * @param id the id of the researchRecordDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the researchRecordDTO, or with status 404 (Not Found)
     */
    @GetMapping("/research-records/{id}")
    @Timed
    public ResponseEntity<ResearchRecordDTO> getResearchRecord(@PathVariable Long id) {
        log.debug("REST request to get ResearchRecord : {}", id);
        Optional<ResearchRecordDTO> researchRecordDTO = researchRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(researchRecordDTO);
    }

    /**
     * DELETE  /research-records/:id : delete the "id" researchRecord.
     *
     * @param id the id of the researchRecordDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/research-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteResearchRecord(@PathVariable Long id) {
        log.debug("REST request to delete ResearchRecord : {}", id);
        researchRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
