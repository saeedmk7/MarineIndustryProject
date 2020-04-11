package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.repository.*;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.LevelFourEffectivenessService;
import com.marineindustryproj.service.dto.LevelFourEffectivenessDTO;
import com.marineindustryproj.service.dto.LevelFourScoreDTO;
import com.marineindustryproj.service.mapper.LevelFourEffectivenessMapper;
import com.marineindustryproj.service.mapper.LevelFourScoreMapper;
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
 * Service Implementation for managing LevelFourEffectiveness.
 */
@Service
@Transactional
public class LevelFourEffectivenessServiceImpl implements LevelFourEffectivenessService {

    private final Logger log = LoggerFactory.getLogger(LevelFourEffectivenessServiceImpl.class);

    private final LevelFourEffectivenessRepository levelFourEffectivenessRepository;

    private final LevelFourEffectivenessMapper levelFourEffectivenessMapper;

    private final LevelFourScoreMapper levelFourScoreMapper;

    private final LevelFourScoreRepository levelFourScoreRepository;

    private final FinalNiazsanjiReportPersonRepository finalNiazsanjiReportPersonRepository;

    private final FinalNiazsanjiReportRepository finalNiazsanjiReportRepository;

    private final EffectivenessPhaseRepository effectivenessPhaseRepository;

    public LevelFourEffectivenessServiceImpl(LevelFourEffectivenessRepository levelFourEffectivenessRepository, LevelFourEffectivenessMapper levelFourEffectivenessMapper, LevelFourScoreMapper levelFourScoreMapper, LevelFourScoreRepository levelFourScoreRepository, FinalNiazsanjiReportPersonRepository finalNiazsanjiReportPersonRepository, FinalNiazsanjiReportRepository finalNiazsanjiReportRepository, EffectivenessPhaseRepository effectivenessPhaseRepository) {
        this.levelFourEffectivenessRepository = levelFourEffectivenessRepository;
        this.levelFourEffectivenessMapper = levelFourEffectivenessMapper;
        this.levelFourScoreMapper = levelFourScoreMapper;
        this.levelFourScoreRepository = levelFourScoreRepository;
        this.finalNiazsanjiReportPersonRepository = finalNiazsanjiReportPersonRepository;
        this.finalNiazsanjiReportRepository = finalNiazsanjiReportRepository;
        this.effectivenessPhaseRepository = effectivenessPhaseRepository;
    }

    /**
     * Save a levelFourEffectiveness.
     *
     * @param levelFourEffectivenessDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LevelFourEffectivenessDTO saveWithScore(LevelFourEffectivenessDTO levelFourEffectivenessDTO) {
        log.debug("Request to save LevelFourEffectiveness : {}", levelFourEffectivenessDTO);

        LevelFourEffectiveness levelFourEffectiveness = levelFourEffectivenessMapper.toEntity(levelFourEffectivenessDTO);
        levelFourEffectiveness = levelFourEffectivenessRepository.save(levelFourEffectiveness);

        for (LevelFourScoreDTO levelFourScoreDTO : levelFourEffectivenessDTO.getLevelFourScores()) {
            LevelFourScore levelFourScore = levelFourScoreMapper.toEntity(levelFourScoreDTO);
            levelFourScore.setLevelFourEffectiveness(levelFourEffectiveness);
            levelFourScore.setCreateDate(ZonedDateTime.now());
            levelFourScore.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
            levelFourScore.setModifyDate(ZonedDateTime.now());
            levelFourScore.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
            levelFourScoreRepository.save(levelFourScore);
        }

        FinalNiazsanjiReportPerson finalNiazsanjiReportPerson = finalNiazsanjiReportPersonRepository.getOne(levelFourEffectiveness.getFinalNiazsanjiReportPerson().getId());
        finalNiazsanjiReportPerson.setStatus(20);
        finalNiazsanjiReportPerson.setLevelFourScore(levelFourEffectivenessDTO.getTotalScorePercent());
        finalNiazsanjiReportPerson.setModifyDate(ZonedDateTime.now());
        finalNiazsanjiReportPerson.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalNiazsanjiReportPersonRepository.save(finalNiazsanjiReportPerson);

        return levelFourEffectivenessMapper.toDto(levelFourEffectiveness);
    }
    @Override
    public LevelFourEffectivenessDTO save(LevelFourEffectivenessDTO levelFourEffectivenessDTO) {
        log.debug("Request to save LevelFourEffectiveness : {}", levelFourEffectivenessDTO);

        LevelFourEffectiveness levelFourEffectiveness = levelFourEffectivenessMapper.toEntity(levelFourEffectivenessDTO);
        levelFourEffectiveness = levelFourEffectivenessRepository.save(levelFourEffectiveness);

        return levelFourEffectivenessMapper.toDto(levelFourEffectiveness);
    }

    @Override
    public Boolean completeLevel(Long finalNiazsanjiReportId) throws Exception {
        FinalNiazsanjiReport finalNiazsanjiReport = finalNiazsanjiReportRepository.getOne(finalNiazsanjiReportId);
        if(finalNiazsanjiReport == null){
            throw new Exception("finalNiazsanjiReportId is not valid");
        }

        List<FinalNiazsanjiReportPerson> finalNiazsanjiReportPeopleNotFinished = finalNiazsanjiReport
            .getFinalNiazsanjiReportPeople().stream().filter(a -> a.getLevelFourScore() == null || a.getLevelFourScore() == 0)
            .collect(Collectors.toList());
        if(!finalNiazsanjiReportPeopleNotFinished.isEmpty()){
            throw new Exception("هنوز یکی از افراد اثربخشی نشده است");
        }

        Optional<EffectivenessPhase> effectivenessPhaseOptional = finalNiazsanjiReport.getEffectivenessPhases().stream()
            .filter(a -> a.getEffectivenessPhaseLevel().getEffectivenessLevel() == 4).findFirst();
        if(!effectivenessPhaseOptional.isPresent()){
            throw new Exception("finalNiazsanjiReportId is not valid");
        }

        EffectivenessPhase effectivenessPhase = effectivenessPhaseOptional.get();

        List<FinalNiazsanjiReportPerson> finalNiazsanjiReportPeople = finalNiazsanjiReport.getFinalNiazsanjiReportPeople()
            .stream().collect(Collectors.toList());

        float finalScore = (float) (finalNiazsanjiReportPeople.stream().mapToDouble(a -> a.getLevelFourScore()).sum()
            / finalNiazsanjiReportPeople.size());
        float weightedScore = (float) ((finalNiazsanjiReportPeople.stream().mapToDouble(a -> a.getLevelFourScore() *
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
     * Get all the levelFourEffectivenesses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LevelFourEffectivenessDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LevelFourEffectivenesses");
        return levelFourEffectivenessRepository.findAll(pageable)
            .map(levelFourEffectivenessMapper::toDto);
    }

    /**
     * Get all the LevelFourEffectiveness with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<LevelFourEffectivenessDTO> findAllWithEagerRelationships(Pageable pageable) {
        return levelFourEffectivenessRepository.findAllWithEagerRelationships(pageable).map(levelFourEffectivenessMapper::toDto);
    }
    

    /**
     * Get one levelFourEffectiveness by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LevelFourEffectivenessDTO> findOne(Long id) {
        log.debug("Request to get LevelFourEffectiveness : {}", id);
        return levelFourEffectivenessRepository.findOneWithEagerRelationships(id)
            .map(levelFourEffectivenessMapper::toDto);
    }

    /**
     * Delete the levelFourEffectiveness by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LevelFourEffectiveness : {}", id);
        levelFourEffectivenessRepository.deleteById(id);
    }
}
