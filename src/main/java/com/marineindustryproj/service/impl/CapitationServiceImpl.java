package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.OrganizationChart;
import com.marineindustryproj.domain.Person;
import com.marineindustryproj.domain.enumeration.RequestStatus;
import com.marineindustryproj.repository.FinalOrganizationNiazsanjiRepository;
import com.marineindustryproj.repository.NiazsanjiIntegrationRepository;
import com.marineindustryproj.repository.OrganizationChartRepository;
import com.marineindustryproj.service.*;
import com.marineindustryproj.domain.Capitation;
import com.marineindustryproj.repository.CapitationRepository;
import com.marineindustryproj.service.dto.*;
import com.marineindustryproj.service.dto.customs.CapitationReportModels.*;
import com.marineindustryproj.service.mapper.CapitationMapper;
import io.github.jhipster.service.filter.IntegerFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Capitation.
 */
@Service
@Transactional
public class CapitationServiceImpl implements CapitationService {

    private final Logger log = LoggerFactory.getLogger(CapitationServiceImpl.class);

    private final CapitationRepository capitationRepository;

    private final CapitationMapper capitationMapper;

    private final CourseTypeService courseTypeService;

    private final EducationalModuleService educationalModuleService;

    private final OrganizationChartService organizationChartService;

    private final OrganizationChartRepository organizationChartRepository;

    private final NiazsanjiIntegrationRepository niazsanjiIntegrationRepository;

    private final FinalOrganizationNiazsanjiRepository finalOrganizationNiazsanjiRepository;

    private final PersonService personService;

    private final CapitationQueryService capitationQueryService;

    private final FinalOrganizationNiazsanjiQueryService finalOrganizationNiazsanjiQueryService;

    public CapitationServiceImpl(CapitationRepository capitationRepository, CapitationMapper capitationMapper, CourseTypeService courseTypeService, EducationalModuleService educationalModuleService, OrganizationChartService organizationChartService, OrganizationChartRepository organizationChartRepository, NiazsanjiIntegrationRepository niazsanjiIntegrationRepository, FinalOrganizationNiazsanjiRepository finalOrganizationNiazsanjiRepository, PersonService personService, CapitationQueryService capitationQueryService, FinalOrganizationNiazsanjiQueryService finalOrganizationNiazsanjiQueryService) {
        this.capitationRepository = capitationRepository;
        this.capitationMapper = capitationMapper;
        this.courseTypeService = courseTypeService;
        this.educationalModuleService = educationalModuleService;
        this.organizationChartService = organizationChartService;
        this.organizationChartRepository = organizationChartRepository;
        this.niazsanjiIntegrationRepository = niazsanjiIntegrationRepository;
        this.finalOrganizationNiazsanjiRepository = finalOrganizationNiazsanjiRepository;
        this.personService = personService;
        this.capitationQueryService = capitationQueryService;
        this.finalOrganizationNiazsanjiQueryService = finalOrganizationNiazsanjiQueryService;
    }

    /**
     * Save a capitation.
     *
     * @param capitationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CapitationDTO save(CapitationDTO capitationDTO) {
        log.debug("Request to save Capitation : {}", capitationDTO);

        Capitation capitation = capitationMapper.toEntity(capitationDTO);
        capitation = capitationRepository.save(capitation);
        return capitationMapper.toDto(capitation);
    }

    /**
     * Get all the capitations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CapitationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Capitations");
        return capitationRepository.findAll(pageable)
            .map(capitationMapper::toDto);
    }


    /**
     * Get one capitation by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CapitationDTO> findOne(Long id) {
        log.debug("Request to get Capitation : {}", id);
        return capitationRepository.findById(id)
            .map(capitationMapper::toDto);
    }

    /**
     * Delete the capitation by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Capitation : {}", id);
        capitationRepository.deleteById(id);
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

    public Boolean contains(List<Long> a, long b){
        for (int i = 0; i < a.size(); i++) {
            if(a.get(i) == b)
                return true;
        }
        return false;
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
    @Override
    @Transactional(readOnly = true)
    public CapitationReport getCapitationReport(Integer niazsanjiYear, long organizationChartId) throws Exception {
        log.debug("Request to get HomePageReport");
        CapitationReport capitationReport = new CapitationReport();
        IntegerFilter yearFilter = new IntegerFilter();
        yearFilter.setEquals(niazsanjiYear);

        CapitationDTO capitationDTO;
        try {
            CapitationCriteria capitationCriteria = new CapitationCriteria();

            capitationCriteria.setCapitationYear(yearFilter);
            List<CapitationDTO> capitationDTOS = capitationQueryService.findByCriteria(capitationCriteria);
            if (capitationDTOS == null || capitationDTOS.size() == 0)
                throw new Exception("Capitation not found...");

            capitationDTO = capitationDTOS.get(0);
        }
        catch (Exception ex){
            log.error(ex.getMessage());
            throw new Exception("Capitation Error");
        }

        //List<CourseTypeDTO> courseTypeDTOS = courseTypeService.findAll();
        List<IndustryChildModel> industryChildModels;
        List<OrganizationChart> groups;
        try {
            List<OrganizationChart> organizationCharts = organizationChartRepository.findAllWithoutCache();
            groups = organizationCharts.stream().filter(a -> a.getParent() == null).sorted(Comparator.comparing(OrganizationChart::getId)).collect(Collectors.toList());
            if (organizationChartId > 0) {
                groups = groups.stream().filter(w -> w.getId() == organizationChartId).collect(Collectors.toList());
            }

            industryChildModels = new ArrayList<>();
            for (OrganizationChart group : groups) {
                //List<OrganizationChart> industries = organizationCharts.stream().filter(w -> w.getParent().getId() > 0 && w.getParent().getId() == group.getId()).collect(Collectors.toList());
                List<OrganizationChart> industries = new ArrayList<>();
                List<String> industriesCodes = new ArrayList<>();
                for (OrganizationChart organizationChart : organizationCharts) {
                    if (organizationChart.getParent() != null && organizationChart.getParent().getId() == group.getId()) {
                        if (!industriesCodes.stream().anyMatch(w -> w.equals(organizationChart.getCode())))
                            industriesCodes.add(organizationChart.getCode());
                        industries.add(organizationChart);
                    }
                }
                //List<String> industriesCodes = industries.stream().map(w -> w.getCode()).distinct().collect(Collectors.toList());

                for (String industriesCode : industriesCodes) {
                    List<OrganizationChart> selectedIndustries = industries.stream().filter(w -> w.getCode() == industriesCode).collect(Collectors.toList());
                    List<Long> orgLongList = new ArrayList<>();
                    for (OrganizationChart selectedIndustry : selectedIndustries) {
                        orgLongList.addAll(getAllOfChilderenIdsOfThisId(organizationCharts, selectedIndustry.getId(), true));
                    }
                    if (group.getCode() == industriesCode)
                        orgLongList.add(group.getId());

                    industryChildModels.add(new IndustryChildModel(
                        group.getId(), group.getTitle(), industriesCode, industriesCode, orgLongList
                    ));
                }
            }
        }
        catch (Exception ex){
            log.error("chart Error: " + ex.getMessage());
            throw new Exception("chart Error: " + ex.getMessage());
        }
        List<PersonDTO> people;
        try {
            people = personService.findAllWithChart();
        }
        catch (Exception ex){
            log.error("people: " + ex.getMessage());
            throw new Exception("people: " + ex.getMessage());
        }
        List<NiazsanjiFardiSummaryDTO> niazsanjiFardiSummaryDTOS;
        try {
            niazsanjiFardiSummaryDTOS = niazsanjiIntegrationRepository.findAllSummaryByNiazsanjiYear(niazsanjiYear);
        }
        catch (Exception ex){
            log.error("niazsanjiFardiSummaryDTOS: " + ex.getMessage());
            throw new Exception("niazsanjiFardiSummaryDTOS: " + ex.getMessage());
        }
        List<FinalOrganizationNiazsanjiDTO> finalOrganizationNiazsanjiDTOS;
        try {
            FinalOrganizationNiazsanjiCriteria finalOrganizationNiazsanjiCriteria = new FinalOrganizationNiazsanjiCriteria();
            finalOrganizationNiazsanjiCriteria.setNiazsanjiYear(yearFilter);
            FinalOrganizationNiazsanjiCriteria.RequestStatusFilter requestStatusFilter = new FinalOrganizationNiazsanjiCriteria.RequestStatusFilter();
            List<RequestStatus> requestStatuses = Arrays.asList(RequestStatus.ACCEPT, RequestStatus.NEW, RequestStatus.READ, RequestStatus.RETURNED);
            requestStatusFilter.setIn(requestStatuses);
            finalOrganizationNiazsanjiCriteria.setRequestStatus(requestStatusFilter);

            finalOrganizationNiazsanjiDTOS = finalOrganizationNiazsanjiQueryService.findByCriteria(finalOrganizationNiazsanjiCriteria);
        }
        catch (Exception ex){
            log.error("finalOrganizationNiazsanji: " + ex.getMessage());
            throw new Exception("finalOrganizationNiazsanji: " + ex.getMessage());
        }

        List<NiazsanjiOrganizationSummaryDTO> niazsanjiOrganizationSummaryDTOS = new ArrayList<>();
        try {
            for (FinalOrganizationNiazsanjiDTO finalOrganizationNiazsanjiDTO : finalOrganizationNiazsanjiDTOS) {
                Optional<CourseTypeDTO> courseTypeDTOOptional = courseTypeService.findOne(finalOrganizationNiazsanjiDTO.getCourseTypeId());
                if (!courseTypeDTOOptional.isPresent())
                    continue;

                Optional<EducationalModuleDTO> educationalModuleDTOOptional = educationalModuleService.findOne(finalOrganizationNiazsanjiDTO.getEducationalModuleId());
                if (!educationalModuleDTOOptional.isPresent())
                    continue;

                Optional<OrganizationChartDTO> organizationChartDTOOptional = organizationChartService.findOne(finalOrganizationNiazsanjiDTO.getOrganizationChartId());
                if (!organizationChartDTOOptional.isPresent())
                    continue;

                NiazsanjiOrganizationSummaryDTO niazsanjiOrganizationSummaryDTO =
                    new NiazsanjiOrganizationSummaryDTO(finalOrganizationNiazsanjiDTO.getNiazsanjiYear(), finalOrganizationNiazsanjiDTO.getPriceCost(),
                        finalOrganizationNiazsanjiDTO.getStatus(), finalOrganizationNiazsanjiDTO.getRequestStatus(),
                        courseTypeDTOOptional.get(), educationalModuleDTOOptional.get(), organizationChartDTOOptional.get(),
                        finalOrganizationNiazsanjiDTO.getPeople());
                niazsanjiOrganizationSummaryDTOS.add(niazsanjiOrganizationSummaryDTO);
            }
        }
        catch (Exception ex){
            log.error("niazsanjiOrganizationSummaryDTOS " + ex.getMessage());
            throw new Exception("niazsanjiOrganizationSummaryDTOS: " + ex.getMessage());
        }

        List<CapitationReportDetailItem> capitationReportDetailItems = new ArrayList<>();


        try {
            for (IndustryChildModel industryChildModel : industryChildModels) {
                CapitationReportDetailItem capitationReportDetailItem = new CapitationReportDetailItem();
                capitationReportDetailItem.setGroupId(industryChildModel.getGroupId());
                capitationReportDetailItem.setGroupTitle(industryChildModel.getGroupName());
                capitationReportDetailItem.setIndustryTitle(industryChildModel.getIndustryTitle());

                List<PersonDTO> selectedPeople = people.stream().filter(w -> contains(industryChildModel.getIndustryOrganizationChartIds(), w.getOrganizationChartId())).collect(Collectors.toList());
                List<PersonDTO> bossPeople = selectedPeople.stream().filter(w -> isManager(w.getJobCode())).collect(Collectors.toList());
                List<PersonDTO> expertPeople = selectedPeople.stream().filter(w -> !isManager(w.getJobCode())).collect(Collectors.toList());

                //Employees
                capitationReportDetailItem.setTotalEmployee(selectedPeople.size());
                capitationReportDetailItem.setTotalBoss(bossPeople.size());
                capitationReportDetailItem.setTotalExpert(expertPeople.size());

                //Capitation
                capitationReportDetailItem.setAvgLearningTimeExpert((float) expertPeople.size() * capitationDTO.getEmployeeMaximumAllowablePersonHours());
                capitationReportDetailItem.setAvgLearningTimeBoss((float) bossPeople.size() * capitationDTO.getBossMaximumAllowablePersonHours());
                capitationReportDetailItem.setMaximumAllowablePersonHour(
                    capitationReportDetailItem.getAvgLearningTimeExpert() + capitationReportDetailItem.getAvgLearningTimeBoss());
            /*capitationReportDetailItem.setMaximumAllowableCost(((float) expertPeople.size() * capitationDTO.getEmployeeMaximumAllowablePersonCosts())
                + ((float) bossPeople.size() * capitationDTO.getBossMaximumAllowablePersonCosts()));*/
                capitationReportDetailItem.setMaximumAllowableCost(capitationReportDetailItem.getMaximumAllowablePersonHour() * capitationDTO.getBossMaximumAllowablePersonCosts());

                List<NiazsanjiFardiSummaryDTO> niazsanjiFardi = niazsanjiFardiSummaryDTOS.stream()
                    .filter(w -> contains(industryChildModel.getIndustryOrganizationChartIds(), w.getOrganizationChart().getId())).collect(Collectors.toList());
                List<NiazsanjiOrganizationSummaryDTO> niazsanjiOrganization = niazsanjiOrganizationSummaryDTOS.stream()
                    .filter(w -> contains(industryChildModel.getIndustryOrganizationChartIds(), w.getOrganizationChart().getId())).collect(Collectors.toList());
                //HSE
                List<NiazsanjiFardiSummaryDTO> hseNiazsanjiFardi = niazsanjiFardi.stream().filter(w -> w.getCourseType().getCode() == "HSE").collect(Collectors.toList());
                List<NiazsanjiOrganizationSummaryDTO> hseNiazsanjiOrganization =
                    niazsanjiOrganization.stream().filter(w -> w.getCourseType().getCode() == "HSE").collect(Collectors.toList());
                capitationReportDetailItem.setHseNumberOfLearner(hseNiazsanjiFardi.size() +
                    (hseNiazsanjiOrganization.size() * hseNiazsanjiOrganization.stream().map(e -> e.getPeople()).collect(Collectors.toList()).size()));

                long sumNiazsanjiFardiHSEPersonHour = hseNiazsanjiFardi.stream().mapToInt(w -> w.getPersonHour()).sum();
                long sumNiazsanjiOrganizationHSEPersonHour = hseNiazsanjiOrganization.stream().mapToLong(w -> w.getTotalPersonHour()).sum();

                capitationReportDetailItem.setHseCourseTypePersonHour(
                    sumNiazsanjiFardiHSEPersonHour + sumNiazsanjiOrganizationHSEPersonHour);

                double sumNiazsanjiFardiHSECost = hseNiazsanjiFardi.stream().mapToDouble(w -> w.getCostEducationalModule()).sum();
                double sumNiazsanjiOrganizationHSECost = hseNiazsanjiOrganization.stream().mapToDouble(w -> w.getPriceCost()).sum();

                capitationReportDetailItem.setHseCourseTypeCost(
                    sumNiazsanjiFardiHSECost + sumNiazsanjiOrganizationHSECost);

                //short times
                long sumNiazsanjiFardiShortTimeBossShare = niazsanjiFardi.stream().filter(w -> isManager(w.getPerson().getJob().getJobCode())).mapToInt(w -> w.getPersonHour()).sum();
                //List<Set<Person>> peopleList = niazsanjiOrganization.stream().map(w -> w.getPeople()).collect(Collectors.toList());
                List<PersonDTO> organizationBossList = new ArrayList<>();
                List<PersonDTO> organizationExpertList = new ArrayList<>();

                long sumNiazsanjiOrganizationShortTimeBossShare = 0;
                long sumNiazsanjiOrganizationShortTimeExpertShare = 0;

                for (NiazsanjiOrganizationSummaryDTO niazsanjiOrganizationSummaryDTO : niazsanjiOrganization) {
                    for (PersonDTO person : niazsanjiOrganizationSummaryDTO.getPeople()) {
                        if (isManager(person.getJobCode())) {
                            organizationBossList.add(person);
                            sumNiazsanjiOrganizationShortTimeBossShare += niazsanjiOrganizationSummaryDTO.getPersonHour();
                        } else {
                            organizationExpertList.add(person);
                            sumNiazsanjiOrganizationShortTimeExpertShare += niazsanjiOrganizationSummaryDTO.getPersonHour();
                        }
                    }
                }
                capitationReportDetailItem.setShortTimeBossShare(
                    sumNiazsanjiFardiShortTimeBossShare + sumNiazsanjiOrganizationShortTimeBossShare);

                long sumNiazsanjiFardiShortTimeExpertShare = niazsanjiFardi.stream().filter(w -> !isManager(w.getPerson().getJob().getJobCode())).mapToInt(w -> w.getPersonHour()).sum();
                capitationReportDetailItem.setShortTimeExpertShare(
                    sumNiazsanjiFardiShortTimeExpertShare + sumNiazsanjiOrganizationShortTimeExpertShare);

                //predicates Person Hour
                long niazsanjiFardiPredicateBeforeAcceptPersonHour = niazsanjiFardi.stream().filter(w -> w.getRequestStatus() != RequestStatus.ACCEPT).mapToInt(w -> w.getPersonHour()).sum();
                long niazsanjiOrganizationPredicateBeforeAcceptPersonHour = niazsanjiOrganization.stream().filter(w -> w.getRequestStatus() != RequestStatus.ACCEPT).mapToLong(w -> w.getTotalPersonHour()).sum();
                capitationReportDetailItem.setPredicateBeforeAcceptPersonHour(niazsanjiFardiPredicateBeforeAcceptPersonHour + niazsanjiOrganizationPredicateBeforeAcceptPersonHour);

                long niazsanjiFardiPredicateAfterAcceptPersonHour = niazsanjiFardi.stream().filter(w -> w.getRequestStatus() == RequestStatus.ACCEPT).mapToInt(w -> w.getPersonHour()).sum();
                long niazsanjiOrganizationPredicateAfterAcceptPersonHour = niazsanjiOrganization.stream().filter(w -> w.getRequestStatus() == RequestStatus.ACCEPT).mapToLong(w -> w.getTotalPersonHour()).sum();
                capitationReportDetailItem.setPredicateBeforeAcceptPersonHour(niazsanjiFardiPredicateAfterAcceptPersonHour + niazsanjiOrganizationPredicateAfterAcceptPersonHour);

                capitationReportDetailItem.setPredicatePersonHourTotal(capitationReportDetailItem.getPredicateBeforeAcceptPersonHour() +
                    capitationReportDetailItem.getPredicateAfterAcceptPersonHour());

                capitationReportDetailItem.setPercentNiazsanjiProgramToMaximumPersonHour(
                    (capitationReportDetailItem.getPredicatePersonHourTotal() /
                        (capitationDTO.getEmployeeMaximumAllowablePersonHours() + capitationDTO.getBossMaximumAllowablePersonHours())
                        * 100)
                );

                //predicates Cost
                double niazsanjiFardiPredicateBeforeAcceptCost = niazsanjiFardi.stream().filter(w -> w.getRequestStatus() != RequestStatus.ACCEPT).mapToDouble(w -> w.getCostEducationalModule()).sum();
                double niazsanjiOrganizationPredicateBeforeAcceptCost = niazsanjiOrganization.stream().filter(w -> w.getRequestStatus() != RequestStatus.ACCEPT).mapToDouble(w -> w.getPriceCost()).sum();
                capitationReportDetailItem.setPredicateBeforeAcceptCost(niazsanjiFardiPredicateBeforeAcceptCost + niazsanjiOrganizationPredicateBeforeAcceptCost);

                double niazsanjiFardiPredicateAfterAcceptCost = niazsanjiFardi.stream().filter(w -> w.getRequestStatus() == RequestStatus.ACCEPT).mapToDouble(w -> w.getCostEducationalModule()).sum();
                double niazsanjiOrganizationPredicateAfterAcceptCost = niazsanjiOrganization.stream().filter(w -> w.getRequestStatus() == RequestStatus.ACCEPT).mapToDouble(w -> w.getPriceCost()).sum();
                capitationReportDetailItem.setPredicateBeforeAcceptCost(niazsanjiFardiPredicateAfterAcceptCost + niazsanjiOrganizationPredicateAfterAcceptCost);

                capitationReportDetailItem.setPredicateCostTotal(capitationReportDetailItem.getPredicateBeforeAcceptCost() +
                    capitationReportDetailItem.getPredicateAfterAcceptCost());

                capitationReportDetailItem.setPercentNiazsanjiProgramToMaximumCost(
                    (capitationReportDetailItem.getPredicateCostTotal() /
                        (capitationDTO.getEmployeeMaximumAllowablePersonCosts() + capitationDTO.getBossMaximumAllowablePersonCosts())
                        * 100)
                );

                //organization + courseType MOT
                capitationReportDetailItem.setOrganizationPredicatePersonHour(niazsanjiOrganization.stream().filter(w -> w.getCourseType().getCode() == "MOT").mapToLong(w -> w.getTotalPersonHour()).sum());
                capitationReportDetailItem.setOrganizationPredicateCost(niazsanjiOrganization.stream().filter(w -> w.getCourseType().getCode() == "MOT").mapToDouble(w -> w.getPriceCost()).sum());

                //organization + Fardi + courseType AGH
                long organizationAGHPersonHour = niazsanjiOrganization.stream().filter(w -> w.getCourseType().getCode() == "AGH").mapToLong(w -> w.getTotalPersonHour()).sum();
                long fardiAGHPersonHour = niazsanjiFardi.stream().filter(w -> w.getCourseType().getCode() == "AGH").mapToLong(w -> w.getPersonHour()).sum();

                capitationReportDetailItem.setInformingPersonHour(
                    organizationAGHPersonHour + fardiAGHPersonHour
                );

                double organizationAGHCost = niazsanjiOrganization.stream().filter(w -> w.getCourseType().getCode() == "AGH").mapToDouble(w -> w.getPriceCost()).sum();
                double fardiAGHCost = niazsanjiFardi.stream().filter(w -> w.getCourseType().getCode() == "AGH").mapToDouble(w -> w.getCostEducationalModule()).sum();
                capitationReportDetailItem.setInformingCost(
                    organizationAGHCost + fardiAGHCost
                );

                //organization + Fardi + courseType PODH
                long organizationPODHPersonHour = niazsanjiOrganization.stream().filter(w -> w.getCourseType().getCode() == "PODH").mapToLong(w -> w.getTotalPersonHour()).sum();
                long fardiPODHPersonHour = niazsanjiFardi.stream().filter(w -> w.getCourseType().getCode() == "PODH").mapToLong(w -> w.getPersonHour()).sum();

                capitationReportDetailItem.setSecurityPersonHour(
                    organizationPODHPersonHour + fardiPODHPersonHour
                );

                double organizationPODHCost = niazsanjiOrganization.stream().filter(w -> w.getCourseType().getCode() == "PODH").mapToDouble(w -> w.getPriceCost()).sum();
                double fardiPODHCost = niazsanjiFardi.stream().filter(w -> w.getCourseType().getCode() == "PODH").mapToDouble(w -> w.getCostEducationalModule()).sum();
                capitationReportDetailItem.setSecurityCost(
                    organizationPODHCost + fardiPODHCost
                );

                //organization + Fardi + courseType PODH + AGH
                long niazsanjiFardiSecurityAndInformingNumberOfLearner = niazsanjiFardi.stream().filter(w -> w.getCourseType().getCode() == "PODH" || w.getCourseType().getCode() == "AGH").count();
                long niazsanjiOrganizationSecurityAndInformingNumberOfLearner =
                    niazsanjiOrganization.stream().filter(w -> w.getCourseType().getCode() == "PODH" || w.getCourseType().getCode() == "AGH").mapToLong(w -> w.getPeople().size()).sum();

                capitationReportDetailItem.setSecurityAndInformingNumberOfLearner(
                    niazsanjiFardiSecurityAndInformingNumberOfLearner + niazsanjiOrganizationSecurityAndInformingNumberOfLearner
                );

                capitationReportDetailItem.setSecurityAndInformingPersonHour(
                    capitationReportDetailItem.getSecurityPersonHour()
                        +
                        capitationReportDetailItem.getInformingPersonHour()
                );
                capitationReportDetailItem.setSecurityAndInformingCost(
                    capitationReportDetailItem.getSecurityCost()
                        +
                        capitationReportDetailItem.getInformingCost()
                );

                capitationReportDetailItems.add(capitationReportDetailItem);
            }
        }
        catch (Exception ex){
            log.error("capitationReportDetailItem: " + ex.getMessage());
            throw new Exception("capitationReportDetailItem: " + ex.getMessage());
        }
        List<CapitationReportDetail> capitationReportDetails = new ArrayList<>();
        try {
            for (OrganizationChart group : groups) {
                CapitationReportDetail capitationReportDetail = new CapitationReportDetail();
                capitationReportDetail.setOrganizationChartTitle(group.getTitle());
                capitationReportDetail.setOrganizationChartId(group.getId());

                List<CapitationReportDetailItem> capitationReportDetailItemList = capitationReportDetailItems.stream()
                    .filter(w -> w.getGroupId() == group.getId()).collect(Collectors.toList());

                capitationReportDetail.setCapitationReportDetailItems(capitationReportDetailItemList);

                //Employees
                capitationReportDetail.setTotalEmployee(capitationReportDetailItemList.stream().mapToLong(w -> w.getTotalEmployee()).sum());
                capitationReportDetail.setTotalBoss(capitationReportDetailItemList.stream().mapToLong(w -> w.getTotalBoss()).sum());
                capitationReportDetail.setTotalExpert(capitationReportDetailItemList.stream().mapToLong(w -> w.getTotalExpert()).sum());

                //Capitation
                capitationReportDetail.setAvgLearningTimeExpert(capitationReportDetailItemList.stream().mapToDouble(w -> w.getAvgLearningTimeExpert()).sum());
                capitationReportDetail.setAvgLearningTimeBoss(capitationReportDetailItemList.stream().mapToDouble(w -> w.getAvgLearningTimeBoss()).sum());
                capitationReportDetail.setMaximumAllowablePersonHour(capitationReportDetailItemList.stream().mapToDouble(w -> w.getMaximumAllowablePersonHour()).sum());
                capitationReportDetail.setMaximumAllowableCost(capitationReportDetailItemList.stream().mapToDouble(w -> w.getMaximumAllowableCost()).sum());

                //HSE
                capitationReportDetail.setHseNumberOfLearner(capitationReportDetailItemList.stream().mapToLong(w -> w.getHseNumberOfLearner()).sum());
                capitationReportDetail.setHseCourseTypePersonHour(capitationReportDetailItemList.stream().mapToLong(w -> w.getHseCourseTypePersonHour()).sum());
                capitationReportDetail.setHseCourseTypeCost(capitationReportDetailItemList.stream().mapToDouble(w -> w.getHseCourseTypeCost()).sum());

                //short times
                capitationReportDetail.setShortTimeBossShare(capitationReportDetailItemList.stream().mapToDouble(w -> w.getShortTimeBossShare()).sum());
                capitationReportDetail.setShortTimeExpertShare(capitationReportDetailItemList.stream().mapToDouble(w -> w.getShortTimeExpertShare()).sum());

                capitationReportDetail.setPredicateBeforeAcceptPersonHour(capitationReportDetailItemList.stream()
                    .mapToLong(w -> w.getPredicateBeforeAcceptPersonHour()).sum());
                capitationReportDetail.setPredicateAfterAcceptPersonHour(capitationReportDetailItemList.stream()
                    .mapToLong(w -> w.getPredicateAfterAcceptPersonHour()).sum());
                capitationReportDetail.setPredicatePersonHourTotal(capitationReportDetailItemList.stream()
                    .mapToLong(w -> w.getPredicatePersonHourTotal()).sum());

                capitationReportDetail.setPredicateBeforeAcceptCost(capitationReportDetailItemList.stream()
                    .mapToDouble(w -> w.getPredicateBeforeAcceptCost()).sum());
                capitationReportDetail.setPredicateAfterAcceptCost(capitationReportDetailItemList.stream()
                    .mapToDouble(w -> w.getPredicateAfterAcceptCost()).sum());
                capitationReportDetail.setPredicateCostTotal(capitationReportDetailItemList.stream()
                    .mapToDouble(w -> w.getPredicateCostTotal()).sum());

                capitationReportDetail.setPercentNiazsanjiProgramToMaximumPersonHour(capitationReportDetailItemList.stream()
                    .mapToDouble(w -> w.getPercentNiazsanjiProgramToMaximumPersonHour()).sum());
                capitationReportDetail.setPercentNiazsanjiProgramToMaximumCost(capitationReportDetailItemList.stream()
                    .mapToDouble(w -> w.getPercentNiazsanjiProgramToMaximumCost()).sum());

                //Organization Niazsanjis
                capitationReportDetail.setOrganizationPredicatePersonHour(capitationReportDetailItemList.stream()
                    .mapToLong(w -> w.getOrganizationPredicatePersonHour()).sum());
                capitationReportDetail.setOrganizationPredicateCost(capitationReportDetailItemList.stream()
                    .mapToDouble(w -> w.getOrganizationPredicateCost()).sum());

                //securityAndInforming
                capitationReportDetail.setSecurityAndInformingNumberOfLearner(capitationReportDetailItemList.stream()
                    .mapToLong(w -> w.getSecurityAndInformingNumberOfLearner()).sum());
                capitationReportDetail.setSecurityAndInformingPersonHour(capitationReportDetailItemList.stream()
                    .mapToDouble(w -> w.getSecurityAndInformingPersonHour()).sum());
                capitationReportDetail.setSecurityAndInformingCost(capitationReportDetailItemList.stream()
                    .mapToDouble(w -> w.getSecurityAndInformingCost()).sum());

                //Informing Courses
                capitationReportDetail.setInformingPersonHour(capitationReportDetailItemList.stream()
                    .mapToLong(w -> w.getInformingPersonHour()).sum());
                capitationReportDetail.setInformingCost(capitationReportDetailItemList.stream()
                    .mapToDouble(w -> w.getInformingCost()).sum());

                //Security
                capitationReportDetail.setSecurityPersonHour(capitationReportDetailItemList.stream()
                    .mapToLong(w -> w.getSecurityPersonHour()).sum());
                capitationReportDetail.setSecurityCost(capitationReportDetailItemList.stream()
                    .mapToDouble(w -> w.getSecurityCost()).sum());

                capitationReportDetails.add(capitationReportDetail);
            }
        }
        catch (Exception ex){
            log.error("capitationReportDetail: " + ex.getMessage());
            throw new Exception("capitationReportDetail: " + ex.getMessage());
        }
        try {
            capitationReport.setCapitationReportDetails(capitationReportDetails);
            //Employees
            capitationReport.setTotalEmployee(capitationReportDetails.stream().mapToLong(w -> w.getTotalEmployee()).sum());
            capitationReport.setTotalBoss(capitationReportDetails.stream().mapToLong(w -> w.getTotalBoss()).sum());
            capitationReport.setTotalExpert(capitationReportDetails.stream().mapToLong(w -> w.getTotalExpert()).sum());

            //Capitation
            capitationReport.setAvgLearningTimeExpert(capitationReportDetails.stream().mapToDouble(w -> w.getAvgLearningTimeExpert()).sum());
            capitationReport.setAvgLearningTimeBoss(capitationReportDetails.stream().mapToDouble(w -> w.getAvgLearningTimeBoss()).sum());
            capitationReport.setMaximumAllowablePersonHour(capitationReportDetails.stream().mapToDouble(w -> w.getMaximumAllowablePersonHour()).sum());
            capitationReport.setMaximumAllowableCost(capitationReportDetails.stream().mapToDouble(w -> w.getMaximumAllowableCost()).sum());

            //HSE
            capitationReport.setHseNumberOfLearner(capitationReportDetails.stream().mapToLong(w -> w.getHseNumberOfLearner()).sum());
            capitationReport.setHseCourseTypePersonHour(capitationReportDetails.stream().mapToLong(w -> w.getHseCourseTypePersonHour()).sum());
            capitationReport.setHseCourseTypeCost(capitationReportDetails.stream().mapToDouble(w -> w.getHseCourseTypeCost()).sum());

            //short times
            capitationReport.setShortTimeBossShare(capitationReportDetails.stream().mapToDouble(w -> w.getShortTimeBossShare()).sum());
            capitationReport.setShortTimeExpertShare(capitationReportDetails.stream().mapToDouble(w -> w.getShortTimeExpertShare()).sum());

            capitationReport.setPredicateBeforeAcceptPersonHour(capitationReportDetails.stream()
                .mapToLong(w -> w.getPredicateBeforeAcceptPersonHour()).sum());
            capitationReport.setPredicateAfterAcceptPersonHour(capitationReportDetails.stream()
                .mapToLong(w -> w.getPredicateAfterAcceptPersonHour()).sum());
            capitationReport.setPredicatePersonHourTotal(capitationReportDetails.stream()
                .mapToLong(w -> w.getPredicatePersonHourTotal()).sum());

            capitationReport.setPredicateBeforeAcceptCost(capitationReportDetails.stream()
                .mapToDouble(w -> w.getPredicateBeforeAcceptCost()).sum());
            capitationReport.setPredicateAfterAcceptCost(capitationReportDetails.stream()
                .mapToDouble(w -> w.getPredicateAfterAcceptCost()).sum());
            capitationReport.setPredicateCostTotal(capitationReportDetails.stream()
                .mapToDouble(w -> w.getPredicateCostTotal()).sum());

            capitationReport.setPercentNiazsanjiProgramToMaximumPersonHour(capitationReportDetails.stream()
                .mapToDouble(w -> w.getPercentNiazsanjiProgramToMaximumPersonHour()).sum());
            capitationReport.setPercentNiazsanjiProgramToMaximumCost(capitationReportDetails.stream()
                .mapToDouble(w -> w.getPercentNiazsanjiProgramToMaximumCost()).sum());

            //Organization Niazsanjis
            capitationReport.setOrganizationPredicatePersonHour(capitationReportDetails.stream()
                .mapToLong(w -> w.getOrganizationPredicatePersonHour()).sum());
            capitationReport.setOrganizationPredicateCost(capitationReportDetails.stream()
                .mapToDouble(w -> w.getOrganizationPredicateCost()).sum());

            //securityAndInforming
            capitationReport.setSecurityAndInformingNumberOfLearner(capitationReportDetails.stream()
                .mapToLong(w -> w.getSecurityAndInformingNumberOfLearner()).sum());
            capitationReport.setSecurityAndInformingPersonHour(capitationReportDetails.stream()
                .mapToDouble(w -> w.getSecurityAndInformingPersonHour()).sum());
            capitationReport.setSecurityAndInformingCost(capitationReportDetails.stream()
                .mapToDouble(w -> w.getSecurityAndInformingCost()).sum());

            //Informing Courses
            capitationReport.setInformingPersonHour(capitationReportDetails.stream()
                .mapToLong(w -> w.getInformingPersonHour()).sum());
            capitationReport.setInformingCost(capitationReportDetails.stream()
                .mapToDouble(w -> w.getInformingCost()).sum());

            //Security
            capitationReport.setSecurityPersonHour(capitationReportDetails.stream()
                .mapToLong(w -> w.getSecurityPersonHour()).sum());
            capitationReport.setSecurityCost(capitationReportDetails.stream()
                .mapToDouble(w -> w.getSecurityCost()).sum());
        }
        catch (Exception ex){
            log.error("capitationReport: " + ex.getMessage());
            throw new Exception("capitationReport: " + ex.getMessage());
        }
        return  capitationReport;
    }
}
