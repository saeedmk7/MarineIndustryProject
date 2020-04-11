package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.EducationalCenterGradeService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.EducationalCenterGradeDTO;
import com.marineindustryproj.service.dto.EducationalCenterGradeCriteria;
import com.marineindustryproj.service.EducationalCenterGradeQueryService;
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
 * REST controller for managing EducationalCenterGrade.
 */
@RestController
@RequestMapping("/api")
public class EducationalCenterGradeResource {

    private final Logger log = LoggerFactory.getLogger(EducationalCenterGradeResource.class);

    private static final String ENTITY_NAME = "educationalCenterGrade";

    private final EducationalCenterGradeService educationalCenterGradeService;

    private final EducationalCenterGradeQueryService educationalCenterGradeQueryService;

    public EducationalCenterGradeResource(EducationalCenterGradeService educationalCenterGradeService, EducationalCenterGradeQueryService educationalCenterGradeQueryService) {
        this.educationalCenterGradeService = educationalCenterGradeService;
        this.educationalCenterGradeQueryService = educationalCenterGradeQueryService;
    }

    /**
     * POST  /educational-center-grades : Create a new educationalCenterGrade.
     *
     * @param educationalCenterGradeDTO the educationalCenterGradeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new educationalCenterGradeDTO, or with status 400 (Bad Request) if the educationalCenterGrade has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/educational-center-grades")
    @Timed
    public ResponseEntity<EducationalCenterGradeDTO> createEducationalCenterGrade(@Valid @RequestBody EducationalCenterGradeDTO educationalCenterGradeDTO) throws URISyntaxException {
        log.debug("REST request to save EducationalCenterGrade : {}", educationalCenterGradeDTO);
        if (educationalCenterGradeDTO.getId() != null) {
            throw new BadRequestAlertException("A new educationalCenterGrade cannot already have an ID", ENTITY_NAME, "idexists");
        }

        educationalCenterGradeDTO.setCreateDate(ZonedDateTime.now());
        educationalCenterGradeDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        EducationalCenterGradeDTO result = educationalCenterGradeService.saveWithScore(educationalCenterGradeDTO);
        return ResponseEntity.created(new URI("/api/educational-center-grades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /educational-center-grades : Updates an existing educationalCenterGrade.
     *
     * @param educationalCenterGradeDTO the educationalCenterGradeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated educationalCenterGradeDTO,
     * or with status 400 (Bad Request) if the educationalCenterGradeDTO is not valid,
     * or with status 500 (Internal Server Error) if the educationalCenterGradeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/educational-center-grades")
    @Timed
    public ResponseEntity<EducationalCenterGradeDTO> updateEducationalCenterGrade(@Valid @RequestBody EducationalCenterGradeDTO educationalCenterGradeDTO) throws URISyntaxException {
        log.debug("REST request to update EducationalCenterGrade : {}", educationalCenterGradeDTO);
        if (educationalCenterGradeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        EducationalCenterGradeDTO educationalCenterGrade = educationalCenterGradeService.findOne(educationalCenterGradeDTO.getId()).get();

        educationalCenterGradeDTO.setCreateUserLogin(educationalCenterGrade.getCreateUserLogin());
        educationalCenterGradeDTO.setCreateDate(educationalCenterGrade.getCreateDate());
        educationalCenterGradeDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        educationalCenterGradeDTO.setModifyDate(ZonedDateTime.now());

        EducationalCenterGradeDTO result = educationalCenterGradeService.saveWithScore(educationalCenterGradeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, educationalCenterGradeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /educational-center-grades : get all the educationalCenterGrades.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of educationalCenterGrades in body
     */
    @GetMapping("/educational-center-grades")
    @Timed
    public ResponseEntity<List<EducationalCenterGradeDTO>> getAllEducationalCenterGrades(EducationalCenterGradeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EducationalCenterGrades by criteria: {}", criteria);
        Page<EducationalCenterGradeDTO> page = educationalCenterGradeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/educational-center-grades");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /educational-center-grades/count : count all the educationalCenterGrades.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/educational-center-grades/count")
    @Timed
    public ResponseEntity<Long> countEducationalCenterGrades(EducationalCenterGradeCriteria criteria) {
        log.debug("REST request to count EducationalCenterGrades by criteria: {}", criteria);
        return ResponseEntity.ok().body(educationalCenterGradeQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /educational-center-grades/:id : get the "id" educationalCenterGrade.
     *
     * @param id the id of the educationalCenterGradeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the educationalCenterGradeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/educational-center-grades/{id}")
    @Timed
    public ResponseEntity<EducationalCenterGradeDTO> getEducationalCenterGrade(@PathVariable Long id) {
        log.debug("REST request to get EducationalCenterGrade : {}", id);
        Optional<EducationalCenterGradeDTO> educationalCenterGradeDTO = educationalCenterGradeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(educationalCenterGradeDTO);
    }

    /**
     * DELETE  /educational-center-grades/:id : delete the "id" educationalCenterGrade.
     *
     * @param id the id of the educationalCenterGradeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/educational-center-grades/{id}")
    @Timed
    public ResponseEntity<Void> deleteEducationalCenterGrade(@PathVariable Long id) {
        log.debug("REST request to delete EducationalCenterGrade : {}", id);
        educationalCenterGradeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
