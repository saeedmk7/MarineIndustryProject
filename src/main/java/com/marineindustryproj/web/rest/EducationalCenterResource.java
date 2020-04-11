package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.EducationalCenterGradeScoreQueryService;
import com.marineindustryproj.service.EducationalCenterGradeScoreService;
import com.marineindustryproj.service.EducationalCenterService;
import com.marineindustryproj.service.dto.EducationalCenterGradeScoreCriteria;
import com.marineindustryproj.service.dto.EducationalCenterGradeScoreDTO;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.EducationalCenterDTO;
import com.marineindustryproj.service.dto.EducationalCenterCriteria;
import com.marineindustryproj.service.EducationalCenterQueryService;
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
 * REST controller for managing EducationalCenter.
 */
@RestController
@RequestMapping("/api")
public class EducationalCenterResource {

    private final Logger log = LoggerFactory.getLogger(EducationalCenterResource.class);

    private static final String ENTITY_NAME = "educationalCenter";

    private final EducationalCenterService educationalCenterService;

    private final EducationalCenterQueryService educationalCenterQueryService;

    public EducationalCenterResource(EducationalCenterService educationalCenterService, EducationalCenterQueryService educationalCenterQueryService) {
        this.educationalCenterService = educationalCenterService;
        this.educationalCenterQueryService = educationalCenterQueryService;
    }

    /**
     * POST  /educational-centers : Create a new educationalCenter.
     *
     * @param educationalCenterDTO the educationalCenterDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new educationalCenterDTO, or with status 400 (Bad Request) if the educationalCenter has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/educational-centers")
    @Timed
    public ResponseEntity<EducationalCenterDTO> createEducationalCenter(@Valid @RequestBody EducationalCenterDTO educationalCenterDTO) throws URISyntaxException {
        log.debug("REST request to save EducationalCenter : {}", educationalCenterDTO);
        if (educationalCenterDTO.getId() != null) {
            throw new BadRequestAlertException("A new educationalCenter cannot already have an ID", ENTITY_NAME, "idexists");
        }

        educationalCenterDTO.setCreateDate(ZonedDateTime.now());
        educationalCenterDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        EducationalCenterDTO result = educationalCenterService.save(educationalCenterDTO);
        return ResponseEntity.created(new URI("/api/educational-centers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /educational-centers : Updates an existing educationalCenter.
     *
     * @param educationalCenterDTO the educationalCenterDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated educationalCenterDTO,
     * or with status 400 (Bad Request) if the educationalCenterDTO is not valid,
     * or with status 500 (Internal Server Error) if the educationalCenterDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/educational-centers")
    @Timed
    public ResponseEntity<EducationalCenterDTO> updateEducationalCenter(@Valid @RequestBody EducationalCenterDTO educationalCenterDTO) throws URISyntaxException {
        log.debug("REST request to update EducationalCenter : {}", educationalCenterDTO);
        if (educationalCenterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        EducationalCenterDTO educationalCenter = educationalCenterService.findOne(educationalCenterDTO.getId()).get();

        educationalCenterDTO.setCreateUserLogin(educationalCenter.getCreateUserLogin());
        educationalCenterDTO.setCreateDate(educationalCenter.getCreateDate());
        educationalCenterDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        educationalCenterDTO.setModifyDate(ZonedDateTime.now());

        EducationalCenterDTO result = educationalCenterService.save(educationalCenterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, educationalCenterDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /educational-centers : get all the educationalCenters.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of educationalCenters in body
     */
    @GetMapping("/educational-centers")
    @Timed
    public ResponseEntity<List<EducationalCenterDTO>> getAllEducationalCenters(EducationalCenterCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EducationalCenters by criteria: {}", criteria);
        Page<EducationalCenterDTO> page = educationalCenterQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/educational-centers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /educational-centers/count : count all the educationalCenters.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/educational-centers/count")
    @Timed
    public ResponseEntity<Long> countEducationalCenters (EducationalCenterCriteria criteria) {
        log.debug("REST request to count EducationalCenters by criteria: {}", criteria);
        return ResponseEntity.ok().body(educationalCenterQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /educational-centers/:id : get the "id" educationalCenter.
     *
     * @param id the id of the educationalCenterDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the educationalCenterDTO, or with status 404 (Not Found)
     */
    @GetMapping("/educational-centers/{id}")
    @Timed
    public ResponseEntity<EducationalCenterDTO> getEducationalCenter(@PathVariable Long id) {
        log.debug("REST request to get EducationalCenter : {}", id);
        Optional<EducationalCenterDTO> educationalCenterDTO = educationalCenterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(educationalCenterDTO);
    }

    /**
     * DELETE  /educational-centers/:id : delete the "id" educationalCenter.
     *
     * @param id the id of the educationalCenterDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/educational-centers/{id}")
    @Timed
    public ResponseEntity<Void> deleteEducationalCenter(@PathVariable Long id) {
        log.debug("REST request to delete EducationalCenter : {}", id);
        educationalCenterService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * REST controller for managing EducationalCenterGradeScore.
     */
    @RestController
    @RequestMapping("/api")
    public static class EducationalCenterGradeScoreResource {

        private final Logger log = LoggerFactory.getLogger(EducationalCenterGradeScoreResource.class);

        private static final String ENTITY_NAME = "educationalCenterGradeScore";

        private final EducationalCenterGradeScoreService educationalCenterGradeScoreService;

        private final EducationalCenterGradeScoreQueryService educationalCenterGradeScoreQueryService;

        public EducationalCenterGradeScoreResource(EducationalCenterGradeScoreService educationalCenterGradeScoreService, EducationalCenterGradeScoreQueryService educationalCenterGradeScoreQueryService) {
            this.educationalCenterGradeScoreService = educationalCenterGradeScoreService;
            this.educationalCenterGradeScoreQueryService = educationalCenterGradeScoreQueryService;
        }

        /**
         * POST  /educational-center-grade-scores : Create a new educationalCenterGradeScore.
         *
         * @param educationalCenterGradeScoreDTO the educationalCenterGradeScoreDTO to create
         * @return the ResponseEntity with status 201 (Created) and with body the new educationalCenterGradeScoreDTO, or with status 400 (Bad Request) if the educationalCenterGradeScore has already an ID
         * @throws URISyntaxException if the Location URI syntax is incorrect
         */
        @PostMapping("/educational-center-grade-scores")
        @Timed
        public ResponseEntity<EducationalCenterGradeScoreDTO> createEducationalCenterGradeScore(@Valid @RequestBody EducationalCenterGradeScoreDTO educationalCenterGradeScoreDTO) throws URISyntaxException {
            log.debug("REST request to save EducationalCenterGradeScore : {}", educationalCenterGradeScoreDTO);
            if (educationalCenterGradeScoreDTO.getId() != null) {
                throw new BadRequestAlertException("A new educationalCenterGradeScore cannot already have an ID", ENTITY_NAME, "idexists");
            }
            EducationalCenterGradeScoreDTO result = educationalCenterGradeScoreService.save(educationalCenterGradeScoreDTO);
            return ResponseEntity.created(new URI("/api/educational-center-grade-scores/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
        }

        /**
         * PUT  /educational-center-grade-scores : Updates an existing educationalCenterGradeScore.
         *
         * @param educationalCenterGradeScoreDTO the educationalCenterGradeScoreDTO to update
         * @return the ResponseEntity with status 200 (OK) and with body the updated educationalCenterGradeScoreDTO,
         * or with status 400 (Bad Request) if the educationalCenterGradeScoreDTO is not valid,
         * or with status 500 (Internal Server Error) if the educationalCenterGradeScoreDTO couldn't be updated
         * @throws URISyntaxException if the Location URI syntax is incorrect
         */
        @PutMapping("/educational-center-grade-scores")
        @Timed
        public ResponseEntity<EducationalCenterGradeScoreDTO> updateEducationalCenterGradeScore(@Valid @RequestBody EducationalCenterGradeScoreDTO educationalCenterGradeScoreDTO) throws URISyntaxException {
            log.debug("REST request to update EducationalCenterGradeScore : {}", educationalCenterGradeScoreDTO);
            if (educationalCenterGradeScoreDTO.getId() == null) {
                throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
            }
            EducationalCenterGradeScoreDTO result = educationalCenterGradeScoreService.save(educationalCenterGradeScoreDTO);
            return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, educationalCenterGradeScoreDTO.getId().toString()))
                .body(result);
        }

        /**
         * GET  /educational-center-grade-scores : get all the educationalCenterGradeScores.
         *
         * @param pageable the pagination information
         * @param criteria the criterias which the requested entities should match
         * @return the ResponseEntity with status 200 (OK) and the list of educationalCenterGradeScores in body
         */
        @GetMapping("/educational-center-grade-scores")
        @Timed
        public ResponseEntity<List<EducationalCenterGradeScoreDTO>> getAllEducationalCenterGradeScores(EducationalCenterGradeScoreCriteria criteria, Pageable pageable) {
            log.debug("REST request to get EducationalCenterGradeScores by criteria: {}", criteria);
            Page<EducationalCenterGradeScoreDTO> page = educationalCenterGradeScoreQueryService.findByCriteria(criteria, pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/educational-center-grade-scores");
            return ResponseEntity.ok().headers(headers).body(page.getContent());
        }

        /**
        * GET  /educational-center-grade-scores/count : count all the educationalCenterGradeScores.
        *
        * @param criteria the criterias which the requested entities should match
        * @return the ResponseEntity with status 200 (OK) and the count in body
        */
        @GetMapping("/educational-center-grade-scores/count")
        @Timed
        public ResponseEntity<Long> countEducationalCenterGradeScores(EducationalCenterGradeScoreCriteria criteria) {
            log.debug("REST request to count EducationalCenterGradeScores by criteria: {}", criteria);
            return ResponseEntity.ok().body(educationalCenterGradeScoreQueryService.countByCriteria(criteria));
        }

        /**
         * GET  /educational-center-grade-scores/:id : get the "id" educationalCenterGradeScore.
         *
         * @param id the id of the educationalCenterGradeScoreDTO to retrieve
         * @return the ResponseEntity with status 200 (OK) and with body the educationalCenterGradeScoreDTO, or with status 404 (Not Found)
         */
        @GetMapping("/educational-center-grade-scores/{id}")
        @Timed
        public ResponseEntity<EducationalCenterGradeScoreDTO> getEducationalCenterGradeScore(@PathVariable Long id) {
            log.debug("REST request to get EducationalCenterGradeScore : {}", id);
            Optional<EducationalCenterGradeScoreDTO> educationalCenterGradeScoreDTO = educationalCenterGradeScoreService.findOne(id);
            return ResponseUtil.wrapOrNotFound(educationalCenterGradeScoreDTO);
        }

        /**
         * DELETE  /educational-center-grade-scores/:id : delete the "id" educationalCenterGradeScore.
         *
         * @param id the id of the educationalCenterGradeScoreDTO to delete
         * @return the ResponseEntity with status 200 (OK)
         */
        @DeleteMapping("/educational-center-grade-scores/{id}")
        @Timed
        public ResponseEntity<Void> deleteEducationalCenterGradeScore(@PathVariable Long id) {
            log.debug("REST request to delete EducationalCenterGradeScore : {}", id);
            educationalCenterGradeScoreService.delete(id);
            return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
        }
    }
}
