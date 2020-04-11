package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.LevelThreeCriteriaGroupService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.LevelThreeCriteriaGroupDTO;
import com.marineindustryproj.service.dto.LevelThreeCriteriaGroupCriteria;
import com.marineindustryproj.service.LevelThreeCriteriaGroupQueryService;
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
 * REST controller for managing LevelThreeCriteriaGroup.
 */
@RestController
@RequestMapping("/api")
public class LevelThreeCriteriaGroupResource {

    private final Logger log = LoggerFactory.getLogger(LevelThreeCriteriaGroupResource.class);

    private static final String ENTITY_NAME = "levelThreeCriteriaGroup";

    private final LevelThreeCriteriaGroupService levelThreeCriteriaGroupService;

    private final LevelThreeCriteriaGroupQueryService levelThreeCriteriaGroupQueryService;

    public LevelThreeCriteriaGroupResource(LevelThreeCriteriaGroupService levelThreeCriteriaGroupService, LevelThreeCriteriaGroupQueryService levelThreeCriteriaGroupQueryService) {
        this.levelThreeCriteriaGroupService = levelThreeCriteriaGroupService;
        this.levelThreeCriteriaGroupQueryService = levelThreeCriteriaGroupQueryService;
    }

    /**
     * POST  /level-three-criteria-groups : Create a new levelThreeCriteriaGroup.
     *
     * @param levelThreeCriteriaGroupDTO the levelThreeCriteriaGroupDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new levelThreeCriteriaGroupDTO, or with status 400 (Bad Request) if the levelThreeCriteriaGroup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/level-three-criteria-groups")
    @Timed
    public ResponseEntity<LevelThreeCriteriaGroupDTO> createLevelThreeCriteriaGroup(@Valid @RequestBody LevelThreeCriteriaGroupDTO levelThreeCriteriaGroupDTO) throws URISyntaxException {
        log.debug("REST request to save LevelThreeCriteriaGroup : {}", levelThreeCriteriaGroupDTO);
        if (levelThreeCriteriaGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new levelThreeCriteriaGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }

        levelThreeCriteriaGroupDTO.setCreateDate(ZonedDateTime.now());
        levelThreeCriteriaGroupDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        LevelThreeCriteriaGroupDTO result = levelThreeCriteriaGroupService.save(levelThreeCriteriaGroupDTO);
        return ResponseEntity.created(new URI("/api/level-three-criteria-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /level-three-criteria-groups : Updates an existing levelThreeCriteriaGroup.
     *
     * @param levelThreeCriteriaGroupDTO the levelThreeCriteriaGroupDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated levelThreeCriteriaGroupDTO,
     * or with status 400 (Bad Request) if the levelThreeCriteriaGroupDTO is not valid,
     * or with status 500 (Internal Server Error) if the levelThreeCriteriaGroupDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/level-three-criteria-groups")
    @Timed
    public ResponseEntity<LevelThreeCriteriaGroupDTO> updateLevelThreeCriteriaGroup(@Valid @RequestBody LevelThreeCriteriaGroupDTO levelThreeCriteriaGroupDTO) throws URISyntaxException {
        log.debug("REST request to update LevelThreeCriteriaGroup : {}", levelThreeCriteriaGroupDTO);
        if (levelThreeCriteriaGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        LevelThreeCriteriaGroupDTO levelThreeCriteriaGroup = levelThreeCriteriaGroupService.findOne(levelThreeCriteriaGroupDTO.getId()).get();

        levelThreeCriteriaGroupDTO.setCreateUserLogin(levelThreeCriteriaGroup.getCreateUserLogin());
        levelThreeCriteriaGroupDTO.setCreateDate(levelThreeCriteriaGroup.getCreateDate());
        levelThreeCriteriaGroupDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        levelThreeCriteriaGroupDTO.setModifyDate(ZonedDateTime.now());

        LevelThreeCriteriaGroupDTO result = levelThreeCriteriaGroupService.save(levelThreeCriteriaGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, levelThreeCriteriaGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /level-three-criteria-groups : get all the levelThreeCriteriaGroups.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of levelThreeCriteriaGroups in body
     */
    @GetMapping("/level-three-criteria-groups")
    @Timed
    public ResponseEntity<List<LevelThreeCriteriaGroupDTO>> getAllLevelThreeCriteriaGroups(LevelThreeCriteriaGroupCriteria criteria, Pageable pageable) {
        log.debug("REST request to get LevelThreeCriteriaGroups by criteria: {}", criteria);
        Page<LevelThreeCriteriaGroupDTO> page = levelThreeCriteriaGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/level-three-criteria-groups");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /level-three-criteria-groups/count : count all the levelThreeCriteriaGroups.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/level-three-criteria-groups/count")
    @Timed
    public ResponseEntity<Long> countLevelThreeCriteriaGroups(LevelThreeCriteriaGroupCriteria criteria) {
        log.debug("REST request to count LevelThreeCriteriaGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(levelThreeCriteriaGroupQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /level-three-criteria-groups/:id : get the "id" levelThreeCriteriaGroup.
     *
     * @param id the id of the levelThreeCriteriaGroupDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the levelThreeCriteriaGroupDTO, or with status 404 (Not Found)
     */
    @GetMapping("/level-three-criteria-groups/{id}")
    @Timed
    public ResponseEntity<LevelThreeCriteriaGroupDTO> getLevelThreeCriteriaGroup(@PathVariable Long id) {
        log.debug("REST request to get LevelThreeCriteriaGroup : {}", id);
        Optional<LevelThreeCriteriaGroupDTO> levelThreeCriteriaGroupDTO = levelThreeCriteriaGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(levelThreeCriteriaGroupDTO);
    }

    /**
     * DELETE  /level-three-criteria-groups/:id : delete the "id" levelThreeCriteriaGroup.
     *
     * @param id the id of the levelThreeCriteriaGroupDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/level-three-criteria-groups/{id}")
    @Timed
    public ResponseEntity<Void> deleteLevelThreeCriteriaGroup(@PathVariable Long id) {
        log.debug("REST request to delete LevelThreeCriteriaGroup : {}", id);
        levelThreeCriteriaGroupService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
