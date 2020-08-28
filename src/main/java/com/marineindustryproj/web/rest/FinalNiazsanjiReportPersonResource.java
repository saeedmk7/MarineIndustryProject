package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.*;
import com.marineindustryproj.service.dto.*;
import com.marineindustryproj.service.dto.customs.CountListModel;
import com.marineindustryproj.service.dto.customs.FinalNiazsanjiPeopleListModel;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * REST controller for managing FinalNiazsanjiReportPerson.
 */
@RestController
@RequestMapping("/api")
public class FinalNiazsanjiReportPersonResource {

    private final Logger log = LoggerFactory.getLogger(FinalNiazsanjiReportPersonResource.class);

    private static final String ENTITY_NAME = "finalNiazsanjiReportPerson";

    private final FinalNiazsanjiReportPersonService finalNiazsanjiReportPersonService;

    private final FinalNiazsanjiReportPersonQueryService finalNiazsanjiReportPersonQueryService;

    private final NiazsanjiPersonGradeQueryService niazsanjiPersonGradeQueryService;

    private final LevelThreeEffectivenessQueryService levelThreeEffectivenessQueryService;

    private final LevelFourEffectivenessQueryService levelFourEffectivenessQueryService;


    public FinalNiazsanjiReportPersonResource(FinalNiazsanjiReportPersonService finalNiazsanjiReportPersonService, FinalNiazsanjiReportPersonQueryService finalNiazsanjiReportPersonQueryService, NiazsanjiPersonGradeQueryService niazsanjiPersonGradeQueryService, LevelThreeEffectivenessQueryService levelThreeEffectivenessQueryService, LevelFourEffectivenessQueryService levelFourEffectivenessQueryService) {
        this.finalNiazsanjiReportPersonService = finalNiazsanjiReportPersonService;
        this.finalNiazsanjiReportPersonQueryService = finalNiazsanjiReportPersonQueryService;
        this.niazsanjiPersonGradeQueryService = niazsanjiPersonGradeQueryService;
        this.levelThreeEffectivenessQueryService = levelThreeEffectivenessQueryService;
        this.levelFourEffectivenessQueryService = levelFourEffectivenessQueryService;
    }

    /**
     * POST  /final-niazsanji-report-people : Create a new finalNiazsanjiReportPerson.
     *
     * @param finalNiazsanjiReportPersonDTO the finalNiazsanjiReportPersonDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new finalNiazsanjiReportPersonDTO, or with status 400 (Bad Request) if the finalNiazsanjiReportPerson has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/final-niazsanji-report-people")
    @Timed
    public ResponseEntity<FinalNiazsanjiReportPersonDTO> createFinalNiazsanjiReportPerson(@Valid @RequestBody FinalNiazsanjiReportPersonDTO finalNiazsanjiReportPersonDTO) throws URISyntaxException {
        log.debug("REST request to save FinalNiazsanjiReportPerson : {}", finalNiazsanjiReportPersonDTO);
        if (finalNiazsanjiReportPersonDTO.getId() != null) {
            throw new BadRequestAlertException("A new finalNiazsanjiReportPerson cannot already have an ID", ENTITY_NAME, "idexists");
        }

        finalNiazsanjiReportPersonDTO.setCreateDate(ZonedDateTime.now());
        finalNiazsanjiReportPersonDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        FinalNiazsanjiReportPersonDTO result = finalNiazsanjiReportPersonService.save(finalNiazsanjiReportPersonDTO);
        return ResponseEntity.created(new URI("/api/final-niazsanji-report-people/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /final-niazsanji-report-people : Updates an existing finalNiazsanjiReportPerson.
     *
     * @param finalNiazsanjiReportPersonDTO the finalNiazsanjiReportPersonDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated finalNiazsanjiReportPersonDTO,
     * or with status 400 (Bad Request) if the finalNiazsanjiReportPersonDTO is not valid,
     * or with status 500 (Internal Server Error) if the finalNiazsanjiReportPersonDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/final-niazsanji-report-people")
    @Timed
    public ResponseEntity<FinalNiazsanjiReportPersonDTO> updateFinalNiazsanjiReportPerson(@Valid @RequestBody FinalNiazsanjiReportPersonDTO finalNiazsanjiReportPersonDTO) throws URISyntaxException {
        log.debug("REST request to update FinalNiazsanjiReportPerson : {}", finalNiazsanjiReportPersonDTO);
        if (finalNiazsanjiReportPersonDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        FinalNiazsanjiReportPersonDTO finalNiazsanjiReportPerson = finalNiazsanjiReportPersonService.findOne(finalNiazsanjiReportPersonDTO.getId()).get();

        finalNiazsanjiReportPersonDTO.setCreateUserLogin(finalNiazsanjiReportPerson.getCreateUserLogin());
        finalNiazsanjiReportPersonDTO.setCreateDate(finalNiazsanjiReportPerson.getCreateDate());
        finalNiazsanjiReportPersonDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalNiazsanjiReportPersonDTO.setModifyDate(ZonedDateTime.now());

        FinalNiazsanjiReportPersonDTO result = finalNiazsanjiReportPersonService.save(finalNiazsanjiReportPersonDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, finalNiazsanjiReportPersonDTO.getId().toString()))
            .body(result);
    }

    @GetMapping("/final-niazsanji-report-people/getLevelOneDataByFinalNiazsanjiReportId/{finalNiazsanjiReportId}")
    @Timed
    public ResponseEntity<List<FinalNiazsanjiReportPersonDTO>> getLevelOneDataByFinalNiazsanjiReportId(@PathVariable Long finalNiazsanjiReportId) {
        log.debug("REST request to get FinalNiazsanjiReportPerson by finalNiazsanjiReportId: {}", finalNiazsanjiReportId);
        //Page<EffectivenessPhaseDTO> page = effectivenessPhaseQueryService.findByCriteria(criteria, pageable);
        List<FinalNiazsanjiReportPersonDTO> finalNiazsanjiReportPeople = getFinalNiazsanjiReportPeople(finalNiazsanjiReportId);

        LongFilter finalNiazsanjiReportPersonFilter = getFinalNiazsanjiReportPersonLongFilter(finalNiazsanjiReportPeople);

        NiazsanjiPersonGradeCriteria niazsanjiPersonGradeCriteria = new NiazsanjiPersonGradeCriteria();
        niazsanjiPersonGradeCriteria.setFinalNiazsanjiReportPersonId(finalNiazsanjiReportPersonFilter);

        List<NiazsanjiPersonGradeDTO> niazsanjiPersonGrades = niazsanjiPersonGradeQueryService.findByCriteria(niazsanjiPersonGradeCriteria);

        for (FinalNiazsanjiReportPersonDTO finalNiazsanjiReportPerson : finalNiazsanjiReportPeople) {
            List<NiazsanjiPersonGradeDTO> niazsanjiPersonGradeDTOS = niazsanjiPersonGrades.stream().filter(w -> w.getFinalNiazsanjiReportPersonId().equals(finalNiazsanjiReportPerson.getId())).collect(Collectors.toList());
            Set<NiazsanjiPersonGradeDTO> niazsanjiPersonGradeDTOSet = new HashSet<>(niazsanjiPersonGradeDTOS);
            if(!niazsanjiPersonGradeDTOS.isEmpty())
            {
                finalNiazsanjiReportPerson.setNiazsanjiPersonGrades(niazsanjiPersonGradeDTOSet);
            }
        }

        //HttpHeaders headers = null;
        return ResponseEntity.ok().body(finalNiazsanjiReportPeople);
    }
    @GetMapping("/final-niazsanji-report-people/getLevelThreeDataByFinalNiazsanjiReportId/{finalNiazsanjiReportId}")
    @Timed
    public ResponseEntity<List<FinalNiazsanjiReportPersonDTO>> getLevelThreeDataByFinalNiazsanjiReportId(@PathVariable Long finalNiazsanjiReportId) {
        log.debug("REST request to get FinalNiazsanjiReportPerson by finalNiazsanjiReportId: {}", finalNiazsanjiReportId);

        List<FinalNiazsanjiReportPersonDTO> finalNiazsanjiReportPeople = getFinalNiazsanjiReportPeople(finalNiazsanjiReportId);

        LongFilter finalNiazsanjiReportPersonFilter = getFinalNiazsanjiReportPersonLongFilter(finalNiazsanjiReportPeople);

        LevelThreeEffectivenessCriteria levelThreeEffectivenessCriteria = new LevelThreeEffectivenessCriteria();
        levelThreeEffectivenessCriteria.setFinalNiazsanjiReportPersonId(finalNiazsanjiReportPersonFilter);

        List<LevelThreeEffectivenessDTO> levelThreeEffectivenesses = levelThreeEffectivenessQueryService.findByCriteria(levelThreeEffectivenessCriteria);

        for (FinalNiazsanjiReportPersonDTO finalNiazsanjiReportPerson : finalNiazsanjiReportPeople) {
            List<LevelThreeEffectivenessDTO> levelThreeEffectivenessDTOS = levelThreeEffectivenesses.stream()
                .filter(w -> w.getFinalNiazsanjiReportPersonId().equals(finalNiazsanjiReportPerson.getId())).collect(Collectors.toList());
            Set<LevelThreeEffectivenessDTO> levelThreeEffectivenessDTOSet = new HashSet<>(levelThreeEffectivenessDTOS);
            if(!levelThreeEffectivenessDTOS.isEmpty())
            {
                finalNiazsanjiReportPerson.setLevelThreeEffectivenesses(levelThreeEffectivenessDTOSet);
            }
        }

        //HttpHeaders headers = null;
        return ResponseEntity.ok().body(finalNiazsanjiReportPeople);
    }

    private List<FinalNiazsanjiReportPersonDTO> getFinalNiazsanjiReportPeople(@PathVariable Long finalNiazsanjiReportId) {
        LongFilter finalNiazsanjiReportFilter = new LongFilter();
        finalNiazsanjiReportFilter.setEquals(finalNiazsanjiReportId);

        FinalNiazsanjiReportPersonCriteria finalNiazsanjiReportPersonCriteria = new FinalNiazsanjiReportPersonCriteria();
        finalNiazsanjiReportPersonCriteria.setFinalNiazsanjiReportId(finalNiazsanjiReportFilter);

        return finalNiazsanjiReportPersonQueryService.findByCriteria(finalNiazsanjiReportPersonCriteria);
    }

    @GetMapping("/final-niazsanji-report-people/getLevelFourDataByFinalNiazsanjiReportId/{finalNiazsanjiReportId}")
    @Timed
    public ResponseEntity<List<FinalNiazsanjiReportPersonDTO>> getLevelFourDataByFinalNiazsanjiReportId(@PathVariable Long finalNiazsanjiReportId) {
        log.debug("REST request to get FinalNiazsanjiReportPerson by finalNiazsanjiReportId: {}", finalNiazsanjiReportId);

        List<FinalNiazsanjiReportPersonDTO> finalNiazsanjiReportPeople = getFinalNiazsanjiReportPeople(finalNiazsanjiReportId);

        LongFilter finalNiazsanjiReportPersonFilter = getFinalNiazsanjiReportPersonLongFilter(finalNiazsanjiReportPeople);

        LevelFourEffectivenessCriteria levelFourEffectivenessCriteria = new LevelFourEffectivenessCriteria();
        levelFourEffectivenessCriteria.setFinalNiazsanjiReportPersonId(finalNiazsanjiReportPersonFilter);

        List<LevelFourEffectivenessDTO> levelFourEffectivenesses = levelFourEffectivenessQueryService.findByCriteria(levelFourEffectivenessCriteria);

        for (FinalNiazsanjiReportPersonDTO finalNiazsanjiReportPerson : finalNiazsanjiReportPeople) {
            List<LevelFourEffectivenessDTO> levelFourEffectivenessDTOS = levelFourEffectivenesses.stream()
                .filter(w -> w.getFinalNiazsanjiReportPersonId().equals(finalNiazsanjiReportPerson.getId())).collect(Collectors.toList());
            Set<LevelFourEffectivenessDTO> levelFourEffectivenessDTOSet = new HashSet<>(levelFourEffectivenessDTOS);
            if(!levelFourEffectivenessDTOS.isEmpty())
            {
                finalNiazsanjiReportPerson.setLevelFourEffectivenesses(levelFourEffectivenessDTOSet);
            }
        }

        //HttpHeaders headers = null;
        return ResponseEntity.ok().body(finalNiazsanjiReportPeople);
    }

    private LongFilter getFinalNiazsanjiReportPersonLongFilter(List<FinalNiazsanjiReportPersonDTO> finalNiazsanjiReportPeople) {
        List<Long> peopleId = finalNiazsanjiReportPeople.stream().map(w -> w.getId()).collect(Collectors.toList());
        LongFilter finalNiazsanjiReportPersonFilter = new LongFilter();
        finalNiazsanjiReportPersonFilter.setIn(peopleId);
        return finalNiazsanjiReportPersonFilter;
    }

    /**
     * GET  /final-niazsanji-report-people : get all the finalNiazsanjiReportPeople.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of finalNiazsanjiReportPeople in body
     */
    @GetMapping("/final-niazsanji-report-people")
    @Timed
    public ResponseEntity<List<FinalNiazsanjiReportPersonDTO>> getAllFinalNiazsanjiReportPeople(FinalNiazsanjiReportPersonCriteria criteria, Pageable pageable) {
        log.debug("REST request to get FinalNiazsanjiReportPeople by criteria: {}", criteria);
        Page<FinalNiazsanjiReportPersonDTO> page = finalNiazsanjiReportPersonQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/final-niazsanji-report-people");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /final-niazsanji-report-people/count : count all the finalNiazsanjiReportPeople.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/final-niazsanji-report-people/count")
    @Timed
    public ResponseEntity<Long> countFinalNiazsanjiReportPeople (FinalNiazsanjiReportPersonCriteria criteria) {
        log.debug("REST request to count FinalNiazsanjiReportPeople by criteria: {}", criteria);
        return ResponseEntity.ok().body(finalNiazsanjiReportPersonQueryService.countByCriteria(criteria));
    }
    @GetMapping("/final-niazsanji-report-people/count-list/{finalNiazsanjiReportIds}")
    @Timed
    public ResponseEntity<List<CountListModel>> countListFinalNiazsanjiReportPeople (@PathVariable long[] finalNiazsanjiReportIds) {
        log.debug("REST request to count FinalNiazsanjiReportPeople by finalNiazsanjiReportIds: {}", finalNiazsanjiReportIds);

        List<CountListModel> countListModels =
            finalNiazsanjiReportPersonService.countListFinalNiazsanjiReportPeople(finalNiazsanjiReportIds);

        return ResponseEntity.ok().body(countListModels);
    }
    @GetMapping("/final-niazsanji-report-people/get-final-niazsanji-report-people-list/{finalNiazsanjiReportIds}")
    @Timed
    public ResponseEntity<List<FinalNiazsanjiPeopleListModel>> finalNiazsanjiReportPeopleList(@PathVariable long[] finalNiazsanjiReportIds) {
        log.debug("REST request to count FinalNiazsanjiReportPeople by finalNiazsanjiReportIds: {}", finalNiazsanjiReportIds);

        List<FinalNiazsanjiPeopleListModel> finalNiazsanjiPeopleListModels =
            finalNiazsanjiReportPersonService.getFinalNiazsanjiReportPeopleList(finalNiazsanjiReportIds);

        return ResponseEntity.ok().body(finalNiazsanjiPeopleListModels);
    }
    /**
     * GET  /final-niazsanji-report-people/:id : get the "id" finalNiazsanjiReportPerson.
     *
     * @param id the id of the finalNiazsanjiReportPersonDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the finalNiazsanjiReportPersonDTO, or with status 404 (Not Found)
     */
    @GetMapping("/final-niazsanji-report-people/{id}")
    @Timed
    public ResponseEntity<FinalNiazsanjiReportPersonDTO> getFinalNiazsanjiReportPerson(@PathVariable Long id) {
        log.debug("REST request to get FinalNiazsanjiReportPerson : {}", id);
        Optional<FinalNiazsanjiReportPersonDTO> finalNiazsanjiReportPersonDTO = finalNiazsanjiReportPersonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(finalNiazsanjiReportPersonDTO);
    }

    /**
     * DELETE  /final-niazsanji-report-people/:id : delete the "id" finalNiazsanjiReportPerson.
     *
     * @param id the id of the finalNiazsanjiReportPersonDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/final-niazsanji-report-people/{id}")
    @Timed
    public ResponseEntity<Void> deleteFinalNiazsanjiReportPerson(@PathVariable Long id) {
        log.debug("REST request to delete FinalNiazsanjiReportPerson : {}", id);
        finalNiazsanjiReportPersonService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
