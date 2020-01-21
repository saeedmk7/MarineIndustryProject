package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.domain.NiazsanjiOther;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.NiazsanjiOtherService;
import com.marineindustryproj.service.PersonService;
import com.marineindustryproj.service.RequestOtherNiazsanjiService;
import com.marineindustryproj.service.dto.PersonDTO;
import com.marineindustryproj.service.dto.RequestOtherNiazsanjiDTO;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.NiazsanjiOtherDTO;
import com.marineindustryproj.service.dto.NiazsanjiOtherCriteria;
import com.marineindustryproj.service.NiazsanjiOtherQueryService;
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
 * REST controller for managing NiazsanjiOther.
 */
@RestController
@RequestMapping("/api")
public class NiazsanjiOtherResource {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiOtherResource.class);

    private static final String ENTITY_NAME = "niazsanjiOther";

    private final NiazsanjiOtherService niazsanjiOtherService;

    private final NiazsanjiOtherQueryService niazsanjiOtherQueryService;

    private final RequestOtherNiazsanjiService requestOtherNiazsanjiService;

    private final PersonService personService;

    public NiazsanjiOtherResource(NiazsanjiOtherService niazsanjiOtherService, NiazsanjiOtherQueryService niazsanjiOtherQueryService, RequestOtherNiazsanjiService requestOtherNiazsanjiService, PersonService personService) {
        this.niazsanjiOtherService = niazsanjiOtherService;
        this.niazsanjiOtherQueryService = niazsanjiOtherQueryService;
        this.requestOtherNiazsanjiService = requestOtherNiazsanjiService;
        this.personService = personService;
    }

    /**
     * POST  /niazsanji-others : Create a new niazsanjiOther.
     *
     * @param niazsanjiOtherDTO the niazsanjiOtherDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new niazsanjiOtherDTO, or with status 400 (Bad Request) if the niazsanjiOther has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/niazsanji-others")
    @Timed
    public ResponseEntity<NiazsanjiOtherDTO> createNiazsanjiOther(@Valid @RequestBody NiazsanjiOtherDTO niazsanjiOtherDTO) throws URISyntaxException {
        log.debug("REST request to save NiazsanjiOther : {}", niazsanjiOtherDTO);
        if (niazsanjiOtherDTO.getId() != null) {
            throw new BadRequestAlertException("A new niazsanjiOther cannot already have an ID", ENTITY_NAME, "idexists");
        }

        PersonDTO personDTO = personService.findOne(niazsanjiOtherDTO.getPersonId()).get();
        niazsanjiOtherDTO.setCreateDate(ZonedDateTime.now());
        niazsanjiOtherDTO.setCreateUserLogin(personDTO.getNationalId());

        NiazsanjiOtherDTO result = niazsanjiOtherService.save(niazsanjiOtherDTO);
        return ResponseEntity.created(new URI("/api/niazsanji-others/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * POST  /niazsanji-fardis : Create a new niazsanjiOther.
     *
     * @param niazsanjiOtherDTO the niazsanjiOtherDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new niazsanjiOtherDTO, or with status 400 (Bad Request) if the niazsanjiOther has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/finalize-niazsanji-other")
    @Timed
    public ResponseEntity<NiazsanjiOtherDTO> finalizeNiazsanjiOther(@Valid @RequestBody NiazsanjiOtherDTO niazsanjiOtherDTO) throws URISyntaxException {
        log.debug("REST request to finalize NiazsanjiFardi : {}", niazsanjiOtherDTO);
        if (niazsanjiOtherDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NiazsanjiOtherDTO niazsanjiOther = niazsanjiOtherService.findOne(niazsanjiOtherDTO.getId()).get();

        niazsanjiOtherDTO.setCreateUserLogin(niazsanjiOther.getCreateUserLogin());
        niazsanjiOtherDTO.setCreateDate(niazsanjiOther.getCreateDate());
        niazsanjiOtherDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        niazsanjiOtherDTO.setModifyDate(ZonedDateTime.now());
        niazsanjiOtherDTO.setStatus(30);
        niazsanjiOtherDTO.setChangeStatusUserLogin(SecurityUtils.getCurrentUserLogin().get());

        NiazsanjiOtherDTO result = niazsanjiOtherService.finalize(niazsanjiOtherDTO);
        return ResponseEntity.created(new URI("/api/niazsanji-fardis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /niazsanji-others : Updates an existing niazsanjiOther.
     *
     * @param niazsanjiOtherDTO the niazsanjiOtherDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated niazsanjiOtherDTO,
     * or with status 400 (Bad Request) if the niazsanjiOtherDTO is not valid,
     * or with status 500 (Internal Server Error) if the niazsanjiOtherDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/niazsanji-others")
    @Timed
    public ResponseEntity<NiazsanjiOtherDTO> updateNiazsanjiOther(@Valid @RequestBody NiazsanjiOtherDTO niazsanjiOtherDTO) throws URISyntaxException {
        log.debug("REST request to update NiazsanjiOther : {}", niazsanjiOtherDTO);
        if (niazsanjiOtherDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        NiazsanjiOtherDTO niazsanjiOther = niazsanjiOtherService.findOne(niazsanjiOtherDTO.getId()).get();

        niazsanjiOtherDTO.setCreateUserLogin(niazsanjiOther.getCreateUserLogin());
        niazsanjiOtherDTO.setCreateDate(niazsanjiOther.getCreateDate());
        niazsanjiOtherDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        niazsanjiOtherDTO.setModifyDate(ZonedDateTime.now());

        NiazsanjiOtherDTO result = niazsanjiOtherService.save(niazsanjiOtherDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, niazsanjiOtherDTO.getId().toString()))
            .body(result);
    }

    @PutMapping("/niazsanji-others/toggleImportantMessage/{id}/{type}")
    @Timed
    public ResponseEntity<NiazsanjiOtherDTO> toggleImportantMessage(@PathVariable long id, @PathVariable boolean type) throws URISyntaxException {
        NiazsanjiOtherDTO niazsanjiOther = niazsanjiOtherService.findOne(id).get();

        niazsanjiOther.setModifyDate(ZonedDateTime.now());
        niazsanjiOther.setHasImportantMessage(type);
        NiazsanjiOtherDTO result = niazsanjiOtherService.save(niazsanjiOther);

        Long requestOtherNiazsanjiId = niazsanjiOther.getRequestOtherNiazsanjiId();
        RequestOtherNiazsanjiDTO requestOtherNiazsanji = requestOtherNiazsanjiService.findOne(requestOtherNiazsanjiId).get();
        requestOtherNiazsanji.setModifyDate(ZonedDateTime.now());
        requestOtherNiazsanji.setHasImportantMessage(type);
        requestOtherNiazsanjiService.save(requestOtherNiazsanji);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, niazsanjiOther.getId().toString()))
            .body(result);
    }

    /**
     * GET  /niazsanji-others : get all the niazsanjiOthers.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of niazsanjiOthers in body
     */
    @GetMapping("/niazsanji-others")
    @Timed
    public ResponseEntity<List<NiazsanjiOtherDTO>> getAllNiazsanjiOthers(NiazsanjiOtherCriteria criteria, Pageable pageable) {
        log.debug("REST request to get NiazsanjiOthers by criteria: {}", criteria);
        Page<NiazsanjiOtherDTO> page = niazsanjiOtherQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/niazsanji-others");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /niazsanji-others/count : count all the niazsanjiOthers.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/niazsanji-others/count")
    @Timed
    public ResponseEntity<Long> countNiazsanjiOthers(NiazsanjiOtherCriteria criteria) {
        log.debug("REST request to count NiazsanjiOthers by criteria: {}", criteria);
        return ResponseEntity.ok().body(niazsanjiOtherQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /niazsanji-others/:id : get the "id" niazsanjiOther.
     *
     * @param id the id of the niazsanjiOtherDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the niazsanjiOtherDTO, or with status 404 (Not Found)
     */
    @GetMapping("/niazsanji-others/{id}")
    @Timed
    public ResponseEntity<NiazsanjiOtherDTO> getNiazsanjiOther(@PathVariable Long id) {
        log.debug("REST request to get NiazsanjiOther : {}", id);
        Optional<NiazsanjiOtherDTO> niazsanjiOtherDTO = niazsanjiOtherService.findOne(id);
        return ResponseUtil.wrapOrNotFound(niazsanjiOtherDTO);
    }

    /**
     * DELETE  /niazsanji-others/:id : delete the "id" niazsanjiOther.
     *
     * @param id the id of the niazsanjiOtherDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/niazsanji-others/{id}")
    @Timed
    public ResponseEntity<Void> deleteNiazsanjiOther(@PathVariable Long id) {
        log.debug("REST request to delete NiazsanjiOther : {}", id);
        niazsanjiOtherService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
