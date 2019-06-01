package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.JamHelpAuthorityService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.JamHelpAuthorityDTO;
import com.marineindustryproj.service.dto.JamHelpAuthorityCriteria;
import com.marineindustryproj.service.JamHelpAuthorityQueryService;
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
 * REST controller for managing JamHelpAuthority.
 */
@RestController
@RequestMapping("/api")
public class JamHelpAuthorityResource {

    private final Logger log = LoggerFactory.getLogger(JamHelpAuthorityResource.class);

    private static final String ENTITY_NAME = "jamHelpAuthority";

    private final JamHelpAuthorityService jamHelpAuthorityService;

    private final JamHelpAuthorityQueryService jamHelpAuthorityQueryService;

    public JamHelpAuthorityResource(JamHelpAuthorityService jamHelpAuthorityService, JamHelpAuthorityQueryService jamHelpAuthorityQueryService) {
        this.jamHelpAuthorityService = jamHelpAuthorityService;
        this.jamHelpAuthorityQueryService = jamHelpAuthorityQueryService;
    }

    /**
     * POST  /jam-help-authorities : Create a new jamHelpAuthority.
     *
     * @param jamHelpAuthorityDTO the jamHelpAuthorityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new jamHelpAuthorityDTO, or with status 400 (Bad Request) if the jamHelpAuthority has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/jam-help-authorities")
    @Timed
    public ResponseEntity<JamHelpAuthorityDTO> createJamHelpAuthority(@Valid @RequestBody JamHelpAuthorityDTO jamHelpAuthorityDTO) throws URISyntaxException {
        log.debug("REST request to save JamHelpAuthority : {}", jamHelpAuthorityDTO);
        if (jamHelpAuthorityDTO.getId() != null) {
            throw new BadRequestAlertException("A new jamHelpAuthority cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JamHelpAuthorityDTO result = jamHelpAuthorityService.save(jamHelpAuthorityDTO);
        return ResponseEntity.created(new URI("/api/jam-help-authorities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /jam-help-authorities : Updates an existing jamHelpAuthority.
     *
     * @param jamHelpAuthorityDTO the jamHelpAuthorityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated jamHelpAuthorityDTO,
     * or with status 400 (Bad Request) if the jamHelpAuthorityDTO is not valid,
     * or with status 500 (Internal Server Error) if the jamHelpAuthorityDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/jam-help-authorities")
    @Timed
    public ResponseEntity<JamHelpAuthorityDTO> updateJamHelpAuthority(@Valid @RequestBody JamHelpAuthorityDTO jamHelpAuthorityDTO) throws URISyntaxException {
        log.debug("REST request to update JamHelpAuthority : {}", jamHelpAuthorityDTO);
        if (jamHelpAuthorityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        JamHelpAuthorityDTO result = jamHelpAuthorityService.save(jamHelpAuthorityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, jamHelpAuthorityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /jam-help-authorities : get all the jamHelpAuthorities.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of jamHelpAuthorities in body
     */
    @GetMapping("/jam-help-authorities")
    @Timed
    public ResponseEntity<List<JamHelpAuthorityDTO>> getAllJamHelpAuthorities(JamHelpAuthorityCriteria criteria, Pageable pageable) {
        log.debug("REST request to get JamHelpAuthorities by criteria: {}", criteria);
        Page<JamHelpAuthorityDTO> page = jamHelpAuthorityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/jam-help-authorities");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /jam-help-authorities/count : count all the jamHelpAuthorities.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/jam-help-authorities/count")
    @Timed
    public ResponseEntity<Long> countJamHelpAuthorities(JamHelpAuthorityCriteria criteria) {
        log.debug("REST request to count JamHelpAuthorities by criteria: {}", criteria);
        return ResponseEntity.ok().body(jamHelpAuthorityQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /jam-help-authorities/:id : get the "id" jamHelpAuthority.
     *
     * @param id the id of the jamHelpAuthorityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the jamHelpAuthorityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/jam-help-authorities/{id}")
    @Timed
    public ResponseEntity<JamHelpAuthorityDTO> getJamHelpAuthority(@PathVariable Long id) {
        log.debug("REST request to get JamHelpAuthority : {}", id);
        Optional<JamHelpAuthorityDTO> jamHelpAuthorityDTO = jamHelpAuthorityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jamHelpAuthorityDTO);
    }

    /**
     * DELETE  /jam-help-authorities/:id : delete the "id" jamHelpAuthority.
     *
     * @param id the id of the jamHelpAuthorityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/jam-help-authorities/{id}")
    @Timed
    public ResponseEntity<Void> deleteJamHelpAuthority(@PathVariable Long id) {
        log.debug("REST request to delete JamHelpAuthority : {}", id);
        jamHelpAuthorityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
