package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.EducationalModuleService;
import com.marineindustryproj.service.OrganizationService;
import com.marineindustryproj.service.dto.OrganizationDTO;
import com.marineindustryproj.service.dto.customs.EducationalModuleMinDTO;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.EducationalModuleDTO;
import com.marineindustryproj.service.dto.EducationalModuleCriteria;
import com.marineindustryproj.service.EducationalModuleQueryService;
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
 * REST controller for managing EducationalModule.
 */
@RestController
@RequestMapping("/api")
public class EducationalModuleResource {

    private final Logger log = LoggerFactory.getLogger(EducationalModuleResource.class);

    private static final String ENTITY_NAME = "educationalModule";

    private final EducationalModuleService educationalModuleService;

    private final OrganizationService organizationService;

    private final EducationalModuleQueryService educationalModuleQueryService;

    public EducationalModuleResource(EducationalModuleService educationalModuleService,
                                     OrganizationService organizationService,
                                     EducationalModuleQueryService educationalModuleQueryService) {
        this.educationalModuleService = educationalModuleService;
        this.organizationService = organizationService;
        this.educationalModuleQueryService = educationalModuleQueryService;
    }

    /**
     * POST  /educational-modules : Create a new educationalModule.
     *
     * @param educationalModuleDTO the educationalModuleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new educationalModuleDTO, or with status 400 (Bad Request) if the educationalModule has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/educational-modules")
    @Timed
    public ResponseEntity<EducationalModuleDTO> createEducationalModule(@Valid @RequestBody EducationalModuleDTO educationalModuleDTO) throws URISyntaxException {
        log.debug("REST request to save EducationalModule : {}", educationalModuleDTO);
        if (educationalModuleDTO.getId() != null) {
            throw new BadRequestAlertException("A new educationalModule cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if(educationalModuleDTO.getOrganizationId() != null) {
            Optional<OrganizationDTO> organizationDTO = organizationService.findOne(educationalModuleDTO.getOrganizationId());
            if (organizationDTO.isPresent())
                educationalModuleDTO.setRecommendedBy(organizationDTO.get().getTitle());
        }
        educationalModuleDTO.setId(Long.parseLong(educationalModuleDTO.getCode()));
        educationalModuleDTO.setCreateDate(ZonedDateTime.now());
        educationalModuleDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        EducationalModuleDTO result = educationalModuleService.save(educationalModuleDTO);
        return ResponseEntity.created(new URI("/api/educational-modules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


    /**
     * PUT  /educational-modules : Updates an existing educationalModule.
     *
     * @param educationalModuleDTO the educationalModuleDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated educationalModuleDTO,
     * or with status 400 (Bad Request) if the educationalModuleDTO is not valid,
     * or with status 500 (Internal Server Error) if the educationalModuleDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/educational-modules")
    @Timed
    public ResponseEntity<EducationalModuleDTO> updateEducationalModule(@Valid @RequestBody EducationalModuleDTO educationalModuleDTO) throws URISyntaxException {
        log.debug("REST request to update EducationalModule : {}", educationalModuleDTO);
        if (educationalModuleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        EducationalModuleDTO educationalModule = educationalModuleService.findOne(educationalModuleDTO.getId()).get();
        Optional<OrganizationDTO> organizationDTO = organizationService.findOne(educationalModuleDTO.getOrganizationId());
        if(organizationDTO.isPresent())
            educationalModuleDTO.setRecommendedBy(organizationDTO.get().getTitle());
        educationalModuleDTO.setId(Long.parseLong(educationalModuleDTO.getCode()));
        educationalModuleDTO.setCreateUserLogin(educationalModule.getCreateUserLogin());
        educationalModuleDTO.setCreateDate(educationalModule.getCreateDate());
        educationalModuleDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        educationalModuleDTO.setModifyDate(ZonedDateTime.now());

        EducationalModuleDTO result = educationalModuleService.save(educationalModuleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, educationalModuleDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /educational-modules : get all the educationalModules.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of educationalModules in body
     */
    @GetMapping("/educational-modules")
    @Timed
    public ResponseEntity<List<EducationalModuleDTO>> getAllEducationalModules(EducationalModuleCriteria criteria, Pageable pageable) throws Exception{
        log.debug("REST request to get EducationalModules by criteria: {}", criteria);
        Page<EducationalModuleDTO> page = educationalModuleQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/educational-modules");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /educational-modules : get all the educationalModules.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of educationalModules in body
     */
    @GetMapping("/educational-modules/all")
    @Timed
    public ResponseEntity<List<EducationalModuleMinDTO>> getAllEducationalModules() {
        log.debug("REST request to get EducationalModules");
        List<EducationalModuleMinDTO> educationalModules = educationalModuleService.findAllFromCache();
        return ResponseEntity.ok().body(educationalModules);
    }

    /**
    * GET  /educational-modules/count : count all the educationalModules.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/educational-modules/count")
    @Timed
    public ResponseEntity<Long> countEducationalModules (EducationalModuleCriteria criteria) {
        log.debug("REST request to count EducationalModules by criteria: {}", criteria);
        return ResponseEntity.ok().body(educationalModuleQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /educational-modules/:id : get the "id" educationalModule.
     *
     * @param id the id of the educationalModuleDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the educationalModuleDTO, or with status 404 (Not Found)
     */
    @GetMapping("/educational-modules/{id}")
    @Timed
    public ResponseEntity<EducationalModuleDTO> getEducationalModule(@PathVariable Long id) {
        log.debug("REST request to get EducationalModule : {}", id);
        Optional<EducationalModuleDTO> educationalModuleDTO = educationalModuleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(educationalModuleDTO);
    }

    /**
     * DELETE  /educational-modules/:id : delete the "id" educationalModule.
     *
     * @param id the id of the educationalModuleDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/educational-modules/{id}")
    @Timed
    public ResponseEntity<Void> deleteEducationalModule(@PathVariable Long id) {
        log.debug("REST request to delete EducationalModule : {}", id);
        educationalModuleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
