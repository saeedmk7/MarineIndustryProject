package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.domain.enumeration.RequestStatus;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.FinalOrganizationNiazsanjiService;
import com.marineindustryproj.service.RequestOrganizationNiazsanjiService;
import com.marineindustryproj.service.dto.RequestOrganizationNiazsanjiDTO;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.FinalOrganizationNiazsanjiDTO;
import com.marineindustryproj.service.dto.FinalOrganizationNiazsanjiCriteria;
import com.marineindustryproj.service.FinalOrganizationNiazsanjiQueryService;
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
 * REST controller for managing FinalOrganizationNiazsanji.
 */
@RestController
@RequestMapping("/api")
public class FinalOrganizationNiazsanjiResource {

    private final Logger log = LoggerFactory.getLogger(FinalOrganizationNiazsanjiResource.class);

    private static final String ENTITY_NAME = "finalOrganizationNiazsanji";

    private final FinalOrganizationNiazsanjiService finalOrganizationNiazsanjiService;

    private final RequestOrganizationNiazsanjiService requestOrganizationNiazsanjiService;

    private final FinalOrganizationNiazsanjiQueryService finalOrganizationNiazsanjiQueryService;

    public FinalOrganizationNiazsanjiResource(FinalOrganizationNiazsanjiService finalOrganizationNiazsanjiService, RequestOrganizationNiazsanjiService requestOrganizationNiazsanjiService, FinalOrganizationNiazsanjiQueryService finalOrganizationNiazsanjiQueryService) {
        this.finalOrganizationNiazsanjiService = finalOrganizationNiazsanjiService;
        this.requestOrganizationNiazsanjiService = requestOrganizationNiazsanjiService;
        this.finalOrganizationNiazsanjiQueryService = finalOrganizationNiazsanjiQueryService;
    }

    /**
     * POST  /final-organization-niazsanjis : Create a new finalOrganizationNiazsanji.
     *
     * @param finalOrganizationNiazsanjiDTO the finalOrganizationNiazsanjiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new finalOrganizationNiazsanjiDTO, or with status 400 (Bad Request) if the finalOrganizationNiazsanji has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/final-organization-niazsanjis")
    @Timed
    public ResponseEntity<FinalOrganizationNiazsanjiDTO> createFinalOrganizationNiazsanji(@Valid @RequestBody FinalOrganizationNiazsanjiDTO finalOrganizationNiazsanjiDTO) throws URISyntaxException {
        log.debug("REST request to save FinalOrganizationNiazsanji : {}", finalOrganizationNiazsanjiDTO);
        if (finalOrganizationNiazsanjiDTO.getId() != null) {
            throw new BadRequestAlertException("A new finalOrganizationNiazsanji cannot already have an ID", ENTITY_NAME, "idexists");
        }

        finalOrganizationNiazsanjiDTO.setCreateDate(ZonedDateTime.now());
        finalOrganizationNiazsanjiDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalOrganizationNiazsanjiDTO.setModifyDate(ZonedDateTime.now());
        finalOrganizationNiazsanjiDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());

        FinalOrganizationNiazsanjiDTO result = finalOrganizationNiazsanjiService.save(finalOrganizationNiazsanjiDTO);
        return ResponseEntity.created(new URI("/api/final-organization-niazsanjis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    /**
     * POST  /final-organization-niazsanjis : Create a new finalOrganizationNiazsanji.
     *
     * @param finalOrganizationNiazsanjiDTO the finalOrganizationNiazsanjiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new finalOrganizationNiazsanjiDTO, or with status 400 (Bad Request) if the finalOrganizationNiazsanji has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/finalize-organization-niazsanjis")
    @Timed
    public ResponseEntity<FinalOrganizationNiazsanjiDTO> finalizeOrganizationNiazsanji(@Valid @RequestBody FinalOrganizationNiazsanjiDTO finalOrganizationNiazsanjiDTO) throws URISyntaxException {
        log.debug("REST request to save FinalOrganizationNiazsanji : {}", finalOrganizationNiazsanjiDTO);
        if (finalOrganizationNiazsanjiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FinalOrganizationNiazsanjiDTO finalOrganizationNiazsanji = finalOrganizationNiazsanjiService.findOne(finalOrganizationNiazsanjiDTO.getId()).get();

        finalOrganizationNiazsanjiDTO.setCreateUserLogin(finalOrganizationNiazsanji.getCreateUserLogin());
        finalOrganizationNiazsanjiDTO.setCreateDate(finalOrganizationNiazsanji.getCreateDate());
        finalOrganizationNiazsanjiDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalOrganizationNiazsanjiDTO.setModifyDate(ZonedDateTime.now());
        finalOrganizationNiazsanjiDTO.setStatus(30);
        finalOrganizationNiazsanjiDTO.setChangeStatusUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalOrganizationNiazsanjiDTO.setRequestStatus(RequestStatus.ACCEPT);

        FinalOrganizationNiazsanjiDTO result = finalOrganizationNiazsanjiService.finalize(finalOrganizationNiazsanjiDTO);
        return ResponseEntity.created(new URI("/api/final-organization-niazsanjis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    /**
     * PUT  /final-organization-niazsanjis : Updates an existing finalOrganizationNiazsanji.
     *
     * @param finalOrganizationNiazsanjiDTO the finalOrganizationNiazsanjiDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated finalOrganizationNiazsanjiDTO,
     * or with status 400 (Bad Request) if the finalOrganizationNiazsanjiDTO is not valid,
     * or with status 500 (Internal Server Error) if the finalOrganizationNiazsanjiDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/final-organization-niazsanjis")
    @Timed
    public ResponseEntity<FinalOrganizationNiazsanjiDTO> updateFinalOrganizationNiazsanji(@Valid @RequestBody FinalOrganizationNiazsanjiDTO finalOrganizationNiazsanjiDTO) throws URISyntaxException {
        log.debug("REST request to update FinalOrganizationNiazsanji : {}", finalOrganizationNiazsanjiDTO);
        if (finalOrganizationNiazsanjiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        FinalOrganizationNiazsanjiDTO finalOrganizationNiazsanji = finalOrganizationNiazsanjiService.findOne(finalOrganizationNiazsanjiDTO.getId()).get();

        finalOrganizationNiazsanjiDTO.setCreateUserLogin(finalOrganizationNiazsanji.getCreateUserLogin());
        finalOrganizationNiazsanjiDTO.setCreateDate(finalOrganizationNiazsanji.getCreateDate());
        finalOrganizationNiazsanjiDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalOrganizationNiazsanjiDTO.setModifyDate(ZonedDateTime.now());

        FinalOrganizationNiazsanjiDTO result = finalOrganizationNiazsanjiService.save(finalOrganizationNiazsanjiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, finalOrganizationNiazsanjiDTO.getId().toString()))
            .body(result);
    }
    @PutMapping("/final-organization-niazsanjis/toggleImportantMessage/{id}/{type}")
    @Timed
    public ResponseEntity<FinalOrganizationNiazsanjiDTO> toggleImportantMessage(@PathVariable long id, @PathVariable boolean type) throws URISyntaxException {

        FinalOrganizationNiazsanjiDTO finalOrganizationNiazsanji = finalOrganizationNiazsanjiService.findOne(id).get();

        finalOrganizationNiazsanji.setModifyDate(ZonedDateTime.now());
        finalOrganizationNiazsanji.setHasImportantMessage(type);

        FinalOrganizationNiazsanjiDTO result = finalOrganizationNiazsanjiService.save(finalOrganizationNiazsanji);

        RequestOrganizationNiazsanjiDTO requestOrganizationNiazsanji = requestOrganizationNiazsanjiService.findOne(result.getRequestOrganizationNiazsanjiId()).get();
        requestOrganizationNiazsanji.setHasImportantMessage(type);
        requestOrganizationNiazsanji.setModifyDate(ZonedDateTime.now());
        requestOrganizationNiazsanjiService.save(requestOrganizationNiazsanji);

        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, finalOrganizationNiazsanji.getId().toString()))
            .body(result);
    }

    /**
     * GET  /final-organization-niazsanjis : get all the finalOrganizationNiazsanjis.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of finalOrganizationNiazsanjis in body
     */
    @GetMapping("/final-organization-niazsanjis")
    @Timed
    public ResponseEntity<List<FinalOrganizationNiazsanjiDTO>> getAllFinalOrganizationNiazsanjis(FinalOrganizationNiazsanjiCriteria criteria, Pageable pageable) {
        log.debug("REST request to get FinalOrganizationNiazsanjis by criteria: {}", criteria);
        Page<FinalOrganizationNiazsanjiDTO> page = finalOrganizationNiazsanjiQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/final-organization-niazsanjis");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /final-organization-niazsanjis/count : count all the finalOrganizationNiazsanjis.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/final-organization-niazsanjis/count")
    @Timed
    public ResponseEntity<Long> countFinalOrganizationNiazsanjis (FinalOrganizationNiazsanjiCriteria criteria) {
        log.debug("REST request to count FinalOrganizationNiazsanjis by criteria: {}", criteria);
        return ResponseEntity.ok().body(finalOrganizationNiazsanjiQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /final-organization-niazsanjis/:id : get the "id" finalOrganizationNiazsanji.
     *
     * @param id the id of the finalOrganizationNiazsanjiDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the finalOrganizationNiazsanjiDTO, or with status 404 (Not Found)
     */
    @GetMapping("/final-organization-niazsanjis/{id}")
    @Timed
    public ResponseEntity<FinalOrganizationNiazsanjiDTO> getFinalOrganizationNiazsanji(@PathVariable Long id) {
        log.debug("REST request to get FinalOrganizationNiazsanji : {}", id);
        Optional<FinalOrganizationNiazsanjiDTO> finalOrganizationNiazsanjiDTO = finalOrganizationNiazsanjiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(finalOrganizationNiazsanjiDTO);
    }

    /**
     * DELETE  /final-organization-niazsanjis/:id : delete the "id" finalOrganizationNiazsanji.
     *
     * @param id the id of the finalOrganizationNiazsanjiDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/final-organization-niazsanjis/{id}")
    @Timed
    public ResponseEntity<Void> deleteFinalOrganizationNiazsanji(@PathVariable Long id) {
        log.debug("REST request to delete FinalOrganizationNiazsanji : {}", id);
        finalOrganizationNiazsanjiService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
