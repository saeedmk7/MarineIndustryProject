package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.LevelThreeScoreService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.LevelThreeScoreDTO;
import com.marineindustryproj.service.dto.LevelThreeScoreCriteria;
import com.marineindustryproj.service.LevelThreeScoreQueryService;
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
 * REST controller for managing LevelThreeScore.
 */
@RestController
@RequestMapping("/api")
public class LevelThreeScoreResource {

    private final Logger log = LoggerFactory.getLogger(LevelThreeScoreResource.class);

    private static final String ENTITY_NAME = "levelThreeScore";

    private final LevelThreeScoreService levelThreeScoreService;

    private final LevelThreeScoreQueryService levelThreeScoreQueryService;

    public LevelThreeScoreResource(LevelThreeScoreService levelThreeScoreService, LevelThreeScoreQueryService levelThreeScoreQueryService) {
        this.levelThreeScoreService = levelThreeScoreService;
        this.levelThreeScoreQueryService = levelThreeScoreQueryService;
    }

    /**
     * POST  /level-three-scores : Create a new levelThreeScore.
     *
     * @param levelThreeScoreDTO the levelThreeScoreDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new levelThreeScoreDTO, or with status 400 (Bad Request) if the levelThreeScore has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/level-three-scores")
    @Timed
    public ResponseEntity<LevelThreeScoreDTO> createLevelThreeScore(@Valid @RequestBody LevelThreeScoreDTO levelThreeScoreDTO) throws URISyntaxException {
        log.debug("REST request to save LevelThreeScore : {}", levelThreeScoreDTO);
        if (levelThreeScoreDTO.getId() != null) {
            throw new BadRequestAlertException("A new levelThreeScore cannot already have an ID", ENTITY_NAME, "idexists");
        }

        levelThreeScoreDTO.setCreateDate(ZonedDateTime.now());
        levelThreeScoreDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        LevelThreeScoreDTO result = levelThreeScoreService.save(levelThreeScoreDTO);
        return ResponseEntity.created(new URI("/api/level-three-scores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /level-three-scores : Updates an existing levelThreeScore.
     *
     * @param levelThreeScoreDTO the levelThreeScoreDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated levelThreeScoreDTO,
     * or with status 400 (Bad Request) if the levelThreeScoreDTO is not valid,
     * or with status 500 (Internal Server Error) if the levelThreeScoreDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/level-three-scores")
    @Timed
    public ResponseEntity<LevelThreeScoreDTO> updateLevelThreeScore(@Valid @RequestBody LevelThreeScoreDTO levelThreeScoreDTO) throws URISyntaxException {
        log.debug("REST request to update LevelThreeScore : {}", levelThreeScoreDTO);
        if (levelThreeScoreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        LevelThreeScoreDTO levelThreeScore = levelThreeScoreService.findOne(levelThreeScoreDTO.getId()).get();

        levelThreeScoreDTO.setCreateUserLogin(levelThreeScore.getCreateUserLogin());
        levelThreeScoreDTO.setCreateDate(levelThreeScore.getCreateDate());
        levelThreeScoreDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        levelThreeScoreDTO.setModifyDate(ZonedDateTime.now());

        LevelThreeScoreDTO result = levelThreeScoreService.save(levelThreeScoreDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, levelThreeScoreDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /level-three-scores : get all the levelThreeScores.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of levelThreeScores in body
     */
    @GetMapping("/level-three-scores")
    @Timed
    public ResponseEntity<List<LevelThreeScoreDTO>> getAllLevelThreeScores(LevelThreeScoreCriteria criteria, Pageable pageable) {
        log.debug("REST request to get LevelThreeScores by criteria: {}", criteria);
        Page<LevelThreeScoreDTO> page = levelThreeScoreQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/level-three-scores");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /level-three-scores/count : count all the levelThreeScores.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/level-three-scores/count")
    @Timed
    public ResponseEntity<Long> countLevelThreeScores(LevelThreeScoreCriteria criteria) {
        log.debug("REST request to count LevelThreeScores by criteria: {}", criteria);
        return ResponseEntity.ok().body(levelThreeScoreQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /level-three-scores/:id : get the "id" levelThreeScore.
     *
     * @param id the id of the levelThreeScoreDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the levelThreeScoreDTO, or with status 404 (Not Found)
     */
    @GetMapping("/level-three-scores/{id}")
    @Timed
    public ResponseEntity<LevelThreeScoreDTO> getLevelThreeScore(@PathVariable Long id) {
        log.debug("REST request to get LevelThreeScore : {}", id);
        Optional<LevelThreeScoreDTO> levelThreeScoreDTO = levelThreeScoreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(levelThreeScoreDTO);
    }

    /**
     * DELETE  /level-three-scores/:id : delete the "id" levelThreeScore.
     *
     * @param id the id of the levelThreeScoreDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/level-three-scores/{id}")
    @Timed
    public ResponseEntity<Void> deleteLevelThreeScore(@PathVariable Long id) {
        log.debug("REST request to delete LevelThreeScore : {}", id);
        levelThreeScoreService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
