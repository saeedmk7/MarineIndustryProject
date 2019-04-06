package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.RequestOrganizationNiazsanjiService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.RequestOrganizationNiazsanjiDTO;
import com.marineindustryproj.service.dto.RequestOrganizationNiazsanjiCriteria;
import com.marineindustryproj.service.RequestOrganizationNiazsanjiQueryService;
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
 * REST controller for managing RequestOrganizationNiazsanji.
 */
@RestController
@RequestMapping("/api")
public class RequestOrganizationNiazsanjiResource {

    private final Logger log = LoggerFactory.getLogger(RequestOrganizationNiazsanjiResource.class);

    private static final String ENTITY_NAME = "requestOrganizationNiazsanji";

    private final RequestOrganizationNiazsanjiService requestOrganizationNiazsanjiService;

    private final RequestOrganizationNiazsanjiQueryService requestOrganizationNiazsanjiQueryService;

    public RequestOrganizationNiazsanjiResource(RequestOrganizationNiazsanjiService requestOrganizationNiazsanjiService, RequestOrganizationNiazsanjiQueryService requestOrganizationNiazsanjiQueryService) {
        this.requestOrganizationNiazsanjiService = requestOrganizationNiazsanjiService;
        this.requestOrganizationNiazsanjiQueryService = requestOrganizationNiazsanjiQueryService;
    }

    /**
     * POST  /request-organization-niazsanjis : Create a new requestOrganizationNiazsanji.
     *
     * @param requestOrganizationNiazsanjiDTO the requestOrganizationNiazsanjiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new requestOrganizationNiazsanjiDTO, or with status 400 (Bad Request) if the requestOrganizationNiazsanji has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/request-organization-niazsanjis")
    @Timed
    public ResponseEntity<RequestOrganizationNiazsanjiDTO> createRequestOrganizationNiazsanji(@Valid @RequestBody RequestOrganizationNiazsanjiDTO requestOrganizationNiazsanjiDTO) throws URISyntaxException {
        log.debug("REST request to save RequestOrganizationNiazsanji : {}", requestOrganizationNiazsanjiDTO);
        if (requestOrganizationNiazsanjiDTO.getId() != null) {
            throw new BadRequestAlertException("A new requestOrganizationNiazsanji cannot already have an ID", ENTITY_NAME, "idexists");
        }

        requestOrganizationNiazsanjiDTO.setCreateDate(ZonedDateTime.now());
        requestOrganizationNiazsanjiDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        //requestOrganizationNiazsanjiDTO.setChangeStatusUserLogin(SecurityUtils.getCurrentUserLogin().get());

        RequestOrganizationNiazsanjiDTO result = requestOrganizationNiazsanjiService.save(requestOrganizationNiazsanjiDTO);
        return ResponseEntity.created(new URI("/api/request-organization-niazsanjis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * POST  /request-organization-niazsanjis : Create a new requestOrganizationNiazsanji.
     *
     * @param requestOrganizationNiazsanjiDTO the requestOrganizationNiazsanjiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new requestOrganizationNiazsanjiDTO, or with status 400 (Bad Request) if the requestOrganizationNiazsanji has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/finalize-request-organization-niazsanji")
    @Timed
    public ResponseEntity<RequestOrganizationNiazsanjiDTO> finalizeRequestOrganizationNiazsanji(@Valid @RequestBody RequestOrganizationNiazsanjiDTO requestOrganizationNiazsanjiDTO) throws URISyntaxException {
        log.debug("REST request to save RequestOrganizationNiazsanji : {}", requestOrganizationNiazsanjiDTO);
        if (requestOrganizationNiazsanjiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        RequestOrganizationNiazsanjiDTO requestOrganizationNiazsanji = requestOrganizationNiazsanjiService.findOne(requestOrganizationNiazsanjiDTO.getId()).get();

        requestOrganizationNiazsanjiDTO.setCreateUserLogin(requestOrganizationNiazsanji.getCreateUserLogin());
        requestOrganizationNiazsanjiDTO.setCreateDate(requestOrganizationNiazsanji.getCreateDate());
        requestOrganizationNiazsanjiDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        requestOrganizationNiazsanjiDTO.setModifyDate(ZonedDateTime.now());
        requestOrganizationNiazsanjiDTO.setChangeStatusUserLogin(SecurityUtils.getCurrentUserLogin().get());
        requestOrganizationNiazsanjiDTO.setStatus(0);


        RequestOrganizationNiazsanjiDTO result = requestOrganizationNiazsanjiService.finalize(requestOrganizationNiazsanjiDTO);
        return ResponseEntity.created(new URI("/api/request-organization-niazsanjis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /request-organization-niazsanjis : Updates an existing requestOrganizationNiazsanji.
     *
     * @param requestOrganizationNiazsanjiDTO the requestOrganizationNiazsanjiDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated requestOrganizationNiazsanjiDTO,
     * or with status 400 (Bad Request) if the requestOrganizationNiazsanjiDTO is not valid,
     * or with status 500 (Internal Server Error) if the requestOrganizationNiazsanjiDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/request-organization-niazsanjis")
    @Timed
    public ResponseEntity<RequestOrganizationNiazsanjiDTO> updateRequestOrganizationNiazsanji(@Valid @RequestBody RequestOrganizationNiazsanjiDTO requestOrganizationNiazsanjiDTO) throws URISyntaxException {
        log.debug("REST request to update RequestOrganizationNiazsanji : {}", requestOrganizationNiazsanjiDTO);
        if (requestOrganizationNiazsanjiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        RequestOrganizationNiazsanjiDTO requestOrganizationNiazsanji = requestOrganizationNiazsanjiService.findOne(requestOrganizationNiazsanjiDTO.getId()).get();

        requestOrganizationNiazsanjiDTO.setCreateUserLogin(requestOrganizationNiazsanji.getCreateUserLogin());
        requestOrganizationNiazsanjiDTO.setCreateDate(requestOrganizationNiazsanji.getCreateDate());
        requestOrganizationNiazsanjiDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        requestOrganizationNiazsanjiDTO.setModifyDate(ZonedDateTime.now());
        //requestOrganizationNiazsanjiDTO.setChangeStatusUserLogin(SecurityUtils.getCurrentUserLogin().get());

        RequestOrganizationNiazsanjiDTO result = requestOrganizationNiazsanjiService.save(requestOrganizationNiazsanjiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, requestOrganizationNiazsanjiDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /request-organization-niazsanjis : get all the requestOrganizationNiazsanjis.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of requestOrganizationNiazsanjis in body
     */
    @GetMapping("/request-organization-niazsanjis")
    @Timed
    public ResponseEntity<List<RequestOrganizationNiazsanjiDTO>> getAllRequestOrganizationNiazsanjis(RequestOrganizationNiazsanjiCriteria criteria, Pageable pageable) {
        log.debug("REST request to get RequestOrganizationNiazsanjis by criteria: {}", criteria);
        Page<RequestOrganizationNiazsanjiDTO> page = requestOrganizationNiazsanjiQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/request-organization-niazsanjis");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /request-organization-niazsanjis/count : count all the requestOrganizationNiazsanjis.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/request-organization-niazsanjis/count")
    @Timed
    public ResponseEntity<Long> countRequestOrganizationNiazsanjis (RequestOrganizationNiazsanjiCriteria criteria) {
        log.debug("REST request to count RequestOrganizationNiazsanjis by criteria: {}", criteria);
        return ResponseEntity.ok().body(requestOrganizationNiazsanjiQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /request-organization-niazsanjis/:id : get the "id" requestOrganizationNiazsanji.
     *
     * @param id the id of the requestOrganizationNiazsanjiDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the requestOrganizationNiazsanjiDTO, or with status 404 (Not Found)
     */
    @GetMapping("/request-organization-niazsanjis/{id}")
    @Timed
    public ResponseEntity<RequestOrganizationNiazsanjiDTO> getRequestOrganizationNiazsanji(@PathVariable Long id) {
        log.debug("REST request to get RequestOrganizationNiazsanji : {}", id);
        Optional<RequestOrganizationNiazsanjiDTO> requestOrganizationNiazsanjiDTO = requestOrganizationNiazsanjiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(requestOrganizationNiazsanjiDTO);
    }

    /**
     * DELETE  /request-organization-niazsanjis/:id : delete the "id" requestOrganizationNiazsanji.
     *
     * @param id the id of the requestOrganizationNiazsanjiDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/request-organization-niazsanjis/{id}")
    @Timed
    public ResponseEntity<Void> deleteRequestOrganizationNiazsanji(@PathVariable Long id) {
        log.debug("REST request to delete RequestOrganizationNiazsanji : {}", id);
        requestOrganizationNiazsanjiService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
