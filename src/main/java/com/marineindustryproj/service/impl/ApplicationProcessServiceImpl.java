package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.repository.*;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.ApplicationProcessRunStepQueryService;
import com.marineindustryproj.service.ApplicationProcessRunStepService;
import com.marineindustryproj.service.ApplicationProcessService;
import com.marineindustryproj.service.dto.ApplicationProcessDTO;
import com.marineindustryproj.service.dto.ApplicationProcessRunStepCriteria;
import com.marineindustryproj.service.dto.ApplicationProcessRunStepDTO;
import com.marineindustryproj.service.dto.customs.ApplicationProcessModels.ApplicationProcessSaveDataItemModel;
import com.marineindustryproj.service.dto.customs.ApplicationProcessModels.ApplicationProcessSaveDataModel;
import com.marineindustryproj.service.mapper.ApplicationProcessMapper;
import io.github.jhipster.service.filter.LongFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ApplicationProcess.
 */
@Service
@Transactional
public class ApplicationProcessServiceImpl implements ApplicationProcessService {

    private final Logger log = LoggerFactory.getLogger(ApplicationProcessServiceImpl.class);

    private final ApplicationProcessRepository applicationProcessRepository;

    private final ApplicationProcessMapper applicationProcessMapper;

    private final PersonRepository personRepository;

    private final OrganizationChartRepository organizationChartRepository;

    private final ApplicationProcessRunStepQueryService applicationProcessRunStepQueryService;

    private final ApplicationProcessRunStepService applicationProcessRunStepService;

    private final EducationalRecordRepository educationalRecordRepository;

    private final QualificationRepository qualificationRepository;

    public ApplicationProcessServiceImpl(ApplicationProcessRepository applicationProcessRepository,
                                         ApplicationProcessMapper applicationProcessMapper, PersonRepository personRepository, OrganizationChartRepository organizationChartRepository, ApplicationProcessRunStepQueryService applicationProcessRunStepQueryService, ApplicationProcessRunStepService applicationProcessRunStepService, EducationalRecordRepository educationalRecordRepository, QualificationRepository qualificationRepository) {
        this.applicationProcessRepository = applicationProcessRepository;
        this.applicationProcessMapper = applicationProcessMapper;
        this.personRepository = personRepository;
        this.organizationChartRepository = organizationChartRepository;
        this.applicationProcessRunStepQueryService = applicationProcessRunStepQueryService;
        this.applicationProcessRunStepService = applicationProcessRunStepService;
        this.educationalRecordRepository = educationalRecordRepository;
        this.qualificationRepository = qualificationRepository;
    }

    /**
     * Save a applicationProcess.
     *
     * @param applicationProcessDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ApplicationProcessDTO save(ApplicationProcessDTO applicationProcessDTO) {
        log.debug("Request to save ApplicationProcess : {}", applicationProcessDTO);

        ApplicationProcess applicationProcess = applicationProcessMapper.toEntity(applicationProcessDTO);
        applicationProcess = applicationProcessRepository.save(applicationProcess);
        return applicationProcessMapper.toDto(applicationProcess);
    }

    @Override
    public ApplicationProcessDTO saveDataModel(ApplicationProcessSaveDataModel applicationProcessSaveDataModel) {
        log.debug("Request to save ApplicationProcess : {}", applicationProcessSaveDataModel);

        ApplicationProcess applicationProcess = new ApplicationProcess();
        if (applicationProcessSaveDataModel.getApplicationProcessId() != null) {
            applicationProcess = applicationProcessRepository.getOne(applicationProcessSaveDataModel.getApplicationProcessId());
        } else {
            Person person = personRepository.getOne(applicationProcessSaveDataModel.getPersonId());
            applicationProcess.setPerson(person);

            OrganizationChart organizationChart = organizationChartRepository.getOne(applicationProcessSaveDataModel.getOrganizationChartId());
            applicationProcess.setOrganizationChart(organizationChart);

            applicationProcess.setArchived(false);
            applicationProcess.setGuid(UUID.randomUUID().toString());
            applicationProcess.setCreateDate(ZonedDateTime.now());
            applicationProcess.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
            applicationProcess.setConversation(applicationProcessSaveDataModel.getConversion());
            applicationProcess.setChartStatus(applicationProcessSaveDataModel.getChartStatus());
            applicationProcess.setBossStatus(applicationProcessSaveDataModel.getBossStatus());
            applicationProcess.setRequestStatus(applicationProcessSaveDataModel.getRequestStatus());
            applicationProcess.setHasImportantMessage(false);
        }


        applicationProcess.academicCommitmentsFulfilled(applicationProcessSaveDataModel.getAcademicCommitmentsFulfilled());
        applicationProcess.acceptedMajorAndField(applicationProcessSaveDataModel.getAcceptedMajorAndField());
        applicationProcess.acceptedUniversityAndDegree(applicationProcessSaveDataModel.getAcceptedUniversityAndDegree());
        applicationProcess.dateOfAcceptanceOfDegree(applicationProcessSaveDataModel.getDateOfAcceptanceOfDegree());
        applicationProcess.setGraduateDateOfNewCourse(applicationProcessSaveDataModel.getGraduateDateOfNewCourse());
        applicationProcess.setHaveUsedEducationalFacilities(applicationProcessSaveDataModel.getHaveUsedEducationalFacilities());
        applicationProcess.setHaveUsedEducationalFacilitiesDescription(applicationProcessSaveDataModel.getHaveUsedEducationalFacilitiesDescription());
        applicationProcess.setJobAfterProcess(applicationProcessSaveDataModel.getJobAfterProcess());
        applicationProcess.setRemainingAcademicCommitments(applicationProcessSaveDataModel.getRemainingAcademicCommitments());
        applicationProcess.setRequestedFacilitiesDescription(applicationProcessSaveDataModel.getRequestedFacilitiesDescription());
        applicationProcess.setRequestedFacilitiesText(applicationProcessSaveDataModel.getRequestedFacilitiesText());
        applicationProcess.setStartStudyDate(applicationProcessSaveDataModel.getStartStudyDate());
        applicationProcess.setTypeAndAmountOfFacilities(applicationProcessSaveDataModel.getTypeAndAmountOfFacilities());
        applicationProcess.setAveragePointOfNewCourse(applicationProcessSaveDataModel.getAveragePointOfNewCourse());
        applicationProcess.setDateOfAcceptanceOfDegree(applicationProcessSaveDataModel.getDateOfAcceptanceOfDegree());

        if(applicationProcessSaveDataModel.getEducationalRecordId() != null)
        {
            EducationalRecord educationalRecord = educationalRecordRepository.getOne(applicationProcessSaveDataModel.getEducationalRecordId());
            applicationProcess.setEducationalRecord(educationalRecord);
        }
        else {
            applicationProcess.setEducationalRecord(null);
        }
        if(applicationProcessSaveDataModel.getQualificationId() != null)
        {
            Qualification qualification = qualificationRepository.getOne(applicationProcessSaveDataModel.getQualificationId());
            applicationProcess.setQualification(qualification);
        }
        else {
            applicationProcess.setQualification(null);
        }

        applicationProcess.setDescription(applicationProcessSaveDataModel.getDescription());
        applicationProcess.setSelectedModuleIds(applicationProcessSaveDataModel.getSelectedModuleIds());
        applicationProcess.setModifyDate(ZonedDateTime.now());
        applicationProcess.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        applicationProcess.setChangeStatusUserLogin(SecurityUtils.getCurrentUserLogin().get());

        /*Set<SkillableLevelOfSkill> skillableLevelOfSkills = applicationProcessSaveDataModel.getSkillableLevelOfSkills().stream()
            .map(skillableLevelOfSkillMapper::toEntity).collect(Collectors.toSet());
        applicationProcess.setSkillableLevelOfSkills(skillableLevelOfSkills);*/

        applicationProcess = applicationProcessRepository.save(applicationProcess);

        ApplicationProcessRunStepCriteria applicationProcessRunStepCriteria = new ApplicationProcessRunStepCriteria();

        LongFilter applicationProcessIdFilter = new LongFilter();
        applicationProcessIdFilter.setEquals(applicationProcess.getId());
        applicationProcessRunStepCriteria.setApplicationProcessId(applicationProcessIdFilter);

        List<ApplicationProcessRunStepDTO> applicationProcessRunSteps = applicationProcessRunStepQueryService.findByCriteria(applicationProcessRunStepCriteria);

        for (ApplicationProcessSaveDataItemModel item : applicationProcessSaveDataModel.getApplicationProcessSaveDataItemModels()) {
            ApplicationProcessRunStepDTO applicationProcessRunStep = new ApplicationProcessRunStepDTO();
            List<ApplicationProcessRunStepDTO> selectedRunRunningSteps = applicationProcessRunSteps.stream()
                .filter(run -> run.getApplicationProcessId().equals(item.getRunningStepId())).collect(Collectors.toList());
            if (!selectedRunRunningSteps.isEmpty()) {
                applicationProcessRunStep = selectedRunRunningSteps.get(0); //applicationProcessRunSteps.stream().filter(a -> a.getRunningStep().getId() == item.getRunningStepId()).collect(Collectors.toList()).get(0);
                if (applicationProcessRunStep.isDone() == false && item.getDone()) {
                    applicationProcessRunStep.setDoneDate(ZonedDateTime.now());
                    applicationProcessRunStep.setDoneUserLogin(SecurityUtils.getCurrentUserLogin().get());
                    applicationProcessRunStep.setDone(true);
                } else {
                    applicationProcessRunStep.setDone(item.getDone());
                }
                applicationProcessRunStep.setDescription(item.getDescription());
                applicationProcessRunStep.setFileDoc(item.getFileDoc());
            } else {
                if (item.getDone()) {
                    applicationProcessRunStep.setDoneDate(ZonedDateTime.now());
                    applicationProcessRunStep.setDoneUserLogin(SecurityUtils.getCurrentUserLogin().get());
                    applicationProcessRunStep.setDone(true);
                } else
                    applicationProcessRunStep.setDone(false);

                applicationProcessRunStep.setDescription(item.getDescription());
                applicationProcessRunStep.setFileDoc(item.getFileDoc());
                applicationProcessRunStep.setCreateDate(ZonedDateTime.now());
                applicationProcessRunStep.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
                //RunningStep runningStep = runningStepRepository.getOne(item.getRunningStepId());
                applicationProcessRunStep.setApplicationProcessStepId(item.getRunningStepId());
                applicationProcessRunStep.setApplicationProcessId(applicationProcess.getId());
            }
            applicationProcessRunStepService.save(applicationProcessRunStep);
        }

        return applicationProcessMapper.toDto(applicationProcess);
    }

    /**
     * Get all the applicationProcesses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ApplicationProcessDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ApplicationProcesses");
        return applicationProcessRepository.findAll(pageable)
            .map(applicationProcessMapper::toDto);
    }

    /**
     * Get all the ApplicationProcess with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<ApplicationProcessDTO> findAllWithEagerRelationships(Pageable pageable) {
        return applicationProcessRepository.findAllWithEagerRelationships(pageable).map(applicationProcessMapper::toDto);
    }


    /**
     * Get one applicationProcess by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ApplicationProcessDTO> findOne(Long id) {
        log.debug("Request to get ApplicationProcess : {}", id);
        return applicationProcessRepository.findOneWithEagerRelationships(id)
            .map(applicationProcessMapper::toDto);
    }

    /**
     * Delete the applicationProcess by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ApplicationProcess : {}", id);
        applicationProcessRepository.deleteById(id);
    }
}
