package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.MonitorLearningProcessGrade;
import com.marineindustryproj.repository.MonitorLearningProcessGradeRepository;
import com.marineindustryproj.service.MonitorLearningProcessService;
import com.marineindustryproj.domain.MonitorLearningProcess;
import com.marineindustryproj.repository.MonitorLearningProcessRepository;
import com.marineindustryproj.service.dto.MonitorLearningProcessDTO;
import com.marineindustryproj.service.dto.MonitorLearningProcessGradeDTO;
import com.marineindustryproj.service.mapper.MonitorLearningProcessGradeMapper;
import com.marineindustryproj.service.mapper.MonitorLearningProcessMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing MonitorLearningProcess.
 */
@Service
@Transactional
public class MonitorLearningProcessServiceImpl implements MonitorLearningProcessService {

    private final Logger log = LoggerFactory.getLogger(MonitorLearningProcessServiceImpl.class);

    private final MonitorLearningProcessRepository monitorLearningProcessRepository;

    private final MonitorLearningProcessMapper monitorLearningProcessMapper;

    private final MonitorLearningProcessGradeRepository monitorLearningProcessGradeRepository;

    private final MonitorLearningProcessGradeMapper monitorLearningProcessGradeMapper;

    public MonitorLearningProcessServiceImpl(MonitorLearningProcessRepository monitorLearningProcessRepository, MonitorLearningProcessMapper monitorLearningProcessMapper, MonitorLearningProcessGradeRepository monitorLearningProcessGradeRepository, MonitorLearningProcessGradeMapper monitorLearningProcessGradeMapper) {
        this.monitorLearningProcessRepository = monitorLearningProcessRepository;
        this.monitorLearningProcessMapper = monitorLearningProcessMapper;
        this.monitorLearningProcessGradeRepository = monitorLearningProcessGradeRepository;
        this.monitorLearningProcessGradeMapper = monitorLearningProcessGradeMapper;
    }

    /**
     * Save a monitorLearningProcess.
     *
     * @param monitorLearningProcessDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MonitorLearningProcessDTO save(MonitorLearningProcessDTO monitorLearningProcessDTO) {
        log.debug("Request to save MonitorLearningProcess : {}", monitorLearningProcessDTO);

        MonitorLearningProcess monitorLearningProcess = monitorLearningProcessMapper.toEntity(monitorLearningProcessDTO);
        monitorLearningProcess = monitorLearningProcessRepository.save(monitorLearningProcess);

        for (MonitorLearningProcessGradeDTO monitorLearningProcessGradeDTO : monitorLearningProcessDTO.getMonitorLearningProcessGrades()) {
            monitorLearningProcessGradeDTO.setMonitorLearningProcessId(monitorLearningProcess.getId());

            MonitorLearningProcessGrade monitorLearningProcessGrade = monitorLearningProcessGradeMapper.toEntity(monitorLearningProcessGradeDTO);
            monitorLearningProcessGrade = monitorLearningProcessGradeRepository.save(monitorLearningProcessGrade);
        }

        return monitorLearningProcessMapper.toDto(monitorLearningProcess);
    }

    /**
     * Get all the monitorLearningProcesses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MonitorLearningProcessDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MonitorLearningProcesses");
        return monitorLearningProcessRepository.findAll(pageable)
            .map(monitorLearningProcessMapper::toDto);
    }

    /**
     * Get all the MonitorLearningProcess with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<MonitorLearningProcessDTO> findAllWithEagerRelationships(Pageable pageable) {
        return monitorLearningProcessRepository.findAllWithEagerRelationships(pageable).map(monitorLearningProcessMapper::toDto);
    }
    

    /**
     * Get one monitorLearningProcess by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MonitorLearningProcessDTO> findOne(Long id) {
        log.debug("Request to get MonitorLearningProcess : {}", id);
        return monitorLearningProcessRepository.findOneWithEagerRelationships(id)
            .map(monitorLearningProcessMapper::toDto);
    }

    /**
     * Delete the monitorLearningProcess by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MonitorLearningProcess : {}", id);
        monitorLearningProcessRepository.deleteById(id);
    }
}
