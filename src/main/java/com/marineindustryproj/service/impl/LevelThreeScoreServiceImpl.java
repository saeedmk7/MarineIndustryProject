package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.LevelThreeScoreService;
import com.marineindustryproj.domain.LevelThreeScore;
import com.marineindustryproj.repository.LevelThreeScoreRepository;
import com.marineindustryproj.service.dto.LevelThreeScoreDTO;
import com.marineindustryproj.service.mapper.LevelThreeScoreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing LevelThreeScore.
 */
@Service
@Transactional
public class LevelThreeScoreServiceImpl implements LevelThreeScoreService {

    private final Logger log = LoggerFactory.getLogger(LevelThreeScoreServiceImpl.class);

    private final LevelThreeScoreRepository levelThreeScoreRepository;

    private final LevelThreeScoreMapper levelThreeScoreMapper;

    public LevelThreeScoreServiceImpl(LevelThreeScoreRepository levelThreeScoreRepository, LevelThreeScoreMapper levelThreeScoreMapper) {
        this.levelThreeScoreRepository = levelThreeScoreRepository;
        this.levelThreeScoreMapper = levelThreeScoreMapper;
    }

    /**
     * Save a levelThreeScore.
     *
     * @param levelThreeScoreDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LevelThreeScoreDTO save(LevelThreeScoreDTO levelThreeScoreDTO) {
        log.debug("Request to save LevelThreeScore : {}", levelThreeScoreDTO);

        LevelThreeScore levelThreeScore = levelThreeScoreMapper.toEntity(levelThreeScoreDTO);
        levelThreeScore = levelThreeScoreRepository.save(levelThreeScore);
        return levelThreeScoreMapper.toDto(levelThreeScore);
    }

    /**
     * Get all the levelThreeScores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LevelThreeScoreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LevelThreeScores");
        return levelThreeScoreRepository.findAll(pageable)
            .map(levelThreeScoreMapper::toDto);
    }


    /**
     * Get one levelThreeScore by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LevelThreeScoreDTO> findOne(Long id) {
        log.debug("Request to get LevelThreeScore : {}", id);
        return levelThreeScoreRepository.findById(id)
            .map(levelThreeScoreMapper::toDto);
    }

    /**
     * Delete the levelThreeScore by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LevelThreeScore : {}", id);
        levelThreeScoreRepository.deleteById(id);
    }
}
