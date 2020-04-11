package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.*;
import com.marineindustryproj.service.dto.*;
import com.marineindustryproj.service.dto.customs.EffectivenessPhasePerCriteriaData;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing LevelFourEffectiveness.
 */
@RestController
@RequestMapping("/api")
public class LevelFourEffectivenessResource {

    private final Logger log = LoggerFactory.getLogger(LevelFourEffectivenessResource.class);

    private static final String ENTITY_NAME = "levelFourEffectiveness";

    private final LevelFourEffectivenessService levelFourEffectivenessService;

    private final LevelFourEffectivenessQueryService levelFourEffectivenessQueryService;

    private final FinalNiazsanjiReportPersonQueryService finalNiazsanjiReportPersonQueryService;

    private final LevelFourCriteriaQueryService levelFourCriteriaQueryService;

    private final LevelFourScoreQueryService levelFourScoreQueryService;

    public LevelFourEffectivenessResource(LevelFourEffectivenessService levelFourEffectivenessService, LevelFourEffectivenessQueryService levelFourEffectivenessQueryService, FinalNiazsanjiReportPersonQueryService finalNiazsanjiReportPersonQueryService, LevelFourCriteriaQueryService levelFourCriteriaQueryService, LevelFourScoreQueryService levelFourScoreQueryService) {
        this.levelFourEffectivenessService = levelFourEffectivenessService;
        this.levelFourEffectivenessQueryService = levelFourEffectivenessQueryService;
        this.finalNiazsanjiReportPersonQueryService = finalNiazsanjiReportPersonQueryService;
        this.levelFourCriteriaQueryService = levelFourCriteriaQueryService;
        this.levelFourScoreQueryService = levelFourScoreQueryService;
    }

    /**
     * POST  /level-four-effectivenesses : Create a new levelFourEffectiveness.
     *
     * @param levelFourEffectivenessDTO the levelFourEffectivenessDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new levelFourEffectivenessDTO, or with status 400 (Bad Request) if the levelFourEffectiveness has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/level-four-effectivenesses")
    @Timed
    public ResponseEntity<LevelFourEffectivenessDTO> createLevelFourEffectiveness(@Valid @RequestBody LevelFourEffectivenessDTO levelFourEffectivenessDTO) throws URISyntaxException {
        log.debug("REST request to save LevelFourEffectiveness : {}", levelFourEffectivenessDTO);
        if (levelFourEffectivenessDTO.getId() != null) {
            throw new BadRequestAlertException("A new levelFourEffectiveness cannot already have an ID", ENTITY_NAME, "idexists");
        }

        levelFourEffectivenessDTO.setCreateDate(ZonedDateTime.now());
        levelFourEffectivenessDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        LevelFourEffectivenessDTO result = levelFourEffectivenessService.saveWithScore(levelFourEffectivenessDTO);
        return ResponseEntity.created(new URI("/api/level-four-effectivenesses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /level-four-effectivenesses : Updates an existing levelFourEffectiveness.
     *
     * @param levelFourEffectivenessDTO the levelFourEffectivenessDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated levelFourEffectivenessDTO,
     * or with status 400 (Bad Request) if the levelFourEffectivenessDTO is not valid,
     * or with status 500 (Internal Server Error) if the levelFourEffectivenessDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/level-four-effectivenesses")
    @Timed
    public ResponseEntity<LevelFourEffectivenessDTO> updateLevelFourEffectiveness(@Valid @RequestBody LevelFourEffectivenessDTO levelFourEffectivenessDTO) throws URISyntaxException {
        log.debug("REST request to update LevelFourEffectiveness : {}", levelFourEffectivenessDTO);
        if (levelFourEffectivenessDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        LevelFourEffectivenessDTO levelFourEffectiveness = levelFourEffectivenessService.findOne(levelFourEffectivenessDTO.getId()).get();

        levelFourEffectivenessDTO.setCreateUserLogin(levelFourEffectiveness.getCreateUserLogin());
        levelFourEffectivenessDTO.setCreateDate(levelFourEffectiveness.getCreateDate());
        levelFourEffectivenessDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        levelFourEffectivenessDTO.setModifyDate(ZonedDateTime.now());

        LevelFourEffectivenessDTO result = levelFourEffectivenessService.saveWithScore(levelFourEffectivenessDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, levelFourEffectivenessDTO.getId().toString()))
            .body(result);
    }

    @PutMapping("/level-four-effectivenesses/complete-level/{finalNiazsanjiReportId}")
    @Timed
    public ResponseEntity<Boolean> completeLevel(@PathVariable Long finalNiazsanjiReportId) throws Exception {

        if (finalNiazsanjiReportId == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        Boolean result = levelFourEffectivenessService.completeLevel(finalNiazsanjiReportId);
        return ResponseEntity.ok().body(result);
    }
    /**
     * GET  /level-four-effectivenesses : get all the levelFourEffectivenesses.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of levelFourEffectivenesses in body
     */
    @GetMapping("/level-four-effectivenesses")
    @Timed
    public ResponseEntity<List<LevelFourEffectivenessDTO>> getAllLevelFourEffectivenesses(LevelFourEffectivenessCriteria criteria, Pageable pageable) {
        log.debug("REST request to get LevelFourEffectivenesses by criteria: {}", criteria);
        Page<LevelFourEffectivenessDTO> page = levelFourEffectivenessQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/level-four-effectivenesses");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/level-four-effectivenesses/criteria-chart/{finalNiazsanjiReportId}")
    @Timed
    public ResponseEntity<List<EffectivenessPhasePerCriteriaData>> getAllLevelFourEffectivenesses(@PathVariable Long finalNiazsanjiReportId) throws Exception {
        log.debug("REST request to get LevelFourEffectivenesses by criteria: {}", finalNiazsanjiReportId);

        if(finalNiazsanjiReportId == null)
            throw new Exception("finalNiazsanjiReportId is invalid");

        FinalNiazsanjiReportPersonCriteria finalNiazsanjiReportPersonCriteria = new FinalNiazsanjiReportPersonCriteria();
        LongFilter finalNiazsanjiReportFilter = new LongFilter();
        finalNiazsanjiReportFilter.setEquals(finalNiazsanjiReportId);

        finalNiazsanjiReportPersonCriteria.setFinalNiazsanjiReportId(finalNiazsanjiReportFilter);

        List<FinalNiazsanjiReportPersonDTO> finalNiazsanjiReportPersonDTOS = finalNiazsanjiReportPersonQueryService.findByCriteria(finalNiazsanjiReportPersonCriteria);

        if(finalNiazsanjiReportPersonDTOS.isEmpty())
            throw new Exception("finalNiazsanjiReportId is invalid");

        List<Long> finalNiazsanjiReportPersonIds = finalNiazsanjiReportPersonDTOS.stream().mapToLong(w -> w.getId()).boxed().collect(Collectors.toList());
        LongFilter finalNiazsanjiReportPersonIdsFilter = new LongFilter();
        finalNiazsanjiReportPersonIdsFilter.setIn(finalNiazsanjiReportPersonIds);

        LevelFourEffectivenessCriteria criteria = new LevelFourEffectivenessCriteria();
        criteria.setFinalNiazsanjiReportPersonId(finalNiazsanjiReportPersonIdsFilter);

        List<LevelFourEffectivenessDTO> levelFourEffectivenesses = levelFourEffectivenessQueryService.findByCriteria(criteria);

        if(levelFourEffectivenesses.isEmpty())
            throw new Exception("levelFourEffectiveness is empty");

        List<Long> levelFourEffectivenessIds = levelFourEffectivenesses.stream().mapToLong(a -> a.getId()).boxed().collect(Collectors.toList());

        LevelFourCriteriaCriteria levelFourCriteriaCriteria = new LevelFourCriteriaCriteria();
        List<LevelFourCriteriaDTO> levelFourCriteriaDTOList = levelFourCriteriaQueryService.findByCriteria(levelFourCriteriaCriteria);

        if(levelFourCriteriaDTOList.isEmpty())
            throw new Exception("levelFourCriteria is empty");

        List<EffectivenessPhasePerCriteriaData> effectivenessPhasePerCriteriaDataList = new ArrayList<>();
        for (LevelFourCriteriaDTO levelFourCriteriaDTO : levelFourCriteriaDTOList) {

            EffectivenessPhasePerCriteriaData effectivenessPhasePerCriteriaData = new EffectivenessPhasePerCriteriaData();

            LevelFourScoreCriteria levelFourScoreCriteria = new LevelFourScoreCriteria();
            LongFilter levelFourCriteriaIdFilter = new LongFilter();
            levelFourCriteriaIdFilter.setEquals(levelFourCriteriaDTO.getId());

            LongFilter levelFourEffectivenessFilter = new LongFilter();
            levelFourEffectivenessFilter.setIn(levelFourEffectivenessIds);

            levelFourScoreCriteria.setLevelFourCriteriaId(levelFourCriteriaIdFilter);
            levelFourScoreCriteria.setLevelFourEffectivenessId(levelFourEffectivenessFilter);

            List<LevelFourScoreDTO> levelFourScoreDTOList = levelFourScoreQueryService.findByCriteria(levelFourScoreCriteria);

            effectivenessPhasePerCriteriaData.setCriteria(levelFourCriteriaDTO);

            Long sumValue = levelFourScoreDTOList.stream().mapToLong(a -> a.getScore()).sum();
            effectivenessPhasePerCriteriaData.setSumValue(sumValue);
            effectivenessPhasePerCriteriaDataList.add(effectivenessPhasePerCriteriaData);
        }

        return ResponseEntity.ok().body(effectivenessPhasePerCriteriaDataList);
    }

    /**
    * GET  /level-four-effectivenesses/count : count all the levelFourEffectivenesses.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/level-four-effectivenesses/count")
    @Timed
    public ResponseEntity<Long> countLevelFourEffectivenesses(LevelFourEffectivenessCriteria criteria) {
        log.debug("REST request to count LevelFourEffectivenesses by criteria: {}", criteria);
        return ResponseEntity.ok().body(levelFourEffectivenessQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /level-four-effectivenesses/:id : get the "id" levelFourEffectiveness.
     *
     * @param id the id of the levelFourEffectivenessDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the levelFourEffectivenessDTO, or with status 404 (Not Found)
     */
    @GetMapping("/level-four-effectivenesses/{id}")
    @Timed
    public ResponseEntity<LevelFourEffectivenessDTO> getLevelFourEffectiveness(@PathVariable Long id) {
        log.debug("REST request to get LevelFourEffectiveness : {}", id);
        Optional<LevelFourEffectivenessDTO> levelFourEffectivenessDTO = levelFourEffectivenessService.findOne(id);
        return ResponseUtil.wrapOrNotFound(levelFourEffectivenessDTO);
    }

    /**
     * DELETE  /level-four-effectivenesses/:id : delete the "id" levelFourEffectiveness.
     *
     * @param id the id of the levelFourEffectivenessDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/level-four-effectivenesses/{id}")
    @Timed
    public ResponseEntity<Void> deleteLevelFourEffectiveness(@PathVariable Long id) {
        log.debug("REST request to delete LevelFourEffectiveness : {}", id);
        levelFourEffectivenessService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
