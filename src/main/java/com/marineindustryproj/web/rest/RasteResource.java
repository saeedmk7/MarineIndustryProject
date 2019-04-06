package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.RasteService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.RasteDTO;
import com.marineindustryproj.service.dto.RasteCriteria;
import com.marineindustryproj.service.RasteQueryService;
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
 * REST controller for managing Raste.
 */
@RestController
@RequestMapping("/api")
public class RasteResource {

    private final Logger log = LoggerFactory.getLogger(RasteResource.class);

    private static final String ENTITY_NAME = "raste";

    private final RasteService rasteService;

    private final RasteQueryService rasteQueryService;

    public RasteResource(RasteService rasteService, RasteQueryService rasteQueryService) {
        this.rasteService = rasteService;
        this.rasteQueryService = rasteQueryService;
    }

    /**
     * POST  /rastes : Create a new raste.
     *
     * @param rasteDTO the rasteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rasteDTO, or with status 400 (Bad Request) if the raste has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rastes")
    @Timed
    public ResponseEntity<RasteDTO> createRaste(@Valid @RequestBody RasteDTO rasteDTO) throws URISyntaxException {
        log.debug("REST request to save Raste : {}", rasteDTO);
        if (rasteDTO.getId() != null) {
            throw new BadRequestAlertException("A new raste cannot already have an ID", ENTITY_NAME, "idexists");
        }
        rasteDTO.setCreateDate(ZonedDateTime.now());
        rasteDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        RasteDTO result = rasteService.save(rasteDTO);
        return ResponseEntity.created(new URI("/api/rastes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rastes : Updates an existing raste.
     *
     * @param rasteDTO the rasteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rasteDTO,
     * or with status 400 (Bad Request) if the rasteDTO is not valid,
     * or with status 500 (Internal Server Error) if the rasteDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rastes")
    @Timed
    public ResponseEntity<RasteDTO> updateRaste(@Valid @RequestBody RasteDTO rasteDTO) throws URISyntaxException {
        log.debug("REST request to update Raste : {}", rasteDTO);
        if (rasteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RasteDTO raste = rasteService.findOne(rasteDTO.getId()).get();

        rasteDTO.setCreateUserLogin(raste.getCreateUserLogin());
        rasteDTO.setCreateDate(raste.getCreateDate());
        rasteDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        rasteDTO.setModifyDate(ZonedDateTime.now());
        RasteDTO result = rasteService.save(rasteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rasteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rastes : get all the rastes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of rastes in body
     */
    @GetMapping("/rastes")
    @Timed
    public ResponseEntity<List<RasteDTO>> getAllRastes(RasteCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Rastes by criteria: {}", criteria);
        Page<RasteDTO> page = rasteQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rastes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /rastes/count : count all the rastes.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/rastes/count")
    @Timed
    public ResponseEntity<Long> countRastes (RasteCriteria criteria) {
        log.debug("REST request to count Rastes by criteria: {}", criteria);
        return ResponseEntity.ok().body(rasteQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /rastes/:id : get the "id" raste.
     *
     * @param id the id of the rasteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rasteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/rastes/{id}")
    @Timed
    public ResponseEntity<RasteDTO> getRaste(@PathVariable Long id) {
        log.debug("REST request to get Raste : {}", id);
        Optional<RasteDTO> rasteDTO = rasteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rasteDTO);
    }

    /**
     * DELETE  /rastes/:id : delete the "id" raste.
     *
     * @param id the id of the rasteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rastes/{id}")
    @Timed
    public ResponseEntity<Void> deleteRaste(@PathVariable Long id) {
        log.debug("REST request to delete Raste : {}", id);
        rasteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
