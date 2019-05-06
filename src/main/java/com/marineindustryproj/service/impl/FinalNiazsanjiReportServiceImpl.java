package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.FinalNiazsanjiReportPerson;
import com.marineindustryproj.domain.OrganizationChart;
import com.marineindustryproj.domain.Person;
import com.marineindustryproj.domain.enumeration.NiazSanjiSource;
import com.marineindustryproj.domain.enumeration.RequestStatus;
import com.marineindustryproj.repository.OrganizationChartRepository;
import com.marineindustryproj.repository.PersonRepository;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.EducationalModuleService;
import com.marineindustryproj.service.EmploymentTypeService;
import com.marineindustryproj.service.FinalNiazsanjiReportPersonQueryService;
import com.marineindustryproj.service.FinalNiazsanjiReportPersonService;
import com.marineindustryproj.service.FinalNiazsanjiReportService;
import com.marineindustryproj.domain.FinalNiazsanjiReport;
import com.marineindustryproj.repository.FinalNiazsanjiReportRepository;
import com.marineindustryproj.service.FinalOrganizationNiazsanjiQueryService;
import com.marineindustryproj.service.FinalOrganizationNiazsanjiService;
import com.marineindustryproj.service.JobService;
import com.marineindustryproj.service.NiazsanjiGroupQueryService;
import com.marineindustryproj.service.NiazsanjiGroupService;
import com.marineindustryproj.service.OrganizationChartQueryService;
import com.marineindustryproj.service.PersonQueryService;
import com.marineindustryproj.service.PersonService;
import com.marineindustryproj.service.RequestNiazsanjiFardiQueryService;
import com.marineindustryproj.service.RequestOrganizationNiazsanjiQueryService;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportDTO;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportPersonCriteria;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportPersonDTO;
import com.marineindustryproj.service.dto.FinalOrganizationNiazsanjiCriteria;
import com.marineindustryproj.service.dto.FinalOrganizationNiazsanjiDTO;
import com.marineindustryproj.service.dto.JobDTO;
import com.marineindustryproj.service.dto.NiazsanjiGroupCriteria;
import com.marineindustryproj.service.dto.NiazsanjiGroupDTO;
import com.marineindustryproj.service.dto.PersonCriteria;
import com.marineindustryproj.service.dto.PersonDTO;
import com.marineindustryproj.service.dto.RequestNiazsanjiFardiCriteria;
import com.marineindustryproj.service.dto.RequestOrganizationNiazsanjiCriteria;
import com.marineindustryproj.service.dto.customs.ChartResult;
import com.marineindustryproj.service.dto.customs.FinalNiazsanjiReportCustomDTO;
import com.marineindustryproj.service.dto.customs.HomePageNiazsanjiReport;
import com.marineindustryproj.service.mapper.FinalNiazsanjiReportMapper;
import com.marineindustryproj.service.mapper.OrganizationChartMapper;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing FinalNiazsanjiReport.
 */
@Service
@Transactional
public class FinalNiazsanjiReportServiceImpl implements FinalNiazsanjiReportService {

    private final Logger log = LoggerFactory.getLogger(FinalNiazsanjiReportServiceImpl.class);

    private final FinalNiazsanjiReportRepository finalNiazsanjiReportRepository;

    private final FinalNiazsanjiReportMapper finalNiazsanjiReportMapper;

    private final NiazsanjiGroupService niazsanjiGroupService;

    private final NiazsanjiGroupQueryService niazsanjiGroupQueryService;

    private final FinalOrganizationNiazsanjiService finalOrganizationNiazsanjiService;

    private final FinalOrganizationNiazsanjiQueryService finalOrganizationNiazsanjiQueryService;

    private final JobService jobService;

    private final PersonRepository personRepository;

    private final PersonService personService;

    private final PersonQueryService personQueryService;

    private final EducationalModuleService educationalModuleService;

    private final EmploymentTypeService employmentTypeService;

    private final FinalNiazsanjiReportPersonService finalNiazsanjiReportPersonService;

    private final FinalNiazsanjiReportPersonQueryService finalNiazsanjiReportPersonQueryService;

    private final OrganizationChartRepository organizationChartRepository;

    private final OrganizationChartMapper organizationChartMapper;

    private final RequestNiazsanjiFardiQueryService requestNiazsanjiFardiQueryService;

    private final RequestOrganizationNiazsanjiQueryService requestOrganizationNiazsanjiQueryService;

    private final CacheManager cacheManager;

    public FinalNiazsanjiReportServiceImpl(FinalNiazsanjiReportRepository finalNiazsanjiReportRepository,
                                           FinalNiazsanjiReportMapper finalNiazsanjiReportMapper,
                                           NiazsanjiGroupService niazsanjiGroupService,
                                           NiazsanjiGroupQueryService niazsanjiGroupQueryService,
                                           FinalOrganizationNiazsanjiService finalOrganizationNiazsanjiService,
                                           FinalOrganizationNiazsanjiQueryService finalOrganizationNiazsanjiQueryService,
                                           JobService jobService,
                                           PersonRepository personRepository,
                                           PersonService personService,
                                           PersonQueryService personQueryService,
                                           EducationalModuleService educationalModuleService,
                                           EmploymentTypeService employmentTypeService,
                                           FinalNiazsanjiReportPersonService finalNiazsanjiReportPersonService,
                                           OrganizationChartQueryService organizationChartQueryService,
                                           FinalNiazsanjiReportPersonQueryService finalNiazsanjiReportPersonQueryService,
                                           OrganizationChartRepository organizationChartRepository,
                                           OrganizationChartMapper organizationChartMapper,
                                           RequestNiazsanjiFardiQueryService requestNiazsanjiFardiQueryService,
                                           RequestOrganizationNiazsanjiQueryService requestOrganizationNiazsanjiQueryService,
                                           CacheManager cacheManager) {
        this.finalNiazsanjiReportRepository = finalNiazsanjiReportRepository;
        this.finalNiazsanjiReportMapper = finalNiazsanjiReportMapper;
        this.niazsanjiGroupService = niazsanjiGroupService;
        this.niazsanjiGroupQueryService = niazsanjiGroupQueryService;
        this.finalOrganizationNiazsanjiService = finalOrganizationNiazsanjiService;
        this.finalOrganizationNiazsanjiQueryService = finalOrganizationNiazsanjiQueryService;
        this.jobService = jobService;
        this.personRepository = personRepository;
        this.personService = personService;
        this.personQueryService = personQueryService;
        this.educationalModuleService = educationalModuleService;
        this.employmentTypeService = employmentTypeService;
        this.finalNiazsanjiReportPersonService = finalNiazsanjiReportPersonService;
        this.finalNiazsanjiReportPersonQueryService = finalNiazsanjiReportPersonQueryService;
        this.organizationChartRepository = organizationChartRepository;
        this.organizationChartMapper = organizationChartMapper;
        this.requestNiazsanjiFardiQueryService = requestNiazsanjiFardiQueryService;
        this.requestOrganizationNiazsanjiQueryService = requestOrganizationNiazsanjiQueryService;
        this.cacheManager = cacheManager;
    }
    /**
     * Save a finalNiazsanjiReport.
     *
     * @param finalNiazsanjiReportDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FinalNiazsanjiReportDTO save(FinalNiazsanjiReportDTO finalNiazsanjiReportDTO) {
        log.debug("Request to save FinalNiazsanjiReport : {}",
                  finalNiazsanjiReportDTO);
        FinalNiazsanjiReport finalNiazsanjiReport = finalNiazsanjiReportMapper.toEntity(finalNiazsanjiReportDTO);
        finalNiazsanjiReport = finalNiazsanjiReportRepository.save(finalNiazsanjiReport);
        clearFinalNiazsanjiReportCaches();
        return finalNiazsanjiReportMapper.toDto(finalNiazsanjiReport);
    }
    /**
     * Save a finalNiazsanjiReport.
     *
     * @param finalNiazsanjiReportDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FinalNiazsanjiReportDTO saveAndComplete(FinalNiazsanjiReportDTO finalNiazsanjiReportDTO) throws Exception {

        log.debug("Request to save FinalNiazsanjiReport : {}", finalNiazsanjiReportDTO);
        FinalNiazsanjiReportDTO finalNiazsanjiReportResult = new FinalNiazsanjiReportDTO();
        try {

            FinalNiazsanjiReport finalNiazsanjiReport = finalNiazsanjiReportMapper.toEntity(finalNiazsanjiReportDTO);
            finalNiazsanjiReport = finalNiazsanjiReportRepository.save(finalNiazsanjiReport);
            finalNiazsanjiReportResult = finalNiazsanjiReportMapper.toDto(finalNiazsanjiReport);
        }
        catch (Exception ex){
            log.error("in finalNiazsanjiReport save section error: {}",ex.getMessage());
            String error = String.format("in finalNiazsanjiReport save section error: %s",ex.getMessage());
            throw new Exception(error);
        }
        List<FinalNiazsanjiReportPersonDTO> finalNiazsanjiReportPersonDTOS = new ArrayList<>();
        try{
        NiazsanjiGroupCriteria niazsanjiGroupCriteria = new NiazsanjiGroupCriteria();

        LongFilter niazsanjiGroupFilter = new LongFilter();
        niazsanjiGroupFilter.setEquals(finalNiazsanjiReportResult.getEducationalModuleId());
        niazsanjiGroupCriteria.setEducationalModuleId(niazsanjiGroupFilter);
        BooleanFilter niazsanjiGroupBooleanFilter = new BooleanFilter();
        niazsanjiGroupBooleanFilter.setEquals(false);
        niazsanjiGroupCriteria.setArchived(niazsanjiGroupBooleanFilter);
        IntegerFilter niazsanjiGroupStatusFilter = new IntegerFilter();
        niazsanjiGroupStatusFilter.setEquals(0);
        niazsanjiGroupCriteria.setStatus(niazsanjiGroupStatusFilter);
        List<NiazsanjiGroupDTO> niazsanjiGroupDTOS = niazsanjiGroupQueryService.findByCriteria(niazsanjiGroupCriteria);


        if(!niazsanjiGroupDTOS.isEmpty()) {
            for (NiazsanjiGroupDTO niazsanjiGroupDTO : niazsanjiGroupDTOS){
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

                        if(!personDTOS.isEmpty())
                        {
                            for(PersonDTO personDTO : personDTOS) {
                                FinalNiazsanjiReportPersonDTO finalNiazsanjiReportPersonDTO = new FinalNiazsanjiReportPersonDTO();
                                finalNiazsanjiReportPersonDTO.setPersonId(personDTO.getId());
                                finalNiazsanjiReportPersonDTO.setArchived(false);
                                finalNiazsanjiReportPersonDTO.setCreateDate(ZonedDateTime.now());
                                finalNiazsanjiReportPersonDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
                                finalNiazsanjiReportPersonDTO.setFinalNiazsanjiReportId(finalNiazsanjiReportResult.getId());
                                finalNiazsanjiReportPersonDTO.setNiazSanjiSource(NiazSanjiSource.GROUP);
                                finalNiazsanjiReportPersonDTO.setPriceCost(niazsanjiGroupDTO.getPriceCost());
                                finalNiazsanjiReportPersonDTO.setStatus(0);
                                finalNiazsanjiReportPersonDTOS.add(finalNiazsanjiReportPersonDTO);
                            }
                        }
                    }
                    niazsanjiGroupDTO.setStatus(1);
                    niazsanjiGroupService.save(niazsanjiGroupDTO);

            }
        }
        }
        catch (Exception ex){
            log.error("in niazsanjiGroup save section error: {}",ex.getMessage());
            String error = String.format("in niazsanjiGroup save section error: %s",ex.getMessage());
            throw new Exception(error);
        }
        try
        {
        FinalOrganizationNiazsanjiCriteria finalOrganizationNiazsanjiCriteria = new FinalOrganizationNiazsanjiCriteria();

        LongFilter finalOrganizationNiazsanjiFilter = new LongFilter();
        finalOrganizationNiazsanjiFilter.setEquals(finalNiazsanjiReportResult.getEducationalModuleId());
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
                    FinalNiazsanjiReportPersonDTO finalNiazsanjiReportPersonDTO = new FinalNiazsanjiReportPersonDTO();
                    finalNiazsanjiReportPersonDTO.setPersonId(personDTO.getId());
                    finalNiazsanjiReportPersonDTO.setArchived(false);
                    finalNiazsanjiReportPersonDTO.setCreateDate(ZonedDateTime.now());
                    finalNiazsanjiReportPersonDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
                    finalNiazsanjiReportPersonDTO.setFinalNiazsanjiReportId(finalNiazsanjiReportResult.getId());
                    finalNiazsanjiReportPersonDTO.setNiazSanjiSource(NiazSanjiSource.ORGANIZATION);
                    finalNiazsanjiReportPersonDTO.setPriceCost(finalOrganizationNiazsanjiDTO.getPriceCost());
                    finalNiazsanjiReportPersonDTO.setStatus(0);

                    finalNiazsanjiReportPersonDTOS.add(finalNiazsanjiReportPersonDTO);
                }
                finalOrganizationNiazsanjiDTO.setStatus(1);
                finalOrganizationNiazsanjiService.save(finalOrganizationNiazsanjiDTO);
            }
        }
        }
        catch (Exception ex){
            log.error("in finalOrganizationNiazsanji save section error: {}",ex.getMessage());
            String error = String.format("in finalOrganizationNiazsanji save section error: %s",ex.getMessage());
            throw new Exception(error);
        }
        try
        {
            finalNiazsanjiReportPersonDTOS = finalNiazsanjiReportPersonDTOS.stream().filter(distinctByKey(p -> p.getPersonId())).collect(Collectors.toList());
        for(FinalNiazsanjiReportPersonDTO finalNiazsanjiReportPersonDTO : finalNiazsanjiReportPersonDTOS) {
            finalNiazsanjiReportPersonService.save(finalNiazsanjiReportPersonDTO);
        }
        }
        catch (Exception ex){
            log.error("in finalNiazsanjiReportPerson save section error: {}",ex.getMessage());
            String error = String.format("in finalNiazsanjiReportPerson save section error: %s",ex.getMessage());
            throw new Exception(error);
        }
        clearFinalNiazsanjiReportCaches();
        return  finalNiazsanjiReportResult;
    }
    public static <T> Predicate<T> distinctByKey(
        Function<? super T, ?> ke) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(ke.apply(t), Boolean.TRUE) == null;
    }
    /**
     * Get all the finalNiazsanjiReports.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FinalNiazsanjiReportDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FinalNiazsanjiReports");
        return finalNiazsanjiReportRepository.findAll(pageable)
            .map(finalNiazsanjiReportMapper::toDto);
    }

    /**
     * Get all the FinalNiazsanjiReport with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<FinalNiazsanjiReportDTO> findAllWithEagerRelationships(Pageable pageable) {
        return finalNiazsanjiReportRepository.findAllWithEagerRelationships(pageable).map(finalNiazsanjiReportMapper::toDto);
    }
    

    /**
     * Get one finalNiazsanjiReport by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FinalNiazsanjiReportDTO> findOne(Long id) {
        log.debug("Request to get FinalNiazsanjiReport : {}", id);
        return finalNiazsanjiReportRepository.findOneWithEagerRelationships(id)
            .map(finalNiazsanjiReportMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChartResult> getChartResult(Integer niazsanjiYear) {
        log.debug("Request to get FinalNiazsanjiReport by niazSanjiYear : {}", niazsanjiYear);
        List<OrganizationChart> organizationCharts = organizationChartRepository.findAll();
        List<Long> groupIds = organizationCharts.stream().filter(a -> a.getParent() == null).map(a -> a.getId()).collect(Collectors.toList());
        List<ChartResult> chartResults = new ArrayList<>();
        for (Long groupId : groupIds) {
            List<Long> orgIds = getAllOfChilderenIdsOfThisId(organizationCharts, groupId,true).stream().distinct().collect(Collectors.toList());
            List<FinalNiazsanjiReportCustomDTO> finalNiazsanjiReportCustomDTOS = finalNiazsanjiReportRepository.findAllFromCache(orgIds, niazsanjiYear);
            ChartResult chartResult = new ChartResult();
            chartResult.setGroupId(groupId);



            Long priceCostNew = finalNiazsanjiReportCustomDTOS.stream().filter(a -> a.getStatus() == 0).mapToLong(o -> o.getPriceCost()).sum();
            chartResult.setPriceCostNew(priceCostNew);

            Long priceCostFinished = finalNiazsanjiReportCustomDTOS.stream().filter(a -> a.getStatus() == 20).mapToLong(o -> o.getPriceCost()).sum();
            chartResult.setPriceCostFinished(priceCostFinished);


            List<Long> idsNew = finalNiazsanjiReportCustomDTOS.stream().filter(a -> a.getStatus() == 0).map(o -> o.getId()).collect(Collectors.toList());

            Long educationalModuleTotalHourNew = Long.valueOf(0);
            educationalModuleTotalHourNew = getEducationHours(finalNiazsanjiReportCustomDTOS,
                                                              idsNew,
                                                              educationalModuleTotalHourNew);
            chartResult.setEducationalModuleTotalHourNew(educationalModuleTotalHourNew);

            List<Long> idsFinished = finalNiazsanjiReportCustomDTOS.stream().filter(a -> a.getStatus() == 20).map(o -> o.getId()).collect(Collectors.toList());
            Long educationalModuleTotalHourFinished = Long.valueOf(0);
            educationalModuleTotalHourFinished = getEducationHours(finalNiazsanjiReportCustomDTOS,
                                                              idsFinished,
                                                              educationalModuleTotalHourFinished);
            chartResult.setEducationalModuleTotalHourFinished(educationalModuleTotalHourFinished);
            chartResults.add(chartResult);
        }
        return chartResults;
    }

    @Override
    @Transactional(readOnly = true)
    public HomePageNiazsanjiReport getHomePageNiazsanjiReport(Long personId) {
        log.debug("Request to get HomePageNiazsanjiReport by personId : {}", personId);
        Optional<Person> person = personRepository.findById(personId);
        HomePageNiazsanjiReport report = new HomePageNiazsanjiReport();
        if(person.isPresent()){
            LongFilter personIdFilter = new LongFilter();
            personIdFilter.setEquals(personId);


            RequestNiazsanjiFardiCriteria requestNiazsanjiFardiCriteria = new RequestNiazsanjiFardiCriteria();
            requestNiazsanjiFardiCriteria.setPersonId(personIdFilter);

            report.setNiazsanjiFardiCount(requestNiazsanjiFardiQueryService.countByCriteria(requestNiazsanjiFardiCriteria));

            RequestNiazsanjiFardiCriteria.RequestStatusFilter requestStatusFilter = new RequestNiazsanjiFardiCriteria.RequestStatusFilter();
            requestStatusFilter.setEquals(RequestStatus.ACCEPT);
            requestNiazsanjiFardiCriteria.setRequestStatus(requestStatusFilter);

            report.setNiazsanjiFardiSucceedCount(requestNiazsanjiFardiQueryService.countByCriteria(requestNiazsanjiFardiCriteria));


            RequestOrganizationNiazsanjiCriteria requestOrganizationNiazsanjiCriteria = new RequestOrganizationNiazsanjiCriteria();
            requestOrganizationNiazsanjiCriteria.setPersonId(personIdFilter);

            report.setOrganizationNiazsanjiCount(requestOrganizationNiazsanjiQueryService.countByCriteria(requestOrganizationNiazsanjiCriteria));

            RequestOrganizationNiazsanjiCriteria.RequestStatusFilter organizationRequestStatusFilter = new RequestOrganizationNiazsanjiCriteria.RequestStatusFilter();
            requestStatusFilter.setEquals(RequestStatus.ACCEPT);
            requestOrganizationNiazsanjiCriteria.setRequestStatus(organizationRequestStatusFilter);

            report.setOrganizationNiazsanjiSucceedCount(requestOrganizationNiazsanjiQueryService.countByCriteria(requestOrganizationNiazsanjiCriteria));

            FinalNiazsanjiReportPersonCriteria finalNiazsanjiReportPersonCriteria = new FinalNiazsanjiReportPersonCriteria();
            finalNiazsanjiReportPersonCriteria.setPersonId(personIdFilter);
            List<FinalNiazsanjiReportPersonDTO> finalNiazsanjiReportPersonDTOS = finalNiazsanjiReportPersonQueryService.findByCriteria(finalNiazsanjiReportPersonCriteria);

            long designAndPlanningStepCount = 0;
            long runningStepCount = 0;

            for (FinalNiazsanjiReportPersonDTO finalNiazsanjiReportPersonDTO: finalNiazsanjiReportPersonDTOS) {
                if(finalNiazsanjiReportPersonDTO.getStatus() == 10)
                    designAndPlanningStepCount++;
                if(finalNiazsanjiReportPersonDTO.getStatus() == 20)
                    runningStepCount++;
            }
            report.setDesignAndPlanningStepCount(designAndPlanningStepCount);
            report.setRunningStepCount(runningStepCount);
        }

        return report;
    }

    private Long getEducationHours(List<FinalNiazsanjiReportCustomDTO> finalNiazsanjiReportCustomDTOS,
                                   List<Long> ids,
                                   Long educationalModuleTotalHour) {
        for (Long id : ids) {
            LongFilter newFilter = new LongFilter();
            newFilter.setEquals(id);

            FinalNiazsanjiReportPersonCriteria finalNiazsanjiReportPersonCriteria = new FinalNiazsanjiReportPersonCriteria();
            finalNiazsanjiReportPersonCriteria.setFinalNiazsanjiReportId(newFilter);

            long count = finalNiazsanjiReportPersonQueryService.countByCriteria(finalNiazsanjiReportPersonCriteria);
            educationalModuleTotalHour = educationalModuleTotalHour + (count * finalNiazsanjiReportCustomDTOS.stream()
                .filter(a -> a.getId() == id)
                .mapToLong(o -> o.getTotalLearningTime()).sum());
        }
        return educationalModuleTotalHour;
    }

    /**
     * Delete the finalNiazsanjiReport by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FinalNiazsanjiReport : {}", id);

        finalNiazsanjiReportPersonService.deleteByFinalNiazsanjiReportId(id);
        finalNiazsanjiReportRepository.deleteById(id);
        clearFinalNiazsanjiReportCaches();
    }

    private void clearFinalNiazsanjiReportCaches() {
        Objects.requireNonNull(cacheManager.getCache(finalNiazsanjiReportRepository.ALL_FINALNIAZSANJIREPORT_CACHE)).clear();
    }

    private List<Long> getAllOfChilderenIdsOfThisId(List<OrganizationChart> organizationCharts, Long organizationChartId, Boolean clear) {
        List<Long> resultChilderenIdsOfThisId = new ArrayList<>();
        List<OrganizationChart> orgs = organizationCharts.stream().filter(a -> a.getParent() != null && a.getParent().getId().equals(organizationChartId)).collect(Collectors.toList());

        if(!orgs.isEmpty()){
            for (OrganizationChart org : orgs) {
                //resultChilderenIdsOfThisId.add(org.getId());
                List<Long> childs = getAllOfChilderenIdsOfThisId(organizationCharts, org.getId(), false);
                resultChilderenIdsOfThisId.addAll(childs);
                /*return resultChilderenIdsOfThisId;*/
            }
        }
        resultChilderenIdsOfThisId.add(organizationChartId);
        return resultChilderenIdsOfThisId;
    }

}
