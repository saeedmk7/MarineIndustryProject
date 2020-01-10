package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.PreJobNiazsanjiCompetencyService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.PreJobNiazsanjiCompetencyDTO;
import com.marineindustryproj.service.dto.PreJobNiazsanjiCompetencyCriteria;
import com.marineindustryproj.service.PreJobNiazsanjiCompetencyQueryService;
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
 * REST controller for managing PreJobNiazsanjiCompetency.
 */
@RestController
@RequestMapping("/api")
public class PreJobNiazsanjiCompetencyResource {

    private final Logger log = LoggerFactory.getLogger(PreJobNiazsanjiCompetencyResource.class);

    private static final String ENTITY_NAME = "preJobNiazsanjiCompetency";

    private final PreJobNiazsanjiCompetencyService preJobNiazsanjiCompetencyService;

    private final PreJobNiazsanjiCompetencyQueryService preJobNiazsanjiCompetencyQueryService;

    public PreJobNiazsanjiCompetencyResource(PreJobNiazsanjiCompetencyService preJobNiazsanjiCompetencyService, PreJobNiazsanjiCompetencyQueryService preJobNiazsanjiCompetencyQueryService) {
        this.preJobNiazsanjiCompetencyService = preJobNiazsanjiCompetencyService;
        this.preJobNiazsanjiCompetencyQueryService = preJobNiazsanjiCompetencyQueryService;
    }

    /**
     * POST  /pre-job-niazsanji-competencies : Create a new preJobNiazsanjiCompetency.
     *
     * @param preJobNiazsanjiCompetencyDTO the preJobNiazsanjiCompetencyDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new preJobNiazsanjiCompetencyDTO, or with status 400 (Bad Request) if the preJobNiazsanjiCompetency has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pre-job-niazsanji-competencies")
    @Timed
    public ResponseEntity<PreJobNiazsanjiCompetencyDTO> createPreJobNiazsanjiCompetency(@Valid @RequestBody PreJobNiazsanjiCompetencyDTO preJobNiazsanjiCompetencyDTO) throws URISyntaxException {
        log.debug("REST request to save PreJobNiazsanjiCompetency : {}", preJobNiazsanjiCompetencyDTO);
        if (preJobNiazsanjiCompetencyDTO.getId() != null) {
            throw new BadRequestAlertException("A new preJobNiazsanjiCompetency cannot already have an ID", ENTITY_NAME, "idexists");
        }
        preJobNiazsanjiCompetencyDTO.setCreateDate(ZonedDateTime.now());
        preJobNiazsanjiCompetencyDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        PreJobNiazsanjiCompetencyDTO result = preJobNiazsanjiCompetencyService.save(preJobNiazsanjiCompetencyDTO);
        return ResponseEntity.created(new URI("/api/pre-job-niazsanji-competencies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pre-job-niazsanji-competencies : Updates an existing preJobNiazsanjiCompetency.
     *
     * @param preJobNiazsanjiCompetencyDTO the preJobNiazsanjiCompetencyDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated preJobNiazsanjiCompetencyDTO,
     * or with status 400 (Bad Request) if the preJobNiazsanjiCompetencyDTO is not valid,
     * or with status 500 (Internal Server Error) if the preJobNiazsanjiCompetencyDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pre-job-niazsanji-competencies")
    @Timed
    public ResponseEntity<PreJobNiazsanjiCompetencyDTO> updatePreJobNiazsanjiCompetency(@Valid @RequestBody PreJobNiazsanjiCompetencyDTO preJobNiazsanjiCompetencyDTO) throws URISyntaxException {
        log.debug("REST request to update PreJobNiazsanjiCompetency : {}", preJobNiazsanjiCompetencyDTO);
        if (preJobNiazsanjiCompetencyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PreJobNiazsanjiCompetencyDTO preJobNiazsanjiCompetency = preJobNiazsanjiCompetencyService.findOne(preJobNiazsanjiCompetencyDTO.getId()).get();

        preJobNiazsanjiCompetencyDTO.setCreateUserLogin(preJobNiazsanjiCompetency.getCreateUserLogin());
        preJobNiazsanjiCompetencyDTO.setCreateDate(preJobNiazsanjiCompetency.getCreateDate());
        preJobNiazsanjiCompetencyDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        preJobNiazsanjiCompetencyDTO.setModifyDate(ZonedDateTime.now());
        PreJobNiazsanjiCompetencyDTO result = preJobNiazsanjiCompetencyService.save(preJobNiazsanjiCompetencyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, preJobNiazsanjiCompetencyDTO.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pre-job-niazsanji-competencies : Updates an existing preJobNiazsanjiCompetency.
     *
     * @param preJobNiazsanjiCompetencyDTO the preJobNiazsanjiCompetencyDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated preJobNiazsanjiCompetencyDTO,
     * or with status 400 (Bad Request) if the preJobNiazsanjiCompetencyDTO is not valid,
     * or with status 500 (Internal Server Error) if the preJobNiazsanjiCompetencyDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pre-job-niazsanji-competencies/bulk")
    @Timed
    public ResponseEntity<PreJobNiazsanjiCompetencyDTO> updatePreJobNiazsanjiCompetencyBulk(@Valid @RequestBody PreJobNiazsanjiCompetencyDTO[] preJobNiazsanjiCompetencyDTO) throws URISyntaxException {

        PreJobNiazsanjiCompetencyDTO result = preJobNiazsanjiCompetencyService.saveBulk(preJobNiazsanjiCompetencyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pre-job-niazsanji-competencies : get all the preJobNiazsanjiCompetencies.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of preJobNiazsanjiCompetencies in body
     */
    @GetMapping("/pre-job-niazsanji-competencies")
    @Timed
    public ResponseEntity<List<PreJobNiazsanjiCompetencyDTO>> getAllPreJobNiazsanjiCompetencies(PreJobNiazsanjiCompetencyCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PreJobNiazsanjiCompetencies by criteria: {}", criteria);
        Page<PreJobNiazsanjiCompetencyDTO> page = preJobNiazsanjiCompetencyQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pre-job-niazsanji-competencies");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /pre-job-niazsanji-competencies/count : count all the preJobNiazsanjiCompetencies.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/pre-job-niazsanji-competencies/count")
    @Timed
    public ResponseEntity<Long> countPreJobNiazsanjiCompetencies(PreJobNiazsanjiCompetencyCriteria criteria) {
        log.debug("REST request to count PreJobNiazsanjiCompetencies by criteria: {}", criteria);
        return ResponseEntity.ok().body(preJobNiazsanjiCompetencyQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /pre-job-niazsanji-competencies/:id : get the "id" preJobNiazsanjiCompetency.
     *
     * @param id the id of the preJobNiazsanjiCompetencyDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the preJobNiazsanjiCompetencyDTO, or with status 404 (Not Found)
     */
    @GetMapping("/pre-job-niazsanji-competencies/{id}")
    @Timed
    public ResponseEntity<PreJobNiazsanjiCompetencyDTO> getPreJobNiazsanjiCompetency(@PathVariable Long id) {
        log.debug("REST request to get PreJobNiazsanjiCompetency : {}", id);
        Optional<PreJobNiazsanjiCompetencyDTO> preJobNiazsanjiCompetencyDTO = preJobNiazsanjiCompetencyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(preJobNiazsanjiCompetencyDTO);
    }

    /**
     * DELETE  /pre-job-niazsanji-competencies/:id : delete the "id" preJobNiazsanjiCompetency.
     *
     * @param id the id of the preJobNiazsanjiCompetencyDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pre-job-niazsanji-competencies/{id}")
    @Timed
    public ResponseEntity<Void> deletePreJobNiazsanjiCompetency(@PathVariable Long id) {
        log.debug("REST request to delete PreJobNiazsanjiCompetency : {}", id);
        preJobNiazsanjiCompetencyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
