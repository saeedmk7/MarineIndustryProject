package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.domain.JamHelp;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.JamHelpService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.JamHelpDTO;
import com.marineindustryproj.service.dto.JamHelpCriteria;
import com.marineindustryproj.service.JamHelpQueryService;
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

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing JamHelp.
 */
@RestController
@RequestMapping("/api")
public class JamHelpResource {

    private final Logger log = LoggerFactory.getLogger(JamHelpResource.class);

    private static final String ENTITY_NAME = "jamHelp";

    private final JamHelpService jamHelpService;

    private final JamHelpQueryService jamHelpQueryService;

    public JamHelpResource(JamHelpService jamHelpService, JamHelpQueryService jamHelpQueryService) {
        this.jamHelpService = jamHelpService;
        this.jamHelpQueryService = jamHelpQueryService;
    }

    /**
     * POST  /jam-helps : Create a new jamHelp.
     *
     * @param jamHelpDTO the jamHelpDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new jamHelpDTO, or with status 400 (Bad Request) if the jamHelp has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/jam-helps")
    @Timed
    public ResponseEntity<JamHelpDTO> createJamHelp(@Valid @RequestBody JamHelpDTO jamHelpDTO) throws URISyntaxException {
        log.debug("REST request to save JamHelp : {}", jamHelpDTO);
        if (jamHelpDTO.getId() != null) {
            throw new BadRequestAlertException("A new jamHelp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        jamHelpDTO.setCreateDate(ZonedDateTime.now());
        jamHelpDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        JamHelpDTO result = jamHelpService.save(jamHelpDTO);
        return ResponseEntity.created(new URI("/api/jam-helps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /jam-helps : Updates an existing jamHelp.
     *
     * @param jamHelpDTO the jamHelpDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated jamHelpDTO,
     * or with status 400 (Bad Request) if the jamHelpDTO is not valid,
     * or with status 500 (Internal Server Error) if the jamHelpDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/jam-helps")
    @Timed
    public ResponseEntity<JamHelpDTO> updateJamHelp(@Valid @RequestBody JamHelpDTO jamHelpDTO) throws URISyntaxException {
        log.debug("REST request to update JamHelp : {}", jamHelpDTO);
        if (jamHelpDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        JamHelpDTO jamHelp = jamHelpService.findOne(jamHelpDTO.getId()).get();

        jamHelpDTO.setCreateUserLogin(jamHelp.getCreateUserLogin());
        jamHelpDTO.setCreateDate(jamHelp.getCreateDate());
        jamHelpDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        jamHelpDTO.setModifyDate(ZonedDateTime.now());
        JamHelpDTO result = jamHelpService.save(jamHelpDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, jamHelpDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /jam-helps : get all the jamHelps.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of jamHelps in body
     */
    @GetMapping("/jam-helps")
    @Timed
    public ResponseEntity<List<JamHelpDTO>> getAllJamHelps(JamHelpCriteria criteria, Pageable pageable) {
        log.debug("REST request to get JamHelps by criteria: {}", criteria);
        Page<JamHelpDTO> page = jamHelpQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/jam-helps");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /jam-helps/count : count all the jamHelps.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/jam-helps/count")
    @Timed
    public ResponseEntity<Long> countJamHelps(JamHelpCriteria criteria) {
        log.debug("REST request to count JamHelps by criteria: {}", criteria);
        return ResponseEntity.ok().body(jamHelpQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /jam-helps/:id : get the "id" jamHelp.
     *
     * @param id the id of the jamHelpDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the jamHelpDTO, or with status 404 (Not Found)
     */
    @GetMapping("/jam-helps/{id}")
    @Timed
    public ResponseEntity<JamHelpDTO> getJamHelp(@PathVariable Long id) {
        log.debug("REST request to get JamHelp : {}", id);
        Optional<JamHelpDTO> jamHelpDTO = jamHelpService.findOne(id);
        if(jamHelpDTO.isPresent())
        {
            jamHelpDTO.get().getId();
        }
        return ResponseUtil.wrapOrNotFound(jamHelpDTO);
    }

    /**
     * DELETE  /jam-helps/:id : delete the "id" jamHelp.
     *
     * @param id the id of the jamHelpDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/jam-helps/{id}")
    @Timed
    public ResponseEntity<Void> deleteJamHelp(@PathVariable Long id) {
        log.debug("REST request to delete JamHelp : {}", id);
        jamHelpService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
