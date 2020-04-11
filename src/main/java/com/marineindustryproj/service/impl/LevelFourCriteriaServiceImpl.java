package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.LevelFourCriteriaService;
import com.marineindustryproj.domain.LevelFourCriteria;
import com.marineindustryproj.repository.LevelFourCriteriaRepository;
import com.marineindustryproj.service.dto.LevelFourCriteriaDTO;
import com.marineindustryproj.service.mapper.LevelFourCriteriaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing LevelFourCriteria.
 */
@Service
@Transactional
public class LevelFourCriteriaServiceImpl implements LevelFourCriteriaService {

    private final Logger log = LoggerFactory.getLogger(LevelFourCriteriaServiceImpl.class);

    private final LevelFourCriteriaRepository levelFourCriteriaRepository;

    private final LevelFourCriteriaMapper levelFourCriteriaMapper;

    public LevelFourCriteriaServiceImpl(LevelFourCriteriaRepository levelFourCriteriaRepository, LevelFourCriteriaMapper levelFourCriteriaMapper) {
        this.levelFourCriteriaRepository = levelFourCriteriaRepository;
        this.levelFourCriteriaMapper = levelFourCriteriaMapper;
    }

    /**
     * Save a levelFourCriteria.
     *
     * @param levelFourCriteriaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LevelFourCriteriaDTO save(LevelFourCriteriaDTO levelFourCriteriaDTO) {
        log.debug("Request to save LevelFourCriteria : {}", levelFourCriteriaDTO);

        LevelFourCriteria levelFourCriteria = levelFourCriteriaMapper.toEntity(levelFourCriteriaDTO);
        levelFourCriteria = levelFourCriteriaRepository.save(levelFourCriteria);
        return levelFourCriteriaMapper.toDto(levelFourCriteria);
    }

    /**
     * Get all the levelFourCriteria.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LevelFourCriteriaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LevelFourCriteria");
        return levelFourCriteriaRepository.findAll(pageable)
            .map(levelFourCriteriaMapper::toDto);
    }


    /**
     * Get one levelFourCriteria by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LevelFourCriteriaDTO> findOne(Long id) {
        log.debug("Request to get LevelFourCriteria : {}", id);
        return levelFourCriteriaRepository.findById(id)
            .map(levelFourCriteriaMapper::toDto);
    }

    /**
     * Delete the levelFourCriteria by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LevelFourCriteria : {}", id);
        levelFourCriteriaRepository.deleteById(id);
    }
}
