package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.domain.NiazsanjiIntegration;
import com.marineindustryproj.domain.enumeration.RequestStatus;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.NiazsanjiIntegrationService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.NiazsanjiIntegrationDTO;
import com.marineindustryproj.service.dto.NiazsanjiIntegrationCriteria;
import com.marineindustryproj.service.NiazsanjiIntegrationQueryService;
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
import java.util.UUID;

/**
 * REST controller for managing NiazsanjiIntegration.
 */
@RestController
@RequestMapping("/api")
public class NiazsanjiIntegrationResource {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiIntegrationResource.class);

    private static final String ENTITY_NAME = "niazsanjiIntegration";

    private final NiazsanjiIntegrationService niazsanjiIntegrationService;

    private final NiazsanjiIntegrationQueryService niazsanjiIntegrationQueryService;

    public NiazsanjiIntegrationResource(NiazsanjiIntegrationService niazsanjiIntegrationService, NiazsanjiIntegrationQueryService niazsanjiIntegrationQueryService) {
        this.niazsanjiIntegrationService = niazsanjiIntegrationService;
        this.niazsanjiIntegrationQueryService = niazsanjiIntegrationQueryService;
    }

    /**
     * POST  /niazsanji-integrations : Create a new niazsanjiIntegration.
     *
     * @param niazsanjiIntegrationDTO the niazsanjiIntegrationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new niazsanjiIntegrationDTO, or with status 400 (Bad Request) if the niazsanjiIntegration has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/niazsanji-integrations")
    @Timed
    public ResponseEntity<NiazsanjiIntegrationDTO> createNiazsanjiIntegration(@Valid @RequestBody NiazsanjiIntegrationDTO niazsanjiIntegrationDTO) throws URISyntaxException {
        log.debug("REST request to save NiazsanjiIntegration : {}", niazsanjiIntegrationDTO);
        if (niazsanjiIntegrationDTO.getId() != null) {
            throw new BadRequestAlertException("A new niazsanjiIntegration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        niazsanjiIntegrationDTO.setCreateDate(ZonedDateTime.now());
        niazsanjiIntegrationDTO.setGuid(UUID.randomUUID().toString());
        NiazsanjiIntegrationDTO result = niazsanjiIntegrationService.save(niazsanjiIntegrationDTO);
        return ResponseEntity.created(new URI("/api/niazsanji-integrations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * POST  /request-other-niazsanjis : Create a new niazsanjiIntegration.
     *
     * @param niazsanjiIntegration the niazsanjiIntegration to create
     * @return the ResponseEntity with status 201 (Created) and with body the new niazsanjiIntegration, or with status 400 (Bad Request) if the niazsanjiIntegration has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/niazsanji-integrations/finalize")
    @Timed
    public ResponseEntity<NiazsanjiIntegrationDTO> FinalizeNiazsanjiIntegration(@Valid @RequestBody NiazsanjiIntegrationDTO niazsanjiIntegrationDTO) throws Exception {
        log.debug("REST request to finalize RequestOtherNiazsanji : {}", niazsanjiIntegrationDTO);
        if (niazsanjiIntegrationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        NiazsanjiIntegrationDTO niazsanjiIntegration = niazsanjiIntegrationService.findOne(niazsanjiIntegrationDTO.getId()).get();

        niazsanjiIntegrationDTO.setCreateDate(niazsanjiIntegration.getCreateDate());
        niazsanjiIntegrationDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        niazsanjiIntegrationDTO.setModifyDate(ZonedDateTime.now());
        niazsanjiIntegrationDTO.setChangeStatusUserLogin(SecurityUtils.getCurrentUserLogin().get());
        niazsanjiIntegrationDTO.setStatus(30);
        niazsanjiIntegrationDTO.setRequestStatus(RequestStatus.ACCEPT);


        NiazsanjiIntegrationDTO result = niazsanjiIntegrationService.finalize(niazsanjiIntegrationDTO);
        return ResponseEntity.created(new URI("/api/niazsanji-integrations/finalize" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /niazsanji-integrations : Updates an existing niazsanjiIntegration.
     *
     * @param niazsanjiIntegrationDTO the niazsanjiIntegrationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated niazsanjiIntegrationDTO,
     * or with status 400 (Bad Request) if the niazsanjiIntegrationDTO is not valid,
     * or with status 500 (Internal Server Error) if the niazsanjiIntegrationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/niazsanji-integrations")
    @Timed
    public ResponseEntity<NiazsanjiIntegrationDTO> updateNiazsanjiIntegration(@Valid @RequestBody NiazsanjiIntegrationDTO niazsanjiIntegrationDTO) throws URISyntaxException {
        log.debug("REST request to update NiazsanjiIntegration : {}", niazsanjiIntegrationDTO);
        if (niazsanjiIntegrationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        NiazsanjiIntegrationDTO niazsanjiIntegration = niazsanjiIntegrationService.findOne(niazsanjiIntegrationDTO.getId()).get();

        niazsanjiIntegrationDTO.setCreateDate(niazsanjiIntegration.getCreateDate());
        niazsanjiIntegrationDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        niazsanjiIntegrationDTO.setModifyDate(ZonedDateTime.now());

        NiazsanjiIntegrationDTO result = niazsanjiIntegrationService.save(niazsanjiIntegrationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, niazsanjiIntegrationDTO.getId().toString()))
            .body(result);
    }

    @PutMapping("/niazsanji-integrations/toggleImportantMessage/{id}/{type}")
    @Timed
    public ResponseEntity<NiazsanjiIntegrationDTO> toggleImportantMessage(@PathVariable long id, @PathVariable boolean type) throws URISyntaxException {

        NiazsanjiIntegrationDTO niazsanjiIntegration = niazsanjiIntegrationService.findOne(id).get();

        niazsanjiIntegration.setModifyDate(ZonedDateTime.now());
        niazsanjiIntegration.setHasImportantMessage(type);

        NiazsanjiIntegrationDTO result = niazsanjiIntegrationService.save(niazsanjiIntegration);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, niazsanjiIntegration.getId().toString()))
            .body(result);
    }

    /**
     * GET  /niazsanji-integrations : get all the niazsanjiIntegrations.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of niazsanjiIntegrations in body
     */
    @GetMapping("/niazsanji-integrations")
    @Timed
    public ResponseEntity<List<NiazsanjiIntegrationDTO>> getAllNiazsanjiIntegrations(NiazsanjiIntegrationCriteria criteria, Pageable pageable) {
        log.debug("REST request to get NiazsanjiIntegrations by criteria: {}", criteria);
        Page<NiazsanjiIntegrationDTO> page = niazsanjiIntegrationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/niazsanji-integrations");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /niazsanji-integrations/count : count all the niazsanjiIntegrations.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/niazsanji-integrations/count")
    @Timed
    public ResponseEntity<Long> countNiazsanjiIntegrations(NiazsanjiIntegrationCriteria criteria) {
        log.debug("REST request to count NiazsanjiIntegrations by criteria: {}", criteria);
        return ResponseEntity.ok().body(niazsanjiIntegrationQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /niazsanji-integrations/:id : get the "id" niazsanjiIntegration.
     *
     * @param id the id of the niazsanjiIntegrationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the niazsanjiIntegrationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/niazsanji-integrations/{id}")
    @Timed
    public ResponseEntity<NiazsanjiIntegrationDTO> getNiazsanjiIntegration(@PathVariable Long id) {
        log.debug("REST request to get NiazsanjiIntegration : {}", id);
        Optional<NiazsanjiIntegrationDTO> niazsanjiIntegrationDTO = niazsanjiIntegrationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(niazsanjiIntegrationDTO);
    }

    /**
     * DELETE  /niazsanji-integrations/:id : delete the "id" niazsanjiIntegration.
     *
     * @param id the id of the niazsanjiIntegrationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/niazsanji-integrations/{id}")
    @Timed
    public ResponseEntity<Void> deleteNiazsanjiIntegration(@PathVariable Long id) {
        log.debug("REST request to delete NiazsanjiIntegration : {}", id);
        niazsanjiIntegrationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
