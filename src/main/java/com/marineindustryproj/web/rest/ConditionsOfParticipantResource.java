package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.ConditionsOfParticipantService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.ConditionsOfParticipantDTO;
import com.marineindustryproj.service.dto.ConditionsOfParticipantCriteria;
import com.marineindustryproj.service.ConditionsOfParticipantQueryService;
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
 * REST controller for managing ConditionsOfParticipant.
 */
@RestController
@RequestMapping("/api")
public class ConditionsOfParticipantResource {

    private final Logger log = LoggerFactory.getLogger(ConditionsOfParticipantResource.class);

    private static final String ENTITY_NAME = "conditionsOfParticipant";

    private final ConditionsOfParticipantService conditionsOfParticipantService;

    private final ConditionsOfParticipantQueryService conditionsOfParticipantQueryService;

    public ConditionsOfParticipantResource(ConditionsOfParticipantService conditionsOfParticipantService, ConditionsOfParticipantQueryService conditionsOfParticipantQueryService) {
        this.conditionsOfParticipantService = conditionsOfParticipantService;
        this.conditionsOfParticipantQueryService = conditionsOfParticipantQueryService;
    }

    /**
     * POST  /conditions-of-participants : Create a new conditionsOfParticipant.
     *
     * @param conditionsOfParticipantDTO the conditionsOfParticipantDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new conditionsOfParticipantDTO, or with status 400 (Bad Request) if the conditionsOfParticipant has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/conditions-of-participants")
    @Timed
    public ResponseEntity<ConditionsOfParticipantDTO> createConditionsOfParticipant(@Valid @RequestBody ConditionsOfParticipantDTO conditionsOfParticipantDTO) throws URISyntaxException {
        log.debug("REST request to save ConditionsOfParticipant : {}", conditionsOfParticipantDTO);
        if (conditionsOfParticipantDTO.getId() != null) {
            throw new BadRequestAlertException("A new conditionsOfParticipant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        conditionsOfParticipantDTO.setCreateDate(ZonedDateTime.now());
        conditionsOfParticipantDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        ConditionsOfParticipantDTO result = conditionsOfParticipantService.save(conditionsOfParticipantDTO);
        return ResponseEntity.created(new URI("/api/conditions-of-participants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /conditions-of-participants : Updates an existing conditionsOfParticipant.
     *
     * @param conditionsOfParticipantDTO the conditionsOfParticipantDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated conditionsOfParticipantDTO,
     * or with status 400 (Bad Request) if the conditionsOfParticipantDTO is not valid,
     * or with status 500 (Internal Server Error) if the conditionsOfParticipantDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/conditions-of-participants")
    @Timed
    public ResponseEntity<ConditionsOfParticipantDTO> updateConditionsOfParticipant(@Valid @RequestBody ConditionsOfParticipantDTO conditionsOfParticipantDTO) throws URISyntaxException {
        log.debug("REST request to update ConditionsOfParticipant : {}", conditionsOfParticipantDTO);
        if (conditionsOfParticipantDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ConditionsOfParticipantDTO conditionsOfParticipant = conditionsOfParticipantService.findOne(conditionsOfParticipantDTO.getId()).get();

        conditionsOfParticipantDTO.setCreateUserLogin(conditionsOfParticipant.getCreateUserLogin());
        conditionsOfParticipantDTO.setCreateDate(conditionsOfParticipant.getCreateDate());
        conditionsOfParticipantDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        conditionsOfParticipantDTO.setModifyDate(ZonedDateTime.now());
        ConditionsOfParticipantDTO result = conditionsOfParticipantService.save(conditionsOfParticipantDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, conditionsOfParticipantDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /conditions-of-participants : get all the conditionsOfParticipants.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of conditionsOfParticipants in body
     */
    @GetMapping("/conditions-of-participants")
    @Timed
    public ResponseEntity<List<ConditionsOfParticipantDTO>> getAllConditionsOfParticipants(ConditionsOfParticipantCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ConditionsOfParticipants by criteria: {}", criteria);
        Page<ConditionsOfParticipantDTO> page = conditionsOfParticipantQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/conditions-of-participants");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /conditions-of-participants/count : count all the conditionsOfParticipants.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/conditions-of-participants/count")
    @Timed
    public ResponseEntity<Long> countConditionsOfParticipants (ConditionsOfParticipantCriteria criteria) {
        log.debug("REST request to count ConditionsOfParticipants by criteria: {}", criteria);
        return ResponseEntity.ok().body(conditionsOfParticipantQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /conditions-of-participants/:id : get the "id" conditionsOfParticipant.
     *
     * @param id the id of the conditionsOfParticipantDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the conditionsOfParticipantDTO, or with status 404 (Not Found)
     */
    @GetMapping("/conditions-of-participants/{id}")
    @Timed
    public ResponseEntity<ConditionsOfParticipantDTO> getConditionsOfParticipant(@PathVariable Long id) {
        log.debug("REST request to get ConditionsOfParticipant : {}", id);
        Optional<ConditionsOfParticipantDTO> conditionsOfParticipantDTO = conditionsOfParticipantService.findOne(id);
        return ResponseUtil.wrapOrNotFound(conditionsOfParticipantDTO);
    }

    /**
     * DELETE  /conditions-of-participants/:id : delete the "id" conditionsOfParticipant.
     *
     * @param id the id of the conditionsOfParticipantDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/conditions-of-participants/{id}")
    @Timed
    public ResponseEntity<Void> deleteConditionsOfParticipant(@PathVariable Long id) {
        log.debug("REST request to delete ConditionsOfParticipant : {}", id);
        conditionsOfParticipantService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
