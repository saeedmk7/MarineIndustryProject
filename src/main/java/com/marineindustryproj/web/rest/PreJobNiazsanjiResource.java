package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.domain.enumeration.NiazSanjiSource;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.ActivationNiazsanjiQueryService;
import com.marineindustryproj.service.PreJobNiazsanjiCompetencyService;
import com.marineindustryproj.service.PreJobNiazsanjiService;
import com.marineindustryproj.service.dto.PreJobNiazsanjiCompetencyDTO;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.PreJobNiazsanjiDTO;
import com.marineindustryproj.service.dto.PreJobNiazsanjiCriteria;
import com.marineindustryproj.service.PreJobNiazsanjiQueryService;
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
import java.util.UUID;

/**
 * REST controller for managing PreJobNiazsanji.
 */
@RestController
@RequestMapping("/api")
public class PreJobNiazsanjiResource {

    private final Logger log = LoggerFactory.getLogger(PreJobNiazsanjiResource.class);

    private static final String ENTITY_NAME = "preJobNiazsanji";

    private final PreJobNiazsanjiService preJobNiazsanjiService;

    private final PreJobNiazsanjiQueryService preJobNiazsanjiQueryService;

    private final PreJobNiazsanjiCompetencyService preJobNiazsanjiCompetencyService;

    private final ActivationNiazsanjiQueryService activationNiazsanjiQueryService;

    public PreJobNiazsanjiResource(PreJobNiazsanjiService preJobNiazsanjiService, PreJobNiazsanjiQueryService preJobNiazsanjiQueryService, PreJobNiazsanjiCompetencyService preJobNiazsanjiCompetencyService, ActivationNiazsanjiQueryService activationNiazsanjiQueryService) {
        this.preJobNiazsanjiService = preJobNiazsanjiService;
        this.preJobNiazsanjiQueryService = preJobNiazsanjiQueryService;
        this.preJobNiazsanjiCompetencyService = preJobNiazsanjiCompetencyService;
        this.activationNiazsanjiQueryService = activationNiazsanjiQueryService;
    }

    /**
     * POST  /pre-job-niazsanjis : Create a new preJobNiazsanji.
     *
     * @param preJobNiazsanjiDTO the preJobNiazsanjiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new preJobNiazsanjiDTO, or with status 400 (Bad Request) if the preJobNiazsanji has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pre-job-niazsanjis")
    @Timed
    public ResponseEntity<PreJobNiazsanjiDTO> createPreJobNiazsanji(@Valid @RequestBody PreJobNiazsanjiDTO preJobNiazsanjiDTO) throws URISyntaxException {
        log.debug("REST request to save PreJobNiazsanji : {}", preJobNiazsanjiDTO);
        if (preJobNiazsanjiDTO.getId() != null) {
            throw new BadRequestAlertException("A new preJobNiazsanji cannot already have an ID", ENTITY_NAME, "idexists");
        }

        if(!activationNiazsanjiQueryService.niazsanjiIsActive(NiazSanjiSource.JOB))
            throw new BadRequestAlertException("زمان ثبت نیازسنجی جدید به اتمام رسیده است", ENTITY_NAME, "finishTime");

        preJobNiazsanjiDTO.setCreateDate(ZonedDateTime.now());
        preJobNiazsanjiDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        preJobNiazsanjiDTO.setModifyDate(ZonedDateTime.now());
        preJobNiazsanjiDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        preJobNiazsanjiDTO.setGuid(UUID.randomUUID().toString());
        PreJobNiazsanjiDTO result = preJobNiazsanjiService.save(preJobNiazsanjiDTO);

        for (PreJobNiazsanjiCompetencyDTO preJobNiazsanjiCompetency : preJobNiazsanjiDTO.getPreJobNiazsanjiCompetencies()) {
            preJobNiazsanjiCompetency.setCreateDate(ZonedDateTime.now());
            preJobNiazsanjiCompetency.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
            preJobNiazsanjiCompetency.setPreJobNiazsanjiId(result.getId());
            preJobNiazsanjiCompetency.setNeedToImprove(0);
            preJobNiazsanjiCompetency.setSelected(false);
            preJobNiazsanjiCompetencyService.save(preJobNiazsanjiCompetency);
        }
        return ResponseEntity.created(new URI("/api/pre-job-niazsanjis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    @PostMapping("/pre-job-niazsanjis/finalize")
    @Timed
    public ResponseEntity<PreJobNiazsanjiDTO> finalizePreJobNiazsanji(@Valid @RequestBody PreJobNiazsanjiDTO preJobNiazsanjiDTO) throws Exception {
        log.debug("REST request to save PreJobNiazsanji : {}", preJobNiazsanjiDTO);
        if (preJobNiazsanjiDTO.getId() == null) {
            throw new BadRequestAlertException("preJobNiazsanji do not already have an ID", ENTITY_NAME, "idexists");
        }
        preJobNiazsanjiDTO.setModifyDate(ZonedDateTime.now());
        preJobNiazsanjiDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());


        PreJobNiazsanjiDTO result = preJobNiazsanjiService.finalize(preJobNiazsanjiDTO);


        return ResponseEntity.created(new URI("/api/pre-job-niazsanjis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pre-job-niazsanjis : Updates an existing preJobNiazsanji.
     *
     * @param preJobNiazsanjiDTO the preJobNiazsanjiDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated preJobNiazsanjiDTO,
     * or with status 400 (Bad Request) if the preJobNiazsanjiDTO is not valid,
     * or with status 500 (Internal Server Error) if the preJobNiazsanjiDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pre-job-niazsanjis")
    @Timed
    public ResponseEntity<PreJobNiazsanjiDTO> updatePreJobNiazsanji(@Valid @RequestBody PreJobNiazsanjiDTO preJobNiazsanjiDTO) throws URISyntaxException {
        log.debug("REST request to update PreJobNiazsanji : {}", preJobNiazsanjiDTO);
        if (preJobNiazsanjiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PreJobNiazsanjiDTO preJobNiazsanji = preJobNiazsanjiService.findOne(preJobNiazsanjiDTO.getId()).get();

        preJobNiazsanjiDTO.setCreateUserLogin(preJobNiazsanji.getCreateUserLogin());
        preJobNiazsanjiDTO.setCreateDate(preJobNiazsanji.getCreateDate());
        preJobNiazsanjiDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        preJobNiazsanjiDTO.setModifyDate(ZonedDateTime.now());
        preJobNiazsanjiDTO.setGuid(preJobNiazsanji.getGuid());
        PreJobNiazsanjiDTO result = preJobNiazsanjiService.save(preJobNiazsanjiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, preJobNiazsanjiDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pre-job-niazsanjis : get all the preJobNiazsanjis.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of preJobNiazsanjis in body
     */
    @GetMapping("/pre-job-niazsanjis")
    @Timed
    public ResponseEntity<List<PreJobNiazsanjiDTO>> getAllPreJobNiazsanjis(PreJobNiazsanjiCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PreJobNiazsanjis by criteria: {}", criteria);
        Page<PreJobNiazsanjiDTO> page = preJobNiazsanjiQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pre-job-niazsanjis");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PutMapping("/pre-job-niazsanjis/toggleImportantMessage/{id}/{type}")
    @Timed
    public ResponseEntity<PreJobNiazsanjiDTO> toggleImportantMessage(@PathVariable long id, @PathVariable boolean type) throws URISyntaxException {

        PreJobNiazsanjiDTO preJobNiazsanji = preJobNiazsanjiService.findOne(id).get();

        preJobNiazsanji.setHasImportantMessage(type);
        preJobNiazsanji.setModifyDate(ZonedDateTime.now());
        //preJobNiazsanjiDTO.setChangeStatusUserLogin(SecurityUtils.getCurrentUserLogin().get());

        PreJobNiazsanjiDTO result = preJobNiazsanjiService.save(preJobNiazsanji);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, preJobNiazsanji.getId().toString()))
            .body(result);
    }

    /**
    * GET  /pre-job-niazsanjis/count : count all the preJobNiazsanjis.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/pre-job-niazsanjis/count")
    @Timed
    public ResponseEntity<Long> countPreJobNiazsanjis(PreJobNiazsanjiCriteria criteria) {
        log.debug("REST request to count PreJobNiazsanjis by criteria: {}", criteria);
        return ResponseEntity.ok().body(preJobNiazsanjiQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /pre-job-niazsanjis/:id : get the "id" preJobNiazsanji.
     *
     * @param id the id of the preJobNiazsanjiDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the preJobNiazsanjiDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pre-job-niazsanjis/{id}")
    @Timed
    public ResponseEntity<PreJobNiazsanjiDTO> getPreJobNiazsanji(@PathVariable Long id) {
        log.debug("REST request to get PreJobNiazsanji : {}", id);
        Optional<PreJobNiazsanjiDTO> preJobNiazsanjiDTO = preJobNiazsanjiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(preJobNiazsanjiDTO);
    }

    /**
     * DELETE  /pre-job-niazsanjis/:id : delete the "id" preJobNiazsanji.
     *
     * @param id the id of the preJobNiazsanjiDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pre-job-niazsanjis/{id}")
    @Timed
    public ResponseEntity<Void> deletePreJobNiazsanji(@PathVariable Long id) {
        log.debug("REST request to delete PreJobNiazsanji : {}", id);
        preJobNiazsanjiService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
