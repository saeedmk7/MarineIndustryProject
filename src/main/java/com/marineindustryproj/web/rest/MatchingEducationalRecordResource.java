package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.MatchingEducationalRecordService;
import com.marineindustryproj.service.PersonService;
import com.marineindustryproj.service.StorageService;
import com.marineindustryproj.service.dto.PersonDTO;
import com.marineindustryproj.service.dto.customs.MatchingEducationalRecordModels.MatchingEducationalRecordSaveDataModel;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.MatchingEducationalRecordDTO;
import com.marineindustryproj.service.dto.MatchingEducationalRecordCriteria;
import com.marineindustryproj.service.MatchingEducationalRecordQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
 * REST controller for managing MatchingEducationalRecord.
 */
@RestController
@RequestMapping("/api")
public class MatchingEducationalRecordResource {

    private final Logger log = LoggerFactory.getLogger(MatchingEducationalRecordResource.class);

    private static final String ENTITY_NAME = "matchingEducationalRecord";

    private final MatchingEducationalRecordService matchingEducationalRecordService;

    private final MatchingEducationalRecordQueryService matchingEducationalRecordQueryService;

    private final StorageService storageService;

    private final PersonService personService;

    public MatchingEducationalRecordResource(MatchingEducationalRecordService matchingEducationalRecordService, MatchingEducationalRecordQueryService matchingEducationalRecordQueryService, StorageService storageService, PersonService personService) {
        this.matchingEducationalRecordService = matchingEducationalRecordService;
        this.matchingEducationalRecordQueryService = matchingEducationalRecordQueryService;
        this.storageService = storageService;
        this.personService = personService;
    }

    @PostMapping("/matching-educational-records/upload-file")
    @Timed
    public ResponseEntity<String> uploadMatchingEducationalRecord(@Valid @RequestBody @RequestParam("file") MultipartFile file) throws URISyntaxException {
        String fileName = storageService.storeMatchingEducationalRecordFile(file);
        String fileAddress = "api/matching-educational-records/download/" + fileName;

        return ResponseEntity.created(new URI("/api/matching-educational-records/" + fileAddress))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, fileAddress))
            .body(fileAddress);
    }
    @DeleteMapping("/matching-educational-records/delete/{fileName:.+}")
    @Timed
    public ResponseEntity<Void> deleteFile(@PathVariable String fileName) {
        log.debug("REST request to delete MatchingEducationalRecordFile : {}", fileName);
        storageService.deleteMatchingEducationalRecordFile(fileName);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, fileName)).build();
    }
    @GetMapping("/matching-educational-records/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = storageService.loadMatchingEducationalRecordFileAsResource(fileName);

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
     * POST  /matching-educational-records : Create a new matchingEducationalRecord.
     *
     * @param matchingEducationalRecordDTO the matchingEducationalRecordDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new matchingEducationalRecordDTO, or with status 400 (Bad Request) if the matchingEducationalRecord has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/matching-educational-records")
    @Timed
    public ResponseEntity<MatchingEducationalRecordDTO> createMatchingEducationalRecord(@Valid @RequestBody MatchingEducationalRecordDTO matchingEducationalRecordDTO) throws URISyntaxException {
        log.debug("REST request to save MatchingEducationalRecord : {}", matchingEducationalRecordDTO);
        if (matchingEducationalRecordDTO.getId() != null) {
            throw new BadRequestAlertException("A new matchingEducationalRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }

        PersonDTO personDTO = personService.findOne(matchingEducationalRecordDTO.getPersonId()).get();
        matchingEducationalRecordDTO.setCreateDate(ZonedDateTime.now());
        matchingEducationalRecordDTO.setCreateUserLogin(personDTO.getNationalId());
        matchingEducationalRecordDTO.setModifyDate(ZonedDateTime.now());
        matchingEducationalRecordDTO.setModifyUserLogin(personDTO.getNationalId());

        MatchingEducationalRecordDTO result = matchingEducationalRecordService.save(matchingEducationalRecordDTO);
        return ResponseEntity.created(new URI("/api/matching-educational-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping("/matching-educational-records/save-data-model")
    @Timed
    public ResponseEntity<MatchingEducationalRecordDTO> SaveMatchingEducationalRecordDataModel(@Valid @RequestBody MatchingEducationalRecordSaveDataModel matchingEducationalRecordSaveDataModel) throws URISyntaxException {
        log.debug("REST request to save RunPhase : {}", matchingEducationalRecordSaveDataModel);

        MatchingEducationalRecordDTO result = matchingEducationalRecordService.saveDataModel(matchingEducationalRecordSaveDataModel);
        return ResponseEntity.created(new URI("/api/matching-educational-records/save-data-model" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /matching-educational-records : Updates an existing matchingEducationalRecord.
     *
     * @param matchingEducationalRecordDTO the matchingEducationalRecordDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated matchingEducationalRecordDTO,
     * or with status 400 (Bad Request) if the matchingEducationalRecordDTO is not valid,
     * or with status 500 (Internal Server Error) if the matchingEducationalRecordDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/matching-educational-records")
    @Timed
    public ResponseEntity<MatchingEducationalRecordDTO> updateMatchingEducationalRecord(@Valid @RequestBody MatchingEducationalRecordDTO matchingEducationalRecordDTO) throws URISyntaxException {
        log.debug("REST request to update MatchingEducationalRecord : {}", matchingEducationalRecordDTO);
        if (matchingEducationalRecordDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        MatchingEducationalRecordDTO matchingEducationalRecord = matchingEducationalRecordService.findOne(matchingEducationalRecordDTO.getId()).get();

        if(matchingEducationalRecord.getCreateUserLogin() == null)
            matchingEducationalRecordDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        else
            matchingEducationalRecordDTO.setCreateUserLogin(matchingEducationalRecord.getCreateUserLogin());
        matchingEducationalRecordDTO.setCreateDate(matchingEducationalRecord.getCreateDate());
        matchingEducationalRecordDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        matchingEducationalRecordDTO.setModifyDate(ZonedDateTime.now());


        MatchingEducationalRecordDTO result = matchingEducationalRecordService.save(matchingEducationalRecordDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, matchingEducationalRecordDTO.getId().toString()))
            .body(result);
    }
    @PutMapping("/matching-educational-records/toggleImportantMessage/{id}/{type}")
    @Timed
    public ResponseEntity<MatchingEducationalRecordDTO> toggleImportantMessage(@PathVariable long id, @PathVariable boolean type) throws URISyntaxException {
        MatchingEducationalRecordDTO matchingEducationalRecordDTO = matchingEducationalRecordService.findOne(id).get();

        matchingEducationalRecordDTO.setHasImportantMessage(type);
        matchingEducationalRecordDTO.setModifyDate(ZonedDateTime.now());

        MatchingEducationalRecordDTO result = matchingEducationalRecordService.save(matchingEducationalRecordDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, matchingEducationalRecordDTO.getId().toString()))
            .body(result);
    }
    /**
     * GET  /matching-educational-records : get all the matchingEducationalRecords.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of matchingEducationalRecords in body
     */
    @GetMapping("/matching-educational-records")
    @Timed
    public ResponseEntity<List<MatchingEducationalRecordDTO>> getAllMatchingEducationalRecords(MatchingEducationalRecordCriteria criteria, Pageable pageable) {
        log.debug("REST request to get MatchingEducationalRecords by criteria: {}", criteria);
        Page<MatchingEducationalRecordDTO> page = matchingEducationalRecordQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/matching-educational-records");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /matching-educational-records/count : count all the matchingEducationalRecords.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/matching-educational-records/count")
    @Timed
    public ResponseEntity<Long> countMatchingEducationalRecords(MatchingEducationalRecordCriteria criteria) {
        log.debug("REST request to count MatchingEducationalRecords by criteria: {}", criteria);
        return ResponseEntity.ok().body(matchingEducationalRecordQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /matching-educational-records/:id : get the "id" matchingEducationalRecord.
     *
     * @param id the id of the matchingEducationalRecordDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the matchingEducationalRecordDTO, or with status 404 (Not Found)
     */
    @GetMapping("/matching-educational-records/{id}")
    @Timed
    public ResponseEntity<MatchingEducationalRecordDTO> getMatchingEducationalRecord(@PathVariable Long id) {
        log.debug("REST request to get MatchingEducationalRecord : {}", id);
        Optional<MatchingEducationalRecordDTO> matchingEducationalRecordDTO = matchingEducationalRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(matchingEducationalRecordDTO);
    }

    /**
     * DELETE  /matching-educational-records/:id : delete the "id" matchingEducationalRecord.
     *
     * @param id the id of the matchingEducationalRecordDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/matching-educational-records/{id}")
    @Timed
    public ResponseEntity<Void> deleteMatchingEducationalRecord(@PathVariable Long id) {
        log.debug("REST request to delete MatchingEducationalRecord : {}", id);
        matchingEducationalRecordService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
