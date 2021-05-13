package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.ApplicationProcessService;
import com.marineindustryproj.service.PersonService;
import com.marineindustryproj.service.StorageService;
import com.marineindustryproj.service.dto.MatchingEducationalRecordDTO;
import com.marineindustryproj.service.dto.PersonDTO;
import com.marineindustryproj.service.dto.customs.ApplicationProcessModels.ApplicationProcessSaveDataModel;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.ApplicationProcessDTO;
import com.marineindustryproj.service.dto.ApplicationProcessCriteria;
import com.marineindustryproj.service.ApplicationProcessQueryService;
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
 * REST controller for managing ApplicationProcess.
 */
@RestController
@RequestMapping("/api")
public class ApplicationProcessResource {

    private final Logger log = LoggerFactory.getLogger(ApplicationProcessResource.class);

    private static final String ENTITY_NAME = "applicationProcess";

    private final ApplicationProcessService applicationProcessService;

    private final ApplicationProcessQueryService applicationProcessQueryService;

    private final StorageService storageService;

    private final PersonService personService;

    public ApplicationProcessResource(ApplicationProcessService applicationProcessService, ApplicationProcessQueryService applicationProcessQueryService, StorageService storageService, PersonService personService) {
        this.applicationProcessService = applicationProcessService;
        this.applicationProcessQueryService = applicationProcessQueryService;
        this.storageService = storageService;
        this.personService = personService;
    }

    @PostMapping("/application-processes/upload-file")
    @Timed
    public ResponseEntity<String> uploadApplicationProcess(@Valid @RequestBody @RequestParam("file") MultipartFile file) throws URISyntaxException {
        String fileName = storageService.storeApplicationProcessFile(file);
        String fileAddress = "api/application-processes/download/" + fileName;

        return ResponseEntity.created(new URI("/api/application-processes/" + fileAddress))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, fileAddress))
            .body(fileAddress);
    }
    @DeleteMapping("/application-processes/delete/{fileName:.+}")
    @Timed
    public ResponseEntity<Void> deleteFile(@PathVariable String fileName) {
        log.debug("REST request to delete ApplicationProcessFile : {}", fileName);
        storageService.deleteApplicationProcessFile(fileName);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, fileName)).build();
    }
    @GetMapping("/application-processes/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = storageService.loadApplicationProcessFileAsResource(fileName);

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
     * POST  /application-processes : Create a new applicationProcess.
     *
     * @param applicationProcessDTO the applicationProcessDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new applicationProcessDTO, or with status 400 (Bad Request) if the applicationProcess has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/application-processes")
    @Timed
    public ResponseEntity<ApplicationProcessDTO> createApplicationProcess(@Valid @RequestBody ApplicationProcessDTO applicationProcessDTO) throws URISyntaxException {
        log.debug("REST request to save ApplicationProcess : {}", applicationProcessDTO);
        if (applicationProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new applicationProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }

        PersonDTO personDTO = personService.findOne(applicationProcessDTO.getPersonId()).get();
        applicationProcessDTO.setCreateDate(ZonedDateTime.now());
        applicationProcessDTO.setCreateUserLogin(personDTO.getNationalId());
        applicationProcessDTO.setModifyDate(ZonedDateTime.now());
        applicationProcessDTO.setModifyUserLogin(personDTO.getNationalId());

        ApplicationProcessDTO result = applicationProcessService.save(applicationProcessDTO);
        return ResponseEntity.created(new URI("/api/application-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping("/application-processes/save-data-model")
    @Timed
    public ResponseEntity<ApplicationProcessDTO> SaveApplicationProcessDataModel(@Valid @RequestBody ApplicationProcessSaveDataModel applicationProcessSaveDataModel) throws URISyntaxException {
        log.debug("REST request to save ApplicationProcess : {}", applicationProcessSaveDataModel);

        ApplicationProcessDTO result = applicationProcessService.saveDataModel(applicationProcessSaveDataModel);
        return ResponseEntity.created(new URI("/api/matching-educational-records/save-data-model" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    /**
     * PUT  /application-processes : Updates an existing applicationProcess.
     *
     * @param applicationProcessDTO the applicationProcessDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated applicationProcessDTO,
     * or with status 400 (Bad Request) if the applicationProcessDTO is not valid,
     * or with status 500 (Internal Server Error) if the applicationProcessDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/application-processes")
    @Timed
    public ResponseEntity<ApplicationProcessDTO> updateApplicationProcess(@Valid @RequestBody ApplicationProcessDTO applicationProcessDTO) throws URISyntaxException {
        log.debug("REST request to update ApplicationProcess : {}", applicationProcessDTO);
        if (applicationProcessDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        ApplicationProcessDTO applicationProcess = applicationProcessService.findOne(applicationProcessDTO.getId()).get();

        if(applicationProcess.getCreateUserLogin() == null)
            applicationProcessDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        else
            applicationProcessDTO.setCreateUserLogin(applicationProcess.getCreateUserLogin());
        applicationProcessDTO.setCreateDate(applicationProcess.getCreateDate());
        applicationProcessDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        applicationProcessDTO.setModifyDate(ZonedDateTime.now());

        ApplicationProcessDTO result = applicationProcessService.save(applicationProcessDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, applicationProcessDTO.getId().toString()))
            .body(result);
    }
    @PutMapping("/application-processes/toggleImportantMessage/{id}/{type}")
    @Timed
    public ResponseEntity<ApplicationProcessDTO> toggleImportantMessage(@PathVariable long id, @PathVariable boolean type) throws URISyntaxException {
        ApplicationProcessDTO applicationProcessDTO = applicationProcessService.findOne(id).get();

        applicationProcessDTO.setHasImportantMessage(type);
        applicationProcessDTO.setModifyDate(ZonedDateTime.now());

        ApplicationProcessDTO result = applicationProcessService.save(applicationProcessDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, applicationProcessDTO.getId().toString()))
            .body(result);
    }
    /**
     * GET  /application-processes : get all the applicationProcesses.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of applicationProcesses in body
     */
    @GetMapping("/application-processes")
    @Timed
    public ResponseEntity<List<ApplicationProcessDTO>> getAllApplicationProcesses(ApplicationProcessCriteria criteria, Pageable pageable) {
        log.debug("REST request to get ApplicationProcesses by criteria: {}", criteria);
        Page<ApplicationProcessDTO> page = applicationProcessQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/application-processes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /application-processes/count : count all the applicationProcesses.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/application-processes/count")
    @Timed
    public ResponseEntity<Long> countApplicationProcesses(ApplicationProcessCriteria criteria) {
        log.debug("REST request to count ApplicationProcesses by criteria: {}", criteria);
        return ResponseEntity.ok().body(applicationProcessQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /application-processes/:id : get the "id" applicationProcess.
     *
     * @param id the id of the applicationProcessDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the applicationProcessDTO, or with status 404 (Not Found)
     */
    @GetMapping("/application-processes/{id}")
    @Timed
    public ResponseEntity<ApplicationProcessDTO> getApplicationProcess(@PathVariable Long id) {
        log.debug("REST request to get ApplicationProcess : {}", id);
        Optional<ApplicationProcessDTO> applicationProcessDTO = applicationProcessService.findOne(id);
        return ResponseUtil.wrapOrNotFound(applicationProcessDTO);
    }

    /**
     * DELETE  /application-processes/:id : delete the "id" applicationProcess.
     *
     * @param id the id of the applicationProcessDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/application-processes/{id}")
    @Timed
    public ResponseEntity<Void> deleteApplicationProcess(@PathVariable Long id) {
        log.debug("REST request to delete ApplicationProcess : {}", id);
        applicationProcessService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
