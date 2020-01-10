package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.domain.MediaAwarenessReport;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.*;
import com.marineindustryproj.service.dto.*;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * REST controller for managing Document.
 */
@RestController
@RequestMapping("/api")
public class DocumentResource {

    private final Logger log = LoggerFactory.getLogger(DocumentResource.class);

    private static final String ENTITY_NAME = "document";

    private final DocumentService documentService;

    private final DocumentQueryService documentQueryService;

    private final PersonService personService;

    private final PersonQueryService personQueryService;

    private final JobService jobService;

    private final JobQueryService jobQueryService;

    private final TeacherService teacherService;

    private final TeacherQueryService teacherQueryService;

    private final EducationalCenterService educationalCenterService;

    private final EducationalCenterQueryService educationalCenterQueryService;

    private final EducationalModuleService educationalModuleService;

    private final EducationalModuleQueryService educationalModuleQueryService;

    private final DesignAndPlanningService designAndPlanningService;

    private final DesignAndPlanningQueryService designAndPlanningQueryService;

    private final FinalNiazsanjiReportService finalNiazsanjiReportService;

    private final FinalNiazsanjiReportQueryService finalNiazsanjiReportQueryService;

    private final RunPhaseService runPhaseService;

    private final RunPhaseQueryService runPhaseQueryService;

    private final ResourceService resourceService;

    private final InstructionService instructionService;

    private final ResourceQueryService resourceQueryService;

    private final InstructionQueryService instructionQueryService;

    private final RequestOrganizationNiazsanjiService requestOrganizationNiazsanjiService;

    private final RequestOrganizationNiazsanjiQueryService requestOrganizationNiazsanjiQueryService;

    private final FinalOrganizationNiazsanjiService finalOrganizationNiazsanjiService;

    private final FinalOrganizationNiazsanjiQueryService finalOrganizationNiazsanjiQueryService;

    private final AnnouncementService announcementService;

    private final AnnouncementQueryService announcementQueryService;

    private final StorageService storageService;

    private final RequestEducationalModuleService requestEducationalModuleService;

    private final RequestEducationalModuleQueryService requestEducationalModuleQueryService;

    private final UsersRequestService usersRequestService;

    private final UsersRequestQueryService usersRequestQueryService;

    private final NiazsanjiFardiService niazsanjiFardiService;

    private final NiazsanjiFardiQueryService niazsanjiFardiQueryService;

    private final InvestToGroupTransactionService investToGroupTransactionService;

    private final InvestToGroupTransactionQueryService investToGroupTransactionQueryService;

    private final RequestNiazsanjiFardiService requestNiazsanjiFardiService;

    private final RequestNiazsanjiFardiQueryService requestNiazsanjiFardiQueryService;

    private final MediaAwarenessReportService mediaAwarenessReportService;

    private final MediaAwarenessReportQueryService mediaAwarenessReportQueryService;

    private final PreJobNiazsanjiService preJobNiazsanjiService;

    private final PreJobNiazsanjiQueryService preJobNiazsanjiQueryService;

    public DocumentResource(DocumentService documentService,
                            DocumentQueryService documentQueryService,
                            PersonService personService,
                            PersonQueryService personQueryService,
                            JobService jobService,
                            JobQueryService jobQueryService,
                            TeacherService teacherService,
                            TeacherQueryService teacherQueryService,
                            EducationalCenterService educationalCenterService,
                            EducationalCenterQueryService educationalCenterQueryService,
                            EducationalModuleService educationalModuleService,
                            EducationalModuleQueryService educationalModuleQueryService,
                            DesignAndPlanningService designAndPlanningService,
                            DesignAndPlanningQueryService designAndPlanningQueryService,
                            FinalNiazsanjiReportService finalNiazsanjiReportService,
                            FinalNiazsanjiReportQueryService finalNiazsanjiReportQueryService,
                            RunPhaseService runPhaseService,
                            RunPhaseQueryService runPhaseQueryService,
                            ResourceService resourceService,
                            InstructionService instructionService,
                            ResourceQueryService resourceQueryService,
                            InstructionQueryService instructionQueryService,
                            RequestOrganizationNiazsanjiService requestOrganizationNiazsanjiService,
                            RequestOrganizationNiazsanjiQueryService requestOrganizationNiazsanjiQueryService,
                            FinalOrganizationNiazsanjiService finalOrganizationNiazsanjiService,
                            FinalOrganizationNiazsanjiQueryService finalOrganizationNiazsanjiQueryService,
                            AnnouncementService announcementService,
                            AnnouncementQueryService announcementQueryService,
                            StorageService storageService,
                            RequestEducationalModuleService requestEducationalModuleService,
                            RequestEducationalModuleQueryService requestEducationalModuleQueryService,
                            UsersRequestService usersRequestService,
                            UsersRequestQueryService usersRequestQueryService,
                            NiazsanjiFardiService niazsanjiFardiService,
                            NiazsanjiFardiQueryService niazsanjiFardiQueryService,
                            InvestToGroupTransactionService investToGroupTransactionService, InvestToGroupTransactionQueryService investToGroupTransactionQueryService, RequestNiazsanjiFardiService requestNiazsanjiFardiService,
                            RequestNiazsanjiFardiQueryService requestNiazsanjiFardiQueryService, MediaAwarenessReportService mediaAwarenessReportService, MediaAwarenessReportQueryService mediaAwarenessReportQueryService, PreJobNiazsanjiService preJobNiazsanjiService, PreJobNiazsanjiQueryService preJobNiazsanjiQueryService) {
        this.documentService = documentService;
        this.documentQueryService = documentQueryService;
        this.personService = personService;
        this.personQueryService = personQueryService;
        this.jobService = jobService;
        this.jobQueryService = jobQueryService;
        this.teacherService = teacherService;
        this.teacherQueryService = teacherQueryService;
        this.educationalCenterService = educationalCenterService;
        this.educationalCenterQueryService = educationalCenterQueryService;
        this.educationalModuleService = educationalModuleService;
        this.educationalModuleQueryService = educationalModuleQueryService;
        this.designAndPlanningService = designAndPlanningService;
        this.designAndPlanningQueryService = designAndPlanningQueryService;
        this.finalNiazsanjiReportService = finalNiazsanjiReportService;
        this.finalNiazsanjiReportQueryService = finalNiazsanjiReportQueryService;
        this.runPhaseService = runPhaseService;
        this.runPhaseQueryService = runPhaseQueryService;
        this.resourceService = resourceService;
        this.instructionService = instructionService;
        this.resourceQueryService = resourceQueryService;
        this.instructionQueryService = instructionQueryService;
        this.requestOrganizationNiazsanjiService = requestOrganizationNiazsanjiService;
        this.requestOrganizationNiazsanjiQueryService = requestOrganizationNiazsanjiQueryService;
        this.finalOrganizationNiazsanjiService = finalOrganizationNiazsanjiService;
        this.finalOrganizationNiazsanjiQueryService = finalOrganizationNiazsanjiQueryService;
        this.announcementService = announcementService;
        this.announcementQueryService = announcementQueryService;
        this.storageService = storageService;
        this.requestEducationalModuleService = requestEducationalModuleService;
        this.requestEducationalModuleQueryService = requestEducationalModuleQueryService;
        this.usersRequestService = usersRequestService;
        this.usersRequestQueryService = usersRequestQueryService;
        this.niazsanjiFardiService = niazsanjiFardiService;
        this.niazsanjiFardiQueryService = niazsanjiFardiQueryService;
        this.investToGroupTransactionService = investToGroupTransactionService;
        this.investToGroupTransactionQueryService = investToGroupTransactionQueryService;
        this.requestNiazsanjiFardiService = requestNiazsanjiFardiService;
        this.requestNiazsanjiFardiQueryService = requestNiazsanjiFardiQueryService;
        this.mediaAwarenessReportService = mediaAwarenessReportService;
        this.mediaAwarenessReportQueryService = mediaAwarenessReportQueryService;
        this.preJobNiazsanjiService = preJobNiazsanjiService;
        this.preJobNiazsanjiQueryService = preJobNiazsanjiQueryService;
    }
    /**
     * POST  /documents : Create a new document.
     *
     * @param file the documentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new documentDTO, or with status 400 (Bad Request) if the document has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/documents")
    @Timed
    public ResponseEntity<DocumentDTO> createDocument(@Valid @RequestBody @RequestParam("file") MultipartFile file, @RequestParam("entityId") Long entityId, @RequestParam("entityName") String entityName, @RequestParam("title") String title) throws URISyntaxException,Exception {
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setEntityId(entityId);
        documentDTO.setEntityName(entityName);
        documentDTO.setTitle(title);

        log.debug("REST request to save Document : {}", documentDTO);
        if (documentDTO.getId() != null) {
            throw new BadRequestAlertException("A new document cannot already have an ID", ENTITY_NAME, "idexists");
        }
        documentDTO.setCreateDate(ZonedDateTime.now());
        documentDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        try
        {
            String fileName = storageService.storeFile(file);

            String fileDownloadUri = "api/downloadFile/" + fileName;

             documentDTO.setFileDocContentType(file.getContentType());
             documentDTO.setFileDoc(fileDownloadUri);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
        DocumentDTO result = documentService.save(documentDTO);
        try {
            if (documentDTO.getEntityName().toLowerCase().equals("person")) {
                PersonDTO person = personService.findOne(documentDTO.getEntityId()).get();
                Set<DocumentDTO> documents = person.getDocuments();
                documents.add(result);
                person.setDocuments(documents);
                personService.save(person, true);
            }
            if (documentDTO.getEntityName().toLowerCase().equals("job")) {
                JobDTO job = jobService.findOne(documentDTO.getEntityId()).get();
                Set<DocumentDTO> documents = job.getDocuments();
                documents.add(result);
                job.setDocuments(documents);
                jobService.save(job);
            }
            if (documentDTO.getEntityName().toLowerCase().equals("teacher")) {
                TeacherDTO teacher = teacherService.findOne(documentDTO.getEntityId()).get();
                Set<DocumentDTO> documents = teacher.getDocuments();
                documents.add(result);
                teacher.setDocuments(documents);
                teacherService.save(teacher);
            }
            if (documentDTO.getEntityName().toLowerCase().equals("educationalcenter")) {
                EducationalCenterDTO educationalCenter = educationalCenterService.findOne(documentDTO.getEntityId()).get();
                Set<DocumentDTO> documents = educationalCenter.getDocuments();
                documents.add(result);
                educationalCenter.setDocuments(documents);
                educationalCenterService.save(educationalCenter);
            }
            if (documentDTO.getEntityName().toLowerCase().equals("educationalmodule")) {
                EducationalModuleDTO educationalModule = educationalModuleService.findOne(documentDTO.getEntityId()).get();
                Set<DocumentDTO> documents = educationalModule.getDocuments();
                documents.add(result);
                educationalModule.setDocuments(documents);
                educationalModuleService.save(educationalModule);
            }
            if (documentDTO.getEntityName().toLowerCase().equals("designandplanning")) {
                DesignAndPlanningDTO designAndPlanning = designAndPlanningService.findOne(documentDTO.getEntityId()).get();
                Set<DocumentDTO> documents = designAndPlanning.getDocuments();
                documents.add(result);
                designAndPlanning.setDocuments(documents);
                designAndPlanningService.save(designAndPlanning);
            }
            if (documentDTO.getEntityName().toLowerCase().equals("finalniazsanjireport")) {
                FinalNiazsanjiReportDTO finalNiazsanjiReport = finalNiazsanjiReportService.findOne(documentDTO.getEntityId()).get();
                Set<DocumentDTO> documents = finalNiazsanjiReport.getDocuments();
                documents.add(result);
                finalNiazsanjiReport.setDocuments(documents);
                finalNiazsanjiReportService.save(finalNiazsanjiReport);
            }
            if (documentDTO.getEntityName().toLowerCase().equals("runphase")) {
                RunPhaseDTO runPhase = runPhaseService.findOne(documentDTO.getEntityId()).get();
                Set<DocumentDTO> documents = runPhase.getDocuments();
                documents.add(result);
                runPhase.setDocuments(documents);
                runPhaseService.save(runPhase);
            }
            if (documentDTO.getEntityName().toLowerCase().equals("resource")) {
                ResourceDTO resource = resourceService.findOne(documentDTO.getEntityId()).get();
                Set<DocumentDTO> documents = resource.getDocuments();
                documents.add(result);
                resource.setDocuments(documents);
                resourceService.save(resource);
            }
            if (documentDTO.getEntityName().toLowerCase().equals("instruction")) {
                InstructionDTO instruction = instructionService.findOne(documentDTO.getEntityId()).get();
                Set<DocumentDTO> documents = instruction.getDocuments();
                documents.add(result);
                instruction.setDocuments(documents);
                instructionService.save(instruction);
            }
            if (documentDTO.getEntityName().toLowerCase().equals("requestorganizationniazsanji")) {
                RequestOrganizationNiazsanjiDTO requestOrganizationNiazsanji = requestOrganizationNiazsanjiService.findOne(documentDTO.getEntityId()).get();
                Set<DocumentDTO> documents = requestOrganizationNiazsanji.getDocuments();
                documents.add(result);
                requestOrganizationNiazsanji.setDocuments(documents);
                requestOrganizationNiazsanjiService.save(requestOrganizationNiazsanji);
            }
            if (documentDTO.getEntityName().toLowerCase().equals("finalorganizationniazsanji")) {
                FinalOrganizationNiazsanjiDTO finalOrganizationNiazsanji = finalOrganizationNiazsanjiService.findOne(documentDTO.getEntityId()).get();
                Set<DocumentDTO> documents = finalOrganizationNiazsanji.getDocuments();
                documents.add(result);
                finalOrganizationNiazsanji.setDocuments(documents);
                finalOrganizationNiazsanjiService.save(finalOrganizationNiazsanji);
            }
            if (documentDTO.getEntityName().toLowerCase().equals("announcement")) {
                AnnouncementDTO announcement = announcementService.findOne(documentDTO.getEntityId()).get();
                Set<DocumentDTO> documents = announcement.getDocuments();
                documents.add(result);
                announcement.setDocuments(documents);
                announcementService.save(announcement);
            }
            if (documentDTO.getEntityName().toLowerCase().equals("requesteducationalmodule")) {
                RequestEducationalModuleDTO requestEducationalModule = requestEducationalModuleService.findOne(documentDTO.getEntityId()).get();
                Set<DocumentDTO> documents = requestEducationalModule.getDocuments();
                documents.add(result);
                requestEducationalModule.setDocuments(documents);
                requestEducationalModuleService.save(requestEducationalModule);
            }
            if (documentDTO.getEntityName().toLowerCase().equals("usersrequest")) {
                UsersRequestDTO usersRequest = usersRequestService.findOne(documentDTO.getEntityId()).get();
                Set<DocumentDTO> documents = usersRequest.getDocuments();
                documents.add(result);
                usersRequest.setDocuments(documents);
                usersRequestService.save(usersRequest);
            }
            if (documentDTO.getEntityName().toLowerCase().equals("requestniazsanjifardi")) {
                RequestNiazsanjiFardiDTO requestNiazsanjiFardi = requestNiazsanjiFardiService.findOne(documentDTO.getEntityId()).get();
                Set<DocumentDTO> documents = requestNiazsanjiFardi.getDocuments();
                documents.add(result);
                requestNiazsanjiFardi.setDocuments(documents);
                requestNiazsanjiFardiService.save(requestNiazsanjiFardi);
            }
            if (documentDTO.getEntityName().toLowerCase().equals("niazsanjifardi")) {
                NiazsanjiFardiDTO niazsanjiFardi = niazsanjiFardiService.findOne(documentDTO.getEntityId()).get();
                Set<DocumentDTO> documents = niazsanjiFardi.getDocuments();
                documents.add(result);
                niazsanjiFardi.setDocuments(documents);
                niazsanjiFardiService.save(niazsanjiFardi);
            }
            if (documentDTO.getEntityName().toLowerCase().equals("investtogrouptransaction")) {
                InvestToGroupTransactionDTO investToGroupTransaction =
                    investToGroupTransactionService.findOne(documentDTO.getEntityId()).get();
                Set<DocumentDTO> documents = investToGroupTransaction.getDocuments();
                documents.add(result);
                investToGroupTransaction.setDocuments(documents);
                investToGroupTransactionService.save(investToGroupTransaction);
            }
            if (documentDTO.getEntityName().toLowerCase().equals("mediaawarenessreport")) {
                MediaAwarenessReportDTO mediaAwarenessReport =
                    mediaAwarenessReportService.findOne(documentDTO.getEntityId()).get();
                Set<DocumentDTO> documents = mediaAwarenessReport.getDocuments();
                documents.add(result);
                mediaAwarenessReport.setDocuments(documents);
                mediaAwarenessReportService.save(mediaAwarenessReport);
            }
            if (documentDTO.getEntityName().toLowerCase().equals("prejobniazsanji")) {
                PreJobNiazsanjiDTO preJobNiazsanji =
                    preJobNiazsanjiService.findOne(documentDTO.getEntityId()).get();
                Set<DocumentDTO> documents = preJobNiazsanji.getDocuments();
                documents.add(result);
                preJobNiazsanji.setDocuments(documents);
                preJobNiazsanjiService.save(preJobNiazsanji);
            }
        } catch (Exception ex) {
            log.debug(ex.getMessage());
        }
        return ResponseEntity.created(new URI("/api/documents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


    /**
     * PUT  /documents : Updates an existing document.
     *
     * @param documentDTO the documentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated documentDTO,
     * or with status 400 (Bad Request) if the documentDTO is not valid,
     * or with status 500 (Internal Server Error) if the documentDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/documents")
    @Timed
    public ResponseEntity<DocumentDTO> updateDocument(@Valid @RequestBody DocumentDTO documentDTO) throws URISyntaxException {
        log.debug("REST request to update Document : {}", documentDTO);
        if (documentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        documentDTO.setCreateDate(ZonedDateTime.now());
        documentDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        DocumentDTO result = documentService.save(documentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, documentDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /documents : get all the documents.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of documents in body
     */
    @GetMapping("/documents")
    @Timed
    public ResponseEntity<List<DocumentDTO>> getAllDocuments(DocumentCriteria criteria, Pageable pageable, String entityName, Long entityId) {
        log.debug("REST request to get Documents by criteria: {}", criteria);
       /* StringFilter titleFilter = new StringFilter();
        titleFilter.setContains("saeed");
        criteria.setTitle(titleFilter);*/
        Page<DocumentDTO> page = documentQueryService.findByCriteria(criteria, pageable, entityName, entityId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/documents");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /documents/count : count all the documents.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/documents/count")
    @Timed
    public ResponseEntity<Long> countDocuments (DocumentCriteria criteria, String entityName, Long entityId) {
        log.debug("REST request to count Documents by criteria: {}", criteria);
        return ResponseEntity.ok().body(documentQueryService.countByCriteria(criteria,entityName,entityId));
    }

    /**
     * GET  /documents/:id : get the "id" document.
     *
     * @param id the id of the documentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the documentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/documents/{id}")
    @Timed
    public ResponseEntity<DocumentDTO> getDocument(@PathVariable Long id) {
        log.debug("REST request to get Document : {}", id);
        Optional<DocumentDTO> documentDTO = documentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(documentDTO);
    }

    /**
     * DELETE  /documents/:id : delete the "id" document.
     *
     * @param id the id of the documentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/documents/{id}/{entityName}")
    @Timed
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id,
                                               @PathVariable String entityName) {
        log.debug("REST request to delete Document : {}", id);

        Optional<DocumentDTO> documentDTO = documentService.findOne(id);
        if(documentDTO.isPresent() && !documentDTO.get().getFileDoc().isEmpty()) {
                storageService.deleteFile(documentDTO.get().getFileDoc());
        }
        if (entityName.toLowerCase().equals("person")) {
            PersonCriteria criteria = new PersonCriteria();
            LongFilter filter = new LongFilter();
            filter.setEquals(id);
            criteria.setDocumentId(filter);
            PersonDTO personDTO = personQueryService.findByCriteria(criteria).get(0);

            Set<DocumentDTO> documents = personDTO.getDocuments();
            documents.remove(documentService.findOne(id).get());
            personDTO.setDocuments(documents);
            personService.save(personDTO, true);
        }
        if (entityName.toLowerCase().equals("job")) {

            JobCriteria criteria = new JobCriteria();
            LongFilter filter = new LongFilter();
            filter.setEquals(id);
            criteria.setDocumentId(filter);
            JobDTO jobDTO = jobQueryService.findByCriteria(criteria).get(0);

            Set<DocumentDTO> documents = jobDTO.getDocuments();
            documents.remove(documentService.findOne(id).get());
            jobDTO.setDocuments(documents);
            jobService.save(jobDTO);
        }
        if (entityName.toLowerCase().equals("teacher")) {

            TeacherCriteria criteria = new TeacherCriteria();
            LongFilter filter = new LongFilter();
            filter.setEquals(id);
            criteria.setDocumentId(filter);
            TeacherDTO teacherDTO = teacherQueryService.findByCriteria(criteria).get(0);

            Set<DocumentDTO> documents = teacherDTO.getDocuments();
            documents.remove(documentService.findOne(id).get());
            teacherDTO.setDocuments(documents);
            teacherService.save(teacherDTO);
        }
        if (entityName.toLowerCase().equals("educationalcenter")) {

            EducationalCenterCriteria criteria = new EducationalCenterCriteria();
            LongFilter filter = new LongFilter();
            filter.setEquals(id);
            criteria.setDocumentId(filter);
            EducationalCenterDTO educationalCenterDTO = educationalCenterQueryService.findByCriteria(criteria).get(0);

            Set<DocumentDTO> documents = educationalCenterDTO.getDocuments();
            documents.remove(documentService.findOne(id).get());
            educationalCenterDTO.setDocuments(documents);
            educationalCenterService.save(educationalCenterDTO);
        }
        if (entityName.toLowerCase().equals("educationalmodule")) {

            EducationalModuleCriteria criteria = new EducationalModuleCriteria();
            LongFilter filter = new LongFilter();
            filter.setEquals(id);
            criteria.setDocumentId(filter);
            EducationalModuleDTO educationalModuleDTO = educationalModuleQueryService.findByCriteria(criteria).get(0);

            Set<DocumentDTO> documents = educationalModuleDTO.getDocuments();
            documents.remove(documentService.findOne(id).get());
            educationalModuleDTO.setDocuments(documents);
            educationalModuleService.save(educationalModuleDTO);
        }
        if (entityName.toLowerCase().equals("designandplanning")) {

            DesignAndPlanningCriteria criteria = new DesignAndPlanningCriteria();
            LongFilter filter = new LongFilter();
            filter.setEquals(id);
            criteria.setDocumentId(filter);
            DesignAndPlanningDTO designAndPlanningDTO = designAndPlanningQueryService.findByCriteria(criteria).get(0);

            Set<DocumentDTO> documents = designAndPlanningDTO.getDocuments();
            documents.remove(documentService.findOne(id).get());
            designAndPlanningDTO.setDocuments(documents);
            designAndPlanningService.save(designAndPlanningDTO);
        }
        if (entityName.toLowerCase().equals("finalniazsanjireport")) {

            FinalNiazsanjiReportCriteria criteria = new FinalNiazsanjiReportCriteria();
            LongFilter filter = new LongFilter();
            filter.setEquals(id);
            criteria.setDocumentId(filter);
            FinalNiazsanjiReportDTO finalNiazsanjiReportDTO = finalNiazsanjiReportQueryService.findByCriteria(criteria).get(0);

            Set<DocumentDTO> documents = finalNiazsanjiReportDTO.getDocuments();
            documents.remove(documentService.findOne(id).get());
            finalNiazsanjiReportDTO.setDocuments(documents);
            finalNiazsanjiReportService.save(finalNiazsanjiReportDTO);
        }
        if (entityName.toLowerCase().equals("runphase")) {

            RunPhaseCriteria criteria = new RunPhaseCriteria();
            LongFilter filter = new LongFilter();
            filter.setEquals(id);
            criteria.setDocumentId(filter);
            RunPhaseDTO runPhaseDTO = runPhaseQueryService.findByCriteria(criteria).get(0);

            Set<DocumentDTO> documents = runPhaseDTO.getDocuments();
            documents.remove(documentService.findOne(id).get());
            runPhaseDTO.setDocuments(documents);
            runPhaseService.save(runPhaseDTO);
        }
        if (entityName.toLowerCase().equals("resource")) {
            ResourceCriteria criteria = new ResourceCriteria();
            LongFilter filter = new LongFilter();
            filter.setEquals(id);
            criteria.setDocumentId(filter);
            ResourceDTO resourceDTO = resourceQueryService.findByCriteria(criteria).get(0);

            Set<DocumentDTO> documents = resourceDTO.getDocuments();
            documents.remove(documentService.findOne(id).get());
            resourceDTO.setDocuments(documents);
            resourceService.save(resourceDTO);
        }
        if (entityName.toLowerCase().equals("instruction")) {
            InstructionCriteria criteria = new InstructionCriteria();
            LongFilter filter = new LongFilter();
            filter.setEquals(id);
            criteria.setDocumentId(filter);
            InstructionDTO instructionDTO = instructionQueryService.findByCriteria(criteria).get(0);

            Set<DocumentDTO> documents = instructionDTO.getDocuments();
            documents.remove(documentService.findOne(id).get());
            instructionDTO.setDocuments(documents);
            instructionService.save(instructionDTO);
        }
        if (entityName.toLowerCase().equals("requestorganizationniazsanji")) {
            RequestOrganizationNiazsanjiCriteria criteria = new RequestOrganizationNiazsanjiCriteria();
            LongFilter filter = new LongFilter();
            filter.setEquals(id);
            criteria.setDocumentId(filter);
            RequestOrganizationNiazsanjiDTO requestOrganizationNiazsanjiDTO = requestOrganizationNiazsanjiQueryService.findByCriteria(criteria).get(0);

            Set<DocumentDTO> documents = requestOrganizationNiazsanjiDTO.getDocuments();
            documents.remove(documentService.findOne(id).get());
            requestOrganizationNiazsanjiDTO.setDocuments(documents);
            requestOrganizationNiazsanjiService.save(requestOrganizationNiazsanjiDTO);
        }
        if (entityName.toLowerCase().equals("finalorganizationniazsanji")) {
            FinalOrganizationNiazsanjiCriteria criteria = new FinalOrganizationNiazsanjiCriteria();
            LongFilter filter = new LongFilter();
            filter.setEquals(id);
            criteria.setDocumentId(filter);
            FinalOrganizationNiazsanjiDTO finalOrganizationNiazsanjiDTO = finalOrganizationNiazsanjiQueryService.findByCriteria(criteria).get(0);

            Set<DocumentDTO> documents = finalOrganizationNiazsanjiDTO.getDocuments();
            documents.remove(documentService.findOne(id).get());
            finalOrganizationNiazsanjiDTO.setDocuments(documents);
            finalOrganizationNiazsanjiService.save(finalOrganizationNiazsanjiDTO);
        }
        if (entityName.toLowerCase().equals("announcement")) {
            AnnouncementCriteria criteria = new AnnouncementCriteria();
            LongFilter filter = new LongFilter();
            filter.setEquals(id);
            criteria.setDocumentId(filter);
            AnnouncementDTO announcementDTO = announcementQueryService.findByCriteria(criteria).get(0);

            Set<DocumentDTO> documents = announcementDTO.getDocuments();
            documents.remove(documentService.findOne(id).get());
            announcementDTO.setDocuments(documents);
            announcementService.save(announcementDTO);
        }
        if (entityName.toLowerCase().equals("requesteducationalmodule")) {
            RequestEducationalModuleCriteria criteria = new RequestEducationalModuleCriteria();
            LongFilter filter = new LongFilter();
            filter.setEquals(id);
            criteria.setDocumentId(filter);
            RequestEducationalModuleDTO requestEducationalModuleDTO = requestEducationalModuleQueryService.findByCriteria(criteria).get(0);

            Set<DocumentDTO> documents = requestEducationalModuleDTO.getDocuments();
            documents.remove(documentService.findOne(id).get());
            requestEducationalModuleDTO.setDocuments(documents);
            requestEducationalModuleService.save(requestEducationalModuleDTO);
        }
        if (entityName.toLowerCase().equals("usersrequest")) {
            UsersRequestCriteria criteria = new UsersRequestCriteria();
            LongFilter filter = new LongFilter();
            filter.setEquals(id);
            criteria.setDocumentId(filter);
            UsersRequestDTO usersRequestDTO = usersRequestQueryService.findByCriteria(criteria).get(0);

            Set<DocumentDTO> documents = usersRequestDTO.getDocuments();
            documents.remove(documentService.findOne(id).get());
            usersRequestDTO.setDocuments(documents);
            usersRequestService.save(usersRequestDTO);
        }
        if (entityName.toLowerCase().equals("requestniazsanjifardi")) {
            RequestNiazsanjiFardiCriteria criteria = new RequestNiazsanjiFardiCriteria();
            LongFilter filter = new LongFilter();
            filter.setEquals(id);
            criteria.setDocumentId(filter);
            RequestNiazsanjiFardiDTO requestNiazsanjiFardiDTO = requestNiazsanjiFardiQueryService.findByCriteria(criteria).get(0);

            Set<DocumentDTO> documents = requestNiazsanjiFardiDTO.getDocuments();
            documents.remove(documentService.findOne(id).get());
            requestNiazsanjiFardiDTO.setDocuments(documents);
            requestNiazsanjiFardiService.save(requestNiazsanjiFardiDTO);
        }
        if (entityName.toLowerCase().equals("niazsanjifardi")) {
            NiazsanjiFardiCriteria criteria = new NiazsanjiFardiCriteria();
            LongFilter filter = new LongFilter();
            filter.setEquals(id);
            criteria.setDocumentId(filter);
            NiazsanjiFardiDTO niazsanjiFardiDTO = niazsanjiFardiQueryService.findByCriteria(criteria).get(0);

            Set<DocumentDTO> documents = niazsanjiFardiDTO.getDocuments();
            documents.remove(documentService.findOne(id).get());
            niazsanjiFardiDTO.setDocuments(documents);
            niazsanjiFardiService.save(niazsanjiFardiDTO);
        }
        if (entityName.toLowerCase().equals("investtogrouptransaction")) {
            InvestToGroupTransactionCriteria criteria = new InvestToGroupTransactionCriteria();
            LongFilter filter = new LongFilter();
            filter.setEquals(id);
            criteria.setDocumentId(filter);
            InvestToGroupTransactionDTO investToGroupTransactionDTO =
                investToGroupTransactionQueryService.findByCriteria(criteria).get(0);

            Set<DocumentDTO> documents = investToGroupTransactionDTO.getDocuments();
            documents.remove(documentService.findOne(id).get());
            investToGroupTransactionDTO.setDocuments(documents);
            investToGroupTransactionService.save(investToGroupTransactionDTO);
        }
        if (entityName.toLowerCase().equals("mediaawarenessreport")) {
            MediaAwarenessReportCriteria criteria = new MediaAwarenessReportCriteria();
            LongFilter filter = new LongFilter();
            filter.setEquals(id);
            criteria.setDocumentId(filter);
            MediaAwarenessReportDTO mediaAwarenessReportDTO =
                mediaAwarenessReportQueryService.findByCriteria(criteria).get(0);

            Set<DocumentDTO> documents = mediaAwarenessReportDTO.getDocuments();
            documents.remove(documentService.findOne(id).get());
            mediaAwarenessReportDTO.setDocuments(documents);
            mediaAwarenessReportService.save(mediaAwarenessReportDTO);
        }
        if (entityName.toLowerCase().equals("prejobniazsanji")) {
            PreJobNiazsanjiCriteria criteria = new PreJobNiazsanjiCriteria();
            LongFilter filter = new LongFilter();
            filter.setEquals(id);
            criteria.setDocumentId(filter);
            PreJobNiazsanjiDTO preJobNiazsanjiDTO =
                preJobNiazsanjiQueryService.findByCriteria(criteria).get(0);

            Set<DocumentDTO> documents = preJobNiazsanjiDTO.getDocuments();
            documents.remove(documentService.findOne(id).get());
            preJobNiazsanjiDTO.setDocuments(documents);
            preJobNiazsanjiService.save(preJobNiazsanjiDTO);
        }
        documentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
