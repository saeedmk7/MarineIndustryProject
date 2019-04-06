package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.AnnouncementService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.AnnouncementDTO;
import com.marineindustryproj.service.dto.AnnouncementCriteria;
import com.marineindustryproj.service.AnnouncementQueryService;
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
 * REST controller for managing Announcement.
 */
@RestController
@RequestMapping("/api")
public class AnnouncementResource {

    private final Logger log = LoggerFactory.getLogger(AnnouncementResource.class);

    private static final String ENTITY_NAME = "announcement";

    private final AnnouncementService announcementService;

    private final AnnouncementQueryService announcementQueryService;

    public AnnouncementResource(AnnouncementService announcementService, AnnouncementQueryService announcementQueryService) {
        this.announcementService = announcementService;
        this.announcementQueryService = announcementQueryService;
    }

    /**
     * POST  /announcements : Create a new announcement.
     *
     * @param announcementDTO the announcementDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new announcementDTO, or with status 400 (Bad Request) if the announcement has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/announcements")
    @Timed
    public ResponseEntity<AnnouncementDTO> createAnnouncement(@Valid @RequestBody AnnouncementDTO announcementDTO) throws URISyntaxException {
        log.debug("REST request to save Announcement : {}", announcementDTO);
        if (announcementDTO.getId() != null) {
            throw new BadRequestAlertException("A new announcement cannot already have an ID", ENTITY_NAME, "idexists");
        }

        announcementDTO.setCreateDate(ZonedDateTime.now());
        announcementDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        AnnouncementDTO result = announcementService.save(announcementDTO);
        return ResponseEntity.created(new URI("/api/announcements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /announcements : Updates an existing announcement.
     *
     * @param announcementDTO the announcementDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated announcementDTO,
     * or with status 400 (Bad Request) if the announcementDTO is not valid,
     * or with status 500 (Internal Server Error) if the announcementDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/announcements")
    @Timed
    public ResponseEntity<AnnouncementDTO> updateAnnouncement(@Valid @RequestBody AnnouncementDTO announcementDTO) throws URISyntaxException {
        log.debug("REST request to update Announcement : {}", announcementDTO);
        if (announcementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        AnnouncementDTO announcement = announcementService.findOne(announcementDTO.getId()).get();

        announcementDTO.setCreateUserLogin(announcement.getCreateUserLogin());
        announcementDTO.setCreateDate(announcement.getCreateDate());
        announcementDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        announcementDTO.setModifyDate(ZonedDateTime.now());

        AnnouncementDTO result = announcementService.save(announcementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, announcementDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /announcements : get all the announcements.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of announcements in body
     */
    @GetMapping("/announcements")
    @Timed
    public ResponseEntity<List<AnnouncementDTO>> getAllAnnouncements(AnnouncementCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Announcements by criteria: {}", criteria);
        Page<AnnouncementDTO> page = announcementQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/announcements");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /announcements/count : count all the announcements.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/announcements/count")
    @Timed
    public ResponseEntity<Long> countAnnouncements (AnnouncementCriteria criteria) {
        log.debug("REST request to count Announcements by criteria: {}", criteria);
        return ResponseEntity.ok().body(announcementQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /announcements/:id : get the "id" announcement.
     *
     * @param id the id of the announcementDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the announcementDTO, or with status 404 (Not Found)
     */
    @GetMapping("/announcements/{id}")
    @Timed
    public ResponseEntity<AnnouncementDTO> getAnnouncement(@PathVariable Long id) {
        log.debug("REST request to get Announcement : {}", id);
        Optional<AnnouncementDTO> announcementDTO = announcementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(announcementDTO);
    }

    /**
     * DELETE  /announcements/:id : delete the "id" announcement.
     *
     * @param id the id of the announcementDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/announcements/{id}")
    @Timed
    public ResponseEntity<Void> deleteAnnouncement(@PathVariable Long id) {
        log.debug("REST request to delete Announcement : {}", id);
        announcementService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
