package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.FixCompetencyDeficiencyService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.FixCompetencyDeficiencyDTO;
import com.marineindustryproj.service.dto.FixCompetencyDeficiencyCriteria;
import com.marineindustryproj.service.FixCompetencyDeficiencyQueryService;
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
 * REST controller for managing FixCompetencyDeficiency.
 */
@RestController
@RequestMapping("/api")
public class FixCompetencyDeficiencyResource {

    private final Logger log = LoggerFactory.getLogger(FixCompetencyDeficiencyResource.class);

    private static final String ENTITY_NAME = "fixCompetencyDeficiency";

    private final FixCompetencyDeficiencyService fixCompetencyDeficiencyService;

    private final FixCompetencyDeficiencyQueryService fixCompetencyDeficiencyQueryService;

    public FixCompetencyDeficiencyResource(FixCompetencyDeficiencyService fixCompetencyDeficiencyService, FixCompetencyDeficiencyQueryService fixCompetencyDeficiencyQueryService) {
        this.fixCompetencyDeficiencyService = fixCompetencyDeficiencyService;
        this.fixCompetencyDeficiencyQueryService = fixCompetencyDeficiencyQueryService;
    }

    /**
     * POST  /fix-competency-deficiencies : Create a new fixCompetencyDeficiency.
     *
     * @param fixCompetencyDeficiencyDTO the fixCompetencyDeficiencyDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fixCompetencyDeficiencyDTO, or with status 400 (Bad Request) if the fixCompetencyDeficiency has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fix-competency-deficiencies")
    @Timed
    public ResponseEntity<FixCompetencyDeficiencyDTO> createFixCompetencyDeficiency(@Valid @RequestBody FixCompetencyDeficiencyDTO fixCompetencyDeficiencyDTO) throws URISyntaxException {
        log.debug("REST request to save FixCompetencyDeficiency : {}", fixCompetencyDeficiencyDTO);
        if (fixCompetencyDeficiencyDTO.getId() != null) {
            throw new BadRequestAlertException("A new fixCompetencyDeficiency cannot already have an ID", ENTITY_NAME, "idexists");
        }
        fixCompetencyDeficiencyDTO.setCreateDate(ZonedDateTime.now());
        fixCompetencyDeficiencyDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        FixCompetencyDeficiencyDTO result = fixCompetencyDeficiencyService.save(fixCompetencyDeficiencyDTO);
        return ResponseEntity.created(new URI("/api/fix-competency-deficiencies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fix-competency-deficiencies : Updates an existing fixCompetencyDeficiency.
     *
     * @param fixCompetencyDeficiencyDTO the fixCompetencyDeficiencyDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fixCompetencyDeficiencyDTO,
     * or with status 400 (Bad Request) if the fixCompetencyDeficiencyDTO is not valid,
     * or with status 500 (Internal Server Error) if the fixCompetencyDeficiencyDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fix-competency-deficiencies")
    @Timed
    public ResponseEntity<FixCompetencyDeficiencyDTO> updateFixCompetencyDeficiency(@Valid @RequestBody FixCompetencyDeficiencyDTO fixCompetencyDeficiencyDTO) throws URISyntaxException {
        log.debug("REST request to update FixCompetencyDeficiency : {}", fixCompetencyDeficiencyDTO);
        if (fixCompetencyDeficiencyDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FixCompetencyDeficiencyDTO fixCompetencyDeficiency = fixCompetencyDeficiencyService.findOne(fixCompetencyDeficiencyDTO.getId()).get();

        fixCompetencyDeficiencyDTO.setCreateUserLogin(fixCompetencyDeficiency.getCreateUserLogin());
        fixCompetencyDeficiencyDTO.setCreateDate(fixCompetencyDeficiency.getCreateDate());
        fixCompetencyDeficiencyDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        fixCompetencyDeficiencyDTO.setModifyDate(ZonedDateTime.now());
        FixCompetencyDeficiencyDTO result = fixCompetencyDeficiencyService.save(fixCompetencyDeficiencyDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fixCompetencyDeficiencyDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fix-competency-deficiencies : get all the fixCompetencyDeficiencies.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of fixCompetencyDeficiencies in body
     */
    @GetMapping("/fix-competency-deficiencies")
    @Timed
    public ResponseEntity<List<FixCompetencyDeficiencyDTO>> getAllFixCompetencyDeficiencies(FixCompetencyDeficiencyCriteria criteria, Pageable pageable) {
        log.debug("REST request to get FixCompetencyDeficiencies by criteria: {}", criteria);
        Page<FixCompetencyDeficiencyDTO> page = fixCompetencyDeficiencyQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fix-competency-deficiencies");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /fix-competency-deficiencies/count : count all the fixCompetencyDeficiencies.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/fix-competency-deficiencies/count")
    @Timed
    public ResponseEntity<Long> countFixCompetencyDeficiencies(FixCompetencyDeficiencyCriteria criteria) {
        log.debug("REST request to count FixCompetencyDeficiencies by criteria: {}", criteria);
        return ResponseEntity.ok().body(fixCompetencyDeficiencyQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /fix-competency-deficiencies/:id : get the "id" fixCompetencyDeficiency.
     *
     * @param id the id of the fixCompetencyDeficiencyDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fixCompetencyDeficiencyDTO, or with status 404 (Not Found)
     */
    @GetMapping("/fix-competency-deficiencies/{id}")
    @Timed
    public ResponseEntity<FixCompetencyDeficiencyDTO> getFixCompetencyDeficiency(@PathVariable Long id) {
        log.debug("REST request to get FixCompetencyDeficiency : {}", id);
        Optional<FixCompetencyDeficiencyDTO> fixCompetencyDeficiencyDTO = fixCompetencyDeficiencyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fixCompetencyDeficiencyDTO);
    }

    /**
     * DELETE  /fix-competency-deficiencies/:id : delete the "id" fixCompetencyDeficiency.
     *
     * @param id the id of the fixCompetencyDeficiencyDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fix-competency-deficiencies/{id}")
    @Timed
    public ResponseEntity<Void> deleteFixCompetencyDeficiency(@PathVariable Long id) {
        log.debug("REST request to delete FixCompetencyDeficiency : {}", id);
        fixCompetencyDeficiencyService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
