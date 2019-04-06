package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.BeautySpeechService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.BeautySpeechDTO;
import com.marineindustryproj.service.dto.BeautySpeechCriteria;
import com.marineindustryproj.service.BeautySpeechQueryService;
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
 * REST controller for managing BeautySpeech.
 */
@RestController
@RequestMapping("/api")
public class BeautySpeechResource {

    private final Logger log = LoggerFactory.getLogger(BeautySpeechResource.class);

    private static final String ENTITY_NAME = "beautySpeech";

    private final BeautySpeechService beautySpeechService;

    private final BeautySpeechQueryService beautySpeechQueryService;

    public BeautySpeechResource(BeautySpeechService beautySpeechService, BeautySpeechQueryService beautySpeechQueryService) {
        this.beautySpeechService = beautySpeechService;
        this.beautySpeechQueryService = beautySpeechQueryService;
    }

    /**
     * POST  /beauty-speeches : Create a new beautySpeech.
     *
     * @param beautySpeechDTO the beautySpeechDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new beautySpeechDTO, or with status 400 (Bad Request) if the beautySpeech has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/beauty-speeches")
    @Timed
    public ResponseEntity<BeautySpeechDTO> createBeautySpeech(@Valid @RequestBody BeautySpeechDTO beautySpeechDTO) throws URISyntaxException {
        log.debug("REST request to save BeautySpeech : {}", beautySpeechDTO);
        if (beautySpeechDTO.getId() != null) {
            throw new BadRequestAlertException("A new beautySpeech cannot already have an ID", ENTITY_NAME, "idexists");
        }
        beautySpeechDTO.setCreateDate(ZonedDateTime.now());
        beautySpeechDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        BeautySpeechDTO result = beautySpeechService.save(beautySpeechDTO);
        return ResponseEntity.created(new URI("/api/beauty-speeches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /beauty-speeches : Updates an existing beautySpeech.
     *
     * @param beautySpeechDTO the beautySpeechDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated beautySpeechDTO,
     * or with status 400 (Bad Request) if the beautySpeechDTO is not valid,
     * or with status 500 (Internal Server Error) if the beautySpeechDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/beauty-speeches")
    @Timed
    public ResponseEntity<BeautySpeechDTO> updateBeautySpeech(@Valid @RequestBody BeautySpeechDTO beautySpeechDTO) throws URISyntaxException {
        log.debug("REST request to update BeautySpeech : {}", beautySpeechDTO);
        if (beautySpeechDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BeautySpeechDTO beautySpeech = beautySpeechService.findOne(beautySpeechDTO.getId()).get();

        beautySpeechDTO.setCreateUserLogin(beautySpeech.getCreateUserLogin());
        beautySpeechDTO.setCreateDate(beautySpeech.getCreateDate());
        beautySpeechDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        beautySpeechDTO.setModifyDate(ZonedDateTime.now());
        BeautySpeechDTO result = beautySpeechService.save(beautySpeechDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, beautySpeechDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /beauty-speeches : get all the beautySpeeches.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of beautySpeeches in body
     */
    @GetMapping("/beauty-speeches")
    @Timed
    public ResponseEntity<List<BeautySpeechDTO>> getAllBeautySpeeches(BeautySpeechCriteria criteria, Pageable pageable) {
        log.debug("REST request to get BeautySpeeches by criteria: {}", criteria);
        Page<BeautySpeechDTO> page = beautySpeechQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/beauty-speeches");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /beauty-speeches/count : count all the beautySpeeches.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/beauty-speeches/count")
    @Timed
    public ResponseEntity<Long> countBeautySpeeches (BeautySpeechCriteria criteria) {
        log.debug("REST request to count BeautySpeeches by criteria: {}", criteria);
        return ResponseEntity.ok().body(beautySpeechQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /beauty-speeches/:id : get the "id" beautySpeech.
     *
     * @param id the id of the beautySpeechDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the beautySpeechDTO, or with status 404 (Not Found)
     */
    @GetMapping("/beauty-speeches/{id}")
    @Timed
    public ResponseEntity<BeautySpeechDTO> getBeautySpeech(@PathVariable Long id) {
        log.debug("REST request to get BeautySpeech : {}", id);
        Optional<BeautySpeechDTO> beautySpeechDTO = beautySpeechService.findOne(id);
        return ResponseUtil.wrapOrNotFound(beautySpeechDTO);
    }

    /**
     * DELETE  /beauty-speeches/:id : delete the "id" beautySpeech.
     *
     * @param id the id of the beautySpeechDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/beauty-speeches/{id}")
    @Timed
    public ResponseEntity<Void> deleteBeautySpeech(@PathVariable Long id) {
        log.debug("REST request to delete BeautySpeech : {}", id);
        beautySpeechService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
