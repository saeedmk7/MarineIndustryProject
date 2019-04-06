package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.OrganizationChartService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.errors.InternalServerErrorException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.service.dto.OrganizationChartDTO;
import com.marineindustryproj.service.dto.OrganizationChartCriteria;
import com.marineindustryproj.service.OrganizationChartQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing OrganizationChart.
 */
@RestController
@RequestMapping("/api")
public class OrganizationChartResource {

    private final Logger log = LoggerFactory.getLogger(OrganizationChartResource.class);

    private static final String ENTITY_NAME = "organizationChart";

    private final OrganizationChartService organizationChartService;

    private final OrganizationChartQueryService organizationChartQueryService;

    public OrganizationChartResource(OrganizationChartService organizationChartService, OrganizationChartQueryService organizationChartQueryService) {
        this.organizationChartService = organizationChartService;
        this.organizationChartQueryService = organizationChartQueryService;
    }

    /**
     * POST  /organization-charts : Create a new organizationChart.
     *
     * @param organizationChartDTO the organizationChartDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new organizationChartDTO, or with status 400 (Bad Request) if the organizationChart has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/organization-charts")
    @Timed
    public ResponseEntity<OrganizationChartDTO> createOrganizationChart(@Valid @RequestBody OrganizationChartDTO organizationChartDTO) throws URISyntaxException {
        log.debug("REST request to save OrganizationChart : {}", organizationChartDTO);
        if (organizationChartDTO.getId() != null) {
            throw new BadRequestAlertException("A new organizationChart cannot already have an ID", ENTITY_NAME, "idexists");
        }

        organizationChartDTO.setCreateDate(ZonedDateTime.now());
        organizationChartDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        OrganizationChartDTO result = organizationChartService.save(organizationChartDTO);
        return ResponseEntity.created(new URI("/api/organization-charts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /organization-charts : Updates an existing organizationChart.
     *
     * @param organizationChartDTO the organizationChartDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated organizationChartDTO,
     * or with status 400 (Bad Request) if the organizationChartDTO is not valid,
     * or with status 500 (Internal Server Error) if the organizationChartDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/organization-charts")
    @Timed
    public ResponseEntity<OrganizationChartDTO> updateOrganizationChart(@Valid @RequestBody OrganizationChartDTO organizationChartDTO) throws URISyntaxException {
        log.debug("REST request to update OrganizationChart : {}", organizationChartDTO);
        if (organizationChartDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        OrganizationChartDTO organizationChart = organizationChartService.findOne(organizationChartDTO.getId()).get();

        organizationChartDTO.setCreateUserLogin(organizationChart.getCreateUserLogin());
        organizationChartDTO.setCreateDate(organizationChart.getCreateDate());
        organizationChartDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        organizationChartDTO.setModifyDate(ZonedDateTime.now());

        OrganizationChartDTO result = organizationChartService.save(organizationChartDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, organizationChartDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /organization-charts : get all the organizationCharts.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of organizationCharts in body
     */
    @GetMapping("/organization-charts")
    @Timed
    public ResponseEntity<List<OrganizationChartDTO>> getAllOrganizationCharts(OrganizationChartCriteria criteria) {
        log.debug("REST request to get OrganizationCharts by criteria: {}", criteria);
        List<OrganizationChartDTO> entityList = organizationChartQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }
    /**
     * GET  /organization-charts : get all the organizationCharts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of organizationCharts in body
     */
    @GetMapping("/organization-charts/all")
    @Timed
    public ResponseEntity<List<OrganizationChartDTO>> getAllOrganizationCharts() {
        log.debug("REST request to get OrganizationCharts");
        List<OrganizationChartDTO> entityList = organizationChartService.findAll();
        return ResponseEntity.ok().body(entityList);
    }
    /**
    * GET  /organization-charts/count : count all the organizationCharts.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/organization-charts/count")
    @Timed
    public ResponseEntity<Long> countOrganizationCharts (OrganizationChartCriteria criteria) {
        log.debug("REST request to count OrganizationCharts by criteria: {}", criteria);
        return ResponseEntity.ok().body(organizationChartQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /organization-charts/:id : get the "id" organizationChart.
     *
     * @param id the id of the organizationChartDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the organizationChartDTO, or with status 404 (Not Found)
     */
    @GetMapping("/organization-charts/{id}")
    @Timed
    public ResponseEntity<OrganizationChartDTO> getOrganizationChart(@PathVariable Long id) {
        log.debug("REST request to get OrganizationChart : {}", id);
        Optional<OrganizationChartDTO> organizationChartDTO = organizationChartService.findOne(id);
        return ResponseUtil.wrapOrNotFound(organizationChartDTO);
    }

    /**
     * DELETE  /organization-charts/:id : delete the "id" organizationChart.
     *
     * @param id the id of the organizationChartDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/organization-charts/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrganizationChart(@PathVariable Long id) {
        log.debug("REST request to delete OrganizationChart : {}", id);
        try {
            organizationChartService.delete(id);
            return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME,
                                                                                    id.toString())).build();
        }
        catch (Exception ex)
        {
            throw new InternalServerErrorException("marineindustryprojApp.organizationChart.delete.error");
        }
    }
}
