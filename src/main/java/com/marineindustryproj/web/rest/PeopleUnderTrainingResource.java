package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.PeopleUnderTrainingService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.PeopleUnderTrainingDTO;
import com.marineindustryproj.service.dto.PeopleUnderTrainingCriteria;
import com.marineindustryproj.service.PeopleUnderTrainingQueryService;
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
 * REST controller for managing PeopleUnderTraining.
 */
@RestController
@RequestMapping("/api")
public class PeopleUnderTrainingResource {

    private final Logger log = LoggerFactory.getLogger(PeopleUnderTrainingResource.class);

    private static final String ENTITY_NAME = "peopleUnderTraining";

    private final PeopleUnderTrainingService peopleUnderTrainingService;

    private final PeopleUnderTrainingQueryService peopleUnderTrainingQueryService;

    public PeopleUnderTrainingResource(PeopleUnderTrainingService peopleUnderTrainingService, PeopleUnderTrainingQueryService peopleUnderTrainingQueryService) {
        this.peopleUnderTrainingService = peopleUnderTrainingService;
        this.peopleUnderTrainingQueryService = peopleUnderTrainingQueryService;
    }

    /**
     * POST  /people-under-trainings : Create a new peopleUnderTraining.
     *
     * @param peopleUnderTrainingDTO the peopleUnderTrainingDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new peopleUnderTrainingDTO, or with status 400 (Bad Request) if the peopleUnderTraining has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/people-under-trainings")
    @Timed
    public ResponseEntity<PeopleUnderTrainingDTO> createPeopleUnderTraining(@Valid @RequestBody PeopleUnderTrainingDTO peopleUnderTrainingDTO) throws URISyntaxException {
        log.debug("REST request to save PeopleUnderTraining : {}", peopleUnderTrainingDTO);
        if (peopleUnderTrainingDTO.getId() != null) {
            throw new BadRequestAlertException("A new peopleUnderTraining cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PeopleUnderTrainingDTO result = peopleUnderTrainingService.save(peopleUnderTrainingDTO);
        return ResponseEntity.created(new URI("/api/people-under-trainings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /people-under-trainings : Updates an existing peopleUnderTraining.
     *
     * @param peopleUnderTrainingDTO the peopleUnderTrainingDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated peopleUnderTrainingDTO,
     * or with status 400 (Bad Request) if the peopleUnderTrainingDTO is not valid,
     * or with status 500 (Internal Server Error) if the peopleUnderTrainingDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/people-under-trainings")
    @Timed
    public ResponseEntity<PeopleUnderTrainingDTO> updatePeopleUnderTraining(@Valid @RequestBody PeopleUnderTrainingDTO peopleUnderTrainingDTO) throws URISyntaxException {
        log.debug("REST request to update PeopleUnderTraining : {}", peopleUnderTrainingDTO);
        if (peopleUnderTrainingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PeopleUnderTrainingDTO result = peopleUnderTrainingService.save(peopleUnderTrainingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, peopleUnderTrainingDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /people-under-trainings : get all the peopleUnderTrainings.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of peopleUnderTrainings in body
     */
    @GetMapping("/people-under-trainings")
    @Timed
    public ResponseEntity<List<PeopleUnderTrainingDTO>> getAllPeopleUnderTrainings(PeopleUnderTrainingCriteria criteria, Pageable pageable) {
        log.debug("REST request to get PeopleUnderTrainings by criteria: {}", criteria);
        Page<PeopleUnderTrainingDTO> page = peopleUnderTrainingQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/people-under-trainings");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /people-under-trainings/count : count all the peopleUnderTrainings.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/people-under-trainings/count")
    @Timed
    public ResponseEntity<Long> countPeopleUnderTrainings(PeopleUnderTrainingCriteria criteria) {
        log.debug("REST request to count PeopleUnderTrainings by criteria: {}", criteria);
        return ResponseEntity.ok().body(peopleUnderTrainingQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /people-under-trainings/:id : get the "id" peopleUnderTraining.
     *
     * @param id the id of the peopleUnderTrainingDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the peopleUnderTrainingDTO, or with status 404 (Not Found)
     */
    @GetMapping("/people-under-trainings/{id}")
    @Timed
    public ResponseEntity<PeopleUnderTrainingDTO> getPeopleUnderTraining(@PathVariable Long id) {
        log.debug("REST request to get PeopleUnderTraining : {}", id);
        Optional<PeopleUnderTrainingDTO> peopleUnderTrainingDTO = peopleUnderTrainingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(peopleUnderTrainingDTO);
    }

    /**
     * DELETE  /people-under-trainings/:id : delete the "id" peopleUnderTraining.
     *
     * @param id the id of the peopleUnderTrainingDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/people-under-trainings/{id}")
    @Timed
    public ResponseEntity<Void> deletePeopleUnderTraining(@PathVariable Long id) {
        log.debug("REST request to delete PeopleUnderTraining : {}", id);
        peopleUnderTrainingService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
