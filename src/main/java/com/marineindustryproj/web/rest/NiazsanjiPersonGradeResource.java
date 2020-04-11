package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.domain.NiazsanjiPersonGrade;
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
 * REST controller for managing NiazsanjiPersonGrade.
 */
@RestController
@RequestMapping("/api")
public class NiazsanjiPersonGradeResource {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiPersonGradeResource.class);

    private static final String ENTITY_NAME = "niazsanjiPersonGrade";

    private final NiazsanjiPersonGradeService niazsanjiPersonGradeService;

    private final NiazsanjiPersonGradeQueryService niazsanjiPersonGradeQueryService;

    private final FinalNiazsanjiReportPersonQueryService finalNiazsanjiReportPersonQueryService;

    private final NiazsanjiPersonCriteriaQueryService niazsanjiPersonCriteriaQueryService;

    private final NiazsanjiPersonGradeScoreQueryService niazsanjiPersonGradeScoreQueryService;

    public NiazsanjiPersonGradeResource(NiazsanjiPersonGradeService niazsanjiPersonGradeService, NiazsanjiPersonGradeQueryService niazsanjiPersonGradeQueryService, FinalNiazsanjiReportPersonQueryService finalNiazsanjiReportPersonQueryService, NiazsanjiPersonCriteriaQueryService niazsanjiPersonCriteriaQueryService, NiazsanjiPersonGradeScoreQueryService niazsanjiPersonGradeScoreQueryService) {
        this.niazsanjiPersonGradeService = niazsanjiPersonGradeService;
        this.niazsanjiPersonGradeQueryService = niazsanjiPersonGradeQueryService;
        this.finalNiazsanjiReportPersonQueryService = finalNiazsanjiReportPersonQueryService;
        this.niazsanjiPersonCriteriaQueryService = niazsanjiPersonCriteriaQueryService;
        this.niazsanjiPersonGradeScoreQueryService = niazsanjiPersonGradeScoreQueryService;
    }

    /**
     * POST  /niazsanji-person-grades : Create a new niazsanjiPersonGrade.
     *
     * @param niazsanjiPersonGradeDTO the niazsanjiPersonGradeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new niazsanjiPersonGradeDTO, or with status 400 (Bad Request) if the niazsanjiPersonGrade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/niazsanji-person-grades")
    @Timed
    public ResponseEntity<NiazsanjiPersonGradeDTO> createNiazsanjiPersonGrade(@Valid @RequestBody NiazsanjiPersonGradeDTO niazsanjiPersonGradeDTO) throws URISyntaxException {
        log.debug("REST request to save NiazsanjiPersonGrade : {}", niazsanjiPersonGradeDTO);
        if (niazsanjiPersonGradeDTO.getId() != null) {
            throw new BadRequestAlertException("A new niazsanjiPersonGrade cannot already have an ID", ENTITY_NAME, "idexists");
        }

        niazsanjiPersonGradeDTO.setCreateDate(ZonedDateTime.now());
        niazsanjiPersonGradeDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        NiazsanjiPersonGradeDTO result = niazsanjiPersonGradeService.saveWithScore(niazsanjiPersonGradeDTO);
        return ResponseEntity.created(new URI("/api/niazsanji-person-grades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /niazsanji-person-grades : Updates an existing niazsanjiPersonGrade.
     *
     * @param niazsanjiPersonGradeDTO the niazsanjiPersonGradeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated niazsanjiPersonGradeDTO,
     * or with status 400 (Bad Request) if the niazsanjiPersonGradeDTO is not valid,
     * or with status 500 (Internal Server Error) if the niazsanjiPersonGradeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/niazsanji-person-grades")
    @Timed
    public ResponseEntity<NiazsanjiPersonGradeDTO> updateNiazsanjiPersonGrade(@Valid @RequestBody NiazsanjiPersonGradeDTO niazsanjiPersonGradeDTO) throws URISyntaxException {
        log.debug("REST request to update NiazsanjiPersonGrade : {}", niazsanjiPersonGradeDTO);
        if (niazsanjiPersonGradeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        NiazsanjiPersonGradeDTO niazsanjiPersonGrade = niazsanjiPersonGradeService.findOne(niazsanjiPersonGradeDTO.getId()).get();

        niazsanjiPersonGradeDTO.setCreateUserLogin(niazsanjiPersonGrade.getCreateUserLogin());
        niazsanjiPersonGradeDTO.setCreateDate(niazsanjiPersonGrade.getCreateDate());
        niazsanjiPersonGradeDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        niazsanjiPersonGradeDTO.setModifyDate(ZonedDateTime.now());

        NiazsanjiPersonGradeDTO result = niazsanjiPersonGradeService.saveWithScore(niazsanjiPersonGradeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, niazsanjiPersonGradeDTO.getId().toString()))
            .body(result);
    }

    @PutMapping("/niazsanji-person-grades/complete-level/{finalNiazsanjiReportId}")
    @Timed
    public ResponseEntity<Boolean> completeLevel(@PathVariable Long finalNiazsanjiReportId) throws Exception {

        if (finalNiazsanjiReportId == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        Boolean result = niazsanjiPersonGradeService.completeLevel(finalNiazsanjiReportId);
        return ResponseEntity.ok().body(result);
    }
    /**
     * GET  /niazsanji-person-grades : get all the niazsanjiPersonGrades.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of niazsanjiPersonGrades in body
     */
    @GetMapping("/niazsanji-person-grades")
    @Timed
    public ResponseEntity<List<NiazsanjiPersonGradeDTO>> getAllNiazsanjiPersonGrades(NiazsanjiPersonGradeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get NiazsanjiPersonGrades by criteria: {}", criteria);
        Page<NiazsanjiPersonGradeDTO> page = niazsanjiPersonGradeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/niazsanji-person-grades");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    @GetMapping("/niazsanji-person-grades/criteria-chart/{finalNiazsanjiReportId}")
    @Timed
    public ResponseEntity<List<EffectivenessPhasePerCriteriaData>> getAllNiazsanjiPersonGrades(@PathVariable Long finalNiazsanjiReportId) throws Exception {
        log.debug("REST request to get NiazsanjiPersonGrades by criteria: {}", finalNiazsanjiReportId);

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

        NiazsanjiPersonGradeCriteria criteria = new NiazsanjiPersonGradeCriteria();
        criteria.setFinalNiazsanjiReportPersonId(finalNiazsanjiReportPersonIdsFilter);

        List<NiazsanjiPersonGradeDTO> niazsanjiPersonGrades = niazsanjiPersonGradeQueryService.findByCriteria(criteria);

        if(niazsanjiPersonGrades.isEmpty())
            throw new Exception("niazsanjiPersonGrade is empty");

        List<Long> niazsanjiPersonGradeIds = niazsanjiPersonGrades.stream().mapToLong(a -> a.getId()).boxed().collect(Collectors.toList());

        NiazsanjiPersonCriteriaCriteria niazsanjiPersonCriteriaCriteria = new NiazsanjiPersonCriteriaCriteria();
        List<NiazsanjiPersonCriteriaDTO> niazsanjiPersonCriteriaList = niazsanjiPersonCriteriaQueryService.findByCriteria(niazsanjiPersonCriteriaCriteria);

        if(niazsanjiPersonCriteriaList.isEmpty())
            throw new Exception("niazsanjiPersonCriteria is empty");

        List<EffectivenessPhasePerCriteriaData> effectivenessPhasePerCriteriaDataList = new ArrayList<>();
        for (NiazsanjiPersonCriteriaDTO niazsanjiPersonCriteriaDTO : niazsanjiPersonCriteriaList) {

            EffectivenessPhasePerCriteriaData effectivenessPhasePerCriteriaData = new EffectivenessPhasePerCriteriaData();

            NiazsanjiPersonGradeScoreCriteria niazsanjiPersonGradeScoreCriteria = new NiazsanjiPersonGradeScoreCriteria();
            LongFilter niazsanjiPersonCriteriaIdFilter = new LongFilter();
            niazsanjiPersonCriteriaIdFilter.setEquals(niazsanjiPersonCriteriaDTO.getId());

            LongFilter niazsanjiPersonGradeFilter = new LongFilter();
            niazsanjiPersonGradeFilter.setIn(niazsanjiPersonGradeIds);

            niazsanjiPersonGradeScoreCriteria.setNiazsanjiPersonCriteriaId(niazsanjiPersonCriteriaIdFilter);
            niazsanjiPersonGradeScoreCriteria.setNiazsanjiPersonGradeId(niazsanjiPersonGradeFilter);

            List<NiazsanjiPersonGradeScoreDTO> niazsanjiPersonGradeScoreDTOList = niazsanjiPersonGradeScoreQueryService.findByCriteria(niazsanjiPersonGradeScoreCriteria);

            effectivenessPhasePerCriteriaData.setCriteria(niazsanjiPersonCriteriaDTO);

            Long sumValue = niazsanjiPersonGradeScoreDTOList.stream().mapToLong(a -> a.getScore()).sum();
            effectivenessPhasePerCriteriaData.setSumValue(sumValue);
            effectivenessPhasePerCriteriaDataList.add(effectivenessPhasePerCriteriaData);
        }

        return ResponseEntity.ok().body(effectivenessPhasePerCriteriaDataList);
    }

    /**
    * GET  /niazsanji-person-grades/count : count all the niazsanjiPersonGrades.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/niazsanji-person-grades/count")
    @Timed
    public ResponseEntity<Long> countNiazsanjiPersonGrades(NiazsanjiPersonGradeCriteria criteria) {
        log.debug("REST request to count NiazsanjiPersonGrades by criteria: {}", criteria);
        return ResponseEntity.ok().body(niazsanjiPersonGradeQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /niazsanji-person-grades/:id : get the "id" niazsanjiPersonGrade.
     *
     * @param id the id of the niazsanjiPersonGradeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the niazsanjiPersonGradeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/niazsanji-person-grades/{id}")
    @Timed
    public ResponseEntity<NiazsanjiPersonGradeDTO> getNiazsanjiPersonGrade(@PathVariable Long id) {
        log.debug("REST request to get NiazsanjiPersonGrade : {}", id);
        Optional<NiazsanjiPersonGradeDTO> niazsanjiPersonGradeDTO = niazsanjiPersonGradeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(niazsanjiPersonGradeDTO);
    }

    /**
     * DELETE  /niazsanji-person-grades/:id : delete the "id" niazsanjiPersonGrade.
     *
     * @param id the id of the niazsanjiPersonGradeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/niazsanji-person-grades/{id}")
    @Timed
    public ResponseEntity<Void> deleteNiazsanjiPersonGrade(@PathVariable Long id) {
        log.debug("REST request to delete NiazsanjiPersonGrade : {}", id);
        niazsanjiPersonGradeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
