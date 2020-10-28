package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.MonitorProcessDurationService;
import com.marineindustryproj.domain.MonitorProcessDuration;
import com.marineindustryproj.repository.MonitorProcessDurationRepository;
import com.marineindustryproj.service.dto.MonitorProcessDurationDTO;
import com.marineindustryproj.service.mapper.MonitorProcessDurationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing MonitorProcessDuration.
 */
@Service
@Transactional
public class MonitorProcessDurationServiceImpl implements MonitorProcessDurationService {

    private final Logger log = LoggerFactory.getLogger(MonitorProcessDurationServiceImpl.class);

    private final MonitorProcessDurationRepository monitorProcessDurationRepository;

    private final MonitorProcessDurationMapper monitorProcessDurationMapper;

    public MonitorProcessDurationServiceImpl(MonitorProcessDurationRepository monitorProcessDurationRepository, MonitorProcessDurationMapper monitorProcessDurationMapper) {
        this.monitorProcessDurationRepository = monitorProcessDurationRepository;
        this.monitorProcessDurationMapper = monitorProcessDurationMapper;
    }

    /**
     * Save a monitorProcessDuration.
     *
     * @param monitorProcessDurationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MonitorProcessDurationDTO save(MonitorProcessDurationDTO monitorProcessDurationDTO) {
        log.debug("Request to save MonitorProcessDuration : {}", monitorProcessDurationDTO);

        MonitorProcessDuration monitorProcessDuration = monitorProcessDurationMapper.toEntity(monitorProcessDurationDTO);
        monitorProcessDuration = monitorProcessDurationRepository.save(monitorProcessDuration);
        return monitorProcessDurationMapper.toDto(monitorProcessDuration);
    }

    /**
     * Get all the monitorProcessDurations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MonitorProcessDurationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MonitorProcessDurations");
        return monitorProcessDurationRepository.findAll(pageable)
            .map(monitorProcessDurationMapper::toDto);
    }


    /**
     * Get one monitorProcessDuration by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MonitorProcessDurationDTO> findOne(Long id) {
        log.debug("Request to get MonitorProcessDuration : {}", id);
        return monitorProcessDurationRepository.findById(id)
            .map(monitorProcessDurationMapper::toDto);
    }

    /**
     * Delete the monitorProcessDuration by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MonitorProcessDuration : {}", id);
        monitorProcessDurationRepository.deleteById(id);
    }
}
