package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.NiazsanjiFardiService;
import com.marineindustryproj.service.PersonService;
import com.marineindustryproj.service.dto.PersonDTO;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.NiazsanjiFardiDTO;
import com.marineindustryproj.service.dto.NiazsanjiFardiCriteria;
import com.marineindustryproj.service.NiazsanjiFardiQueryService;
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
 * REST controller for managing NiazsanjiFardi.
 */
@RestController
@RequestMapping("/api")
public class NiazsanjiFardiResource {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiFardiResource.class);

    private static final String ENTITY_NAME = "niazsanjiFardi";

    private final NiazsanjiFardiService niazsanjiFardiService;

    private final NiazsanjiFardiQueryService niazsanjiFardiQueryService;

    private final PersonService personService;

    public NiazsanjiFardiResource(NiazsanjiFardiService niazsanjiFardiService,
                                  NiazsanjiFardiQueryService niazsanjiFardiQueryService,
                                  PersonService personService) {
        this.niazsanjiFardiService = niazsanjiFardiService;
        this.niazsanjiFardiQueryService = niazsanjiFardiQueryService;
        this.personService = personService;
    }

    /**
     * POST  /niazsanji-fardis : Create a new niazsanjiFardi.
     *
     * @param niazsanjiFardiDTO the niazsanjiFardiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new niazsanjiFardiDTO, or with status 400 (Bad Request) if the niazsanjiFardi has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/niazsanji-fardis")
    @Timed
    public ResponseEntity<NiazsanjiFardiDTO> createNiazsanjiFardi(@Valid @RequestBody NiazsanjiFardiDTO niazsanjiFardiDTO) throws URISyntaxException {
        log.debug("REST request to save NiazsanjiFardi : {}", niazsanjiFardiDTO);
        if (niazsanjiFardiDTO.getId() != null) {
            throw new BadRequestAlertException("A new niazsanjiFardi cannot already have an ID", ENTITY_NAME, "idexists");
        }

        PersonDTO personDTO = personService.findOne(niazsanjiFardiDTO.getPersonId()).get();
        niazsanjiFardiDTO.setCreateDate(ZonedDateTime.now());
        niazsanjiFardiDTO.setCreateUserLogin(personDTO.getNationalId());

        NiazsanjiFardiDTO result = niazsanjiFardiService.save(niazsanjiFardiDTO);
        return ResponseEntity.created(new URI("/api/niazsanji-fardis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * POST  /niazsanji-fardis : Create a new niazsanjiFardi.
     *
     * @param niazsanjiFardiDTO the niazsanjiFardiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new niazsanjiFardiDTO, or with status 400 (Bad Request) if the niazsanjiFardi has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/finalize-niazsanji-fardi")
    @Timed
    public ResponseEntity<NiazsanjiFardiDTO> finalizeNiazsanjiFardi(@Valid @RequestBody NiazsanjiFardiDTO niazsanjiFardiDTO) throws URISyntaxException {
        log.debug("REST request to finalize NiazsanjiFardi : {}", niazsanjiFardiDTO);
        if (niazsanjiFardiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NiazsanjiFardiDTO niazsanjiFardi = niazsanjiFardiService.findOne(niazsanjiFardiDTO.getId()).get();

        niazsanjiFardiDTO.setCreateUserLogin(niazsanjiFardi.getCreateUserLogin());
        niazsanjiFardiDTO.setCreateDate(niazsanjiFardi.getCreateDate());
        niazsanjiFardiDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        niazsanjiFardiDTO.setModifyDate(ZonedDateTime.now());
        niazsanjiFardiDTO.setStatus(20);
        niazsanjiFardiDTO.setChangeStatusUserLogin(SecurityUtils.getCurrentUserLogin().get());

        NiazsanjiFardiDTO result = niazsanjiFardiService.finalize(niazsanjiFardiDTO);
        return ResponseEntity.created(new URI("/api/niazsanji-fardis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /niazsanji-fardis : Updates an existing niazsanjiFardi.
     *
     * @param niazsanjiFardiDTO the niazsanjiFardiDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated niazsanjiFardiDTO,
     * or with status 400 (Bad Request) if the niazsanjiFardiDTO is not valid,
     * or with status 500 (Internal Server Error) if the niazsanjiFardiDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/niazsanji-fardis")
    @Timed
    public ResponseEntity<NiazsanjiFardiDTO> updateNiazsanjiFardi(@Valid @RequestBody NiazsanjiFardiDTO niazsanjiFardiDTO) throws URISyntaxException {
        log.debug("REST request to update NiazsanjiFardi : {}", niazsanjiFardiDTO);
        if (niazsanjiFardiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        NiazsanjiFardiDTO niazsanjiFardi = niazsanjiFardiService.findOne(niazsanjiFardiDTO.getId()).get();

        niazsanjiFardiDTO.setCreateUserLogin(niazsanjiFardi.getCreateUserLogin());
        niazsanjiFardiDTO.setCreateDate(niazsanjiFardi.getCreateDate());
        niazsanjiFardiDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        niazsanjiFardiDTO.setModifyDate(ZonedDateTime.now());

        NiazsanjiFardiDTO result = niazsanjiFardiService.save(niazsanjiFardiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, niazsanjiFardiDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /niazsanji-fardis : get all the niazsanjiFardis.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of niazsanjiFardis in body
     */
    @GetMapping("/niazsanji-fardis")
    @Timed
    public ResponseEntity<List<NiazsanjiFardiDTO>> getAllNiazsanjiFardis(NiazsanjiFardiCriteria criteria, Pageable pageable) {
        log.debug("REST request to get NiazsanjiFardis by criteria: {}", criteria);
        Page<NiazsanjiFardiDTO> page = niazsanjiFardiQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/niazsanji-fardis");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /niazsanji-fardis/count : count all the niazsanjiFardis.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/niazsanji-fardis/count")
    @Timed
    public ResponseEntity<Long> countNiazsanjiFardis(NiazsanjiFardiCriteria criteria) {
        log.debug("REST request to count NiazsanjiFardis by criteria: {}", criteria);
        return ResponseEntity.ok().body(niazsanjiFardiQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /niazsanji-fardis/:id : get the "id" niazsanjiFardi.
     *
     * @param id the id of the niazsanjiFardiDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the niazsanjiFardiDTO, or with status 404 (Not Found)
     */
    @GetMapping("/niazsanji-fardis/{id}")
    @Timed
    public ResponseEntity<NiazsanjiFardiDTO> getNiazsanjiFardi(@PathVariable Long id) {
        log.debug("REST request to get NiazsanjiFardi : {}", id);
        Optional<NiazsanjiFardiDTO> niazsanjiFardiDTO = niazsanjiFardiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(niazsanjiFardiDTO);
    }

    /**
     * DELETE  /niazsanji-fardis/:id : delete the "id" niazsanjiFardi.
     *
     * @param id the id of the niazsanjiFardiDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/niazsanji-fardis/{id}")
    @Timed
    public ResponseEntity<Void> deleteNiazsanjiFardi(@PathVariable Long id) {
        log.debug("REST request to delete NiazsanjiFardi : {}", id);
        niazsanjiFardiService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
