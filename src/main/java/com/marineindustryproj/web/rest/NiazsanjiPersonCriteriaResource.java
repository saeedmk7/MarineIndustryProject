package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.NiazsanjiPersonCriteriaService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.NiazsanjiPersonCriteriaDTO;
import com.marineindustryproj.service.dto.NiazsanjiPersonCriteriaCriteria;
import com.marineindustryproj.service.NiazsanjiPersonCriteriaQueryService;
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
 * REST controller for managing NiazsanjiPersonCriteria.
 */
@RestController
@RequestMapping("/api")
public class NiazsanjiPersonCriteriaResource {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiPersonCriteriaResource.class);

    private static final String ENTITY_NAME = "niazsanjiPersonCriteria";

    private final NiazsanjiPersonCriteriaService niazsanjiPersonCriteriaService;

    private final NiazsanjiPersonCriteriaQueryService niazsanjiPersonCriteriaQueryService;

    public NiazsanjiPersonCriteriaResource(NiazsanjiPersonCriteriaService niazsanjiPersonCriteriaService, NiazsanjiPersonCriteriaQueryService niazsanjiPersonCriteriaQueryService) {
        this.niazsanjiPersonCriteriaService = niazsanjiPersonCriteriaService;
        this.niazsanjiPersonCriteriaQueryService = niazsanjiPersonCriteriaQueryService;
    }

    /**
     * POST  /niazsanji-person-criteria : Create a new niazsanjiPersonCriteria.
     *
     * @param niazsanjiPersonCriteriaDTO the niazsanjiPersonCriteriaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new niazsanjiPersonCriteriaDTO, or with status 400 (Bad Request) if the niazsanjiPersonCriteria has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/niazsanji-person-criteria")
    @Timed
    public ResponseEntity<NiazsanjiPersonCriteriaDTO> createNiazsanjiPersonCriteria(@Valid @RequestBody NiazsanjiPersonCriteriaDTO niazsanjiPersonCriteriaDTO) throws URISyntaxException {
        log.debug("REST request to save NiazsanjiPersonCriteria : {}", niazsanjiPersonCriteriaDTO);
        if (niazsanjiPersonCriteriaDTO.getId() != null) {
            throw new BadRequestAlertException("A new niazsanjiPersonCriteria cannot already have an ID", ENTITY_NAME, "idexists");
        }

        niazsanjiPersonCriteriaDTO.setCreateDate(ZonedDateTime.now());
        niazsanjiPersonCriteriaDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        NiazsanjiPersonCriteriaDTO result = niazsanjiPersonCriteriaService.save(niazsanjiPersonCriteriaDTO);
        return ResponseEntity.created(new URI("/api/niazsanji-person-criteria/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /niazsanji-person-criteria : Updates an existing niazsanjiPersonCriteria.
     *
     * @param niazsanjiPersonCriteriaDTO the niazsanjiPersonCriteriaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated niazsanjiPersonCriteriaDTO,
     * or with status 400 (Bad Request) if the niazsanjiPersonCriteriaDTO is not valid,
     * or with status 500 (Internal Server Error) if the niazsanjiPersonCriteriaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/niazsanji-person-criteria")
    @Timed
    public ResponseEntity<NiazsanjiPersonCriteriaDTO> updateNiazsanjiPersonCriteria(@Valid @RequestBody NiazsanjiPersonCriteriaDTO niazsanjiPersonCriteriaDTO) throws URISyntaxException {
        log.debug("REST request to update NiazsanjiPersonCriteria : {}", niazsanjiPersonCriteriaDTO);
        if (niazsanjiPersonCriteriaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        NiazsanjiPersonCriteriaDTO niazsanjiPersonCriteria = niazsanjiPersonCriteriaService.findOne(niazsanjiPersonCriteriaDTO.getId()).get();

        niazsanjiPersonCriteriaDTO.setCreateUserLogin(niazsanjiPersonCriteria.getCreateUserLogin());
        niazsanjiPersonCriteriaDTO.setCreateDate(niazsanjiPersonCriteria.getCreateDate());
        niazsanjiPersonCriteriaDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        niazsanjiPersonCriteriaDTO.setModifyDate(ZonedDateTime.now());

        NiazsanjiPersonCriteriaDTO result = niazsanjiPersonCriteriaService.save(niazsanjiPersonCriteriaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, niazsanjiPersonCriteriaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /niazsanji-person-criteria : get all the niazsanjiPersonCriteria.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of niazsanjiPersonCriteria in body
     */
    @GetMapping("/niazsanji-person-criteria")
    @Timed
    public ResponseEntity<List<NiazsanjiPersonCriteriaDTO>> getAllNiazsanjiPersonCriteria(NiazsanjiPersonCriteriaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get NiazsanjiPersonCriteria by criteria: {}", criteria);
        Page<NiazsanjiPersonCriteriaDTO> page = niazsanjiPersonCriteriaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/niazsanji-person-criteria");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /niazsanji-person-criteria/count : count all the niazsanjiPersonCriteria.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/niazsanji-person-criteria/count")
    @Timed
    public ResponseEntity<Long> countNiazsanjiPersonCriteria(NiazsanjiPersonCriteriaCriteria criteria) {
        log.debug("REST request to count NiazsanjiPersonCriteria by criteria: {}", criteria);
        return ResponseEntity.ok().body(niazsanjiPersonCriteriaQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /niazsanji-person-criteria/:id : get the "id" niazsanjiPersonCriteria.
     *
     * @param id the id of the niazsanjiPersonCriteriaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the niazsanjiPersonCriteriaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/niazsanji-person-criteria/{id}")
    @Timed
    public ResponseEntity<NiazsanjiPersonCriteriaDTO> getNiazsanjiPersonCriteria(@PathVariable Long id) {
        log.debug("REST request to get NiazsanjiPersonCriteria : {}", id);
        Optional<NiazsanjiPersonCriteriaDTO> niazsanjiPersonCriteriaDTO = niazsanjiPersonCriteriaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(niazsanjiPersonCriteriaDTO);
    }

    /**
     * DELETE  /niazsanji-person-criteria/:id : delete the "id" niazsanjiPersonCriteria.
     *
     * @param id the id of the niazsanjiPersonCriteriaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/niazsanji-person-criteria/{id}")
    @Timed
    public ResponseEntity<Void> deleteNiazsanjiPersonCriteria(@PathVariable Long id) {
        log.debug("REST request to delete NiazsanjiPersonCriteria : {}", id);
        niazsanjiPersonCriteriaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
