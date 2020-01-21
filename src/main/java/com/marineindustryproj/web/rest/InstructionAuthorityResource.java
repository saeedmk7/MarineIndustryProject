package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.InstructionAuthorityService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.InstructionAuthorityDTO;
import com.marineindustryproj.service.dto.InstructionAuthorityCriteria;
import com.marineindustryproj.service.InstructionAuthorityQueryService;
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
 * REST controller for managing InstructionAuthority.
 */
@RestController
@RequestMapping("/api")
public class InstructionAuthorityResource {

    private final Logger log = LoggerFactory.getLogger(InstructionAuthorityResource.class);

    private static final String ENTITY_NAME = "instructionAuthority";

    private final InstructionAuthorityService instructionAuthorityService;

    private final InstructionAuthorityQueryService instructionAuthorityQueryService;

    public InstructionAuthorityResource(InstructionAuthorityService instructionAuthorityService, InstructionAuthorityQueryService instructionAuthorityQueryService) {
        this.instructionAuthorityService = instructionAuthorityService;
        this.instructionAuthorityQueryService = instructionAuthorityQueryService;
    }

    /**
     * POST  /instruction-authorities : Create a new instructionAuthority.
     *
     * @param instructionAuthorityDTO the instructionAuthorityDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new instructionAuthorityDTO, or with status 400 (Bad Request) if the instructionAuthority has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/instruction-authorities")
    @Timed
    public ResponseEntity<InstructionAuthorityDTO> createInstructionAuthority(@Valid @RequestBody InstructionAuthorityDTO instructionAuthorityDTO) throws URISyntaxException {
        log.debug("REST request to save InstructionAuthority : {}", instructionAuthorityDTO);
        if (instructionAuthorityDTO.getId() != null) {
            throw new BadRequestAlertException("A new instructionAuthority cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InstructionAuthorityDTO result = instructionAuthorityService.save(instructionAuthorityDTO);
        return ResponseEntity.created(new URI("/api/instruction-authorities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /instruction-authorities : Updates an existing instructionAuthority.
     *
     * @param instructionAuthorityDTO the instructionAuthorityDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated instructionAuthorityDTO,
     * or with status 400 (Bad Request) if the instructionAuthorityDTO is not valid,
     * or with status 500 (Internal Server Error) if the instructionAuthorityDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/instruction-authorities")
    @Timed
    public ResponseEntity<InstructionAuthorityDTO> updateInstructionAuthority(@Valid @RequestBody InstructionAuthorityDTO instructionAuthorityDTO) throws URISyntaxException {
        log.debug("REST request to update InstructionAuthority : {}", instructionAuthorityDTO);
        if (instructionAuthorityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InstructionAuthorityDTO result = instructionAuthorityService.save(instructionAuthorityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, instructionAuthorityDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /instruction-authorities : get all the instructionAuthorities.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of instructionAuthorities in body
     */
    @GetMapping("/instruction-authorities")
    @Timed
    public ResponseEntity<List<InstructionAuthorityDTO>> getAllInstructionAuthorities(InstructionAuthorityCriteria criteria, Pageable pageable) {
        log.debug("REST request to get InstructionAuthorities by criteria: {}", criteria);
        Page<InstructionAuthorityDTO> page = instructionAuthorityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/instruction-authorities");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /instruction-authorities/count : count all the instructionAuthorities.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/instruction-authorities/count")
    @Timed
    public ResponseEntity<Long> countInstructionAuthorities(InstructionAuthorityCriteria criteria) {
        log.debug("REST request to count InstructionAuthorities by criteria: {}", criteria);
        return ResponseEntity.ok().body(instructionAuthorityQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /instruction-authorities/:id : get the "id" instructionAuthority.
     *
     * @param id the id of the instructionAuthorityDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the instructionAuthorityDTO, or with status 404 (Not Found)
     */
    @GetMapping("/instruction-authorities/{id}")
    @Timed
    public ResponseEntity<InstructionAuthorityDTO> getInstructionAuthority(@PathVariable Long id) {
        log.debug("REST request to get InstructionAuthority : {}", id);
        Optional<InstructionAuthorityDTO> instructionAuthorityDTO = instructionAuthorityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(instructionAuthorityDTO);
    }

    /**
     * DELETE  /instruction-authorities/:id : delete the "id" instructionAuthority.
     *
     * @param id the id of the instructionAuthorityDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/instruction-authorities/{id}")
    @Timed
    public ResponseEntity<Void> deleteInstructionAuthority(@PathVariable Long id) {
        log.debug("REST request to delete InstructionAuthority : {}", id);
        instructionAuthorityService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
