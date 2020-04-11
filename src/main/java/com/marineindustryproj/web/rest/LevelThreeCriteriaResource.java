package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.LevelThreeCriteriaService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.LevelThreeCriteriaDTO;
import com.marineindustryproj.service.dto.LevelThreeCriteriaCriteria;
import com.marineindustryproj.service.LevelThreeCriteriaQueryService;
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
 * REST controller for managing LevelThreeCriteria.
 */
@RestController
@RequestMapping("/api")
public class LevelThreeCriteriaResource {

    private final Logger log = LoggerFactory.getLogger(LevelThreeCriteriaResource.class);

    private static final String ENTITY_NAME = "levelThreeCriteria";

    private final LevelThreeCriteriaService levelThreeCriteriaService;

    private final LevelThreeCriteriaQueryService levelThreeCriteriaQueryService;

    public LevelThreeCriteriaResource(LevelThreeCriteriaService levelThreeCriteriaService, LevelThreeCriteriaQueryService levelThreeCriteriaQueryService) {
        this.levelThreeCriteriaService = levelThreeCriteriaService;
        this.levelThreeCriteriaQueryService = levelThreeCriteriaQueryService;
    }

    /**
     * POST  /level-three-criteria : Create a new levelThreeCriteria.
     *
     * @param levelThreeCriteriaDTO the levelThreeCriteriaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new levelThreeCriteriaDTO, or with status 400 (Bad Request) if the levelThreeCriteria has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/level-three-criteria")
    @Timed
    public ResponseEntity<LevelThreeCriteriaDTO> createLevelThreeCriteria(@Valid @RequestBody LevelThreeCriteriaDTO levelThreeCriteriaDTO) throws URISyntaxException {
        log.debug("REST request to save LevelThreeCriteria : {}", levelThreeCriteriaDTO);
        if (levelThreeCriteriaDTO.getId() != null) {
            throw new BadRequestAlertException("A new levelThreeCriteria cannot already have an ID", ENTITY_NAME, "idexists");
        }

        levelThreeCriteriaDTO.setCreateDate(ZonedDateTime.now());
        levelThreeCriteriaDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        LevelThreeCriteriaDTO result = levelThreeCriteriaService.save(levelThreeCriteriaDTO);
        return ResponseEntity.created(new URI("/api/level-three-criteria/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /level-three-criteria : Updates an existing levelThreeCriteria.
     *
     * @param levelThreeCriteriaDTO the levelThreeCriteriaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated levelThreeCriteriaDTO,
     * or with status 400 (Bad Request) if the levelThreeCriteriaDTO is not valid,
     * or with status 500 (Internal Server Error) if the levelThreeCriteriaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/level-three-criteria")
    @Timed
    public ResponseEntity<LevelThreeCriteriaDTO> updateLevelThreeCriteria(@Valid @RequestBody LevelThreeCriteriaDTO levelThreeCriteriaDTO) throws URISyntaxException {
        log.debug("REST request to update LevelThreeCriteria : {}", levelThreeCriteriaDTO);
        if (levelThreeCriteriaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        LevelThreeCriteriaDTO levelThreeCriteria = levelThreeCriteriaService.findOne(levelThreeCriteriaDTO.getId()).get();

        levelThreeCriteriaDTO.setCreateUserLogin(levelThreeCriteria.getCreateUserLogin());
        levelThreeCriteriaDTO.setCreateDate(levelThreeCriteria.getCreateDate());
        levelThreeCriteriaDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        levelThreeCriteriaDTO.setModifyDate(ZonedDateTime.now());

        LevelThreeCriteriaDTO result = levelThreeCriteriaService.save(levelThreeCriteriaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, levelThreeCriteriaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /level-three-criteria : get all the levelThreeCriteria.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of levelThreeCriteria in body
     */
    @GetMapping("/level-three-criteria")
    @Timed
    public ResponseEntity<List<LevelThreeCriteriaDTO>> getAllLevelThreeCriteria(LevelThreeCriteriaCriteria criteria, Pageable pageable) {
        log.debug("REST request to get LevelThreeCriteria by criteria: {}", criteria);
        Page<LevelThreeCriteriaDTO> page = levelThreeCriteriaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/level-three-criteria");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /level-three-criteria/count : count all the levelThreeCriteria.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/level-three-criteria/count")
    @Timed
    public ResponseEntity<Long> countLevelThreeCriteria(LevelThreeCriteriaCriteria criteria) {
        log.debug("REST request to count LevelThreeCriteria by criteria: {}", criteria);
        return ResponseEntity.ok().body(levelThreeCriteriaQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /level-three-criteria/:id : get the "id" levelThreeCriteria.
     *
     * @param id the id of the levelThreeCriteriaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the levelThreeCriteriaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/level-three-criteria/{id}")
    @Timed
    public ResponseEntity<LevelThreeCriteriaDTO> getLevelThreeCriteria(@PathVariable Long id) {
        log.debug("REST request to get LevelThreeCriteria : {}", id);
        Optional<LevelThreeCriteriaDTO> levelThreeCriteriaDTO = levelThreeCriteriaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(levelThreeCriteriaDTO);
    }

    /**
     * DELETE  /level-three-criteria/:id : delete the "id" levelThreeCriteria.
     *
     * @param id the id of the levelThreeCriteriaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/level-three-criteria/{id}")
    @Timed
    public ResponseEntity<Void> deleteLevelThreeCriteria(@PathVariable Long id) {
        log.debug("REST request to delete LevelThreeCriteria : {}", id);
        levelThreeCriteriaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
