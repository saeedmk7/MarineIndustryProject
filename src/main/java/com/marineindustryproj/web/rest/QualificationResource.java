package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.QualificationService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.QualificationDTO;
import com.marineindustryproj.service.dto.QualificationCriteria;
import com.marineindustryproj.service.QualificationQueryService;
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
 * REST controller for managing Qualification.
 */
@RestController
@RequestMapping("/api")
public class QualificationResource {

    private final Logger log = LoggerFactory.getLogger(QualificationResource.class);

    private static final String ENTITY_NAME = "qualification";

    private final QualificationService qualificationService;

    private final QualificationQueryService qualificationQueryService;

    public QualificationResource(QualificationService qualificationService, QualificationQueryService qualificationQueryService) {
        this.qualificationService = qualificationService;
        this.qualificationQueryService = qualificationQueryService;
    }

    /**
     * POST  /qualifications : Create a new qualification.
     *
     * @param qualificationDTO the qualificationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new qualificationDTO, or with status 400 (Bad Request) if the qualification has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/qualifications")
    @Timed
    public ResponseEntity<QualificationDTO> createQualification(@Valid @RequestBody QualificationDTO qualificationDTO) throws URISyntaxException {
        log.debug("REST request to save Qualification : {}", qualificationDTO);
        if (qualificationDTO.getId() != null) {
            throw new BadRequestAlertException("A new qualification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        qualificationDTO.setCreateDate(ZonedDateTime.now());
        qualificationDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        QualificationDTO result = qualificationService.save(qualificationDTO);
        return ResponseEntity.created(new URI("/api/qualifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /qualifications : Updates an existing qualification.
     *
     * @param qualificationDTO the qualificationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated qualificationDTO,
     * or with status 400 (Bad Request) if the qualificationDTO is not valid,
     * or with status 500 (Internal Server Error) if the qualificationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/qualifications")
    @Timed
    public ResponseEntity<QualificationDTO> updateQualification(@Valid @RequestBody QualificationDTO qualificationDTO) throws URISyntaxException {
        log.debug("REST request to update Qualification : {}", qualificationDTO);
        if (qualificationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QualificationDTO qualification = qualificationService.findOne(qualificationDTO.getId()).get();

        qualificationDTO.setCreateUserLogin(qualification.getCreateUserLogin());
        qualificationDTO.setCreateDate(qualification.getCreateDate());
        qualificationDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        qualificationDTO.setModifyDate(ZonedDateTime.now());
        QualificationDTO result = qualificationService.save(qualificationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, qualificationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /qualifications : get all the qualifications.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of qualifications in body
     */
    @GetMapping("/qualifications")
    @Timed
    public ResponseEntity<List<QualificationDTO>> getAllQualifications(QualificationCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Qualifications by criteria: {}", criteria);
        Page<QualificationDTO> page = qualificationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qualifications");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /qualifications/count : count all the qualifications.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/qualifications/count")
    @Timed
    public ResponseEntity<Long> countQualifications (QualificationCriteria criteria) {
        log.debug("REST request to count Qualifications by criteria: {}", criteria);
        return ResponseEntity.ok().body(qualificationQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /qualifications/:id : get the "id" qualification.
     *
     * @param id the id of the qualificationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the qualificationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/qualifications/{id}")
    @Timed
    public ResponseEntity<QualificationDTO> getQualification(@PathVariable Long id) {
        log.debug("REST request to get Qualification : {}", id);
        Optional<QualificationDTO> qualificationDTO = qualificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qualificationDTO);
    }

    /**
     * DELETE  /qualifications/:id : delete the "id" qualification.
     *
     * @param id the id of the qualificationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/qualifications/{id}")
    @Timed
    public ResponseEntity<Void> deleteQualification(@PathVariable Long id) {
        log.debug("REST request to delete Qualification : {}", id);
        qualificationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
