package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.EducationalCenterServiceService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.EducationalCenterServiceDTO;
import com.marineindustryproj.service.dto.EducationalCenterServiceCriteria;
import com.marineindustryproj.service.EducationalCenterServiceQueryService;
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
 * REST controller for managing EducationalCenterService.
 */
@RestController
@RequestMapping("/api")
public class EducationalCenterServiceResource {

    private final Logger log = LoggerFactory.getLogger(EducationalCenterServiceResource.class);

    private static final String ENTITY_NAME = "educationalCenterService";

    private final EducationalCenterServiceService educationalCenterServiceService;

    private final EducationalCenterServiceQueryService educationalCenterServiceQueryService;

    public EducationalCenterServiceResource(EducationalCenterServiceService educationalCenterServiceService, EducationalCenterServiceQueryService educationalCenterServiceQueryService) {
        this.educationalCenterServiceService = educationalCenterServiceService;
        this.educationalCenterServiceQueryService = educationalCenterServiceQueryService;
    }

    /**
     * POST  /educational-center-services : Create a new educationalCenterService.
     *
     * @param educationalCenterServiceDTO the educationalCenterServiceDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new educationalCenterServiceDTO, or with status 400 (Bad Request) if the educationalCenterService has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/educational-center-services")
    @Timed
    public ResponseEntity<EducationalCenterServiceDTO> createEducationalCenterService(@Valid @RequestBody EducationalCenterServiceDTO educationalCenterServiceDTO) throws URISyntaxException {
        log.debug("REST request to save EducationalCenterService : {}", educationalCenterServiceDTO);
        if (educationalCenterServiceDTO.getId() != null) {
            throw new BadRequestAlertException("A new educationalCenterService cannot already have an ID", ENTITY_NAME, "idexists");
        }

        educationalCenterServiceDTO.setCreateDate(ZonedDateTime.now());
        educationalCenterServiceDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        EducationalCenterServiceDTO result = educationalCenterServiceService.save(educationalCenterServiceDTO);
        return ResponseEntity.created(new URI("/api/educational-center-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /educational-center-services : Updates an existing educationalCenterService.
     *
     * @param educationalCenterServiceDTO the educationalCenterServiceDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated educationalCenterServiceDTO,
     * or with status 400 (Bad Request) if the educationalCenterServiceDTO is not valid,
     * or with status 500 (Internal Server Error) if the educationalCenterServiceDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/educational-center-services")
    @Timed
    public ResponseEntity<EducationalCenterServiceDTO> updateEducationalCenterService(@Valid @RequestBody EducationalCenterServiceDTO educationalCenterServiceDTO) throws URISyntaxException {
        log.debug("REST request to update EducationalCenterService : {}", educationalCenterServiceDTO);
        if (educationalCenterServiceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        EducationalCenterServiceDTO educationalCenterService = educationalCenterServiceService.findOne(educationalCenterServiceDTO.getId()).get();

        educationalCenterServiceDTO.setCreateUserLogin(educationalCenterService.getCreateUserLogin());
        educationalCenterServiceDTO.setCreateDate(educationalCenterService.getCreateDate());
        educationalCenterServiceDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        educationalCenterServiceDTO.setModifyDate(ZonedDateTime.now());

        EducationalCenterServiceDTO result = educationalCenterServiceService.save(educationalCenterServiceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, educationalCenterServiceDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /educational-center-services : get all the educationalCenterServices.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of educationalCenterServices in body
     */
    @GetMapping("/educational-center-services")
    @Timed
    public ResponseEntity<List<EducationalCenterServiceDTO>> getAllEducationalCenterServices(EducationalCenterServiceCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EducationalCenterServices by criteria: {}", criteria);
        Page<EducationalCenterServiceDTO> page = educationalCenterServiceQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/educational-center-services");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /educational-center-services/count : count all the educationalCenterServices.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/educational-center-services/count")
    @Timed
    public ResponseEntity<Long> countEducationalCenterServices(EducationalCenterServiceCriteria criteria) {
        log.debug("REST request to count EducationalCenterServices by criteria: {}", criteria);
        return ResponseEntity.ok().body(educationalCenterServiceQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /educational-center-services/:id : get the "id" educationalCenterService.
     *
     * @param id the id of the educationalCenterServiceDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the educationalCenterServiceDTO, or with status 404 (Not Found)
     */
    @GetMapping("/educational-center-services/{id}")
    @Timed
    public ResponseEntity<EducationalCenterServiceDTO> getEducationalCenterService(@PathVariable Long id) {
        log.debug("REST request to get EducationalCenterService : {}", id);
        Optional<EducationalCenterServiceDTO> educationalCenterServiceDTO = educationalCenterServiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(educationalCenterServiceDTO);
    }

    /**
     * DELETE  /educational-center-services/:id : delete the "id" educationalCenterService.
     *
     * @param id the id of the educationalCenterServiceDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/educational-center-services/{id}")
    @Timed
    public ResponseEntity<Void> deleteEducationalCenterService(@PathVariable Long id) {
        log.debug("REST request to delete EducationalCenterService : {}", id);
        educationalCenterServiceService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
