package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.LevelThreeCriteriaService;
import com.marineindustryproj.domain.LevelThreeCriteria;
import com.marineindustryproj.repository.LevelThreeCriteriaRepository;
import com.marineindustryproj.service.dto.LevelThreeCriteriaDTO;
import com.marineindustryproj.service.mapper.LevelThreeCriteriaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing LevelThreeCriteria.
 */
@Service
@Transactional
public class LevelThreeCriteriaServiceImpl implements LevelThreeCriteriaService {

    private final Logger log = LoggerFactory.getLogger(LevelThreeCriteriaServiceImpl.class);

    private final LevelThreeCriteriaRepository levelThreeCriteriaRepository;

    private final LevelThreeCriteriaMapper levelThreeCriteriaMapper;

    public LevelThreeCriteriaServiceImpl(LevelThreeCriteriaRepository levelThreeCriteriaRepository, LevelThreeCriteriaMapper levelThreeCriteriaMapper) {
        this.levelThreeCriteriaRepository = levelThreeCriteriaRepository;
        this.levelThreeCriteriaMapper = levelThreeCriteriaMapper;
    }

    /**
     * Save a levelThreeCriteria.
     *
     * @param levelThreeCriteriaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LevelThreeCriteriaDTO save(LevelThreeCriteriaDTO levelThreeCriteriaDTO) {
        log.debug("Request to save LevelThreeCriteria : {}", levelThreeCriteriaDTO);

        LevelThreeCriteria levelThreeCriteria = levelThreeCriteriaMapper.toEntity(levelThreeCriteriaDTO);
        levelThreeCriteria = levelThreeCriteriaRepository.save(levelThreeCriteria);
        return levelThreeCriteriaMapper.toDto(levelThreeCriteria);
    }

    /**
     * Get all the levelThreeCriteria.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LevelThreeCriteriaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LevelThreeCriteria");
        return levelThreeCriteriaRepository.findAll(pageable)
            .map(levelThreeCriteriaMapper::toDto);
    }


    /**
     * Get one levelThreeCriteria by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LevelThreeCriteriaDTO> findOne(Long id) {
        log.debug("Request to get LevelThreeCriteria : {}", id);
        return levelThreeCriteriaRepository.findById(id)
            .map(levelThreeCriteriaMapper::toDto);
    }

    /**
     * Delete the levelThreeCriteria by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LevelThreeCriteria : {}", id);
        levelThreeCriteriaRepository.deleteById(id);
    }
}
