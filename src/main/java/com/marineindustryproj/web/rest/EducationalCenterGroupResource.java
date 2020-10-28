package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.EducationalCenterGroupService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.EducationalCenterGroupDTO;
import com.marineindustryproj.service.dto.EducationalCenterGroupCriteria;
import com.marineindustryproj.service.EducationalCenterGroupQueryService;
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
 * REST controller for managing EducationalCenterGroup.
 */
@RestController
@RequestMapping("/api")
public class EducationalCenterGroupResource {

    private final Logger log = LoggerFactory.getLogger(EducationalCenterGroupResource.class);

    private static final String ENTITY_NAME = "educationalCenterGroup";

    private final EducationalCenterGroupService educationalCenterGroupService;

    private final EducationalCenterGroupQueryService educationalCenterGroupQueryService;

    public EducationalCenterGroupResource(EducationalCenterGroupService educationalCenterGroupService, EducationalCenterGroupQueryService educationalCenterGroupQueryService) {
        this.educationalCenterGroupService = educationalCenterGroupService;
        this.educationalCenterGroupQueryService = educationalCenterGroupQueryService;
    }

    /**
     * POST  /educational-center-groups : Create a new educationalCenterGroup.
     *
     * @param educationalCenterGroupDTO the educationalCenterGroupDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new educationalCenterGroupDTO, or with status 400 (Bad Request) if the educationalCenterGroup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/educational-center-groups")
    @Timed
    public ResponseEntity<EducationalCenterGroupDTO> createEducationalCenterGroup(@Valid @RequestBody EducationalCenterGroupDTO educationalCenterGroupDTO) throws URISyntaxException {
        log.debug("REST request to save EducationalCenterGroup : {}", educationalCenterGroupDTO);
        if (educationalCenterGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new educationalCenterGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }

        educationalCenterGroupDTO.setCreateDate(ZonedDateTime.now());
        educationalCenterGroupDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        EducationalCenterGroupDTO result = educationalCenterGroupService.save(educationalCenterGroupDTO);
        return ResponseEntity.created(new URI("/api/educational-center-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /educational-center-groups : Updates an existing educationalCenterGroup.
     *
     * @param educationalCenterGroupDTO the educationalCenterGroupDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated educationalCenterGroupDTO,
     * or with status 400 (Bad Request) if the educationalCenterGroupDTO is not valid,
     * or with status 500 (Internal Server Error) if the educationalCenterGroupDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/educational-center-groups")
    @Timed
    public ResponseEntity<EducationalCenterGroupDTO> updateEducationalCenterGroup(@Valid @RequestBody EducationalCenterGroupDTO educationalCenterGroupDTO) throws URISyntaxException {
        log.debug("REST request to update EducationalCenterGroup : {}", educationalCenterGroupDTO);
        if (educationalCenterGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        EducationalCenterGroupDTO educationalCenterGroup = educationalCenterGroupService.findOne(educationalCenterGroupDTO.getId()).get();

        educationalCenterGroupDTO.setCreateUserLogin(educationalCenterGroup.getCreateUserLogin());
        educationalCenterGroupDTO.setCreateDate(educationalCenterGroup.getCreateDate());
        educationalCenterGroupDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        educationalCenterGroupDTO.setModifyDate(ZonedDateTime.now());

        EducationalCenterGroupDTO result = educationalCenterGroupService.save(educationalCenterGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, educationalCenterGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /educational-center-groups : get all the educationalCenterGroups.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of educationalCenterGroups in body
     */
    @GetMapping("/educational-center-groups")
    @Timed
    public ResponseEntity<List<EducationalCenterGroupDTO>> getAllEducationalCenterGroups(EducationalCenterGroupCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EducationalCenterGroups by criteria: {}", criteria);
        Page<EducationalCenterGroupDTO> page = educationalCenterGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/educational-center-groups");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /educational-center-groups/count : count all the educationalCenterGroups.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/educational-center-groups/count")
    @Timed
    public ResponseEntity<Long> countEducationalCenterGroups(EducationalCenterGroupCriteria criteria) {
        log.debug("REST request to count EducationalCenterGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(educationalCenterGroupQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /educational-center-groups/:id : get the "id" educationalCenterGroup.
     *
     * @param id the id of the educationalCenterGroupDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the educationalCenterGroupDTO, or with status 404 (Not Found)
     */
    @GetMapping("/educational-center-groups/{id}")
    @Timed
    public ResponseEntity<EducationalCenterGroupDTO> getEducationalCenterGroup(@PathVariable Long id) {
        log.debug("REST request to get EducationalCenterGroup : {}", id);
        Optional<EducationalCenterGroupDTO> educationalCenterGroupDTO = educationalCenterGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(educationalCenterGroupDTO);
    }

    /**
     * DELETE  /educational-center-groups/:id : delete the "id" educationalCenterGroup.
     *
     * @param id the id of the educationalCenterGroupDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/educational-center-groups/{id}")
    @Timed
    public ResponseEntity<Void> deleteEducationalCenterGroup(@PathVariable Long id) {
        log.debug("REST request to delete EducationalCenterGroup : {}", id);
        educationalCenterGroupService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
