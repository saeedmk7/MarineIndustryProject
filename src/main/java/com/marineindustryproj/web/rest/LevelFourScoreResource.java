package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.LevelFourScoreService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.LevelFourScoreDTO;
import com.marineindustryproj.service.dto.LevelFourScoreCriteria;
import com.marineindustryproj.service.LevelFourScoreQueryService;
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
 * REST controller for managing LevelFourScore.
 */
@RestController
@RequestMapping("/api")
public class LevelFourScoreResource {

    private final Logger log = LoggerFactory.getLogger(LevelFourScoreResource.class);

    private static final String ENTITY_NAME = "levelFourScore";

    private final LevelFourScoreService levelFourScoreService;

    private final LevelFourScoreQueryService levelFourScoreQueryService;

    public LevelFourScoreResource(LevelFourScoreService levelFourScoreService, LevelFourScoreQueryService levelFourScoreQueryService) {
        this.levelFourScoreService = levelFourScoreService;
        this.levelFourScoreQueryService = levelFourScoreQueryService;
    }

    /**
     * POST  /level-four-scores : Create a new levelFourScore.
     *
     * @param levelFourScoreDTO the levelFourScoreDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new levelFourScoreDTO, or with status 400 (Bad Request) if the levelFourScore has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/level-four-scores")
    @Timed
    public ResponseEntity<LevelFourScoreDTO> createLevelFourScore(@Valid @RequestBody LevelFourScoreDTO levelFourScoreDTO) throws URISyntaxException {
        log.debug("REST request to save LevelFourScore : {}", levelFourScoreDTO);
        if (levelFourScoreDTO.getId() != null) {
            throw new BadRequestAlertException("A new levelFourScore cannot already have an ID", ENTITY_NAME, "idexists");
        }

        levelFourScoreDTO.setCreateDate(ZonedDateTime.now());
        levelFourScoreDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        LevelFourScoreDTO result = levelFourScoreService.save(levelFourScoreDTO);
        return ResponseEntity.created(new URI("/api/level-four-scores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /level-four-scores : Updates an existing levelFourScore.
     *
     * @param levelFourScoreDTO the levelFourScoreDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated levelFourScoreDTO,
     * or with status 400 (Bad Request) if the levelFourScoreDTO is not valid,
     * or with status 500 (Internal Server Error) if the levelFourScoreDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/level-four-scores")
    @Timed
    public ResponseEntity<LevelFourScoreDTO> updateLevelFourScore(@Valid @RequestBody LevelFourScoreDTO levelFourScoreDTO) throws URISyntaxException {
        log.debug("REST request to update LevelFourScore : {}", levelFourScoreDTO);
        if (levelFourScoreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        LevelFourScoreDTO levelFourScore = levelFourScoreService.findOne(levelFourScoreDTO.getId()).get();

        levelFourScoreDTO.setCreateUserLogin(levelFourScore.getCreateUserLogin());
        levelFourScoreDTO.setCreateDate(levelFourScore.getCreateDate());
        levelFourScoreDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        levelFourScoreDTO.setModifyDate(ZonedDateTime.now());

        LevelFourScoreDTO result = levelFourScoreService.save(levelFourScoreDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, levelFourScoreDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /level-four-scores : get all the levelFourScores.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of levelFourScores in body
     */
    @GetMapping("/level-four-scores")
    @Timed
    public ResponseEntity<List<LevelFourScoreDTO>> getAllLevelFourScores(LevelFourScoreCriteria criteria, Pageable pageable) {
        log.debug("REST request to get LevelFourScores by criteria: {}", criteria);
        Page<LevelFourScoreDTO> page = levelFourScoreQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/level-four-scores");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /level-four-scores/count : count all the levelFourScores.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/level-four-scores/count")
    @Timed
    public ResponseEntity<Long> countLevelFourScores(LevelFourScoreCriteria criteria) {
        log.debug("REST request to count LevelFourScores by criteria: {}", criteria);
        return ResponseEntity.ok().body(levelFourScoreQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /level-four-scores/:id : get the "id" levelFourScore.
     *
     * @param id the id of the levelFourScoreDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the levelFourScoreDTO, or with status 404 (Not Found)
     */
    @GetMapping("/level-four-scores/{id}")
    @Timed
    public ResponseEntity<LevelFourScoreDTO> getLevelFourScore(@PathVariable Long id) {
        log.debug("REST request to get LevelFourScore : {}", id);
        Optional<LevelFourScoreDTO> levelFourScoreDTO = levelFourScoreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(levelFourScoreDTO);
    }

    /**
     * DELETE  /level-four-scores/:id : delete the "id" levelFourScore.
     *
     * @param id the id of the levelFourScoreDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/level-four-scores/{id}")
    @Timed
    public ResponseEntity<Void> deleteLevelFourScore(@PathVariable Long id) {
        log.debug("REST request to delete LevelFourScore : {}", id);
        levelFourScoreService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
