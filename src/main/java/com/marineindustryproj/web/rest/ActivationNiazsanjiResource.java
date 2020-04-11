package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.ActivationNiazsanjiService;
import com.marineindustryproj.service.dto.CriterionDTO;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.ActivationNiazsanjiDTO;
import com.marineindustryproj.service.dto.ActivationNiazsanjiCriteria;
import com.marineindustryproj.service.ActivationNiazsanjiQueryService;
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
 * REST controller for managing ActivationNiazsanji.
 */
@RestController
@RequestMapping("/api")
public class ActivationNiazsanjiResource {

    private final Logger log = LoggerFactory.getLogger(ActivationNiazsanjiResource.class);

    private static final String ENTITY_NAME = "activationNiazsanji";

    private final ActivationNiazsanjiService activationNiazsanjiService;

    private final ActivationNiazsanjiQueryService activationNiazsanjiQueryService;

    public ActivationNiazsanjiResource(ActivationNiazsanjiService activationNiazsanjiService, ActivationNiazsanjiQueryService activationNiazsanjiQueryService) {
        this.activationNiazsanjiService = activationNiazsanjiService;
        this.activationNiazsanjiQueryService = activationNiazsanjiQueryService;
    }

    /**
     * POST  /activation-niazsanjis : Create a new activationNiazsanji.
     *
     * @param activationNiazsanjiDTO the activationNiazsanjiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new activationNiazsanjiDTO, or with status 400 (Bad Request) if the activationNiazsanji has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/activation-niazsanjis")
    @Timed
    public ResponseEntity<ActivationNiazsanjiDTO> createActivationNiazsanji(@Valid @RequestBody ActivationNiazsanjiDTO activationNiazsanjiDTO) throws URISyntaxException {
        log.debug("REST request to save ActivationNiazsanji : {}", activationNiazsanjiDTO);
        if (activationNiazsanjiDTO.getId() != null) {
            throw new BadRequestAlertException("A new activationNiazsanji cannot already have an ID", ENTITY_NAME, "idexists");
        }
        activationNiazsanjiDTO.setCreateDate(ZonedDateTime.now());
        activationNiazsanjiDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        ActivationNiazsanjiDTO result = activationNiazsanjiService.save(activationNiazsanjiDTO);
        return ResponseEntity.created(new URI("/api/activation-niazsanjis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /activation-niazsanjis : Updates an existing activationNiazsanji.
     *
     * @param activationNiazsanjiDTO the activationNiazsanjiDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated activationNiazsanjiDTO,
     * or with status 400 (Bad Request) if the activationNiazsanjiDTO is not valid,
     * or with status 500 (Internal Server Error) if the activationNiazsanjiDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/activation-niazsanjis")
    @Timed
    public ResponseEntity<ActivationNiazsanjiDTO> updateActivationNiazsanji(@Valid @RequestBody ActivationNiazsanjiDTO activationNiazsanjiDTO) throws URISyntaxException {
        log.debug("REST request to update ActivationNiazsanji : {}", activationNiazsanjiDTO);
        if (activationNiazsanjiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        ActivationNiazsanjiDTO activationNiazsanji = activationNiazsanjiService.findOne(activationNiazsanjiDTO.getId()).get();

        activationNiazsanjiDTO.setCreateUserLogin(activationNiazsanji.getCreateUserLogin());
        activationNiazsanjiDTO.setCreateDate(activationNiazsanji.getCreateDate());
        activationNiazsanjiDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        activationNiazsanjiDTO.setModifyDate(ZonedDateTime.now());

        ActivationNiazsanjiDTO result = activationNiazsanjiService.save(activationNiazsanjiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, activationNiazsanjiDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /activation-niazsanjis : get all the activationNiazsanjis.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of activationNiazsanjis in body
     */
    @GetMapping("/activation-niazsanjis")
    @Timed
    public ResponseEntity<List<ActivationNiazsanjiDTO>> getAllActivationNiazsanjis(ActivationNiazsanjiCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ActivationNiazsanjis by criteria: {}", criteria);
        Page<ActivationNiazsanjiDTO> page = activationNiazsanjiQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/activation-niazsanjis");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /activation-niazsanjis/count : count all the activationNiazsanjis.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/activation-niazsanjis/count")
    @Timed
    public ResponseEntity<Long> countActivationNiazsanjis(ActivationNiazsanjiCriteria criteria) {
        log.debug("REST request to count ActivationNiazsanjis by criteria: {}", criteria);
        return ResponseEntity.ok().body(activationNiazsanjiQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /activation-niazsanjis/:id : get the "id" activationNiazsanji.
     *
     * @param id the id of the activationNiazsanjiDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the activationNiazsanjiDTO, or with status 404 (Not Found)
     */
    @GetMapping("/activation-niazsanjis/{id}")
    @Timed
    public ResponseEntity<ActivationNiazsanjiDTO> getActivationNiazsanji(@PathVariable Long id) {
        log.debug("REST request to get ActivationNiazsanji : {}", id);
        Optional<ActivationNiazsanjiDTO> activationNiazsanjiDTO = activationNiazsanjiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(activationNiazsanjiDTO);
    }

    /**
     * DELETE  /activation-niazsanjis/:id : delete the "id" activationNiazsanji.
     *
     * @param id the id of the activationNiazsanjiDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/activation-niazsanjis/{id}")
    @Timed
    public ResponseEntity<Void> deleteActivationNiazsanji(@PathVariable Long id) {
        log.debug("REST request to delete ActivationNiazsanji : {}", id);
        activationNiazsanjiService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
