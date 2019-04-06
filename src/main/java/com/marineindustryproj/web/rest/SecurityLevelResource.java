package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.SecurityLevelService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.SecurityLevelDTO;
import com.marineindustryproj.service.dto.SecurityLevelCriteria;
import com.marineindustryproj.service.SecurityLevelQueryService;
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
 * REST controller for managing SecurityLevel.
 */
@RestController
@RequestMapping("/api")
public class SecurityLevelResource {

    private final Logger log = LoggerFactory.getLogger(SecurityLevelResource.class);

    private static final String ENTITY_NAME = "securityLevel";

    private final SecurityLevelService securityLevelService;

    private final SecurityLevelQueryService securityLevelQueryService;

    public SecurityLevelResource(SecurityLevelService securityLevelService, SecurityLevelQueryService securityLevelQueryService) {
        this.securityLevelService = securityLevelService;
        this.securityLevelQueryService = securityLevelQueryService;
    }

    /**
     * POST  /security-levels : Create a new securityLevel.
     *
     * @param securityLevelDTO the securityLevelDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new securityLevelDTO, or with status 400 (Bad Request) if the securityLevel has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/security-levels")
    @Timed
    public ResponseEntity<SecurityLevelDTO> createSecurityLevel(@Valid @RequestBody SecurityLevelDTO securityLevelDTO) throws URISyntaxException {
        log.debug("REST request to save SecurityLevel : {}", securityLevelDTO);
        if (securityLevelDTO.getId() != null) {
            throw new BadRequestAlertException("A new securityLevel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SecurityLevelDTO result = securityLevelService.save(securityLevelDTO);
        return ResponseEntity.created(new URI("/api/security-levels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /security-levels : Updates an existing securityLevel.
     *
     * @param securityLevelDTO the securityLevelDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated securityLevelDTO,
     * or with status 400 (Bad Request) if the securityLevelDTO is not valid,
     * or with status 500 (Internal Server Error) if the securityLevelDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/security-levels")
    @Timed
    public ResponseEntity<SecurityLevelDTO> updateSecurityLevel(@Valid @RequestBody SecurityLevelDTO securityLevelDTO) throws URISyntaxException {
        log.debug("REST request to update SecurityLevel : {}", securityLevelDTO);
        if (securityLevelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SecurityLevelDTO result = securityLevelService.save(securityLevelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, securityLevelDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /security-levels : get all the securityLevels.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of securityLevels in body
     */
    @GetMapping("/security-levels")
    @Timed
    public ResponseEntity<List<SecurityLevelDTO>> getAllSecurityLevels(SecurityLevelCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SecurityLevels by criteria: {}", criteria);
        Page<SecurityLevelDTO> page = securityLevelQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/security-levels");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /security-levels/count : count all the securityLevels.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/security-levels/count")
    @Timed
    public ResponseEntity<Long> countSecurityLevels (SecurityLevelCriteria criteria) {
        log.debug("REST request to count SecurityLevels by criteria: {}", criteria);
        return ResponseEntity.ok().body(securityLevelQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /security-levels/:id : get the "id" securityLevel.
     *
     * @param id the id of the securityLevelDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the securityLevelDTO, or with status 404 (Not Found)
     */
    @GetMapping("/security-levels/{id}")
    @Timed
    public ResponseEntity<SecurityLevelDTO> getSecurityLevel(@PathVariable Long id) {
        log.debug("REST request to get SecurityLevel : {}", id);
        Optional<SecurityLevelDTO> securityLevelDTO = securityLevelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(securityLevelDTO);
    }

    /**
     * DELETE  /security-levels/:id : delete the "id" securityLevel.
     *
     * @param id the id of the securityLevelDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/security-levels/{id}")
    @Timed
    public ResponseEntity<Void> deleteSecurityLevel(@PathVariable Long id) {
        log.debug("REST request to delete SecurityLevel : {}", id);
        securityLevelService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
