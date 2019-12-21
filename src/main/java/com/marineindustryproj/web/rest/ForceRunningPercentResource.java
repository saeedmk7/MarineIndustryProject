package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.domain.ForceRunningPercent;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.ForceRunningPercentService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.ForceRunningPercentDTO;
import com.marineindustryproj.service.dto.ForceRunningPercentCriteria;
import com.marineindustryproj.service.ForceRunningPercentQueryService;
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
 * REST controller for managing ForceRunningPercent.
 */
@RestController
@RequestMapping("/api")
public class ForceRunningPercentResource {

    private final Logger log = LoggerFactory.getLogger(ForceRunningPercentResource.class);

    private static final String ENTITY_NAME = "forceRunningPercent";

    private final ForceRunningPercentService forceRunningPercentService;

    private final ForceRunningPercentQueryService forceRunningPercentQueryService;

    public ForceRunningPercentResource(ForceRunningPercentService forceRunningPercentService, ForceRunningPercentQueryService forceRunningPercentQueryService) {
        this.forceRunningPercentService = forceRunningPercentService;
        this.forceRunningPercentQueryService = forceRunningPercentQueryService;
    }

    /**
     * POST  /force-running-percents : Create a new forceRunningPercent.
     *
     * @param forceRunningPercentDTO the forceRunningPercentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new forceRunningPercentDTO, or with status 400 (Bad Request) if the forceRunningPercent has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/force-running-percents")
    @Timed
    public ResponseEntity<ForceRunningPercentDTO> createForceRunningPercent(@Valid @RequestBody ForceRunningPercentDTO forceRunningPercentDTO) throws URISyntaxException {
        log.debug("REST request to save ForceRunningPercent : {}", forceRunningPercentDTO);
        if (forceRunningPercentDTO.getId() != null) {
            throw new BadRequestAlertException("A new forceRunningPercent cannot already have an ID", ENTITY_NAME, "idexists");
        }

        forceRunningPercentDTO.setCreateDate(ZonedDateTime.now());
        forceRunningPercentDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        forceRunningPercentDTO.setModifyDate(ZonedDateTime.now());
        forceRunningPercentDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());


        ForceRunningPercentDTO result = forceRunningPercentService.save(forceRunningPercentDTO);
        return ResponseEntity.created(new URI("/api/force-running-percents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /force-running-percents : Updates an existing forceRunningPercent.
     *
     * @param forceRunningPercentDTO the forceRunningPercentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated forceRunningPercentDTO,
     * or with status 400 (Bad Request) if the forceRunningPercentDTO is not valid,
     * or with status 500 (Internal Server Error) if the forceRunningPercentDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/force-running-percents")
    @Timed
    public ResponseEntity<ForceRunningPercentDTO> updateForceRunningPercent(@Valid @RequestBody ForceRunningPercentDTO forceRunningPercentDTO) throws URISyntaxException {
        log.debug("REST request to update ForceRunningPercent : {}", forceRunningPercentDTO);
        if (forceRunningPercentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        ForceRunningPercentDTO forceRunningPercent = forceRunningPercentService.findOne(forceRunningPercentDTO.getId()).get();

        forceRunningPercentDTO.setCreateUserLogin(forceRunningPercent.getCreateUserLogin());
        forceRunningPercentDTO.setCreateDate(forceRunningPercent.getCreateDate());
        forceRunningPercentDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        forceRunningPercentDTO.setModifyDate(ZonedDateTime.now());

        ForceRunningPercentDTO result = forceRunningPercentService.save(forceRunningPercentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, forceRunningPercentDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /force-running-percents : get all the forceRunningPercents.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of forceRunningPercents in body
     */
    @GetMapping("/force-running-percents")
    @Timed
    public ResponseEntity<List<ForceRunningPercentDTO>> getAllForceRunningPercents(ForceRunningPercentCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ForceRunningPercents by criteria: {}", criteria);
        Page<ForceRunningPercentDTO> page = forceRunningPercentQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/force-running-percents");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /force-running-percents/count : count all the forceRunningPercents.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/force-running-percents/count")
    @Timed
    public ResponseEntity<Long> countForceRunningPercents(ForceRunningPercentCriteria criteria) {
        log.debug("REST request to count ForceRunningPercents by criteria: {}", criteria);
        return ResponseEntity.ok().body(forceRunningPercentQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /force-running-percents/:id : get the "id" forceRunningPercent.
     *
     * @param id the id of the forceRunningPercentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the forceRunningPercentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/force-running-percents/{id}")
    @Timed
    public ResponseEntity<ForceRunningPercentDTO> getForceRunningPercent(@PathVariable Long id) {
        log.debug("REST request to get ForceRunningPercent : {}", id);
        Optional<ForceRunningPercentDTO> forceRunningPercentDTO = forceRunningPercentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(forceRunningPercentDTO);
    }

    /**
     * DELETE  /force-running-percents/:id : delete the "id" forceRunningPercent.
     *
     * @param id the id of the forceRunningPercentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/force-running-percents/{id}")
    @Timed
    public ResponseEntity<Void> deleteForceRunningPercent(@PathVariable Long id) {
        log.debug("REST request to delete ForceRunningPercent : {}", id);
        forceRunningPercentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
