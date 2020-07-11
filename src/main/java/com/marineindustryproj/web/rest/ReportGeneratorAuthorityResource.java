package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.ReportGeneratorAuthorityService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.ReportGeneratorAuthorityDTO;
import com.marineindustryproj.service.dto.ReportGeneratorAuthorityCriteria;
import com.marineindustryproj.service.ReportGeneratorAuthorityQueryService;
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
 * REST controller for managing ReportGeneratorAuthority.
 */
@RestController
@RequestMapping("/api")
public class ReportGeneratorAuthorityResource {

    private final Logger log = LoggerFactory.getLogger(ReportGeneratorAuthorityResource.class);

    private static final String ENTITY_NAME = "reportGeneratorAuthority";

    private final ReportGeneratorAuthorityService reportGeneratorAuthorityService;

    private final ReportGeneratorAuthorityQueryService reportGeneratorAuthorityQueryService;

    public ReportGeneratorAuthorityResource(ReportGeneratorAuthorityService reportGeneratorAuthorityService, ReportGeneratorAuthorityQueryService reportGeneratorAuthorityQueryService) {
        this.reportGeneratorAuthorityService = reportGeneratorAuthorityService;
        this.reportGeneratorAuthorityQueryService = reportGeneratorAuthorityQueryService;
    }

    /**
     * POST  /report-generator-authorities : Create a new reportGeneratorAuthority.
     *
     * @param reportGeneratorAuthorityDTO the reportGeneratorAuthorityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new reportGeneratorAuthorityDTO, or with status 400 (Bad Request) if the reportGeneratorAuthority has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/report-generator-authorities")
    @Timed
    public ResponseEntity<ReportGeneratorAuthorityDTO> createReportGeneratorAuthority(@Valid @RequestBody ReportGeneratorAuthorityDTO reportGeneratorAuthorityDTO) throws URISyntaxException {
        log.debug("REST request to save ReportGeneratorAuthority : {}", reportGeneratorAuthorityDTO);
        if (reportGeneratorAuthorityDTO.getId() != null) {
            throw new BadRequestAlertException("A new reportGeneratorAuthority cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReportGeneratorAuthorityDTO result = reportGeneratorAuthorityService.save(reportGeneratorAuthorityDTO);
        return ResponseEntity.created(new URI("/api/report-generator-authorities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /report-generator-authorities : Updates an existing reportGeneratorAuthority.
     *
     * @param reportGeneratorAuthorityDTO the reportGeneratorAuthorityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated reportGeneratorAuthorityDTO,
     * or with status 400 (Bad Request) if the reportGeneratorAuthorityDTO is not valid,
     * or with status 500 (Internal Server Error) if the reportGeneratorAuthorityDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/report-generator-authorities")
    @Timed
    public ResponseEntity<ReportGeneratorAuthorityDTO> updateReportGeneratorAuthority(@Valid @RequestBody ReportGeneratorAuthorityDTO reportGeneratorAuthorityDTO) throws URISyntaxException {
        log.debug("REST request to update ReportGeneratorAuthority : {}", reportGeneratorAuthorityDTO);
        if (reportGeneratorAuthorityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReportGeneratorAuthorityDTO result = reportGeneratorAuthorityService.save(reportGeneratorAuthorityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, reportGeneratorAuthorityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /report-generator-authorities : get all the reportGeneratorAuthorities.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of reportGeneratorAuthorities in body
     */
    @GetMapping("/report-generator-authorities")
    @Timed
    public ResponseEntity<List<ReportGeneratorAuthorityDTO>> getAllReportGeneratorAuthorities(ReportGeneratorAuthorityCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ReportGeneratorAuthorities by criteria: {}", criteria);
        Page<ReportGeneratorAuthorityDTO> page = reportGeneratorAuthorityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/report-generator-authorities");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /report-generator-authorities/count : count all the reportGeneratorAuthorities.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/report-generator-authorities/count")
    @Timed
    public ResponseEntity<Long> countReportGeneratorAuthorities(ReportGeneratorAuthorityCriteria criteria) {
        log.debug("REST request to count ReportGeneratorAuthorities by criteria: {}", criteria);
        return ResponseEntity.ok().body(reportGeneratorAuthorityQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /report-generator-authorities/:id : get the "id" reportGeneratorAuthority.
     *
     * @param id the id of the reportGeneratorAuthorityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the reportGeneratorAuthorityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/report-generator-authorities/{id}")
    @Timed
    public ResponseEntity<ReportGeneratorAuthorityDTO> getReportGeneratorAuthority(@PathVariable Long id) {
        log.debug("REST request to get ReportGeneratorAuthority : {}", id);
        Optional<ReportGeneratorAuthorityDTO> reportGeneratorAuthorityDTO = reportGeneratorAuthorityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reportGeneratorAuthorityDTO);
    }

    /**
     * DELETE  /report-generator-authorities/:id : delete the "id" reportGeneratorAuthority.
     *
     * @param id the id of the reportGeneratorAuthorityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/report-generator-authorities/{id}")
    @Timed
    public ResponseEntity<Void> deleteReportGeneratorAuthority(@PathVariable Long id) {
        log.debug("REST request to delete ReportGeneratorAuthority : {}", id);
        reportGeneratorAuthorityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
