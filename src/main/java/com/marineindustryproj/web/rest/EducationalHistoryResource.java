package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.domain.enumeration.RequestStatus;
import com.marineindustryproj.repository.PersonRepository;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.EducationalHistoryService;
import com.marineindustryproj.service.PersonService;
import com.marineindustryproj.service.StorageService;
import com.marineindustryproj.service.dto.PersonDTO;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.EducationalHistoryDTO;
import com.marineindustryproj.service.dto.EducationalHistoryCriteria;
import com.marineindustryproj.service.EducationalHistoryQueryService;
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
 * REST controller for managing EducationalHistory.
 */
@RestController
@RequestMapping("/api")
public class EducationalHistoryResource {

    private final Logger log = LoggerFactory.getLogger(EducationalHistoryResource.class);

    private static final String ENTITY_NAME = "educationalHistory";

    private final EducationalHistoryService educationalHistoryService;

    private final PersonService personService;

    private final EducationalHistoryQueryService educationalHistoryQueryService;

    private final StorageService storageService;

    public EducationalHistoryResource(EducationalHistoryService educationalHistoryService,
                                      PersonService personService,
                                      EducationalHistoryQueryService educationalHistoryQueryService,
                                      StorageService storageService) {
        this.educationalHistoryService = educationalHistoryService;
        this.personService = personService;
        this.educationalHistoryQueryService = educationalHistoryQueryService;
        this.storageService = storageService;
    }

    /**
     * POST  /educational-histories : Create a new educationalHistory.
     *
     * @param educationalHistoryDTO the educationalHistoryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new educationalHistoryDTO, or with status 400 (Bad Request) if the educationalHistory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/educational-histories")
    @Timed
    public ResponseEntity<EducationalHistoryDTO> createEducationalHistory(@Valid @RequestBody EducationalHistoryDTO educationalHistoryDTO) throws URISyntaxException {
        log.debug("REST request to save EducationalHistory : {}", educationalHistoryDTO);
        if (educationalHistoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new educationalHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }

        PersonDTO personDTO = personService.findOne(educationalHistoryDTO.getPersonId()).get();

        educationalHistoryDTO.setCreateDate(ZonedDateTime.now());
        educationalHistoryDTO.setCreateUserLogin(personDTO.getNationalId());

        EducationalHistoryDTO result = educationalHistoryService.save(educationalHistoryDTO);
        return ResponseEntity.created(new URI("/api/educational-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * POST  /educational-histories : Create a new educationalHistory.
     *
     * @return the ResponseEntity with status 201 (Created) and with body the new educationalHistoryDTO, or with status 400 (Bad Request) if the educationalHistory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/educational-histories/upload-file")
    @Timed
    public ResponseEntity<String> uploadFileEducationalHistory(@Valid @RequestBody @RequestParam("file") MultipartFile file) throws URISyntaxException, Exception {
        String fileDownloadUri;
        try
        {
            String fileName = storageService.storeHistoryFile(file);

            fileDownloadUri = "api/downloadHistoryFile/" + fileName;
        }
        catch (Exception ex){
            throw new Exception(ex);
        }

        return ResponseEntity.ok().body(fileDownloadUri);
    }

    /**
     * POST  /educational-histories : Updates an existing educationalHistory.
     *
     * @param educationalHistoryDTO the educationalHistoryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated educationalHistoryDTO,
     * or with status 400 (Bad Request) if the educationalHistoryDTO is not valid,
     * or with status 500 (Internal Server Error) if the educationalHistoryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/finalize-educational-histories")
    @Timed
    public ResponseEntity<EducationalHistoryDTO> finalizeUpdateEducationalHistory(@Valid @RequestBody EducationalHistoryDTO educationalHistoryDTO) throws URISyntaxException {
        log.debug("REST request to finalize EducationalHistory : {}", educationalHistoryDTO);
        if (educationalHistoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        EducationalHistoryDTO educationalHistory = educationalHistoryService.findOne(educationalHistoryDTO.getId()).get();

        educationalHistoryDTO.setFileDoc(educationalHistory.getFileDoc());
        educationalHistoryDTO.setCreateUserLogin(educationalHistory.getCreateUserLogin());
        educationalHistoryDTO.setCreateDate(educationalHistory.getCreateDate());
        educationalHistoryDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        educationalHistoryDTO.setModifyDate(ZonedDateTime.now());
        educationalHistoryDTO.setChangeStatusUserLogin(SecurityUtils.getCurrentUserLogin().get());
        educationalHistoryDTO.setStatus(0);
        educationalHistoryDTO.setRequestStatus(RequestStatus.ACCEPT);

        EducationalHistoryDTO result = educationalHistoryService.save(educationalHistoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, educationalHistoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /educational-histories : Updates an existing educationalHistory.
     *
     * @param educationalHistoryDTO the educationalHistoryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated educationalHistoryDTO,
     * or with status 400 (Bad Request) if the educationalHistoryDTO is not valid,
     * or with status 500 (Internal Server Error) if the educationalHistoryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/educational-histories")
    @Timed
    public ResponseEntity<EducationalHistoryDTO> updateEducationalHistory(@Valid @RequestBody EducationalHistoryDTO educationalHistoryDTO) throws URISyntaxException {
        log.debug("REST request to update EducationalHistory : {}", educationalHistoryDTO);
        if (educationalHistoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        EducationalHistoryDTO educationalHistory = educationalHistoryService.findOne(educationalHistoryDTO.getId()).get();

        educationalHistoryDTO.setFileDoc(educationalHistory.getFileDoc());
        educationalHistoryDTO.setCreateUserLogin(educationalHistory.getCreateUserLogin());
        educationalHistoryDTO.setCreateDate(educationalHistory.getCreateDate());
        educationalHistoryDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        educationalHistoryDTO.setModifyDate(ZonedDateTime.now());

        EducationalHistoryDTO result = educationalHistoryService.save(educationalHistoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, educationalHistoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /educational-histories : get all the educationalHistories.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of educationalHistories in body
     */
    @GetMapping("/educational-histories")
    @Timed
    public ResponseEntity<List<EducationalHistoryDTO>> getAllEducationalHistories(EducationalHistoryCriteria criteria, Pageable pageable) {
        log.debug("REST request to get EducationalHistories by criteria: {}", criteria);
        Page<EducationalHistoryDTO> page = educationalHistoryQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/educational-histories");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /educational-histories/count : count all the educationalHistories.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/educational-histories/count")
    @Timed
    public ResponseEntity<Long> countEducationalHistories(EducationalHistoryCriteria criteria) {
        log.debug("REST request to count EducationalHistories by criteria: {}", criteria);
        return ResponseEntity.ok().body(educationalHistoryQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /educational-histories/:id : get the "id" educationalHistory.
     *
     * @param id the id of the educationalHistoryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the educationalHistoryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/educational-histories/{id}")
    @Timed
    public ResponseEntity<EducationalHistoryDTO> getEducationalHistory(@PathVariable Long id) {
        log.debug("REST request to get EducationalHistory : {}", id);
        Optional<EducationalHistoryDTO> educationalHistoryDTO = educationalHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(educationalHistoryDTO);
    }

    /**
     * DELETE  /educational-histories/:id : delete the "id" educationalHistory.
     *
     * @param id the id of the educationalHistoryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/educational-histories/{id}")
    @Timed
    public ResponseEntity<Void> deleteEducationalHistory(@PathVariable Long id) {
        log.debug("REST request to delete EducationalHistory : {}", id);
        educationalHistoryService.delete(id);

        Optional<EducationalHistoryDTO> educationalHistory = educationalHistoryService.findOne(id);
        if(educationalHistory.isPresent() && !educationalHistory.get().getFileDoc().isEmpty()) {
            storageService.deleteFile(educationalHistory.get().getFileDoc());
        }
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
