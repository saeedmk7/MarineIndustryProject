package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.TeachTechniqueService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.TeachTechniqueDTO;
import com.marineindustryproj.service.dto.TeachTechniqueCriteria;
import com.marineindustryproj.service.TeachTechniqueQueryService;
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
 * REST controller for managing TeachTechnique.
 */
@RestController
@RequestMapping("/api")
public class TeachTechniqueResource {

    private final Logger log = LoggerFactory.getLogger(TeachTechniqueResource.class);

    private static final String ENTITY_NAME = "teachTechnique";

    private final TeachTechniqueService teachTechniqueService;

    private final TeachTechniqueQueryService teachTechniqueQueryService;

    public TeachTechniqueResource(TeachTechniqueService teachTechniqueService, TeachTechniqueQueryService teachTechniqueQueryService) {
        this.teachTechniqueService = teachTechniqueService;
        this.teachTechniqueQueryService = teachTechniqueQueryService;
    }

    /**
     * POST  /teach-techniques : Create a new teachTechnique.
     *
     * @param teachTechniqueDTO the teachTechniqueDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new teachTechniqueDTO, or with status 400 (Bad Request) if the teachTechnique has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/teach-techniques")
    @Timed
    public ResponseEntity<TeachTechniqueDTO> createTeachTechnique(@Valid @RequestBody TeachTechniqueDTO teachTechniqueDTO) throws URISyntaxException {
        log.debug("REST request to save TeachTechnique : {}", teachTechniqueDTO);
        if (teachTechniqueDTO.getId() != null) {
            throw new BadRequestAlertException("A new teachTechnique cannot already have an ID", ENTITY_NAME, "idexists");
        }
        teachTechniqueDTO.setCreateDate(ZonedDateTime.now());
        teachTechniqueDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        TeachTechniqueDTO result = teachTechniqueService.save(teachTechniqueDTO);
        return ResponseEntity.created(new URI("/api/teach-techniques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /teach-techniques : Updates an existing teachTechnique.
     *
     * @param teachTechniqueDTO the teachTechniqueDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated teachTechniqueDTO,
     * or with status 400 (Bad Request) if the teachTechniqueDTO is not valid,
     * or with status 500 (Internal Server Error) if the teachTechniqueDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/teach-techniques")
    @Timed
    public ResponseEntity<TeachTechniqueDTO> updateTeachTechnique(@Valid @RequestBody TeachTechniqueDTO teachTechniqueDTO) throws URISyntaxException {
        log.debug("REST request to update TeachTechnique : {}", teachTechniqueDTO);
        if (teachTechniqueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TeachTechniqueDTO teachTechnique = teachTechniqueService.findOne(teachTechniqueDTO.getId()).get();

        teachTechniqueDTO.setCreateUserLogin(teachTechnique.getCreateUserLogin());
        teachTechniqueDTO.setCreateDate(teachTechnique.getCreateDate());
        teachTechniqueDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        teachTechniqueDTO.setModifyDate(ZonedDateTime.now());
        TeachTechniqueDTO result = teachTechniqueService.save(teachTechniqueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, teachTechniqueDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /teach-techniques : get all the teachTechniques.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of teachTechniques in body
     */
    @GetMapping("/teach-techniques")
    @Timed
    public ResponseEntity<List<TeachTechniqueDTO>> getAllTeachTechniques(TeachTechniqueCriteria criteria, Pageable pageable) {
        log.debug("REST request to get TeachTechniques by criteria: {}", criteria);
        Page<TeachTechniqueDTO> page = teachTechniqueQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/teach-techniques");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /teach-techniques/count : count all the teachTechniques.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/teach-techniques/count")
    @Timed
    public ResponseEntity<Long> countTeachTechniques (TeachTechniqueCriteria criteria) {
        log.debug("REST request to count TeachTechniques by criteria: {}", criteria);
        return ResponseEntity.ok().body(teachTechniqueQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /teach-techniques/:id : get the "id" teachTechnique.
     *
     * @param id the id of the teachTechniqueDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the teachTechniqueDTO, or with status 404 (Not Found)
     */
    @GetMapping("/teach-techniques/{id}")
    @Timed
    public ResponseEntity<TeachTechniqueDTO> getTeachTechnique(@PathVariable Long id) {
        log.debug("REST request to get TeachTechnique : {}", id);
        Optional<TeachTechniqueDTO> teachTechniqueDTO = teachTechniqueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(teachTechniqueDTO);
    }

    /**
     * DELETE  /teach-techniques/:id : delete the "id" teachTechnique.
     *
     * @param id the id of the teachTechniqueDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/teach-techniques/{id}")
    @Timed
    public ResponseEntity<Void> deleteTeachTechnique(@PathVariable Long id) {
        log.debug("REST request to delete TeachTechnique : {}", id);
        teachTechniqueService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
