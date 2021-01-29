package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.repository.*;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.*;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportPersonDTO;
import com.marineindustryproj.service.dto.NiazsanjiPersonGradeDTO;
import com.marineindustryproj.service.dto.NiazsanjiPersonGradeScoreDTO;
import com.marineindustryproj.service.mapper.NiazsanjiPersonGradeMapper;
import com.marineindustryproj.service.mapper.NiazsanjiPersonGradeScoreMapper;
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
 * Service Implementation for managing NiazsanjiPersonGrade.
 */
@Service
@Transactional
public class NiazsanjiPersonGradeServiceImpl implements NiazsanjiPersonGradeService {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiPersonGradeServiceImpl.class);

    private final NiazsanjiPersonGradeRepository niazsanjiPersonGradeRepository;

    private final NiazsanjiPersonGradeMapper niazsanjiPersonGradeMapper;

    private final NiazsanjiPersonGradeScoreService niazsanjiPersonGradeScoreService;

    private final NiazsanjiPersonGradeScoreRepository niazsanjiPersonGradeScoreRepository;

    private final NiazsanjiPersonGradeQueryService niazsanjiPersonGradeQueryService;

    private final FinalNiazsanjiReportPersonService finalNiazsanjiReportPersonService;

    private final FinalNiazsanjiReportPersonRepository finalNiazsanjiReportPersonRepository;

    private final FinalNiazsanjiReportPersonQueryService finalNiazsanjiReportPersonQueryService;

    private final FinalNiazsanjiReportService finalNiazsanjiReportService;

    private final FinalNiazsanjiReportRepository finalNiazsanjiReportRepository;

    private final EffectivenessPhaseRepository effectivenessPhaseRepository;

    private final NiazsanjiPersonGradeScoreMapper niazsanjiPersonGradeScoreMapper;

    public NiazsanjiPersonGradeServiceImpl(NiazsanjiPersonGradeRepository niazsanjiPersonGradeRepository,
                                           NiazsanjiPersonGradeMapper niazsanjiPersonGradeMapper,
                                           NiazsanjiPersonGradeScoreService niazsanjiPersonGradeScoreService,
                                           NiazsanjiPersonGradeScoreRepository niazsanjiPersonGradeScoreRepository, NiazsanjiPersonGradeQueryService niazsanjiPersonGradeQueryService,
                                           FinalNiazsanjiReportPersonService finalNiazsanjiReportPersonService, FinalNiazsanjiReportPersonRepository finalNiazsanjiReportPersonRepository, FinalNiazsanjiReportPersonQueryService finalNiazsanjiReportPersonQueryService, FinalNiazsanjiReportService finalNiazsanjiReportService, FinalNiazsanjiReportRepository finalNiazsanjiReportRepository, EffectivenessPhaseRepository effectivenessPhaseRepository, NiazsanjiPersonGradeScoreMapper niazsanjiPersonGradeScoreMapper) {
        this.niazsanjiPersonGradeRepository = niazsanjiPersonGradeRepository;
        this.niazsanjiPersonGradeMapper = niazsanjiPersonGradeMapper;
        this.niazsanjiPersonGradeScoreService = niazsanjiPersonGradeScoreService;
        this.niazsanjiPersonGradeScoreRepository = niazsanjiPersonGradeScoreRepository;
        this.niazsanjiPersonGradeQueryService = niazsanjiPersonGradeQueryService;
        this.finalNiazsanjiReportPersonService = finalNiazsanjiReportPersonService;
        this.finalNiazsanjiReportPersonRepository = finalNiazsanjiReportPersonRepository;
        this.finalNiazsanjiReportPersonQueryService = finalNiazsanjiReportPersonQueryService;
        this.finalNiazsanjiReportService = finalNiazsanjiReportService;
        this.finalNiazsanjiReportRepository = finalNiazsanjiReportRepository;
        this.effectivenessPhaseRepository = effectivenessPhaseRepository;
        this.niazsanjiPersonGradeScoreMapper = niazsanjiPersonGradeScoreMapper;
    }

    /**
     * Save a niazsanjiPersonGrade.
     *
     * @param niazsanjiPersonGradeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NiazsanjiPersonGradeDTO save(NiazsanjiPersonGradeDTO niazsanjiPersonGradeDTO) {
        log.debug("Request to save NiazsanjiPersonGrade : {}", niazsanjiPersonGradeDTO);

        NiazsanjiPersonGrade niazsanjiPersonGrade = niazsanjiPersonGradeMapper.toEntity(niazsanjiPersonGradeDTO);

        niazsanjiPersonGrade = niazsanjiPersonGradeRepository.save(niazsanjiPersonGrade);

        return niazsanjiPersonGradeMapper.toDto(niazsanjiPersonGrade);
    }

    @Override
    public NiazsanjiPersonGradeDTO saveWithScore(NiazsanjiPersonGradeDTO niazsanjiPersonGradeDTO) {
        log.debug("Request to save NiazsanjiPersonGrade : {}", niazsanjiPersonGradeDTO);

        NiazsanjiPersonGrade niazsanjiPersonGrade = niazsanjiPersonGradeMapper.toEntity(niazsanjiPersonGradeDTO);

        niazsanjiPersonGrade = niazsanjiPersonGradeRepository.save(niazsanjiPersonGrade);

        for (NiazsanjiPersonGradeScoreDTO niazsanjiPersonGradeScoreDTO : niazsanjiPersonGradeDTO.getNiazsanjiPersonGradeScores()) {
            NiazsanjiPersonGradeScore niazsanjiPersonGradeScore = niazsanjiPersonGradeScoreMapper.toEntity(niazsanjiPersonGradeScoreDTO);
            niazsanjiPersonGradeScore.setNiazsanjiPersonGrade(niazsanjiPersonGrade);
            niazsanjiPersonGradeScore.setCreateDate(ZonedDateTime.now());
            niazsanjiPersonGradeScore.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
            niazsanjiPersonGradeScore.setModifyDate(ZonedDateTime.now());
            niazsanjiPersonGradeScore.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
            niazsanjiPersonGradeScoreRepository.save(niazsanjiPersonGradeScore);
        }

        FinalNiazsanjiReportPerson finalNiazsanjiReportPerson = finalNiazsanjiReportPersonRepository.getOne(niazsanjiPersonGrade.getFinalNiazsanjiReportPerson().getId());
        finalNiazsanjiReportPerson.setStatus(20);
        finalNiazsanjiReportPerson.setLevelOneScore(niazsanjiPersonGradeDTO.getTotalScorePercent());
        finalNiazsanjiReportPerson.setModifyDate(ZonedDateTime.now());
        finalNiazsanjiReportPerson.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalNiazsanjiReportPersonRepository.save(finalNiazsanjiReportPerson);

        return niazsanjiPersonGradeMapper.toDto(niazsanjiPersonGrade);
    }

    @Override
    public Boolean completeLevel(Long finalNiazsanjiReportId) throws Exception {
        FinalNiazsanjiReport finalNiazsanjiReport = finalNiazsanjiReportRepository.getOne(finalNiazsanjiReportId);
        if(finalNiazsanjiReport == null){
            throw new Exception("finalNiazsanjiReportId is not valid");
        }

        List<FinalNiazsanjiReportPerson> finalNiazsanjiReportPeopleNotFinished = finalNiazsanjiReport.getFinalNiazsanjiReportPeople().stream().filter(a -> a.getLevelOneScore() == null || a.getLevelOneScore() == 0).collect(Collectors.toList());
        if(!finalNiazsanjiReportPeopleNotFinished.isEmpty()){
            throw new Exception("هنوز یکی از افراد اثربخشی نشده است");
        }

        EffectivenessPhase effectivenessPhase;
        List<EffectivenessPhase> effectivenessPhasesList = effectivenessPhaseRepository.findAllByFinalNiazsanjiReport(finalNiazsanjiReportId);
        if((!effectivenessPhasesList.isEmpty()) && effectivenessPhasesList.size() > 0)
        {
            Optional<EffectivenessPhase> effectivenessPhaseOptional = effectivenessPhasesList.stream().filter(a -> a.getEffectivenessPhaseLevel().getEffectivenessLevel() == 1).findFirst();
            if(!effectivenessPhaseOptional.isPresent()){
                throw new Exception("finalNiazsanjiReportId is not valid");
            }
            effectivenessPhase = effectivenessPhaseOptional.get();
        }
        else {
            throw new Exception("finalNiazsanjiReportId is not valid");
        }

        List<FinalNiazsanjiReportPerson> finalNiazsanjiReportPeople = finalNiazsanjiReport.getFinalNiazsanjiReportPeople().stream().collect(Collectors.toList());

        float finalScore = (float) (finalNiazsanjiReportPeople.stream().mapToDouble(a -> a.getLevelOneScore()).sum() / finalNiazsanjiReportPeople.size());
        float weightedScore = (float) ((finalNiazsanjiReportPeople.stream().mapToDouble(a -> a.getLevelOneScore() * effectivenessPhase.getEffectivenessPhaseLevel().getWeight()).sum() / finalNiazsanjiReportPeople.size())) / 100;

        effectivenessPhase.setSecondScore(finalScore);
        effectivenessPhase.setFinalScore(finalScore);
        effectivenessPhase.setWeightedPoints(weightedScore);
        effectivenessPhase.setStatus(20);
        effectivenessPhase.setFinishPhaseDate(ZonedDateTime.now());
        effectivenessPhase.setFinishPhaseUserLogin(SecurityUtils.getCurrentUserLogin().get());
        effectivenessPhase.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        effectivenessPhase.setModifyDate(ZonedDateTime.now());

        effectivenessPhaseRepository.save(effectivenessPhase);

        return true;
    }

    /**
     * Get all the niazsanjiPersonGrades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NiazsanjiPersonGradeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NiazsanjiPersonGrades");
        return niazsanjiPersonGradeRepository.findAll(pageable)
            .map(niazsanjiPersonGradeMapper::toDto);
    }

    /**
     * Get all the NiazsanjiPersonGrade with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<NiazsanjiPersonGradeDTO> findAllWithEagerRelationships(Pageable pageable) {
        return niazsanjiPersonGradeRepository.findAllWithEagerRelationships(pageable).map(niazsanjiPersonGradeMapper::toDto);
    }
    

    /**
     * Get one niazsanjiPersonGrade by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NiazsanjiPersonGradeDTO> findOne(Long id) {
        log.debug("Request to get NiazsanjiPersonGrade : {}", id);
        return niazsanjiPersonGradeRepository.findOneWithEagerRelationships(id)
            .map(niazsanjiPersonGradeMapper::toDto);
    }

    /**
     * Delete the niazsanjiPersonGrade by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NiazsanjiPersonGrade : {}", id);
        niazsanjiPersonGradeRepository.deleteById(id);
    }
}
