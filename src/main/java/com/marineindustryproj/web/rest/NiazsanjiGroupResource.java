package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.domain.enumeration.NiazSanjiSource;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.JobQueryService;
import com.marineindustryproj.service.JobService;
import com.marineindustryproj.service.NiazsanjiGroupService;
import com.marineindustryproj.service.PersonQueryService;
import com.marineindustryproj.service.PersonService;
import com.marineindustryproj.service.dto.EducationalModuleCriteria;
import com.marineindustryproj.service.dto.EducationalModuleDTO;
import com.marineindustryproj.service.dto.JobDTO;
import com.marineindustryproj.service.dto.PersonCriteria;
import com.marineindustryproj.service.dto.PersonDTO;
import com.marineindustryproj.service.dto.ReportDTO;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.NiazsanjiGroupDTO;
import com.marineindustryproj.service.dto.NiazsanjiGroupCriteria;
import com.marineindustryproj.service.NiazsanjiGroupQueryService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.File;
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

/**
 * REST controller for managing NiazsanjiGroup.
 */
@RestController
@RequestMapping("/api")
public class NiazsanjiGroupResource {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiGroupResource.class);

    private static final String ENTITY_NAME = "niazsanjiGroup";

    private final NiazsanjiGroupService niazsanjiGroupService;

    private final NiazsanjiGroupQueryService niazsanjiGroupQueryService;

    private final PersonService personService;
    private final PersonQueryService personQueryService;

    private final JobService jobService;
    private final JobQueryService jobQueryService;

    public NiazsanjiGroupResource(NiazsanjiGroupService niazsanjiGroupService,
                                  NiazsanjiGroupQueryService niazsanjiGroupQueryService,
                                  PersonService personService,
                                  PersonQueryService personQueryService,
                                  JobService jobService,
                                  JobQueryService jobQueryService) {
        this.niazsanjiGroupService = niazsanjiGroupService;
        this.niazsanjiGroupQueryService = niazsanjiGroupQueryService;
        this.personService = personService;
        this.personQueryService = personQueryService;
        this.jobService = jobService;
        this.jobQueryService = jobQueryService;
    }

    @GetMapping("/niazsanji-groups/Report/{id}")
    @Timed
    public ResponseEntity<ReportDTO> Report(@PathVariable Long id) throws JRException, IOException {
        log.debug("REST request to get niazsanji-groups/Report/ : {}", id);

        if(id == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);


        Optional<NiazsanjiGroupDTO> niazsanjiGroupDTO = niazsanjiGroupService.findOne(id);

        if(!niazsanjiGroupDTO.isPresent())
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        NiazsanjiGroupDTO niazsanjiGroup = niazsanjiGroupDTO.get();

        InputStream file = new ClassPathResource("reports/finalNiazSanji.jrxml").getInputStream();

        JasperReport jasperReport = JasperCompileManager
            .compileReport(file);

        // Parameters for report
        List<Map<String, Object>> parameters = new ArrayList<>();

        EducationalModuleCriteria educationalModuleCriteria = new EducationalModuleCriteria();

        int rowNum = 0;

        List<PersonDTO> personDTOList = new ArrayList<PersonDTO>();
        if(!niazsanjiGroup.getJobs().isEmpty()) {
            for (JobDTO jobDTO : niazsanjiGroup.getJobs()) {
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
        }
        if(!personDTOList.isEmpty()) {
            if (!niazsanjiGroup.getEducationalModules().isEmpty()) {
                for (EducationalModuleDTO educationalModuleDTO : niazsanjiGroup.getEducationalModules()) {
                    for (PersonDTO item : personDTOList) {
                        rowNum++;
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
                                  item.getName() + " " + item.getFamily());
                        addJobToParam(item,
                                      param);
                        param.put("priceCost",
                                  niazsanjiGroup.getPriceCost());
                        param.put("niazSanjiSource",
                                  convertNiazSanjiSourceEnum(NiazSanjiSource.GROUP));
                        if (item.getEmploymentTypeId() != null) {
                            param.put("employmentTypeTitle",
                                      item.getEmploymentTypeTitle());
                        } else {
                            param.put("employmentTypeTitle",
                                      " ");
                        }
                        param.put("RowNumber",
                                  rowNum);

                        parameters.add(param);
                    }
                }
            }
        }
        //gera relatorio com as classes do jasper
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(parameters);

        Map<String,Object> params = new HashMap<>();
        params.put("datasource",parameters);



        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, beanCollectionDataSource);

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
    /**
     * POST  /niazsanji-groups : Create a new niazsanjiGroup.
     *
     * @param niazsanjiGroupDTO the niazsanjiGroupDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new niazsanjiGroupDTO, or with status 400 (Bad Request) if the niazsanjiGroup has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/niazsanji-groups")
    @Timed
    public ResponseEntity<NiazsanjiGroupDTO> createNiazsanjiGroup(@Valid @RequestBody NiazsanjiGroupDTO niazsanjiGroupDTO) throws URISyntaxException {
        log.debug("REST request to save NiazsanjiGroup : {}", niazsanjiGroupDTO);
        if (niazsanjiGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new niazsanjiGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }

        niazsanjiGroupDTO.setCreateDate(ZonedDateTime.now());
        niazsanjiGroupDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        NiazsanjiGroupDTO result = niazsanjiGroupService.save(niazsanjiGroupDTO);
        return ResponseEntity.created(new URI("/api/niazsanji-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /niazsanji-groups : Updates an existing niazsanjiGroup.
     *
     * @param niazsanjiGroupDTO the niazsanjiGroupDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated niazsanjiGroupDTO,
     * or with status 400 (Bad Request) if the niazsanjiGroupDTO is not valid,
     * or with status 500 (Internal Server Error) if the niazsanjiGroupDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/niazsanji-groups")
    @Timed
    public ResponseEntity<NiazsanjiGroupDTO> updateNiazsanjiGroup(@Valid @RequestBody NiazsanjiGroupDTO niazsanjiGroupDTO) throws URISyntaxException {
        log.debug("REST request to update NiazsanjiGroup : {}", niazsanjiGroupDTO);
        if (niazsanjiGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        NiazsanjiGroupDTO niazsanjiGroup = niazsanjiGroupService.findOne(niazsanjiGroupDTO.getId()).get();

        niazsanjiGroupDTO.setCreateUserLogin(niazsanjiGroup.getCreateUserLogin());
        niazsanjiGroupDTO.setCreateDate(niazsanjiGroup.getCreateDate());
        niazsanjiGroupDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        niazsanjiGroupDTO.setModifyDate(ZonedDateTime.now());

        NiazsanjiGroupDTO result = niazsanjiGroupService.save(niazsanjiGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, niazsanjiGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /niazsanji-groups : get all the niazsanjiGroups.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of niazsanjiGroups in body
     */
    @GetMapping("/niazsanji-groups")
    @Timed
    public ResponseEntity<List<NiazsanjiGroupDTO>> getAllNiazsanjiGroups(NiazsanjiGroupCriteria criteria, Pageable pageable) {
        log.debug("REST request to get NiazsanjiGroups by criteria: {}", criteria);
        Page<NiazsanjiGroupDTO> page = niazsanjiGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/niazsanji-groups");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /niazsanji-groups/count : count all the niazsanjiGroups.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/niazsanji-groups/count")
    @Timed
    public ResponseEntity<Long> countNiazsanjiGroups (NiazsanjiGroupCriteria criteria) {
        log.debug("REST request to count NiazsanjiGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(niazsanjiGroupQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /niazsanji-groups/:id : get the "id" niazsanjiGroup.
     *
     * @param id the id of the niazsanjiGroupDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the niazsanjiGroupDTO, or with status 404 (Not Found)
     */
    @GetMapping("/niazsanji-groups/{id}")
    @Timed
    public ResponseEntity<NiazsanjiGroupDTO> getNiazsanjiGroup(@PathVariable Long id) {
        log.debug("REST request to get NiazsanjiGroup : {}", id);
        Optional<NiazsanjiGroupDTO> niazsanjiGroupDTO = niazsanjiGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(niazsanjiGroupDTO);
    }

    /**
     * DELETE  /niazsanji-groups/:id : delete the "id" niazsanjiGroup.
     *
     * @param id the id of the niazsanjiGroupDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/niazsanji-groups/{id}")
    @Timed
    public ResponseEntity<Void> deleteNiazsanjiGroup(@PathVariable Long id) {
        log.debug("REST request to delete NiazsanjiGroup : {}", id);
        niazsanjiGroupService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
