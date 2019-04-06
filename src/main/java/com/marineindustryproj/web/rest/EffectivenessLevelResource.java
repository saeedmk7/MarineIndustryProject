package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.EffectivenessLevelService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.EffectivenessLevelDTO;
import com.marineindustryproj.service.dto.EffectivenessLevelCriteria;
import com.marineindustryproj.service.EffectivenessLevelQueryService;
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
 * REST controller for managing EffectivenessLevel.
 */
@RestController
@RequestMapping("/api")
public class EffectivenessLevelResource {

    private final Logger log = LoggerFactory.getLogger(EffectivenessLevelResource.class);

    private static final String ENTITY_NAME = "effectivenessLevel";

    private final EffectivenessLevelService effectivenessLevelService;

    private final EffectivenessLevelQueryService effectivenessLevelQueryService;

    public EffectivenessLevelResource(EffectivenessLevelService effectivenessLevelService, EffectivenessLevelQueryService effectivenessLevelQueryService) {
        this.effectivenessLevelService = effectivenessLevelService;
        this.effectivenessLevelQueryService = effectivenessLevelQueryService;
    }

    /**
     * POST  /effectiveness-levels : Create a new effectivenessLevel.
     *
     * @param effectivenessLevelDTO the effectivenessLevelDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new effectivenessLevelDTO, or with status 400 (Bad Request) if the effectivenessLevel has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/effectiveness-levels")
    @Timed
    public ResponseEntity<EffectivenessLevelDTO> createEffectivenessLevel(@Valid @RequestBody EffectivenessLevelDTO effectivenessLevelDTO) throws URISyntaxException {
        log.debug("REST request to save EffectivenessLevel : {}", effectivenessLevelDTO);
        if (effectivenessLevelDTO.getId() != null) {
            throw new BadRequestAlertException("A new effectivenessLevel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EffectivenessLevelDTO result = effectivenessLevelService.save(effectivenessLevelDTO);
        return ResponseEntity.created(new URI("/api/effectiveness-levels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /effectiveness-levels : Updates an existing effectivenessLevel.
     *
     * @param effectivenessLevelDTO the effectivenessLevelDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated effectivenessLevelDTO,
     * or with status 400 (Bad Request) if the effectivenessLevelDTO is not valid,
     * or with status 500 (Internal Server Error) if the effectivenessLevelDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/effectiveness-levels")
    @Timed
    public ResponseEntity<EffectivenessLevelDTO> updateEffectivenessLevel(@Valid @RequestBody EffectivenessLevelDTO effectivenessLevelDTO) throws URISyntaxException {
        log.debug("REST request to update EffectivenessLevel : {}", effectivenessLevelDTO);
        if (effectivenessLevelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EffectivenessLevelDTO result = effectivenessLevelService.save(effectivenessLevelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, effectivenessLevelDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /effectiveness-levels : get all the effectivenessLevels.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of effectivenessLevels in body
     */
    @GetMapping("/effectiveness-levels")
    @Timed
    public ResponseEntity<List<EffectivenessLevelDTO>> getAllEffectivenessLevels(EffectivenessLevelCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EffectivenessLevels by criteria: {}", criteria);
        Page<EffectivenessLevelDTO> page = effectivenessLevelQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/effectiveness-levels");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /effectiveness-levels/count : count all the effectivenessLevels.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/effectiveness-levels/count")
    @Timed
    public ResponseEntity<Long> countEffectivenessLevels (EffectivenessLevelCriteria criteria) {
        log.debug("REST request to count EffectivenessLevels by criteria: {}", criteria);
        return ResponseEntity.ok().body(effectivenessLevelQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /effectiveness-levels/:id : get the "id" effectivenessLevel.
     *
     * @param id the id of the effectivenessLevelDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the effectivenessLevelDTO, or with status 404 (Not Found)
     */
    @GetMapping("/effectiveness-levels/{id}")
    @Timed
    public ResponseEntity<EffectivenessLevelDTO> getEffectivenessLevel(@PathVariable Long id) {
        log.debug("REST request to get EffectivenessLevel : {}", id);
        Optional<EffectivenessLevelDTO> effectivenessLevelDTO = effectivenessLevelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(effectivenessLevelDTO);
    }

    /**
     * DELETE  /effectiveness-levels/:id : delete the "id" effectivenessLevel.
     *
     * @param id the id of the effectivenessLevelDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/effectiveness-levels/{id}")
    @Timed
    public ResponseEntity<Void> deleteEffectivenessLevel(@PathVariable Long id) {
        log.debug("REST request to delete EffectivenessLevel : {}", id);
        effectivenessLevelService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
