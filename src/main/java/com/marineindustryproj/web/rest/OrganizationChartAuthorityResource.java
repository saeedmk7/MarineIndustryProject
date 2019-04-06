package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.OrganizationChartAuthorityService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.OrganizationChartAuthorityDTO;
import com.marineindustryproj.service.dto.OrganizationChartAuthorityCriteria;
import com.marineindustryproj.service.OrganizationChartAuthorityQueryService;
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
 * REST controller for managing OrganizationChartAuthority.
 */
@RestController
@RequestMapping("/api")
public class OrganizationChartAuthorityResource {

    private final Logger log = LoggerFactory.getLogger(OrganizationChartAuthorityResource.class);

    private static final String ENTITY_NAME = "organizationChartAuthority";

    private final OrganizationChartAuthorityService organizationChartAuthorityService;

    private final OrganizationChartAuthorityQueryService organizationChartAuthorityQueryService;

    public OrganizationChartAuthorityResource(OrganizationChartAuthorityService organizationChartAuthorityService, OrganizationChartAuthorityQueryService organizationChartAuthorityQueryService) {
        this.organizationChartAuthorityService = organizationChartAuthorityService;
        this.organizationChartAuthorityQueryService = organizationChartAuthorityQueryService;
    }

    /**
     * POST  /organization-chart-authorities : Create a new organizationChartAuthority.
     *
     * @param organizationChartAuthorityDTO the organizationChartAuthorityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new organizationChartAuthorityDTO, or with status 400 (Bad Request) if the organizationChartAuthority has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/organization-chart-authorities")
    @Timed
    public ResponseEntity<OrganizationChartAuthorityDTO> createOrganizationChartAuthority(@Valid @RequestBody OrganizationChartAuthorityDTO organizationChartAuthorityDTO) throws URISyntaxException {
        log.debug("REST request to save OrganizationChartAuthority : {}", organizationChartAuthorityDTO);
        if (organizationChartAuthorityDTO.getId() != null) {
            throw new BadRequestAlertException("A new organizationChartAuthority cannot already have an ID", ENTITY_NAME, "idexists");
        }

        organizationChartAuthorityDTO.setCreateDate(ZonedDateTime.now());
        organizationChartAuthorityDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        OrganizationChartAuthorityDTO result = organizationChartAuthorityService.save(organizationChartAuthorityDTO);
        return ResponseEntity.created(new URI("/api/organization-chart-authorities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /organization-chart-authorities : Updates an existing organizationChartAuthority.
     *
     * @param organizationChartAuthorityDTO the organizationChartAuthorityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated organizationChartAuthorityDTO,
     * or with status 400 (Bad Request) if the organizationChartAuthorityDTO is not valid,
     * or with status 500 (Internal Server Error) if the organizationChartAuthorityDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/organization-chart-authorities")
    @Timed
    public ResponseEntity<OrganizationChartAuthorityDTO> updateOrganizationChartAuthority(@Valid @RequestBody OrganizationChartAuthorityDTO organizationChartAuthorityDTO) throws URISyntaxException {
        log.debug("REST request to update OrganizationChartAuthority : {}", organizationChartAuthorityDTO);
        if (organizationChartAuthorityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        OrganizationChartAuthorityDTO organizationChartAuthority = organizationChartAuthorityService.findOne(organizationChartAuthorityDTO.getId()).get();

        organizationChartAuthorityDTO.setCreateUserLogin(organizationChartAuthority.getCreateUserLogin());
        organizationChartAuthorityDTO.setCreateDate(organizationChartAuthority.getCreateDate());
        organizationChartAuthorityDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        organizationChartAuthorityDTO.setModifyDate(ZonedDateTime.now());

        OrganizationChartAuthorityDTO result = organizationChartAuthorityService.save(organizationChartAuthorityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, organizationChartAuthorityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /organization-chart-authorities : get all the organizationChartAuthorities.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of organizationChartAuthorities in body
     */
    @GetMapping("/organization-chart-authorities")
    @Timed
    public ResponseEntity<List<OrganizationChartAuthorityDTO>> getAllOrganizationChartAuthorities(OrganizationChartAuthorityCriteria criteria, Pageable pageable) {
        log.debug("REST request to get OrganizationChartAuthorities by criteria: {}", criteria);
        Page<OrganizationChartAuthorityDTO> page = organizationChartAuthorityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/organization-chart-authorities");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /organization-chart-authorities/count : count all the organizationChartAuthorities.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/organization-chart-authorities/count")
    @Timed
    public ResponseEntity<Long> countOrganizationChartAuthorities (OrganizationChartAuthorityCriteria criteria) {
        log.debug("REST request to count OrganizationChartAuthorities by criteria: {}", criteria);
        return ResponseEntity.ok().body(organizationChartAuthorityQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /organization-chart-authorities/:id : get the "id" organizationChartAuthority.
     *
     * @param id the id of the organizationChartAuthorityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the organizationChartAuthorityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/organization-chart-authorities/{id}")
    @Timed
    public ResponseEntity<OrganizationChartAuthorityDTO> getOrganizationChartAuthority(@PathVariable Long id) {
        log.debug("REST request to get OrganizationChartAuthority : {}", id);
        Optional<OrganizationChartAuthorityDTO> organizationChartAuthorityDTO = organizationChartAuthorityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(organizationChartAuthorityDTO);
    }

    /**
     * DELETE  /organization-chart-authorities/:id : delete the "id" organizationChartAuthority.
     *
     * @param id the id of the organizationChartAuthorityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/organization-chart-authorities/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrganizationChartAuthority(@PathVariable Long id) {
        log.debug("REST request to delete OrganizationChartAuthority : {}", id);
        organizationChartAuthorityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
