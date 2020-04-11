package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.LevelFourCriteriaService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.LevelFourCriteriaDTO;
import com.marineindustryproj.service.dto.LevelFourCriteriaCriteria;
import com.marineindustryproj.service.LevelFourCriteriaQueryService;
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
 * REST controller for managing LevelFourCriteria.
 */
@RestController
@RequestMapping("/api")
public class LevelFourCriteriaResource {

    private final Logger log = LoggerFactory.getLogger(LevelFourCriteriaResource.class);

    private static final String ENTITY_NAME = "levelFourCriteria";

    private final LevelFourCriteriaService levelFourCriteriaService;

    private final LevelFourCriteriaQueryService levelFourCriteriaQueryService;

    public LevelFourCriteriaResource(LevelFourCriteriaService levelFourCriteriaService, LevelFourCriteriaQueryService levelFourCriteriaQueryService) {
        this.levelFourCriteriaService = levelFourCriteriaService;
        this.levelFourCriteriaQueryService = levelFourCriteriaQueryService;
    }

    /**
     * POST  /level-four-criteria : Create a new levelFourCriteria.
     *
     * @param levelFourCriteriaDTO the levelFourCriteriaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new levelFourCriteriaDTO, or with status 400 (Bad Request) if the levelFourCriteria has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/level-four-criteria")
    @Timed
    public ResponseEntity<LevelFourCriteriaDTO> createLevelFourCriteria(@Valid @RequestBody LevelFourCriteriaDTO levelFourCriteriaDTO) throws URISyntaxException {
        log.debug("REST request to save LevelFourCriteria : {}", levelFourCriteriaDTO);
        if (levelFourCriteriaDTO.getId() != null) {
            throw new BadRequestAlertException("A new levelFourCriteria cannot already have an ID", ENTITY_NAME, "idexists");
        }

        levelFourCriteriaDTO.setCreateDate(ZonedDateTime.now());
        levelFourCriteriaDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        LevelFourCriteriaDTO result = levelFourCriteriaService.save(levelFourCriteriaDTO);
        return ResponseEntity.created(new URI("/api/level-four-criteria/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /level-four-criteria : Updates an existing levelFourCriteria.
     *
     * @param levelFourCriteriaDTO the levelFourCriteriaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated levelFourCriteriaDTO,
     * or with status 400 (Bad Request) if the levelFourCriteriaDTO is not valid,
     * or with status 500 (Internal Server Error) if the levelFourCriteriaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/level-four-criteria")
    @Timed
    public ResponseEntity<LevelFourCriteriaDTO> updateLevelFourCriteria(@Valid @RequestBody LevelFourCriteriaDTO levelFourCriteriaDTO) throws URISyntaxException {
        log.debug("REST request to update LevelFourCriteria : {}", levelFourCriteriaDTO);
        if (levelFourCriteriaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        LevelFourCriteriaDTO levelFourCriteria = levelFourCriteriaService.findOne(levelFourCriteriaDTO.getId()).get();

        levelFourCriteriaDTO.setCreateUserLogin(levelFourCriteria.getCreateUserLogin());
        levelFourCriteriaDTO.setCreateDate(levelFourCriteria.getCreateDate());
        levelFourCriteriaDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        levelFourCriteriaDTO.setModifyDate(ZonedDateTime.now());

        LevelFourCriteriaDTO result = levelFourCriteriaService.save(levelFourCriteriaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, levelFourCriteriaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /level-four-criteria : get all the levelFourCriteria.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of levelFourCriteria in body
     */
    @GetMapping("/level-four-criteria")
    @Timed
    public ResponseEntity<List<LevelFourCriteriaDTO>> getAllLevelFourCriteria(LevelFourCriteriaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get LevelFourCriteria by criteria: {}", criteria);
        Page<LevelFourCriteriaDTO> page = levelFourCriteriaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/level-four-criteria");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /level-four-criteria/count : count all the levelFourCriteria.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/level-four-criteria/count")
    @Timed
    public ResponseEntity<Long> countLevelFourCriteria(LevelFourCriteriaCriteria criteria) {
        log.debug("REST request to count LevelFourCriteria by criteria: {}", criteria);
        return ResponseEntity.ok().body(levelFourCriteriaQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /level-four-criteria/:id : get the "id" levelFourCriteria.
     *
     * @param id the id of the levelFourCriteriaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the levelFourCriteriaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/level-four-criteria/{id}")
    @Timed
    public ResponseEntity<LevelFourCriteriaDTO> getLevelFourCriteria(@PathVariable Long id) {
        log.debug("REST request to get LevelFourCriteria : {}", id);
        Optional<LevelFourCriteriaDTO> levelFourCriteriaDTO = levelFourCriteriaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(levelFourCriteriaDTO);
    }

    /**
     * DELETE  /level-four-criteria/:id : delete the "id" levelFourCriteria.
     *
     * @param id the id of the levelFourCriteriaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/level-four-criteria/{id}")
    @Timed
    public ResponseEntity<Void> deleteLevelFourCriteria(@PathVariable Long id) {
        log.debug("REST request to delete LevelFourCriteria : {}", id);
        levelFourCriteriaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
