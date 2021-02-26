package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.*;
import com.marineindustryproj.service.dto.*;
import com.marineindustryproj.service.dto.customs.EffectivenessPhasePerCriteriaData;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import io.github.jhipster.service.filter.BooleanFilter;
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
 * REST controller for managing LevelThreeEffectiveness.
 */
@RestController
@RequestMapping("/api")
public class LevelThreeEffectivenessResource {

    private final Logger log = LoggerFactory.getLogger(LevelThreeEffectivenessResource.class);

    private static final String ENTITY_NAME = "levelThreeEffectiveness";

    private final LevelThreeEffectivenessService levelThreeEffectivenessService;

    private final LevelThreeEffectivenessQueryService levelThreeEffectivenessQueryService;

    private final FinalNiazsanjiReportPersonQueryService finalNiazsanjiReportPersonQueryService;

    private final LevelThreeCriteriaQueryService levelThreeCriteriaQueryService;

    private final LevelThreeScoreQueryService levelThreeScoreQueryService;

    public LevelThreeEffectivenessResource(LevelThreeEffectivenessService levelThreeEffectivenessService, LevelThreeEffectivenessQueryService levelThreeEffectivenessQueryService, FinalNiazsanjiReportPersonQueryService finalNiazsanjiReportPersonQueryService, LevelThreeCriteriaQueryService levelThreeCriteriaQueryService, LevelThreeScoreQueryService levelThreeScoreQueryService) {
        this.levelThreeEffectivenessService = levelThreeEffectivenessService;
        this.levelThreeEffectivenessQueryService = levelThreeEffectivenessQueryService;
        this.finalNiazsanjiReportPersonQueryService = finalNiazsanjiReportPersonQueryService;
        this.levelThreeCriteriaQueryService = levelThreeCriteriaQueryService;
        this.levelThreeScoreQueryService = levelThreeScoreQueryService;
    }

    /**
     * POST  /level-three-effectivenesses : Create a new levelThreeEffectiveness.
     *
     * @param levelThreeEffectivenessDTO the levelThreeEffectivenessDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new levelThreeEffectivenessDTO, or with status 400 (Bad Request) if the levelThreeEffectiveness has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/level-three-effectivenesses")
    @Timed
    public ResponseEntity<LevelThreeEffectivenessDTO> createLevelThreeEffectiveness(@Valid @RequestBody LevelThreeEffectivenessDTO levelThreeEffectivenessDTO) throws URISyntaxException {
        log.debug("REST request to save LevelThreeEffectiveness : {}", levelThreeEffectivenessDTO);
        if (levelThreeEffectivenessDTO.getId() != null) {
            throw new BadRequestAlertException("A new levelThreeEffectiveness cannot already have an ID", ENTITY_NAME, "idexists");
        }

        levelThreeEffectivenessDTO.setCreateDate(ZonedDateTime.now());
        levelThreeEffectivenessDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        LevelThreeEffectivenessDTO result = levelThreeEffectivenessService.saveWithScore(levelThreeEffectivenessDTO);
        return ResponseEntity.created(new URI("/api/level-three-effectivenesses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /level-three-effectivenesses : Updates an existing levelThreeEffectiveness.
     *
     * @param levelThreeEffectivenessDTO the levelThreeEffectivenessDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated levelThreeEffectivenessDTO,
     * or with status 400 (Bad Request) if the levelThreeEffectivenessDTO is not valid,
     * or with status 500 (Internal Server Error) if the levelThreeEffectivenessDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/level-three-effectivenesses")
    @Timed
    public ResponseEntity<LevelThreeEffectivenessDTO> updateLevelThreeEffectiveness(@Valid @RequestBody LevelThreeEffectivenessDTO levelThreeEffectivenessDTO) throws URISyntaxException {
        log.debug("REST request to update LevelThreeEffectiveness : {}", levelThreeEffectivenessDTO);
        if (levelThreeEffectivenessDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        LevelThreeEffectivenessDTO levelThreeEffectiveness = levelThreeEffectivenessService.findOne(levelThreeEffectivenessDTO.getId()).get();

        levelThreeEffectivenessDTO.setCreateUserLogin(levelThreeEffectiveness.getCreateUserLogin());
        levelThreeEffectivenessDTO.setCreateDate(levelThreeEffectiveness.getCreateDate());
        levelThreeEffectivenessDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        levelThreeEffectivenessDTO.setModifyDate(ZonedDateTime.now());

        LevelThreeEffectivenessDTO result = levelThreeEffectivenessService.saveWithScore(levelThreeEffectivenessDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, levelThreeEffectivenessDTO.getId().toString()))
            .body(result);
    }

    @PutMapping("/level-three-effectivenesses/complete-level/{finalNiazsanjiReportId}")
    @Timed
    public ResponseEntity<Boolean> completeLevel(@PathVariable Long finalNiazsanjiReportId) throws Exception {

        if (finalNiazsanjiReportId == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        Boolean result = levelThreeEffectivenessService.completeLevel(finalNiazsanjiReportId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * GET  /level-three-effectivenesses : get all the levelThreeEffectivenesses.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of levelThreeEffectivenesses in body
     */
    @GetMapping("/level-three-effectivenesses")
    @Timed
    public ResponseEntity<List<LevelThreeEffectivenessDTO>> getAllLevelThreeEffectivenesses(LevelThreeEffectivenessCriteria criteria, Pageable pageable) {
        log.debug("REST request to get LevelThreeEffectivenesses by criteria: {}", criteria);
        Page<LevelThreeEffectivenessDTO> page = levelThreeEffectivenessQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/level-three-effectivenesses");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/level-three-effectivenesses/criteria-chart/{finalNiazsanjiReportId}")
    @Timed
    public ResponseEntity<List<EffectivenessPhasePerCriteriaData>> getAllLevelThreeEffectivenesses(@PathVariable Long finalNiazsanjiReportId) throws Exception {
        log.debug("REST request to get LevelThreeEffectivenesses by criteria: {}", finalNiazsanjiReportId);

        if(finalNiazsanjiReportId == null)
            throw new Exception("finalNiazsanjiReportId is invalid");

        FinalNiazsanjiReportPersonCriteria finalNiazsanjiReportPersonCriteria = new FinalNiazsanjiReportPersonCriteria();
        LongFilter finalNiazsanjiReportFilter = new LongFilter();
        finalNiazsanjiReportFilter.setEquals(finalNiazsanjiReportId);

        BooleanFilter finalNiazsanjiReportPersonAbsentFilter = new BooleanFilter();
        finalNiazsanjiReportPersonAbsentFilter.setEquals(false);

        finalNiazsanjiReportPersonCriteria.setFinalNiazsanjiReportId(finalNiazsanjiReportFilter);
        finalNiazsanjiReportPersonCriteria.setAbsented(finalNiazsanjiReportPersonAbsentFilter);

        List<FinalNiazsanjiReportPersonDTO> finalNiazsanjiReportPersonDTOS = finalNiazsanjiReportPersonQueryService.findByCriteria(finalNiazsanjiReportPersonCriteria);

        if(finalNiazsanjiReportPersonDTOS.isEmpty())
            throw new Exception("finalNiazsanjiReportId is invalid");

        Long mahiatId = finalNiazsanjiReportPersonDTOS.get(0).getFinalNiazsanjiReport().getMahiatCourseId();

        if(mahiatId == null)
            throw new Exception("mahiatId is invalid");

        List<Long> finalNiazsanjiReportPersonIds = finalNiazsanjiReportPersonDTOS.stream().mapToLong(w -> w.getId()).boxed().collect(Collectors.toList());
        LongFilter finalNiazsanjiReportPersonIdsFilter = new LongFilter();
        finalNiazsanjiReportPersonIdsFilter.setIn(finalNiazsanjiReportPersonIds);

        LevelThreeEffectivenessCriteria criteria = new LevelThreeEffectivenessCriteria();
        criteria.setFinalNiazsanjiReportPersonId(finalNiazsanjiReportPersonIdsFilter);

        List<LevelThreeEffectivenessDTO> levelThreeEffectivenesses = levelThreeEffectivenessQueryService.findByCriteria(criteria);

        if(levelThreeEffectivenesses.isEmpty())
            throw new Exception("levelThreeEffectiveness is empty");

        List<Long> levelThreeEffectivenessIds = levelThreeEffectivenesses.stream().mapToLong(a -> a.getId()).boxed().collect(Collectors.toList());

        LevelThreeCriteriaCriteria levelThreeCriteriaCriteria = new LevelThreeCriteriaCriteria();
        LongFilter mahiatFilter = new LongFilter();
        mahiatFilter.setEquals(mahiatId);
        levelThreeCriteriaCriteria.setMahiatCourseId(mahiatFilter);
        List<LevelThreeCriteriaDTO> levelThreeCriteriaDTOList = levelThreeCriteriaQueryService.findByCriteria(levelThreeCriteriaCriteria);

        if(levelThreeCriteriaDTOList.isEmpty())
            throw new Exception("levelThreeCriteria is empty");

        List<EffectivenessPhasePerCriteriaData> effectivenessPhasePerCriteriaDataList = new ArrayList<>();
        for (LevelThreeCriteriaDTO levelThreeCriteriaDTO : levelThreeCriteriaDTOList) {

            EffectivenessPhasePerCriteriaData effectivenessPhasePerCriteriaData = new EffectivenessPhasePerCriteriaData();

            LevelThreeScoreCriteria levelThreeScoreCriteria = new LevelThreeScoreCriteria();
            LongFilter levelThreeCriteriaIdFilter = new LongFilter();
            levelThreeCriteriaIdFilter.setEquals(levelThreeCriteriaDTO.getId());

            LongFilter levelThreeEffectivenessFilter = new LongFilter();
            levelThreeEffectivenessFilter.setIn(levelThreeEffectivenessIds);

            levelThreeScoreCriteria.setLevelThreeCriteriaId(levelThreeCriteriaIdFilter);
            levelThreeScoreCriteria.setLevelThreeEffectivenessId(levelThreeEffectivenessFilter);

            List<LevelThreeScoreDTO> levelThreeScoreDTOList = levelThreeScoreQueryService.findByCriteria(levelThreeScoreCriteria);

            effectivenessPhasePerCriteriaData.setCriteria(levelThreeCriteriaDTO);

            Long sumValue = levelThreeScoreDTOList.stream().mapToLong(a -> a.getScore()).sum();
            effectivenessPhasePerCriteriaData.setSumValue(sumValue);
            effectivenessPhasePerCriteriaDataList.add(effectivenessPhasePerCriteriaData);
        }

        return ResponseEntity.ok().body(effectivenessPhasePerCriteriaDataList);
    }

    /**
    * GET  /level-three-effectivenesses/count : count all the levelThreeEffectivenesses.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/level-three-effectivenesses/count")
    @Timed
    public ResponseEntity<Long> countLevelThreeEffectivenesses(LevelThreeEffectivenessCriteria criteria) {
        log.debug("REST request to count LevelThreeEffectivenesses by criteria: {}", criteria);
        return ResponseEntity.ok().body(levelThreeEffectivenessQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /level-three-effectivenesses/:id : get the "id" levelThreeEffectiveness.
     *
     * @param id the id of the levelThreeEffectivenessDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the levelThreeEffectivenessDTO, or with status 404 (Not Found)
     */
    @GetMapping("/level-three-effectivenesses/{id}")
    @Timed
    public ResponseEntity<LevelThreeEffectivenessDTO> getLevelThreeEffectiveness(@PathVariable Long id) {
        log.debug("REST request to get LevelThreeEffectiveness : {}", id);
        Optional<LevelThreeEffectivenessDTO> levelThreeEffectivenessDTO = levelThreeEffectivenessService.findOne(id);
        return ResponseUtil.wrapOrNotFound(levelThreeEffectivenessDTO);
    }

    /**
     * DELETE  /level-three-effectivenesses/:id : delete the "id" levelThreeEffectiveness.
     *
     * @param id the id of the levelThreeEffectivenessDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/level-three-effectivenesses/{id}")
    @Timed
    public ResponseEntity<Void> deleteLevelThreeEffectiveness(@PathVariable Long id) {
        log.debug("REST request to delete LevelThreeEffectiveness : {}", id);
        levelThreeEffectivenessService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
