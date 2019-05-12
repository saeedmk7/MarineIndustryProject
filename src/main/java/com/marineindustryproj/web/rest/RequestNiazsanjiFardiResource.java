package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.PersonService;
import com.marineindustryproj.service.RequestNiazsanjiFardiService;
import com.marineindustryproj.service.dto.PersonDTO;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.RequestNiazsanjiFardiDTO;
import com.marineindustryproj.service.dto.RequestNiazsanjiFardiCriteria;
import com.marineindustryproj.service.RequestNiazsanjiFardiQueryService;
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
 * REST controller for managing RequestNiazsanjiFardi.
 */
@RestController
@RequestMapping("/api")
public class RequestNiazsanjiFardiResource {

    private final Logger log = LoggerFactory.getLogger(RequestNiazsanjiFardiResource.class);

    private static final String ENTITY_NAME = "requestNiazsanjiFardi";

    private final RequestNiazsanjiFardiService requestNiazsanjiFardiService;

    private final RequestNiazsanjiFardiQueryService requestNiazsanjiFardiQueryService;

    private final PersonService personService;

    public RequestNiazsanjiFardiResource(RequestNiazsanjiFardiService requestNiazsanjiFardiService,
                                         RequestNiazsanjiFardiQueryService requestNiazsanjiFardiQueryService,
                                         PersonService personService) {
        this.requestNiazsanjiFardiService = requestNiazsanjiFardiService;
        this.requestNiazsanjiFardiQueryService = requestNiazsanjiFardiQueryService;
        this.personService = personService;
    }

    /**
     * POST  /request-niazsanji-fardis : Create a new requestNiazsanjiFardi.
     *
     * @param requestNiazsanjiFardiDTO the requestNiazsanjiFardiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new requestNiazsanjiFardiDTO, or with status 400 (Bad Request) if the requestNiazsanjiFardi has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/request-niazsanji-fardis")
    @Timed
    public ResponseEntity<RequestNiazsanjiFardiDTO> createRequestNiazsanjiFardi(@Valid @RequestBody RequestNiazsanjiFardiDTO requestNiazsanjiFardiDTO) throws URISyntaxException {
        log.debug("REST request to save RequestNiazsanjiFardi : {}", requestNiazsanjiFardiDTO);
        if (requestNiazsanjiFardiDTO.getId() != null) {
            throw new BadRequestAlertException("A new requestNiazsanjiFardi cannot already have an ID", ENTITY_NAME, "idexists");
        }

        PersonDTO personDTO = personService.findOne(requestNiazsanjiFardiDTO.getPersonId()).get();
        requestNiazsanjiFardiDTO.setCreateDate(ZonedDateTime.now());
        requestNiazsanjiFardiDTO.setCreateUserLogin(personDTO.getNationalId());
        //requestNiazsanjiFardiDTO.setChangeStatusUserLogin(SecurityUtils.getCurrentUserLogin().get());

        RequestNiazsanjiFardiDTO result = requestNiazsanjiFardiService.save(requestNiazsanjiFardiDTO);
        return ResponseEntity.created(new URI("/api/request-niazsanji-fardis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    /**
     * POST  /request-niazsanji-fardis : Create a new requestNiazsanjiFardi.
     *
     * @param requestNiazsanjiFardiDTO the requestNiazsanjiFardiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new requestNiazsanjiFardiDTO, or with status 400 (Bad Request) if the requestNiazsanjiFardi has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/finalize-request-niazsanji-fardi")
    @Timed
    public ResponseEntity<RequestNiazsanjiFardiDTO> finalizeRequestNiazsanjiFardi(@Valid @RequestBody RequestNiazsanjiFardiDTO requestNiazsanjiFardiDTO) throws URISyntaxException {
        log.debug("REST request to finalize RequestNiazsanjiFardi : {}", requestNiazsanjiFardiDTO);
        if (requestNiazsanjiFardiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        RequestNiazsanjiFardiDTO requestOrganizationNiazsanji = requestNiazsanjiFardiService.findOne(requestNiazsanjiFardiDTO.getId()).get();

        requestNiazsanjiFardiDTO.setCreateUserLogin(requestOrganizationNiazsanji.getCreateUserLogin());
        requestNiazsanjiFardiDTO.setCreateDate(requestOrganizationNiazsanji.getCreateDate());
        requestNiazsanjiFardiDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        requestNiazsanjiFardiDTO.setModifyDate(ZonedDateTime.now());
        requestNiazsanjiFardiDTO.setChangeStatusUserLogin(SecurityUtils.getCurrentUserLogin().get());
        //requestNiazsanjiFardiDTO.setRequestStatus(RequestStatus.ACCEPT);
        requestNiazsanjiFardiDTO.setStatus(0);


        RequestNiazsanjiFardiDTO result = requestNiazsanjiFardiService.finalize(requestNiazsanjiFardiDTO);
        return ResponseEntity.created(new URI("/api/request-niazsanji-fardis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /request-niazsanji-fardis : Updates an existing requestNiazsanjiFardi.
     *
     * @param requestNiazsanjiFardiDTO the requestNiazsanjiFardiDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated requestNiazsanjiFardiDTO,
     * or with status 400 (Bad Request) if the requestNiazsanjiFardiDTO is not valid,
     * or with status 500 (Internal Server Error) if the requestNiazsanjiFardiDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/request-niazsanji-fardis")
    @Timed
    public ResponseEntity<RequestNiazsanjiFardiDTO> updateRequestNiazsanjiFardi(@Valid @RequestBody RequestNiazsanjiFardiDTO requestNiazsanjiFardiDTO) throws URISyntaxException {
        log.debug("REST request to update RequestNiazsanjiFardi : {}", requestNiazsanjiFardiDTO);
        if (requestNiazsanjiFardiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        RequestNiazsanjiFardiDTO requestOrganizationNiazsanji = requestNiazsanjiFardiService.findOne(requestNiazsanjiFardiDTO.getId()).get();

        requestNiazsanjiFardiDTO.setCreateUserLogin(requestOrganizationNiazsanji.getCreateUserLogin());
        requestNiazsanjiFardiDTO.setCreateDate(requestOrganizationNiazsanji.getCreateDate());
        requestNiazsanjiFardiDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        requestNiazsanjiFardiDTO.setModifyDate(ZonedDateTime.now());

        RequestNiazsanjiFardiDTO result = requestNiazsanjiFardiService.save(requestNiazsanjiFardiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, requestNiazsanjiFardiDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /request-niazsanji-fardis : get all the requestNiazsanjiFardis.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of requestNiazsanjiFardis in body
     */
    @GetMapping("/request-niazsanji-fardis")
    @Timed
    public ResponseEntity<List<RequestNiazsanjiFardiDTO>> getAllRequestNiazsanjiFardis(RequestNiazsanjiFardiCriteria criteria, Pageable pageable) {
        log.debug("REST request to get RequestNiazsanjiFardis by criteria: {}", criteria);
        Page<RequestNiazsanjiFardiDTO> page = requestNiazsanjiFardiQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/request-niazsanji-fardis");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /request-niazsanji-fardis/count : count all the requestNiazsanjiFardis.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/request-niazsanji-fardis/count")
    @Timed
    public ResponseEntity<Long> countRequestNiazsanjiFardis(RequestNiazsanjiFardiCriteria criteria) {
        log.debug("REST request to count RequestNiazsanjiFardis by criteria: {}", criteria);
        return ResponseEntity.ok().body(requestNiazsanjiFardiQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /request-niazsanji-fardis/:id : get the "id" requestNiazsanjiFardi.
     *
     * @param id the id of the requestNiazsanjiFardiDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the requestNiazsanjiFardiDTO, or with status 404 (Not Found)
     */
    @GetMapping("/request-niazsanji-fardis/{id}")
    @Timed
    public ResponseEntity<RequestNiazsanjiFardiDTO> getRequestNiazsanjiFardi(@PathVariable Long id) {
        log.debug("REST request to get RequestNiazsanjiFardi : {}", id);
        Optional<RequestNiazsanjiFardiDTO> requestNiazsanjiFardiDTO = requestNiazsanjiFardiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(requestNiazsanjiFardiDTO);
    }

    /**
     * DELETE  /request-niazsanji-fardis/:id : delete the "id" requestNiazsanjiFardi.
     *
     * @param id the id of the requestNiazsanjiFardiDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/request-niazsanji-fardis/{id}")
    @Timed
    public ResponseEntity<Void> deleteRequestNiazsanjiFardi(@PathVariable Long id) {
        log.debug("REST request to delete RequestNiazsanjiFardi : {}", id);
        requestNiazsanjiFardiService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
