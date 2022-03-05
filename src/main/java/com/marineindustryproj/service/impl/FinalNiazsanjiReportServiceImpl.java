package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.domain.enumeration.EducationalModuleType;
import com.marineindustryproj.domain.enumeration.NiazSanjiSource;
import com.marineindustryproj.domain.enumeration.RequestStatus;
import com.marineindustryproj.repository.*;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.*;
import com.marineindustryproj.service.dto.*;
import com.marineindustryproj.service.dto.customs.*;
import com.marineindustryproj.service.dto.customs.CapitationReportModels.IndustryChildModel;
import com.marineindustryproj.service.dto.customs.EffectivenessPhaseModels.DetailFinalEffectivenessPhaseReportModel;
import com.marineindustryproj.service.dto.customs.EffectivenessPhaseModels.FinalEffectivenessPhaseReportModel;
import com.marineindustryproj.service.mapper.EffectivenessPhaseLevelMapper;
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

import javax.swing.*;
import java.time.ZonedDateTime;
import java.util.*;
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

    private final FinalNiazsanjiReportQueryService finalNiazsanjiReportQueryService;

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

    private final EducationalHistoryQueryService educationalHistoryQueryService;

    private final EducationalModuleJobQueryService educationalModuleJobQueryService;

    private final DesignAndPlanningQueryService designAndPlanningQueryService;

    private final DesignAndPlanningService designAndPlanningService;

    private final RunPhaseQueryService runPhaseQueryService;

    private final RunPhaseService runPhaseService;

    private final CacheManager cacheManager;

    private final NiazsanjiFardiService niazsanjiFardiService;

    private final CourseTypeService courseTypeService;


    private final EffectivenessPhaseLevelQueryService effectivenessPhaseLevelQueryService;

    private final EffectivenessPhaseLevelMapper effectivenessPhaseLevelMapper;

    private final EffectivenessPhaseRepository effectivenessPhaseRepository;

    private final EffectivenessPhaseQueryService effectivenessPhaseQueryService;

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
                                           FinalNiazsanjiReportQueryService finalNiazsanjiReportQueryService,
                                           FinalNiazsanjiReportPersonQueryService finalNiazsanjiReportPersonQueryService,
                                           OrganizationChartRepository organizationChartRepository,
                                           OrganizationChartMapper organizationChartMapper,
                                           RequestNiazsanjiFardiQueryService requestNiazsanjiFardiQueryService,
                                           RequestOrganizationNiazsanjiQueryService requestOrganizationNiazsanjiQueryService,
                                           EducationalHistoryQueryService educationalHistoryQueryService,
                                           EducationalModuleJobQueryService educationalModuleJobQueryService,
                                           DesignAndPlanningQueryService designAndPlanningQueryService,
                                           DesignAndPlanningService designAndPlanningService,
                                           RunPhaseQueryService runPhaseQueryService,
                                           RunPhaseService runPhaseService,
                                           CacheManager cacheManager,
                                           NiazsanjiFardiService niazsanjiFardiService, CourseTypeService courseTypeService,
                                           EffectivenessPhaseLevelQueryService effectivenessPhaseLevelQueryService,
                                           EffectivenessPhaseLevelMapper effectivenessPhaseLevelMapper,
                                           EffectivenessPhaseRepository effectivenessPhaseRepository, EffectivenessPhaseQueryService effectivenessPhaseQueryService) {
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
        this.finalNiazsanjiReportQueryService = finalNiazsanjiReportQueryService;
        this.finalNiazsanjiReportPersonQueryService = finalNiazsanjiReportPersonQueryService;
        this.organizationChartRepository = organizationChartRepository;
        this.organizationChartMapper = organizationChartMapper;
        this.requestNiazsanjiFardiQueryService = requestNiazsanjiFardiQueryService;
        this.requestOrganizationNiazsanjiQueryService = requestOrganizationNiazsanjiQueryService;
        this.educationalHistoryQueryService = educationalHistoryQueryService;
        this.educationalModuleJobQueryService = educationalModuleJobQueryService;
        this.designAndPlanningQueryService = designAndPlanningQueryService;
        this.designAndPlanningService = designAndPlanningService;
        this.runPhaseQueryService = runPhaseQueryService;
        this.runPhaseService = runPhaseService;
        this.cacheManager = cacheManager;
        this.niazsanjiFardiService = niazsanjiFardiService;
        this.courseTypeService = courseTypeService;
        this.effectivenessPhaseLevelQueryService = effectivenessPhaseLevelQueryService;
        this.effectivenessPhaseLevelMapper = effectivenessPhaseLevelMapper;
        this.effectivenessPhaseRepository = effectivenessPhaseRepository;
        this.effectivenessPhaseQueryService = effectivenessPhaseQueryService;
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
        Optional<RunPhaseDTO> runPhaseDTO = runPhaseService.findByFinalNiazsanjiReportId(finalNiazsanjiReportDTO.getId());
        if(runPhaseDTO.isPresent())
        {
            RunPhaseDTO runPhase = runPhaseDTO.get();
            runPhase.setEducationalModuleId(finalNiazsanjiReport.getEducationalModule().getId());
            runPhase.setCourseTypeId(finalNiazsanjiReport.getCourseType().getId());
            runPhase.setOrganizationChartId(finalNiazsanjiReport.getOrganizationChart().getId());
            runPhaseService.save(runPhase);
        }
        Optional<DesignAndPlanningDTO> designAndPlanningDTO = designAndPlanningService.findByFinalNiazsanjiReportId(finalNiazsanjiReportDTO.getId());
        if(designAndPlanningDTO.isPresent())
        {
            DesignAndPlanningDTO designAndPlanning = designAndPlanningDTO.get();
            designAndPlanning.setEducationalModuleId(finalNiazsanjiReport.getEducationalModule().getId());
            designAndPlanning.setCourseTypeId(finalNiazsanjiReport.getCourseType().getId());
            designAndPlanning.setOrganizationChartId(finalNiazsanjiReport.getOrganizationChart().getId());
            designAndPlanningService.save(designAndPlanning);
        }

        finalNiazsanjiReport = finalNiazsanjiReportRepository.save(finalNiazsanjiReport);
        clearFinalNiazsanjiReportCaches();
        return finalNiazsanjiReportMapper.toDto(finalNiazsanjiReport);
    }
    @Override
    public FinalNiazsanjiReportDTO setEffectivenessPhaseLevel(FinalNiazsanjiReportDTO finalNiazsanjiReportDTO) throws Exception {
        log.debug("Request to save FinalNiazsanjiReport : {}",
            finalNiazsanjiReportDTO);
        FinalNiazsanjiReport finalNiazsanjiReport = finalNiazsanjiReportMapper.toEntity(finalNiazsanjiReportDTO);


        EffectivenessPhaseLevelCriteria criteria = new EffectivenessPhaseLevelCriteria();
        IntegerFilter effectivenessLevelUseFilter = new IntegerFilter();
        effectivenessLevelUseFilter.setEquals(finalNiazsanjiReportDTO.getSelectedEffectivenessPhaseLevel());
        criteria.setEffectivenessLevelUse(effectivenessLevelUseFilter);
        List<EffectivenessPhaseLevelDTO>  effectivenessPhaseLevels = effectivenessPhaseLevelQueryService.findByCriteria(criteria);
        if(effectivenessPhaseLevels.isEmpty()){
            throw new Exception("لولی برای این سطح تعریف نشده است.");
        }

        for (EffectivenessPhaseLevelDTO effectivenessPhaseLevelDTO : effectivenessPhaseLevels) {
            EffectivenessPhaseLevel effectivenessPhaseLevel = effectivenessPhaseLevelMapper.toEntity(effectivenessPhaseLevelDTO);
            EffectivenessPhase effectivenessPhase = new EffectivenessPhase();
            effectivenessPhase.setEffectivenessPhaseLevel(effectivenessPhaseLevel);
            effectivenessPhase.setFinalNiazsanjiReport(finalNiazsanjiReport);
            effectivenessPhase.setCreateDate(ZonedDateTime.now());
            effectivenessPhase.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
            effectivenessPhase.setModifyDate(ZonedDateTime.now());
            effectivenessPhase.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
            effectivenessPhase.setStatus(0);
            effectivenessPhase.setFinalScore(0f);
            effectivenessPhase.setFirstScore(0f);
            effectivenessPhase.setSecondScore(0f);
            effectivenessPhase.setWeightedPoints(0f);
            effectivenessPhaseRepository.save(effectivenessPhase);
        }

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
                                finalNiazsanjiReportPersonDTO.setAbsented(false);
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
                        finalNiazsanjiReportPersonDTO.setAbsented(false);
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

            Long totalCost = finalNiazsanjiReportCustomDTOS.stream().mapToLong(o -> o.getPriceCost()).sum();
            chartResult.setTotalPriceCost(totalCost);

            Long priceCostNew = finalNiazsanjiReportCustomDTOS.stream().filter(a -> a.getStatus() < 20).mapToLong(o -> o.getPriceCost()).sum();
            chartResult.setPriceCostNew(priceCostNew);

            Long priceCostFinished = finalNiazsanjiReportCustomDTOS.stream().filter(a -> a.getStatus() >= 20).mapToLong(o -> o.getFinalizeCost()).sum();
            chartResult.setPriceCostFinished(priceCostFinished);

            List<Long> idsTotal = finalNiazsanjiReportCustomDTOS.stream().map(o -> o.getId()).collect(Collectors.toList());

            Long totalHour = Long.valueOf(0);
            totalHour = getEducationHours(finalNiazsanjiReportCustomDTOS,
                idsTotal,
                totalHour);
            chartResult.setTotalPersonHour(totalHour);

            List<Long> idsNew = finalNiazsanjiReportCustomDTOS.stream().filter(a -> a.getStatus() < 20).map(o -> o.getId()).collect(Collectors.toList());

            Long educationalModuleTotalHourNew = Long.valueOf(0);
            educationalModuleTotalHourNew = getEducationHours(finalNiazsanjiReportCustomDTOS,
                idsNew,
                educationalModuleTotalHourNew);
            chartResult.setEducationalModuleTotalHourNew(educationalModuleTotalHourNew);

            List<Long> idsFinished = finalNiazsanjiReportCustomDTOS.stream().filter(a -> a.getStatus() >= 20).map(o -> o.getId()).collect(Collectors.toList());
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
    public List<ChartResultDetail> getChartResultDetail(Integer niazsanjiYear, Long rootOrgId) {
        log.debug("Request to get FinalNiazsanjiReport by niazSanjiYear : {}", niazsanjiYear);
        List<OrganizationChart> organizationCharts = organizationChartRepository.findAllWithoutCache();
        List<OrganizationChart> groups = organizationCharts.stream().filter(a -> a.getParent() == null).collect(Collectors.toList());

        if(rootOrgId > 0)
        {
            groups = groups.stream().filter(w -> w.getId() == rootOrgId.longValue()).collect(Collectors.toList());
        }

        List<IndustryChildModel> industryChildModels = new ArrayList<>();
        for (OrganizationChart group : groups) {
            List<OrganizationChart> industries = new ArrayList<>();
            List<String> industriesCodes = new ArrayList<>();
            for (OrganizationChart organizationChart : organizationCharts) {
                if(organizationChart.getParent() != null && organizationChart.getParent().getId() == group.getId()) {
                    if(!industriesCodes.stream().anyMatch(w -> w.equals(organizationChart.getCode())))
                        industriesCodes.add(organizationChart.getCode());
                    industries.add(organizationChart);
                }
            }

            for (String industriesCode : industriesCodes) {
                List<OrganizationChart> selectedIndustries = industries.stream().filter(w -> w.getCode() == industriesCode).collect(Collectors.toList());
                List<Long> orgLongList = new ArrayList<>();
                for (OrganizationChart selectedIndustry : selectedIndustries) {
                    orgLongList.addAll(getAllOfChilderenIdsOfThisId(organizationCharts, selectedIndustry.getId(), true));
                }
                if(group.getCode() == industriesCode)
                    orgLongList.add(group.getId());

                industryChildModels.add(new IndustryChildModel(
                    group.getId(), group.getTitle(), industriesCode, industriesCode, orgLongList
                ));
            }
        }

        List<ChartResultDetail> chartResults = new ArrayList<>();
        for (IndustryChildModel industryChildModel : industryChildModels) {
            //List<Long> orgIds = getAllOfChilderenIdsOfThisId(organizationCharts, groupId,true).stream().distinct().collect(Collectors.toList());
            List<FinalNiazsanjiReportCustomDTO> finalNiazsanjiReportCustomDTOS = finalNiazsanjiReportRepository.findAllFromCache(industryChildModel.getIndustryOrganizationChartIds(), niazsanjiYear);
            ChartResultDetail chartResult = new ChartResultDetail();
            chartResult.setGroupId(industryChildModel.getGroupId());
            chartResult.setIndustryTitle(industryChildModel.getIndustryTitle());
            chartResult.setOrgChartIds(industryChildModel.getIndustryOrganizationChartIds());

            Long totalCost = finalNiazsanjiReportCustomDTOS.stream().mapToLong(o -> o.getPriceCost()).sum();
            chartResult.setTotalPriceCost(totalCost);

            Long priceCostNew = finalNiazsanjiReportCustomDTOS.stream().filter(a -> a.getStatus() < 20).mapToLong(o -> o.getPriceCost()).sum();
            chartResult.setPriceCostNew(priceCostNew);

            Long priceCostFinished = finalNiazsanjiReportCustomDTOS.stream().filter(a -> a.getStatus() >= 20).mapToLong(o -> o.getFinalizeCost()).sum();
            chartResult.setPriceCostFinished(priceCostFinished);

            List<Long> idsTotal = finalNiazsanjiReportCustomDTOS.stream().map(o -> o.getId()).collect(Collectors.toList());

            Long totalHour = Long.valueOf(0);
            totalHour = getEducationHours(finalNiazsanjiReportCustomDTOS,
                idsTotal,
                totalHour);
            chartResult.setTotalPersonHour(totalHour);

            List<Long> idsNew = finalNiazsanjiReportCustomDTOS.stream().filter(a -> a.getStatus() < 20).map(o -> o.getId()).collect(Collectors.toList());

            Long educationalModuleTotalHourNew = Long.valueOf(0);
            educationalModuleTotalHourNew = getEducationHours(finalNiazsanjiReportCustomDTOS,
                idsNew,
                educationalModuleTotalHourNew);
            chartResult.setEducationalModuleTotalHourNew(educationalModuleTotalHourNew);

            List<Long> idsFinished = finalNiazsanjiReportCustomDTOS.stream().filter(a -> a.getStatus() >= 20).map(o -> o.getId()).collect(Collectors.toList());
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
    @Override
    @Transactional(readOnly = true)
    public List<PlanningAndRunMonthReport> getPlanningAndRunMonthReport(Integer niazsanjiYear, Integer reportType, Long rootOrgId) {
        log.debug("Request to get HomePageNiazsanjiReport by personId : {}", niazsanjiYear);

        List<PlanningAndRunMonthReport> planningAndRunMonthReports = new ArrayList<>();

        List<OrganizationChart> organizationCharts = organizationChartRepository.findAll();
        List<Long> orgIds = this.getAllOfChilderenIdsOfThisId(organizationCharts ,rootOrgId, true);
        List<FinalNiazsanjiReportCustomDTO> finalNiazsanjiReportCustomDTOS = finalNiazsanjiReportRepository.findAllFromCache(orgIds, niazsanjiYear);

        Long totalCost = finalNiazsanjiReportCustomDTOS.stream().mapToLong(o -> o.getPriceCost()).sum();

        List<Long> idsTotal = finalNiazsanjiReportCustomDTOS.stream().map(o -> o.getId()).collect(Collectors.toList());
        Long totalHour = Long.valueOf(0);
        totalHour = getEducationHours(finalNiazsanjiReportCustomDTOS,
            idsTotal,
            totalHour);

        IntegerFilter niazsanjiYearFilter = new IntegerFilter();
        niazsanjiYearFilter.setEquals(niazsanjiYear);
        LongFilter orgIdsFilter = new LongFilter();


        orgIdsFilter.setIn(orgIds);
        BooleanFilter booleanFilter = new BooleanFilter();
        booleanFilter.setEquals(true);

        List<EducationalModuleMinDTO> educationalModuleMinDTOS = educationalModuleService.findAllFromCache();
        //if want just planning
        if(reportType == 1) {
            planningAndRunMonthReports.addAll(getDesignAndPlanningMonthResult(niazsanjiYearFilter,
                orgIdsFilter,
                booleanFilter,
                totalCost,
                totalHour,
                educationalModuleMinDTOS));
        }
        //if want just run
        else if(reportType == 2){
            planningAndRunMonthReports.addAll(getRunPhaseMonthResult(niazsanjiYearFilter,
                orgIdsFilter,
                booleanFilter,
                totalCost,
                totalHour,
                educationalModuleMinDTOS));
        }
        //if want them both
        else if(reportType == 3){
            planningAndRunMonthReports.addAll(getDesignAndPlanningMonthResult(niazsanjiYearFilter,
                orgIdsFilter,
                booleanFilter,
                totalCost,
                totalHour,
                educationalModuleMinDTOS));
            planningAndRunMonthReports.addAll(getRunPhaseMonthResult(niazsanjiYearFilter,
                orgIdsFilter,
                booleanFilter,
                totalCost,
                totalHour,
                educationalModuleMinDTOS));
        }
        return planningAndRunMonthReports;
    }

    private List<PlanningAndRunMonthReport> getRunPhaseMonthResult(IntegerFilter niazsanjiYearFilter,
                                                                   LongFilter orgIdsFilter,
                                                                   BooleanFilter booleanFilter,
                                                                   Long totalCost,
                                                                   Long totalHour,
                                                                   List<EducationalModuleMinDTO> educationalModuleMinDTOS) {
        List<PlanningAndRunMonthReport> planningAndRunMonthReports = new ArrayList<>();
        RunPhaseCriteria runPhaseCriteria = new RunPhaseCriteria();
        runPhaseCriteria.setNiazsanjiYear(niazsanjiYearFilter);
        runPhaseCriteria.setOrganizationChartId(orgIdsFilter);
        runPhaseCriteria.setDone(booleanFilter);
        List<RunPhaseDTO> runPhaseDTOS = runPhaseQueryService.findByCriteria(runPhaseCriteria);
        for (int i = 1; i <= 12; i++) {
            Integer month = i;
            List<RunPhaseDTO> runPhaseMonth = runPhaseDTOS.stream().filter(a -> a.getRunMonth() != null && a.getRunMonth().equals(month)).collect(Collectors.toList());
            Long personHour = Long.valueOf(0);
            Long personCost = Long.valueOf(0);
            for (RunPhaseDTO runPhaseDTO: runPhaseMonth) {
                Integer peopleCount = runPhaseDTO.getPeople().size();
                personCost += (runPhaseDTO.getFinalizeCost() * peopleCount);
                List<EducationalModuleMinDTO> selectedEducts = educationalModuleMinDTOS.stream().filter(a -> a.getId().equals(runPhaseDTO.getEducationalModuleId())).collect(Collectors.toList());
                if(selectedEducts.size() > 0)
                {
                    EducationalModuleMinDTO educationalModuleMinDTO = selectedEducts.get(0);
                    personHour += ((educationalModuleMinDTO.getLearningTimePractical() + educationalModuleMinDTO.getLearningTimeTheorical()) * peopleCount);
                }
            }
            planningAndRunMonthReports.add(new PlanningAndRunMonthReport(month, totalHour, totalCost, personHour, personCost,
                2));
        }
        return planningAndRunMonthReports;
    }

    private List<PlanningAndRunMonthReport> getDesignAndPlanningMonthResult(IntegerFilter niazsanjiYearFilter,
                                                                            LongFilter orgIdsFilter,
                                                                            BooleanFilter booleanFilter,
                                                                            Long totalCost,
                                                                            Long totalHour,
                                                                            List<EducationalModuleMinDTO> educationalModuleMinDTOS) {
        List<PlanningAndRunMonthReport> planningAndRunMonthReports = new ArrayList<>();
        DesignAndPlanningCriteria designAndPlanningCriteria = new DesignAndPlanningCriteria();
        designAndPlanningCriteria.setNiazsanjiYear(niazsanjiYearFilter);
        designAndPlanningCriteria.setOrganizationChartId(orgIdsFilter);
        designAndPlanningCriteria.setFinished(booleanFilter);
        List<DesignAndPlanningDTO> designAndPlanningDTOS = designAndPlanningQueryService.findByCriteria(designAndPlanningCriteria);
        for (int i = 1; i <= 12; i++) {
            Integer month = i;
            List<DesignAndPlanningDTO> designAndPlanningMonth = designAndPlanningDTOS.stream().filter(a -> a.getRunMonth() != null && a.getRunMonth().equals(month)).collect(Collectors.toList());
            Long personHour = Long.valueOf(0);
            Long personCost = Long.valueOf(0);
            for (DesignAndPlanningDTO designAndPlanningDTO: designAndPlanningMonth) {
                Integer peopleCount = designAndPlanningDTO.getPeople().size();
                personCost += ((designAndPlanningDTO.getDirectCost() + designAndPlanningDTO.getUndirectCost()) * peopleCount);
                List<EducationalModuleMinDTO> selectedEducts = educationalModuleMinDTOS.stream().filter(a -> a.getId().equals(designAndPlanningDTO.getEducationalModuleId())).collect(Collectors.toList());
                if(selectedEducts.size() > 0)
                {
                    EducationalModuleMinDTO educationalModuleMinDTO = selectedEducts.get(0);
                    personHour += ((educationalModuleMinDTO.getLearningTimePractical() + educationalModuleMinDTO.getLearningTimeTheorical()) * peopleCount);
                }
            }
            planningAndRunMonthReports.add(new PlanningAndRunMonthReport(month, totalHour, totalCost, personHour, personCost,
                1));
        }
        return planningAndRunMonthReports;
    }

    @Override
    @Transactional(readOnly = true)
    public HomePagePersonHourChart getHomePagePersonHourChart(Long personId) {
        log.debug("Request to get HomePagePersonHourChart by personId : {}", personId);
        Optional<Person> person = personRepository.findById(personId);
        HomePagePersonHourChart report = new HomePagePersonHourChart();
        if(person.isPresent()){
            LongFilter personIdFilter = new LongFilter();
            personIdFilter.setEquals(personId);

            LongFilter jobIdFilter = new LongFilter();
            jobIdFilter.setEquals(person.get().getJob().getId());

            EducationalModuleJobCriteria educationalModuleJobCriteria = new EducationalModuleJobCriteria();
            educationalModuleJobCriteria.setJobId(jobIdFilter);
            List<EducationalModuleJobDTO> educationalModuleJobDTOS = educationalModuleJobQueryService.findByCriteria(educationalModuleJobCriteria);
            long[] educationalIds = educationalModuleJobDTOS.stream().mapToLong(a -> a.getEducationalModuleId()).toArray();
            List<EducationalModuleMinDTO> educationalModules = educationalModuleService.findAllFromCache().stream().filter(a -> Arrays.stream(educationalIds).anyMatch(w -> w == a.getId())).collect(Collectors.toList());
            long[] totalLearningTimes = educationalModules.stream().mapToLong(a -> a.getLearningTimePractical() + a.getLearningTimeTheorical()).toArray();
            Long totalTime = Arrays.stream(totalLearningTimes).sum();

            DesignAndPlanningCriteria designAndPlanningCriteria = new DesignAndPlanningCriteria();
            designAndPlanningCriteria.setPersonId(personIdFilter);

            BooleanFilter finishedFilter = new BooleanFilter();
            finishedFilter.setEquals(false);
            designAndPlanningCriteria.setFinished(finishedFilter);

            List<DesignAndPlanningDTO> designAndPlanningDTOS = designAndPlanningQueryService.findByCriteria(designAndPlanningCriteria);
            long[] designEducationalIds = designAndPlanningDTOS.stream().mapToLong(a -> a.getEducationalModuleId()).toArray();
            List<EducationalModuleMinDTO> designEducationalModules = educationalModuleService.findAllFromCache().stream().filter(a -> Arrays.stream(designEducationalIds).anyMatch(w -> w == a.getId())).collect(Collectors.toList());
            long[] designTimes = designEducationalModules.stream().mapToLong(a -> a.getLearningTimePractical() + a.getLearningTimeTheorical()).toArray();
            Long designTotalTime = Arrays.stream(designTimes).sum();

            EducationalHistoryCriteria educationalHistoryCriteria = new EducationalHistoryCriteria();
            educationalHistoryCriteria.setPersonId(personIdFilter);

            EducationalHistoryCriteria.RequestStatusFilter requestStatusFilter = new EducationalHistoryCriteria.RequestStatusFilter();
            requestStatusFilter.setEquals(RequestStatus.ACCEPT);
            educationalHistoryCriteria.setRequestStatus(requestStatusFilter);

            List<EducationalHistoryDTO> educationalHistoryDTOS = educationalHistoryQueryService.findByCriteria(educationalHistoryCriteria);
            //List<EducationalHistoryDTO> educationalHistoryDTOS1 = educationalHistoryDTOS.stream().filter(a -> a.getEducationalModuleId() != null).collect(Collectors.toList());
            long[] educationalHistoryEducationalIds = educationalHistoryDTOS.stream().filter(a -> a.getEducationalModuleId() != null).mapToLong(a -> a.getEducationalModuleId()).toArray();
            List<EducationalModuleMinDTO> educationalHistoryEducationalModules = educationalModuleService.findAllFromCache().stream().filter(a -> Arrays.stream(educationalHistoryEducationalIds).anyMatch(w -> w == a.getId())).collect(Collectors.toList());
            long[] educationalHistoryTimes = educationalHistoryEducationalModules.stream().mapToLong(a -> a.getLearningTimePractical() + a.getLearningTimeTheorical()).toArray();
            Long educationalHistoryTime = Arrays.stream(educationalHistoryTimes).sum();

            float designPercent = ((float) designTotalTime / totalTime) * 100;
            float educationalHistoryPercent = ((float) educationalHistoryTime / totalTime) * 100;

            float remaining = 100 - designPercent - educationalHistoryPercent;

            report.setDesignAndPlanning(designPercent);
            report.setPassed(educationalHistoryPercent);
            report.setRemaining(remaining);
        }

        return report;
    }

    @Override
    @Transactional(readOnly = true)
    public HomePageReport getHomePageReport(Integer niazsanjiYear, HomePageReportType homePageReportType) {
        log.debug("Request to get HomePageReport");
        List<CourseTypeDTO> courseTypeDTOS = courseTypeService.findAll();
        List<OrganizationChart> organizationCharts = organizationChartRepository.findAll();
        List<OrganizationChart> groups = organizationCharts.stream().filter(a -> a.getParent() == null).sorted(Comparator.comparing(OrganizationChart::getId)).collect(Collectors.toList());
        List<FinalNiazsanjiReport> finalNiazsanjiReports = finalNiazsanjiReportRepository.findAllByNiazsanjiYear(niazsanjiYear);

        List<FinalNiazsanjiReportHomePageDTO> finalNiazsanjiReportHomePageDTOS = new ArrayList<>();

        HomePageReport homePageReport = new HomePageReport();

        /*if(finalNiazsanjiReports.size() == 0) {
                return emptyHomePageReport();
        }*/

        for (FinalNiazsanjiReport finalNiazsanjiReport : finalNiazsanjiReports) {
            if(finalNiazsanjiReport.getFinalNiazsanjiReportPeople().size() > 1)
            {
                Set<FinalNiazsanjiReportPerson> managerPeople = new HashSet<>();
                Set<FinalNiazsanjiReportPerson> stuffPeople = new HashSet<>();
                for (FinalNiazsanjiReportPerson finalNiazsanjiReportPerson : finalNiazsanjiReport.getFinalNiazsanjiReportPeople()) {
                    if(isManager(finalNiazsanjiReportPerson.getPerson().getJob().getJobCode())){
                        managerPeople.add(finalNiazsanjiReportPerson);
                    }
                    else {
                        stuffPeople.add(finalNiazsanjiReportPerson);
                    }
                }
                Integer pricePerPerson = (finalNiazsanjiReport.getPriceCost() != null ? finalNiazsanjiReport.getPriceCost().intValue() : 0) / finalNiazsanjiReport.getFinalNiazsanjiReportPeople().size();
                Integer finalizeCostPerPerson = (finalNiazsanjiReport.getFinalizeCost() != null ? finalNiazsanjiReport.getFinalizeCost().intValue() : 0) / finalNiazsanjiReport.getFinalNiazsanjiReportPeople().size();
                if(managerPeople.size() > 0)
                {
                    Integer finalPrice = pricePerPerson * managerPeople.size();
                    Integer finalizeCost = finalizeCostPerPerson * managerPeople.size();
                    FinalNiazsanjiReportHomePageDTO finalNiazsanjiReportHomePageDTO =
                        new FinalNiazsanjiReportHomePageDTO(finalNiazsanjiReport.getId(),
                            homePageReportType,
                            finalPrice,
                            //finalNiazsanjiReport.getFinalizeCost(),
                            finalizeCost,
                            finalNiazsanjiReport.getEducationalModule(),
                            finalNiazsanjiReport.getStatus(),
                            finalNiazsanjiReport.getNiazsanjiYear(),
                            finalNiazsanjiReport.getCourseType(),
                            finalNiazsanjiReport.getOrganizationChart(),
                            managerPeople,
                            true);
                    finalNiazsanjiReportHomePageDTOS.add(finalNiazsanjiReportHomePageDTO);
                }
                if(stuffPeople.size() > 0)
                {
                    Integer finalPrice = pricePerPerson * stuffPeople.size();
                    Integer finalizeCost = finalizeCostPerPerson * stuffPeople.size();
                    FinalNiazsanjiReportHomePageDTO finalNiazsanjiReportHomePageDTO =
                        new FinalNiazsanjiReportHomePageDTO(finalNiazsanjiReport.getId(),
                            homePageReportType,
                            finalPrice,
                            //finalNiazsanjiReport.getFinalizeCost(),
                            finalizeCost,
                            finalNiazsanjiReport.getEducationalModule(),
                            finalNiazsanjiReport.getStatus(),
                            finalNiazsanjiReport.getNiazsanjiYear(),
                            finalNiazsanjiReport.getCourseType(),
                            finalNiazsanjiReport.getOrganizationChart(),
                            stuffPeople,
                            false);
                    finalNiazsanjiReportHomePageDTOS.add(finalNiazsanjiReportHomePageDTO);
                }

            }
            else if(finalNiazsanjiReport.getFinalNiazsanjiReportPeople().size() == 1) {
                FinalNiazsanjiReportHomePageDTO finalNiazsanjiReportHomePageDTO =
                    new FinalNiazsanjiReportHomePageDTO(finalNiazsanjiReport.getId(),
                        homePageReportType,
                        finalNiazsanjiReport.getPriceCost(),
                        finalNiazsanjiReport.getFinalizeCost(),
                        finalNiazsanjiReport.getEducationalModule(),
                        finalNiazsanjiReport.getStatus(),
                        finalNiazsanjiReport.getNiazsanjiYear(),
                        finalNiazsanjiReport.getCourseType(),
                        finalNiazsanjiReport.getOrganizationChart(),
                        finalNiazsanjiReport.getFinalNiazsanjiReportPeople(),
                        isManager(finalNiazsanjiReport.getFinalNiazsanjiReportPeople().stream().findFirst().get().getPerson().getJob().getJobCode()));
                finalNiazsanjiReportHomePageDTOS.add(finalNiazsanjiReportHomePageDTO);
            }

        }


        homePageReport.setTotal((float) finalNiazsanjiReportHomePageDTOS.stream().mapToLong(a -> a.getValue()).sum());
        homePageReport.setTotalPassed((float) finalNiazsanjiReportHomePageDTOS.stream().filter(a -> a.getStatus() >= 20).mapToLong(a -> a.getPassedValue()).sum());
        if(homePageReport.getTotal() > 0)
            homePageReport.setTotalPassedPercent((homePageReport.getTotalPassed() / homePageReport.getTotal()) * 100);
        else
            homePageReport.setTotalPassedPercent(0f);

        /*List<JobMinDTO> jobMinDTOS = jobService.findAllFromCache();
        List<Long> managerJobIds = new ArrayList<>();
        for (JobMinDTO jobMinDTO : jobMinDTOS) {
            if(jobMinDTO.getJobCode().startsWith("18") || jobMinDTO.getJobCode().startsWith("19") || jobMinDTO.getJobCode().startsWith("20")){
                managerJobIds.add(jobMinDTO.getId());
            }
        }
        //List<Long> managerJobIds = jobMinDTOS.stream().filter(a -> a.getJobCode().startsWith("18") || a.getJobCode().startsWith("19") || a.getJobCode().startsWith("20")).map(a -> a.getId()).distinct().collect(Collectors.toList());
        List<Long> stuffJobIds = jobMinDTOS.stream().map(JobMinDTO::getId).collect(Collectors.toList());
        for (Long id : managerJobIds) {
            if(stuffJobIds.contains(id))
                stuffJobIds.remove(id);
        }*/
        //= jobMinDTOS.stream().filter(a -> managerJobIds.stream().noneMatch(w -> w.equals(a.getId()))).map(a -> a.getId()).distinct().collect(Collectors.toList());
        //List<Long> stuffJobIds = jobMinDTOS.stream().filter(a -> (!a.getJobCode().startsWith("18")) || (!a.getJobCode().startsWith("19")) || (!a.getJobCode().startsWith("20"))).map(a -> a.getId()).distinct().collect(Collectors.toList());

        homePageReport.setTotalManagers((float) finalNiazsanjiReportHomePageDTOS.stream().filter(a -> a.isManager()).mapToDouble(a -> a.getValue()).sum());
        if(homePageReport.getTotal() > 0)
            homePageReport.setTotalManagersPercent((homePageReport.getTotalManagers() / homePageReport.getTotal()) * 100);
        else
            homePageReport.setTotalManagersPercent(0f);
        homePageReport.setTotalStuffs((float) finalNiazsanjiReportHomePageDTOS.stream().filter(a -> !a.isManager()).mapToDouble(a -> a.getValue()).sum());
        if(homePageReport.getTotal() > 0)
            homePageReport.setTotalStuffsPercent((homePageReport.getTotalStuffs() / homePageReport.getTotal()) * 100);
        else
            homePageReport.setTotalStuffsPercent(0f);

        homePageReport.setTotalPassedManagers((float) finalNiazsanjiReportHomePageDTOS.stream().filter(a -> a.getStatus() >= 20 && a.isManager()).mapToLong(a -> a.getPassedValue()).sum());

        if(homePageReport.getTotalManagers() > 0)
            homePageReport.setTotalPassedManagersPercent((homePageReport.getTotalPassedManagers() / homePageReport.getTotalManagers()) * 100);
        else
            homePageReport.setTotalPassedManagersPercent(0f);

        homePageReport.setTotalPassedStuffs((float) finalNiazsanjiReportHomePageDTOS.stream().filter(a -> a.getStatus() >= 20 && !a.isManager()).mapToLong(a -> a.getPassedValue()).sum());

        if(homePageReport.getTotalStuffs() > 0)
            homePageReport.setTotalPassedStuffsPercent((homePageReport.getTotalPassedStuffs() / homePageReport.getTotalStuffs()) * 100);
        else
            homePageReport.setTotalPassedStuffsPercent(0f);

        List<HomePageReportCourseTypeDetail> homePageReportCourseTypeDetails = new ArrayList<>();
        for (CourseTypeDTO courseTypeDTO : courseTypeDTOS) {
            float total =
                (float) finalNiazsanjiReportHomePageDTOS.stream().filter(a -> a.getCourseTypeId().equals(courseTypeDTO.getId()))
                    .mapToDouble(a -> a.getValue()).sum();
            float totalPercent = 0;
            if(homePageReport.getTotal() > 0)
                totalPercent =
                    ((total / homePageReport.getTotal()) * 100);

            float totalManagers =
                (float) finalNiazsanjiReportHomePageDTOS.stream().filter(a -> a.getCourseTypeId().equals(courseTypeDTO.getId()) && a.isManager())
                    .mapToDouble(a -> a.getValue()).sum();

            float totalManagersPercent = 0;
            if(homePageReport.getTotal() > 0)
                 totalManagersPercent = ((totalManagers / homePageReport.getTotal()) * 100);

            float totalStuffs =
                (float) finalNiazsanjiReportHomePageDTOS.stream().filter(a -> a.getCourseTypeId().equals(courseTypeDTO.getId()) && !a.isManager())
                    .mapToDouble(a -> a.getValue()).sum();

            float totalStuffsPercent = 0;
            if(homePageReport.getTotal() > 0)
                totalStuffsPercent = ((totalStuffs / homePageReport.getTotal()) * 100);

            HomePageReportCourseTypeDetail homePageReportCourseTypeDetail =
                new HomePageReportCourseTypeDetail(courseTypeDTO.getId(),
                    courseTypeDTO.getTitle(),
                    total,
                    totalPercent,
                    totalManagers,
                    totalManagersPercent,
                    totalStuffs,
                    totalStuffsPercent
                );
            homePageReportCourseTypeDetails.add(homePageReportCourseTypeDetail);
        }
        homePageReport.setHomePageReportCourseTypeDetails(homePageReportCourseTypeDetails);

        List<HomePageReportDetail> homePageReportDetails = new ArrayList<>();
        for (OrganizationChart group: groups) {

            List<Long> orgIds = this.getAllOfChilderenIdsOfThisId(organizationCharts, group.getId(), true);
            List<FinalNiazsanjiReportHomePageDTO> finalNiazsanjiReportDTOs = finalNiazsanjiReportHomePageDTOS.stream()
                .filter(a -> orgIds.contains(a.getOrganizationChartId()) && a.getNiazsanjiYear().equals(niazsanjiYear)).collect(Collectors.toList());

            HomePageReportDetail homePageReportDetail = new HomePageReportDetail();
            homePageReportDetail.setOrganizationChartId(group.getId());
            homePageReportDetail.setOrganizationChartTitle(group.getTitle());
            homePageReportDetail.setTotal((float) finalNiazsanjiReportDTOs.stream().mapToDouble(a -> a.getValue()).sum());
            if(homePageReport.getTotal() > 0)
                homePageReportDetail.setTotalPercent((homePageReportDetail.getTotal() / homePageReport.getTotal()) * 100);
            else
                homePageReportDetail.setTotalPercent(0f);

            homePageReportDetail.setTotalManagers((float) finalNiazsanjiReportDTOs.stream()
                .filter(a -> a.isManager()).mapToDouble(a -> a.getValue()).sum());

            if(homePageReportDetail.getTotal() > 0)
                homePageReportDetail.setTotalManagersPercent((homePageReportDetail.getTotalManagers() / homePageReportDetail.getTotal()) * 100);
            else
                homePageReportDetail.setTotalManagersPercent(0f);

            homePageReportDetail.setTotalStuffs((float) finalNiazsanjiReportDTOs.stream()
                .filter(a -> !a.isManager()).mapToDouble(a -> a.getValue()).sum());

            if(homePageReportDetail.getTotal() > 0)
                homePageReportDetail.setTotalStuffsPercent((homePageReportDetail.getTotalStuffs() / homePageReportDetail.getTotal()) * 100);
            else
                homePageReportDetail.setTotalStuffsPercent(0f);

            homePageReportDetail.setTotalPassed((float) finalNiazsanjiReportDTOs.stream()
                .filter(a -> a.getStatus() >= 20).mapToDouble(a -> a.getPassedValue()).sum());
            if(homePageReportDetail.getTotal() > 0)
                homePageReportDetail.setTotalPassedPercent((homePageReportDetail.getTotalPassed() / homePageReportDetail.getTotal()) * 100);
            else
                homePageReportDetail.setTotalPassedPercent(0f);

            homePageReportDetail.setTotalPassedManagers((float) finalNiazsanjiReportDTOs.stream()
                .filter(a -> a.isManager() && a.getStatus() >= 20).mapToDouble(a -> a.getPassedValue()).sum());

            if(homePageReportDetail.getTotalPassed() > 0)
                homePageReportDetail.setTotalPassedManagersPercent((homePageReportDetail.getTotalPassedManagers() / homePageReportDetail.getTotalPassed()) * 100);
            else
                homePageReportDetail.setTotalPassedManagersPercent(0f);

            homePageReportDetail.setTotalPassedStuffs((float) finalNiazsanjiReportDTOs.stream()
                .filter(a -> !a.isManager() && a.getStatus() >= 20).mapToDouble(a -> a.getPassedValue()).sum());

            if(homePageReportDetail.getTotalPassed() > 0)
                homePageReportDetail.setTotalPassedStuffsPercent((homePageReportDetail.getTotalPassedStuffs() / homePageReportDetail.getTotalPassed()) * 100);
            else
                homePageReportDetail.setTotalPassedStuffsPercent(0f);

            homePageReportDetail.setRemaining((float) finalNiazsanjiReportDTOs.stream()
                .filter(a -> a.getStatus() < 20).mapToDouble(a -> a.getValue()).sum());
            if(homePageReportDetail.getTotal() > 0)
                homePageReportDetail.setRemainingPercent((homePageReportDetail.getRemaining() / homePageReportDetail.getTotal()) * 100);
            else
                homePageReportDetail.setRemainingPercent(0f);

            homePageReportDetail.setRemainingManagers((float) finalNiazsanjiReportDTOs.stream()
                .filter(a -> a.getStatus() < 20 && a.isManager()).mapToDouble(a -> a.getValue()).sum());

            if(homePageReportDetail.getRemaining() > 0)
                homePageReportDetail.setRemainingManagersPercent((homePageReportDetail.getRemainingManagers() / homePageReportDetail.getRemaining()) * 100);
            else
                homePageReportDetail.setRemainingManagersPercent(0f);

            homePageReportDetail.setRemainingStuffs((float) finalNiazsanjiReportDTOs.stream()
                .filter(a -> a.getStatus() < 20 && !a.isManager()).mapToDouble(a -> a.getValue()).sum());
            if(homePageReportDetail.getRemaining() > 0)
                homePageReportDetail.setRemainingStuffsPercent((homePageReportDetail.getRemainingStuffs() / homePageReportDetail.getRemaining()) * 100);
            else
                homePageReportDetail.setRemainingStuffsPercent(0f);

            homePageReportDetails.add(homePageReportDetail);
        }
        homePageReport.setHomePageReportDetails(homePageReportDetails);

        List<HomePageReportOrganizationAndCourseTypeDetail> homePageReportOrganizationAndCourseTypeDetails = new ArrayList<>();

        for (CourseTypeDTO courseTypeDTO : courseTypeDTOS) {
            HomePageReportOrganizationAndCourseTypeDetail homePageReportOrganizationAndCourseTypeDetail = new HomePageReportOrganizationAndCourseTypeDetail();
            homePageReportOrganizationAndCourseTypeDetail.setCourseTypeId(courseTypeDTO.getId());
            homePageReportOrganizationAndCourseTypeDetail.setCourseTypeTitle(courseTypeDTO.getTitle());

            List<HomePageReportChartOrganizationDetail> homePageReportChartOrganizationDetails = new ArrayList<>();
            for (OrganizationChart group: groups) {
                HomePageReportChartOrganizationDetail homePageReportChartOrganizationDetail = new HomePageReportChartOrganizationDetail();
                homePageReportChartOrganizationDetail.setOrganizationChartId(group.getId());
                homePageReportChartOrganizationDetail.setOrganizationChartTitle(group.getTitle());

                List<Long> orgIds = this.getAllOfChilderenIdsOfThisId(organizationCharts, group.getId(), true);
                List<FinalNiazsanjiReportHomePageDTO> finalNiazsanjiReportDTOs = finalNiazsanjiReportHomePageDTOS.stream()
                    .filter(a -> orgIds.contains(a.getOrganizationChartId()) && a.getCourseTypeId()
                        .equals(courseTypeDTO.getId()) && a.getNiazsanjiYear().equals(niazsanjiYear)).collect(Collectors.toList());

                HomePageReportDetail homePageReportDetail = homePageReportDetails.stream().filter(a -> a.getOrganizationChartId() == group.getId()).collect(Collectors.toList()).get(0);

                homePageReportChartOrganizationDetail.setTotal((float) finalNiazsanjiReportDTOs.stream().mapToDouble(a -> a.getValue()).sum());
                if(homePageReportDetail.getTotal() > 0)
                    homePageReportChartOrganizationDetail.setTotalPercent((homePageReportChartOrganizationDetail.getTotal() / homePageReportDetail.getTotal()) * 100);
                else
                    homePageReportChartOrganizationDetail.setTotalPercent(0f);

                homePageReportChartOrganizationDetail.setPassed((float) finalNiazsanjiReportDTOs.stream().filter(a -> a.getStatus() >= 20).mapToDouble(a -> a.getPassedValue()).sum());
                if(homePageReportDetail.getTotal() > 0)
                    homePageReportChartOrganizationDetail.setPassedPercent((homePageReportChartOrganizationDetail.getPassed() / homePageReportDetail.getTotal()) * 100);
                else
                    homePageReportChartOrganizationDetail.setPassedPercent(0f);

                homePageReportChartOrganizationDetail.setRemaining((float) finalNiazsanjiReportDTOs.stream().filter(a -> a.getStatus() < 20).mapToDouble(a -> a.getValue()).sum());
                if(homePageReportDetail.getTotal() > 0)
                    homePageReportChartOrganizationDetail.setRemainingPercent((homePageReportChartOrganizationDetail.getRemaining() / homePageReportDetail.getTotal()) * 100);
                else
                    homePageReportChartOrganizationDetail.setRemainingPercent(0f);

                homePageReportChartOrganizationDetails.add(homePageReportChartOrganizationDetail);
            }
            homePageReportOrganizationAndCourseTypeDetail.setHomePageReportChartOrganizationDetails(homePageReportChartOrganizationDetails);
            homePageReportOrganizationAndCourseTypeDetails.add(homePageReportOrganizationAndCourseTypeDetail);
        }
        homePageReport.setHomePageReportOrganizationAndCourseTypeDetails(homePageReportOrganizationAndCourseTypeDetails);
        return  homePageReport;
    }
    public HomePageReport emptyHomePageReport(){
        return new HomePageReport(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f);
    }
    public Boolean isManager(String jobCode){
        if(jobCode.startsWith("18") || jobCode.startsWith("19") || jobCode.startsWith("20"))
            return true;
        return false;
    }
    public Boolean contains(List<Long> a, long[] b){
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < b.length; j++) {
                if(a.get(i) == b[j])
                    return true;
            }
        }
        return false;
    }
    @Override
    @Transactional(readOnly = true)
    public List<HomePagePersonEducationalModule> getHomePagePersonEducationalModules(Long personId) {
        log.debug("Request to get HomePagePersonEducationalModules by personId : {}", personId);
        Optional<Person> person = personRepository.findById(personId);
        List<HomePagePersonEducationalModule> report = new ArrayList<>();
        if(person.isPresent()){
            LongFilter personIdFilter = new LongFilter();
            personIdFilter.setEquals(personId);

            LongFilter jobIdFilter = new LongFilter();
            jobIdFilter.setEquals(person.get().getJob().getId());

            EducationalHistoryCriteria educationalHistoryCriteria = new EducationalHistoryCriteria();
            educationalHistoryCriteria.setPersonId(personIdFilter);

            EducationalHistoryCriteria.RequestStatusFilter requestStatusFilter = new EducationalHistoryCriteria.RequestStatusFilter();
            requestStatusFilter.setEquals(RequestStatus.ACCEPT);
            educationalHistoryCriteria.setRequestStatus(requestStatusFilter);

            List<EducationalHistoryDTO> educationalHistoryDTOS = educationalHistoryQueryService.findByCriteria(educationalHistoryCriteria);

            for (EducationalHistoryDTO educationalHistoryDTO : educationalHistoryDTOS) {
                Integer status = 100; //getEducationalModuleStatus(educationalModule, personIdFilter);

                Optional<EducationalModuleDTO> educationalModuleDTO = educationalModuleService.findOne(educationalHistoryDTO.getEducationalModuleId());
                if(educationalModuleDTO.isPresent()) {
                    HomePagePersonEducationalModule homePagePersonEducationalModule = new HomePagePersonEducationalModule(educationalHistoryDTO,
                        educationalModuleDTO.get(),
                        status);
                    report.add(homePagePersonEducationalModule);
                }
            }

            FinalNiazsanjiReportPersonCriteria finalNiazsanjiReportPersonCriteria = new FinalNiazsanjiReportPersonCriteria();
            finalNiazsanjiReportPersonCriteria.setPersonId(personIdFilter);

            List<FinalNiazsanjiReportPersonDTO> finalNiazsanjiReportPersonDTOS =
                finalNiazsanjiReportPersonQueryService.findByCriteria(finalNiazsanjiReportPersonCriteria);
            long[] finalIds = finalNiazsanjiReportPersonDTOS.stream().mapToLong(a -> a.getFinalNiazsanjiReportId()).toArray();

            if(finalIds.length > 0) {
                EducationalModuleType educationalModuleType = EducationalModuleType.ALL;
                FinalNiazsanjiReportPersonDTO niazsanji = finalNiazsanjiReportPersonDTOS.get(0);
                if(niazsanji.getNiazSanjiSource() == NiazSanjiSource.FARDI){
                    Optional<NiazsanjiFardiDTO> niazsanjiFardiDTO = niazsanjiFardiService.findOne(niazsanji.getSourceId());
                    if(niazsanjiFardiDTO.isPresent())
                        educationalModuleType = niazsanjiFardiDTO.get().getEducationalModuleType();
                }
                FinalNiazsanjiReportCriteria finalNiazsanjiReportCriteria = new FinalNiazsanjiReportCriteria();
                LongFilter finalIdFilter = new LongFilter();
                List<Long> listFinalIds = new ArrayList<>();

                for(long finalId: finalIds){
                    listFinalIds.add(finalId);
                }
                finalIdFilter.setIn(listFinalIds);
                finalNiazsanjiReportCriteria.setId(finalIdFilter);
                List<FinalNiazsanjiReportDTO> finalNiazsanjiReportDTOS = finalNiazsanjiReportQueryService.findByCriteria(finalNiazsanjiReportCriteria);
                for (FinalNiazsanjiReportDTO finalNiazsanjiReportDTO : finalNiazsanjiReportDTOS) {
                    //Integer status = getEducationalModuleStatus(finalNiazsanjiReportDTO, personIdFilter);
                    if(report.stream().filter(a -> a.getTitle().equals(finalNiazsanjiReportDTO.getEducationalModuleTitle())).count() == 0){
                        Optional<EducationalModuleDTO> educationalModuleDTO = educationalModuleService.findOne(finalNiazsanjiReportDTO.getEducationalModuleId());
                        if(educationalModuleDTO.isPresent()) {
                            HomePagePersonEducationalModule homePagePersonEducationalModule = new HomePagePersonEducationalModule(finalNiazsanjiReportDTO,
                                educationalModuleType,
                                educationalModuleDTO.get());
                            if(!(homePagePersonEducationalModule.getFinalNiazsanjiReportId() != null &&
                                homePagePersonEducationalModule.getFinalNiazsanjiReportId() == finalNiazsanjiReportDTO.getId()))
                                report.add(homePagePersonEducationalModule);
                        }
                    }
                }
            }
            EducationalModuleJobCriteria educationalModuleJobCriteria = new EducationalModuleJobCriteria();
            educationalModuleJobCriteria.setJobId(jobIdFilter);

            List<EducationalModuleJobDTO> educationalModuleJobDTOS = educationalModuleJobQueryService.findByCriteria(educationalModuleJobCriteria);
            long[] educationalIds = educationalModuleJobDTOS.stream().mapToLong(a -> a.getEducationalModuleId()).toArray();
            List<EducationalModuleMinDTO> educationalModules = educationalModuleService.findAllFromCache().stream().filter(a -> Arrays.stream(educationalIds).anyMatch(w -> w == a.getId())).collect(Collectors.toList());

            for (EducationalModuleMinDTO educationalModule : educationalModules) {
                if(report.stream().filter(a -> a.getTitle().equals(educationalModule.getTitle())).count() == 0) {
                    Integer status = 0; //getEducationalModuleStatus(educationalModule, personIdFilter);
                    HomePagePersonEducationalModule homePagePersonEducationalModule = new HomePagePersonEducationalModule(educationalModule, status);
                    report.add(homePagePersonEducationalModule);
                }
                else{
                    HomePagePersonEducationalModule homePagePersonEducationalModule = report.stream().filter(a -> a.getTitle().equals(educationalModule.getTitle())).findFirst().get();
                    report.remove(homePagePersonEducationalModule);
                    homePagePersonEducationalModule.setEducationalModuleType("نیازسنجی از شناسنامه شغلی");
                    report.add(homePagePersonEducationalModule);
                }
            }
        }
        return report;
    }

    private Integer getEducationalModuleStatus(EducationalModuleMinDTO educationalModule, LongFilter personIdFilter){

        LongFilter educationalModuleIdFilter = new LongFilter();
        educationalModuleIdFilter.setEquals(educationalModule.getId());

        EducationalHistoryCriteria educationalHistoryCriteria = new EducationalHistoryCriteria();
        educationalHistoryCriteria.setPersonId(personIdFilter);
        educationalHistoryCriteria.setEducationalModuleId(educationalModuleIdFilter);
        EducationalHistoryCriteria.RequestStatusFilter requestStatusFilter = new EducationalHistoryCriteria.RequestStatusFilter();
        requestStatusFilter.setEquals(RequestStatus.ACCEPT);
        educationalHistoryCriteria.setRequestStatus(requestStatusFilter);

        List<EducationalHistoryDTO> educationalHistoryDTOS = educationalHistoryQueryService.findByCriteria(educationalHistoryCriteria);
        if(!educationalHistoryDTOS.isEmpty())
            return 100;

        RunPhaseCriteria runPhaseCriteria = new RunPhaseCriteria();
        runPhaseCriteria.setPersonId(personIdFilter);
        runPhaseCriteria.setEducationalModuleId(educationalModuleIdFilter);

        List<RunPhaseDTO> runPhaseDTOS = runPhaseQueryService.findByCriteria(runPhaseCriteria);
        if(!runPhaseDTOS.isEmpty())
            return 90;

        DesignAndPlanningCriteria designAndPlanningCriteria = new DesignAndPlanningCriteria();
        designAndPlanningCriteria.setPersonId(personIdFilter);
        designAndPlanningCriteria.setEducationalModuleId(educationalModuleIdFilter);

        List<DesignAndPlanningDTO> designAndPlanningDTOS = designAndPlanningQueryService.findByCriteria(designAndPlanningCriteria);
        if(!designAndPlanningDTOS.isEmpty())
            return 80;

        return 0;
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
            long totalTime = finalNiazsanjiReportCustomDTOS.stream()
                .filter(a -> a.getId() == id)
                .mapToLong(o -> o.getTotalLearningTime()).sum();
            educationalModuleTotalHour = educationalModuleTotalHour + (count * totalTime);
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

        /*runPhaseService.deleteByFinalNiazsanjiReportId(id);
        designAndPlanningService.deleteByFinalNiazsanjiReportId(id);
        finalNiazsanjiReportPersonService.deleteByFinalNiazsanjiReportId(id);*/
        finalNiazsanjiReportRepository.deleteById(id);
        clearFinalNiazsanjiReportCaches();
    }

    @Override
    public List<FinalEffectivenessPhaseReportModel> getFinalEffectivenessPhaseReport(Integer reportYear, List<Long> organizationChartIds) {

        List<FinalNiazsanjiReport> finalNiazsanjiReports;

        if(organizationChartIds.isEmpty())
        {
            finalNiazsanjiReports = finalNiazsanjiReportRepository
                .findAllByNiazsanjiYearAndStatus(reportYear, 40);
        }
        else{
            finalNiazsanjiReports = finalNiazsanjiReportRepository
                .findAllByNiazsanjiYearAndStatusAndOrganizationChart(reportYear, 40, organizationChartIds);
        }

        long[] finalNiazsanjiReportPeople = finalNiazsanjiReports.stream().mapToLong(w -> w.getId()).toArray();

        List<CountListModel> countListModels = finalNiazsanjiReportPersonService
            .countListFinalNiazsanjiReportPeople(finalNiazsanjiReportPeople, true);

        List<FinalEffectivenessPhaseReportModel> finalEffectivenessPhaseReportModels = new ArrayList<>();

        List<DetailFinalEffectivenessPhaseReportModel> levelOneDetail = new ArrayList<>();
        List<DetailFinalEffectivenessPhaseReportModel> levelTwoDetail = new ArrayList<>();
        List<DetailFinalEffectivenessPhaseReportModel> levelThreeDetail = new ArrayList<>();
        List<DetailFinalEffectivenessPhaseReportModel> levelFourDetail = new ArrayList<>();

        for (FinalNiazsanjiReport finalNiazsanjiReport : finalNiazsanjiReports) {

            EffectivenessPhaseCriteria effectivenessPhaseCriteria = new EffectivenessPhaseCriteria();

            LongFilter finalNiazsanjiReportIdFilter = new LongFilter();
            finalNiazsanjiReportIdFilter.setEquals(finalNiazsanjiReport.getId());
            effectivenessPhaseCriteria.setFinalNiazsanjiReportId(finalNiazsanjiReportIdFilter);

            List<EffectivenessPhaseDTO> effectivenessPhases = effectivenessPhaseQueryService.findByCriteria(effectivenessPhaseCriteria);

            List<CountListModel> countListModels1 = countListModels.stream().filter(w -> w.getEntityId().equals(finalNiazsanjiReport.getId())).collect(Collectors.toList());
            CountListModel countListModel = countListModels1.get(0);


            for (EffectivenessPhaseDTO effectivenessPhase : effectivenessPhases) {
                DetailFinalEffectivenessPhaseReportModel detailFinalEffectivenessPhaseReportModel = new DetailFinalEffectivenessPhaseReportModel(finalNiazsanjiReport.getId(),
                    finalNiazsanjiReport.getEducationalModule().getTitle(),
                    finalNiazsanjiReport.getEffectivenessPhaseAverage(),
                    countListModel.getCount());

                switch (effectivenessPhase.getEffectivenessPhaseLevel().getEffectivenessLevel()){
                    case 1:
                        detailFinalEffectivenessPhaseReportModel.setEffectivenessLevel(1);
                        levelOneDetail.add(detailFinalEffectivenessPhaseReportModel);
                        break;
                    case 2:
                        detailFinalEffectivenessPhaseReportModel.setEffectivenessLevel(2);
                        levelTwoDetail.add(detailFinalEffectivenessPhaseReportModel);
                        break;
                    case 3:
                        detailFinalEffectivenessPhaseReportModel.setEffectivenessLevel(3);
                        levelThreeDetail.add(detailFinalEffectivenessPhaseReportModel);
                        break;
                    case 4:
                        detailFinalEffectivenessPhaseReportModel.setEffectivenessLevel(4);
                        levelFourDetail.add(detailFinalEffectivenessPhaseReportModel);
                        break;
                }
            }
        }
        // level one calculation
        finalEffectivenessPhaseReportModels.add(calculateFinalResult(levelOneDetail, 1));
        finalEffectivenessPhaseReportModels.add(calculateFinalResult(levelTwoDetail, 2));
        finalEffectivenessPhaseReportModels.add(calculateFinalResult(levelThreeDetail, 3));
        finalEffectivenessPhaseReportModels.add(calculateFinalResult(levelFourDetail, 4));

        return finalEffectivenessPhaseReportModels;
    }

    private FinalEffectivenessPhaseReportModel calculateFinalResult(List<DetailFinalEffectivenessPhaseReportModel> levelDetail, Integer levelId){
        long sumPeopleCount = levelDetail.stream().mapToLong(w -> w.getPeopleCount()).sum();

        float levelOneDetailSum = 0;
        for (DetailFinalEffectivenessPhaseReportModel levelOne : levelDetail) {
            levelOne.setFinalAverage((levelOne.getPeopleCount() * levelOne.getEffectivenessPhaseFinalResultPercent()) /
                sumPeopleCount);
            levelOneDetailSum += levelOne.getPeopleCount() * levelOne.getEffectivenessPhaseFinalResultPercent();
        }
        String levelTitle = getEffectivenessLevelTitle(levelId);
        Float averageEffectiveness = levelOneDetailSum / sumPeopleCount;
        return new FinalEffectivenessPhaseReportModel(
            levelId,
            levelTitle,
            (long) levelDetail.size(),
            averageEffectiveness,
            levelDetail
        );
    }

    private String getEffectivenessLevelTitle(int levelId) {
        EffectivenessPhaseLevelCriteria criteria = new EffectivenessPhaseLevelCriteria();
        IntegerFilter effectivenessLevelFilter = new IntegerFilter();
        effectivenessLevelFilter.setEquals(levelId);
        criteria.setEffectivenessLevel(effectivenessLevelFilter);
        List<EffectivenessPhaseLevelDTO>  effectivenessPhaseLevels = effectivenessPhaseLevelQueryService.findByCriteria(criteria);

        return effectivenessPhaseLevels.get(0).getTitle();
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
