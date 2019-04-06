package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.EffectivenessIndexService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.EffectivenessIndexDTO;
import com.marineindustryproj.service.dto.EffectivenessIndexCriteria;
import com.marineindustryproj.service.EffectivenessIndexQueryService;
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
 * REST controller for managing EffectivenessIndex.
 */
@RestController
@RequestMapping("/api")
public class EffectivenessIndexResource {

    private final Logger log = LoggerFactory.getLogger(EffectivenessIndexResource.class);

    private static final String ENTITY_NAME = "effectivenessIndex";

    private final EffectivenessIndexService effectivenessIndexService;

    private final EffectivenessIndexQueryService effectivenessIndexQueryService;

    public EffectivenessIndexResource(EffectivenessIndexService effectivenessIndexService, EffectivenessIndexQueryService effectivenessIndexQueryService) {
        this.effectivenessIndexService = effectivenessIndexService;
        this.effectivenessIndexQueryService = effectivenessIndexQueryService;
    }

    /**
     * POST  /effectiveness-indices : Create a new effectivenessIndex.
     *
     * @param effectivenessIndexDTO the effectivenessIndexDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new effectivenessIndexDTO, or with status 400 (Bad Request) if the effectivenessIndex has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/effectiveness-indices")
    @Timed
    public ResponseEntity<EffectivenessIndexDTO> createEffectivenessIndex(@Valid @RequestBody EffectivenessIndexDTO effectivenessIndexDTO) throws URISyntaxException {
        log.debug("REST request to save EffectivenessIndex : {}", effectivenessIndexDTO);
        if (effectivenessIndexDTO.getId() != null) {
            throw new BadRequestAlertException("A new effectivenessIndex cannot already have an ID", ENTITY_NAME, "idexists");
        }
        effectivenessIndexDTO.setCreateDate(ZonedDateTime.now());
        effectivenessIndexDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        EffectivenessIndexDTO result = effectivenessIndexService.save(effectivenessIndexDTO);
        return ResponseEntity.created(new URI("/api/effectiveness-indices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /effectiveness-indices : Updates an existing effectivenessIndex.
     *
     * @param effectivenessIndexDTO the effectivenessIndexDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated effectivenessIndexDTO,
     * or with status 400 (Bad Request) if the effectivenessIndexDTO is not valid,
     * or with status 500 (Internal Server Error) if the effectivenessIndexDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/effectiveness-indices")
    @Timed
    public ResponseEntity<EffectivenessIndexDTO> updateEffectivenessIndex(@Valid @RequestBody EffectivenessIndexDTO effectivenessIndexDTO) throws URISyntaxException {
        log.debug("REST request to update EffectivenessIndex : {}", effectivenessIndexDTO);
        if (effectivenessIndexDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EffectivenessIndexDTO effectivenessIndex = effectivenessIndexService.findOne(effectivenessIndexDTO.getId()).get();

        effectivenessIndexDTO.setCreateUserLogin(effectivenessIndex.getCreateUserLogin());
        effectivenessIndexDTO.setCreateDate(effectivenessIndex.getCreateDate());
        effectivenessIndexDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        effectivenessIndexDTO.setModifyDate(ZonedDateTime.now());
        EffectivenessIndexDTO result = effectivenessIndexService.save(effectivenessIndexDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, effectivenessIndexDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /effectiveness-indices : get all the effectivenessIndices.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of effectivenessIndices in body
     */
    @GetMapping("/effectiveness-indices")
    @Timed
    public ResponseEntity<List<EffectivenessIndexDTO>> getAllEffectivenessIndices(EffectivenessIndexCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EffectivenessIndices by criteria: {}", criteria);
        Page<EffectivenessIndexDTO> page = effectivenessIndexQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/effectiveness-indices");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /effectiveness-indices/count : count all the effectivenessIndices.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/effectiveness-indices/count")
    @Timed
    public ResponseEntity<Long> countEffectivenessIndices (EffectivenessIndexCriteria criteria) {
        log.debug("REST request to count EffectivenessIndices by criteria: {}", criteria);
        return ResponseEntity.ok().body(effectivenessIndexQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /effectiveness-indices/:id : get the "id" effectivenessIndex.
     *
     * @param id the id of the effectivenessIndexDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the effectivenessIndexDTO, or with status 404 (Not Found)
     */
    @GetMapping("/effectiveness-indices/{id}")
    @Timed
    public ResponseEntity<EffectivenessIndexDTO> getEffectivenessIndex(@PathVariable Long id) {
        log.debug("REST request to get EffectivenessIndex : {}", id);
        Optional<EffectivenessIndexDTO> effectivenessIndexDTO = effectivenessIndexService.findOne(id);
        return ResponseUtil.wrapOrNotFound(effectivenessIndexDTO);
    }

    /**
     * DELETE  /effectiveness-indices/:id : delete the "id" effectivenessIndex.
     *
     * @param id the id of the effectivenessIndexDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/effectiveness-indices/{id}")
    @Timed
    public ResponseEntity<Void> deleteEffectivenessIndex(@PathVariable Long id) {
        log.debug("REST request to delete EffectivenessIndex : {}", id);
        effectivenessIndexService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
