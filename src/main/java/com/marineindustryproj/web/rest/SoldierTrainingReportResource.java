package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.SoldierTrainingReportService;
import com.marineindustryproj.service.StorageService;
import com.marineindustryproj.service.dto.ResourceDTO;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.SoldierTrainingReportDTO;
import com.marineindustryproj.service.dto.SoldierTrainingReportCriteria;
import com.marineindustryproj.service.SoldierTrainingReportQueryService;
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
 * REST controller for managing SoldierTrainingReport.
 */
@RestController
@RequestMapping("/api")
public class SoldierTrainingReportResource {

    private final Logger log = LoggerFactory.getLogger(SoldierTrainingReportResource.class);

    private static final String ENTITY_NAME = "soldierTrainingReport";

    private final SoldierTrainingReportService soldierTrainingReportService;

    private final SoldierTrainingReportQueryService soldierTrainingReportQueryService;

    private final StorageService storageService;

    public SoldierTrainingReportResource(SoldierTrainingReportService soldierTrainingReportService, SoldierTrainingReportQueryService soldierTrainingReportQueryService, StorageService storageService) {
        this.soldierTrainingReportService = soldierTrainingReportService;
        this.soldierTrainingReportQueryService = soldierTrainingReportQueryService;
        this.storageService = storageService;
    }

    /**
     * POST  /soldier-training-reports : Create a new soldierTrainingReport.
     *
     * @param soldierTrainingReportDTO the soldierTrainingReportDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new soldierTrainingReportDTO, or with status 400 (Bad Request) if the soldierTrainingReport has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/soldier-training-reports")
    @Timed
    public ResponseEntity<SoldierTrainingReportDTO> createSoldierTrainingReport(@Valid @RequestBody SoldierTrainingReportDTO soldierTrainingReportDTO) throws URISyntaxException {
        log.debug("REST request to save SoldierTrainingReport : {}", soldierTrainingReportDTO);
        if (soldierTrainingReportDTO.getId() != null) {
            throw new BadRequestAlertException("A new soldierTrainingReport cannot already have an ID", ENTITY_NAME, "idexists");
        }

        soldierTrainingReportDTO.setCreateDate(ZonedDateTime.now());
        soldierTrainingReportDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        SoldierTrainingReportDTO result = soldierTrainingReportService.save(soldierTrainingReportDTO);
        return ResponseEntity.created(new URI("/api/soldier-training-reports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /soldier-training-reports : Updates an existing soldierTrainingReport.
     *
     * @param soldierTrainingReportDTO the soldierTrainingReportDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated soldierTrainingReportDTO,
     * or with status 400 (Bad Request) if the soldierTrainingReportDTO is not valid,
     * or with status 500 (Internal Server Error) if the soldierTrainingReportDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/soldier-training-reports")
    @Timed
    public ResponseEntity<SoldierTrainingReportDTO> updateSoldierTrainingReport(@Valid @RequestBody SoldierTrainingReportDTO soldierTrainingReportDTO) throws URISyntaxException {
        log.debug("REST request to update SoldierTrainingReport : {}", soldierTrainingReportDTO);
        if (soldierTrainingReportDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        SoldierTrainingReportDTO soldierTrainingReport = soldierTrainingReportService.findOne(soldierTrainingReportDTO.getId()).get();

        if(soldierTrainingReportDTO.getFileDoc() == null)
            soldierTrainingReportDTO.setFileDoc(soldierTrainingReport.getFileDoc());
        soldierTrainingReportDTO.setCreateUserLogin(soldierTrainingReport.getCreateUserLogin());
        soldierTrainingReportDTO.setCreateDate(soldierTrainingReport.getCreateDate());
        soldierTrainingReportDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        soldierTrainingReportDTO.setModifyDate(ZonedDateTime.now());

        SoldierTrainingReportDTO result = soldierTrainingReportService.save(soldierTrainingReportDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, soldierTrainingReportDTO.getId().toString()))
            .body(result);
    }

    /**
     * POST  /soldier-training-reports : Create a new educationalHistory.
     *
     * @return the ResponseEntity with status 201 (Created) and with body the new educationalHistoryDTO, or with status 400 (Bad Request) if the educationalHistory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/soldier-training-reports/upload-file")
    @Timed
    public ResponseEntity<String> uploadFileSoldierTrainingReport(@Valid @RequestBody @RequestParam("file") MultipartFile file) throws URISyntaxException, Exception {
        String fileDownloadUri;
        try
        {
            String fileName = storageService.storeSoldierTrainingReport(file);

            fileDownloadUri = "api/downloadSoldierTrainingReportFile/" + fileName;
        }
        catch (Exception ex){
            throw new Exception(ex);
        }

        return ResponseEntity.ok().body(fileDownloadUri);
    }

    /**
     * GET  /soldier-training-reports : get all the soldierTrainingReports.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of soldierTrainingReports in body
     */
    @GetMapping("/soldier-training-reports")
    @Timed
    public ResponseEntity<List<SoldierTrainingReportDTO>> getAllSoldierTrainingReports(SoldierTrainingReportCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SoldierTrainingReports by criteria: {}", criteria);
        Page<SoldierTrainingReportDTO> page = soldierTrainingReportQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/soldier-training-reports");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /soldier-training-reports/count : count all the soldierTrainingReports.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/soldier-training-reports/count")
    @Timed
    public ResponseEntity<Long> countSoldierTrainingReports(SoldierTrainingReportCriteria criteria) {
        log.debug("REST request to count SoldierTrainingReports by criteria: {}", criteria);
        return ResponseEntity.ok().body(soldierTrainingReportQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /soldier-training-reports/:id : get the "id" soldierTrainingReport.
     *
     * @param id the id of the soldierTrainingReportDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the soldierTrainingReportDTO, or with status 404 (Not Found)
     */
    @GetMapping("/soldier-training-reports/{id}")
    @Timed
    public ResponseEntity<SoldierTrainingReportDTO> getSoldierTrainingReport(@PathVariable Long id) {
        log.debug("REST request to get SoldierTrainingReport : {}", id);
        Optional<SoldierTrainingReportDTO> soldierTrainingReportDTO = soldierTrainingReportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(soldierTrainingReportDTO);
    }

    /**
     * DELETE  /soldier-training-reports/:id : delete the "id" soldierTrainingReport.
     *
     * @param id the id of the soldierTrainingReportDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/soldier-training-reports/{id}")
    @Timed
    public ResponseEntity<Void> deleteSoldierTrainingReport(@PathVariable Long id) {
        log.debug("REST request to delete SoldierTrainingReport : {}", id);
        soldierTrainingReportService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
