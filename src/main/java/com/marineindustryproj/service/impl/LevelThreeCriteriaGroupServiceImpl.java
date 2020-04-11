package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.LevelThreeCriteriaGroupService;
import com.marineindustryproj.domain.LevelThreeCriteriaGroup;
import com.marineindustryproj.repository.LevelThreeCriteriaGroupRepository;
import com.marineindustryproj.service.dto.LevelThreeCriteriaGroupDTO;
import com.marineindustryproj.service.mapper.LevelThreeCriteriaGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing LevelThreeCriteriaGroup.
 */
@Service
@Transactional
public class LevelThreeCriteriaGroupServiceImpl implements LevelThreeCriteriaGroupService {

    private final Logger log = LoggerFactory.getLogger(LevelThreeCriteriaGroupServiceImpl.class);

    private final LevelThreeCriteriaGroupRepository levelThreeCriteriaGroupRepository;

    private final LevelThreeCriteriaGroupMapper levelThreeCriteriaGroupMapper;

    public LevelThreeCriteriaGroupServiceImpl(LevelThreeCriteriaGroupRepository levelThreeCriteriaGroupRepository, LevelThreeCriteriaGroupMapper levelThreeCriteriaGroupMapper) {
        this.levelThreeCriteriaGroupRepository = levelThreeCriteriaGroupRepository;
        this.levelThreeCriteriaGroupMapper = levelThreeCriteriaGroupMapper;
    }

    /**
     * Save a levelThreeCriteriaGroup.
     *
     * @param levelThreeCriteriaGroupDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LevelThreeCriteriaGroupDTO save(LevelThreeCriteriaGroupDTO levelThreeCriteriaGroupDTO) {
        log.debug("Request to save LevelThreeCriteriaGroup : {}", levelThreeCriteriaGroupDTO);

        LevelThreeCriteriaGroup levelThreeCriteriaGroup = levelThreeCriteriaGroupMapper.toEntity(levelThreeCriteriaGroupDTO);
        levelThreeCriteriaGroup = levelThreeCriteriaGroupRepository.save(levelThreeCriteriaGroup);
        return levelThreeCriteriaGroupMapper.toDto(levelThreeCriteriaGroup);
    }

    /**
     * Get all the levelThreeCriteriaGroups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LevelThreeCriteriaGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LevelThreeCriteriaGroups");
        return levelThreeCriteriaGroupRepository.findAll(pageable)
            .map(levelThreeCriteriaGroupMapper::toDto);
    }


    /**
     * Get one levelThreeCriteriaGroup by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LevelThreeCriteriaGroupDTO> findOne(Long id) {
        log.debug("Request to get LevelThreeCriteriaGroup : {}", id);
        return levelThreeCriteriaGroupRepository.findById(id)
            .map(levelThreeCriteriaGroupMapper::toDto);
    }

    /**
     * Delete the levelThreeCriteriaGroup by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LevelThreeCriteriaGroup : {}", id);
        levelThreeCriteriaGroupRepository.deleteById(id);
    }
}
