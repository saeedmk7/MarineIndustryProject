package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.RestrictionService;
import com.marineindustryproj.service.dto.ResourceDTO;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.RestrictionDTO;
import com.marineindustryproj.service.dto.RestrictionCriteria;
import com.marineindustryproj.service.RestrictionQueryService;
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
import java.util.UUID;

/**
 * REST controller for managing Restriction.
 */
@RestController
@RequestMapping("/api")
public class RestrictionResource {

    private final Logger log = LoggerFactory.getLogger(RestrictionResource.class);

    private static final String ENTITY_NAME = "restriction";

    private final RestrictionService restrictionService;

    private final RestrictionQueryService restrictionQueryService;

    public RestrictionResource(RestrictionService restrictionService, RestrictionQueryService restrictionQueryService) {
        this.restrictionService = restrictionService;
        this.restrictionQueryService = restrictionQueryService;
    }

    /**
     * POST  /restrictions : Create a new restriction.
     *
     * @param restrictionDTO the restrictionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new restrictionDTO, or with status 400 (Bad Request) if the restriction has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/restrictions")
    @Timed
    public ResponseEntity<RestrictionDTO> createRestriction(@Valid @RequestBody RestrictionDTO restrictionDTO) throws URISyntaxException {
        log.debug("REST request to save Restriction : {}", restrictionDTO);
        if (restrictionDTO.getId() != null) {
            throw new BadRequestAlertException("A new restriction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        restrictionDTO.setCreateDate(ZonedDateTime.now());
        restrictionDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        restrictionDTO.setGuid(UUID.randomUUID().toString());
        RestrictionDTO result = restrictionService.save(restrictionDTO);
        return ResponseEntity.created(new URI("/api/restrictions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /restrictions : Updates an existing restriction.
     *
     * @param restrictionDTO the restrictionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated restrictionDTO,
     * or with status 400 (Bad Request) if the restrictionDTO is not valid,
     * or with status 500 (Internal Server Error) if the restrictionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/restrictions")
    @Timed
    public ResponseEntity<RestrictionDTO> updateRestriction(@Valid @RequestBody RestrictionDTO restrictionDTO) throws URISyntaxException {
        log.debug("REST request to update Restriction : {}", restrictionDTO);
        if (restrictionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RestrictionDTO restriction = restrictionService.findOne(restrictionDTO.getId()).get();

        restrictionDTO.setCreateUserLogin(restriction.getCreateUserLogin());
        restrictionDTO.setCreateDate(restriction.getCreateDate());
        restrictionDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        restrictionDTO.setModifyDate(ZonedDateTime.now());
        restrictionDTO.setGuid(restriction.getGuid());
        RestrictionDTO result = restrictionService.save(restrictionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, restrictionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /restrictions : get all the restrictions.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of restrictions in body
     */
    @GetMapping("/restrictions")
    @Timed
    public ResponseEntity<List<RestrictionDTO>> getAllRestrictions(RestrictionCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Restrictions by criteria: {}", criteria);
        Page<RestrictionDTO> page = restrictionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/restrictions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /restrictions/count : count all the restrictions.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/restrictions/count")
    @Timed
    public ResponseEntity<Long> countRestrictions(RestrictionCriteria criteria) {
        log.debug("REST request to count Restrictions by criteria: {}", criteria);
        return ResponseEntity.ok().body(restrictionQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /restrictions/:id : get the "id" restriction.
     *
     * @param id the id of the restrictionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the restrictionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/restrictions/{id}")
    @Timed
    public ResponseEntity<RestrictionDTO> getRestriction(@PathVariable Long id) {
        log.debug("REST request to get Restriction : {}", id);
        Optional<RestrictionDTO> restrictionDTO = restrictionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(restrictionDTO);
    }

    /**
     * DELETE  /restrictions/:id : delete the "id" restriction.
     *
     * @param id the id of the restrictionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/restrictions/{id}")
    @Timed
    public ResponseEntity<Void> deleteRestriction(@PathVariable Long id) {
        log.debug("REST request to delete Restriction : {}", id);
        restrictionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
