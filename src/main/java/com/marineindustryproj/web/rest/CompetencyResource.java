package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.CompetencyService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.CompetencyDTO;
import com.marineindustryproj.service.dto.CompetencyCriteria;
import com.marineindustryproj.service.CompetencyQueryService;
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
 * REST controller for managing Competency.
 */
@RestController
@RequestMapping("/api")
public class CompetencyResource {

    private final Logger log = LoggerFactory.getLogger(CompetencyResource.class);

    private static final String ENTITY_NAME = "competency";

    private final CompetencyService competencyService;

    private final CompetencyQueryService competencyQueryService;

    public CompetencyResource(CompetencyService competencyService, CompetencyQueryService competencyQueryService) {
        this.competencyService = competencyService;
        this.competencyQueryService = competencyQueryService;
    }

    /**
     * POST  /competencies : Create a new competency.
     *
     * @param competencyDTO the competencyDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new competencyDTO, or with status 400 (Bad Request) if the competency has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/competencies")
    @Timed
    public ResponseEntity<CompetencyDTO> createCompetency(@Valid @RequestBody CompetencyDTO competencyDTO) throws URISyntaxException {
        log.debug("REST request to save Competency : {}", competencyDTO);
        if (competencyDTO.getId() != null) {
            throw new BadRequestAlertException("A new competency cannot already have an ID", ENTITY_NAME, "idexists");
        }
        competencyDTO.setCreateDate(ZonedDateTime.now());
        competencyDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        CompetencyDTO result = competencyService.save(competencyDTO);
        return ResponseEntity.created(new URI("/api/competencies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /competencies : Updates an existing competency.
     *
     * @param competencyDTO the competencyDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated competencyDTO,
     * or with status 400 (Bad Request) if the competencyDTO is not valid,
     * or with status 500 (Internal Server Error) if the competencyDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/competencies")
    @Timed
    public ResponseEntity<CompetencyDTO> updateCompetency(@Valid @RequestBody CompetencyDTO competencyDTO) throws URISyntaxException {
        log.debug("REST request to update Competency : {}", competencyDTO);
        if (competencyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CompetencyDTO competency = competencyService.findOne(competencyDTO.getId()).get();

        competencyDTO.setCreateUserLogin(competency.getCreateUserLogin());
        competencyDTO.setCreateDate(competency.getCreateDate());
        competencyDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        competencyDTO.setModifyDate(ZonedDateTime.now());
        CompetencyDTO result = competencyService.save(competencyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, competencyDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /competencies : get all the competencies.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of competencies in body
     */
    @GetMapping("/competencies")
    @Timed
    public ResponseEntity<List<CompetencyDTO>> getAllCompetencies(CompetencyCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Competencies by criteria: {}", criteria);
        Page<CompetencyDTO> page = competencyQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/competencies");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /competencies/count : count all the competencies.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/competencies/count")
    @Timed
    public ResponseEntity<Long> countCompetencies(CompetencyCriteria criteria) {
        log.debug("REST request to count Competencies by criteria: {}", criteria);
        return ResponseEntity.ok().body(competencyQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /competencies/:id : get the "id" competency.
     *
     * @param id the id of the competencyDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the competencyDTO, or with status 404 (Not Found)
     */
    @GetMapping("/competencies/{id}")
    @Timed
    public ResponseEntity<CompetencyDTO> getCompetency(@PathVariable Long id) {
        log.debug("REST request to get Competency : {}", id);
        Optional<CompetencyDTO> competencyDTO = competencyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(competencyDTO);
    }

    /**
     * DELETE  /competencies/:id : delete the "id" competency.
     *
     * @param id the id of the competencyDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/competencies/{id}")
    @Timed
    public ResponseEntity<Void> deleteCompetency(@PathVariable Long id) {
        log.debug("REST request to delete Competency : {}", id);
        competencyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
