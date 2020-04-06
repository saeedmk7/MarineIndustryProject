package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.FinalNiazsanjiReport;
import com.marineindustryproj.domain.Person;
import com.marineindustryproj.domain.Teacher;
import com.marineindustryproj.repository.*;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.*;
import com.marineindustryproj.domain.RunPhase;
import com.marineindustryproj.service.dto.*;
import com.marineindustryproj.service.dto.customs.RunPhaseSaveDataItemModel;
import com.marineindustryproj.service.dto.customs.RunPhaseSaveDataModel;
import com.marineindustryproj.service.mapper.RunPhaseMapper;
import io.github.jhipster.service.filter.LongFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing RunPhase.
 */
@Service
@Transactional
public class RunPhaseServiceImpl implements RunPhaseService {

    private final Logger log = LoggerFactory.getLogger(RunPhaseServiceImpl.class);

    private final RunPhaseRepository runPhaseRepository;

    private final RunPhaseMapper runPhaseMapper;

    private final FinalNiazsanjiReportRepository finalNiazsanjiReportRepository;

    private final RunningStepRepository runningStepRepository;

    private final RunRunningStepRepository runRunningStepRepository;

    private final RunRunningStepService runRunningStepService;

    private final RunRunningStepQueryService runRunningStepQueryService;

    private final RunPhaseQueryService runPhaseQueryService;

    private final TeacherRepository teacherRepository;

    public RunPhaseServiceImpl(RunPhaseRepository runPhaseRepository,
                               RunPhaseMapper runPhaseMapper,
                               FinalNiazsanjiReportRepository finalNiazsanjiReportRepository,
                               RunningStepRepository runningStepRepository,
                               RunRunningStepRepository runRunningStepRepository,
                               RunRunningStepService runRunningStepService,
                               RunRunningStepQueryService runRunningStepQueryService,
                               RunPhaseQueryService runPhaseQueryService, TeacherRepository teacherRepository) {
        this.runPhaseRepository = runPhaseRepository;
        this.runPhaseMapper = runPhaseMapper;
        this.finalNiazsanjiReportRepository = finalNiazsanjiReportRepository;
        this.runningStepRepository = runningStepRepository;
        this.runRunningStepRepository = runRunningStepRepository;
        this.runRunningStepService = runRunningStepService;
        this.runRunningStepQueryService = runRunningStepQueryService;
        this.runPhaseQueryService = runPhaseQueryService;
        this.teacherRepository = teacherRepository;
    }

    /**
     * Save a runPhase.
     *
     * @param runPhaseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RunPhaseDTO save(RunPhaseDTO runPhaseDTO) {
        log.debug("Request to save RunPhase : {}", runPhaseDTO);

        RunPhase runPhase = runPhaseMapper.toEntity(runPhaseDTO);
        runPhase = runPhaseRepository.save(runPhase);
        return runPhaseMapper.toDto(runPhase);
    }

    @Override
    public RunPhaseDTO saveDataModel(RunPhaseSaveDataModel runPhaseSaveDataModel) {
        log.debug("Request to save RunPhase : {}", runPhaseSaveDataModel);
        FinalNiazsanjiReport finalNiazsanjiReport = finalNiazsanjiReportRepository.getOne(runPhaseSaveDataModel.getFinalNiazsanjiReportId());
        RunPhase runPhase = new RunPhase();
        if(runPhaseSaveDataModel.getRunPhaseId() != null)
        {
            runPhase = runPhaseRepository.getOne(runPhaseSaveDataModel.getRunPhaseId());
        }
        else{
            runPhase.setFinalNiazsanjiReport(finalNiazsanjiReport);
            runPhase.setArchived(false);
            runPhase.setCreateDate(ZonedDateTime.now());
            runPhase.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
            runPhase.setStatus(runPhaseSaveDataModel.getStatus());
            runPhase.setDone(false);
        }
        runPhase.setDescription(runPhaseSaveDataModel.getDescription());
        runPhase.setConversation(runPhaseSaveDataModel.getConversion());
        runPhase.setFinalizeCost(runPhaseSaveDataModel.getFinalizeCost());
        runPhase.setFinishDate(runPhaseSaveDataModel.getFinishDate());
        runPhase.setStepNumber(runPhaseSaveDataModel.getStepNumber());
        runPhase.setRunMonth(runPhaseSaveDataModel.getRunMonth());
        runPhase.setEducationalModule(finalNiazsanjiReport.getEducationalModule());
        runPhase.setOrganizationChart(finalNiazsanjiReport.getOrganizationChart());
        finalNiazsanjiReport.setFinalizeCost(runPhase.getFinalizeCost());
        finalNiazsanjiReport.setRunMonth(runPhase.getRunMonth());
        if((finalNiazsanjiReport.getTeacher() == null) || (finalNiazsanjiReport.getTeacher() != null && finalNiazsanjiReport.getTeacher().getId() != runPhaseSaveDataModel.getTeacherId()))
        {
            Optional<Teacher> teacher = teacherRepository.findOneWithEagerRelationships(runPhaseSaveDataModel.getTeacherId());
            if(teacher.isPresent())
            {
                finalNiazsanjiReport.setTeacher(teacher.get());
            }
        }

        if(runPhaseSaveDataModel.getDone() && runPhase.getStatus() < 10)
        {
            if(SecurityUtils.isCurrentUserInRole("ROLE_KARSHENAS_ARSHAD_AMOZESH_SAZMAN") || SecurityUtils.isCurrentUserInRole("ROLE_MODIR_KOL_AMOZESH")) {
                runPhase.setDone(runPhaseSaveDataModel.getDone());
                runPhase.setDoneDate(ZonedDateTime.now());
                runPhase.setDoneUserLogin(SecurityUtils.getCurrentUserLogin().get());
                runPhase.setStatus(10);

                finalNiazsanjiReport.setStatus(20);
                finalNiazsanjiReportRepository.save(finalNiazsanjiReport);
            }
            else{
                runPhase.setStatus(5);
            }
        }
        //RunPhase runPhase = runPhaseMapper.toEntity(runPhaseDTO);
        runPhase = runPhaseRepository.save(runPhase);

        RunRunningStepCriteria runRunningStepCriteria = new RunRunningStepCriteria();

        LongFilter runPhaseIdFilter = new LongFilter();
        runPhaseIdFilter.setEquals(runPhase.getId());
        runRunningStepCriteria.setRunPhaseId(runPhaseIdFilter);

        List<RunRunningStepDTO> runRunningSteps = runRunningStepQueryService.findByCriteria(runRunningStepCriteria);

        //Set<RunRunningStep> runRunningSteps = runRunningStepRepository.find //runPhase.getRunRunningSteps();
        for (RunPhaseSaveDataItemModel item: runPhaseSaveDataModel.getRunPhaseSaveDataItemModels()) {
            RunRunningStepDTO runRunningStep = new RunRunningStepDTO();
            List<RunRunningStepDTO> selectedRunRunningSteps = runRunningSteps.stream().filter(run -> run.getRunningStepId().equals(item.getRunningStepId())).collect(Collectors.toList());
            if(!selectedRunRunningSteps.isEmpty())
            {
                 runRunningStep = selectedRunRunningSteps.get(0); //runRunningSteps.stream().filter(a -> a.getRunningStep().getId() == item.getRunningStepId()).collect(Collectors.toList()).get(0);
                 if(runRunningStep.isDone() == false && item.getDone()) {
                     runRunningStep.setDoneDate(ZonedDateTime.now());
                     runRunningStep.setDoneUserLogin(SecurityUtils.getCurrentUserLogin().get());
                     runRunningStep.setDone(true);
                 }
                 else{
                     runRunningStep.setDone(item.getDone());
                 }
                 runRunningStep.setDescription(item.getDescription());
                 runRunningStep.setFileDoc(item.getFileDoc());
            }
            else{
                if(item.getDone()) {
                    runRunningStep.setDoneDate(ZonedDateTime.now());
                    runRunningStep.setDoneUserLogin(SecurityUtils.getCurrentUserLogin().get());
                    runRunningStep.setDone(true);
                }
                else
                    runRunningStep.setDone(false);

                runRunningStep.setDescription(item.getDescription());
                runRunningStep.setFileDoc(item.getFileDoc());
                runRunningStep.setCreateDate(ZonedDateTime.now());
                runRunningStep.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
                //RunningStep runningStep = runningStepRepository.getOne(item.getRunningStepId());
                runRunningStep.setRunningStepId(item.getRunningStepId());
                runRunningStep.setRunPhaseId(runPhase.getId());
            }
            runRunningStepService.save(runRunningStep);
        }

        if(runPhase.getPeople().isEmpty()) {
            long[] personIds = finalNiazsanjiReport.getFinalNiazsanjiReportPeople().stream().mapToLong(a ->a.getPerson().getId()).toArray();
            Set<Person> people = new HashSet<>();
            for (long personId : personIds) {
                Person person = new Person();
                person.setId(personId);
                people.add(person);
            }
            runPhase.setPeople(people);
        }
        return runPhaseMapper.toDto(runPhase);
    }

    /**
     * Get all the runPhases.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RunPhaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RunPhases");
        return runPhaseRepository.findAll(pageable)
            .map(runPhaseMapper::toDto);
    }

    /**
     * Get all the RunPhase with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<RunPhaseDTO> findAllWithEagerRelationships(Pageable pageable) {
        return runPhaseRepository.findAllWithEagerRelationships(pageable).map(runPhaseMapper::toDto);
    }
    

    /**
     * Get one runPhase by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RunPhaseDTO> findOne(Long id) {
        log.debug("Request to get RunPhase : {}", id);
        return runPhaseRepository.findOneWithEagerRelationships(id)
            .map(runPhaseMapper::toDto);
    }

    /**
     * Delete the runPhase by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RunPhase : {}", id);
        runPhaseRepository.deleteById(id);
    }

    @Override
    public void deleteByFinalNiazsanjiReportId(Long finalNiazsanjiReportId) {
        log.debug("Request to delete FinalNiazsanjiReportPerson by finalNiazsanjiReportId: {}", finalNiazsanjiReportId);
        LongFilter filter = new LongFilter();
        filter.setEquals(finalNiazsanjiReportId);

        RunPhaseCriteria runPhaseCriteria = new RunPhaseCriteria();
        runPhaseCriteria.setFinalNiazsanjiReportId(filter);
        List<RunPhaseDTO> runPhaseDTOS =  runPhaseQueryService.findByCriteria(runPhaseCriteria);
        if(!runPhaseDTOS.isEmpty()){
            for (RunPhaseDTO runPhase : runPhaseDTOS) {
                delete(runPhase.getId());
            }
        }
    }

    @Override
    public Optional<RunPhaseDTO> findByFinalNiazsanjiReportId(Long finalNiazsanjiReportId) {
        LongFilter filter = new LongFilter();
        filter.setEquals(finalNiazsanjiReportId);

        RunPhaseCriteria runPhaseCriteria = new RunPhaseCriteria();
        runPhaseCriteria.setFinalNiazsanjiReportId(filter);
        List<RunPhaseDTO> runPhaseDTOS =  runPhaseQueryService.findByCriteria(runPhaseCriteria);
        if(runPhaseDTOS.size() > 0)
            return Optional.of(runPhaseDTOS.get(0));
        return Optional.empty();
    }
}
