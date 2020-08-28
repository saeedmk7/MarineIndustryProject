package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.SoldierMediaAwarenessReportService;
import com.marineindustryproj.service.StorageService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.SoldierMediaAwarenessReportDTO;
import com.marineindustryproj.service.dto.SoldierMediaAwarenessReportCriteria;
import com.marineindustryproj.service.SoldierMediaAwarenessReportQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SoldierMediaAwarenessReport.
 */
@RestController
@RequestMapping("/api")
public class SoldierMediaAwarenessReportResource {

    private final Logger log = LoggerFactory.getLogger(SoldierMediaAwarenessReportResource.class);

    private static final String ENTITY_NAME = "soldierMediaAwarenessReport";

    private final SoldierMediaAwarenessReportService soldierMediaAwarenessReportService;

    private final SoldierMediaAwarenessReportQueryService soldierMediaAwarenessReportQueryService;

    private final StorageService storageService;

    public SoldierMediaAwarenessReportResource(SoldierMediaAwarenessReportService soldierMediaAwarenessReportService, SoldierMediaAwarenessReportQueryService soldierMediaAwarenessReportQueryService, StorageService storageService) {
        this.soldierMediaAwarenessReportService = soldierMediaAwarenessReportService;
        this.soldierMediaAwarenessReportQueryService = soldierMediaAwarenessReportQueryService;
        this.storageService = storageService;
    }

    /**
     * POST  /soldier-media-awareness-reports : Create a new soldierMediaAwarenessReport.
     *
     * @param soldierMediaAwarenessReportDTO the soldierMediaAwarenessReportDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new soldierMediaAwarenessReportDTO, or with status 400 (Bad Request) if the soldierMediaAwarenessReport has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/soldier-media-awareness-reports")
    @Timed
    public ResponseEntity<SoldierMediaAwarenessReportDTO> createSoldierMediaAwarenessReport(@Valid @RequestBody SoldierMediaAwarenessReportDTO soldierMediaAwarenessReportDTO) throws URISyntaxException {
        log.debug("REST request to save SoldierMediaAwarenessReport : {}", soldierMediaAwarenessReportDTO);
        if (soldierMediaAwarenessReportDTO.getId() != null) {
            throw new BadRequestAlertException("A new soldierMediaAwarenessReport cannot already have an ID", ENTITY_NAME, "idexists");
        }

        soldierMediaAwarenessReportDTO.setCreateDate(ZonedDateTime.now());
        soldierMediaAwarenessReportDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        SoldierMediaAwarenessReportDTO result = soldierMediaAwarenessReportService.save(soldierMediaAwarenessReportDTO);
        return ResponseEntity.created(new URI("/api/soldier-media-awareness-reports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /soldier-media-awareness-reports : Updates an existing soldierMediaAwarenessReport.
     *
     * @param soldierMediaAwarenessReportDTO the soldierMediaAwarenessReportDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated soldierMediaAwarenessReportDTO,
     * or with status 400 (Bad Request) if the soldierMediaAwarenessReportDTO is not valid,
     * or with status 500 (Internal Server Error) if the soldierMediaAwarenessReportDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/soldier-media-awareness-reports")
    @Timed
    public ResponseEntity<SoldierMediaAwarenessReportDTO> updateSoldierMediaAwarenessReport(@Valid @RequestBody SoldierMediaAwarenessReportDTO soldierMediaAwarenessReportDTO) throws URISyntaxException {
        log.debug("REST request to update SoldierMediaAwarenessReport : {}", soldierMediaAwarenessReportDTO);
        if (soldierMediaAwarenessReportDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        SoldierMediaAwarenessReportDTO soldierMediaAwarenessReport = soldierMediaAwarenessReportService.findOne(soldierMediaAwarenessReportDTO.getId()).get();

        if(soldierMediaAwarenessReportDTO.getFileDoc() == null)
            soldierMediaAwarenessReportDTO.setFileDoc(soldierMediaAwarenessReport.getFileDoc());
        soldierMediaAwarenessReportDTO.setCreateUserLogin(soldierMediaAwarenessReport.getCreateUserLogin());
        soldierMediaAwarenessReportDTO.setCreateDate(soldierMediaAwarenessReport.getCreateDate());
        soldierMediaAwarenessReportDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        soldierMediaAwarenessReportDTO.setModifyDate(ZonedDateTime.now());

        SoldierMediaAwarenessReportDTO result = soldierMediaAwarenessReportService.save(soldierMediaAwarenessReportDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, soldierMediaAwarenessReportDTO.getId().toString()))
            .body(result);
    }

    /**
     * POST  /soldier-media-awareness-reports : Create a new media-awareness-reports.
     *
     * @return the ResponseEntity with status 201 (Created) and with body the new educationalHistoryDTO, or with status 400 (Bad Request) if the educationalHistory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/soldier-media-awareness-reports/upload-file")
    @Timed
    public ResponseEntity<String> uploadFileSoldierMediaAwareness(@Valid @RequestBody @RequestParam("file") MultipartFile file) throws URISyntaxException, Exception {
        String fileDownloadUri;
        try
        {
            String fileName = storageService.storeSoldierMediaAwarenessReport(file);

            fileDownloadUri = "api/downloadSoldierMediaAwarenessReportFile/" + fileName;
        }
        catch (Exception ex){
            throw new Exception(ex);
        }

        return ResponseEntity.ok().body(fileDownloadUri);
    }

    /**
     * GET  /soldier-media-awareness-reports : get all the soldierMediaAwarenessReports.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of soldierMediaAwarenessReports in body
     */
    @GetMapping("/soldier-media-awareness-reports")
    @Timed
    public ResponseEntity<List<SoldierMediaAwarenessReportDTO>> getAllSoldierMediaAwarenessReports(SoldierMediaAwarenessReportCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SoldierMediaAwarenessReports by criteria: {}", criteria);
        Page<SoldierMediaAwarenessReportDTO> page = soldierMediaAwarenessReportQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/soldier-media-awareness-reports");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /soldier-media-awareness-reports/count : count all the soldierMediaAwarenessReports.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/soldier-media-awareness-reports/count")
    @Timed
    public ResponseEntity<Long> countSoldierMediaAwarenessReports(SoldierMediaAwarenessReportCriteria criteria) {
        log.debug("REST request to count SoldierMediaAwarenessReports by criteria: {}", criteria);
        return ResponseEntity.ok().body(soldierMediaAwarenessReportQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /soldier-media-awareness-reports/:id : get the "id" soldierMediaAwarenessReport.
     *
     * @param id the id of the soldierMediaAwarenessReportDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the soldierMediaAwarenessReportDTO, or with status 404 (Not Found)
     */
    @GetMapping("/soldier-media-awareness-reports/{id}")
    @Timed
    public ResponseEntity<SoldierMediaAwarenessReportDTO> getSoldierMediaAwarenessReport(@PathVariable Long id) {
        log.debug("REST request to get SoldierMediaAwarenessReport : {}", id);
        Optional<SoldierMediaAwarenessReportDTO> soldierMediaAwarenessReportDTO = soldierMediaAwarenessReportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(soldierMediaAwarenessReportDTO);
    }

    /**
     * DELETE  /soldier-media-awareness-reports/:id : delete the "id" soldierMediaAwarenessReport.
     *
     * @param id the id of the soldierMediaAwarenessReportDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/soldier-media-awareness-reports/{id}")
    @Timed
    public ResponseEntity<Void> deleteSoldierMediaAwarenessReport(@PathVariable Long id) {
        log.debug("REST request to delete SoldierMediaAwarenessReport : {}", id);
        soldierMediaAwarenessReportService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
