package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.SoldierService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.SoldierDTO;
import com.marineindustryproj.service.dto.SoldierCriteria;
import com.marineindustryproj.service.SoldierQueryService;
import io.github.jhipster.service.filter.BooleanFilter;
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
 * REST controller for managing Soldier.
 */
@RestController
@RequestMapping("/api")
public class SoldierResource {

    private final Logger log = LoggerFactory.getLogger(SoldierResource.class);

    private static final String ENTITY_NAME = "soldier";

    private final SoldierService soldierService;

    private final SoldierQueryService soldierQueryService;

    public SoldierResource(SoldierService soldierService, SoldierQueryService soldierQueryService) {
        this.soldierService = soldierService;
        this.soldierQueryService = soldierQueryService;
    }

    /**
     * POST  /soldiers : Create a new soldier.
     *
     * @param soldierDTO the soldierDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new soldierDTO, or with status 400 (Bad Request) if the soldier has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/soldiers")
    @Timed
    public ResponseEntity<SoldierDTO> createSoldier(@Valid @RequestBody SoldierDTO soldierDTO) throws URISyntaxException {
        log.debug("REST request to save Soldier : {}", soldierDTO);
        if (soldierDTO.getId() != null) {
            throw new BadRequestAlertException("A new soldier cannot already have an ID", ENTITY_NAME, "idexists");
        }

        soldierDTO.setGuid(UUID.randomUUID().toString());
        soldierDTO.setId(Long.parseLong(soldierDTO.getNationalId()));
        soldierDTO.setCreateDate(ZonedDateTime.now());
        soldierDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        SoldierDTO result = soldierService.save(soldierDTO);
        return ResponseEntity.created(new URI("/api/soldiers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /soldiers : Updates an existing soldier.
     *
     * @param soldierDTO the soldierDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated soldierDTO,
     * or with status 400 (Bad Request) if the soldierDTO is not valid,
     * or with status 500 (Internal Server Error) if the soldierDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/soldiers")
    @Timed
    public ResponseEntity<SoldierDTO> updateSoldier(@Valid @RequestBody SoldierDTO soldierDTO) throws URISyntaxException {
        log.debug("REST request to update Soldier : {}", soldierDTO);
        if (soldierDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        SoldierDTO soldier = soldierService.findOne(soldierDTO.getId()).get();
        soldierDTO.setGuid(soldier.getGuid());
        soldierDTO.setCreateUserLogin(soldier.getCreateUserLogin());
        soldierDTO.setCreateDate(soldier.getCreateDate());
        soldierDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        soldierDTO.setModifyDate(ZonedDateTime.now());

        SoldierDTO result = soldierService.save(soldierDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, soldierDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /soldiers : get all the soldiers.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of soldiers in body
     */
    @GetMapping("/soldiers")
    @Timed
    public ResponseEntity<List<SoldierDTO>> getAllSoldiers(SoldierCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Soldiers by criteria: {}", criteria);
        if(criteria.getArchived() == null)
        {
            BooleanFilter booleanFilter = new BooleanFilter();
            booleanFilter.setEquals(false);
            criteria.setArchived(booleanFilter);

            //new ZonedDateTimeFilter().setGreaterOrEqualThan()
        }
        Page<SoldierDTO> page = soldierQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/soldiers");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /soldiers/count : count all the soldiers.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/soldiers/count")
    @Timed
    public ResponseEntity<Long> countSoldiers(SoldierCriteria criteria) {
        log.debug("REST request to count Soldiers by criteria: {}", criteria);
        return ResponseEntity.ok().body(soldierQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /soldiers/:id : get the "id" soldier.
     *
     * @param id the id of the soldierDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the soldierDTO, or with status 404 (Not Found)
     */
    @GetMapping("/soldiers/{id}")
    @Timed
    public ResponseEntity<SoldierDTO> getSoldier(@PathVariable Long id) {
        log.debug("REST request to get Soldier : {}", id);
        Optional<SoldierDTO> soldierDTO = soldierService.findOne(id);
        return ResponseUtil.wrapOrNotFound(soldierDTO);
    }

    /**
     * DELETE  /soldiers/:id : delete the "id" soldier.
     *
     * @param id the id of the soldierDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/soldiers/{id}")
    @Timed
    public ResponseEntity<Void> deleteSoldier(@PathVariable Long id) {
        log.debug("REST request to delete Soldier : {}", id);
        soldierService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
