package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.LevelFourScoreService;
import com.marineindustryproj.domain.LevelFourScore;
import com.marineindustryproj.repository.LevelFourScoreRepository;
import com.marineindustryproj.service.dto.LevelFourScoreDTO;
import com.marineindustryproj.service.mapper.LevelFourScoreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing LevelFourScore.
 */
@Service
@Transactional
public class LevelFourScoreServiceImpl implements LevelFourScoreService {

    private final Logger log = LoggerFactory.getLogger(LevelFourScoreServiceImpl.class);

    private final LevelFourScoreRepository levelFourScoreRepository;

    private final LevelFourScoreMapper levelFourScoreMapper;

    public LevelFourScoreServiceImpl(LevelFourScoreRepository levelFourScoreRepository, LevelFourScoreMapper levelFourScoreMapper) {
        this.levelFourScoreRepository = levelFourScoreRepository;
        this.levelFourScoreMapper = levelFourScoreMapper;
    }

    /**
     * Save a levelFourScore.
     *
     * @param levelFourScoreDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LevelFourScoreDTO save(LevelFourScoreDTO levelFourScoreDTO) {
        log.debug("Request to save LevelFourScore : {}", levelFourScoreDTO);

        LevelFourScore levelFourScore = levelFourScoreMapper.toEntity(levelFourScoreDTO);
        levelFourScore = levelFourScoreRepository.save(levelFourScore);
        return levelFourScoreMapper.toDto(levelFourScore);
    }

    /**
     * Get all the levelFourScores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LevelFourScoreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LevelFourScores");
        return levelFourScoreRepository.findAll(pageable)
            .map(levelFourScoreMapper::toDto);
    }


    /**
     * Get one levelFourScore by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LevelFourScoreDTO> findOne(Long id) {
        log.debug("Request to get LevelFourScore : {}", id);
        return levelFourScoreRepository.findById(id)
            .map(levelFourScoreMapper::toDto);
    }

    /**
     * Delete the levelFourScore by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LevelFourScore : {}", id);
        levelFourScoreRepository.deleteById(id);
    }
}
