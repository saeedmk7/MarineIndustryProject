package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.EmploymentTypeService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.EmploymentTypeDTO;
import com.marineindustryproj.service.dto.EmploymentTypeCriteria;
import com.marineindustryproj.service.EmploymentTypeQueryService;
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
 * REST controller for managing EmploymentType.
 */
@RestController
@RequestMapping("/api")
public class EmploymentTypeResource {

    private final Logger log = LoggerFactory.getLogger(EmploymentTypeResource.class);

    private static final String ENTITY_NAME = "employmentType";

    private final EmploymentTypeService employmentTypeService;

    private final EmploymentTypeQueryService employmentTypeQueryService;

    public EmploymentTypeResource(EmploymentTypeService employmentTypeService, EmploymentTypeQueryService employmentTypeQueryService) {
        this.employmentTypeService = employmentTypeService;
        this.employmentTypeQueryService = employmentTypeQueryService;
    }

    /**
     * POST  /employment-types : Create a new employmentType.
     *
     * @param employmentTypeDTO the employmentTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new employmentTypeDTO, or with status 400 (Bad Request) if the employmentType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/employment-types")
    @Timed
    public ResponseEntity<EmploymentTypeDTO> createEmploymentType(@Valid @RequestBody EmploymentTypeDTO employmentTypeDTO) throws URISyntaxException {
        log.debug("REST request to save EmploymentType : {}", employmentTypeDTO);
        if (employmentTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new employmentType cannot already have an ID", ENTITY_NAME, "idexists");
        }

        employmentTypeDTO.setCreateDate(ZonedDateTime.now());
        employmentTypeDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        EmploymentTypeDTO result = employmentTypeService.save(employmentTypeDTO);
        return ResponseEntity.created(new URI("/api/employment-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /employment-types : Updates an existing employmentType.
     *
     * @param employmentTypeDTO the employmentTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated employmentTypeDTO,
     * or with status 400 (Bad Request) if the employmentTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the employmentTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/employment-types")
    @Timed
    public ResponseEntity<EmploymentTypeDTO> updateEmploymentType(@Valid @RequestBody EmploymentTypeDTO employmentTypeDTO) throws URISyntaxException {
        log.debug("REST request to update EmploymentType : {}", employmentTypeDTO);
        if (employmentTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        EmploymentTypeDTO employmentType = employmentTypeService.findOne(employmentTypeDTO.getId()).get();

        employmentTypeDTO.setCreateUserLogin(employmentType.getCreateUserLogin());
        employmentTypeDTO.setCreateDate(employmentType.getCreateDate());
        employmentTypeDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        employmentTypeDTO.setModifyDate(ZonedDateTime.now());

        EmploymentTypeDTO result = employmentTypeService.save(employmentTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, employmentTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /employment-types : get all the employmentTypes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of employmentTypes in body
     */
    @GetMapping("/employment-types")
    @Timed
    public ResponseEntity<List<EmploymentTypeDTO>> getAllEmploymentTypes(EmploymentTypeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EmploymentTypes by criteria: {}", criteria);
        Page<EmploymentTypeDTO> page = employmentTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/employment-types");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /employment-types/count : count all the employmentTypes.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/employment-types/count")
    @Timed
    public ResponseEntity<Long> countEmploymentTypes (EmploymentTypeCriteria criteria) {
        log.debug("REST request to count EmploymentTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(employmentTypeQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /employment-types/:id : get the "id" employmentType.
     *
     * @param id the id of the employmentTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the employmentTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/employment-types/{id}")
    @Timed
    public ResponseEntity<EmploymentTypeDTO> getEmploymentType(@PathVariable Long id) {
        log.debug("REST request to get EmploymentType : {}", id);
        Optional<EmploymentTypeDTO> employmentTypeDTO = employmentTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(employmentTypeDTO);
    }

    /**
     * DELETE  /employment-types/:id : delete the "id" employmentType.
     *
     * @param id the id of the employmentTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/employment-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmploymentType(@PathVariable Long id) {
        log.debug("REST request to delete EmploymentType : {}", id);
        employmentTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
