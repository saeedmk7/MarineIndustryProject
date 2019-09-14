package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.domain.enumeration.NiazSanjiSource;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.EducationalModuleService;
import com.marineindustryproj.service.EmploymentTypeService;
import com.marineindustryproj.service.FinalNiazsanjiReportPersonQueryService;
import com.marineindustryproj.service.FinalNiazsanjiReportPersonService;
import com.marineindustryproj.service.FinalNiazsanjiReportService;
import com.marineindustryproj.service.FinalOrganizationNiazsanjiQueryService;
import com.marineindustryproj.service.FinalOrganizationNiazsanjiService;
import com.marineindustryproj.service.JobService;
import com.marineindustryproj.service.NiazsanjiGroupQueryService;
import com.marineindustryproj.service.NiazsanjiGroupService;
import com.marineindustryproj.service.PersonQueryService;
import com.marineindustryproj.service.PersonService;
import com.marineindustryproj.service.dto.EducationalModuleDTO;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportPersonCriteria;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportPersonDTO;
import com.marineindustryproj.service.dto.FinalOrganizationNiazsanjiCriteria;
import com.marineindustryproj.service.dto.FinalOrganizationNiazsanjiDTO;
import com.marineindustryproj.service.dto.JobDTO;
import com.marineindustryproj.service.dto.NiazsanjiGroupCriteria;
import com.marineindustryproj.service.dto.NiazsanjiGroupDTO;
import com.marineindustryproj.service.dto.PersonCriteria;
import com.marineindustryproj.service.dto.PersonDTO;
import com.marineindustryproj.service.dto.ReportDTO;
import com.marineindustryproj.service.dto.customs.*;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportDTO;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportCriteria;
import com.marineindustryproj.service.FinalNiazsanjiReportQueryService;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.web.util.ResponseUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

/**
 * REST controller for managing FinalNiazsanjiReport.
 */
@RestController
@RequestMapping("/api")
public class FinalNiazsanjiReportResource {

    private final Logger log = LoggerFactory.getLogger(FinalNiazsanjiReportResource.class);

    private static final String ENTITY_NAME = "finalNiazsanjiReport";

    private final FinalNiazsanjiReportService finalNiazsanjiReportService;

    private final FinalNiazsanjiReportQueryService finalNiazsanjiReportQueryService;

    private final NiazsanjiGroupService niazsanjiGroupService;

    private final NiazsanjiGroupQueryService niazsanjiGroupQueryService;

    private final FinalOrganizationNiazsanjiService finalOrganizationNiazsanjiService;

    private final FinalOrganizationNiazsanjiQueryService finalOrganizationNiazsanjiQueryService;

    private final JobService jobService;

    private final PersonService personService;

    private final PersonQueryService personQueryService;

    private final EducationalModuleService educationalModuleService;

    private final EmploymentTypeService employmentTypeService;

    private final FinalNiazsanjiReportPersonService finalNiazsanjiReportPersonService;
    private final FinalNiazsanjiReportPersonQueryService finalNiazsanjiReportPersonQueryService;

    public FinalNiazsanjiReportResource(FinalNiazsanjiReportService finalNiazsanjiReportService,
                                        FinalNiazsanjiReportQueryService finalNiazsanjiReportQueryService,
                                        NiazsanjiGroupService niazsanjiGroupService,
                                        NiazsanjiGroupQueryService niazsanjiGroupQueryService,
                                        FinalOrganizationNiazsanjiService finalOrganizationNiazsanjiService,
                                        FinalOrganizationNiazsanjiQueryService finalOrganizationNiazsanjiQueryService,
                                        JobService jobService,
                                        PersonService personService,
                                        PersonQueryService personQueryService,
                                        EducationalModuleService educationalModuleService,
                                        EmploymentTypeService employmentTypeService,
                                        FinalNiazsanjiReportPersonService finalNiazsanjiReportPersonService,
                                        FinalNiazsanjiReportPersonQueryService finalNiazsanjiReportPersonQueryService) {
        this.finalNiazsanjiReportService = finalNiazsanjiReportService;
        this.finalNiazsanjiReportQueryService = finalNiazsanjiReportQueryService;
        this.niazsanjiGroupService = niazsanjiGroupService;
        this.niazsanjiGroupQueryService = niazsanjiGroupQueryService;

        this.finalOrganizationNiazsanjiService = finalOrganizationNiazsanjiService;
        this.finalOrganizationNiazsanjiQueryService = finalOrganizationNiazsanjiQueryService;
        this.jobService = jobService;
        this.personService = personService;
        this.personQueryService = personQueryService;
        this.educationalModuleService = educationalModuleService;
        this.employmentTypeService = employmentTypeService;
        this.finalNiazsanjiReportPersonService = finalNiazsanjiReportPersonService;
        this.finalNiazsanjiReportPersonQueryService = finalNiazsanjiReportPersonQueryService;
    }
    @GetMapping("/final-niazsanji-reports/PreReport/{educationalModuleId}")
    @Timed
    public ResponseEntity<ReportDTO> PreReport(@PathVariable Long educationalModuleId) throws JRException, IOException {
        log.debug("REST request to get FinalNiazsanjiReport/PreReport/ : {}", educationalModuleId);

        if(educationalModuleId == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);


        Optional<EducationalModuleDTO> educationalModuleDTO = educationalModuleService.findOne(educationalModuleId);

        if(!educationalModuleDTO.isPresent())
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        EducationalModuleDTO educationalModule = educationalModuleDTO.get();

        InputStream file = new ClassPathResource("reports/finalNiazSanji.jrxml").getInputStream();

        JasperReport jasperReport = JasperCompileManager
            .compileReport(file);

        // Parameters for report
        List<Map<String, Object>> parameters = new ArrayList<>();

        NiazsanjiGroupCriteria niazsanjiGroupCriteria = new NiazsanjiGroupCriteria();

        LongFilter niazsanjiGroupFilter = new LongFilter();
        niazsanjiGroupFilter.setEquals(educationalModuleId);
        niazsanjiGroupCriteria.setEducationalModuleId(niazsanjiGroupFilter);
        BooleanFilter niazsanjiGroupBooleanFilter = new BooleanFilter();
        niazsanjiGroupBooleanFilter.setEquals(false);
        niazsanjiGroupCriteria.setArchived(niazsanjiGroupBooleanFilter);
        IntegerFilter niazsanjiGroupStatusFilter = new IntegerFilter();
        niazsanjiGroupStatusFilter.setEquals(0);
        niazsanjiGroupCriteria.setStatus(niazsanjiGroupStatusFilter);
        List<NiazsanjiGroupDTO> niazsanjiGroupDTOS = niazsanjiGroupQueryService.findByCriteria(niazsanjiGroupCriteria);
        int rowNum = 0;
        if(!niazsanjiGroupDTOS.isEmpty()) {
            for (NiazsanjiGroupDTO niazsanjiGroupDTO : niazsanjiGroupDTOS){


                List<PersonDTO> personDTOList = new ArrayList<PersonDTO>();

                for(JobDTO jobDTO : niazsanjiGroupDTO.getJobs()){
                    PersonCriteria personCriteria = new PersonCriteria();

                    LongFilter personFilter = new LongFilter();
                    personFilter.setEquals(jobDTO.getId());
                    personCriteria.setJobId(personFilter);
                    BooleanFilter personBooleanFilter = new BooleanFilter();
                    personBooleanFilter.setEquals(false);
                    personCriteria.setArchived(personBooleanFilter);
                    IntegerFilter personStatusFilter = new IntegerFilter();
                    personStatusFilter.setEquals(0);
                    personCriteria.setStatus(personStatusFilter);
                    List<PersonDTO> personDTOS = personQueryService.findByCriteria(personCriteria);

                    personDTOList.addAll(personDTOS);
                }
                for (PersonDTO item : personDTOList) {
                    rowNum++;
                    Map<String, Object> param = new HashMap<>();
                    param.put("educationalModuleTitle",
                              educationalModule.getTitle());
                    param.put("educationalModuleId",
                              educationalModule.getId());
                    param.put("educationalModuleLevel",
                              educationalModule.getSkillableLevelOfSkillTitle());
                    param.put("educationalModuleHour",
                              educationalModule.getLearningTimePractical() + educationalModule.getLearningTimeTheorical());
                    param.put("personFullName",
                              item.getName() + " " + item.getFamily());
                    addJobToParam(item,
                                  param);
                    param.put("priceCost",
                              niazsanjiGroupDTO.getPriceCost());
                    param.put("niazSanjiSource",
                              convertNiazSanjiSourceEnum(NiazSanjiSource.GROUP));
                    if (item.getEmploymentTypeId() != null) {

                        /*Optional<EmploymentTypeDTO> employmentType = employmentTypeService.findOne();
                        String employmentTypeTitle = " ";
                        if (employmentType.isPresent())
                            employmentTypeTitle = employmentType.get().getTitle();*/
                        param.put("employmentTypeTitle",
                                  item.getEmploymentTypeTitle());
                    }
                    else {
                        param.put("employmentTypeTitle",
                                 " ");
                    }
                    param.put("RowNumber",
                              rowNum);

                    parameters.add(param);
                }
            }


        }

        FinalOrganizationNiazsanjiCriteria finalOrganizationNiazsanjiCriteria = new FinalOrganizationNiazsanjiCriteria();

        LongFilter finalOrganizationNiazsanjiFilter = new LongFilter();
        finalOrganizationNiazsanjiFilter.setEquals(educationalModuleId);
        finalOrganizationNiazsanjiCriteria.setEducationalModuleId(finalOrganizationNiazsanjiFilter);
        BooleanFilter finalOrganizationNiazsanjiBooleanFilter = new BooleanFilter();
        finalOrganizationNiazsanjiBooleanFilter.setEquals(false);
        finalOrganizationNiazsanjiCriteria.setArchived(finalOrganizationNiazsanjiBooleanFilter);
        IntegerFilter finalOrganizationNiazsanjiStatusFilter = new IntegerFilter();
        finalOrganizationNiazsanjiStatusFilter.setEquals(0);
        finalOrganizationNiazsanjiCriteria.setStatus(finalOrganizationNiazsanjiStatusFilter);

        List<FinalOrganizationNiazsanjiDTO> finalOrganizationNiazsanjiDTOS = finalOrganizationNiazsanjiQueryService.findByCriteria(finalOrganizationNiazsanjiCriteria);

        if(!finalOrganizationNiazsanjiDTOS.isEmpty()) {
            for(FinalOrganizationNiazsanjiDTO finalOrganizationNiazsanjiDTO :finalOrganizationNiazsanjiDTOS)
            {

                for(PersonDTO personDTO : finalOrganizationNiazsanjiDTO.getPeople()){
                    rowNum++;
                    Map<String, Object> param = new HashMap<>();
                    param.put("educationalModuleTitle",
                              educationalModule.getTitle());
                    param.put("educationalModuleId",
                              educationalModule.getId());
                    param.put("educationalModuleLevel",
                              educationalModule.getSkillableLevelOfSkillTitle());
                    param.put("educationalModuleHour",
                              educationalModule.getLearningTimePractical() + educationalModule.getLearningTimeTheorical());
                    param.put("personFullName",
                              personDTO.getName() + " " + personDTO.getFamily());
                    addJobToParam(personDTO,
                                  param);
                    param.put("priceCost",
                              finalOrganizationNiazsanjiDTO.getPriceCost());
                    param.put("niazSanjiSource",
                              convertNiazSanjiSourceEnum(NiazSanjiSource.ORGANIZATION));
                    if (personDTO.getEmploymentTypeId() != null) {

                        /*Optional<EmploymentTypeDTO> employmentType = employmentTypeService.findOne();
                        String employmentTypeTitle = " ";
                        if (employmentType.isPresent())
                            employmentTypeTitle = employmentType.get().getTitle();*/
                        param.put("employmentTypeTitle",
                                  personDTO.getEmploymentTypeTitle());
                    }
                    else {
                        param.put("employmentTypeTitle",
                                  " ");
                    }

                    param.put("RowNumber",
                              rowNum);
                    parameters.add(param);
                }
            }
        }
        //gera relatorio com as classes do jasper
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(parameters);

        Map<String,Object> params = new HashMap<>();
        params.put("datasource",parameters);



        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,beanCollectionDataSource);

        ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
        JRXlsExporter exporter = new JRXlsExporter();
        ExporterInput exporterInput = new SimpleExporterInput(jasperPrint);
        exporter.setExporterInput(exporterInput);

        // ExporterOutput
        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(dadosByte);
        // Output
        exporter.setExporterOutput(exporterOutput);

        exporter.exportReport();
        byte[] bytes = dadosByte.toByteArray();

        ResponseEntity<ReportDTO> reportDTO = getReportDTOResponseEntity(bytes);
        if (reportDTO != null) return reportDTO;
        String error = "خطا در تولید گزارش";
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    private String convertNiazSanjiSourceEnum(NiazSanjiSource niazSanjiSource){
        if(niazSanjiSource.equals(NiazSanjiSource.FARDI))
            return "فردی";
        if(niazSanjiSource.equals(NiazSanjiSource.GROUP))
            return "گروهی";
        if(niazSanjiSource.equals(NiazSanjiSource.ORGANIZATION))
            return "سازمانی";
        return "";
    }
    private void addJobToParam(PersonDTO personDTO,
                               Map<String, Object> param) {
        if(personDTO.getJobId() != null) {
            Optional<JobDTO> jobDTO = jobService.findOne(personDTO.getJobId());
            if (jobDTO.isPresent()) {
                param.put("jobTitle",
                          jobDTO.get().getTitle());
            } else {
                param.put("jobTitle",
                          " ");
            }
        }
        else {
            param.put("jobTitle",
                      " ");
        }
    }

    @GetMapping("/final-niazsanji-reports/PostReport/{finalNiazsanjiReportId}")
    @Timed
    public ResponseEntity<ReportDTO> PostReport(@PathVariable Long finalNiazsanjiReportId) throws JRException, IOException {
        log.debug("REST request to get FinalNiazsanjiReport/PostReport/ : {}", finalNiazsanjiReportId);

        if(finalNiazsanjiReportId == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);


        Optional<FinalNiazsanjiReportDTO> finalNiazsanjiReportDTO = finalNiazsanjiReportService.findOne(finalNiazsanjiReportId);

        if(!finalNiazsanjiReportDTO.isPresent())
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        FinalNiazsanjiReportDTO finalNiazsanjiReport = finalNiazsanjiReportDTO.get();
        InputStream file = new ClassPathResource("reports/finalNiazSanji.jrxml").getInputStream();

        JasperReport jasperReport = JasperCompileManager
            .compileReport(file);

        // Parameters for report
        List<Map<String, Object>> parameters = new ArrayList<>();

        FinalNiazsanjiReportPersonCriteria finalNiazsanjiReportPersonCriteria = new FinalNiazsanjiReportPersonCriteria();

        LongFilter finalNiazsanjiReportPersonFilter = new LongFilter();
        finalNiazsanjiReportPersonFilter.setEquals(finalNiazsanjiReportId);
        finalNiazsanjiReportPersonCriteria.setFinalNiazsanjiReportId(finalNiazsanjiReportPersonFilter);

        BooleanFilter finalNiazsanjiReportPersonBooleanFilter = new BooleanFilter();
        finalNiazsanjiReportPersonBooleanFilter.setEquals(false);
        finalNiazsanjiReportPersonCriteria.setArchived(finalNiazsanjiReportPersonBooleanFilter);

        IntegerFilter finalNiazsanjiReportPersonStatusFilter = new IntegerFilter();
        finalNiazsanjiReportPersonStatusFilter.setEquals(0);
        finalNiazsanjiReportPersonCriteria.setStatus(finalNiazsanjiReportPersonStatusFilter);

        List<FinalNiazsanjiReportPersonDTO> finalNiazsanjiReportPersonDTOS = finalNiazsanjiReportPersonQueryService.findByCriteria(finalNiazsanjiReportPersonCriteria);


        if(!finalNiazsanjiReportPersonDTOS.isEmpty()) {
            EducationalModuleDTO educationalModuleDTO = educationalModuleService.findOne(finalNiazsanjiReport.getEducationalModuleId()).get();
            int rowNum = 0;
            for (FinalNiazsanjiReportPersonDTO finalNiazsanjiReportPersonDTO : finalNiazsanjiReportPersonDTOS){
                rowNum++;
                PersonDTO personDTO = personService.findOne(finalNiazsanjiReportPersonDTO.getPersonId()).get();
                    Map<String, Object> param = new HashMap<>();

                    param.put("educationalModuleTitle",
                              educationalModuleDTO.getTitle());
                    param.put("educationalModuleId",
                              educationalModuleDTO.getId());
                    param.put("educationalModuleLevel",
                              educationalModuleDTO.getSkillableLevelOfSkillTitle());
                    param.put("educationalModuleHour",
                              educationalModuleDTO.getLearningTimePractical() + educationalModuleDTO.getLearningTimeTheorical());

                    param.put("personFullName",
                              personDTO.getName() + " " + personDTO.getFamily());
                addJobToParam(personDTO,
                              param);
                param.put("priceCost",
                              finalNiazsanjiReportPersonDTO.getPriceCost());
                    param.put("niazSanjiSource",
                              convertNiazSanjiSourceEnum(finalNiazsanjiReportPersonDTO.getNiazSanjiSource()));
                    if (personDTO.getEmploymentTypeId() != null) {

                        /*Optional<EmploymentTypeDTO> employmentType = employmentTypeService.findOne();
                        String employmentTypeTitle = " ";
                        if (employmentType.isPresent())
                            employmentTypeTitle = employmentType.get().getTitle();*/
                        param.put("employmentTypeTitle",
                                  personDTO.getEmploymentTypeTitle());
                    }
                    else {
                        param.put("employmentTypeTitle",
                                  " ");
                    }
                param.put("RowNumber",
                          rowNum);
                    parameters.add(param);

            }


        }
        //gera relatorio com as classes do jasper
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(parameters);

        Map<String,Object> params = new HashMap<>();
        params.put("datasource",parameters);


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,beanCollectionDataSource);

        ByteArrayOutputStream dadosByte = new ByteArrayOutputStream();
        JRXlsExporter exporter = new JRXlsExporter();
        ExporterInput exporterInput = new SimpleExporterInput(jasperPrint);
        exporter.setExporterInput(exporterInput);

        // ExporterOutput
        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(dadosByte);
        // Output
        exporter.setExporterOutput(exporterOutput);

        exporter.exportReport();
        byte[] bytes = dadosByte.toByteArray();

        ResponseEntity<ReportDTO> reportDTO = getReportDTOResponseEntity(bytes);
        if (reportDTO != null)
            return reportDTO;
        String error = "خطا در تولید گزارش";
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ReportDTO> getReportDTOResponseEntity(byte[] bytes) {
        if (bytes != null && bytes.length > 0) {
            ReportDTO reportDTO = new ReportDTO();
            //reportDTO.setFileDoc(bytes);
            reportDTO.setFileDoc(Arrays.copyOf(bytes, bytes.length ) );
            reportDTO.setFileDocContentType("application/vnd.ms-excel");
            return ResponseEntity.ok().headers(null).body(reportDTO);
        }
        return null;
    }

    /**
     * POST  /final-niazsanji-reports : Create a new finalNiazsanjiReport.
     *
     * @param finalNiazsanjiReportDTO the finalNiazsanjiReportDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new finalNiazsanjiReportDTO, or with status 400 (Bad Request) if the finalNiazsanjiReport has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/final-niazsanji-reports")
    @Timed
    public ResponseEntity<FinalNiazsanjiReportDTO> createFinalNiazsanjiReport(@Valid @RequestBody FinalNiazsanjiReportDTO finalNiazsanjiReportDTO) throws URISyntaxException,Exception {
        log.debug("REST request to save FinalNiazsanjiReport : {}", finalNiazsanjiReportDTO);
        if (finalNiazsanjiReportDTO.getId() != null) {
            throw new BadRequestAlertException("A new finalNiazsanjiReport cannot already have an ID", ENTITY_NAME, "idexists");
        }

        finalNiazsanjiReportDTO.setCreateDate(ZonedDateTime.now());
        finalNiazsanjiReportDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalNiazsanjiReportDTO.setModifyDate(ZonedDateTime.now());
        finalNiazsanjiReportDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalNiazsanjiReportDTO.setNiazSanjiSource(NiazSanjiSource.FARDI);

        FinalNiazsanjiReportDTO result = finalNiazsanjiReportService.saveAndComplete(finalNiazsanjiReportDTO);
        return ResponseEntity.created(new URI("/api/final-niazsanji-reports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /final-niazsanji-reports : Updates an existing finalNiazsanjiReport.
     *
     * @param finalNiazsanjiReportDTO the finalNiazsanjiReportDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated finalNiazsanjiReportDTO,
     * or with status 400 (Bad Request) if the finalNiazsanjiReportDTO is not valid,
     * or with status 500 (Internal Server Error) if the finalNiazsanjiReportDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/final-niazsanji-reports")
    @Timed
    public ResponseEntity<FinalNiazsanjiReportDTO> updateFinalNiazsanjiReport(@Valid @RequestBody FinalNiazsanjiReportDTO finalNiazsanjiReportDTO) throws URISyntaxException,Exception {
        log.debug("REST request to update FinalNiazsanjiReport : {}", finalNiazsanjiReportDTO);
        if (finalNiazsanjiReportDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        FinalNiazsanjiReportDTO finalNiazsanjiReport = finalNiazsanjiReportService.findOne(finalNiazsanjiReportDTO.getId()).get();

        finalNiazsanjiReportDTO.setCreateUserLogin(finalNiazsanjiReport.getCreateUserLogin());
        finalNiazsanjiReportDTO.setCreateDate(finalNiazsanjiReport.getCreateDate());
        finalNiazsanjiReportDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalNiazsanjiReportDTO.setModifyDate(ZonedDateTime.now());

        FinalNiazsanjiReportDTO result = finalNiazsanjiReportService.save(finalNiazsanjiReportDTO);
        return ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, finalNiazsanjiReportDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /final-niazsanji-reports : get all the finalNiazsanjiReports.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of finalNiazsanjiReports in body
     */
    @GetMapping("/final-niazsanji-reports")
    @Timed
    public ResponseEntity<List<FinalNiazsanjiReportDTO>> getAllFinalNiazsanjiReports(FinalNiazsanjiReportCriteria criteria, Pageable pageable) {
        log.debug("REST request to get FinalNiazsanjiReports by criteria: {}", criteria);
        Page<FinalNiazsanjiReportDTO> page = finalNiazsanjiReportQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/final-niazsanji-reports");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /final-niazsanji-reports/count : count all the finalNiazsanjiReports.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/final-niazsanji-reports/count")
    @Timed
    public ResponseEntity<Long> countFinalNiazsanjiReports (FinalNiazsanjiReportCriteria criteria) {
        log.debug("REST request to count FinalNiazsanjiReports by criteria: {}", criteria);
        return ok().body(finalNiazsanjiReportQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /final-niazsanji-reports/:id : get the "id" finalNiazsanjiReport.
     *
     * @param id the id of the finalNiazsanjiReportDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the finalNiazsanjiReportDTO, or with status 404 (Not Found)
     */
    @GetMapping("/final-niazsanji-reports/{id}")
    @Timed
    public ResponseEntity<FinalNiazsanjiReportDTO> getFinalNiazsanjiReport(@PathVariable Long id) {
        log.debug("REST request to get FinalNiazsanjiReport : {}", id);
        Optional<FinalNiazsanjiReportDTO> finalNiazsanjiReportDTO = finalNiazsanjiReportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(finalNiazsanjiReportDTO);
    }

    /**
     * GET  /final-niazsanji-reports/:id : get the "id" finalNiazsanjiReport.
     *
     * @param niazsanjiYear the id of the finalNiazsanjiReportDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the finalNiazsanjiReportDTO, or with status 404 (Not Found)
     */
    @GetMapping("/final-niazsanji-reports/getChartData/{niazsanjiYear}")
    @Timed
    public ResponseEntity<List<ChartResult>> getFinalNiazsanjiReportChartData(@PathVariable Integer niazsanjiYear) {
        log.debug("REST request to get FinalNiazsanjiReport : {}", niazsanjiYear);

        List<ChartResult> chartResults = finalNiazsanjiReportService.getChartResult(niazsanjiYear);
        return ResponseEntity.ok().body(chartResults);
    }

    /**
     * GET  /final-niazsanji-reports/:id : get the "id" finalNiazsanjiReport.
     *
     * @param personId the id of the finalNiazsanjiReportDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the finalNiazsanjiReportDTO, or with status 404 (Not Found)
     */
    @GetMapping("/final-niazsanji-reports/getHomePageNiazsanjiReport/{personId}")
    @Timed
    public ResponseEntity<HomePageNiazsanjiReport> getHomePageNiazsanjiReport(@PathVariable Long personId) {
        log.debug("REST request to get HomePageNiazsanjiReport : {}", personId);

        HomePageNiazsanjiReport homePageNiazsanjiReports = finalNiazsanjiReportService.getHomePageNiazsanjiReport(personId);
        return ResponseEntity.ok().body(homePageNiazsanjiReports);
    }

    @GetMapping("/final-niazsanji-reports/getHomePagePersonHourChart/{personId}")
    @Timed
    public ResponseEntity<HomePagePersonHourChart> getHomePagePersonHourChart(@PathVariable Long personId) {
        log.debug("REST request to get HPersonHourChart : {}", personId);

        HomePagePersonHourChart homePagePersonHourChart = finalNiazsanjiReportService.getHomePagePersonHourChart(personId);
        return ResponseEntity.ok().body(homePagePersonHourChart);
    }

    @GetMapping("/final-niazsanji-reports/getHomePageReport/{niazsanjiYear}/{reportType}")
    @Timed
    public ResponseEntity<HomePageReport> getHomePageReport(@PathVariable Integer niazsanjiYear, @PathVariable Integer reportType) {
        log.debug("REST request to get homePageReport");
        HomePageReportType homePageReportType = HomePageReportType.PersonHour;
        if(reportType == 2)
            homePageReportType = HomePageReportType.Price;
        HomePageReport homePageReport = finalNiazsanjiReportService.getHomePageReport(niazsanjiYear, homePageReportType);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(homePageReport);
    }

    @GetMapping("/final-niazsanji-reports/getHomePagePersonEducationalModule/{personId}")
    @Timed
    public ResponseEntity<List<HomePagePersonEducationalModule>> getHomePagePersonEducationalModule(@PathVariable Long personId) {
        log.debug("REST request to get HPersonHourChart : {}", personId);

        List<HomePagePersonEducationalModule> homePagePersonEducationalModules = finalNiazsanjiReportService.getHomePagePersonEducationalModules(personId);
        return ResponseEntity.ok().body(homePagePersonEducationalModules);
    }

    @GetMapping("/final-niazsanji-reports/getPlanningAndRunMonthReport/{niazsanjiYear}/{reportType}/{rootOrgId}")
    @Timed
    public ResponseEntity<List<PlanningAndRunMonthReport>> getPlanningAndRunMonthReport(@PathVariable Integer niazsanjiYear, @PathVariable Integer reportType, @PathVariable Long rootOrgId) {
        log.debug("REST request to get PlanningAndRunMonthReport : {}", niazsanjiYear);

        List<PlanningAndRunMonthReport> planningAndRunMonthReports = finalNiazsanjiReportService.getPlanningAndRunMonthReport(niazsanjiYear, reportType, rootOrgId);
        return ResponseEntity.ok().body(planningAndRunMonthReports);
    }

    /**
     * DELETE  /final-niazsanji-reports/:id : delete the "id" finalNiazsanjiReport.
     *
     * @param id the id of the finalNiazsanjiReportDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/final-niazsanji-reports/{id}")
    @Timed
    public ResponseEntity<Void> deleteFinalNiazsanjiReport(@PathVariable Long id) {
        log.debug("REST request to delete FinalNiazsanjiReport : {}", id);
        finalNiazsanjiReportService.delete(id);
        return ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
