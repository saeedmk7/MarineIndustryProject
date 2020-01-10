package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.DesignNiazsanjiService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.DesignNiazsanjiDTO;
import com.marineindustryproj.service.dto.DesignNiazsanjiCriteria;
import com.marineindustryproj.service.DesignNiazsanjiQueryService;
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
 * REST controller for managing DesignNiazsanji.
 */
@RestController
@RequestMapping("/api")
public class DesignNiazsanjiResource {

    private final Logger log = LoggerFactory.getLogger(DesignNiazsanjiResource.class);

    private static final String ENTITY_NAME = "designNiazsanji";

    private final DesignNiazsanjiService designNiazsanjiService;

    private final DesignNiazsanjiQueryService designNiazsanjiQueryService;

    public DesignNiazsanjiResource(DesignNiazsanjiService designNiazsanjiService, DesignNiazsanjiQueryService designNiazsanjiQueryService) {
        this.designNiazsanjiService = designNiazsanjiService;
        this.designNiazsanjiQueryService = designNiazsanjiQueryService;
    }

    /**
     * POST  /design-niazsanjis : Create a new designNiazsanji.
     *
     * @param designNiazsanjiDTO the designNiazsanjiDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new designNiazsanjiDTO, or with status 400 (Bad Request) if the designNiazsanji has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/design-niazsanjis")
    @Timed
    public ResponseEntity<DesignNiazsanjiDTO> createDesignNiazsanji(@Valid @RequestBody DesignNiazsanjiDTO designNiazsanjiDTO) throws URISyntaxException {
        log.debug("REST request to save DesignNiazsanji : {}", designNiazsanjiDTO);
        if (designNiazsanjiDTO.getId() != null) {
            throw new BadRequestAlertException("A new designNiazsanji cannot already have an ID", ENTITY_NAME, "idexists");
        }
        designNiazsanjiDTO.setCreateDate(ZonedDateTime.now());
        designNiazsanjiDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        DesignNiazsanjiDTO result = designNiazsanjiService.save(designNiazsanjiDTO);
        return ResponseEntity.created(new URI("/api/design-niazsanjis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /design-niazsanjis : Updates an existing designNiazsanji.
     *
     * @param designNiazsanjiDTO the designNiazsanjiDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated designNiazsanjiDTO,
     * or with status 400 (Bad Request) if the designNiazsanjiDTO is not valid,
     * or with status 500 (Internal Server Error) if the designNiazsanjiDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/design-niazsanjis")
    @Timed
    public ResponseEntity<DesignNiazsanjiDTO> updateDesignNiazsanji(@Valid @RequestBody DesignNiazsanjiDTO designNiazsanjiDTO) throws URISyntaxException {
        log.debug("REST request to update DesignNiazsanji : {}", designNiazsanjiDTO);
        if (designNiazsanjiDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DesignNiazsanjiDTO designNiazsanji = designNiazsanjiService.findOne(designNiazsanjiDTO.getId()).get();

        designNiazsanjiDTO.setCreateUserLogin(designNiazsanji.getCreateUserLogin());
        designNiazsanjiDTO.setCreateDate(designNiazsanji.getCreateDate());
        designNiazsanjiDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        designNiazsanjiDTO.setModifyDate(ZonedDateTime.now());
        DesignNiazsanjiDTO result = designNiazsanjiService.save(designNiazsanjiDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, designNiazsanjiDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /design-niazsanjis : get all the designNiazsanjis.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of designNiazsanjis in body
     */
    @GetMapping("/design-niazsanjis")
    @Timed
    public ResponseEntity<List<DesignNiazsanjiDTO>> getAllDesignNiazsanjis(DesignNiazsanjiCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DesignNiazsanjis by criteria: {}", criteria);
        Page<DesignNiazsanjiDTO> page = designNiazsanjiQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/design-niazsanjis");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /design-niazsanjis/count : count all the designNiazsanjis.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/design-niazsanjis/count")
    @Timed
    public ResponseEntity<Long> countDesignNiazsanjis(DesignNiazsanjiCriteria criteria) {
        log.debug("REST request to count DesignNiazsanjis by criteria: {}", criteria);
        return ResponseEntity.ok().body(designNiazsanjiQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /design-niazsanjis/:id : get the "id" designNiazsanji.
     *
     * @param id the id of the designNiazsanjiDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the designNiazsanjiDTO, or with status 404 (Not Found)
     */
    @GetMapping("/design-niazsanjis/{id}")
    @Timed
    public ResponseEntity<DesignNiazsanjiDTO> getDesignNiazsanji(@PathVariable Long id) {
        log.debug("REST request to get DesignNiazsanji : {}", id);
        Optional<DesignNiazsanjiDTO> designNiazsanjiDTO = designNiazsanjiService.findOne(id);
        return ResponseUtil.wrapOrNotFound(designNiazsanjiDTO);
    }

    /**
     * DELETE  /design-niazsanjis/:id : delete the "id" designNiazsanji.
     *
     * @param id the id of the designNiazsanjiDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/design-niazsanjis/{id}")
    @Timed
    public ResponseEntity<Void> deleteDesignNiazsanji(@PathVariable Long id) {
        log.debug("REST request to delete DesignNiazsanji : {}", id);
        designNiazsanjiService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
