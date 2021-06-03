package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.CapitationService;
import com.marineindustryproj.service.dto.customs.CapitationReportModels.CapitationReport;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.CapitationDTO;
import com.marineindustryproj.service.dto.CapitationCriteria;
import com.marineindustryproj.service.CapitationQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Capitation.
 */
@RestController
@RequestMapping("/api")
public class CapitationResource {

    private final Logger log = LoggerFactory.getLogger(CapitationResource.class);

    private static final String ENTITY_NAME = "capitation";

    private final CapitationService capitationService;

    private final CapitationQueryService capitationQueryService;

    public CapitationResource(CapitationService capitationService, CapitationQueryService capitationQueryService) {
        this.capitationService = capitationService;
        this.capitationQueryService = capitationQueryService;
    }

    /**
     * POST  /capitations : Create a new capitation.
     *
     * @param capitationDTO the capitationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new capitationDTO, or with status 400 (Bad Request) if the capitation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/capitations")
    @Timed
    public ResponseEntity<CapitationDTO> createCapitation(@Valid @RequestBody CapitationDTO capitationDTO) throws URISyntaxException {
        log.debug("REST request to save Capitation : {}", capitationDTO);
        if (capitationDTO.getId() != null) {
            throw new BadRequestAlertException("A new capitation cannot already have an ID", ENTITY_NAME, "idexists");
        }

        capitationDTO.setCreateDate(ZonedDateTime.now());
        capitationDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        CapitationDTO result = capitationService.save(capitationDTO);
        return ResponseEntity.created(new URI("/api/capitations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @GetMapping("/capitations/getCapitationReport/{niazsanjiYear}/{organizationChartId}")
    @Timed
    public ResponseEntity<CapitationReport> getCapitationReport(@PathVariable Integer niazsanjiYear, @PathVariable Long organizationChartId) throws Exception {
        log.debug("REST request to get CapitationReport");
        CapitationReport capitationReport = capitationService.getCapitationReport(niazsanjiYear, organizationChartId);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(capitationReport);
    }

    /**
     * PUT  /capitations : Updates an existing capitation.
     *
     * @param capitationDTO the capitationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated capitationDTO,
     * or with status 400 (Bad Request) if the capitationDTO is not valid,
     * or with status 500 (Internal Server Error) if the capitationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/capitations")
    @Timed
    public ResponseEntity<CapitationDTO> updateCapitation(@Valid @RequestBody CapitationDTO capitationDTO) throws URISyntaxException {
        log.debug("REST request to update Capitation : {}", capitationDTO);
        if (capitationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CapitationDTO capitation = capitationService.findOne(capitationDTO.getId()).get();

        capitationDTO.setCreateUserLogin(capitation.getCreateUserLogin());
        capitationDTO.setCreateDate(capitation.getCreateDate());
        capitationDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        capitationDTO.setModifyDate(ZonedDateTime.now());
        CapitationDTO result = capitationService.save(capitationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, capitationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /capitations : get all the capitations.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of capitations in body
     */
    @GetMapping("/capitations")
    @Timed
    public ResponseEntity<List<CapitationDTO>> getAllCapitations(CapitationCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Capitations by criteria: {}", criteria);
        Page<CapitationDTO> page = capitationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/capitations");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /capitations/count : count all the capitations.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/capitations/count")
    @Timed
    public ResponseEntity<Long> countCapitations(CapitationCriteria criteria) {
        log.debug("REST request to count Capitations by criteria: {}", criteria);
        return ResponseEntity.ok().body(capitationQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /capitations/:id : get the "id" capitation.
     *
     * @param id the id of the capitationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the capitationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/capitations/{id}")
    @Timed
    public ResponseEntity<CapitationDTO> getCapitation(@PathVariable Long id) {
        log.debug("REST request to get Capitation : {}", id);
        Optional<CapitationDTO> capitationDTO = capitationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(capitationDTO);
    }

    /**
     * DELETE  /capitations/:id : delete the "id" capitation.
     *
     * @param id the id of the capitationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/capitations/{id}")
    @Timed
    public ResponseEntity<Void> deleteCapitation(@PathVariable Long id) {
        log.debug("REST request to delete Capitation : {}", id);
        capitationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
