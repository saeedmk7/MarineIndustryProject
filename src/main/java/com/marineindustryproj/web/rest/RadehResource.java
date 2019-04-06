package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.RadehService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.RadehDTO;
import com.marineindustryproj.service.dto.RadehCriteria;
import com.marineindustryproj.service.RadehQueryService;
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
 * REST controller for managing Radeh.
 */
@RestController
@RequestMapping("/api")
public class RadehResource {

    private final Logger log = LoggerFactory.getLogger(RadehResource.class);

    private static final String ENTITY_NAME = "radeh";

    private final RadehService radehService;

    private final RadehQueryService radehQueryService;

    public RadehResource(RadehService radehService, RadehQueryService radehQueryService) {
        this.radehService = radehService;
        this.radehQueryService = radehQueryService;
    }

    /**
     * POST  /radehs : Create a new radeh.
     *
     * @param radehDTO the radehDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new radehDTO, or with status 400 (Bad Request) if the radeh has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/radehs")
    @Timed
    public ResponseEntity<RadehDTO> createRadeh(@Valid @RequestBody RadehDTO radehDTO) throws URISyntaxException {
        log.debug("REST request to save Radeh : {}", radehDTO);
        if (radehDTO.getId() != null) {
            throw new BadRequestAlertException("A new radeh cannot already have an ID", ENTITY_NAME, "idexists");
        }
        radehDTO.setCreateDate(ZonedDateTime.now());
        radehDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        RadehDTO result = radehService.save(radehDTO);
        return ResponseEntity.created(new URI("/api/radehs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /radehs : Updates an existing radeh.
     *
     * @param radehDTO the radehDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated radehDTO,
     * or with status 400 (Bad Request) if the radehDTO is not valid,
     * or with status 500 (Internal Server Error) if the radehDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/radehs")
    @Timed
    public ResponseEntity<RadehDTO> updateRadeh(@Valid @RequestBody RadehDTO radehDTO) throws URISyntaxException {
        log.debug("REST request to update Radeh : {}", radehDTO);
        if (radehDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RadehDTO radeh = radehService.findOne(radehDTO.getId()).get();

        radehDTO.setCreateUserLogin(radeh.getCreateUserLogin());
        radehDTO.setCreateDate(radeh.getCreateDate());
        radehDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        radehDTO.setModifyDate(ZonedDateTime.now());
        RadehDTO result = radehService.save(radehDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, radehDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /radehs : get all the radehs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of radehs in body
     */
    @GetMapping("/radehs")
    @Timed
    public ResponseEntity<List<RadehDTO>> getAllRadehs(RadehCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Radehs by criteria: {}", criteria);
        Page<RadehDTO> page = radehQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/radehs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /radehs/count : count all the radehs.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/radehs/count")
    @Timed
    public ResponseEntity<Long> countRadehs (RadehCriteria criteria) {
        log.debug("REST request to count Radehs by criteria: {}", criteria);
        return ResponseEntity.ok().body(radehQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /radehs/:id : get the "id" radeh.
     *
     * @param id the id of the radehDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the radehDTO, or with status 404 (Not Found)
     */
    @GetMapping("/radehs/{id}")
    @Timed
    public ResponseEntity<RadehDTO> getRadeh(@PathVariable Long id) {
        log.debug("REST request to get Radeh : {}", id);
        Optional<RadehDTO> radehDTO = radehService.findOne(id);
        return ResponseUtil.wrapOrNotFound(radehDTO);
    }

    /**
     * DELETE  /radehs/:id : delete the "id" radeh.
     *
     * @param id the id of the radehDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/radehs/{id}")
    @Timed
    public ResponseEntity<Void> deleteRadeh(@PathVariable Long id) {
        log.debug("REST request to delete Radeh : {}", id);
        radehService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
