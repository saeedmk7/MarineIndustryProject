package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.NavBarItemAuthorityService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.NavBarItemAuthorityDTO;
import com.marineindustryproj.service.dto.NavBarItemAuthorityCriteria;
import com.marineindustryproj.service.NavBarItemAuthorityQueryService;
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
 * REST controller for managing NavBarItemAuthority.
 */
@RestController
@RequestMapping("/api")
public class NavBarItemAuthorityResource {

    private final Logger log = LoggerFactory.getLogger(NavBarItemAuthorityResource.class);

    private static final String ENTITY_NAME = "navBarItemAuthority";

    private final NavBarItemAuthorityService navBarItemAuthorityService;

    private final NavBarItemAuthorityQueryService navBarItemAuthorityQueryService;

    public NavBarItemAuthorityResource(NavBarItemAuthorityService navBarItemAuthorityService, NavBarItemAuthorityQueryService navBarItemAuthorityQueryService) {
        this.navBarItemAuthorityService = navBarItemAuthorityService;
        this.navBarItemAuthorityQueryService = navBarItemAuthorityQueryService;
    }

    /**
     * POST  /nav-bar-item-authorities : Create a new navBarItemAuthority.
     *
     * @param navBarItemAuthorityDTO the navBarItemAuthorityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new navBarItemAuthorityDTO, or with status 400 (Bad Request) if the navBarItemAuthority has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    /*@PostMapping("/nav-bar-item-authorities")
    @Timed
    public ResponseEntity<NavBarItemAuthorityDTO> createNavBarItemAuthority(@Valid @RequestBody NavBarItemAuthorityDTO navBarItemAuthorityDTO) throws URISyntaxException {
        log.debug("REST request to save NavBarItemAuthority : {}", navBarItemAuthorityDTO);
        if (navBarItemAuthorityDTO.getId() != null) {
            throw new BadRequestAlertException("A new navBarItemAuthority cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NavBarItemAuthorityDTO result = navBarItemAuthorityService.save(navBarItemAuthorityDTO);
        return ResponseEntity.created(new URI("/api/nav-bar-item-authorities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }*/

    /**
     * POST  /nav-bar-item-authorities : Create a new navBarItemAuthority.
     *
     * @param navBarItemAuthorityDTOS the navBarItemAuthorityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new navBarItemAuthorityDTO, or with status 400 (Bad Request) if the navBarItemAuthority has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nav-bar-item-authorities")
    @Timed
    public ResponseEntity<NavBarItemAuthorityDTO> createNavBarItemAuthority(@Valid @RequestBody List<NavBarItemAuthorityDTO> navBarItemAuthorityDTOS) throws URISyntaxException {
        log.debug("REST request to save NavBarItemAuthority : {}", navBarItemAuthorityDTOS);
        if (navBarItemAuthorityDTOS == null) {
            throw new BadRequestAlertException("A new navBarItemAuthority cannot already have an ID", ENTITY_NAME, "idexists");
        }
        for (int i = 0; i < navBarItemAuthorityDTOS.size(); i++) {
            navBarItemAuthorityDTOS.get(i).setCreateDate(ZonedDateTime.now());
            navBarItemAuthorityDTOS.get(i).setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        }

        NavBarItemAuthorityDTO result = navBarItemAuthorityService.bulkSave(navBarItemAuthorityDTOS);
        return ResponseEntity.created(new URI("/api/nav-bar-item-authorities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nav-bar-item-authorities : Updates an existing navBarItemAuthority.
     *
     * @param navBarItemAuthorityDTO the navBarItemAuthorityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated navBarItemAuthorityDTO,
     * or with status 400 (Bad Request) if the navBarItemAuthorityDTO is not valid,
     * or with status 500 (Internal Server Error) if the navBarItemAuthorityDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nav-bar-item-authorities")
    @Timed
    public ResponseEntity<NavBarItemAuthorityDTO> updateNavBarItemAuthority(@Valid @RequestBody NavBarItemAuthorityDTO navBarItemAuthorityDTO) throws URISyntaxException {
        log.debug("REST request to update NavBarItemAuthority : {}", navBarItemAuthorityDTO);
        if (navBarItemAuthorityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NavBarItemAuthorityDTO result = navBarItemAuthorityService.save(navBarItemAuthorityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, navBarItemAuthorityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nav-bar-item-authorities : get all the navBarItemAuthorities.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of navBarItemAuthorities in body
     */
    @GetMapping("/nav-bar-item-authorities")
    @Timed
    public ResponseEntity<List<NavBarItemAuthorityDTO>> getAllNavBarItemAuthorities(NavBarItemAuthorityCriteria criteria, Pageable pageable) {
        log.debug("REST request to get NavBarItemAuthorities by criteria: {}", criteria);
        Page<NavBarItemAuthorityDTO> page = navBarItemAuthorityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/nav-bar-item-authorities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /nav-bar-item-authorities/count : count all the navBarItemAuthorities.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/nav-bar-item-authorities/count")
    @Timed
    public ResponseEntity<Long> countNavBarItemAuthorities (NavBarItemAuthorityCriteria criteria) {
        log.debug("REST request to count NavBarItemAuthorities by criteria: {}", criteria);
        return ResponseEntity.ok().body(navBarItemAuthorityQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /nav-bar-item-authorities/:id : get the "id" navBarItemAuthority.
     *
     * @param id the id of the navBarItemAuthorityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the navBarItemAuthorityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/nav-bar-item-authorities/{id}")
    @Timed
    public ResponseEntity<NavBarItemAuthorityDTO> getNavBarItemAuthority(@PathVariable Long id) {
        log.debug("REST request to get NavBarItemAuthority : {}", id);
        Optional<NavBarItemAuthorityDTO> navBarItemAuthorityDTO = navBarItemAuthorityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(navBarItemAuthorityDTO);
    }

    /**
     * DELETE  /nav-bar-item-authorities/:id : delete the "id" navBarItemAuthority.
     *
     * @param id the id of the navBarItemAuthorityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    /*@DeleteMapping("/nav-bar-item-authorities/{id}")
    @Timed
    public ResponseEntity<Void> deleteNavBarItemAuthority(@PathVariable Long id) {
        log.debug("REST request to delete NavBarItemAuthority : {}", id);
        navBarItemAuthorityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }*/
    /**
     * DELETE  /nav-bar-item-authorities/:id : delete the "id" navBarItemAuthority.
     *
     * @param authority the id of the navBarItemAuthorityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nav-bar-item-authorities/{authority}")
    @Timed
    public ResponseEntity<Void> deleteNavBarItemAuthority(@PathVariable String authority) {
        log.debug("REST request to delete NavBarItemAuthority : {}", authority);
        navBarItemAuthorityService.delete(authority);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, authority)).build();
    }
}
