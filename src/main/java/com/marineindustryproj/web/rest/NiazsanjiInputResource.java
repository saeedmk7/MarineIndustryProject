package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.NiazsanjiInputService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.NiazsanjiInputDTO;
import com.marineindustryproj.service.dto.NiazsanjiInputCriteria;
import com.marineindustryproj.service.NiazsanjiInputQueryService;
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
 * REST controller for managing NiazsanjiInput.
 */
@RestController
@RequestMapping("/api")
public class NiazsanjiInputResource {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiInputResource.class);

    private static final String ENTITY_NAME = "niazsanjiInput";

    private final NiazsanjiInputService niazsanjiInputService;

    private final NiazsanjiInputQueryService niazsanjiInputQueryService;

    public NiazsanjiInputResource(NiazsanjiInputService niazsanjiInputService, NiazsanjiInputQueryService niazsanjiInputQueryService) {
        this.niazsanjiInputService = niazsanjiInputService;
        this.niazsanjiInputQueryService = niazsanjiInputQueryService;
    }

    /**
     * POST  /niazsanji-inputs : Create a new niazsanjiInput.
     *
     * @param niazsanjiInputDTO the niazsanjiInputDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new niazsanjiInputDTO, or with status 400 (Bad Request) if the niazsanjiInput has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/niazsanji-inputs")
    @Timed
    public ResponseEntity<NiazsanjiInputDTO> createNiazsanjiInput(@Valid @RequestBody NiazsanjiInputDTO niazsanjiInputDTO) throws URISyntaxException {
        log.debug("REST request to save NiazsanjiInput : {}", niazsanjiInputDTO);
        if (niazsanjiInputDTO.getId() != null) {
            throw new BadRequestAlertException("A new niazsanjiInput cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NiazsanjiInputDTO result = niazsanjiInputService.save(niazsanjiInputDTO);
        return ResponseEntity.created(new URI("/api/niazsanji-inputs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /niazsanji-inputs : Updates an existing niazsanjiInput.
     *
     * @param niazsanjiInputDTO the niazsanjiInputDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated niazsanjiInputDTO,
     * or with status 400 (Bad Request) if the niazsanjiInputDTO is not valid,
     * or with status 500 (Internal Server Error) if the niazsanjiInputDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/niazsanji-inputs")
    @Timed
    public ResponseEntity<NiazsanjiInputDTO> updateNiazsanjiInput(@Valid @RequestBody NiazsanjiInputDTO niazsanjiInputDTO) throws URISyntaxException {
        log.debug("REST request to update NiazsanjiInput : {}", niazsanjiInputDTO);
        if (niazsanjiInputDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NiazsanjiInputDTO result = niazsanjiInputService.save(niazsanjiInputDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, niazsanjiInputDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /niazsanji-inputs : get all the niazsanjiInputs.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of niazsanjiInputs in body
     */
    @GetMapping("/niazsanji-inputs")
    @Timed
    public ResponseEntity<List<NiazsanjiInputDTO>> getAllNiazsanjiInputs(NiazsanjiInputCriteria criteria, Pageable pageable) {
        log.debug("REST request to get NiazsanjiInputs by criteria: {}", criteria);
        Page<NiazsanjiInputDTO> page = niazsanjiInputQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/niazsanji-inputs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /niazsanji-inputs/count : count all the niazsanjiInputs.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/niazsanji-inputs/count")
    @Timed
    public ResponseEntity<Long> countNiazsanjiInputs(NiazsanjiInputCriteria criteria) {
        log.debug("REST request to count NiazsanjiInputs by criteria: {}", criteria);
        return ResponseEntity.ok().body(niazsanjiInputQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /niazsanji-inputs/:id : get the "id" niazsanjiInput.
     *
     * @param id the id of the niazsanjiInputDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the niazsanjiInputDTO, or with status 404 (Not Found)
     */
    @GetMapping("/niazsanji-inputs/{id}")
    @Timed
    public ResponseEntity<NiazsanjiInputDTO> getNiazsanjiInput(@PathVariable Long id) {
        log.debug("REST request to get NiazsanjiInput : {}", id);
        Optional<NiazsanjiInputDTO> niazsanjiInputDTO = niazsanjiInputService.findOne(id);
        return ResponseUtil.wrapOrNotFound(niazsanjiInputDTO);
    }

    /**
     * DELETE  /niazsanji-inputs/:id : delete the "id" niazsanjiInput.
     *
     * @param id the id of the niazsanjiInputDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/niazsanji-inputs/{id}")
    @Timed
    public ResponseEntity<Void> deleteNiazsanjiInput(@PathVariable Long id) {
        log.debug("REST request to delete NiazsanjiInput : {}", id);
        niazsanjiInputService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
