package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.ReportGeneratorService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.ReportGeneratorDTO;
import com.marineindustryproj.service.dto.ReportGeneratorCriteria;
import com.marineindustryproj.service.ReportGeneratorQueryService;
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
 * REST controller for managing ReportGenerator.
 */
@RestController
@RequestMapping("/api")
public class ReportGeneratorResource {

    private final Logger log = LoggerFactory.getLogger(ReportGeneratorResource.class);

    private static final String ENTITY_NAME = "reportGenerator";

    private final ReportGeneratorService reportGeneratorService;

    private final ReportGeneratorQueryService reportGeneratorQueryService;

    public ReportGeneratorResource(ReportGeneratorService reportGeneratorService, ReportGeneratorQueryService reportGeneratorQueryService) {
        this.reportGeneratorService = reportGeneratorService;
        this.reportGeneratorQueryService = reportGeneratorQueryService;
    }

    /**
     * POST  /report-generators : Create a new reportGenerator.
     *
     * @param reportGeneratorDTO the reportGeneratorDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new reportGeneratorDTO, or with status 400 (Bad Request) if the reportGenerator has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/report-generators")
    @Timed
    public ResponseEntity<ReportGeneratorDTO> createReportGenerator(@Valid @RequestBody ReportGeneratorDTO reportGeneratorDTO) throws URISyntaxException {
        log.debug("REST request to save ReportGenerator : {}", reportGeneratorDTO);
        if (reportGeneratorDTO.getId() != null) {
            throw new BadRequestAlertException("A new reportGenerator cannot already have an ID", ENTITY_NAME, "idexists");
        }

        reportGeneratorDTO.setCreateDate(ZonedDateTime.now());
        reportGeneratorDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        reportGeneratorDTO.setModifyDate(ZonedDateTime.now());
        reportGeneratorDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        reportGeneratorDTO.setGuid(UUID.randomUUID().toString());

        ReportGeneratorDTO result = reportGeneratorService.save(reportGeneratorDTO);
        return ResponseEntity.created(new URI("/api/report-generators/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /report-generators : Updates an existing reportGenerator.
     *
     * @param reportGeneratorDTO the reportGeneratorDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated reportGeneratorDTO,
     * or with status 400 (Bad Request) if the reportGeneratorDTO is not valid,
     * or with status 500 (Internal Server Error) if the reportGeneratorDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/report-generators")
    @Timed
    public ResponseEntity<ReportGeneratorDTO> updateReportGenerator(@Valid @RequestBody ReportGeneratorDTO reportGeneratorDTO) throws URISyntaxException {
        log.debug("REST request to update ReportGenerator : {}", reportGeneratorDTO);
        if (reportGeneratorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        ReportGeneratorDTO reportGenerator = reportGeneratorService.findOne(reportGeneratorDTO.getId()).get();

        reportGeneratorDTO.setCreateUserLogin(reportGenerator.getCreateUserLogin());
        reportGeneratorDTO.setCreateDate(reportGenerator.getCreateDate());
        reportGeneratorDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        reportGeneratorDTO.setModifyDate(ZonedDateTime.now());
        reportGeneratorDTO.setGuid(reportGenerator.getGuid());

        ReportGeneratorDTO result = reportGeneratorService.save(reportGeneratorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, reportGeneratorDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /report-generators : get all the reportGenerators.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of reportGenerators in body
     */
    @GetMapping("/report-generators")
    @Timed
    public ResponseEntity<List<ReportGeneratorDTO>> getAllReportGenerators(ReportGeneratorCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ReportGenerators by criteria: {}", criteria);
        Page<ReportGeneratorDTO> page = reportGeneratorQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/report-generators");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /report-generators/count : count all the reportGenerators.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/report-generators/count")
    @Timed
    public ResponseEntity<Long> countReportGenerators(ReportGeneratorCriteria criteria) {
        log.debug("REST request to count ReportGenerators by criteria: {}", criteria);
        return ResponseEntity.ok().body(reportGeneratorQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /report-generators/:id : get the "id" reportGenerator.
     *
     * @param id the id of the reportGeneratorDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the reportGeneratorDTO, or with status 404 (Not Found)
     */
    @GetMapping("/report-generators/{id}")
    @Timed
    public ResponseEntity<ReportGeneratorDTO> getReportGenerator(@PathVariable Long id) {
        log.debug("REST request to get ReportGenerator : {}", id);
        Optional<ReportGeneratorDTO> reportGeneratorDTO = reportGeneratorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reportGeneratorDTO);
    }

    @GetMapping("/report-generators/byGuid/{id}")
    @Timed
    public ResponseEntity<ReportGeneratorDTO> getReportGeneratorByGuid(@PathVariable String id) {
        log.debug("REST request to get ReportGenerator : {}", id);
        Optional<ReportGeneratorDTO> reportGeneratorDTO = reportGeneratorService.findOneByGuid(id);
        return ResponseUtil.wrapOrNotFound(reportGeneratorDTO);
    }

    /**
     * DELETE  /report-generators/:id : delete the "id" reportGenerator.
     *
     * @param id the id of the reportGeneratorDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/report-generators/{id}")
    @Timed
    public ResponseEntity<Void> deleteReportGenerator(@PathVariable Long id) {
        log.debug("REST request to delete ReportGenerator : {}", id);
        reportGeneratorService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
