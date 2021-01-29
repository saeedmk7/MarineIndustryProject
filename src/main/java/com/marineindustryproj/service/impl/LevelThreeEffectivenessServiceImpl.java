package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.repository.*;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.LevelThreeEffectivenessService;
import com.marineindustryproj.service.dto.LevelThreeEffectivenessDTO;
import com.marineindustryproj.service.dto.LevelThreeScoreDTO;
import com.marineindustryproj.service.mapper.LevelThreeEffectivenessMapper;
import com.marineindustryproj.service.mapper.LevelThreeScoreMapper;
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
 * Service Implementation for managing LevelThreeEffectiveness.
 */
@Service
@Transactional
public class LevelThreeEffectivenessServiceImpl implements LevelThreeEffectivenessService {

    private final Logger log = LoggerFactory.getLogger(LevelThreeEffectivenessServiceImpl.class);

    private final LevelThreeEffectivenessRepository levelThreeEffectivenessRepository;

    private final LevelThreeEffectivenessMapper levelThreeEffectivenessMapper;

    private final LevelThreeScoreMapper levelThreeScoreMapper;

    private final LevelThreeScoreRepository levelThreeScoreRepository;

    private final FinalNiazsanjiReportPersonRepository finalNiazsanjiReportPersonRepository;

    private final FinalNiazsanjiReportRepository finalNiazsanjiReportRepository;

    private final EffectivenessPhaseRepository effectivenessPhaseRepository;

    public LevelThreeEffectivenessServiceImpl(LevelThreeEffectivenessRepository levelThreeEffectivenessRepository, LevelThreeEffectivenessMapper levelThreeEffectivenessMapper, LevelThreeScoreMapper levelThreeScoreMapper, LevelThreeScoreRepository levelThreeScoreRepository, FinalNiazsanjiReportPersonRepository finalNiazsanjiReportPersonRepository, FinalNiazsanjiReportRepository finalNiazsanjiReportRepository, EffectivenessPhaseRepository effectivenessPhaseRepository) {
        this.levelThreeEffectivenessRepository = levelThreeEffectivenessRepository;
        this.levelThreeEffectivenessMapper = levelThreeEffectivenessMapper;
        this.levelThreeScoreMapper = levelThreeScoreMapper;
        this.levelThreeScoreRepository = levelThreeScoreRepository;
        this.finalNiazsanjiReportPersonRepository = finalNiazsanjiReportPersonRepository;
        this.finalNiazsanjiReportRepository = finalNiazsanjiReportRepository;
        this.effectivenessPhaseRepository = effectivenessPhaseRepository;
    }

    /**
     * Save a levelThreeEffectiveness.
     *
     * @param levelThreeEffectivenessDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LevelThreeEffectivenessDTO save(LevelThreeEffectivenessDTO levelThreeEffectivenessDTO) {
        log.debug("Request to save LevelThreeEffectiveness : {}", levelThreeEffectivenessDTO);

        LevelThreeEffectiveness levelThreeEffectiveness = levelThreeEffectivenessMapper.toEntity(levelThreeEffectivenessDTO);
        levelThreeEffectiveness = levelThreeEffectivenessRepository.save(levelThreeEffectiveness);

        return levelThreeEffectivenessMapper.toDto(levelThreeEffectiveness);
    }

    @Override
    public LevelThreeEffectivenessDTO saveWithScore(LevelThreeEffectivenessDTO levelThreeEffectivenessDTO) {
        log.debug("Request to save LevelThreeEffectiveness : {}", levelThreeEffectivenessDTO);

        LevelThreeEffectiveness levelThreeEffectiveness = levelThreeEffectivenessMapper.toEntity(levelThreeEffectivenessDTO);
        levelThreeEffectiveness = levelThreeEffectivenessRepository.save(levelThreeEffectiveness);

        for (LevelThreeScoreDTO levelThreeScoreDTO : levelThreeEffectivenessDTO.getLevelThreeScores()) {
            LevelThreeScore levelThreeScore = levelThreeScoreMapper.toEntity(levelThreeScoreDTO);
            levelThreeScore.setLevelThreeEffectiveness(levelThreeEffectiveness);
            levelThreeScore.setCreateDate(ZonedDateTime.now());
            levelThreeScore.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
            levelThreeScore.setModifyDate(ZonedDateTime.now());
            levelThreeScore.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
            levelThreeScoreRepository.save(levelThreeScore);
        }

        FinalNiazsanjiReportPerson finalNiazsanjiReportPerson = finalNiazsanjiReportPersonRepository.getOne(levelThreeEffectiveness.getFinalNiazsanjiReportPerson().getId());
        finalNiazsanjiReportPerson.setStatus(20);
        finalNiazsanjiReportPerson.setLevelThreeScore(levelThreeEffectivenessDTO.getTotalScorePercent());
        finalNiazsanjiReportPerson.setModifyDate(ZonedDateTime.now());
        finalNiazsanjiReportPerson.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalNiazsanjiReportPersonRepository.save(finalNiazsanjiReportPerson);

        return levelThreeEffectivenessMapper.toDto(levelThreeEffectiveness);
    }

    @Override
    public Boolean completeLevel(Long finalNiazsanjiReportId) throws Exception {
        FinalNiazsanjiReport finalNiazsanjiReport = finalNiazsanjiReportRepository.getOne(finalNiazsanjiReportId);
        if(finalNiazsanjiReport == null){
            throw new Exception("finalNiazsanjiReportId is not valid");
        }

        List<FinalNiazsanjiReportPerson> finalNiazsanjiReportPeopleNotFinished = finalNiazsanjiReport
            .getFinalNiazsanjiReportPeople().stream().filter(a -> a.getLevelThreeScore() == null || a.getLevelThreeScore() == 0)
            .collect(Collectors.toList());
        if(!finalNiazsanjiReportPeopleNotFinished.isEmpty()){
            throw new Exception("هنوز یکی از افراد اثربخشی نشده است");
        }

        EffectivenessPhase effectivenessPhase;
        List<EffectivenessPhase> effectivenessPhasesList = effectivenessPhaseRepository.findAllByFinalNiazsanjiReport(finalNiazsanjiReportId);
        if((!effectivenessPhasesList.isEmpty()) && effectivenessPhasesList.size() > 0)
        {
            Optional<EffectivenessPhase> effectivenessPhaseOptional = effectivenessPhasesList.stream().filter(a -> a.getEffectivenessPhaseLevel().getEffectivenessLevel() == 3).findFirst();
            if(!effectivenessPhaseOptional.isPresent()){
                throw new Exception("finalNiazsanjiReportId is not valid");
            }
            effectivenessPhase = effectivenessPhaseOptional.get();
        }
        else {
            throw new Exception("finalNiazsanjiReportId is not valid");
        }

        List<FinalNiazsanjiReportPerson> finalNiazsanjiReportPeople = finalNiazsanjiReport.getFinalNiazsanjiReportPeople()
            .stream().collect(Collectors.toList());

        float finalScore = (float) (finalNiazsanjiReportPeople.stream().mapToDouble(a -> a.getLevelThreeScore()).sum()
            / finalNiazsanjiReportPeople.size());
        float weightedScore = (float) ((finalNiazsanjiReportPeople.stream().mapToDouble(a -> a.getLevelThreeScore() *
            effectivenessPhase.getEffectivenessPhaseLevel().getWeight()).sum() / finalNiazsanjiReportPeople.size())) / 100;

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
     * Get all the levelThreeEffectivenesses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LevelThreeEffectivenessDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LevelThreeEffectivenesses");
        return levelThreeEffectivenessRepository.findAll(pageable)
            .map(levelThreeEffectivenessMapper::toDto);
    }

    /**
     * Get all the LevelThreeEffectiveness with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<LevelThreeEffectivenessDTO> findAllWithEagerRelationships(Pageable pageable) {
        return levelThreeEffectivenessRepository.findAllWithEagerRelationships(pageable).map(levelThreeEffectivenessMapper::toDto);
    }
    

    /**
     * Get one levelThreeEffectiveness by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LevelThreeEffectivenessDTO> findOne(Long id) {
        log.debug("Request to get LevelThreeEffectiveness : {}", id);
        return levelThreeEffectivenessRepository.findOneWithEagerRelationships(id)
            .map(levelThreeEffectivenessMapper::toDto);
    }

    /**
     * Delete the levelThreeEffectiveness by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LevelThreeEffectiveness : {}", id);
        levelThreeEffectivenessRepository.deleteById(id);
    }
}
