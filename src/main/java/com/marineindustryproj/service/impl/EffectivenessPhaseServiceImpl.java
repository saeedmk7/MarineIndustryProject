package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.FinalNiazsanjiReport;
import com.marineindustryproj.domain.FinalNiazsanjiReportPerson;
import com.marineindustryproj.domain.enumeration.Grade;
import com.marineindustryproj.repository.FinalNiazsanjiReportRepository;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.EffectivenessPhaseService;
import com.marineindustryproj.domain.EffectivenessPhase;
import com.marineindustryproj.repository.EffectivenessPhaseRepository;
import com.marineindustryproj.service.FinalNiazsanjiReportService;
import com.marineindustryproj.service.dto.EffectivenessPhaseDTO;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportDTO;
import com.marineindustryproj.service.mapper.EffectivenessPhaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing EffectivenessPhase.
 */
@Service
@Transactional
public class EffectivenessPhaseServiceImpl implements EffectivenessPhaseService {

    private final Logger log = LoggerFactory.getLogger(EffectivenessPhaseServiceImpl.class);

    private final EffectivenessPhaseRepository effectivenessPhaseRepository;

    private final EffectivenessPhaseMapper effectivenessPhaseMapper;

    private final FinalNiazsanjiReportService finalNiazsanjiReportService;

    private final FinalNiazsanjiReportRepository finalNiazsanjiReportRepository;

    public EffectivenessPhaseServiceImpl(EffectivenessPhaseRepository effectivenessPhaseRepository, EffectivenessPhaseMapper effectivenessPhaseMapper, FinalNiazsanjiReportService finalNiazsanjiReportService, FinalNiazsanjiReportRepository finalNiazsanjiReportRepository) {
        this.effectivenessPhaseRepository = effectivenessPhaseRepository;
        this.effectivenessPhaseMapper = effectivenessPhaseMapper;
        this.finalNiazsanjiReportService = finalNiazsanjiReportService;
        this.finalNiazsanjiReportRepository = finalNiazsanjiReportRepository;
    }

    /**
     * Save a effectivenessPhase.
     *
     * @param effectivenessPhaseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EffectivenessPhaseDTO save(EffectivenessPhaseDTO effectivenessPhaseDTO) {
        log.debug("Request to save EffectivenessPhase : {}", effectivenessPhaseDTO);

        EffectivenessPhase effectivenessPhase = effectivenessPhaseMapper.toEntity(effectivenessPhaseDTO);
        effectivenessPhase = effectivenessPhaseRepository.save(effectivenessPhase);
        return effectivenessPhaseMapper.toDto(effectivenessPhase);
    }

    @Override
    public Boolean completeEffectivenessPhase(Long finalNiazsanjiReportId) throws Exception {
        FinalNiazsanjiReport finalNiazsanjiReport = finalNiazsanjiReportRepository.getOne(finalNiazsanjiReportId);
        if (finalNiazsanjiReport == null) {
            throw new Exception("finalNiazsanjiReportId is not valid");
        }

        List<EffectivenessPhase> effectivenessPhaseNotFinished = finalNiazsanjiReport.getEffectivenessPhases()
            .stream().filter(a -> a.getStatus() != 20).collect(Collectors.toList());
        if(!effectivenessPhaseNotFinished.isEmpty()){
            throw new Exception("هنوز یکی از سطوح تکمیل نشده است");
        }

        List<EffectivenessPhase> effectivenessPhases = finalNiazsanjiReport.getEffectivenessPhases()
            .stream().collect(Collectors.toList());

        float finalScore = (float) effectivenessPhases.stream().mapToDouble(a -> a.getWeightedPoints()).sum();

        finalNiazsanjiReport.setLastEffectivenessPhaseFinish(ZonedDateTime.now());
        finalNiazsanjiReport.setStatus(40);
        finalNiazsanjiReport.setEffectivenessPhaseAverage(finalScore);
        finalNiazsanjiReport.setEffectivenessPhaseGrade(calculateGrade(finalScore));

        finalNiazsanjiReport.setModifyDate(ZonedDateTime.now());
        finalNiazsanjiReport.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());

        finalNiazsanjiReportRepository.save(finalNiazsanjiReport);

        return true;

    }
    public Grade calculateGrade(float score){
        if(score <= 50)
            return Grade.D;
        else if(score <= 60)
            return Grade.C;
        else if(score <= 75)
            return Grade.B;
        else if(score <= 100)
            return Grade.A;

        return Grade.A;
    }

    @Override
    public Boolean completeLevelTwo(Long finalNiazsanjiReportId) throws Exception {
        FinalNiazsanjiReport finalNiazsanjiReport = finalNiazsanjiReportRepository.getOne(finalNiazsanjiReportId);
        if(finalNiazsanjiReport == null){
            throw new Exception("finalNiazsanjiReportId is not valid");
        }

        List<FinalNiazsanjiReportPerson> finalNiazsanjiReportPeopleNotFinished = finalNiazsanjiReport.
            getFinalNiazsanjiReportPeople().stream().filter(a -> a.getAverageScore() == null || a.getAverageScore() == 0).collect(Collectors.toList());
        if(!finalNiazsanjiReportPeopleNotFinished.isEmpty()){
            throw new Exception("هنوز یکی از افراد اثربخشی نشده است");
        }



        Optional<EffectivenessPhase> effectivenessPhaseOptional = finalNiazsanjiReport.getEffectivenessPhases()
            .stream().filter(a -> a.getEffectivenessPhaseLevel().getEffectivenessLevel() == 2).findFirst();
        if(!effectivenessPhaseOptional.isPresent()){
            throw new Exception("finalNiazsanjiReportId is not valid");
        }

        EffectivenessPhase effectivenessPhase = effectivenessPhaseOptional.get();

        List<FinalNiazsanjiReportPerson> finalNiazsanjiReportPeople = finalNiazsanjiReport
            .getFinalNiazsanjiReportPeople().stream().collect(Collectors.toList());

        float firstScore = (float) (finalNiazsanjiReportPeople.stream()
            .mapToDouble(a -> a.getScoreBeforeTest()).sum() / finalNiazsanjiReportPeople.size());
        float secondScore = (float) (finalNiazsanjiReportPeople.stream()
            .mapToDouble(a -> a.getScoreAfterTest()).sum() / finalNiazsanjiReportPeople.size());
        float finalScore = (float) (finalNiazsanjiReportPeople.stream()
            .mapToDouble(a -> a.getAverageScore()).sum() / finalNiazsanjiReportPeople.size());
        float weightedScore = (float) ((finalNiazsanjiReportPeople.stream()
            .mapToDouble(a -> a.getAverageScore() * effectivenessPhase.getEffectivenessPhaseLevel().getWeight()).sum()
            / finalNiazsanjiReportPeople.size())) / 100;

        effectivenessPhase.setFirstScore(firstScore);
        effectivenessPhase.setSecondScore(secondScore);
        effectivenessPhase.setFinalScore(finalScore);
        effectivenessPhase.setWeightedPoints(weightedScore);
        effectivenessPhase.setStatus(20);
        effectivenessPhase.setFinishPhaseDate(ZonedDateTime.now());
        effectivenessPhase.setFinishPhaseUserLogin(SecurityUtils.getCurrentUserLogin().get());
        effectivenessPhase.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        effectivenessPhase.setModifyDate(ZonedDateTime.now());

        effectivenessPhaseRepository.save(effectivenessPhase);

        finalNiazsanjiReport.setLastEffectivenessPhaseFinish(ZonedDateTime.now());
        finalNiazsanjiReportRepository.save(finalNiazsanjiReport);

        return true;
    }
    /**
     * Get all the effectivenessPhases.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EffectivenessPhaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EffectivenessPhases");
        return effectivenessPhaseRepository.findAll(pageable)
            .map(effectivenessPhaseMapper::toDto);
    }

    /**
     * Get all the EffectivenessPhase with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<EffectivenessPhaseDTO> findAllWithEagerRelationships(Pageable pageable) {
        return effectivenessPhaseRepository.findAllWithEagerRelationships(pageable).map(effectivenessPhaseMapper::toDto);
    }
    

    /**
     * Get one effectivenessPhase by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EffectivenessPhaseDTO> findOne(Long id) {
        log.debug("Request to get EffectivenessPhase : {}", id);
        return effectivenessPhaseRepository.findOneWithEagerRelationships(id)
            .map(effectivenessPhaseMapper::toDto);
    }

    /**
     * Delete the effectivenessPhase by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EffectivenessPhase : {}", id);
        effectivenessPhaseRepository.deleteById(id);
    }
}
