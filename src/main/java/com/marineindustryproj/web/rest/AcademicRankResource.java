package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.AcademicRankService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.errors.InternalServerErrorException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.AcademicRankDTO;
import com.marineindustryproj.service.dto.AcademicRankCriteria;
import com.marineindustryproj.service.AcademicRankQueryService;
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

import static com.marineindustryproj.web.rest.errors.ErrorConstants.ERR_DELETION;

/**
 * REST controller for managing AcademicRank.
 */
@RestController
@RequestMapping("/api")
public class AcademicRankResource {

    private final Logger log = LoggerFactory.getLogger(AcademicRankResource.class);

    private static final String ENTITY_NAME = "academicRank";

    private final AcademicRankService academicRankService;

    private final AcademicRankQueryService academicRankQueryService;

    public AcademicRankResource(AcademicRankService academicRankService, AcademicRankQueryService academicRankQueryService) {
        this.academicRankService = academicRankService;
        this.academicRankQueryService = academicRankQueryService;
    }

    /**
     * POST  /academic-ranks : Create a new academicRank.
     *
     * @param academicRankDTO the academicRankDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new academicRankDTO, or with status 400 (Bad Request) if the academicRank has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/academic-ranks")
    @Timed
    public ResponseEntity<AcademicRankDTO> createAcademicRank(@Valid @RequestBody AcademicRankDTO academicRankDTO) throws URISyntaxException {
        log.debug("REST request to save AcademicRank : {}", academicRankDTO);
        if (academicRankDTO.getId() != null) {
            throw new BadRequestAlertException("A new academicRank cannot already have an ID", ENTITY_NAME, "idexists");
        }
        academicRankDTO.setCreateDate(ZonedDateTime.now());
        academicRankDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().toString());
        AcademicRankDTO result = academicRankService.save(academicRankDTO);
        return ResponseEntity.created(new URI("/api/academic-ranks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /academic-ranks : Updates an existing academicRank.
     *
     * @param academicRankDTO the academicRankDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated academicRankDTO,
     * or with status 400 (Bad Request) if the academicRankDTO is not valid,
     * or with status 500 (Internal Server Error) if the academicRankDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/academic-ranks")
    @Timed
    public ResponseEntity<AcademicRankDTO> updateAcademicRank(@Valid @RequestBody AcademicRankDTO academicRankDTO) throws URISyntaxException {
        log.debug("REST request to update AcademicRank : {}", academicRankDTO);
        if (academicRankDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AcademicRankDTO academicRank = academicRankService.findOne(academicRankDTO.getId()).get();

        academicRankDTO.setCreateUserLogin(academicRank.getCreateUserLogin());
        academicRankDTO.setCreateDate(academicRank.getCreateDate());
        academicRankDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        academicRankDTO.setModifyDate(ZonedDateTime.now());
        AcademicRankDTO result = academicRankService.save(academicRankDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, academicRankDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /academic-ranks : get all the academicRanks.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of academicRanks in body
     */
    @GetMapping("/academic-ranks")
    @Timed
    public ResponseEntity<List<AcademicRankDTO>> getAllAcademicRanks(AcademicRankCriteria criteria, Pageable pageable) {
        log.debug("REST request to get AcademicRanks by criteria: {}", criteria);
        Page<AcademicRankDTO> page = academicRankQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/academic-ranks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /academic-ranks/count : count all the academicRanks.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/academic-ranks/count")
    @Timed
    public ResponseEntity<Long> countAcademicRanks (AcademicRankCriteria criteria) {
        log.debug("REST request to count AcademicRanks by criteria: {}", criteria);
        return ResponseEntity.ok().body(academicRankQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /academic-ranks/:id : get the "id" academicRank.
     *
     * @param id the id of the academicRankDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the academicRankDTO, or with status 404 (Not Found)
     */
    @GetMapping("/academic-ranks/{id}")
    @Timed
    public ResponseEntity<AcademicRankDTO> getAcademicRank(@PathVariable Long id) {
        log.debug("REST request to get AcademicRank : {}", id);
        Optional<AcademicRankDTO> academicRankDTO = academicRankService.findOne(id);
        return ResponseUtil.wrapOrNotFound(academicRankDTO);
    }

    /**
     * DELETE  /academic-ranks/:id : delete the "id" academicRank.
     *
     * @param id the id of the academicRankDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/academic-ranks/{id}")
    @Timed
    public ResponseEntity<Void> deleteAcademicRank(@PathVariable Long id) {
        log.debug("REST request to delete AcademicRank : {}", id);
        try {
        academicRankService.delete(id);
        }
        catch (Exception ex)
        {
            throw new InternalServerErrorException(ERR_DELETION);
        }
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
