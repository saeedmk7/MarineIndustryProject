package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.BeautySpeechAuthorityService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.BeautySpeechAuthorityDTO;
import com.marineindustryproj.service.dto.BeautySpeechAuthorityCriteria;
import com.marineindustryproj.service.BeautySpeechAuthorityQueryService;
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

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing BeautySpeechAuthority.
 */
@RestController
@RequestMapping("/api")
public class BeautySpeechAuthorityResource {

    private final Logger log = LoggerFactory.getLogger(BeautySpeechAuthorityResource.class);

    private static final String ENTITY_NAME = "beautySpeechAuthority";

    private final BeautySpeechAuthorityService beautySpeechAuthorityService;

    private final BeautySpeechAuthorityQueryService beautySpeechAuthorityQueryService;

    public BeautySpeechAuthorityResource(BeautySpeechAuthorityService beautySpeechAuthorityService, BeautySpeechAuthorityQueryService beautySpeechAuthorityQueryService) {
        this.beautySpeechAuthorityService = beautySpeechAuthorityService;
        this.beautySpeechAuthorityQueryService = beautySpeechAuthorityQueryService;
    }

    /**
     * POST  /beauty-speech-authorities : Create a new beautySpeechAuthority.
     *
     * @param beautySpeechAuthorityDTO the beautySpeechAuthorityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new beautySpeechAuthorityDTO, or with status 400 (Bad Request) if the beautySpeechAuthority has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/beauty-speech-authorities")
    @Timed
    public ResponseEntity<BeautySpeechAuthorityDTO> createBeautySpeechAuthority(@Valid @RequestBody BeautySpeechAuthorityDTO beautySpeechAuthorityDTO) throws URISyntaxException {
        log.debug("REST request to save BeautySpeechAuthority : {}", beautySpeechAuthorityDTO);
        if (beautySpeechAuthorityDTO.getId() != null) {
            throw new BadRequestAlertException("A new beautySpeechAuthority cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BeautySpeechAuthorityDTO result = beautySpeechAuthorityService.save(beautySpeechAuthorityDTO);
        return ResponseEntity.created(new URI("/api/beauty-speech-authorities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /beauty-speech-authorities : Updates an existing beautySpeechAuthority.
     *
     * @param beautySpeechAuthorityDTO the beautySpeechAuthorityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated beautySpeechAuthorityDTO,
     * or with status 400 (Bad Request) if the beautySpeechAuthorityDTO is not valid,
     * or with status 500 (Internal Server Error) if the beautySpeechAuthorityDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/beauty-speech-authorities")
    @Timed
    public ResponseEntity<BeautySpeechAuthorityDTO> updateBeautySpeechAuthority(@Valid @RequestBody BeautySpeechAuthorityDTO beautySpeechAuthorityDTO) throws URISyntaxException {
        log.debug("REST request to update BeautySpeechAuthority : {}", beautySpeechAuthorityDTO);
        if (beautySpeechAuthorityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BeautySpeechAuthorityDTO result = beautySpeechAuthorityService.save(beautySpeechAuthorityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, beautySpeechAuthorityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /beauty-speech-authorities : get all the beautySpeechAuthorities.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of beautySpeechAuthorities in body
     */
    @GetMapping("/beauty-speech-authorities")
    @Timed
    public ResponseEntity<List<BeautySpeechAuthorityDTO>> getAllBeautySpeechAuthorities(BeautySpeechAuthorityCriteria criteria, Pageable pageable) {
        log.debug("REST request to get BeautySpeechAuthorities by criteria: {}", criteria);
        Page<BeautySpeechAuthorityDTO> page = beautySpeechAuthorityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/beauty-speech-authorities");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /beauty-speech-authorities/count : count all the beautySpeechAuthorities.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/beauty-speech-authorities/count")
    @Timed
    public ResponseEntity<Long> countBeautySpeechAuthorities(BeautySpeechAuthorityCriteria criteria) {
        log.debug("REST request to count BeautySpeechAuthorities by criteria: {}", criteria);
        return ResponseEntity.ok().body(beautySpeechAuthorityQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /beauty-speech-authorities/:id : get the "id" beautySpeechAuthority.
     *
     * @param id the id of the beautySpeechAuthorityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the beautySpeechAuthorityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/beauty-speech-authorities/{id}")
    @Timed
    public ResponseEntity<BeautySpeechAuthorityDTO> getBeautySpeechAuthority(@PathVariable Long id) {
        log.debug("REST request to get BeautySpeechAuthority : {}", id);
        Optional<BeautySpeechAuthorityDTO> beautySpeechAuthorityDTO = beautySpeechAuthorityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(beautySpeechAuthorityDTO);
    }

    /**
     * DELETE  /beauty-speech-authorities/:id : delete the "id" beautySpeechAuthority.
     *
     * @param id the id of the beautySpeechAuthorityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/beauty-speech-authorities/{id}")
    @Timed
    public ResponseEntity<Void> deleteBeautySpeechAuthority(@PathVariable Long id) {
        log.debug("REST request to delete BeautySpeechAuthority : {}", id);
        beautySpeechAuthorityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
