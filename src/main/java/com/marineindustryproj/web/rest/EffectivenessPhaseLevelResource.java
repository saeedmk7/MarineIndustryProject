package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.EffectivenessPhaseLevelService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.EffectivenessPhaseLevelDTO;
import com.marineindustryproj.service.dto.EffectivenessPhaseLevelCriteria;
import com.marineindustryproj.service.EffectivenessPhaseLevelQueryService;
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
 * REST controller for managing EffectivenessPhaseLevel.
 */
@RestController
@RequestMapping("/api")
public class EffectivenessPhaseLevelResource {

    private final Logger log = LoggerFactory.getLogger(EffectivenessPhaseLevelResource.class);

    private static final String ENTITY_NAME = "effectivenessPhaseLevel";

    private final EffectivenessPhaseLevelService effectivenessPhaseLevelService;

    private final EffectivenessPhaseLevelQueryService effectivenessPhaseLevelQueryService;

    public EffectivenessPhaseLevelResource(EffectivenessPhaseLevelService effectivenessPhaseLevelService, EffectivenessPhaseLevelQueryService effectivenessPhaseLevelQueryService) {
        this.effectivenessPhaseLevelService = effectivenessPhaseLevelService;
        this.effectivenessPhaseLevelQueryService = effectivenessPhaseLevelQueryService;
    }

    /**
     * POST  /effectiveness-phase-levels : Create a new effectivenessPhaseLevel.
     *
     * @param effectivenessPhaseLevelDTO the effectivenessPhaseLevelDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new effectivenessPhaseLevelDTO, or with status 400 (Bad Request) if the effectivenessPhaseLevel has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/effectiveness-phase-levels")
    @Timed
    public ResponseEntity<EffectivenessPhaseLevelDTO> createEffectivenessPhaseLevel(@Valid @RequestBody EffectivenessPhaseLevelDTO effectivenessPhaseLevelDTO) throws URISyntaxException {
        log.debug("REST request to save EffectivenessPhaseLevel : {}", effectivenessPhaseLevelDTO);
        if (effectivenessPhaseLevelDTO.getId() != null) {
            throw new BadRequestAlertException("A new effectivenessPhaseLevel cannot already have an ID", ENTITY_NAME, "idexists");
        }

        effectivenessPhaseLevelDTO.setCreateDate(ZonedDateTime.now());
        effectivenessPhaseLevelDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        EffectivenessPhaseLevelDTO result = effectivenessPhaseLevelService.save(effectivenessPhaseLevelDTO);
        return ResponseEntity.created(new URI("/api/effectiveness-phase-levels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /effectiveness-phase-levels : Updates an existing effectivenessPhaseLevel.
     *
     * @param effectivenessPhaseLevelDTO the effectivenessPhaseLevelDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated effectivenessPhaseLevelDTO,
     * or with status 400 (Bad Request) if the effectivenessPhaseLevelDTO is not valid,
     * or with status 500 (Internal Server Error) if the effectivenessPhaseLevelDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/effectiveness-phase-levels")
    @Timed
    public ResponseEntity<EffectivenessPhaseLevelDTO> updateEffectivenessPhaseLevel(@Valid @RequestBody EffectivenessPhaseLevelDTO effectivenessPhaseLevelDTO) throws URISyntaxException {
        log.debug("REST request to update EffectivenessPhaseLevel : {}", effectivenessPhaseLevelDTO);
        if (effectivenessPhaseLevelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        EffectivenessPhaseLevelDTO effectivenessPhaseLevel = effectivenessPhaseLevelService.findOne(effectivenessPhaseLevelDTO.getId()).get();

        effectivenessPhaseLevelDTO.setCreateUserLogin(effectivenessPhaseLevel.getCreateUserLogin());
        effectivenessPhaseLevelDTO.setCreateDate(effectivenessPhaseLevel.getCreateDate());
        effectivenessPhaseLevelDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        effectivenessPhaseLevelDTO.setModifyDate(ZonedDateTime.now());

        EffectivenessPhaseLevelDTO result = effectivenessPhaseLevelService.save(effectivenessPhaseLevelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, effectivenessPhaseLevelDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /effectiveness-phase-levels : get all the effectivenessPhaseLevels.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of effectivenessPhaseLevels in body
     */
    @GetMapping("/effectiveness-phase-levels")
    @Timed
    public ResponseEntity<List<EffectivenessPhaseLevelDTO>> getAllEffectivenessPhaseLevels(EffectivenessPhaseLevelCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EffectivenessPhaseLevels by criteria: {}", criteria);
        Page<EffectivenessPhaseLevelDTO> page = effectivenessPhaseLevelQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/effectiveness-phase-levels");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /effectiveness-phase-levels/count : count all the effectivenessPhaseLevels.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/effectiveness-phase-levels/count")
    @Timed
    public ResponseEntity<Long> countEffectivenessPhaseLevels(EffectivenessPhaseLevelCriteria criteria) {
        log.debug("REST request to count EffectivenessPhaseLevels by criteria: {}", criteria);
        return ResponseEntity.ok().body(effectivenessPhaseLevelQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /effectiveness-phase-levels/:id : get the "id" effectivenessPhaseLevel.
     *
     * @param id the id of the effectivenessPhaseLevelDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the effectivenessPhaseLevelDTO, or with status 404 (Not Found)
     */
    @GetMapping("/effectiveness-phase-levels/{id}")
    @Timed
    public ResponseEntity<EffectivenessPhaseLevelDTO> getEffectivenessPhaseLevel(@PathVariable Long id) {
        log.debug("REST request to get EffectivenessPhaseLevel : {}", id);
        Optional<EffectivenessPhaseLevelDTO> effectivenessPhaseLevelDTO = effectivenessPhaseLevelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(effectivenessPhaseLevelDTO);
    }

    /**
     * DELETE  /effectiveness-phase-levels/:id : delete the "id" effectivenessPhaseLevel.
     *
     * @param id the id of the effectivenessPhaseLevelDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/effectiveness-phase-levels/{id}")
    @Timed
    public ResponseEntity<Void> deleteEffectivenessPhaseLevel(@PathVariable Long id) {
        log.debug("REST request to delete EffectivenessPhaseLevel : {}", id);
        effectivenessPhaseLevelService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
