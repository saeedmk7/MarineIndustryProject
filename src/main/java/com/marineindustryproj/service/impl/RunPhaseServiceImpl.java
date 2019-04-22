package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.FinalNiazsanjiReport;
import com.marineindustryproj.domain.RunRunningStep;
import com.marineindustryproj.domain.RunningStep;
import com.marineindustryproj.repository.FinalNiazsanjiReportRepository;
import com.marineindustryproj.repository.RunRunningStepRepository;
import com.marineindustryproj.repository.RunningStepRepository;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.RunPhaseService;
import com.marineindustryproj.domain.RunPhase;
import com.marineindustryproj.repository.RunPhaseRepository;
import com.marineindustryproj.service.dto.RunPhaseDTO;
import com.marineindustryproj.service.dto.customs.RunPhaseSaveDataItemModel;
import com.marineindustryproj.service.dto.customs.RunPhaseSaveDataModel;
import com.marineindustryproj.service.mapper.RunPhaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
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

    public RunPhaseServiceImpl(RunPhaseRepository runPhaseRepository, RunPhaseMapper runPhaseMapper, FinalNiazsanjiReportRepository finalNiazsanjiReportRepository, RunningStepRepository runningStepRepository, RunRunningStepRepository runRunningStepRepository) {
        this.runPhaseRepository = runPhaseRepository;
        this.runPhaseMapper = runPhaseMapper;
        this.finalNiazsanjiReportRepository = finalNiazsanjiReportRepository;
        this.runningStepRepository = runningStepRepository;
        this.runRunningStepRepository = runRunningStepRepository;
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
            runPhase.setDescription(runPhaseSaveDataModel.getDescription());
            runPhase.setFinalizeCost(runPhaseSaveDataModel.getFinalizeCost());
            runPhase.setStatus(runPhaseSaveDataModel.getStatus());
            runPhase.setDone(false);
        }
        runPhase.setStepNumber(runPhaseSaveDataModel.getStepNumber());
        if(runPhaseSaveDataModel.getDone() && runPhase.getStatus() < 10)
        {
            runPhase.setDone(runPhaseSaveDataModel.getDone());
            runPhase.setDoneDate(ZonedDateTime.now());
            runPhase.setDoneUserLogin(SecurityUtils.getCurrentUserLogin().get());
            runPhase.setStatus(10);

            finalNiazsanjiReport.setStatus(20);
            finalNiazsanjiReportRepository.save(finalNiazsanjiReport);
        }
        //RunPhase runPhase = runPhaseMapper.toEntity(runPhaseDTO);
        runPhase = runPhaseRepository.save(runPhase);


        Set<RunRunningStep> runRunningSteps = runPhase.getRunRunningSteps();
        for (RunPhaseSaveDataItemModel item: runPhaseSaveDataModel.getRunPhaseSaveDataItemModels()) {
            RunRunningStep runRunningStep = new RunRunningStep();
            if(runRunningSteps.stream().anyMatch(a -> a.getRunningStep().getId() == item.getRunningStepId()))
            {
                 runRunningStep = runRunningSteps.stream().filter(a -> a.getRunningStep().getId() == item.getRunningStepId()).collect(Collectors.toList()).get(0);
                 if(runRunningStep.isDone() != true && item.getDone()) {
                     runRunningStep.setDoneDate(ZonedDateTime.now());
                     runRunningStep.setDoneUserLogin(SecurityUtils.getCurrentUserLogin().get());
                     runRunningStep.setDone(true);
                 }
                 else{
                     runRunningStep.setDone(false);
                 }
                 runRunningStep.setDescription(item.getDescription());
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
                runRunningStep.setCreateDate(ZonedDateTime.now());
                runRunningStep.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
                RunningStep runningStep = runningStepRepository.getOne(item.getRunningStepId());
                runRunningStep.setRunningStep(runningStep);
                runRunningStep.setRunPhase(runPhase);
            }
            runRunningStepRepository.save(runRunningStep);
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
}
