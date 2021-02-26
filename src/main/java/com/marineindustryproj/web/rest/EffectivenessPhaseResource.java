package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.domain.EffectivenessPhaseLevel;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.*;
import com.marineindustryproj.service.dto.*;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
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
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing EffectivenessPhase.
 */
@RestController
@RequestMapping("/api")
public class EffectivenessPhaseResource {

    private final Logger log = LoggerFactory.getLogger(EffectivenessPhaseResource.class);

    private static final String ENTITY_NAME = "effectivenessPhase";

    private final EffectivenessPhaseService effectivenessPhaseService;

    private final EffectivenessPhaseQueryService effectivenessPhaseQueryService;

    private final EffectivenessPhaseLevelQueryService effectivenessPhaseLevelQueryService;

    private final FinalNiazsanjiReportPersonQueryService finalNiazsanjiReportPersonQueryService;

    private final NiazsanjiPersonGradeQueryService niazsanjiPersonGradeQueryService;

    private final FinalNiazsanjiReportService finalNiazsanjiReportService;

    public EffectivenessPhaseResource(EffectivenessPhaseService effectivenessPhaseService, EffectivenessPhaseQueryService effectivenessPhaseQueryService, EffectivenessPhaseLevelQueryService effectivenessPhaseLevelQueryService, FinalNiazsanjiReportPersonQueryService finalNiazsanjiReportPersonQueryService, NiazsanjiPersonGradeQueryService niazsanjiPersonGradeQueryService, FinalNiazsanjiReportService finalNiazsanjiReportService) {
        this.effectivenessPhaseService = effectivenessPhaseService;
        this.effectivenessPhaseQueryService = effectivenessPhaseQueryService;
        this.effectivenessPhaseLevelQueryService = effectivenessPhaseLevelQueryService;
        this.finalNiazsanjiReportPersonQueryService = finalNiazsanjiReportPersonQueryService;
        this.niazsanjiPersonGradeQueryService = niazsanjiPersonGradeQueryService;
        this.finalNiazsanjiReportService = finalNiazsanjiReportService;
    }

    /**
     * POST  /effectiveness-phases : Create a new effectivenessPhase.
     *
     * @param effectivenessPhaseDTO the effectivenessPhaseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new effectivenessPhaseDTO, or with status 400 (Bad Request) if the effectivenessPhase has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/effectiveness-phases")
    @Timed
    public ResponseEntity<EffectivenessPhaseDTO> createEffectivenessPhase(@Valid @RequestBody EffectivenessPhaseDTO effectivenessPhaseDTO) throws URISyntaxException {
        log.debug("REST request to save EffectivenessPhase : {}", effectivenessPhaseDTO);
        if (effectivenessPhaseDTO.getId() != null) {
            throw new BadRequestAlertException("A new effectivenessPhase cannot already have an ID", ENTITY_NAME, "idexists");
        }

        effectivenessPhaseDTO.setCreateDate(ZonedDateTime.now());
        effectivenessPhaseDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        EffectivenessPhaseDTO result = effectivenessPhaseService.save(effectivenessPhaseDTO);
        return ResponseEntity.created(new URI("/api/effectiveness-phases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /effectiveness-phases : Updates an existing effectivenessPhase.
     *
     * @param effectivenessPhaseDTO the effectivenessPhaseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated effectivenessPhaseDTO,
     * or with status 400 (Bad Request) if the effectivenessPhaseDTO is not valid,
     * or with status 500 (Internal Server Error) if the effectivenessPhaseDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/effectiveness-phases")
    @Timed
    public ResponseEntity<EffectivenessPhaseDTO> updateEffectivenessPhase(@Valid @RequestBody EffectivenessPhaseDTO effectivenessPhaseDTO) throws URISyntaxException {
        log.debug("REST request to update EffectivenessPhase : {}", effectivenessPhaseDTO);
        if (effectivenessPhaseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        EffectivenessPhaseDTO effectivenessPhase = effectivenessPhaseService.findOne(effectivenessPhaseDTO.getId()).get();

        effectivenessPhaseDTO.setCreateUserLogin(effectivenessPhase.getCreateUserLogin());
        effectivenessPhaseDTO.setCreateDate(effectivenessPhase.getCreateDate());
        effectivenessPhaseDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        effectivenessPhaseDTO.setModifyDate(ZonedDateTime.now());

        EffectivenessPhaseDTO result = effectivenessPhaseService.save(effectivenessPhaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, effectivenessPhaseDTO.getId().toString()))
            .body(result);
    }
    @PutMapping("/effectiveness-phases/startPhase/{id}")
    @Timed
    public ResponseEntity<EffectivenessPhaseDTO> startPhase(@Valid @PathVariable long id) throws URISyntaxException {
        log.debug("REST request to startPhase EffectivenessPhase : {}", id);
        if (id <= 0) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        EffectivenessPhaseDTO effectivenessPhase = effectivenessPhaseService.findOne(id).get();

        effectivenessPhase.setStartPhaseDate(ZonedDateTime.now());
        effectivenessPhase.setStatus(10);
        effectivenessPhase.setStartPhaseUserLogin(SecurityUtils.getCurrentUserLogin().get());

        effectivenessPhase.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        effectivenessPhase.setModifyDate(ZonedDateTime.now());

        //This must test
        FinalNiazsanjiReportDTO finalNiazsanjiReport = effectivenessPhase.getFinalNiazsanjiReport();
        finalNiazsanjiReport.setCurrentEffectivenessPhaseLevel(effectivenessPhase.getEffectivenessPhaseLevel().getEffectivenessLevel());
        finalNiazsanjiReportService.save(finalNiazsanjiReport);

        EffectivenessPhaseDTO result = effectivenessPhaseService.save(effectivenessPhase);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, effectivenessPhase.getId().toString()))
            .body(result);
    }
    @PutMapping("/effectiveness-phases/complete-level-two/{finalNiazsanjiReportId}")
    @Timed
    public ResponseEntity<Boolean> completeLevelTwo(@Valid @PathVariable Long finalNiazsanjiReportId) throws Exception {
        if (finalNiazsanjiReportId == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        Boolean result = effectivenessPhaseService.completeLevelTwo(finalNiazsanjiReportId);
        return ResponseEntity.ok().body(result);
    }
    @PutMapping("/effectiveness-phases/complete-effectiveness-phase/{finalNiazsanjiReportId}")
    @Timed
    public ResponseEntity<Boolean> completeEffectivenessPhase(@Valid @PathVariable Long finalNiazsanjiReportId) throws Exception {
        if (finalNiazsanjiReportId == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        Boolean result = effectivenessPhaseService.completeEffectivenessPhase(finalNiazsanjiReportId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * GET  /effectiveness-phases : get all the effectivenessPhases.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of effectivenessPhases in body
     */
    @GetMapping("/effectiveness-phases")
    @Timed
    public ResponseEntity<List<EffectivenessPhaseDTO>> getAllEffectivenessPhases(EffectivenessPhaseCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EffectivenessPhases by criteria: {}", criteria);
        Page<EffectivenessPhaseDTO> page = effectivenessPhaseQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/effectiveness-phases");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/effectiveness-phases/byFinalNiazsanjiId/{finalNiazsanjiReportId}")
    @Timed
    public ResponseEntity<List<EffectivenessPhaseDTO>> getAllEffectivenessPhasesByFinalNiazsanjiReportId(@PathVariable Long finalNiazsanjiReportId) {
        log.debug("REST request to get EffectivenessPhases by finalNiazsanjiReportId: {}", finalNiazsanjiReportId);

        Optional<FinalNiazsanjiReportDTO> finalNiazsanjiReportDTO = finalNiazsanjiReportService.findOne(finalNiazsanjiReportId);
        if(!finalNiazsanjiReportDTO.isPresent())
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");

        FinalNiazsanjiReportDTO finalNiazsanjiReport = finalNiazsanjiReportDTO.get();

        LongFilter finalNiazsanjiReportFilter = new LongFilter();
        finalNiazsanjiReportFilter.setEquals(finalNiazsanjiReportId);

        EffectivenessPhaseCriteria criteria = new EffectivenessPhaseCriteria();
        criteria.setFinalNiazsanjiReportId(finalNiazsanjiReportFilter);

        List<EffectivenessPhaseDTO> effectivenessPhaseDTOS = effectivenessPhaseQueryService.findByCriteria(criteria);

        FinalNiazsanjiReportPersonCriteria finalNiazsanjiReportPersonCriteria = new FinalNiazsanjiReportPersonCriteria();

        BooleanFilter finalNiazsanjiReportPersonAbsentFilter = new BooleanFilter();
        finalNiazsanjiReportPersonAbsentFilter.setEquals(false);

        finalNiazsanjiReportPersonCriteria.setFinalNiazsanjiReportId(finalNiazsanjiReportFilter);
        finalNiazsanjiReportPersonCriteria.setAbsented(finalNiazsanjiReportPersonAbsentFilter);

         long allPeopleCount = finalNiazsanjiReportPersonQueryService.countByCriteria(finalNiazsanjiReportPersonCriteria);

        for (EffectivenessPhaseDTO effectivenessPhaseDTO : effectivenessPhaseDTOS) {
            effectivenessPhaseDTO.setPeopleCount(allPeopleCount);
            FloatFilter floatFilter = new FloatFilter();
            floatFilter.setGreaterThan(0f);

            if(effectivenessPhaseDTO.getEffectivenessPhaseLevel().getEffectivenessLevel() == 1){
                finalNiazsanjiReportPersonCriteria.setLevelOneScore(floatFilter);
                List<FinalNiazsanjiReportPersonDTO> finishPeople = finalNiazsanjiReportPersonQueryService.findByCriteria(finalNiazsanjiReportPersonCriteria);
                long finishPeopleCount = finishPeople.size();
                effectivenessPhaseDTO.setPeopleFinishCount(finishPeopleCount);

                double sumValue = finishPeople.stream().mapToDouble(w -> w.getLevelOneScore()).sum();

                float finalScore = (float) sumValue / allPeopleCount;
                float weightedPoints = (finalScore * effectivenessPhaseDTO.getEffectivenessPhaseLevel().getWeight()) / 100;

                effectivenessPhaseDTO.setFirstScore(0f);
                effectivenessPhaseDTO.setSecondScore(finalScore);
                effectivenessPhaseDTO.setFinalScore(finalScore);
                effectivenessPhaseDTO.setWeightedPoints(weightedPoints);
                finalNiazsanjiReportPersonCriteria.setLevelOneScore(null);
            }
            if(effectivenessPhaseDTO.getEffectivenessPhaseLevel().getEffectivenessLevel() == 2){
                finalNiazsanjiReportPersonCriteria.setAverageScore(floatFilter);

                List<FinalNiazsanjiReportPersonDTO> finishPeople = finalNiazsanjiReportPersonQueryService.findByCriteria(finalNiazsanjiReportPersonCriteria);
                long finishPeopleCount = finishPeople.size();
                effectivenessPhaseDTO.setPeopleFinishCount(finishPeopleCount);

                double beforeTestScore = finishPeople.stream().mapToDouble(w -> w.getScoreBeforeTest()).sum();
                double afterTestScore = finishPeople.stream().mapToDouble(w -> w.getScoreAfterTest()).sum();
                //double sumAverageScore = finishPeople.stream().mapToDouble(w -> w.getAverageScore()).sum();

                float beforeTestScoreFinal = (float) beforeTestScore / allPeopleCount;
                float afterTestScoreFinal = (float) afterTestScore / allPeopleCount;

                float averageScoreFinal = 0f;
                float diff = afterTestScoreFinal - beforeTestScoreFinal;
                if(diff >= effectivenessPhaseDTO.getEffectivenessPhaseLevel().getGoal())
                    averageScoreFinal = 100;
                else
                    averageScoreFinal = (diff / effectivenessPhaseDTO.getEffectivenessPhaseLevel().getGoal()) * 100;

                //float sumAverageScoreFinal = (float) sumAverageScore / allPeopleCount;
                float weightedPoints = (averageScoreFinal * effectivenessPhaseDTO.getEffectivenessPhaseLevel().getWeight()) / 100;

                effectivenessPhaseDTO.setFinalScore(beforeTestScoreFinal);
                effectivenessPhaseDTO.setSecondScore(afterTestScoreFinal);
                effectivenessPhaseDTO.setFinalScore(averageScoreFinal);
                effectivenessPhaseDTO.setWeightedPoints(weightedPoints);

                finalNiazsanjiReportPersonCriteria.setAverageScore(null);
            }
            if(effectivenessPhaseDTO.getEffectivenessPhaseLevel().getEffectivenessLevel() == 3){
                finalNiazsanjiReportPersonCriteria.setLevelThreeScore(floatFilter);
                List<FinalNiazsanjiReportPersonDTO> finishPeople = finalNiazsanjiReportPersonQueryService.findByCriteria(finalNiazsanjiReportPersonCriteria);
                long finishPeopleCount = finishPeople.size();
                effectivenessPhaseDTO.setPeopleFinishCount(finishPeopleCount);

                double sumValue = finishPeople.stream().mapToDouble(w -> w.getLevelThreeScore()).sum();

                float secondScore = (float) sumValue / allPeopleCount;
                float finalScore = (secondScore * effectivenessPhaseDTO.getEffectivenessPhaseLevel().getWeight()) / 100;

                effectivenessPhaseDTO.setFirstScore(0f);
                effectivenessPhaseDTO.setSecondScore(secondScore);
                effectivenessPhaseDTO.setFinalScore(secondScore);
                effectivenessPhaseDTO.setWeightedPoints(finalScore);
                finalNiazsanjiReportPersonCriteria.setLevelThreeScore(null);
            }
            if(effectivenessPhaseDTO.getEffectivenessPhaseLevel().getEffectivenessLevel() == 4){
                finalNiazsanjiReportPersonCriteria.setLevelFourScore(floatFilter);
                List<FinalNiazsanjiReportPersonDTO> finishPeople = finalNiazsanjiReportPersonQueryService.findByCriteria(finalNiazsanjiReportPersonCriteria);
                long finishPeopleCount = finishPeople.size();
                effectivenessPhaseDTO.setPeopleFinishCount(finishPeopleCount);

                double sumValue = finishPeople.stream().mapToDouble(w -> w.getLevelFourScore()).sum();

                float secondScore = (float) sumValue / allPeopleCount;
                float finalScore = (secondScore * effectivenessPhaseDTO.getEffectivenessPhaseLevel().getWeight()) / 100;

                effectivenessPhaseDTO.setFirstScore(0f);
                effectivenessPhaseDTO.setSecondScore(secondScore);
                effectivenessPhaseDTO.setFinalScore(secondScore);
                effectivenessPhaseDTO.setWeightedPoints(finalScore);

                finalNiazsanjiReportPersonCriteria.setLevelFourScore(null);
            }
        }

        if(finalNiazsanjiReport.getCurrentEffectivenessPhaseLevel() <= finalNiazsanjiReport.getSelectedEffectivenessPhaseLevel())
        {
            EffectivenessPhaseLevelCriteria effectivenessPhaseLevelCriteria = new EffectivenessPhaseLevelCriteria();
            IntegerFilter currentEffectivenessPhaseLevelFilter = new IntegerFilter();
            currentEffectivenessPhaseLevelFilter.setEquals(finalNiazsanjiReport.getCurrentEffectivenessPhaseLevel());
            effectivenessPhaseLevelCriteria.setEffectivenessLevelUse(currentEffectivenessPhaseLevelFilter);

            List<EffectivenessPhaseLevelDTO> effectivenessPhaseLevelDTOList = effectivenessPhaseLevelQueryService.findByCriteria(effectivenessPhaseLevelCriteria);

            if(!effectivenessPhaseLevelDTOList.isEmpty())
            {
                for (EffectivenessPhaseDTO effectivenessPhaseDTO : effectivenessPhaseDTOS) {
                    if(effectivenessPhaseDTO.getEffectivenessPhaseLevel().getEffectivenessLevel() == 1){
                        EffectivenessPhaseLevelDTO effectivenessPhaseLevelDTO = effectivenessPhaseLevelDTOList.stream()
                            .filter(w -> w.getEffectivenessLevel() == 1).findFirst().get();
                        effectivenessPhaseDTO.setCurrentWeightedPoints(
                            (effectivenessPhaseDTO.getFinalScore() * effectivenessPhaseLevelDTO.getWeight()) / 100);
                    }
                    if(effectivenessPhaseDTO.getEffectivenessPhaseLevel().getEffectivenessLevel() == 2){
                        EffectivenessPhaseLevelDTO effectivenessPhaseLevelDTO = effectivenessPhaseLevelDTOList.stream()
                            .filter(w -> w.getEffectivenessLevel() == 2).findFirst().get();
                        effectivenessPhaseDTO.setCurrentWeightedPoints(
                            (effectivenessPhaseDTO.getFinalScore() * effectivenessPhaseLevelDTO.getWeight()) / 100);
                    }
                    if(effectivenessPhaseDTO.getEffectivenessPhaseLevel().getEffectivenessLevel() == 3){
                        EffectivenessPhaseLevelDTO effectivenessPhaseLevelDTO = effectivenessPhaseLevelDTOList.stream()
                            .filter(w -> w.getEffectivenessLevel() == 3).findFirst().get();
                        effectivenessPhaseDTO.setCurrentWeightedPoints(
                            (effectivenessPhaseDTO.getFinalScore() * effectivenessPhaseLevelDTO.getWeight()) / 100);
                    }
                    if(effectivenessPhaseDTO.getEffectivenessPhaseLevel().getEffectivenessLevel() == 4){
                        EffectivenessPhaseLevelDTO effectivenessPhaseLevelDTO = effectivenessPhaseLevelDTOList.stream()
                            .filter(w -> w.getEffectivenessLevel() == 4).findFirst().get();
                        effectivenessPhaseDTO.setCurrentWeightedPoints(
                            (effectivenessPhaseDTO.getFinalScore() * effectivenessPhaseLevelDTO.getWeight()) / 100);
                    }
                }
            }

        }

        //HttpHeaders headers = null;
        return ResponseEntity.ok().body(effectivenessPhaseDTOS);
    }

    /**
    * GET  /effectiveness-phases/count : count all the effectivenessPhases.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/effectiveness-phases/count")
    @Timed
    public ResponseEntity<Long> countEffectivenessPhases(EffectivenessPhaseCriteria criteria) {
        log.debug("REST request to count EffectivenessPhases by criteria: {}", criteria);
        return ResponseEntity.ok().body(effectivenessPhaseQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /effectiveness-phases/:id : get the "id" effectivenessPhase.
     *
     * @param id the id of the effectivenessPhaseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the effectivenessPhaseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/effectiveness-phases/{id}")
    @Timed
    public ResponseEntity<EffectivenessPhaseDTO> getEffectivenessPhase(@PathVariable Long id) {
        log.debug("REST request to get EffectivenessPhase : {}", id);
        Optional<EffectivenessPhaseDTO> effectivenessPhaseDTO = effectivenessPhaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(effectivenessPhaseDTO);
    }

    /**
     * DELETE  /effectiveness-phases/:id : delete the "id" effectivenessPhase.
     *
     * @param id the id of the effectivenessPhaseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/effectiveness-phases/{id}")
    @Timed
    public ResponseEntity<Void> deleteEffectivenessPhase(@PathVariable Long id) {
        log.debug("REST request to delete EffectivenessPhase : {}", id);
        effectivenessPhaseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
