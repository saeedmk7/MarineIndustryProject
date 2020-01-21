package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.domain.PrioritizeRequestNiazsanji;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.PrioritizeRequestNiazsanjiService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.PrioritizeRequestNiazsanjiDTO;
import com.marineindustryproj.service.dto.PrioritizeRequestNiazsanjiCriteria;
import com.marineindustryproj.service.PrioritizeRequestNiazsanjiQueryService;
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
 * REST controller for managing PrioritizeRequestNiazsanji.
 */
@RestController
@RequestMapping("/api")
public class PrioritizeRequestNiazsanjiResource {

    private final Logger log = LoggerFactory.getLogger(PrioritizeRequestNiazsanjiResource.class);

    private static final String ENTITY_NAME = "prioritizeRequestNiazsanji";

    private final PrioritizeRequestNiazsanjiService prioritizeRequestNiazsanjiService;

    private final PrioritizeRequestNiazsanjiQueryService prioritizeRequestNiazsanjiQueryService;

    public PrioritizeRequestNiazsanjiResource(PrioritizeRequestNiazsanjiService prioritizeRequestNiazsanjiService, PrioritizeRequestNiazsanjiQueryService prioritizeRequestNiazsanjiQueryService) {
        this.prioritizeRequestNiazsanjiService = prioritizeRequestNiazsanjiService;
        this.prioritizeRequestNiazsanjiQueryService = prioritizeRequestNiazsanjiQueryService;
    }

    /**
     * POST  /prioritize-request-niazsanjis : Create a new prioritizeRequestNiazsanji.
     *
     * @param prioritizeRequestNiazsanjiDTO the prioritizeRequestNiazsanjiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prioritizeRequestNiazsanjiDTO, or with status 400 (Bad Request) if the prioritizeRequestNiazsanji has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/prioritize-request-niazsanjis")
    @Timed
    public ResponseEntity<PrioritizeRequestNiazsanjiDTO> createPrioritizeRequestNiazsanji(@Valid @RequestBody PrioritizeRequestNiazsanjiDTO prioritizeRequestNiazsanjiDTO) throws URISyntaxException {
        log.debug("REST request to save PrioritizeRequestNiazsanji : {}", prioritizeRequestNiazsanjiDTO);
        if (prioritizeRequestNiazsanjiDTO.getId() != null) {
            throw new BadRequestAlertException("A new prioritizeRequestNiazsanji cannot already have an ID", ENTITY_NAME, "idexists");
        }
        prioritizeRequestNiazsanjiDTO.setCreateDate(ZonedDateTime.now());
        prioritizeRequestNiazsanjiDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        prioritizeRequestNiazsanjiDTO.setGuid(UUID.randomUUID().toString());
        PrioritizeRequestNiazsanjiDTO result = prioritizeRequestNiazsanjiService.save(prioritizeRequestNiazsanjiDTO);
        return ResponseEntity.created(new URI("/api/prioritize-request-niazsanjis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * POST  /request-other-niazsanjis : Create a new prioritizeRequestNiazsanji.
     *
     * @param prioritizeRequestNiazsanji the prioritizeRequestNiazsanji to create
     * @return the ResponseEntity with status 201 (Created) and with body the new prioritizeRequestNiazsanji, or with status 400 (Bad Request) if the prioritizeRequestNiazsanji has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/prioritize-request-niazsanjis/finalize")
    @Timed
    public ResponseEntity<PrioritizeRequestNiazsanjiDTO> finalizePrioritizeRequestNiazsanji(@Valid @RequestBody PrioritizeRequestNiazsanjiDTO prioritizeRequestNiazsanjiDTO) throws URISyntaxException {
        log.debug("REST request to finalize RequestOtherNiazsanji : {}", prioritizeRequestNiazsanjiDTO);
        if (prioritizeRequestNiazsanjiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        PrioritizeRequestNiazsanjiDTO prioritizeRequestNiazsanji = prioritizeRequestNiazsanjiService.findOne(prioritizeRequestNiazsanjiDTO.getId()).get();

        prioritizeRequestNiazsanjiDTO.setCreateUserLogin(prioritizeRequestNiazsanji.getCreateUserLogin());
        prioritizeRequestNiazsanjiDTO.setCreateDate(prioritizeRequestNiazsanji.getCreateDate());
        prioritizeRequestNiazsanjiDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        prioritizeRequestNiazsanjiDTO.setModifyDate(ZonedDateTime.now());
        prioritizeRequestNiazsanjiDTO.setChangeStatusUserLogin(SecurityUtils.getCurrentUserLogin().get());
        prioritizeRequestNiazsanjiDTO.setStatus(0);


        PrioritizeRequestNiazsanjiDTO result = prioritizeRequestNiazsanjiService.finalize(prioritizeRequestNiazsanjiDTO);
        return ResponseEntity.created(new URI("/api/prioritize-request-niazsanjis/finalize" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /prioritize-request-niazsanjis : Updates an existing prioritizeRequestNiazsanji.
     *
     * @param prioritizeRequestNiazsanjiDTO the prioritizeRequestNiazsanjiDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated prioritizeRequestNiazsanjiDTO,
     * or with status 400 (Bad Request) if the prioritizeRequestNiazsanjiDTO is not valid,
     * or with status 500 (Internal Server Error) if the prioritizeRequestNiazsanjiDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/prioritize-request-niazsanjis")
    @Timed
    public ResponseEntity<PrioritizeRequestNiazsanjiDTO> updatePrioritizeRequestNiazsanji(@Valid @RequestBody PrioritizeRequestNiazsanjiDTO prioritizeRequestNiazsanjiDTO) throws URISyntaxException {
        log.debug("REST request to update PrioritizeRequestNiazsanji : {}", prioritizeRequestNiazsanjiDTO);
        if (prioritizeRequestNiazsanjiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PrioritizeRequestNiazsanjiDTO prioritizeRequestNiazsanji = prioritizeRequestNiazsanjiService.findOne(prioritizeRequestNiazsanjiDTO.getId()).get();

        prioritizeRequestNiazsanjiDTO.setCreateUserLogin(prioritizeRequestNiazsanji.getCreateUserLogin());
        prioritizeRequestNiazsanjiDTO.setCreateDate(prioritizeRequestNiazsanji.getCreateDate());
        prioritizeRequestNiazsanjiDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        prioritizeRequestNiazsanjiDTO.setModifyDate(ZonedDateTime.now());
        PrioritizeRequestNiazsanjiDTO result = prioritizeRequestNiazsanjiService.save(prioritizeRequestNiazsanjiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, prioritizeRequestNiazsanjiDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /prioritize-request-niazsanjis : get all the prioritizeRequestNiazsanjis.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of prioritizeRequestNiazsanjis in body
     */
    @GetMapping("/prioritize-request-niazsanjis")
    @Timed
    public ResponseEntity<List<PrioritizeRequestNiazsanjiDTO>> getAllPrioritizeRequestNiazsanjis(PrioritizeRequestNiazsanjiCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PrioritizeRequestNiazsanjis by criteria: {}", criteria);
        Page<PrioritizeRequestNiazsanjiDTO> page = prioritizeRequestNiazsanjiQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/prioritize-request-niazsanjis");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /prioritize-request-niazsanjis/count : count all the prioritizeRequestNiazsanjis.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/prioritize-request-niazsanjis/count")
    @Timed
    public ResponseEntity<Long> countPrioritizeRequestNiazsanjis(PrioritizeRequestNiazsanjiCriteria criteria) {
        log.debug("REST request to count PrioritizeRequestNiazsanjis by criteria: {}", criteria);
        return ResponseEntity.ok().body(prioritizeRequestNiazsanjiQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /prioritize-request-niazsanjis/:id : get the "id" prioritizeRequestNiazsanji.
     *
     * @param id the id of the prioritizeRequestNiazsanjiDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the prioritizeRequestNiazsanjiDTO, or with status 404 (Not Found)
     */
    @GetMapping("/prioritize-request-niazsanjis/{id}")
    @Timed
    public ResponseEntity<PrioritizeRequestNiazsanjiDTO> getPrioritizeRequestNiazsanji(@PathVariable Long id) {
        log.debug("REST request to get PrioritizeRequestNiazsanji : {}", id);
        Optional<PrioritizeRequestNiazsanjiDTO> prioritizeRequestNiazsanjiDTO = prioritizeRequestNiazsanjiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(prioritizeRequestNiazsanjiDTO);
    }

    /**
     * DELETE  /prioritize-request-niazsanjis/:id : delete the "id" prioritizeRequestNiazsanji.
     *
     * @param id the id of the prioritizeRequestNiazsanjiDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/prioritize-request-niazsanjis/{id}")
    @Timed
    public ResponseEntity<Void> deletePrioritizeRequestNiazsanji(@PathVariable Long id) {
        log.debug("REST request to delete PrioritizeRequestNiazsanji : {}", id);
        prioritizeRequestNiazsanjiService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
