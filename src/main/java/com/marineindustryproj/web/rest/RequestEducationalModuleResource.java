package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.RequestEducationalModuleService;
import com.marineindustryproj.service.dto.EducationalModuleDTO;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.RequestEducationalModuleDTO;
import com.marineindustryproj.service.dto.RequestEducationalModuleCriteria;
import com.marineindustryproj.service.RequestEducationalModuleQueryService;
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
 * REST controller for managing RequestEducationalModule.
 */
@RestController
@RequestMapping("/api")
public class RequestEducationalModuleResource {

    private final Logger log = LoggerFactory.getLogger(RequestEducationalModuleResource.class);

    private static final String ENTITY_NAME = "requestEducationalModule";

    private final RequestEducationalModuleService requestEducationalModuleService;

    private final RequestEducationalModuleQueryService requestEducationalModuleQueryService;

    public RequestEducationalModuleResource(RequestEducationalModuleService requestEducationalModuleService, RequestEducationalModuleQueryService requestEducationalModuleQueryService) {
        this.requestEducationalModuleService = requestEducationalModuleService;
        this.requestEducationalModuleQueryService = requestEducationalModuleQueryService;
    }

    /**
     * POST  /request-educational-modules : Create a new requestEducationalModule.
     *
     * @param requestEducationalModuleDTO the requestEducationalModuleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new requestEducationalModuleDTO, or with status 400 (Bad Request) if the requestEducationalModule has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/request-educational-modules")
    @Timed
    public ResponseEntity<RequestEducationalModuleDTO> createRequestEducationalModule(@Valid @RequestBody RequestEducationalModuleDTO requestEducationalModuleDTO) throws URISyntaxException {
        log.debug("REST request to save RequestEducationalModule : {}", requestEducationalModuleDTO);
        if (requestEducationalModuleDTO.getId() != null) {
            throw new BadRequestAlertException("A new requestEducationalModule cannot already have an ID", ENTITY_NAME, "idexists");
        }

        requestEducationalModuleDTO.setCreateDate(ZonedDateTime.now());
        requestEducationalModuleDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        requestEducationalModuleDTO.setChangeStatusUserLogin(SecurityUtils.getCurrentUserLogin().get());

        RequestEducationalModuleDTO result = requestEducationalModuleService.save(requestEducationalModuleDTO);
        return ResponseEntity.created(new URI("/api/request-educational-modules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /request-educational-modules : Updates an existing requestEducationalModule.
     *
     * @param requestEducationalModuleDTO the requestEducationalModuleDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated requestEducationalModuleDTO,
     * or with status 400 (Bad Request) if the requestEducationalModuleDTO is not valid,
     * or with status 500 (Internal Server Error) if the requestEducationalModuleDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/request-educational-modules")
    @Timed
    public ResponseEntity<RequestEducationalModuleDTO> updateRequestEducationalModule(@Valid @RequestBody RequestEducationalModuleDTO requestEducationalModuleDTO) throws URISyntaxException {
        log.debug("REST request to update RequestEducationalModule : {}", requestEducationalModuleDTO);
        if (requestEducationalModuleDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        RequestEducationalModuleDTO requestEducationalModule = requestEducationalModuleService.findOne(requestEducationalModuleDTO.getId()).get();

        requestEducationalModuleDTO.setCreateUserLogin(requestEducationalModule.getCreateUserLogin());
        requestEducationalModuleDTO.setCreateDate(requestEducationalModule.getCreateDate());
        requestEducationalModuleDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        requestEducationalModuleDTO.setModifyDate(ZonedDateTime.now());

        RequestEducationalModuleDTO result = requestEducationalModuleService.save(requestEducationalModuleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, requestEducationalModuleDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /request-educational-modules : get all the requestEducationalModules.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of requestEducationalModules in body
     */
    @GetMapping("/request-educational-modules")
    @Timed
    public ResponseEntity<List<RequestEducationalModuleDTO>> getAllRequestEducationalModules(RequestEducationalModuleCriteria criteria, Pageable pageable) {
        log.debug("REST request to get RequestEducationalModules by criteria: {}", criteria);
        Page<RequestEducationalModuleDTO> page = requestEducationalModuleQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/request-educational-modules");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /request-educational-modules/count : count all the requestEducationalModules.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/request-educational-modules/count")
    @Timed
    public ResponseEntity<Long> countRequestEducationalModules (RequestEducationalModuleCriteria criteria) {
        log.debug("REST request to count RequestEducationalModules by criteria: {}", criteria);
        return ResponseEntity.ok().body(requestEducationalModuleQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /request-educational-modules/:id : get the "id" requestEducationalModule.
     *
     * @param id the id of the requestEducationalModuleDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the requestEducationalModuleDTO, or with status 404 (Not Found)
     */
    @GetMapping("/request-educational-modules/{id}")
    @Timed
    public ResponseEntity<RequestEducationalModuleDTO> getRequestEducationalModule(@PathVariable Long id) {
        log.debug("REST request to get RequestEducationalModule : {}", id);
        Optional<RequestEducationalModuleDTO> requestEducationalModuleDTO = requestEducationalModuleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(requestEducationalModuleDTO);
    }

    /**
     * DELETE  /request-educational-modules/:id : delete the "id" requestEducationalModule.
     *
     * @param id the id of the requestEducationalModuleDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/request-educational-modules/{id}")
    @Timed
    public ResponseEntity<Void> deleteRequestEducationalModule(@PathVariable Long id) {
        log.debug("REST request to delete RequestEducationalModule : {}", id);
        requestEducationalModuleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
