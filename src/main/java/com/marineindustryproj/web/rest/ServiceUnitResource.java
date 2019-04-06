package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.ServiceUnitService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.ServiceUnitDTO;
import com.marineindustryproj.service.dto.ServiceUnitCriteria;
import com.marineindustryproj.service.ServiceUnitQueryService;
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
 * REST controller for managing ServiceUnit.
 */
@RestController
@RequestMapping("/api")
public class ServiceUnitResource {

    private final Logger log = LoggerFactory.getLogger(ServiceUnitResource.class);

    private static final String ENTITY_NAME = "serviceUnit";

    private final ServiceUnitService serviceUnitService;

    private final ServiceUnitQueryService serviceUnitQueryService;

    public ServiceUnitResource(ServiceUnitService serviceUnitService, ServiceUnitQueryService serviceUnitQueryService) {
        this.serviceUnitService = serviceUnitService;
        this.serviceUnitQueryService = serviceUnitQueryService;
    }

    /**
     * POST  /service-units : Create a new serviceUnit.
     *
     * @param serviceUnitDTO the serviceUnitDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new serviceUnitDTO, or with status 400 (Bad Request) if the serviceUnit has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/service-units")
    @Timed
    public ResponseEntity<ServiceUnitDTO> createServiceUnit(@Valid @RequestBody ServiceUnitDTO serviceUnitDTO) throws URISyntaxException {
        log.debug("REST request to save ServiceUnit : {}", serviceUnitDTO);
        if (serviceUnitDTO.getId() != null) {
            throw new BadRequestAlertException("A new serviceUnit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServiceUnitDTO result = serviceUnitService.save(serviceUnitDTO);
        return ResponseEntity.created(new URI("/api/service-units/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /service-units : Updates an existing serviceUnit.
     *
     * @param serviceUnitDTO the serviceUnitDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated serviceUnitDTO,
     * or with status 400 (Bad Request) if the serviceUnitDTO is not valid,
     * or with status 500 (Internal Server Error) if the serviceUnitDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/service-units")
    @Timed
    public ResponseEntity<ServiceUnitDTO> updateServiceUnit(@Valid @RequestBody ServiceUnitDTO serviceUnitDTO) throws URISyntaxException {
        log.debug("REST request to update ServiceUnit : {}", serviceUnitDTO);
        if (serviceUnitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ServiceUnitDTO result = serviceUnitService.save(serviceUnitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, serviceUnitDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /service-units : get all the serviceUnits.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of serviceUnits in body
     */
    @GetMapping("/service-units")
    @Timed
    public ResponseEntity<List<ServiceUnitDTO>> getAllServiceUnits(ServiceUnitCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ServiceUnits by criteria: {}", criteria);
        Page<ServiceUnitDTO> page = serviceUnitQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/service-units");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /service-units/count : count all the serviceUnits.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/service-units/count")
    @Timed
    public ResponseEntity<Long> countServiceUnits (ServiceUnitCriteria criteria) {
        log.debug("REST request to count ServiceUnits by criteria: {}", criteria);
        return ResponseEntity.ok().body(serviceUnitQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /service-units/:id : get the "id" serviceUnit.
     *
     * @param id the id of the serviceUnitDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the serviceUnitDTO, or with status 404 (Not Found)
     */
    @GetMapping("/service-units/{id}")
    @Timed
    public ResponseEntity<ServiceUnitDTO> getServiceUnit(@PathVariable Long id) {
        log.debug("REST request to get ServiceUnit : {}", id);
        Optional<ServiceUnitDTO> serviceUnitDTO = serviceUnitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(serviceUnitDTO);
    }

    /**
     * DELETE  /service-units/:id : delete the "id" serviceUnit.
     *
     * @param id the id of the serviceUnitDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/service-units/{id}")
    @Timed
    public ResponseEntity<Void> deleteServiceUnit(@PathVariable Long id) {
        log.debug("REST request to delete ServiceUnit : {}", id);
        serviceUnitService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
