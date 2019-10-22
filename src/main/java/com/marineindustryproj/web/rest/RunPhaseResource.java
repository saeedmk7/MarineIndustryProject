package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.RunPhaseService;
import com.marineindustryproj.service.StorageService;
import com.marineindustryproj.service.dto.customs.RunPhaseSaveDataModel;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.RunPhaseDTO;
import com.marineindustryproj.service.dto.RunPhaseCriteria;
import com.marineindustryproj.service.RunPhaseQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing RunPhase.
 */
@RestController
@RequestMapping("/api")
public class RunPhaseResource {

    private final Logger log = LoggerFactory.getLogger(RunPhaseResource.class);

    private static final String ENTITY_NAME = "runPhase";

    private final RunPhaseService runPhaseService;

    private final RunPhaseQueryService runPhaseQueryService;

    private final StorageService storageService;

    public RunPhaseResource(RunPhaseService runPhaseService, RunPhaseQueryService runPhaseQueryService, StorageService storageService) {
        this.runPhaseService = runPhaseService;
        this.runPhaseQueryService = runPhaseQueryService;
        this.storageService = storageService;
    }

    @PostMapping("/run-phases/upload-file")
    @Timed
    public ResponseEntity<String> uploadRunPhase(@Valid @RequestBody @RequestParam("file") MultipartFile file) throws URISyntaxException {
        String fileName = storageService.storeRunPhaseFile(file);
        String fileAddress = "api/run-phases/download/" + fileName;

        return ResponseEntity.created(new URI("/api/run-phases/" + fileAddress))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, fileAddress))
            .body(fileAddress);
    }
    @DeleteMapping("/run-phases/delete/{fileName:.+}")
    @Timed
    public ResponseEntity<Void> deleteFile(@PathVariable String fileName) {
        log.debug("REST request to delete RunPhaseFile : {}", fileName);
        storageService.deleteRunPhaseFile(fileName);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, fileName)).build();
    }
    @GetMapping("/run-phases/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = storageService.loadRunPhaseFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
    }
    /**
     * POST  /run-phases : Create a new runPhase.
     *
     * @param runPhaseDTO the runPhaseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new runPhaseDTO, or with status 400 (Bad Request) if the runPhase has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/run-phases")
    @Timed
    public ResponseEntity<RunPhaseDTO> createRunPhase(@Valid @RequestBody RunPhaseDTO runPhaseDTO) throws URISyntaxException {
        log.debug("REST request to save RunPhase : {}", runPhaseDTO);
        if (runPhaseDTO.getId() != null) {
            throw new BadRequestAlertException("A new runPhase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RunPhaseDTO result = runPhaseService.save(runPhaseDTO);
        return ResponseEntity.created(new URI("/api/run-phases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping("/run-phases/save-data-model")
    @Timed
    public ResponseEntity<RunPhaseDTO> SaveRunPhaseDataModel(@Valid @RequestBody RunPhaseSaveDataModel runPhaseSaveDataModel) throws URISyntaxException {
        log.debug("REST request to save RunPhase : {}", runPhaseSaveDataModel);
        /*if (runPhaseDTO.getId() != null) {
            throw new BadRequestAlertException("A new runPhase cannot already have an ID", ENTITY_NAME, "idexists");
        }*/
        RunPhaseDTO result = runPhaseService.saveDataModel(runPhaseSaveDataModel);
        return ResponseEntity.created(new URI("/api/run-phases/save-data-model" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /run-phases : Updates an existing runPhase.
     *
     * @param runPhaseDTO the runPhaseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated runPhaseDTO,
     * or with status 400 (Bad Request) if the runPhaseDTO is not valid,
     * or with status 500 (Internal Server Error) if the runPhaseDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/run-phases")
    @Timed
    public ResponseEntity<RunPhaseDTO> updateRunPhase(@Valid @RequestBody RunPhaseDTO runPhaseDTO) throws URISyntaxException {
        log.debug("REST request to update RunPhase : {}", runPhaseDTO);
        if (runPhaseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RunPhaseDTO result = runPhaseService.save(runPhaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, runPhaseDTO.getId().toString()))
            .body(result);
    }
    @PutMapping("/run-phases/toggleImportantMessage/{id}/{type}")
    @Timed
    public ResponseEntity<RunPhaseDTO> toggleImportantMessage(@PathVariable long id, @PathVariable boolean type) throws URISyntaxException {
        RunPhaseDTO runPhaseDTO = runPhaseService.findOne(id).get();

        runPhaseDTO.setHasImportantMessage(type);
        runPhaseDTO.setModifyDate(ZonedDateTime.now());

        RunPhaseDTO result = runPhaseService.save(runPhaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, runPhaseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /run-phases : get all the runPhases.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of runPhases in body
     */
    @GetMapping("/run-phases")
    @Timed
    public ResponseEntity<List<RunPhaseDTO>> getAllRunPhases(RunPhaseCriteria criteria, Pageable pageable) {
        log.debug("REST request to get RunPhases by criteria: {}", criteria);
        Page<RunPhaseDTO> page = runPhaseQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/run-phases");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /run-phases/count : count all the runPhases.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/run-phases/count")
    @Timed
    public ResponseEntity<Long> countRunPhases (RunPhaseCriteria criteria) {
        log.debug("REST request to count RunPhases by criteria: {}", criteria);
        return ResponseEntity.ok().body(runPhaseQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /run-phases/:id : get the "id" runPhase.
     *
     * @param id the id of the runPhaseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the runPhaseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/run-phases/{id}")
    @Timed
    public ResponseEntity<RunPhaseDTO> getRunPhase(@PathVariable Long id) {
        log.debug("REST request to get RunPhase : {}", id);
        Optional<RunPhaseDTO> runPhaseDTO = runPhaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(runPhaseDTO);
    }

    /**
     * DELETE  /run-phases/:id : delete the "id" runPhase.
     *
     * @param id the id of the runPhaseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/run-phases/{id}")
    @Timed
    public ResponseEntity<Void> deleteRunPhase(@PathVariable Long id) {
        log.debug("REST request to delete RunPhase : {}", id);
        runPhaseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
