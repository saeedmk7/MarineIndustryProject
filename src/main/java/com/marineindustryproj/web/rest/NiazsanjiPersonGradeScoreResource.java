package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.NiazsanjiPersonGradeScoreService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.NiazsanjiPersonGradeScoreDTO;
import com.marineindustryproj.service.dto.NiazsanjiPersonGradeScoreCriteria;
import com.marineindustryproj.service.NiazsanjiPersonGradeScoreQueryService;
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

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing NiazsanjiPersonGradeScore.
 */
@RestController
@RequestMapping("/api")
public class NiazsanjiPersonGradeScoreResource {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiPersonGradeScoreResource.class);

    private static final String ENTITY_NAME = "niazsanjiPersonGradeScore";

    private final NiazsanjiPersonGradeScoreService niazsanjiPersonGradeScoreService;

    private final NiazsanjiPersonGradeScoreQueryService niazsanjiPersonGradeScoreQueryService;

    public NiazsanjiPersonGradeScoreResource(NiazsanjiPersonGradeScoreService niazsanjiPersonGradeScoreService, NiazsanjiPersonGradeScoreQueryService niazsanjiPersonGradeScoreQueryService) {
        this.niazsanjiPersonGradeScoreService = niazsanjiPersonGradeScoreService;
        this.niazsanjiPersonGradeScoreQueryService = niazsanjiPersonGradeScoreQueryService;
    }

    /**
     * POST  /niazsanji-person-grade-scores : Create a new niazsanjiPersonGradeScore.
     *
     * @param niazsanjiPersonGradeScoreDTO the niazsanjiPersonGradeScoreDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new niazsanjiPersonGradeScoreDTO, or with status 400 (Bad Request) if the niazsanjiPersonGradeScore has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/niazsanji-person-grade-scores")
    @Timed
    public ResponseEntity<NiazsanjiPersonGradeScoreDTO> createNiazsanjiPersonGradeScore(@Valid @RequestBody NiazsanjiPersonGradeScoreDTO niazsanjiPersonGradeScoreDTO) throws URISyntaxException {
        log.debug("REST request to save NiazsanjiPersonGradeScore : {}", niazsanjiPersonGradeScoreDTO);
        if (niazsanjiPersonGradeScoreDTO.getId() != null) {
            throw new BadRequestAlertException("A new niazsanjiPersonGradeScore cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NiazsanjiPersonGradeScoreDTO result = niazsanjiPersonGradeScoreService.save(niazsanjiPersonGradeScoreDTO);
        return ResponseEntity.created(new URI("/api/niazsanji-person-grade-scores/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /niazsanji-person-grade-scores : Updates an existing niazsanjiPersonGradeScore.
     *
     * @param niazsanjiPersonGradeScoreDTO the niazsanjiPersonGradeScoreDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated niazsanjiPersonGradeScoreDTO,
     * or with status 400 (Bad Request) if the niazsanjiPersonGradeScoreDTO is not valid,
     * or with status 500 (Internal Server Error) if the niazsanjiPersonGradeScoreDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/niazsanji-person-grade-scores")
    @Timed
    public ResponseEntity<NiazsanjiPersonGradeScoreDTO> updateNiazsanjiPersonGradeScore(@Valid @RequestBody NiazsanjiPersonGradeScoreDTO niazsanjiPersonGradeScoreDTO) throws URISyntaxException {
        log.debug("REST request to update NiazsanjiPersonGradeScore : {}", niazsanjiPersonGradeScoreDTO);
        if (niazsanjiPersonGradeScoreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NiazsanjiPersonGradeScoreDTO result = niazsanjiPersonGradeScoreService.save(niazsanjiPersonGradeScoreDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, niazsanjiPersonGradeScoreDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /niazsanji-person-grade-scores : get all the niazsanjiPersonGradeScores.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of niazsanjiPersonGradeScores in body
     */
    @GetMapping("/niazsanji-person-grade-scores")
    @Timed
    public ResponseEntity<List<NiazsanjiPersonGradeScoreDTO>> getAllNiazsanjiPersonGradeScores(NiazsanjiPersonGradeScoreCriteria criteria, Pageable pageable) {
        log.debug("REST request to get NiazsanjiPersonGradeScores by criteria: {}", criteria);
        Page<NiazsanjiPersonGradeScoreDTO> page = niazsanjiPersonGradeScoreQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/niazsanji-person-grade-scores");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /niazsanji-person-grade-scores/count : count all the niazsanjiPersonGradeScores.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/niazsanji-person-grade-scores/count")
    @Timed
    public ResponseEntity<Long> countNiazsanjiPersonGradeScores(NiazsanjiPersonGradeScoreCriteria criteria) {
        log.debug("REST request to count NiazsanjiPersonGradeScores by criteria: {}", criteria);
        return ResponseEntity.ok().body(niazsanjiPersonGradeScoreQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /niazsanji-person-grade-scores/:id : get the "id" niazsanjiPersonGradeScore.
     *
     * @param id the id of the niazsanjiPersonGradeScoreDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the niazsanjiPersonGradeScoreDTO, or with status 404 (Not Found)
     */
    @GetMapping("/niazsanji-person-grade-scores/{id}")
    @Timed
    public ResponseEntity<NiazsanjiPersonGradeScoreDTO> getNiazsanjiPersonGradeScore(@PathVariable Long id) {
        log.debug("REST request to get NiazsanjiPersonGradeScore : {}", id);
        Optional<NiazsanjiPersonGradeScoreDTO> niazsanjiPersonGradeScoreDTO = niazsanjiPersonGradeScoreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(niazsanjiPersonGradeScoreDTO);
    }

    /**
     * DELETE  /niazsanji-person-grade-scores/:id : delete the "id" niazsanjiPersonGradeScore.
     *
     * @param id the id of the niazsanjiPersonGradeScoreDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/niazsanji-person-grade-scores/{id}")
    @Timed
    public ResponseEntity<Void> deleteNiazsanjiPersonGradeScore(@PathVariable Long id) {
        log.debug("REST request to delete NiazsanjiPersonGradeScore : {}", id);
        niazsanjiPersonGradeScoreService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
