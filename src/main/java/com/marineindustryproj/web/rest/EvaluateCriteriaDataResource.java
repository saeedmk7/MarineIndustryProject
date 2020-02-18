package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.EvaluateCriteriaDataService;
import com.marineindustryproj.service.StorageService;
import com.marineindustryproj.service.dto.EvaluateCriteriaTrainingDTO;
import com.marineindustryproj.service.dto.SoldierTrainingReportDTO;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.EvaluateCriteriaDataDTO;
import com.marineindustryproj.service.dto.EvaluateCriteriaDataCriteria;
import com.marineindustryproj.service.EvaluateCriteriaDataQueryService;
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
 * REST controller for managing EvaluateCriteriaData.
 */
@RestController
@RequestMapping("/api")
public class EvaluateCriteriaDataResource {

    private final Logger log = LoggerFactory.getLogger(EvaluateCriteriaDataResource.class);

    private static final String ENTITY_NAME = "evaluateCriteriaData";

    private final EvaluateCriteriaDataService evaluateCriteriaDataService;

    private final EvaluateCriteriaDataQueryService evaluateCriteriaDataQueryService;

    private final StorageService storageService;

    public EvaluateCriteriaDataResource(EvaluateCriteriaDataService evaluateCriteriaDataService, EvaluateCriteriaDataQueryService evaluateCriteriaDataQueryService, StorageService storageService) {
        this.evaluateCriteriaDataService = evaluateCriteriaDataService;
        this.evaluateCriteriaDataQueryService = evaluateCriteriaDataQueryService;
        this.storageService = storageService;
    }

    /**
     * POST  /evaluate-criteria-data : Create a new evaluateCriteriaData.
     *
     * @param evaluateCriteriaDataDTO the evaluateCriteriaDataDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new evaluateCriteriaDataDTO, or with status 400 (Bad Request) if the evaluateCriteriaData has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/evaluate-criteria-data")
    @Timed
    public ResponseEntity<EvaluateCriteriaDataDTO> createEvaluateCriteriaData(@Valid @RequestBody EvaluateCriteriaDataDTO evaluateCriteriaDataDTO) throws URISyntaxException {
        log.debug("REST request to save EvaluateCriteriaData : {}", evaluateCriteriaDataDTO);
        if (evaluateCriteriaDataDTO.getId() != null) {
            throw new BadRequestAlertException("A new evaluateCriteriaData cannot already have an ID", ENTITY_NAME, "idexists");
        }

        evaluateCriteriaDataDTO.setCreateDate(ZonedDateTime.now());
        evaluateCriteriaDataDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        EvaluateCriteriaDataDTO result = evaluateCriteriaDataService.save(evaluateCriteriaDataDTO);
        return ResponseEntity.created(new URI("/api/evaluate-criteria-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /evaluate-criteria-data : Updates an existing evaluateCriteriaData.
     *
     * @param evaluateCriteriaDataDTO the evaluateCriteriaDataDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated evaluateCriteriaDataDTO,
     * or with status 400 (Bad Request) if the evaluateCriteriaDataDTO is not valid,
     * or with status 500 (Internal Server Error) if the evaluateCriteriaDataDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/evaluate-criteria-data")
    @Timed
    public ResponseEntity<EvaluateCriteriaDataDTO> updateEvaluateCriteriaData(@Valid @RequestBody EvaluateCriteriaDataDTO evaluateCriteriaDataDTO) throws URISyntaxException {
        log.debug("REST request to update EvaluateCriteriaData : {}", evaluateCriteriaDataDTO);
        if (evaluateCriteriaDataDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        EvaluateCriteriaDataDTO evaluateCriteriaData = evaluateCriteriaDataService.findOne(evaluateCriteriaDataDTO.getId()).get();

        if(evaluateCriteriaDataDTO.getFileDoc() == null)
            evaluateCriteriaDataDTO.setFileDoc(evaluateCriteriaData.getFileDoc());
        evaluateCriteriaDataDTO.setCreateUserLogin(evaluateCriteriaData.getCreateUserLogin());
        evaluateCriteriaDataDTO.setCreateDate(evaluateCriteriaData.getCreateDate());
        evaluateCriteriaDataDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        evaluateCriteriaDataDTO.setModifyDate(ZonedDateTime.now());

        EvaluateCriteriaDataDTO result = evaluateCriteriaDataService.save(evaluateCriteriaDataDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, evaluateCriteriaDataDTO.getId().toString()))
            .body(result);
    }

    @PostMapping("/evaluate-criteria-data/upload-file")
    @Timed
    public ResponseEntity<String> uploadFileEvaluateCriteriaData(@Valid @RequestBody @RequestParam("file") MultipartFile file) throws URISyntaxException, Exception {
        String fileDownloadUri;
        try
        {
            String fileName = storageService.storeEvaluateCriteriaData(file);

            fileDownloadUri = "api/downloadEvaluateCriteriaDataFile/" + fileName;
        }
        catch (Exception ex){
            throw new Exception(ex);
        }

        return ResponseEntity.ok().body(fileDownloadUri);
    }

    /**
     * GET  /evaluate-criteria-data : get all the evaluateCriteriaData.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of evaluateCriteriaData in body
     */
    @GetMapping("/evaluate-criteria-data")
    @Timed
    public ResponseEntity<List<EvaluateCriteriaDataDTO>> getAllEvaluateCriteriaData(EvaluateCriteriaDataCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EvaluateCriteriaData by criteria: {}", criteria);
        Page<EvaluateCriteriaDataDTO> page = evaluateCriteriaDataQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/evaluate-criteria-data");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /evaluate-criteria-data/count : count all the evaluateCriteriaData.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/evaluate-criteria-data/count")
    @Timed
    public ResponseEntity<Long> countEvaluateCriteriaData(EvaluateCriteriaDataCriteria criteria) {
        log.debug("REST request to count EvaluateCriteriaData by criteria: {}", criteria);
        return ResponseEntity.ok().body(evaluateCriteriaDataQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /evaluate-criteria-data/:id : get the "id" evaluateCriteriaData.
     *
     * @param id the id of the evaluateCriteriaDataDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the evaluateCriteriaDataDTO, or with status 404 (Not Found)
     */
    @GetMapping("/evaluate-criteria-data/{id}")
    @Timed
    public ResponseEntity<EvaluateCriteriaDataDTO> getEvaluateCriteriaData(@PathVariable Long id) {
        log.debug("REST request to get EvaluateCriteriaData : {}", id);
        Optional<EvaluateCriteriaDataDTO> evaluateCriteriaDataDTO = evaluateCriteriaDataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(evaluateCriteriaDataDTO);
    }

    /**
     * DELETE  /evaluate-criteria-data/:id : delete the "id" evaluateCriteriaData.
     *
     * @param id the id of the evaluateCriteriaDataDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/evaluate-criteria-data/{id}")
    @Timed
    public ResponseEntity<Void> deleteEvaluateCriteriaData(@PathVariable Long id) {
        log.debug("REST request to delete EvaluateCriteriaData : {}", id);
        evaluateCriteriaDataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
