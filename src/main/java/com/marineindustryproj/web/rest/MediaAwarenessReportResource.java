package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.MediaAwarenessReportService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.MediaAwarenessReportDTO;
import com.marineindustryproj.service.dto.MediaAwarenessReportCriteria;
import com.marineindustryproj.service.MediaAwarenessReportQueryService;
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
 * REST controller for managing MediaAwarenessReport.
 */
@RestController
@RequestMapping("/api")
public class MediaAwarenessReportResource {

    private final Logger log = LoggerFactory.getLogger(MediaAwarenessReportResource.class);

    private static final String ENTITY_NAME = "mediaAwarenessReport";

    private final MediaAwarenessReportService mediaAwarenessReportService;

    private final MediaAwarenessReportQueryService mediaAwarenessReportQueryService;

    public MediaAwarenessReportResource(MediaAwarenessReportService mediaAwarenessReportService, MediaAwarenessReportQueryService mediaAwarenessReportQueryService) {
        this.mediaAwarenessReportService = mediaAwarenessReportService;
        this.mediaAwarenessReportQueryService = mediaAwarenessReportQueryService;
    }

    /**
     * POST  /media-awareness-reports : Create a new mediaAwarenessReport.
     *
     * @param mediaAwarenessReportDTO the mediaAwarenessReportDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mediaAwarenessReportDTO, or with status 400 (Bad Request) if the mediaAwarenessReport has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/media-awareness-reports")
    @Timed
    public ResponseEntity<MediaAwarenessReportDTO> createMediaAwarenessReport(@Valid @RequestBody MediaAwarenessReportDTO mediaAwarenessReportDTO) throws URISyntaxException {
        log.debug("REST request to save MediaAwarenessReport : {}", mediaAwarenessReportDTO);
        if (mediaAwarenessReportDTO.getId() != null) {
            throw new BadRequestAlertException("A new mediaAwarenessReport cannot already have an ID", ENTITY_NAME, "idexists");
        }

        mediaAwarenessReportDTO.setGuid(UUID.randomUUID().toString());
        mediaAwarenessReportDTO.setCreateDate(ZonedDateTime.now());
        mediaAwarenessReportDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        MediaAwarenessReportDTO result = mediaAwarenessReportService.save(mediaAwarenessReportDTO);
        return ResponseEntity.created(new URI("/api/media-awareness-reports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /media-awareness-reports : Updates an existing mediaAwarenessReport.
     *
     * @param mediaAwarenessReportDTO the mediaAwarenessReportDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mediaAwarenessReportDTO,
     * or with status 400 (Bad Request) if the mediaAwarenessReportDTO is not valid,
     * or with status 500 (Internal Server Error) if the mediaAwarenessReportDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/media-awareness-reports")
    @Timed
    public ResponseEntity<MediaAwarenessReportDTO> updateMediaAwarenessReport(@Valid @RequestBody MediaAwarenessReportDTO mediaAwarenessReportDTO) throws URISyntaxException {
        log.debug("REST request to update MediaAwarenessReport : {}", mediaAwarenessReportDTO);
        if (mediaAwarenessReportDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        MediaAwarenessReportDTO mediaAwarenessReport = mediaAwarenessReportService.findOne(mediaAwarenessReportDTO.getId()).get();

        mediaAwarenessReportDTO.setGuid(mediaAwarenessReport.getGuid());
        mediaAwarenessReportDTO.setCreateUserLogin(mediaAwarenessReport.getCreateUserLogin());
        mediaAwarenessReportDTO.setCreateDate(mediaAwarenessReport.getCreateDate());
        mediaAwarenessReportDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        mediaAwarenessReportDTO.setModifyDate(ZonedDateTime.now());

        MediaAwarenessReportDTO result = mediaAwarenessReportService.save(mediaAwarenessReportDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mediaAwarenessReportDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /media-awareness-reports : get all the mediaAwarenessReports.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of mediaAwarenessReports in body
     */
    @GetMapping("/media-awareness-reports")
    @Timed
    public ResponseEntity<List<MediaAwarenessReportDTO>> getAllMediaAwarenessReports(MediaAwarenessReportCriteria criteria, Pageable pageable) {
        log.debug("REST request to get MediaAwarenessReports by criteria: {}", criteria);
        Page<MediaAwarenessReportDTO> page = mediaAwarenessReportQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/media-awareness-reports");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /media-awareness-reports/count : count all the mediaAwarenessReports.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/media-awareness-reports/count")
    @Timed
    public ResponseEntity<Long> countMediaAwarenessReports(MediaAwarenessReportCriteria criteria) {
        log.debug("REST request to count MediaAwarenessReports by criteria: {}", criteria);
        return ResponseEntity.ok().body(mediaAwarenessReportQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /media-awareness-reports/:id : get the "id" mediaAwarenessReport.
     *
     * @param id the id of the mediaAwarenessReportDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mediaAwarenessReportDTO, or with status 404 (Not Found)
     */
    @GetMapping("/media-awareness-reports/{id}")
    @Timed
    public ResponseEntity<MediaAwarenessReportDTO> getMediaAwarenessReport(@PathVariable Long id) {
        log.debug("REST request to get MediaAwarenessReport : {}", id);
        Optional<MediaAwarenessReportDTO> mediaAwarenessReportDTO = mediaAwarenessReportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mediaAwarenessReportDTO);
    }

    /**
     * DELETE  /media-awareness-reports/:id : delete the "id" mediaAwarenessReport.
     *
     * @param id the id of the mediaAwarenessReportDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/media-awareness-reports/{id}")
    @Timed
    public ResponseEntity<Void> deleteMediaAwarenessReport(@PathVariable Long id) {
        log.debug("REST request to delete MediaAwarenessReport : {}", id);
        mediaAwarenessReportService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
