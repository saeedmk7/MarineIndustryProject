package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.MonitorLearningProcessLevelService;
import com.marineindustryproj.domain.MonitorLearningProcessLevel;
import com.marineindustryproj.repository.MonitorLearningProcessLevelRepository;
import com.marineindustryproj.service.dto.MonitorLearningProcessLevelDTO;
import com.marineindustryproj.service.mapper.MonitorLearningProcessLevelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing MonitorLearningProcessLevel.
 */
@Service
@Transactional
public class MonitorLearningProcessLevelServiceImpl implements MonitorLearningProcessLevelService {

    private final Logger log = LoggerFactory.getLogger(MonitorLearningProcessLevelServiceImpl.class);

    private final MonitorLearningProcessLevelRepository monitorLearningProcessLevelRepository;

    private final MonitorLearningProcessLevelMapper monitorLearningProcessLevelMapper;

    public MonitorLearningProcessLevelServiceImpl(MonitorLearningProcessLevelRepository monitorLearningProcessLevelRepository, MonitorLearningProcessLevelMapper monitorLearningProcessLevelMapper) {
        this.monitorLearningProcessLevelRepository = monitorLearningProcessLevelRepository;
        this.monitorLearningProcessLevelMapper = monitorLearningProcessLevelMapper;
    }

    /**
     * Save a monitorLearningProcessLevel.
     *
     * @param monitorLearningProcessLevelDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MonitorLearningProcessLevelDTO save(MonitorLearningProcessLevelDTO monitorLearningProcessLevelDTO) {
        log.debug("Request to save MonitorLearningProcessLevel : {}", monitorLearningProcessLevelDTO);

        MonitorLearningProcessLevel monitorLearningProcessLevel = monitorLearningProcessLevelMapper.toEntity(monitorLearningProcessLevelDTO);
        monitorLearningProcessLevel = monitorLearningProcessLevelRepository.save(monitorLearningProcessLevel);
        return monitorLearningProcessLevelMapper.toDto(monitorLearningProcessLevel);
    }

    /**
     * Get all the monitorLearningProcessLevels.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MonitorLearningProcessLevelDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MonitorLearningProcessLevels");
        return monitorLearningProcessLevelRepository.findAll(pageable)
            .map(monitorLearningProcessLevelMapper::toDto);
    }


    /**
     * Get one monitorLearningProcessLevel by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MonitorLearningProcessLevelDTO> findOne(Long id) {
        log.debug("Request to get MonitorLearningProcessLevel : {}", id);
        return monitorLearningProcessLevelRepository.findById(id)
            .map(monitorLearningProcessLevelMapper::toDto);
    }

    /**
     * Delete the monitorLearningProcessLevel by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MonitorLearningProcessLevel : {}", id);
        monitorLearningProcessLevelRepository.deleteById(id);
    }
}
