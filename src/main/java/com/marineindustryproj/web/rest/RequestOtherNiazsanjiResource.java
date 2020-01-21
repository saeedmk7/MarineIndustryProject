package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.domain.RequestOtherNiazsanji;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.PersonService;
import com.marineindustryproj.service.RequestOtherNiazsanjiService;
import com.marineindustryproj.service.dto.PersonDTO;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.RequestOtherNiazsanjiDTO;
import com.marineindustryproj.service.dto.RequestOtherNiazsanjiCriteria;
import com.marineindustryproj.service.RequestOtherNiazsanjiQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing RequestOtherNiazsanji.
 */
@RestController
@RequestMapping("/api")
public class RequestOtherNiazsanjiResource {

    private final Logger log = LoggerFactory.getLogger(RequestOtherNiazsanjiResource.class);

    private static final String ENTITY_NAME = "requestOtherNiazsanji";

    private final RequestOtherNiazsanjiService requestOtherNiazsanjiService;

    private final RequestOtherNiazsanjiQueryService requestOtherNiazsanjiQueryService;

    private final PersonService personService;

    public RequestOtherNiazsanjiResource(RequestOtherNiazsanjiService requestOtherNiazsanjiService, RequestOtherNiazsanjiQueryService requestOtherNiazsanjiQueryService, PersonService personService) {
        this.requestOtherNiazsanjiService = requestOtherNiazsanjiService;
        this.requestOtherNiazsanjiQueryService = requestOtherNiazsanjiQueryService;
        this.personService = personService;
    }

    /**
     * POST  /request-other-niazsanjis : Create a new requestOtherNiazsanji.
     *
     * @param requestOtherNiazsanjiDTO the requestOtherNiazsanjiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new requestOtherNiazsanjiDTO, or with status 400 (Bad Request) if the requestOtherNiazsanji has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/request-other-niazsanjis")
    @Timed
    public ResponseEntity<RequestOtherNiazsanjiDTO> createRequestOtherNiazsanji(@Valid @RequestBody RequestOtherNiazsanjiDTO requestOtherNiazsanjiDTO) throws URISyntaxException {
        log.debug("REST request to save RequestOtherNiazsanji : {}", requestOtherNiazsanjiDTO);
        if (requestOtherNiazsanjiDTO.getId() != null) {
            throw new BadRequestAlertException("A new requestOtherNiazsanji cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PersonDTO personDTO = personService.findOne(requestOtherNiazsanjiDTO.getPersonId()).get();
        requestOtherNiazsanjiDTO.setCreateDate(ZonedDateTime.now());
        requestOtherNiazsanjiDTO.setCreateUserLogin(personDTO.getNationalId());
        requestOtherNiazsanjiDTO.setModifyDate(ZonedDateTime.now());
        requestOtherNiazsanjiDTO.setModifyUserLogin(personDTO.getNationalId());
        RequestOtherNiazsanjiDTO result = requestOtherNiazsanjiService.save(requestOtherNiazsanjiDTO);
        return ResponseEntity.created(new URI("/api/request-other-niazsanjis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * POST  /request-other-niazsanjis : Create a new requestOtherNiazsanji.
     *
     * @param requestOtherNiazsanjiDTO the requestOtherNiazsanjiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new requestOtherNiazsanjiDTO, or with status 400 (Bad Request) if the requestOtherNiazsanji has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/finalize-request-other-niazsanjis")
    @Timed
    public ResponseEntity<RequestOtherNiazsanjiDTO> finalizeRequestOtherNiazsanji(@Valid @RequestBody RequestOtherNiazsanjiDTO requestOtherNiazsanjiDTO) throws URISyntaxException {
        log.debug("REST request to finalize RequestOtherNiazsanji : {}", requestOtherNiazsanjiDTO);
        if (requestOtherNiazsanjiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        RequestOtherNiazsanjiDTO requestOtherNiazsanji = requestOtherNiazsanjiService.findOne(requestOtherNiazsanjiDTO.getId()).get();

        requestOtherNiazsanjiDTO.setCreateUserLogin(requestOtherNiazsanji.getCreateUserLogin());
        requestOtherNiazsanjiDTO.setCreateDate(requestOtherNiazsanji.getCreateDate());
        requestOtherNiazsanjiDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        requestOtherNiazsanjiDTO.setModifyDate(ZonedDateTime.now());
        requestOtherNiazsanjiDTO.setChangeStatusUserLogin(SecurityUtils.getCurrentUserLogin().get());
        requestOtherNiazsanjiDTO.setStatus(0);


        RequestOtherNiazsanjiDTO result = requestOtherNiazsanjiService.finalize(requestOtherNiazsanjiDTO);
        return ResponseEntity.created(new URI("/api/request-other-niazsanjis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /request-other-niazsanjis : Updates an existing requestOtherNiazsanji.
     *
     * @param requestOtherNiazsanjiDTO the requestOtherNiazsanjiDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated requestOtherNiazsanjiDTO,
     * or with status 400 (Bad Request) if the requestOtherNiazsanjiDTO is not valid,
     * or with status 500 (Internal Server Error) if the requestOtherNiazsanjiDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/request-other-niazsanjis")
    @Timed
    public ResponseEntity<RequestOtherNiazsanjiDTO> updateRequestOtherNiazsanji(@Valid @RequestBody RequestOtherNiazsanjiDTO requestOtherNiazsanjiDTO) throws URISyntaxException {
        log.debug("REST request to update RequestOtherNiazsanji : {}", requestOtherNiazsanjiDTO);
        if (requestOtherNiazsanjiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        RequestOtherNiazsanjiDTO requestOtherNiazsanji = requestOtherNiazsanjiService.findOne(requestOtherNiazsanjiDTO.getId()).get();

        requestOtherNiazsanjiDTO.setCreateUserLogin(requestOtherNiazsanji.getCreateUserLogin());
        requestOtherNiazsanjiDTO.setCreateDate(requestOtherNiazsanji.getCreateDate());
        requestOtherNiazsanjiDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        requestOtherNiazsanjiDTO.setModifyDate(ZonedDateTime.now());

        RequestOtherNiazsanjiDTO result = requestOtherNiazsanjiService.save(requestOtherNiazsanjiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, requestOtherNiazsanjiDTO.getId().toString()))
            .body(result);
    }

    @PutMapping("/request-other-niazsanjis/toggleImportantMessage/{id}/{type}")
    @Timed
    public ResponseEntity<RequestOtherNiazsanjiDTO> toggleImportantMessage(@PathVariable long id, @PathVariable boolean type) throws URISyntaxException {
        log.debug("REST request to toggleImportantMessage RequestOrganizationNiazsanji : {}", id);

        RequestOtherNiazsanjiDTO requestOtherNiazsanji = requestOtherNiazsanjiService.findOne(id).get();

        requestOtherNiazsanji.setHasImportantMessage(type);
        requestOtherNiazsanji.setModifyDate(ZonedDateTime.now());
        //requestOrganizationNiazsanjiDTO.setChangeStatusUserLogin(SecurityUtils.getCurrentUserLogin().get());

        RequestOtherNiazsanjiDTO result = requestOtherNiazsanjiService.save(requestOtherNiazsanji);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, requestOtherNiazsanji.getId().toString()))
            .body(result);
    }
    /**
     * GET  /request-other-niazsanjis : get all the requestOtherNiazsanjis.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of requestOtherNiazsanjis in body
     */
    @GetMapping("/request-other-niazsanjis")
    @Timed
    public ResponseEntity<List<RequestOtherNiazsanjiDTO>> getAllRequestOtherNiazsanjis(RequestOtherNiazsanjiCriteria criteria, Pageable pageable) {
        log.debug("REST request to get RequestOtherNiazsanjis by criteria: {}", criteria);
        Page<RequestOtherNiazsanjiDTO> page = requestOtherNiazsanjiQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/request-other-niazsanjis");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /request-other-niazsanjis/count : count all the requestOtherNiazsanjis.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/request-other-niazsanjis/count")
    @Timed
    public ResponseEntity<Long> countRequestOtherNiazsanjis(RequestOtherNiazsanjiCriteria criteria) {
        log.debug("REST request to count RequestOtherNiazsanjis by criteria: {}", criteria);
        return ResponseEntity.ok().body(requestOtherNiazsanjiQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /request-other-niazsanjis/:id : get the "id" requestOtherNiazsanji.
     *
     * @param id the id of the requestOtherNiazsanjiDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the requestOtherNiazsanjiDTO, or with status 404 (Not Found)
     */
    @GetMapping("/request-other-niazsanjis/{id}")
    @Timed
    public ResponseEntity<RequestOtherNiazsanjiDTO> getRequestOtherNiazsanji(@PathVariable Long id) {
        log.debug("REST request to get RequestOtherNiazsanji : {}", id);
        Optional<RequestOtherNiazsanjiDTO> requestOtherNiazsanjiDTO = requestOtherNiazsanjiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(requestOtherNiazsanjiDTO);
    }

    /**
     * DELETE  /request-other-niazsanjis/:id : delete the "id" requestOtherNiazsanji.
     *
     * @param id the id of the requestOtherNiazsanjiDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/request-other-niazsanjis/{id}")
    @Timed
    public ResponseEntity<Void> deleteRequestOtherNiazsanji(@PathVariable Long id) {
        log.debug("REST request to delete RequestOtherNiazsanji : {}", id);
        requestOtherNiazsanjiService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
